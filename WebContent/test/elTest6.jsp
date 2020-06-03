<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h1>elTest6.jsp</h1>
	<hr/>
	
	<%
		// 10000
		String money = request.getParameter("money");
		
	%>
	<script>
		var num = 100;
		var m = '${param.money}';
		console.log(m);

		// ${param.money}를 ''로 감싸면 문자열이 됨
		var sum = num + '${param.money}';
		console.log(sum);

		var m2 = <%= money %>;
		console.log(m2);
	</script>

</body>
</html>