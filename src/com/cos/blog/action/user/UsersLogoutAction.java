package com.cos.blog.action.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cos.blog.action.Action;
import com.cos.blog.util.Script;

public class UsersLogoutAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.invalidate();	// 세션을 무효화
		// session.removeAttribute("principal");	// invalidate() 대신 이것만 하는 방법도 가능은 하지만 잘 안쓴다고
		
		// Script.href("로그아웃 성공", "/blog/board?cmd=home", response);
		Script.href("로그아웃 성공", "index.jsp", response);	// index.jsp에 가보면
															// 로 가도록 설정되어 있으므로
	}

}
