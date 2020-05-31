package com.cos.blog.action.user;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cos.blog.action.Action;
import com.cos.blog.model.Users;
import com.cos.blog.repository.UsersRepository;
import com.cos.blog.util.Script;

public class UsersLoginProcAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		if (
				username == null || username.equals("") ||
				password == null || password.equals("")
			)
		return;

		UsersRepository usersRepository = UsersRepository.getInstance();
		Users u = usersRepository.findByUsernameAndPassword(username,password);
		
		if (u != null) {
			HttpSession session = request.getSession();
			session.setAttribute("users", u);
			
			RequestDispatcher dis = request.getRequestDispatcher("index.jsp");
			dis.forward(request, response);
		} else {
			Script.href("로그인에 실패했습니다", "user/login.jsp", response);
		}
	}

}
