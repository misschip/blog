package com.cos.blog.action.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cos.blog.action.Action;
import com.cos.blog.model.Board;
import com.cos.blog.repository.BoardRepository;
import com.cos.blog.util.HtmlParser;

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
		
		int page = Integer.parseInt(request.getParameter("page"));
		
		// 1. DB 연결해서 Board 목록 다 불러와서
		BoardRepository boardRepository = BoardRepository.getInstance();
		
		// 2. 3건만 페이징하여 가져오기
		List<Board> boards = boardRepository.findAll(page);
		
		// List<Board> boards = boardRepository.findAll();
		
		// 본문을 미리보기 형태로 짧게 가공하기
		for (Board board : boards) {
			String preview = HtmlParser.getContentPreview(board.getContent());
			board.setContent(preview);
		}
		
		
		// 2. request에 담고
		request.setAttribute("boards", boards);
		
		// 2.5 마지막 페이지 확인 로직
		int count = boardRepository.count();
		int lastPage = (count-1)/3;	// 한페이지에 글 3개씩 보여주고 0페이지에서 이미 3개 보여주고 있으므로
		// 현재 페이지가 전체의 몇 프로인지
		double currentPercent = (double)(page)/(lastPage)*100;
		
		request.setAttribute("lastPage", lastPage);
		request.setAttribute("currentPercent", currentPercent);
		
		// 이전 페이지 정보
		HttpSession session = request.getSession();
		session.setAttribute("backPage", page);
		session.setAttribute("backKeyword", null);
		
		// 3. home.jsp로 이동
		RequestDispatcher dis = request.getRequestDispatcher("home.jsp");
		dis.forward(request, response);
	}

}
