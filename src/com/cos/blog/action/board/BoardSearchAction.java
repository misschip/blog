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
import com.cos.blog.util.HtmlParser;
import com.cos.blog.util.Script;

public class BoardSearchAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if (request.getParameter("keyword") == null ||
			request.getParameter("keyword").equals("")	
			) {
			Script.back("검색 키워드가 없습니다", response);
			return;
		}
			
		

		int page = Integer.parseInt(request.getParameter("page"));
		String keyword = request.getParameter("keyword");
		
		// 1. DB 연결해서 Board 목록 다 불러와서
		BoardRepository boardRepository = BoardRepository.getInstance();
		
		// 2. 키워드로 검색하여 일치하는 3건만 가져오기
		
		List<Board> boards = boardRepository.findAll(page,keyword);
		
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
		double currentPercent = (double)(page)/(lastPage)*100;
		
		request.setAttribute("lastPage", lastPage);
		request.setAttribute("currentPercent", currentPercent);
		
		// 3. home.jsp로 이동
		RequestDispatcher dis = request.getRequestDispatcher("home.jsp");
		dis.forward(request, response);
	}

}
