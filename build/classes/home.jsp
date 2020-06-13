<%@page import="com.cos.blog.model.Users"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<%@ include file='include/navBar.jsp' %>

	<%
	Users user = (Users)session.getAttribute("users");
	
	%>
	<% if (user != null) { %>
		<%= user.getUsername() %> 님 환영합니다.
	<% } %>
</body>


<div class="container">


<c:forEach var="board" items="${boards}">
  <div class="card" style="width:400px">
    <img class="card-img-top" src="images/userProfile.png" alt="Card image" style="width:100%">
    <div class="card-body">
      <h4 class="card-title">${board.title}</h4>
      <p class="card-text">${board.content}</p>
      <a href="#" class="btn btn-primary">상세보기</a>
    </div>
  </div>
  <br>
  </c:forEach>
 
  
</div>
  

<%@ include file="include/footer.jsp" %>
