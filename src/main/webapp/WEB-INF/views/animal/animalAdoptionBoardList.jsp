<%@page import="com.sh.hairball.board.adoptboard.model.vo.AdopBoard"%>
<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<%@ page
	import="com.sh.hairball.board.adoptboard.model.vo.AdopBoardEntity"%>
<%@ page import="java.util.List"%>
<%@ include file="/WEB-INF/views/templates/header.jsp"%>

<%
List<AdopBoard> adoptionBoardList = (List<AdopBoard>) request.getAttribute("adoptionBoardList");

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
		<div class="checked-title2">입양신청</div>
		<hr class="section-hr" />
		<div class="adop-detail-div">
			<div class="adoption-container">
				<%
				if (loginMember != null)
				%>
				<table id="tbl-adopboard">
					<thead>
						<tr>
							<th>번호</th>
							<th>제목</th>
							<th>작성자</th>
							<th>등록일</th>
						</tr>
					</thead>
					<tbody>
						<%
						for (AdopBoard board : adoptionBoardList) {
						%>
						<tr>
							<td><%=board.getId()%></td>
							<td class="list-title"><a
								href="<%=request.getContextPath()%>/animal/animalAdoptionBoardDetail?no=<%=board.getId()%>"><%=board.getMemberName()%>님의
									입양신청서 💌</a></td>
							<td><%=board.getMemberId()%></td>
							<td><%=board.getRegDate()%></td>
						<tr>
							<%
							}
							%>
					</tbody>
				</table>
			</div>
		</div>
		<div id='pagebar'>
				<%=request.getAttribute("pagebar")%>
		</div>
		<div>
				<input type="button" value="글쓰기" class="btn1"
					onclick="location.href = '<%=request.getContextPath()%>/animal/animalAdoptionBoardCreate';" />
		</div>
	</div>
</section>

<%@ include file="/WEB-INF/views/templates/footer.jsp"%>