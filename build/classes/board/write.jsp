<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="/blog/board?cmd=writeProc" method="POST">
<label>글제목</label>
<input type="text" name="title"><br/>
<label>내용</label>
<textarea name="content">
</textarea><br/>
<button type="submit">글쓰기</button>
</form>
</body>
</html>