package com.cos.blog.action.user;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cos.blog.action.Action;
import com.cos.blog.model.RoleType;
import com.cos.blog.model.Users;
import com.cos.blog.repository.UsersRepository;
import com.cos.blog.util.SHA256;
import com.cos.blog.util.Script;

public class UsersJoinProcAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 0. 유효성 검사
		if
		(
				request.getParameter("username") == null ||
				request.getParameter("username").equals("") ||
				request.getParameter("password") == null ||
				request.getParameter("password").equals("") ||
				request.getParameter("email") == null ||
				request.getParameter("email").equals("") ||
				request.getParameter("address") == null ||
				request.getParameter("address").equals("")
				) {
			// request로부터 상대방의 ip 주소등을 로그로 남기는게 좋다고 함 (공격자)
			return;
		}
		
		// 1. 파라메터 받기 (x-www-form-urlencoded 라는 MIME 타입. key=value)
		String username = request.getParameter("username");
		String rawPassword = request.getParameter("password");
		String password = SHA256.encodeSha256(rawPassword);
		String email = request.getParameter("email");
		String address = request.getParameter("address");
		String userRole = RoleType.USER.toString();	// 기본값은 일반 사용자임. 테이블에다가 default로 들어가게 세팅해도 됨
		
		// 2. User 오브젝트 변환
		Users user = Users.builder()
				.username(username)
				.password(password)
				.email(email)
				.address(address)
				.userRole(userRole)
				.build();
		
		// 3. DB 연결 - UsersRepository의 save() 호출
		UsersRepository usersRepository = UsersRepository.getInstance();
		int  result = usersRepository.save(user);
		
		// 4. index.jsp 페이지로 이동
		if (result == 1) {
			
			
//			RequestDispatcher dis = request.getRequestDispatcher("user/login.jsp");
//			dis.forward(request, response);
			// request.getRequestDispatcher("/blog/user?cmd=login")로 할 필요는 없음
			// uri 사용하면 폴더 구조 노출되지 않으므로 굳이 RequestDispatcher 사용할 필요가 없음
			
			// 무조건 Controller를 타는 주소로 보낼 것! cmd=xxx
			// 방법 1		- 잘 안 쓰이는 방식	- 이 방식으로도 uri 사용하므로 직접 폴더 구조가 노출되지는 않음
			// response.sendRedirect("/blog/user?cmd=login");
			// 방법 2		- 방법1보다 좋은 점. 메세지창을 띄울 수 있음
			Script.href("회원가입에 성공했습니다", "/blog/user?cmd=login", response);
			
		} else {
			Script.back("회원가입에 실패하였습니다", response);
		}
	}
}
