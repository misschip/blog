<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>



<%@ include file="../include/nav.jsp" %>

<!-- 아래 Bootstrap Form 관련 소스코드의 출처 :
	https://www.w3schools.com/bootstrap4/bootstrap_forms.asp 에서
	Form Validation 파트 소스코드임. 거기에 입력창 두어개 더 추가한 것! -->

<div class="container">

<form action="/blog/user?cmd=joinProc" method="POST" class="was-validated">
  <div class="form-group">
    <label for="username">Username:</label>
    <input type="text" class="form-control" id="username" placeholder="Enter username" name="username" required>
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
    <input type="email" class="form-control" id="email" placeholder="Enter email" name="email" required>
    <div class="valid-feedback">Valid.</div>
    <div class="invalid-feedback">Please fill out this field.</div>
  </div>
  
  
   <div class="form-group">
    <label for="address">address:</label>
    <!-- 아래 button이 float-right임에 주의. inline-block이어서라고 하는데 ... -->
    <button type="button" class="btn btn-warning float-right" onClick="goPopup()">주소검색</button>
    <!--  위 버튼 type="button"으로 해야 눌러도 submit이 일어나지 않는다. 그래서 validation check 에러가 안남 -->
    <input type="text" class="form-control" id="address" placeholder="Enter address" name="address" required>
    <div class="valid-feedback">Valid.</div>
    <div class="invalid-feedback">Please fill out this field.</div>
  </div>
  

  <button type="submit" class="btn btn-primary">Submit</button>
</form>

</div>


<script>
// opener관련 오류가 발생하는 경우 아래 주석을 해지하고, 사용자의 도메인정보를 입력합니다. ("팝업API 호출 소스"도 동일하게 적용시켜야 합니다.)
//document.domain = "abc.go.kr";

function goPopup(){
	// 주소검색을 수행할 팝업 페이지를 호출합니다.
	// 호출된 페이지(jusopopup.jsp)에서 실제 주소검색URL(http://www.juso.go.kr/addrlink/addrLinkUrl.do)를 호출하게 됩니다.
	var pop = window.open("/blog/juso/jusoPopup.jsp","pop","width=570,height=420, scrollbars=yes, resizable=yes"); 
	
	// 모바일 웹인 경우, 호출된 페이지(jusopopup.jsp)에서 실제 주소검색URL(http://www.juso.go.kr/addrlink/addrMobileLinkUrl.do)를 호출하게 됩니다.
    //var pop = window.open("/popup/jusoPopup.jsp","pop","scrollbars=yes, resizable=yes"); 
}


function jusoCallBack(roadFullAddr){
		// 팝업페이지에서 주소입력한 정보를 받아서, 현 페이지에 정보를 등록합니다.
		// document.form.roadFullAddr.value = roadFullAddr;
		var tfAddress = document.querySelector("#address");
		tfAddress.value = roadFullAddr;
}

</script>


<%@ include file="../include/footer.jsp" %>