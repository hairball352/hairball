<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.sh.hairball.qnaboard.model.AnswerVo"%>
<%@page import="com.sh.hairball.member.model.vo.MemberRole"%>
<%@page import="java.util.List"%>
<%@page import="com.sh.hairball.qnaboard.model.QuestionVo"%>
<%@ page import="java.util.ArrayList" %>
<%@ include file="/WEB-INF/views/templates/header.jsp" %>
<%@ include file="/WEB-INF/views/templates/aside.jsp" %>
<%
	QuestionVo question = (QuestionVo) request.getAttribute("question");
	List<AnswerVo> answers = (List<AnswerVo>) request.getAttribute("answers");
%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/question.css" />
<section id="question-container">
	<h2>Q&A 게시판</h2>
	<%	if (loginMember != null) { %>
		<input 
			type="button" id="btn-add" value="질문하기" 
			onclick="location.href = '<%= request.getContextPath() %>/qnaBoard/questionCreate';"/>
	<%  } %>
	<table id="tbl-question-view">
		<tr>
			<th>글번호</th>
			<td><%= question.getId() %></td>
		</tr>
		<tr>
			<th>제 목</th>
			<td><%= question.getTitle() %></td>
		</tr>
		<tr>
			<th>작성자</th>
			<td><%= question.getMemberId() %></td>
		</tr>
		<tr>
			<th>내 용</th>
			<td>
				<textarea readonly style="resize: none;" rows="10"><%= question.getContent() %></textarea>
			</td>
		</tr>
		<%-- 작성자와 관리자만 마지막행 수정/삭제버튼이 보일수 있게 할 것 --%>
		<% if (loginMember != null && (loginMember.getMemberRole() == MemberRole.A 
							|| loginMember.getMemberId().equals(question.getMemberId()) ) ) { %>
		<tr>
			<th colspan="2">
				<%--게시물 수정 --%>
				<input type="button" value="수정하기" onclick="updateQuestion()">
				<input type="button" value="삭제하기" onclick="deleteQuestion()">
			</th>
		</tr>
		<% } %>
	</table>
	
	<hr style="margin-top:30px;" />	
    
	<div class="answer-container">
        <div class="answer-editor">
            <form
				action="<%=request.getContextPath()%>/qnaBoard/answerCreate" 
				method="post" 
				name="boardanswerFrm">
                <input type="hidden" name="questionId" value="<%= question.getId() %>" />
                <input type="hidden" name="memberId" value="<%= loginMember != null ? loginMember.getMemberId() : "" %>" />
				<textarea name="content" cols="60" rows="3"></textarea>
                <button type="submit" id="btn-answer-enroll1">등록</button>
            </form>
        </div>
		<!--table#tbl-answer-->
		<%	if(answers != null && !answers.isEmpty()) { %>
			<table id="tbl-answer">
				<%
					for(AnswerVo as : answers) {
						boolean canRemove = 
								loginMember != null && 
								(loginMember.getMemberId().equals(as.getMemberId())
								  || MemberRole.A == loginMember.getMemberRole());
				%>
							<tr class="level1">
								<td>
									<sub class=answer-MemberId><%= as.getMemberId() %></sub>
									<sub class=answer-date><%= as.getRegDate() %></sub>
									<br />
									<%= as.getContent() %>
								</td>
								<td>
									<% 	if (canRemove) { %>
									<%-- 로그인하고, 작성자본인 또는 관리자인 경우만 노출 --%>
									<button class="btn-delete" value="<%= as.getId() %>">삭제</button>
									<%  } %>
								</td>
							</tr>
				<%
						}	
				%>
			</table>
		<% 	} %>
	</div>
	<form 
		action="<%= request.getContextPath() %>/qnaBoard/answerDelete" 
		name="answerDelFrm"
		method="POST">
		<input type="hidden" name="id" />
		<input type="hidden" name="questionId" value="<%= question.getId() %>"/>
	</form>
	<script>
	document.querySelectorAll(".btn-delete").forEach((button) => {
		button.onclick = (e) => {
			if(confirm("해당 댓글을 삭제하시겠습니까?")){
				const frm = document.answerDelFrm;
				const {value} = e.target;
				console.log(value);
				frm.id.value = value; // value에 e.target으로 코멘트no 넣어줌
				frm.submit();
			}
		}
	});
	
	// 이벤트버블링을 이용한 textarea focus핸들러
	// focus, blur 버블링되지 않음. 대신 focusin, focusout 사용.
	document.addEventListener("focusin", (e) => {
		if(e.target.matches("form[name=boardanswerFrm] textarea")) {
			<% 	if (loginMember == null) { %>
				loginAlert();
			<% 	} %>
		}
	});
	
	// 이벤트버블링을 이용한 폼유효성 검사 
	document.addEventListener("submit", (e) => {
		
		// 특정선택자와 매칭여부 matches
		if (e.target.matches("form[name=answerFrm]")) {			
			<% 	if (loginMember == null) { %>
				loginAlert();
				e.preventDefault();
				return;
			<% 	} %>
			
			const frm = e.target;
			const content = frm.content;
			
			if(!/^(.|\n)+$/.test(content.value)) {
				alert("내용을 작성해주세요.");
				e.preventDefault();
				return;
			}
		}
		
	});
	
	const loginAlert = () => {
		alert("로그인후 댓글을 작성할 수 있습니다.");
		document.querySelector("#memberId").focus();
	};

	</script>
</section>

	<form 
		name="questionDelFrm" 
		action="<%= request.getContextPath() %>/qnaBoard/questionDelete" 
		method="POST">
		<input type="hidden" name="id" value="<%= question.getId() %>" />
	</form>
	
<script>
const deleteQuestion = () => {
	if(confirm("정말 삭제하시겠습니까?"))
		document.questionDelFrm.submit();
}

const updateQuestion = () => {
	location.href = "<%= request.getContextPath() %>/qnaBoard/questionUpdate?id=<%= question.getId() %>";
}
</script>
<%@ include file="/WEB-INF/views/templates/footer.jsp" %>
