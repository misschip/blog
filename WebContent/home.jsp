
<%@page import="com.cos.blog.model.Users"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="include/nav.jsp" %>


<h1> </h1>

<!-- 아래 부트스트랩 코드의 소스는
https://www.w3schools.com/bootstrap4/bootstrap_cards.asp 에서
Card Images 항목에 있는 걸 반복해서 붙여넣은 것! -->


<div class="container">
	 
	 
	<%-- 아래 c:forEach에서는 items 부분에 쌍따옴표 빠뜨리거나 ${}를 빠뜨리기 쉬우므로 주의! --%>
	<c:forEach var="board" items="${boards}">
		<div class="card m-2" style="width:100%">  <!-- 고정 픽셀보다는 화면 퍼센트로 지정  -->
	  <!-- <img class="card-img-top" src="img_avatar1.png" alt="Card image">  -->
	  <div class="card-body">
	    <h4 class="card-title">${board.title}</h4>
	    <p class="card-text">${board.content}</p>
	    <a href="#" class="btn btn-primary">상세 보기</a>
	  </div>
		</div>
	</c:forEach>

</div>


<%@ include file="include/footer.jsp" %>