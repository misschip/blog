<%@page import="com.cos.blog.model.Users"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- 아래 소스코드 출처는 https://www.w3schools.com/bootstrap4/bootstrap_navbar.asp -->
<!-- Collapsing The Navigation Bar의 Try it Yourself로 들어와서 첫부분부터 긁어와 사용 -->

<%
	Users principal = (Users)session.getAttribute("principal");
	System.out.println("nav.jsp : " + ((principal==null)? "null" : principal.getUsername()));
%>

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
</head>
<body>

<nav class="navbar navbar-expand-md bg-dark navbar-dark">
  <a class="navbar-brand" href="index.jsp">Cos Blog</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="collapsibleNavbar">
    <ul class="navbar-nav">
    
    <% if (principal == null ) { %>
	      <li class="nav-item">
	        <a class="nav-link" href="/blog/user?cmd=login">로그인</a>
	      </li>
	      <li class="nav-item">
	        <a class="nav-link" href="/blog/user?cmd=join">회원가입</a>
	      </li>
      <% } else { %>
      		<li class="nav-item">
	        <a class="nav-link" href="/blog/board?cmd=write">글쓰기</a>
	      	</li>
      		<li class="nav-item">
	        <a class="nav-link" href="/blog/user?cmd=update">회원정보</a>
	      	</li>
	      	<li class="nav-item">
	        <a class="nav-link" href="/blog/user?cmd=logout">로그아웃</a>
      		</li>
      
      <% }; %>
      
    </ul>
  </div>  
</nav>
<br>