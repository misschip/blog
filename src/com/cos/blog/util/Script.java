package com.cos.blog.util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

public class Script {
	public static void back(String msg, HttpServletResponse response) {
		
		try {
			response.setCharacterEncoding("utf-8");	// web.xml에 설정하는 게 더 나은 부분
			response.setContentType("text/html;charset=utf-8");	// 이건 web.xml 아님 JSON일 수도 있으므로
			PrintWriter out = response.getWriter();
			
			out.println("<script>");
			out.println("alert('" + msg + "');");
			out.println("history.back();");
			out.println("</script>");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public static void href(String msg, String uri, HttpServletResponse response) {
		try {
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			
			out.println("<script>");
			out.println("alert('" + msg + "');");
			out.println("location.href='" + uri +"';");
			out.println("</script>");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/*
	 * HTML DOM에서 location은 window 객체의 일부임
	 * location.href 로 현재 페이지의 주소값을 set하거나 get 할 수 있다.
	 * 
	 * jsp 페이지 내에서 Script.href() 메서드가 호출이 되면
	 * 페이지의 맨 위에 <script>...</scrip>가 출력이 되고
	 * 그에 이어서 비로소 jsp 페이지의 <html> 등 내용들이 합쳐짐을 확인할 수 있다
	 * 그리고 blog/usr/cmd=loginProc 등 URI를 사용하는 주소에서 Script.href()가 호출된 경우에는
	 * 해당 페이지에는 <script> .. </scrip> 만이 출력됨을 확인할 수 있다.
	 * 즉, response.setContentType() 부분 때문에 <html>의 기본 태그들이 덧붙여 출력되지는 않음!
	 */

	public static void href(String uri, HttpServletResponse response) {
		try {
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			
			out.println("<script>");
			out.println("location.href='" + uri +"';");
			out.println("</script>");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
