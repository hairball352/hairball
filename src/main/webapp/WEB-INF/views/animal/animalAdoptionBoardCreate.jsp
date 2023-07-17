<%@page import="com.sh.hairball.animal.model.vo.Animal"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/templates/header.jsp"%>
<%-- 입양 신청 게시글 작성하는 jsp --%>
<%
	Animal animal = (Animal) request.getAttribute("animal");
%>
<section class="adoption-board-section">
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
		<div class="checked-title2">입양신청</div>
		<hr class="section-hr" />
		<div class="adoption-board">
			<div id="guide-text">
				<p>게시글 작성해주시면 순차적으로 연락드리겠습니다.</p>
			</div>
			<div>
				<img src="/hairball/images/1687851928029.png" alt=""
					style="width: 500px" />
				<form name="adoptionFrm"
					action="<%=request.getContextPath()%>/animal/animalAdoptionBoardCreate"
					method="post">
					<table class="adoption-board-table">
						<tr>
							<td>등록 동물 번호</td>
							<td>
								<div class="ui-widget">
								<% if(animal == null) { %>
									<label for="animalList"></label><input id="animalList" name="animalPblId" value="">
								<% } else { %>
									<label for="animalList"></label><input id="animalList" name="animalPblId" value="<%= animal.getPblId() %>">
								<% } %>
								</div>
							</td>
						</tr>
						<tr>
							<td>아이디</td>
							<td><input type="text" value="<%= loginMember != null ? loginMember.getMemberId() : "" %>"/>
							<input type="hidden" name="memberId" value="<%= loginMember != null ? loginMember.getId() : "" %>"/></td>
						</tr>
						<tr>
							<td>방문날짜</td>
							<td><input type="date" name="visitDate" /></td>
						</tr>
					</table>
				</form>
			</div>
			<div class="">
				<button onclick="backbtn();" class="btn1">돌아가기</button>
				<button onclick="adoptionFrmSubmit();" class="btn1">신청하기</button>
			</div>
		</div>
	</div>
</section>
<script>
	const backbtn = () => {
		window.history.back();
	};
	
	const adoptionFrmSubmit = (e) => {
		const frm = document.forms.adoptionFrm;;
		if(confirm('정말 신청하시겠습니까?')) {
			frm.submit();
			return true;
		}
		alert('작성을 취소하였습니다.');
		return false;
	};
	
	$("#animalList").autocomplete({
	    source: function(request, response) {
	        const { term } = request;

	        $.ajax({
	            url: "<%=request.getContextPath()%>/animal/autocomplete",
	            method: "GET",
	            dataType: "text",
	            data: {
	                term
	            },
	            success: function(animals) {
	                if (animals === '') return;
	                console.log(animals);

	                const temp = animals.split(",");
	                const arr = temp.map((no) => ({
	                    label: no,
	                    value: no
	                }));
	                console.log(arr);
	                response(arr);
	            }
	        });
	    },
	    select: function(event, selected) {
	        const { temp: { label } } = selected;
	    }
	});
</script>
<%@ include file="/WEB-INF/views/templates/footer.jsp"%>