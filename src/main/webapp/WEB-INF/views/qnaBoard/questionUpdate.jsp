<%@page import="java.util.List"%>
<%@ page import="com.sh.hairball.qnaboard.model.QuestionVo" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/templates/header.jsp" %>
<%@ include file="/WEB-INF/views/templates/aside.jsp" %>
<%
	QuestionVo b = (QuestionVo) request.getAttribute("question");
%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/question.css" />
<section id="question-container">
<h2>게시판 수정</h2>
<form action="<%=request.getContextPath() %>/qnaBoard/questionUpdate"
	  method="post">
	<table id="tbl-question-view">
	<tr>
		<th>번호</th>
		<td>
			<input type="text" name="id" value="<%= q.getId() %>" readonly/>
		</td>
	</tr>
	<tr>
		<th>제 목</th>
		<td><input type="text" name="title" value="<%= b.getTitle() %>" required></td>
	</tr>
	<tr>
		<th>작성자</th>
		<td>
			<input type="text" name="writer" value="<%= b.getMemberId() %>" readonly/>
		</td>
	</tr>
	<tr>
		<th>내 용</th>
		<td><textarea rows="5" cols="50" name="content"><%= b.getContent() %></textarea></td>
	</tr>
	<tr>
		<th colspan="2">
			<input type="submit" value="수정하기">
			<input type="button" value="취소" onclick="history.go(-1);">
		</th>
	</tr>
</table>
</form>
</section>
<script>
document.questionUpdateFrm.onsubmit = (e) => {
	const frm = e.target;
	//제목을 작성하지 않은 경우 폼제출할 수 없음.
	const titleVal = frm.title.value.trim(); // 좌우공백
	if(!/^.+$/.test(titleVal)){
		alert("제목을 작성해주세요.");
		frm.title.select();
		return false;
	}		
					   
	//내용을 작성하지 않은 경우 폼제출할 수 없음.
	const contentVal = frm.content.value.trim();
	if(!/^(.|\n)+$/.test(contentVal)){
		alert("내용을 작성해주세요.");
		frm.content.select();
		return false;
	}
}
</script>
<%@ include file="/WEB-INF/views/templates/footer.jsp" %>
