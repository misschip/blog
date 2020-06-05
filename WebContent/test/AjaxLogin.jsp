<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../include/nav.jsp" %>

<div class="container">

<form action="/blog/user?cmd=loginProc" method="POST" class="was-validated" id="actionForm">
  <div class="form-group">
    <label for="username">Username:</label>


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
  <button type="button" class="btn btn-primary" onclick="tryLogin()">로그인</button>
</form>

</div>

<script>

function tryLogin() {
	var data = {
		username: $('#username').val(),
		password: $('#password').val()
	}	

	console.log(JSON.stringify(data));
	
	$.ajax({
		type: 'POST',	// 디폴트는 GET
		url: '/blog/user?cmd=loginProc',	// 필수값
		data: JSON.stringify(data),	// 보내는 데이터. 생략가능.
		contentType: 'application/json; charset=utf-8',	// 보낼 데이터 타입
		// dataType: 'json'
	}).done(function(result) {
		console.log(result);
		console.log(data.username + "님 로그인 성공했습니다!");
		$("#actionForm").remove();
		$(".container").append("<p>" + username +  "님 환영합니다.</p>");
	}).fail(function(error) {
		console.log(error);
	});
}


</script>


<%@ include file="../include/footer.jsp" %>