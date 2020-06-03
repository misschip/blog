<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%
	// response.sendRedirect("/blog/board?cmd=home");
%>

<c:redirect url="/board?cmd=home" />
<!--  c:redirect는 잘 안 쓰이는 태그임. -->
<!--  왜 그런진 모르지만 c:redirect로 쓸때는 blog 부분을 빼야 제대로 된다 -->