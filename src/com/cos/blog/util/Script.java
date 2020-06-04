package com.cos.blog.util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

public class Script {
	public static void getMessage(String msg, HttpServletResponse response) {
		
		try {
			
			response.setCharacterEncoding("utf-8");	// web.xml에 설정하는 게 더 나은 부분
			response.setContentType("text/html;charset=utf-8");	// 이건 web.xml 아님 JSON일 수도 있으므로
			
			PrintWriter out = response.getWriter();
			
			out.println("<h1>" + msg + "</h1>");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
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
			
			response.setCharacterEncoding("utf-8");	// web.xml에 설정하는 게 더 나은 부분
			response.setContentType("text/html;charset=utf-8");	// 이건 web.xml 아님 JSON일 수도 있으므로
			
			PrintWriter out = response.getWriter();
			
			out.println("<script>");
			out.println("alert('" + msg + "');");
			out.println("location.href='" + uri + "';");
			out.println("</script>");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void href(String uri, HttpServletResponse response) {
		
		try {
			
			response.setCharacterEncoding("utf-8");	// web.xml에 설정하는 게 더 나은 부분
			response.setContentType("text/html;charset=utf-8");	// 이건 web.xml 아님 JSON일 수도 있으므로
			
			PrintWriter out = response.getWriter();
			
			out.println("<script>");
			out.println("location.href='" + uri + "';");
			out.println("</script>");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
