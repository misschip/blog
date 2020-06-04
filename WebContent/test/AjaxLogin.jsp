<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../include/nav.jsp" %>

<%
	
%>

<div class="container">

<form action="/blog/user?cmd=loginProc" method="POST" class="was-validated">
  <div class="form-group">
    <label for="username">Username:</label>

<%--     <input type="text" value="<%=remember %>" class="form-control" id="username" placeholder="Enter username" name="username" required>
 --%>
 	<%-- 아래 ${cookie}는 Expression Language의 내장 객체임 --%>
    <input type="text" value="${cookie.remember.value}" class="form-control" id="username" placeholder="Enter username" name="username" required>
    <div class="valid-feedback">Valid.</div>
    <div class="invalid-feedback">Please fill out this field.</div>
  </div>
  <div class="form-group">
    <label for="pwd">Password:</label>
    <input type="password" class="form-control" id="password" placeholder="Enter password" name="password" required>
    <div class="valid-feedback">Valid.</div>
    <div class="invalid-feedback">Please fill out this field.</div>
  </div>
  
  <div class="form-group form-check">
    <label class="form-check-label">
      <input class="form-check-input" type="checkbox" name="remember" > 아이디 기억하기
      <div class="valid-feedback">Valid.</div>
      <div class="invalid-feedback">Check this checkbox to continue.</div>
    </label>
  </div>
  <button type="button" class="btn btn-primary" onclick=tryLogin()>로그인</button>
</form>

</div>


<script>
	function tryLogin() {
		var username = $('#username').val();
		var password = $('#password').val();
	
		$.ajax({
			type: 'POST',	// 디폴트는 GET
			url: '/blog/user?cmd=loginProc',	// 필수값
			data: `username=${username}&password=${password}`,	// 보내는 데이터. 생략가능.
			contentType: 'application/json; charset=utf-8',	// 보낼 데이터 타입
			// dataType: 'json'	// 응답받을 데이터의 타입. text, json 둘 중 하나만 가능함. 받을 데이터를 어떻게 파싱할까를 정의
		}).done(function(result){
			console.log(result);
			// console.log(JSON.parse(result));
			// console.log(JSON.stringify(result));
			$('.container').text(result);
		}).fail(function(error){
			console.log(error);
		});
	}
</script>



<%@ include file="../include/footer.jsp" %>