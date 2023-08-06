<%@page import="java.util.List"%>
<%@ page import="com.sh.hairball.qnaboard.model.QuestionVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/templates/header.jsp"%>
<%
QuestionVo q = (QuestionVo) request.getAttribute("question");
%>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/question.css" />
<section class="animal-section">
	<div class="introduce01-container">
		<div class="introduce01-bar">
			<div class="side-menu-title">
				<a>참여소통</a>
			</div>
			<hr class="side-hr" />
			<div class="side-menu">
				<a href="<%=request.getContextPath()%>/qnaBoard/questionIntro">자주하는질문</a>
			</div>
			<hr class="side-hr" />
			<div class="side-menu">
				<a href="<%=request.getContextPath()%>/qnaBoard/questionList">Q&A</a>
			</div>
			<hr class="side-hr" />
		</div>
	</div>
	<div class="introduce01-detail-section">
		<div class="checked-title2">Q&A</div>
		<hr class="section-hr" />
		<section id="question-container">
			<h2 id="h2-title">Q&A 수정</h2>
			<form action="<%=request.getContextPath()%>/qnaBoard/questionUpdate"
				method="post">
				<table id="tbl-question-view">
					<tr>
						<th>번호</th>
						<td><input type="text" name="id" value="<%=q.getId()%>"
							readonly /></td>
					</tr>
					<tr>
						<th>제 목</th>
						<td><input type="text" name="title" value="<%=q.getTitle()%>"
							required></td>
					</tr>
					<tr>
						<th>작성자</th>
						<td><input type="text" name="memberId"
							value="<%=q.getMemberId()%>" readonly /></td>
					</tr>
					<tr>
						<th>내 용</th>
						<td><textarea rows="5" cols="50" name="content"><%=q.getContent()%></textarea></td>
					</tr>
					<tr>
						<th colspan="2"><input type="submit" id="btn-update" value="수정하기"> <input
							type="button" value="취소" id="btn-cancel" onclick="history.go(-1);"></th>
					</tr>
				</table>
			</form>
	</div>
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
<%@ include file="/WEB-INF/views/templates/footer.jsp"%>