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
    <!-- 아래 c:choose의 기능 -->
    <!-- 앞선 로그인시 아이디 기억하기를 체크했었을 경우에 remember 쿠키가 있을 것이므로
    	그 값의 유무에 따라 이번 로그인 화면 표출시에도 아이디 기억하기를 미리 체크된 상태로 보여주도록 함 -->
    <c:choose>
    	<c:when test="${empty cookie.remember}">
      <input class="form-check-input" type="checkbox" name="remember"> 아이디 기억하기
      	</c:when>
      	<c:otherwise>
      	<input class="form-check-input" type="checkbox" name="remember" checked> 아이디 기억하기
      	</c:otherwise>
     </c:choose>
     
      <div class="valid-feedback">Valid.</div>
      <div class="invalid-feedback">Check this checkbox to continue.</div>
    </label>
  </div>
  <button type="submit" class="btn btn-primary">로그인</button>
  <%--  아래 a 링크 출처 : https://developers.kakao.com/docs/latest/ko/kakaologin/rest-api
  // GET /oauth/authorize?client_id={app_key}&redirect_uri={redirect_uri}&response_type=code HTTP/1.1
  // client_id 값은 카카오 앱키의 REST API키 값임 https://developers.kakao.com/console/app/449149/config/appKey
  // redirect_uri 값은 제품설정-카카오 로그인의 Redirect URI에 설정한 주소값임 https://developers.kakao.com/console/app/449149/product/login --%>
  <a href="https://kauth.kakao.com/oauth/authorize?client_id=fd8427c16b609d2587da1dd88736a545&redirect_uri=http://localhost:8000/blog/oauth/kakao?cmd=callback&response_type=code"><img height="38px" src="/blog/images/kakao_login_button.png"></a>
	<%-- 위 링크 클릭시 클라이언트ID, response 타입:code, 콜백주소가 보내지고 그 외 동의한 email,profile 사진,닉네임 등 선택값도 보내짐 --%>
</form>

</div>


<%@ include file="../include/footer.jsp" %>