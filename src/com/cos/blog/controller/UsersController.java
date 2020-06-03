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
import com.cos.blog.action.user.UsersLoginAction;
import com.cos.blog.action.user.UsersLoginProcAction;
import com.cos.blog.action.user.UsersLogoutAction;
import com.cos.blog.action.user.UsersUsernameCheckAction;

// http://localhost:8000/blog/user
// 등 /blog/user 주소로 들어오는 GET, POST 요청을 전부 아래 서블릿이 처리함
// 실제로는 http://localhost:8000/blog/user?cmd=login
// http://localhost:8000/blog/user?cmd=join 등 주소값으로 요청이 들어오게 된다.
// 또는 cmd=loginProc, cmd=joinProc 등의 경우는 Form 입력을 통해 POST 방식으로 요청이 들어온다.
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
	
	// GET이든 POST등 결국은 아래 doProcess()가 처리하게 된다.
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// http://localhost:8000/blog/user?cmd=join
		String cmd = request.getParameter("cmd");
		System.out.println(TAG + "doProcess : " + cmd);
		// cmd값이 무엇이냐에 따라 각기 다른 Action 구현 객체가 반환됨
		Action action = router(cmd);
		// execute() 실행시 request, response 객체가 전달되고 있음
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
			return new UsersLoginProcAction();
		} else if (cmd.equals("logout")) {
			
			
			return new UsersLogoutAction();
		} else if (cmd.equals("usernameCheck")) {
			return new UsersUsernameCheckAction();
		}
		
		return null;
	}

}
