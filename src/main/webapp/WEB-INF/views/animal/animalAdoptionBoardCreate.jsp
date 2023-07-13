<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/templates/header.jsp"%>
<%@ include file="/WEB-INF/views/templates/header2.jsp"%>
<%-- 입양 신청 게시글 작성하는 jsp --%>
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
				<img src="/hairball/images/1687851928029.png" alt="" style="width:500px" />
				<form
					name="adoptionFrm"
					action="<%=request.getContextPath() %>/animal/animalAdoptionBoardCreate" 
					method="post"
				>
					<table class="adoption-board-table">
						<tr>
							<td>등록 동물 번호</td>
							<td><input type="text" name="animalId" /></td>
						</tr>
						<tr>
							<td>아이디</td>
							<td><input type="text" name="memberId" /></td>
						</tr>
						<tr>
							<td>방문날짜</td>
							<td><input type="date" name="visitDate" /></td>
						</tr>
					</table>
				</form>
			</div>
			<div class="adop-btn">
				<button onclick="backbtn();">돌아가기</button>
				<button onclick="adoptionFrmSubmit();">신청하기</button>
			</div>
		</div>
	</div>
</section>
<script>
	const backbtn = () => {
		window.history.back();
	}
	
	const adoptionFrmSubmit = (e) => {
		const frm = document.forms.adoptionFrm;;
		if(confirm('정말 신청하시겠습니까?')) {
			frm.submit();
			return true;
		}
		alert('작성을 취소하였습니다.');
		return false;
	}
	
</script>
<%@ include file="/WEB-INF/views/templates/footer.jsp"%>