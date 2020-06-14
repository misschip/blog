<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	request.setCharacterEncoding("utf-8");
	String name = request.getParameter("name");
	String fileName = request.getParameter("filename");
	String originalFileName = request.getParameter("originalFileName");
%>
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>파일 업로드 확인</title>
</head>
<body>
	fileName : <%=fileName %><br/>
	originalFileName : <%=originalFileName %>
	name : <%=name %>
	FILE NAME : <a href="images/<%=fileName %>"><%=originalFileName %></a><br/>
</body>
</html>