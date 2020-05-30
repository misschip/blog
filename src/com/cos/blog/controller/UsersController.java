package com.cos.blog.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cos.blog.action.Action;
import com.cos.blog.action.user.UsersJoinAction;
import com.cos.blog.action.user.UsersJoinProcAction;

// http://localhost:8000/blog/user
@WebServlet("/user")
public class UsersController extends HttpServlet {
	private static final String TAG = "UsersController : ";
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// http://localhost:8000/blog/user?cmd=join
		String cmd = request.getParameter("cmd");
		System.out.println(TAG + "doProcess : " + cmd);
		Action action = router(cmd);
		action.execute(request, response);
		
		
	}
	
	
	// 팩토리 패턴
	public Action router(String cmd) {
		if (cmd.equals("join")) {
			// 회원가입 페이지로 이동
			return new UsersJoinAction();
		} else if (cmd.equals("joinProc")) {
			// 회원가입을 진행한 후 -> index.jsp로 이동
			return new UsersJoinProcAction();
		} else if (cmd.equals("update")) {
			// 회원정보 수정 페이지로 이동 (세션에 User 오브젝트를 갖고 있을 예정)
		} else if (cmd.equals("updateProc")) {
			// 회원정보 수정을 진행한 후 -> index.jsp로 이동
		} else if (cmd.equals("delete")) {
			// 회원 삭제를 진행한 후 -> 로그아웃을 하고 -> index.jsp로 이동
		} else if (cmd.equals("login")) {
			// 회원 로그인 페이지로 이동
			return new UsersLoginAction();
		} else if (cmd.equals("loginProc")) {
			// 회원 로그인을 수행한 후 -> 세션에 등록을 하고 -> index.jsp로 이동
		}
		
		return null;
	}

}
