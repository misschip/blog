package com.cos.blog.action.user;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cos.blog.action.Action;
import com.cos.blog.model.Users;
import com.cos.blog.repository.UsersRepository;
import com.cos.blog.util.SHA256;
import com.cos.blog.util.Script;

public class UsersLoginProcAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 1. Validation 체크
				if
				(
						request.getParameter("username") == null ||
						request.getParameter("username").equals("") ||
						request.getParameter("password") == null ||
						request.getParameter("password").equals("")
						) {
					// request로부터 상대방의 ip 주소등을 로그로 남기는게 좋다고 함 (공격자)
					return;
				}
				
				
			String username = request.getParameter("username");
			String rawPassword = request.getParameter("password");
			
			String password = SHA256.encodeSha256(rawPassword);
	
			UsersRepository usersRepository = UsersRepository.getInstance();
			// 세션은 사용자 정보를 다 담고 있어야 하므로 사용자 정보 전체 받아옴
			Users user = usersRepository.findByUsernameAndPassword(username, password);
			
			if (user != null) {
				HttpSession session = request.getSession();
				
				// session.setAttribute("users", user);
				session.setAttribute("principal", user);	// principal : 인증주체
				
				// checkbox가 선택된 경우에는 "on"이 반환되고 그 외는 null인 듯. 이건 form으로 테스트해 보면 금방 나옴.
				// 체크박스가 선택된 경우에 쿠키에 "remember"를 key로 하고 username을 value로 해서 저장한 후
				// 클라이언트에 대한 response에 담아서 보냄
				if (request.getParameter("remember") != null) {
					// 아래 코드에 의해 response header에는
					// key => Set-Cookie
					// value => remember=ssar
					// 라고 하는 항목으로 클라이언트로 보냄
					Cookie cookie = new Cookie("remember", user.getUsername());
					// cookie.setHttpOnly(true); 	// 자바스크립트에 의한 쿠키 탈취를 방지함
					response.addCookie(cookie);
					
					// 결국 위 2줄 코드는 아래 한줄 코드와 동일한 작용을 함. ssar은 username
					// response.setHeader("Set-Cookie", "remember=ssar");
				} else {	// 쿠키 삭제
					// Cookie cookie = new Cookie("remember", user.getUsername());
					Cookie cookie = new Cookie("remember", "");	// 어차피 사라질 쿠키이므로 두번째 매개값은 ""로 해도 ok
					cookie.setMaxAge(0);	// setMaxAge(0)으로 하는 게 핵심!
					response.addCookie(cookie);
				}
		
				Script.href("로그인 성공", "/blog/index.jsp", response);
			} else {
				Script.back("로그인  실패", response);
			}
	}
	
}
