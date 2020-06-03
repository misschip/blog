<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	// String username = (String) request.getAttribute("username");
	// String password = (String) session.getAttribute("password");
%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>elTest2.jsp</title>
</head>
<body>
	<h1>elTest2.jsp 파일</h1>
	<hr/>
	username : ${username} <br/>	<!-- session, application보다 request가 우선한다 -->
	requestScope.username : ${requestScope.username } <br/>
	sessionScope.username : ${sessionScope.username} <br/>
	applicationScope.username : ${applicationScope.username} <br/>
</body>
</html>