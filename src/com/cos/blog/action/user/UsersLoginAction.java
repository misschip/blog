package com.cos.blog.action.user;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cos.blog.action.Action;

public class UsersLoginAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 쿠키 정보를 갖고 오는 두가지 방법
		// 1. request header에서 Cookie 라는 key로 들어있는 값을 갖고 옴.
		//	  이 방식은 여러 쿠키가 한꺼번에 다 들어있으므로 파싱을 해야 개별 쿠키를 분리할 수 있음
		
//		String myCookie = request.getHeader("Cookie");
//		System.out.println("myCookie : " + myCookie);
		
		// 2. request의 getCookies() 메서드를 사용
		//	 Cookie 객체를 배열로 한번에 가져오므로 이후 작업을 하기가 편리함
//		Cookie[] cookies = request.getCookies();
//		if (cookies != null) {
//			for (Cookie cookie : cookies) {
//				System.out.println("모든 쿠키 출력: " + cookie.getName() + " : " + cookie.getValue());
//				if (cookie.getName().equals("remember")) {
//					request.setAttribute("remember", cookie.getValue());
//					// System.out.println("cookie.getValue(): " + cookie.getValue());
//				}
//			}
//		}
		
		RequestDispatcher dis = request.getRequestDispatcher("user/login.jsp");
		dis.forward(request, response);
		
	}
	
}
