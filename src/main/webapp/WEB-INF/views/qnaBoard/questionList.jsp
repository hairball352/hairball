<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%>
<%@ page import="com.sh.hairball.qnaboard.model.QuestionVo"%>
<%@ include file="/WEB-INF/views/templates/header.jsp"%>
<%@ include file="/WEB-INF/views/templates/header2.jsp"%>
<%@ include file="/WEB-INF/views/templates/aside.jsp"%>
<%
List<QuestionVo> questions = (List<QuestionVo>) request.getAttribute("questions");
%>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/question.css" />
<section class="question-section">
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
			<%
			if (loginMember != null) {
			%>
			<input type="button" id="btn-add" value="글쓰기"
				onclick="location.href = '<%=request.getContextPath()%>/qnaBoard/questionCreate';" />
			<%
			}
			%>

			<table id="tbl-question">
				<thead>
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>작성자</th>
						<th>작성일</th>
					</tr>
				</thead>
				<tbody>
					<%
					if (questions != null && !questions.isEmpty()) {
						for (QuestionVo question : questions) {
					%>
					<tr>
						<td><%=question.getId()%></td>
						<td><a
							href="<%=request.getContextPath()%>/qnaBoard/questionDetail?id=<%=question.getId()%>"><%=question.getTitle()%></a></td>
						<td><%=question.getMemberId()%></td>
						<td><%=question.getRegDate()%></td>
					</tr>
					<%
					}
					} else {
					%>
					<tr>
						<td colspan="4">조회된 질문이 없습니다.</td>
					</tr>
					<%
					}
					%>
				</tbody>
			</table>

			<div id='pagebar'>
				<%=request.getAttribute("pagebar")%>
			</div>
		</section>
	</div>
</section>
<%@ include file="/WEB-INF/views/templates/footer.jsp"%>
