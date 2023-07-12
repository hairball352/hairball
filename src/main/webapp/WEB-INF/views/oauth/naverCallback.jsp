<%@page import="com.sh.hairball.member.model.vo.Member"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <%
        Member loginMember =  (Member)session.getAttribute("loginMember") ;
    %>
    <h1><%= loginMember.getEmail() %></h1>
</body>
</html>
