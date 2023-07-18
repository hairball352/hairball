<%@page import="com.sh.hairball.member.model.vo.Member"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Web Socket Example</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/webchat.css" />
	<script src="<%= request.getContextPath() %>/js/jquery-3.7.0.js"></script>
</head>
	<%
	String loginMember = (String)request.getAttribute("loginMemberName");
	Member loginMember2 = (Member) session.getAttribute("loginMember");
	%>
<body>
	<!-- 채팅 영역 -->
	<div class="template">
		<br />
		<div id="messageContainer"></div>
		<!-- 서버와 메시지를 주고 받는 콘솔 텍스트 영역 -->
		<textarea id="messageTextArea" rows="30" cols="52" disabled="disabled"></textarea>
	</div>
	<form>
		<!-- 텍스트 박스에 채팅의 내용을 작성한다. -->
		<input id="textMessage" type="text" onkeydown="return enter()"
			style="width: 310px; height: 39px;">
		<!-- 서버로 메시지를 전송하는 버튼 -->
		<input onclick="sendMessage()" value="전송" type="button" style="margin-left: 12px;">
		<input onclick="endChat()" value="채팅종료" type="button" style="margin-top: 5px;">
	</form>
	
	<script type="text/javascript">
		// 서버의 broadsocket의 서블릿으로 웹 소켓을 한다.
		var webSocket = new WebSocket(
				"ws://localhost:8080/hairball/broadsocket");
		// 콘솔 텍스트 영역
		var messageTextArea = document.getElementById("messageTextArea");
		// 메시지 컨테이너
		var messageContainer = document.getElementById("messageContainer");
		// 접속이 완료되면
		webSocket.onopen = function(message) {
			// 콘솔에 메시지를 남긴다.
			let temp = "<%= loginMember %>";
			messageTextArea.value += "✨" + temp + "님 환영합니다✨ \n\n관리자가 접속 중입니다. 잠시만 기다려주세요.\n";
		};
		// 접속이 끝기는 경우는 브라우저를 닫는 경우이기 때문에 이 이벤트는 의미가 없음.
		webSocket.onclose = function(message) {
		};

		//창을 닫을 때 웹소켓 연결을 종료
		function endChat() {
		  // 웹소켓 연결을 종료
		  webSocket.close();
		  // 채팅 기록을 서버에 저장
		  saveChatHistoryToDB();
		}

		// 에러가 발생하면
		webSocket.onerror = function(message) {
		// 콘솔에 메시지를 남긴다.
			messageTextArea.value += "error...\n";
		};
		 // 서버로부터 메시지가 도착하면 콘솔 화면에 메시지를 남긴다.
	    webSocket.onmessage = function(message) {
	      messageTextArea.value += "\n관리자 : " + message.data + "\n";
	    };
		//채팅 기록을 저장할 배열을 추가
		var chatHistoryAll = [];

		// 서버로 메시지를 발송하는 함수
		// Send 버튼을 누르거나 텍스트 박스에서 엔터를 치면 실행
		function sendMessage() {
			// 텍스트 박스의 객체를 가져옴
			let message = document.getElementById("textMessage");
			// 콘솔에 메세지를 남긴다.
			messageTextArea.value += "\n나 : " + message.value + "\n";
			chatHistoryAll.push(message.value); // 메시지를 채팅 기록에 추가
			// 소켓으로 보낸다.
			webSocket.send(message.value);
			// 텍스트 박스 초기화
			message.value = "";
		}
		// 텍스트 박스에서 엔터를 누르면
		function enter() {
			// keyCode 13은 엔터이다.
			if (event.keyCode === 13) {
				// 서버로 메시지 전송
				sendMessage();
				// form에 의해 자동 submit을 막는다.
				return false;
			}
			return true;
		}
		// chatHistory 배열을 서버에 전송하고, 서버는 이 배열을 데이터베이스에 저장하는 함수
		function saveChatHistoryToDB() {
			$.ajax({
				url: "/hairball/saveChatHistory",
				type: "POST",
				data: JSON.stringify(chatHistoryAll),
				contentType: "application/json",
				success: function(response) {
					console.log("db 저장 성공!");
					chatHistoryAll = []; // 채팅 기록이 서버에 저장된 후에는 배열을 비워준다.
				},
				error: function(xhr, status, error) {
					console.log("db 저장 실패ㅠㅠ: " + status, error);
				}
			});
		}
		
		// 페이지가 로드되었을 때 채팅 기록을 가져오는 함수
		function loadChatHistoryFromDB() {
		    let memberId = '<%=loginMember2.getId()%>';
		    console.log(memberId);

		    $.ajax({
		        url: '<%=request.getContextPath()%>/loadChatHistory',
		        type: 'GET',
		        data: {memberId: memberId},
		        dataType: 'json', // JSON 데이터로 응답을 받기 위해 dataType을 설정
		        success: function(response) {
		            console.log(response);
		            // 서버로부터 받아온 채팅 내역을 화면에 추가
		            if (response) { // 응답 데이터를 확인하여 null 또는 undefined가 아닌지 검사
		                for (let i = 0; i < response.length; i++) {
		                    // 서버로부터 받은 메시지 내용을 textarea에 추가
		                    messageTextArea.value += "\n이전 채팅 : " + response[i].content + "\n";
		                }
		                // 스크롤을 최하단으로 이동 (최근 메시지 보기)
		                messageContainer.scrollTop = messageContainer.scrollHeight;
		            } else {
		                console.log("응답 데이터가 유효하지 않습니다.");
		            }
		        },
		        error: function(xhr, status, error) {
		            console.log("db 로딩 실패ㅠㅠ: " + status, error);
		        }
		    });
		}

		// 페이지 로드가 완료되면 채팅 기록을 불러옵니다.
		$(document).ready(function() {
		    loadChatHistoryFromDB();
		});
	</script>
</body>
</html>