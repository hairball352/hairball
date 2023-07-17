<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/templates/header.jsp"%>
<section class="introduce01-section">
	<div class="introduce01-container">
		<div class="introduce01-detail-section">
			<div id="checked-title2">타이틀</div>
			<hr class="section-hr" />
			<div class="introduce01-detail-div">
				<div>
					<img id="table-img" src="/hairball/images/강쥐사진모음/몽이3_정사각형.jpg">
					<div class="vertical-line"></div>
				</div>
				<div class="table-container2">
					<table>
						<thead></thead>
						<tbody>
							<tr>
								<td>
									<p>동물들은 당신의 사랑과 관심이 필요합니다.</p>
									<p>털뭉치들은 올바른 반려동물 문화를 만들고</p>
									<p>동물들이 새로운 가족을 만날 수 있도록</p>
									<p>도움을 주기 위해 만들어진 센터입니다.</p>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
				<div style="clear: both;"></div>
			</div>
			<hr class="side-hr" />
			<div class="side-menu">
				<a href="<%=request.getContextPath()%>/introduce/introduce1.jsp">센터소개</a>
			</div>
			<hr class="side-hr" />
			<div class="side-menu">
				<a href="<%=request.getContextPath()%>/introduce/introduce2.jsp">팀원소개</a>
			</div>
			<hr class="side-hr" />
		</div>
	</div>
</section>
<%@ include file="/WEB-INF/views/templates/footer.jsp"%>