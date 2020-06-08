package com.cos.blog.action.user;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cos.blog.action.Action;
import com.cos.blog.repository.UsersRepository;
import com.cos.blog.util.Script;

public class UsersUsernameCheckAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		
		UsersRepository usersRepository = UsersRepository.getInstance();
		int result = usersRepository.findByUsername(username);
		
		// 체크한 결과값은 0(username이 중복되지 않는 아이디인 경우) 또는 1(username이 중복되는 아이디인 경우)이다.
		// join.jsp의 usernameCheck() 함수에 의해 이 메서드가 실행이 되는데 그에 대한 return값을 out에 출력하는 방식으로 전달하는 것은 좀 의문이다.
		// 
//		PrintWriter out = response.getWriter();
//		out.print(result);
		
		Script.outText(result + "", response);

	}

}
