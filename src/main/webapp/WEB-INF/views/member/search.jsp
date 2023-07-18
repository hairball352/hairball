<%@page import="com.sh.hairball.member.model.vo.Member"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<%

Member selectedMember = (Member) request.getAttribute("selectedMember");

%>


<%= selectedMember.getMemberId() %>




</body>
</html>