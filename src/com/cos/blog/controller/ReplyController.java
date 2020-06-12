package com.cos.blog.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cos.blog.action.Action;
import com.cos.blog.action.reply.ReplyWriteProcAction;

// http://localhost:8000/blog/reply
@WebServlet("/reply")
public class ReplyController extends HttpServlet {
	private static final String TAG = "ReplyController : ";
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// http://localhost:8000/blog/reply?cmd=writeProc
		String cmd = request.getParameter("cmd");
		System.out.println(TAG + "doProcess : " + cmd);
		Action action = router(cmd); 
		action.execute(request, response);
	}
	
	
	// 팩토리 패턴 
	public Action router(String cmd) {
		if (cmd.equals("writeProc")) {
			// 회원가입 페이지로 이동
			return new ReplyWriteProcAction();	
		}
		
		return null;
	}

}
