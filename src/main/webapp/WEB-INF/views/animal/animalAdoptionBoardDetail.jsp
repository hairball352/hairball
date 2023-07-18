<%@page import="com.sh.hairball.animal.model.vo.Animal"%>
<%@page import="com.sh.hairball.board.adoptboard.model.vo.AdopBoard"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/templates/header.jsp"%>

<%
	AdopBoard adopBoard = (AdopBoard)request.getAttribute("adopBoard");
	Animal animal = adopBoard.getAnimal();
%>
<section class="animal-section">
	<div class="introduce01-container">
		<div class="introduce01-bar">
			<div class="side-menu-title">
				<a href="<%=request.getContextPath()%>/animal/animalAdoptionList">입양신청</a>
			</div>
			<hr class="side-hr" />
			<div class="side-menu">
				<a href="<%=request.getContextPath()%>/animal/animalAdoptionList">입양신청</a>
			</div>
			<hr class="side-hr" />
		</div>
	</div>
	<div class="introduce01-detail-section">
		<div class="checked-title2">입양신청내역</div>
		<hr class="section-hr" />
		<div class="animal-detail-div">
			<div class="adoption-container">
				<%
				if (loginMember != null)
				%>
				<table id="adoption-detail-table">
					<thead>
						<tr>
							<td colspan="2"><img src="<%= request.getContextPath() %>/upload/animal/<%= animal.getRenamedFileName()%>" id="animal-profile-img"></td>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>진행단계</td>
							<td><%= animal.getState() %></td>
						</tr>
						<tr>
							<td>등록번호</td>
							<td><%= animal.getId() %></td>
						</tr>
						<tr>
							<td>방문 예정 날짜</td>
							<td><%= adopBoard.getVisitDate() %></td>
						</tr>
						<tr>
							<td colspan="2"><a href="<%= request.getContextPath()%>/animal/animalDetail?no=<%=animal.getId()%>">상세 동물 페이지로 이동하기</a></td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</section>
<%@ include file="/WEB-INF/views/templates/footer.jsp"%>