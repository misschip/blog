<%@page import="com.cos.blog.test.ScriptTest"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Script.href Test</title>
</head>
<body>
	<% ScriptTest.href("dummy address", response); %>
	<!-- 이걸로 테스트를 해보면 ScriptTet.href()가 출력하는 <script> ... </script>
		부분이 맨 위에 출력되어 있고 그에 이어서 이 문서의 윗부분 <!DOCTYPE htm><html>...
		이하가 연결되어 하나의 문서로 만들어지고 있다. -->
</body>
</html>