<%@page import="com.sh.hairball.member.model.vo.MemberRole"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/templates/header.jsp" %>
<%
	
	List<Member> members = (List<Member>) request.getAttribute("members"); 
	List<Member> memberList = (List<Member>) request.getAttribute("memberList");

	// 검색관련 
	String searchType = request.getParameter("searchType");
	String searchKeyword = request.getParameter("searchKeyword");


%>
<!-- 관리자용 admin.css link -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/admin.css" />
<style>
div#search-container 	{width: 100%; margin:0 0 10px 0; padding:3px; background-color: 5fab95;}
div#search-memberId 	{display: <%= searchType == null || "member_id".equals(searchType) ? "inline-block" : "none" %>;}
div#search-name			{display: <%= "name".equals(searchType) ? "inline-block" : "none" %>;}
</style>
<section class="adminPage-section">
	<div class="introduce01-container">
		<div class="introduce01-bar">
			<div class="side-menu-title">
				<a href="<%=request.getContextPath()%>/admin/animalRegistration">관리자페이지</a>
			</div>
			<hr class="side-hr" />
			<div class="side-menu">
				<a href="<%=request.getContextPath()%>/admin/animalRegistration">동물등록</a>
			</div>
			<hr class="side-hr" />
			<div class="side-menu">
				<a href="<%=request.getContextPath()%>/admin/memberList">회원목록조회</a>
			</div>
			<hr class="side-hr" />
			<div class="side-menu">
				<a href="<%=request.getContextPath()%>/admin/webChatList">채팅기록조회</a>
			</div>
			<hr class="side-hr" />
		</div>
	</div>
	<div class="introduce01-detail-section">
		<div class="checked-title2">회원목록조회</div>
		<hr class="section-hr" />
		<div class="adminPage-board">
		<div id="search-container">
	        <label for="searchType"></label> 
	        <select id="searchType">
	            <option value="memberId" <%= "member_id".equals(searchType) ? "selected" : "" %>>아이디</option>		
	            <option value="name" <%= "name".equals(searchType) ? "selected" : "" %>>회원명</option>
	        </select>
	        <div id="search-memberId" class="search-type">
	            <form action="<%=request.getContextPath()%>/admin/webChatFinder">
	                <input type="hidden" name="searchType" value="member_id"/>
	                <input 
	                	type="text" name="searchKeyword"  size="25" placeholder="검색할 아이디를 입력하세요." 
	                	value="<%= "member_id".equals(searchType) ? searchKeyword : "" %>"/>
	                <button type="submit">검색</button>			
	            </form>	
	        </div>
	        <div id="search-name" class="search-type">
	            <form action="<%=request.getContextPath()%>/admin/webChatFinder">
	                <input type="hidden" name="searchType" value="name"/>
	                <input 
	                	type="text" name="searchKeyword" size="25" placeholder="검색할 이름을 입력하세요."
	                	value="<%= "name".equals(searchType) ? searchKeyword : "" %>"/>
	                <button type="submit">검색</button>			
	            </form>	
	        </div>
	    </div>
	</div>
		<table id="tbl-member">
			<thead>
				<tr>
					<th class="id">아이디</th>
					<th class="name">이름</th>
					<th class="email">이메일</th>
					<th>전화번호</th>
					<th>주소</th>
				</tr>
			</thead>
			<tbody>
				<%
				if (members == null || members.isEmpty()) {
				%>
				<tr>
					<td colspan="10">조회 결과가 없습니다.</td>
				</tr>
				<%
				} else {
				for (Member member : memberList) {
				%>
				<tr class="member-row">
					<td class="id"><%=member.getMemberId()%></td>
					<td class="name"><%=member.getName()%></td>
					<td class="email"><%=member.getEmail() != null ? member.getEmail() : ""%></td>
					<td><%=member.getPhone()%></td>
					<td><%=member.getAddress()%></td>
				</tr>
				<tr class="chat-row" style="display: none;">
					<td colspan="5">
						<div class="chat-container"
							data-member-id="<%=member.getMemberId()%>">
						</div>
					</td>
				</tr>
				<%
				}
				}
				%>
			</tbody>
		</table>
		<div id='pagebar'>
			<%=request.getAttribute("pagebar")%>
		</div>
	</div>
</section>
<form 
	name="memberRoleUpdateFrm" 
	action="<%= request.getContextPath() %>/admin/memberRoleUpdate"
	method="post">
	<input type="hidden" name="memberRole"/>
	<input type="hidden" name="memberId"/>
</form>
<script>
document.querySelector("select#searchType").onchange = (e) => {
	console.log(e.target.value);
	document.querySelectorAll(".search-type").forEach((elem) => {
		elem.style.display = "none";
	});
	
	document.querySelector(`#search-\${e.target.value}`).style.display = "inline-block";
};


document.querySelectorAll("select.member-role").forEach((elem) => {
	elem.addEventListener("change", (e) => {
		// console.log(e.target.dataset); // 작성한 data태그를 보관하는 맵(key-value)
	
		
		
		if(confirm("회원권한을 정말 수정하시겠습니까?")) {			
			const memberRoleVal = e.target.value;
			const memberIdVal = e.target.dataset.memberId;
			
			const frm = document.memberRoleUpdateFrm;
			frm.memberRole.value = memberRoleVal;
			frm.memberId.value = memberIdVal;
			frm.submit();
		}
		else {
			// 사용자변경을 원래값으로 취소
			// option태그 selected속성(inline에 작성된)이 있는 태그를 찾아서 selected속성(태그객체)을 true로 지정 
			e.target.querySelector("option[selected]").selected = true;
		}
		
	});
});
</script>
<%@ include file="/WEB-INF/views/templates/footer.jsp" %>