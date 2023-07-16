<%@page import="com.sh.hairball.webchat.model.WebChat"%>
<%@page import="com.sh.hairball.webchat.model.WebChatService"%>
<%@page import="com.sh.hairball.member.model.vo.MemberRole"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/templates/header.jsp" %>
<%@ include file="/WEB-INF/views/templates/header2.jsp" %>
<%@ include file="/WEB-INF/views/templates/aside.jsp" %>
<%
	
	List<Member> members = (List<Member>) request.getAttribute("members"); 
	WebChatService webChatService = new WebChatService();
	List<WebChat> chatHistory = webChatService.webChatfindAll();

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
               	<div class="side-menu"><a href="<%= request.getContextPath() %>/animal/enroll">동물등록</a></div>
                <hr class="side-hr" />
                <div class="side-menu"><a href="<%= request.getContextPath() %>/admin/memberList">회원목록조회</a></div>
                <hr class="side-hr" />
                <div class="side-menu"><a href="<%= request.getContextPath() %>/admin/webChatList">채팅기록조회</a></div>
                <hr class="side-hr" />
            </div>
        </div>
   <div class="introduce01-detail-section">
            <div id="checked-title2">채팅기록조회</div>
            <hr class="section-hr" />
    <section id="memberList-container">
    <div class="memberList-container2">
		<h2>채팅기록</h2>
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
				<tr class="member-row">
					<td><%= member.getMemberId() %></td>
					<td><%= member.getName() %></td>
					<td><%= member.getEmail() != null ? member.getEmail() : "" %></td>
					<td><%= member.getPhone() %></td>	
					<td><%= member.getAddress() %></td>			
				</tr>
			 <tr class="chat-row" style="display: none;">
      			<td colspan="5">
        			<div class="chat-container" data-member-id="<%= member.getMemberId() %>">
          		 	<!-- 채팅 기록을 동적으로 로드할 컨테이너 -->
        	</div>
      	</td>
    </tr>
			<% 		
					}
				} 
			%>
		</tbody>
	</table>
</section>
<script>
  const memberRows = document.querySelectorAll('.member-row');
  memberRows.forEach((row) => {
    row.addEventListener('click', () => {
      const chatRow = row.nextElementSibling;
      const chatContainer = chatRow.querySelector('.chat-container');
      const memberId = chatContainer.dataset.memberId;

      // 서비스를 통해 채팅 기록을 가져옴
      fetch(`/admin/getChatHistory?memberId=${memberId}`)
        .then((response) => response.json())
        .then((chatHistory) => {
          // 받아온 채팅 기록을 삽입
          chatContainer.innerHTML = chatHistory
            .map((chat) => `<div>${chat.content}</div>`)
            .join('');
          // 아코디언 효과
          chatRow.style.display = chatRow.style.display === 'none' ? 'table-row' : 'none';
        });
    });
  });
</script>
<%@ include file="/WEB-INF/views/templates/footer.jsp" %>