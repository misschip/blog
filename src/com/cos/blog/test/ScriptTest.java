package com.cos.blog.test;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

public class ScriptTest {

	
	public static void href(String uri, HttpServletResponse response) {
		try {
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			
			out.println("<script>");
			// out.println("location.href='" + uri +"';");
			out.println("</script>");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
