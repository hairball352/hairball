<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/templates/header.jsp"%>
<section class="animal-section">
	<div class="introduce01-container">
		<div class="introduce01-bar">
			<div class="side-menu-title">
				<a href="<%=request.getContextPath()%>/introduce/introduce1.jsp">소개</a>
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
	<div class="introduce01-detail-section">
		<div class="checked-title2">팀원소개</div>
		<hr class="section-hr2" />
		<div class="introduce-board">
			<div class="vertical-line2"></div>
		<div class="table-container4">
				<table>
					<tbody>
						<tr>
							<td>
								<div class="table-container3"><span>싸우지말조</span>를 소개합니다 😘</div>
								<p class="p2">🤮 정건룡 : 시간이 금인데 말이죠<br/><br/>
								👀 전예라 : 안녕하세요 전예라입니다.<br/><br/>
								🐰 김담희 : 안녕하세요 김담희입니다.<br/><br/>
								😒 김상훈 : 안녕하세요 김상훈입니다.<br/><br/>
								💩 김대원 : 안녕하세요 김대원입니다.</p>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
</section>
<%@ include file="/WEB-INF/views/templates/footer.jsp"%>