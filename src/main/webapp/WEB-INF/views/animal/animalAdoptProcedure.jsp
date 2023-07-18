<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/templates/header.jsp" %>

<section class="animal-section">
	<div class="introduce01-container">
		<div class="introduce01-bar">
			<div class="side-menu-title">
				<a href="<%= request.getContextPath() %>/animal/procedure">보호동물</a>
			</div>
			<hr class="side-hr" />
			<div class="side-menu">
				<a href="<%= request.getContextPath() %>/animal/procedure">입양절차</a>
			</div>
			<hr class="side-hr" />
			<div class="side-menu">
				<a href="<%=request.getContextPath()%>/animal/list">보호동물</a>
			</div>
			<hr class="side-hr" />
		</div>
	</div>
	<div class="introduce01-detail-section">
		<div class="checked-title2">입양절차</div>
		<hr class="section-hr" />
		<div class="procedure-container">
			<div class="procedure-div">
				<div class="procedure1">
					<div>
						<img src="/hairball/images/procedure/1.gif" class="proc-img">
						<p>1.입양 가능한 동물 확인</p>
					</div>
					<img src="/hairball/images/procedure/right_arrow.png" class="arrow">
					<div class="proc-smaill-div">
						<img src="/hairball/images/procedure/2.gif" class="proc-img">
						<p>2.입양 신청 글 작성</p>
					</div>
					<img src="/hairball/images/procedure/right_arrow.png" class="arrow">
					<div class="proc-smaill-div">
						<img src="/hairball/images/procedure/3.gif" class="proc-img">
						<p>3.센터 방문</p>
					</div>
				</div>
				<div class="procedure2">
					<div class="proc-smaill-div">
						<img src="/hairball/images/procedure/4.gif" class="proc-img">
						<p>4.입양 전 교육 2회 이상 수료</p>
					</div>
					<img src="/hairball/images/procedure/right_arrow.png" class="arrow">
					<div class="proc-smaill-div">
						<img src="/hairball/images/procedure/5.gif" class="proc-img">
						<p>5.입양 확정시 개별 연락</p>
					</div>
					<img src="/hairball/images/procedure/right_arrow.png" class="arrow">
					<div class="proc-smaill-div">
						<img src="/hairball/images/procedure/6.gif" class="proc-img">
						<p>6.입양 완료</p>
					</div>
				</div>
			</div>
		</div>
	</div>
</section>

<%@ include file="/WEB-INF/views/templates/footer.jsp" %>