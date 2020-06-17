package com.cos.blog.action.kakao;

import java.io.IOException;
import java.net.HttpURLConnection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cos.blog.action.Action;
import com.cos.blog.util.Script;

public class KakaoCallbackAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String code = request.getParameter("code");
		// System.out.println("카카오 인증 완료 : 코드값 ");
		// System.out.println(code);
		Script.outText(code, response);
		

		
		
		// POST 요청, x-www-form-urlencoded 타입
		
		// BufferedReader로 읽어서
		
		// Gson으로 파싱

	}

}
