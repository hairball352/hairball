<%@page import="com.sh.hairball.member.model.vo.MemberRole"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/templates/header.jsp" %>
<%@ include file="/WEB-INF/views/templates/header2.jsp" %>
<%@ include file="/WEB-INF/views/templates/aside.jsp" %>
<%
	
	List<Member> members = (List<Member>) request.getAttribute("members"); 

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

    <div class="introduce01-container">
        <div class="introduce01-bar">
                <div class="side-menu-title"><a href="<%= request.getContextPath() %>/admin/animalRegistration">관리자페이지</a></div>
                <hr class="side-hr" />
               	<div class="side-menu"><a href="<%= request.getContextPath() %>/admin/animalRegistration">동물등록</a></div>
                <hr class="side-hr" />
                <div class="side-menu"><a href="<%= request.getContextPath() %>/admin/memberList">회원목록조회</a></div>
                <hr class="side-hr" />
                <div class="side-menu"><a href="<%= request.getContextPath() %>/admin/webChatList">채팅기록조회</a></div>
                <hr class="side-hr" />
            </div>
        </div>
        <div class="introduce01-detail-section">
            <div id="checked-title2">회원목록조회</div>
            <hr class="section-hr" />
    <section id="memberList-container">
    <div class="memberList-container2">
		<h2>회원관리</h2>
		<div id="search-container">
	        <label for="searchType">검색타입 :</label> 
	        <select id="searchType">
	            <option value="memberId" <%= "member_id".equals(searchType) ? "selected" : "" %>>아이디</option>		
	            <option value="name" <%= "name".equals(searchType) ? "selected" : "" %>>회원명</option>
	        </select>
	        <div id="search-memberId" class="search-type">
	            <form action="<%=request.getContextPath()%>/admin/memberFinder">
	                <input type="hidden" name="searchType" value="member_id"/>
	                <input 
	                	type="text" name="searchKeyword"  size="25" placeholder="검색할 아이디를 입력하세요." 
	                	value="<%= "member_id".equals(searchType) ? searchKeyword : "" %>"/>
	                <button type="submit">검색</button>			
	            </form>	
	        </div>
	        <div id="search-name" class="search-type">
	            <form action="<%=request.getContextPath()%>/admin/memberFinder">
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
				<th>아이디</th>
				<th>이름</th>
				<th>회원권한</th>
				<th>이메일</th>
				<th>전화번호</th>
				<th>주소</th>
			</tr>
		</thead>
		<tbody>
			<% 	if(members == null || members.isEmpty()) { %>
				<tr>
					<td colspan="10">조회 결과가 없습니다.</td>
				</tr>
			<%	
				} 
				else { 
					for(Member member : members) {
			%>
				<tr>
					<td><%= member.getMemberId() %></td>
					<td><%= member.getName() %></td>
					<td>
						<select class="member-role" data-member-id="<%= member.getMemberId() %>">
							<option value="U" <%= member.getMemberRole() == MemberRole.U ? "selected" : "" %>>일반</option>
							<option value="A" <%= member.getMemberRole() == MemberRole.A ? "selected" : "" %>>관리자</option>
						</select>
					</td>
					<td><%= member.getEmail() != null ? member.getEmail() : "" %></td>
					<td><%= member.getPhone() %></td>	
					<td><%= member.getAddress() %></td>			
				</tr>
			
			<% 		
					}
				} 
			%>
		</tbody>
	</table>
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