package com.cos.blog.action.board;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cos.blog.action.Action;

public class BoardHomeAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 쿠키 정보 출력해 보는 루틴. 나중에 지울것!
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				System.out.println("모든 쿠키 출력: " + cookie.getName() + " : " + cookie.getValue());
				//if (cookie.getName().equals("remember")) {
					//request.setAttribute("remember", cookie.getValue());
					//System.out.println("cookie.getValue(): " + cookie.getValue());
				//}
			}
		}	// 쿠키 정보 출력해 보는 루틴. 나중에 지울것!
		
		// 1. DB 연결해서 Board 목록 다 불러와서
		
		// 2. request에 담고
		
		// 3. home.jsp로 이동
		RequestDispatcher dis = request.getRequestDispatcher("home.jsp");
		dis.forward(request, response);
		
	
	}

}
