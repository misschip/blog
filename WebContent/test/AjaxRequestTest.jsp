<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<style>
	div {
		border: 1px solid black;
		margin: 5px;
		padding: 5px;
	}
</style>
</head>
<body>
<div id="reply-box">
	<div id="reply-1">
		첫번째 댓글입니다.
	</div>
</div>
<input type="text" id="tf-reply"/><br/>
<button onclick="start()">댓글쓰기</button>
<script>
var num = 1;
function start() {
	num++;
	var a = $('#tf-reply').val();

	var data = {
		username: "ssar",
		content: a
	};

	// 통신이 성공하면 아래 작업 실행
	$.ajax({
		type: 'POST',	// 디폴트는 GET
		url: 'AjaxResponseTest.jsp',	// 필수값
		data: JSON.stringify(data),	// 보내는 데이터. 생략가능.
		contentType: 'application/json; charset=utf-8',	// 보낼 데이터 타입
		dataType: 'json'	// 응답받을 데이터의 타입. text, json 둘 중 하나만 가능함. 받을 데이터를 어떻게 파싱할까를 정의
	}).done(function(result){
		console.log(result);
		// console.log(JSON.parse(result));
		// console.log(JSON.stringify(result));
		$('#reply-box').prepend("<div id='reply-" + num + "'>" + a + "</div>");
	}).fail(function(error){
		console.log(error);
	});

	/*
		data: '<h1>hello</h1>',
		contentType: 'text/html; charset=utf-8'
	*/
	
	// $('#reply-box').prepend("<div id='reply-" + num + "'>" + a + "</div>");
}

</script>

</body>
</html>