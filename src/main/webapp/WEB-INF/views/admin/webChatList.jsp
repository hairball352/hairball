<%@page import="com.sh.hairball.webchat.model.WebChat"%>
<%@page import="com.sh.hairball.webchat.model.WebChatService"%>
<%@page import="com.sh.hairball.member.model.vo.MemberRole"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/templates/header.jsp"%>
<%
	
	List<Member> members = (List<Member>) request.getAttribute("members"); 
	List<Member> memberList = (List<Member>) request.getAttribute("memberList");
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
<!-- 관리자용 admin.css link -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/admin.css" />
<style>
div#search-container 	{width: 100%; margin:0 0 10px 0; padding:3px; background-color: 5fab95;}
div#search-memberId 	{display: <%= searchType == null || "member_id".equals(searchType) ? "inline-block" : "none" %>;}
div#search-name			{display: <%= "name".equals(searchType) ? "inline-block" : "none" %>;}
}
</style>
<section class="adminPage-section">
	<div class="introduce01-container">
		<div class="introduce01-bar">
			<div class="side-menu-title">
				<a href="<%=request.getContextPath()%>/animal/enroll">관리자페이지</a>
			</div>
			<hr class="side-hr" />
			<div class="side-menu">
				<a href="<%=request.getContextPath()%>/animal/enroll">동물등록</a>
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
			<div class="side-menu">
				<a href="<%= request.getContextPath() %>/admin">관리자채팅상담</a>
			</div>
			<hr class="side-hr" />
		</div>
	</div>
	<div class="introduce01-detail-section">
		<div class="checked-title2">채팅기록조회</div>
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
				if (memberList == null || memberList.isEmpty()) {
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
							data-member-id="<%=member.getMemberId()%>"
							style="overflow-y: auto; width: 970px; max-height: 500px; padding: 4px; border: 1 solid #000000;">
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
<script>
$(document).ready(function(){
    $(".member-row").click(function(){
        var $chatRow = $(this).next(".chat-row");
        var memberId = $chatRow.find(".chat-container").data("member-id");
        console.log(memberId);

        if ($chatRow.is(":visible")) {
            $chatRow.hide();
        } else {
            // Ajax로 서버에서 채팅 내역 가져오기
            $.ajax({
                url: '<%=request.getContextPath()%>/admin/getChatHistory', 
                type: 'GET',
                data: {memberId: memberId},
                success: function(chatHistory) {
                    var chatHtml = '';
                    for (var i = 0; i < chatHistory.length; i++) {
                        chatHtml += '<p>' + chatHistory[i].content + '</p>';
                    }
                    $chatRow.find(".chat-container").html(chatHtml);
                    $chatRow.show();
                },
                error: function(error) {
                    console.log('error', error);
                }
            });
        }
    });
});
</script>
<script>
document.querySelector("select#searchType").onchange = (e) => {
	console.log(e.target.value);
	document.querySelectorAll(".search-type").forEach((elem) => {
		elem.style.display = "none";
	});
	
	document.querySelector(`#search-\${e.target.value}`).style.display = "inline-block";
};

</script>
<%@ include file="/WEB-INF/views/templates/footer.jsp"%>