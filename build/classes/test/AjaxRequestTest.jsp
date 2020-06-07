<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>

<input type="text" id="tfTitle"/><br/>
<input type="text" id="tfContent"/><br/>
<button type="button" onclick="sendData()">보내기</button>

<%-- JSP 파일 안에서 자바스크립트의 backtick과 ${expression} 변수 표현은 제대로 작동하지 않음 --%>
<%-- 	JSP의 EL 표현식과 혼동을 일으키기 때문이라고! 그래서 backtick과 ${expression}을 사용하려면 --%>
<!-- 	javascript 코드만 .js 파일로 따로 빼내서 써야 한다고 함 -->
	
<script>
	function sendData() {

		// $("#tfContent")와 $("#tfContent").val()은 엄청난 차이가 있다!
		var data = {
				username: "ssarmango",
				content: $("#tfContent").val()
		};

		console.log(data);
		
		$.ajax({
			type: 'POST',	// 디폴트는 GET
			url: 'AjaxResponseTest.jsp',	// 필수값
			data: JSON.stringify(data),	// 보내는 데이터. 생략가능.
			// contentType: 'application/json; charset=utf-8',	// 보낼 데이터 타입
			contentType: 'text/plain; charset=utf-8',
			dataType: 'json'	// 응답받을 데이터의 타입. text, json 둘 중 하나만 가능함. 받을 데이터를 어떻게 파싱할까를 정의
		}).done(function(result){
			console.log(result);
			// console.log(JSON.parse(result));
			// console.log(JSON.stringify(result));
			// $('#reply-box').prepend("<div id='reply-" + num + "'>" + a + "</div>");
		}).fail(function(error){
			console.log(error);
		});
	}

</script>

</body>
</html>