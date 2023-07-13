<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/templates/header.jsp" %>
<%@ include file="/WEB-INF/views/templates/header2.jsp" %>
<%@ include file="/WEB-INF/views/templates/aside.jsp" %>

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
            <div id="checked-title2">동물등록</div>
            <hr class="section-hr" />
            
<%@ include file="/WEB-INF/views/templates/footer.jsp" %>