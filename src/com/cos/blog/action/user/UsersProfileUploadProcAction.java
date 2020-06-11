package com.cos.blog.action.user;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cos.blog.action.Action;
import com.cos.blog.model.Users;
import com.cos.blog.repository.UsersRepository;
import com.cos.blog.util.Script;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class UsersProfileUploadProcAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String realPath = request.getServletContext().getRealPath("images");
		int id;
		String filename = null;
		
		System.out.println("realPath :" + realPath);
		
		String contextPath = request.getServletContext().getContextPath();
		String userProfile = null;	// DB에 들어갈 변수 : 위치
		
		System.out.println("contextPath : " + contextPath);
		
		try {
			MultipartRequest multi = new MultipartRequest
					(
							request,
							realPath,
							1024*1024*2,
							"UTF-8",
							new DefaultFileRenamePolicy()
					);
			
			/////// 테스트 ///////
			Enumeration<String> files = multi.getFileNames();
			String file1 = files.nextElement();
			System.out.println("UsersProfileUploadProcAction : getFilesystemName : " + multi.getFilesystemName(file1));
			System.out.println("UsersProfileUploadProcAction : getOriginalFileName : " + multi.getOriginalFileName(file1));
			/////// 테스트 ///////
			
			filename = multi.getFilesystemName("userProfile");
			System.out.println("filename : " + filename);
			id = Integer.parseInt(multi.getParameter("id"));
			
			userProfile = contextPath + "/images/" + filename;
			
			UsersRepository userRepository = UsersRepository.getInstance();
			int result = userRepository.update(id, userProfile);
			System.out.println("result : " + result);
			
			if (result==1) {
				
				HttpSession session = request.getSession();
				Users principal = userRepository.findById(id);
				session.setAttribute("principal", principal);
				
				Script.href("사진 변경 완료", "/blog/index.jsp", response);
			} else {
				Script.back("사진 변경 실패", response);
			}
		} catch (Exception e) {
			e.printStackTrace();
			Script.getMessage("오류 : "+e.getMessage(), response);
		}
	}

}
