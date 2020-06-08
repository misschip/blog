package com.cos.blog.action.board;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cos.blog.action.Action;
import com.cos.blog.dto.DetailResponseDto;
import com.cos.blog.repository.BoardRepository;
import com.cos.blog.util.HtmlParser;
import com.cos.blog.util.Script;

public class BoardDetailAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// id값을 파라메터로 넘겨받지 못한 경우 처리
		if (request.getParameter("id") == null || request.getParameter("id").equals("")) {
			Script.back("잘못된 접근입니다", response);
			return;
		}
		
		int id = Integer.parseInt(request.getParameter("id"));
		BoardRepository boardRepository = BoardRepository.getInstance();
		DetailResponseDto dto = boardRepository.findById(id);
		
		
		
		if (dto != null) {
			// 유튜브 파싱하기
			String content = dto.getBoard().getContent();
			content =HtmlParser.getContentYoutube(content);
			dto.getBoard().setContent(content);
			
			
			request.setAttribute("dto", dto);
			RequestDispatcher dis = request.getRequestDispatcher("board/detail.jsp");
			dis.forward(request, response);
		} else {
			// 상세보기를 클릭했을 때 이 분기를 타고 온다는 건 뭔가 정상적인 접근이 아님을 추정할 수
			Script.back("잘못된 접근입니다", response);
		}
	}
	
	

}
