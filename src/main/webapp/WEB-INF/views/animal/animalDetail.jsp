<%@page import="com.sh.hairball.attachment.model.vo.Attachment"%>
<%@page import="com.sh.hairball.animal.model.vo.Animal"%>
<%@ page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/templates/header.jsp"%>
<%
Animal animal = (Animal) request.getAttribute("animal");
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
			<div class="animal-detail-container">
				<table id="detail-table">
					<thead>
						<tr>
							<img
								src="<%=request.getContextPath()%>/upload/animal/<%=animal.getRenamedFileName()%>"
								id="animal-profile-img" />
						</tr>
					</thead>
					<tbody>
						<tr>
							<%
							if (loginMember != null && loginMember.getMemberRole().name().equals("A")) {
							%>
							<div>
								<select id="procedureState">
									<option value="0">입양가능</option>
									<option value="1">센터 방문 대기</option>
									<option value="2">교육 수료중</option>
									<option value="3">입양 확정</option>
								</select>
							</div>
							<%
							} else {
							%>
						<tr>
							<th colspan="10">입양상태
							<td colspan="30" id="state"><%=stateArr[animal.getState()]%></td>
							</th>
						</tr>
						<%
						}
						%>
						<tr>
							<th colspan="10">동물등록번호</th>
							<td colspan="30" id="pblId"><%=animal.getPblId()%></td>
						</tr>
						<tr>
							<th colspan="10">견종</th>
							<td colspan="20" id="species"><%=animal.getSpecies()%></td>
							<th colspan="5">성별</th>
							<td colspan="5" id="sex"><%=animal.getSex()%></td>
						</tr>
						<tr>
							<th colspan="10">나이(연생)</th>
							<td colspan="20" id="age"><%=animal.getAge()%></td>
							<th colspan="5">몸무게</th>
							<td colspan="5" id="weight"><%=animal.getWeight()%></td>
						</tr>
						<tr>
							<th colspan="10">발견장소</th>
							<td colspan="30" id="discoveryPlace"><%=animal.getDiscoveryPlace()%></td>
						</tr>
					</tbody>
				</table>
				<form name="animalAdopFrm" method="GET"
					action="<%=request.getContextPath()%>/animal/animalAdoptionBoardCreate">
					<input type="hidden" name="no" value="<%=animal.getId()%>" />
					<button class="btn1" type="submit">입양하러가기</button>
				</form>
			</div>
			<%
			if (loginMember != null && loginMember.getMemberRole().name().equals("A")) {
			%>
			<a
				href="<%=request.getContextPath()%>/animal/delete?animalId=<%=animal.getId()%>">정보삭제하기</a>
			<%
			}
			%>
		</div>
	</div>
</section>
<script>
<%if (loginMember != null && loginMember.getMemberRole().name().equals("A")) {%>
window.onload = () => {
	const selector = document.querySelector('#procedureState');
	selector.selectedIndex = <%=animal.getState()%>;

	selector.addEventListener('change', () => {
		const selectedOption = selector.options[selector.selectedIndex];
		console.log(selectedOption.value);

		const confirmMessage = "정말 변경하시겠습니까?";
		if(confirm(confirmMessage)) {
		$.ajax({
			url : `<%=request.getContextPath()%>/animal/update?animalId=<%=animal.getId()%>&state=\${selectedOption.value}`,
			success(resp){
				console.log(resp)
			}
		})
		}
	});
};
<%}%>


</script>

<%@ include file="/WEB-INF/views/templates/footer.jsp"%>

