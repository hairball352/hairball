<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/templates/header.jsp"%>
<section class="introduce-section">
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
								<p class="p2">🤮 <span2>정건룡</span2> : 카카오 아이디 로그인하기, 전체 백업을 맡았습니다. <br/>팀장으로서 팀원들의 열정과 노력으로 인해 성공적으로 프로젝트를 이끌어나갈 수 있어 영광이었습니다. <br/><br/>
								👀 <span2>전예라</span2> : 1:1 채팅, 채팅기록조회, 회원목록조회, 여러 css 및 뷰 디자인을 맡았습니다. <br/>제대로 된 프로젝트는 처음해봤는데 팀원들과 같이해서 그런지 재밌게 할 수 있었습니다. ᵔεᵔ <br/><br/>
								🐰 <span2>김담희</span2> : 네이버 아이디로 로그인하기, 입양 신청 게시판 CRUD 및 여러 CSS 작업을 맡았습니다. <br/> 협력과 커뮤니케이션을 통해 협업의 가치를 깨닫게 해주는 소중한 경험이었습니다.  <br/><br/>
								😒 <span2>김상훈</span2> : 멤버 CRUD 기능을 맡았습니다.<br/>함께 일하는 동료들과 즐거운 협업을 경험했고, 기술을 더욱 발전시켜 다음 프로젝트에는 더 잘하고 싶습니다. <br/><br/>
								☠ <span2>김대원</span2> : FAQ게시판, Q&A게시판, DB, 전체 Filter 작업을 맡았습니다.<br/>협력을 통해 부족한 부분을 서로 채워나가서 많은걸 배운 프로젝트였습니다. </p>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
</section>
<%@ include file="/WEB-INF/views/templates/footer.jsp"%>