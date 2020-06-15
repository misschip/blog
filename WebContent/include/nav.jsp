
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<!-- 아래 소스코드 출처는 https://www.w3schools.com/bootstrap4/bootstrap_navbar.asp -->
<!-- Collapsing The Navigation Bar의 Try it Yourself로 들어와서 첫부분부터 긁어와 사용 -->

 
<%--
	세션에 저장된 principal과 user 객체는 이제 EL을 사용해서 간편하게 가져오기 때문에 ( ${sessionScope.principal} )
	아래 한 줄 코드는 필요없음
	Users principal = (Users)session.getAttribute("principal");
	System.out.println("nav.jsp : " + ((principal==null)? "null" : principal.getUsername()));
--%>


<!DOCTYPE html>
<html lang="en">
<head>
  <title>Cos Blog</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
  
   <link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.css" rel="stylesheet">
  <script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.js"></script>
  
   <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
  
  <link href="/blog/css/styles.css" rel="stylesheet">
</head>
<body>

<nav class="navbar navbar-expand-md bg-dark navbar-dark">
  <a class="navbar-brand" href="/blog/index.jsp">Cos Blog</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
    <span class="navbar-toggler-icon"></span>
  </button>
  <%-- 아래 justify-content-between (justify-content-end, justify-content-around) 등으로 메뉴 배치 바꿈 --%>
  <div class="justify-content-between collapse navbar-collapse" id="collapsibleNavbar">
    <ul class="navbar-nav">
    
    <%-- <% if (principal == null ) { %> --%>
    <c:choose>
    	<c:when test="${empty sessionScope.principal}">
	      <li class="nav-item">
	        <a class="nav-link" href="/blog/user?cmd=login">로그인</a>
	      </li>
	      <li class="nav-item">
	        <a class="nav-link" href="/blog/user?cmd=join">회원가입</a>
	      </li>
	      </c:when>
      <%-- <% } else {  %> --%>
      	<c:otherwise>
      		<li class="nav-item">
	        <a class="nav-link" href="/blog/board?cmd=write">글쓰기</a>
	      	</li>
      		<li class="nav-item">
	        <a class="nav-link" href="/blog/user?cmd=update">회원정보</a>
	      	</li>
	      	<li class="nav-item">
	        <a class="nav-link" href="/blog/user?cmd=logout">로그아웃</a>
      		</li>
      
     <%--  <% }; %> --%>
      	</c:otherwise>
      </c:choose>
    </ul>
    
    <ul class="navbar-nav">
    	<c:if test="${not empty sessionScope.principal}">
    	<li class="nav-item">
    		<a href="/blog/user?cmd=profileUpload">
    		<img style="border-radius:20px" onerror="this.src='/blog/images/userProfile.png'" src="${sessionScope.principal.userProfile}" width="40px" height="40px"/>
    		</a>
    	</li>
    	</c:if>
    </ul>
    
  </div>  
</nav>
<br>