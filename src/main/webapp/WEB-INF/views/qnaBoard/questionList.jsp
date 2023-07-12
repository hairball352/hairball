<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%>
<%@ page import="com.sh.hairball.qnaboard.model.QuestionVo" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/templates/header.jsp" %>
<%@ include file="/WEB-INF/views/templates/aside.jsp" %>
<%
	List<QuestionVo> Questions = (List<QuestionVo>) request.getAttribute("Questions");
%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/question.css" />
<section id="question-container">
	<h2>게시판 </h2>
	
	<%	if (loginMember != null) { %>
		<input 
			type="button" id="btn-add" value="글쓰기" 
			onclick="location.href = '<%= request.getContextPath() %>/question/questionCreate';"/>
	<%  } %>
	
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
				if(Questions != null && !Questions.isEmpty()){
					for(QuestionVo question : Questions){
			%>
						<tr>
							<td><%= question.getId() %></td>
							<td><%= question.getTitle() %></td>
							<td><%= question.getMemberId() %></td>
							<td><%= question.getRegDate() %></td>
						</tr>
			<%
					}
				} 
				else { 
			%>
				<tr>
					<td colspan="5">조회된 질문이 없습니다.</td>
				</tr>
			<% } %>
		</tbody>
	</table>

	<div id='pagebar'>
		<%= request.getAttribute("pagebar") %>
	</div>
</section>
<%@ include file="/WEB-INF/views/templates/footer.jsp" %>
