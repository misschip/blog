<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>



<%@ include file="../include/nav.jsp" %>

<!-- 아래 Bootstrap Form 관련 소스코드의 출처 :
	https://www.w3schools.com/bootstrap4/bootstrap_forms.asp 에서
	Form Validation 파트 소스코드임. 거기에 입력창 두어개 더 추가한 것! -->

<div class="container">

<!-- 아래 onsumbit에서 return validate()가 true인 경우에만 action 실행됨 -->
<form action="/blog/user?cmd=joinProc" method="POST" class="was-validated" onsubmit="return validate()">
  <div class="form-group">
    <label for="username">Username:</label>
    <button type="button" class="btn btn-warning float-right" onClick="usernameCheck()">중복확인</button>
    <input value="${principal.username}" type="text" class="form-control" id="username" placeholder="Enter username" name="username" required readonly>
    <div class="valid-feedback">Valid.</div>
    <div class="invalid-feedback">Please fill out this field.</div>
  </div>
  
  <div class="form-group">
    <label for="password">Password:</label>
    <input type="password" class="form-control" id="password" placeholder="Enter password" name="password" required>
    <div class="valid-feedback">Valid.</div>
    <div class="invalid-feedback">Please fill out this field.</div>
  </div>
  
   <div class="form-group">
    <label for="email">email:</label>
    <input value="${principal.email}" type="email" class="form-control" id="email" placeholder="Enter email" name="email" required>
    <div class="valid-feedback">Valid.</div>
    <div class="invalid-feedback">Please fill out this field.</div>
  </div>
  
  
   <div class="form-group">
    <label for="address">address:</label>
    <!-- 아래 button이 float-right임에 주의. inline-block이어서라고 하는데 ... -->
    <button type="button" class="btn btn-warning float-right" onClick="goPopup()">주소검색</button>
    <!--  위 버튼 type="button"으로 해야 눌러도 submit이 일어나지 않는다. 그래서 validation check 에러가 안남 -->
    <!-- 만약 input type="submit" 으로 하면 클릭시 validation check로 빈 필드에 먼저 입력해야 한다는 알림이 뜨게 되는 현상 -->
    <input value="${principal.address}" type="text" class="form-control" id="address" placeholder="Enter address" name="address" required readonly>
    <div class="valid-feedback">Valid.</div>
    <div class="invalid-feedback">Please fill out this field.</div>
  </div>
  

  <button type="submit" class="btn btn-primary">Submit</button>
</form>

</div>


<script src="/blog/js/join.js"></script>

<%@ include file="../include/footer.jsp" %>