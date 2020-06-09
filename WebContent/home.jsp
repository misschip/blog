
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
	
	<ul class="pagination justify-content-center"> <!-- justify-content-start -->

		<c:choose>		<%-- 아래 param.page == null은 empty param.page로 써도 동일할 듯한데 확인 필요 --%>
			<c:when test="${param.page == 0 || param.page == null}">	<%-- /board?cmd=home만 있는 경우 page=0으로 취급하는데 이때 param.page는 null --%>
				<li class="page-item disabled"><a class="page-link" href="/blog/board?cmd=home&page=${param.page-1}">Previous</a></li>
			</c:when>
			<c:otherwise>
				<li class="page-item"><a class="page-link" href="/blog/board?cmd=home&page=${param.page-1}">Previous</a></li>
			</c:otherwise>
		</c:choose>
		<c:choose>
			<c:when test="${isLast}">
				<li class="page-item disabled"><a class="page-link" href="/blog/board?cmd=home&page=${param.page+1}">Next</a></li>
			</c:when>
			<c:otherwise>
				<li class="page-item"><a class="page-link" href="/blog/board?cmd=home&page=${param.page+1}">Next</a></li>
			</c:otherwise>
		</c:choose>

	</ul>


</div>


<%@ include file="include/footer.jsp" %>