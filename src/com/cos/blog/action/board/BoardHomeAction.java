package com.cos.blog.action.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cos.blog.action.Action;
import com.cos.blog.model.Board;
import com.cos.blog.repository.BoardRepository;

public class BoardHomeAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 쿠키 정보 출력해 보는 루틴. 나중에 지울것!
//		Cookie[] cookies = request.getCookies();
//		if (cookies != null) {
//			for (Cookie cookie : cookies) {
//				System.out.println("모든 쿠키 출력: " + cookie.getName() + " : " + cookie.getValue());
//				//if (cookie.getName().equals("remember")) {
//					//request.setAttribute("remember", cookie.getValue());
//					//System.out.println("cookie.getValue(): " + cookie.getValue());
//				//}
//			}
//		}	// 쿠키 정보 출력해 보는 루틴. 나중에 지울것!
		
		// 1. DB 연결해서 Board 목록 다 불러와서
		BoardRepository boardRepository = BoardRepository.getInstance();
		List<Board> boards = boardRepository.findAll();
		
		// 본문을 미리보기 형태로 짧게 가공하기
		for (Board board : boards) {
			String preview = board.getContent();
			// 글 본문(content)가 10글자 미만인 경우에도 아래 substring(0,10)에서 indexOutOfBounds 에러가 뜨지 않는 건 미스테리임
			preview = preview.substring(0, 10) + "...";
			board.setContent(preview);
		}
		
		// 2. request에 담고
		request.setAttribute("boards", boards);
		
		// 3. home.jsp로 이동
		RequestDispatcher dis = request.getRequestDispatcher("home.jsp");
		dis.forward(request, response);
	}

}
