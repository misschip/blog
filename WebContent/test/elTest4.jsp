<%@page import="java.io.BufferedReader"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	// String email = request.getParameter("email");
	// elTest3.jsp에서 Post 방식으로 key, value로 전송한 값을
	// 여기서 request.getParameter("email")로
	// 받아올 수 있는 것은 너무 당연한 것임
	// 그 외에 Reader로 읽어올 수 있는지를 테스트 하는 것이 아래 코드의 목적이다.
	BufferedReader br = request.getReader();
	
	String input = null;
	StringBuilder sb = new StringBuilder();
	while ((input = br.readLine()) != null) {
		sb.append(input);
		System.out.println(input);
	}
	String email = sb.toString();

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>elTest4.jsp</h1>
	<hr/>
	이메일 : <%=email %>
</body>
</html>