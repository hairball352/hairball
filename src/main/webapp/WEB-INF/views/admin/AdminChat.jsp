<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/templates/header.jsp"%>

<div class="animal-section" >
<div class="introduce01-bar">
                <div class="side-menu-title"><a href="<%= request.getContextPath() %>/animal/enroll">관리자페이지</a></div>
                <hr class="side-hr" />
               	<div class="side-menu"><a href="<%= request.getContextPath() %>/animal/enroll">동물등록</a></div>
                <hr class="side-hr" />
                <div class="side-menu"><a href="<%= request.getContextPath() %>/admin/memberList">회원목록조회</a></div>
                <hr class="side-hr" />
                <div class="side-menu"><a href="<%= request.getContextPath() %>/admin/webChatList">채팅기록조회</a></div>
                <hr class="side-hr" />
                <div class="side-menu"><a href="<%= request.getContextPath() %>/admin">관리자채팅상담</a></div>
                <hr class="side-hr" />
            </div>


<div class="introduce01-detail-section animalList" style="width:950px">
		<div class="checked-title2">관리자채팅상담</div>
		<hr class="section-hr" />
		
		
		<!-- 채팅창들 -->
		
</div>
</div>

<script>

<!-- 스크립트들 -->

</script>
<%@ include file="/WEB-INF/views/templates/footer.jsp"%>