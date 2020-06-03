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
    <!-- 만약 input type="submit" 으로 하면 클릭시 validation check로 빈 필드에 먼저 입력해야 한다는 알림이 뜨게 되는 현상 -->
    <input type="text" class="form-control" id="address" placeholder="Enter address" name="address" required readonly>
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

<script>
var isCheckedUsername = false;

function validate() {
	// alert('validate() 실행');
	if (!isCheckedUsername) {
		alert('username 중복체크를 실행해 주세요');
	}
	return isCheckedUsername;
}

function usernameCheck() {
	// 성공시
	var tfUsername = $('#username').val();
	// alert(tfUsername);	// 모든 걸 String으로 보여줌
	console.log(tfUsername);	// 객체 타입도 대응 가능한 방법

	$.ajax({
		type: 'get',
		url: '/blog/user?cmd=usernameCheck&username=' +tfUsername
		// ajax가 이렇게 호출함으로써 UsersUsernameCheckAction 객체가 생성 및 실행되고
		// 결과적으로는 response.getWriter() 한후 결과를 출력해주고 있는데
		// UsersUsernameCheckAction의 execute 메서드 참조)
		// 이게 어떻게 아래 function의 매개값으로 전달되는지는 조금 의아함
	}).done(function(result){
		console.log(result);
		if (result == 1) {
			alert('아이디가 중복되었습니다');
		} else if (result == 0) {
			alert('사용가능한 아이디입니다');
			isCheckedUsername = true;
		} else {
			console.log('develop : 서버오류');
		}
	}).fail(function(error){
		console.log(error);
	});
	
}

</script>

<%@ include file="../include/footer.jsp" %>