<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../include/nav.jsp" %>

<%
	// remember에 대응하는 username을 직접 가져오는 대신 이제부터는 EL로 직접 가져오도록 수정함
/* 	String remember = (String)request.getAttribute("remember");

	if (remember == null) {
		remember = "";
	} */
	
%>

<!-- 아래 Bootstrap Form 관련 소스코드의 출처 :
	https://www.w3schools.com/bootstrap4/bootstrap_forms.asp 에서
	Form Validation 파트 소스코드임. -->

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
  <button type="submit" class="btn btn-primary">로그인</button>
</form>

</div>


<%@ include file="../include/footer.jsp" %>