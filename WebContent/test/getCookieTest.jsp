<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!--  XSS (자바스크립트 공격)에 의한 쿠키 내용 유출 막기   -->

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		Cookie cookie = new Cookie("hello", "world");
		// cookie.setHttpOnly(true);	// 중요!!! 이 설정을 하고 나면 자바스크립트로 쿠키 내용 출력하는 걸 막을 수 있음
		response.addCookie(cookie);
	%>
	
<script>
	var a = document.cookie;
	console.log(a);
</script>
</body>
</html>