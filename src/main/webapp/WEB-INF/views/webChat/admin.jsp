<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<%@ include file="/WEB-INF/views/templates/header.jsp"%>

<section class="adminPage-section">
	<div class="introduce01-container">
		<div class="introduce01-bar">
			<div class="side-menu-title">
				<a href="<%=request.getContextPath()%>/animal/enroll">관리자페이지</a>
			</div>
			<hr class="side-hr" />
			<div class="side-menu">
				<a href="<%=request.getContextPath()%>/animal/enroll">동물등록</a>
			</div>
			<hr class="side-hr" />
			<div class="side-menu">
				<a href="<%=request.getContextPath()%>/admin/memberList">회원목록조회</a>
			</div>
			<hr class="side-hr" />
			<div class="side-menu">
				<a href="<%=request.getContextPath()%>/admin/webChatList">채팅기록조회</a>
			</div>
			<hr class="side-hr" />
			<div class="side-menu">
				<a href="<%=request.getContextPath()%>/admin">관리자채팅상담</a>
			</div>
			<hr class="side-hr" />
		</div>
		<div class="admin-detail-section">
			<div class="checked-title2">관리자채팅상담</div>
			<hr class="section-hr" />
		</div>
	</div>
</section>
<style>
</style>
</head>
	<%
	String loginMemberId = (String)request.getAttribute("loginMemberId");
	%>
<body>
	<div class="adminPage-board">
		<!-- 유저가 접속할 때마다 이 템플릿으로 채팅창을 생성한다. -->
		<div class="template" style="display: none">
			<form>
				<!-- 메시지 텍스트 박스 -->
				<input type="text" class="message"
					onkeydown="if(event.keyCode === 13) return false;">
				<!-- 전송 버튼 -->
				<input value="Send" type="button" class="sendBtn">
			</form>
			<br />
			<!-- 서버와 메시지를 주고 받는 콘솔 텍스트 영역 -->
			<textarea rows="10" cols="50" class="console" disabled="disabled"></textarea>
		</div>
	</div>
	<!-- 소스를 간단하게 하기 위하 Jquery를 사용했습니다. -->
	<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
	<script type="text/javascript">
		// 서버의 admin의 서블릿으로 웹 소켓을 한다.
		var webSocket = new WebSocket("ws://localhost:8080/hairball/admin");
		// 운영자에서의 open, close, error는 의미가 없어서 형태만 선언
		webSocket.onopen = function(message) {
		};
		webSocket.onclose = function(message) {
		};
		webSocket.onerror = function(message) {
		};
		
		// 채팅창을 감싸는 컨테이너 생성
		var $chatContainer = $("<div class='chat-container2'></div>");
		$("body").append($chatContainer);
		
		// 서버로 부터 메시지가 오면
		webSocket.onmessage = function(message) {
			// 메시지의 구조는 JSON 형태로 만들었다.
			let node = JSON.parse(message.data);
			// 메시지의 status는 유저의 접속 형태이다.
			// visit은 유저가 접속했을 때 알리는 메시지다.
			if (node.status === "visit") {
				// 위 템플릿 div를 취득한다.
				let form = $(".template").clone().removeClass('template').css('display', 'block');
				// div를 감싸고 속성 data-key에 unique키를 넣는다.
				form = $("<div class='float-left'></div>").attr("data-key",
						node.key).append(form);
				
		        // 컨테이너에 추가한다.
		        $chatContainer.append(form);
				
				// body에 추가한다.
				$(".chat-container").append(form);
				// message는 유저가 메시지를 보낼 때 알려주는 메시지이다.
			} else if (node.status === "message") {
				// key로 해당 div영역을 찾는다.
				let $div = $("[data-key='" + node.key + "']");
				// console영역을 찾는다.
				let log = $div.find(".console").val();
				// 아래에 메시지를 추가한다.
				let temp2 = "<%= loginMemberId %>";
				$div.find(".console").val(log + temp2 + " : " + node.message + "\n");
				// bye는 유저가 접속을 끊었을 때 알려주는 메시지이다.
			} else if (node.status === "bye") {
				// 해당 키로 div를 찾아서 dom을 제거한다.
				$("[data-key='" + node.key + "']").remove();
			}
		};
		// 전송 버튼을 클릭하면 발생하는 이벤트
		$(document).on("click", ".sendBtn", function() {
			// div 태그를 찾는다.
			let $div = $(this).closest(".float-left");
			// 메시지 텍스트 박스를 찾아서 값을 취득한다.
			let message = $div.find(".message").val();
			// 유저 key를 취득한다.
			let key = $div.data("key");
			// console영역을 찾는다.
			let log = $div.find(".console").val();
			// 아래에 메시지를 추가한다.
			$div.find(".console").val(log + "\n관리자 : " + message + "\n");
			// 텍스트 박스의 값을 초기화 한다.
			$div.find(".message").val("");
			// 웹소켓으로 메시지를 보낸다.
			webSocket.send(key + "#####" + message);
		});
		// 텍스트 박스에서 엔터키를 누르면
		$(document).on(
				"keydown",
				".message",
				function() {
					// keyCode 13은 엔터이다.
					if (event.keyCode === 13) {
						// 버튼을 클릭하는 트리거를 발생한다.
						$(this).closest(".float-left").find(".sendBtn")
								.trigger("click");
						// form에 의해 자동 submit을 막는다.
						return false;
					}
					return true;
				});
	</script>
<footer class="copyright2">
    <div class="copyright_img2">
        <img
                src="/hairball/images/로고/메뉴바_로고.png"
                alt="카피라이터 로고"
        />
    </div>
    <div class="copyright_line2"></div>
    <p>&lt;Copyright <strong>DoFighting</strong>. All rights reserved.&gt;</p>
</footer>
<script src="/hairball/js/jquery-3.7.0.js"></script>
<script src="/hairball/js/main.js"></script>
<script src="https://unpkg.com/swiper/swiper-bundle.min.js"></script>
</body>
</html>
