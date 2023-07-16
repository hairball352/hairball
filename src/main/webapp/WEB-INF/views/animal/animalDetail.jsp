<%@page import="com.sh.hairball.attachment.model.vo.Attachment"%>
<%@page import="com.sh.hairball.animal.model.vo.Animal"%>
<%@ page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/templates/header.jsp"%>
<%
	Animal animal = (Animal) request.getAttribute("animal");
	Attachment img = (Attachment) request.getAttribute("attachment");
%>


<section class="animal-section">
	<div class="introduce01-container">
		<div class="introduce01-bar">
			<div class="side-menu-title">
				<a href="<%=request.getContextPath()%>/animal/animalAdoptionList">입양신청</a>
			</div>
			<hr class="side-hr" />
			<div class="side-menu">
				<a href="<%=request.getContextPath()%>/animal/animalAdoptionList">보호동물</a>
			</div>
			<hr class="side-hr" />
		</div>
	</div>
	<div class="introduce01-detail-section">
		<div class="checked-title2">동물 상세 정보</div>
		<hr class="section-hr" />
		<div class="animal-detail-div">
			<div class="adoption-container">
				<%
				if (loginMember != null)
				%>
				<table>
					<thead>
					<tr>
						<th><img alt="" src="">사진넣을공간</th>
					</tr>
					</thead>
					<tbody>
						<tr>
							<th colspan="4">입양상태
								<td colspan="6" id="state"><%=animal.getState() %></td>
							</th>
							<th colspan="4">동물등록번호
								<td colspan="6" id="pblId"><%=animal.getPblId()%></td>
							</th>
						</tr>
						<tr>
							<th colspan="4">견종
								<td colspan="6" id="species"><%=animal.getSpecies()%></td>
							</th>
							<th colspan="4">성별
								<td colspan="6" id="sex"><%= animal.getSex() %></td>
							</th>
						</tr>
						<tr>
							<th colspan="4">나이(연생)
								<td colspan="6" id="age"><%= animal.getAge() %></td>
							</th>
							<th colspan="4">몸무게
								<td colspan="6" id="weight"><%=animal.getWeight() %></td>
							</th>
						</tr>
						<tr>
							<th colspan="4">발견장소
								<td colspan="16" id="discoveryPlace"><%= animal.getDiscoveryPlace() %></td>
							</th>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</section>

<%@ include file="/WEB-INF/views/templates/footer.jsp"%>

