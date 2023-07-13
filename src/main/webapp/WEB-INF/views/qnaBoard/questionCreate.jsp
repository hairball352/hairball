<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/templates/header.jsp" %>
<%@ include file="/WEB-INF/views/templates/aside.jsp" %>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/question.css" />
<section id="question-container">
	<h2>게시판 작성</h2>
	<form
		name="questionCreateFrm"
		action="<%=request.getContextPath() %>/qnaBoard/questionCreate"
		method="post"
		enctype="multipart/form-data">
		<table id="tbl-question-view">
		<tr>
			<th>제 목</th>
			<td><input type="text" name="title" required></td>
		</tr>
		<tr>
			<th>작성자</th>
			<td>
				<input type="text" name="memberId" value="<%= loginMember.getMemberId() %>" readonly/>
			</td>
		</tr>
		<tr>
			<th>내 용</th>
			<td><textarea rows="5" cols="40" name="content"></textarea></td>
		</tr>
		<tr>
			<th colspan="2">
				<input type="submit" value="등록하기">
			</th>
		</tr>
	</table>
	</form>
</section>
<script>
/**
* questionCreateFrm 유효성 검사
*/
document.questionCreateFrm.onsubmit = (e) => {
	const frm = e.target;
	const title = e.target.title;
	const content = e.target.content;
	//제목을 작성하지 않은 경우 폼제출할 수 없음.
	if(!/^.+$/.test(title.value)) {
		alert("제목을 작성해주세요.");
		return false;
	}				   
	//내용을 작성하지 않은 경우 폼제출할 수 없음.
	if(!/^(.|\n)+$/.test(content.value)){
		alert("내용을 작성해주세요.");
		return false;
	}
};
</script>
<%@ include file="/WEB-INF/views/templates/footer.jsp" %>
