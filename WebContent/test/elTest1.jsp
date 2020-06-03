<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	// 동일한 key로 page, request, session, application에 저장한 경우
	// 우선 순위는 아래와 같다. 가까운 객체가 우선순위를 갖는 셈
	// page -> request -> session -> application
	request.setAttribute("username", "ssar");
	session.setAttribute("username", "1234");
	application.setAttribute("username", "appUser");
	RequestDispatcher dis = request.getRequestDispatcher("elTest2.jsp");
	dis.forward(request, response);
%>