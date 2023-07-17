<%@page import="com.sh.hairball.member.model.vo.MemberRole"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="com.sh.hairball.member.model.vo.Member"%>
<%@ page import="java.math.BigInteger" %>
<%@ page import="java.net.URLEncoder" %>
<%@ page import="java.security.SecureRandom" %>

<%
  String msg = (String) session.getAttribute("msg"); 
   if(msg != null){ 
      session.removeAttribute("msg"); 
   }
  Member loginMember = (Member)session.getAttribute("loginMember");
      
  Cookie[] cookies = request.getCookies(); 
   String saveId = null;
   if(cookies != null){                 
      for(Cookie cookie : cookies){
         String name = cookie.getName();  
         String value = cookie.getValue();
         if("saveId".equals(name))
            saveId = value;   
      }
   } 
%>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="stylesheet" href="/hairball/css/index.css" />
    <link rel="stylesheet" href="/hairball/css/animal.css" />
    <link rel="stylesheet" href="/hairball/css/aside.css" />
    <link rel="stylesheet" href="/hairball/css/swiper.css" />
    <link rel="stylesheet" href="/hairball/css/introduce.css" />
    <link rel="stylesheet" href="/hairball/css/memberLogin.css" />
    <link rel="stylesheet" href="/hairball/css/procedure.css" />
    <link rel="stylesheet"
	href="//code.jquery.com/ui/1.13.2/themes/base/jquery-ui.css">
	<script src="<%= request.getContextPath() %>/js/jquery-3.7.0.js"></script>
	<script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js"></script>
    <title>유기견/유기묘 입양 사이트</title>
</head>


   
<body>

<script>
window.onload = () => {
		
	<% 	if(msg != null) { %>
		alert('<%= msg %>');
	<% 	} %>	
		
	<% 	if(loginMember == null) { %>	
		document.loginFrm.onsubmit = (e) => {
			// 아이디
			console.log(memberId);
			console.log(memberId.value);
			const memberId = e.target.memberId;
			if(!/^\w{4,}$/.test(memberId.value)) {
				alert(" 아이디 또는 비밀번호를 잘못 입력했습니다.");
				e.preventDefault();
				return;
			}
			
			// 비밀번호
			const password = e.target.password;
			if(!/^.{4,}$/.test(password.value)) {
				alert(" 아이디 비밀번호를 잘못 입력했습니다.");
				e.preventDefault();
				return;
			}
		}
	<% 	} %>
	};

</script>

<header>
    <div class="header">
        <div class="logo_img">
            <a href="<%= request.getContextPath() %>"><img src="/hairball/images/로고/메뉴바_로고.png" alt=""/></a>
        </div>
        <ul class="utility">
				<% 
				    if(loginMember != null && loginMember.getMemberRole() == MemberRole.A) {
				%>
			    <li class="admin_li">
			        <a href="<%= request.getContextPath() %>/animal/enroll">관리자</a>
			    </li>
			<% } %>
            <%if(loginMember == null){ %>
            <li class="login_li">
                <a href="<%= request.getContextPath() %>/member/login">로그인</a>
            </li>
            <%}else{ %>
            <li class="login_li">
                <a href="<%= request.getContextPath() %>/member/login">마이페이지</a>
            </li>
            <%} 
            %>
            <%if(loginMember == null){ %>
            <li class="signup_li">
                <a href="<%= request.getContextPath() %>/member/terms">회원가입</a>
            </li>
            <% } %>
			<% if (loginMember != null) { %>
			    <li class="logout_li">
			        <a href="<%= request.getContextPath() %>/member/logout">로그아웃</a>
			    </li>
			<% } %>
        </ul>
        <div class="menu-container">
            <ul class="nav">
                <ul>
                    <li class="on">
                        <a href="<%= request.getContextPath() %>" class="font">홈</a>
                    </li>
                    <li>
                        <a href="<%= request.getContextPath() %>/introduce/introduce1.jsp" class="font">소개</a>
                    </li>
                    <li>
                        <a href="<%= request.getContextPath() %>/animal/procedure" class="font">보호동물</a>
                    </li>
                    <li>
                        <a href="<%= request.getContextPath() %>/animal/animalAdoptionList" class="font">입양신청</a>
                    </li>
                    <li>
                        <a href="<%= request.getContextPath() %>/qnaBoard/questionIntro" class="font">참여소통</a>
                    </li>
                </ul>
            </ul>
        </div>
    </div>
</header>
<%@ include file="/WEB-INF/views/templates/header2.jsp" %>
<%@ include file="/WEB-INF/views/templates/aside.jsp" %>
