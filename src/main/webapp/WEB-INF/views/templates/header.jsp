<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="com.sh.hairball.member.model.vo.Member"%>
<%@ page import="java.math.BigInteger" %>
<%@ page import="java.net.URLEncoder" %>
<%@ page import="java.security.SecureRandom" %>

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
    <title>유기견/유기묘 입양 사이트</title>
</head>
<%
  String clientId = "9kBGa_4PSPHg5IPpNrhO";//애플리케이션 클라이언트 아이디값
  String redirectURI = URLEncoder.encode("http://localhost:8080/Oauth/naver", "UTF-8");
  SecureRandom random = new SecureRandom();
  String state = new BigInteger(130, random).toString();
  String apiURL = "https://nid.naver.com/oauth2.0/authorize?response_type=code";
  apiURL += "&client_id=" + clientId;
  apiURL += "&redirect_uri=" + redirectURI;
  apiURL += "&state=" + state;
  session.setAttribute("state", state);
  
  
  Member loginMember = (Member)session.getAttribute("loginMember");
%>
<body>
<header>
    <div class="header">
        <div class="logo_img">
            <img src="/hairball/images/로고/메뉴바_로고.png" alt=""/>
        </div>
        <ul class="utility">
            <li>
                <a href="https://kauth.kakao.com/oauth/authorize?response_type=code&client_id=a7b86ff96d50db1785b75938758aeb44&redirect_uri=http://localhost:8080/oauth2/kakao">
                    <img width="50" height="20" src="../../../images/kakao_login_simple/ko/kakao_login_large.png"/>
                </a>
            </li>
            <li>
                <a href="<%=apiURL%>">
                    <img width="50" height="20" src="/hairball/images/naver_login_simple/btnW_축약형.png"/>
                </a>
            </li>
            <li class="admin_li">
                <a href="<%= request.getContextPath() %>/admin/memberList">관리자</a>
            </li>
            <li class="login_li">
                <a href="<%= request.getContextPath() %>/member/login">로그인</a>
            </li>
            <li class="signup_li">
                <a href="#">회원가입</a>
            </li>
            <li class="logout_li" style="display: none">
                <a href="#">로그아웃</a>
            </li>
        </ul>
        <div class="menu-container">
            <ul class="nav">
                <ul>
                    <li class="on">
                        <a href="<%= request.getContextPath() %>/" class="font">홈</a>
                    </li>
                    <li>
                        <a href="<%= request.getContextPath() %>/introduce/introduce1.jsp" class="font">소개</a>
                    </li>
                    <li>
                        <a href="<%= request.getContextPath() %>/animal/list" class="font">보호동물</a>
                    </li>
                    <li>
                        <a href="#section_4" class="font">입양신청</a>
                    </li>
                    <li>
                        <a href="<%= request.getContextPath() %>/qnaBoard/questionIntro" class="font">참여소통</a>
                    </li>
                </ul>
            </ul>
        </div>
    </div>
</header>

