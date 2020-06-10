
<%@page import="com.cos.blog.model.Users"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="include/nav.jsp" %>



<h1> </h1>

<!-- 아래 부트스트랩 코드의 소스는
https://www.w3schools.com/bootstrap4/bootstrap_cards.asp 에서
Card Images 항목에 있는 걸 반복해서 붙여넣은 것! -->


<div class="container">

	<%-- div 크기를 화면 전체크기에 가깝게 키우고 form을 justify-content-end로 오른쪽 끝 부분에 배치 --%>
	<div class="col-md-12 m-2"> <%-- 이 부분 소스 출처 https://www.w3schools.com/bootstrap4/bootstrap_navbar.asp 에서 Navbar Forms and Buttons --%>
		<%-- <div class="d-flex justify-content-center align-items-center container"> 	--%>
		<form class="form-inline justify-content-end" action="/blog/board" method="get">
		<input type="hidden" name="cmd" value="search" />
		<input type="hidden" name="page" value="0" />
    	<input type="text" name="keyword" class="form-control mr-sm-2" placeholder="Search">
    	<button class="btn btn-primary" type="submit">검색</button>
  </form>

	</div>
	 
	<%-- 아래 c:forEach에서는 items 부분에 쌍따옴표 빠뜨리거나 ${}를 빠뜨리기 쉬우므로 주의! --%>
	<c:forEach var="board" items="${boards}">	<%-- 직전의 BoardHomeAction에서 request.setAttribute("boards", boards)로 저장한 것을 가져오는 것 --%>
		<div class="card m-2" style="width:100%">  <!-- 고정 픽셀보다는 화면 퍼센트로 지정  -->
	  <!-- <img class="card-img-top" src="img_avatar1.png" alt="Card image">  -->
	  <div class="card-body">
	  	<%-- board.title, board.content EL 표현식 사용시
	  		board.getTitle(), board.getContent() 메서드(Board 클래스의 getter)가 호출된다 --%>
	    <h4 class="card-title">${board.title}</h4>
	    <p class="card-text">${board.content}</p>
	    <a href="/blog/board?cmd=detail&id=${board.id}" class="btn btn-primary">상세 보기</a>
	  </div>
		</div>
	</c:forEach>

	<br/>
	
	
	<%@ include file="include/paging.jsp"%>
	


</div>


<%@ include file="include/footer.jsp" %>