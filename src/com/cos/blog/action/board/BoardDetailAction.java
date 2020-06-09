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
		// id값을 파라메터로 넘겨받지 못한 경우 처리. id값은 home.jsp에서 넘겨진다
		if (request.getParameter("id") == null || request.getParameter("id").equals("")) {
			Script.back("잘못된 접근입니다", response);
			return;
		}
		
		// 상세보기를 할 글의 id값
		int id = Integer.parseInt(request.getParameter("id"));
		BoardRepository boardRepository = BoardRepository.getInstance();
		
		//조회수 증가가 상세보기가 되기 전에 실행되는 것이 좋음
		int result = boardRepository.updateReadCount(id);

		if(result != 1) {
			Script.back("서버 오류!", response);
			return;
		}
		
		
		DetailResponseDto dto = boardRepository.findById(id);	// username 정보가 있어야 하기 때문에 Board, Users 두 테이블
																//  JOIN이 필요하고 그래서 model의 클래스가 아니라 DetailResponseDto에 담아야
		
		
		if (dto != null) {
			// 유튜브 파싱하기. 유튜브 영상 링크 주소가 있을 시 그 아래에 미리보기 화면을 추가하기 위함(iframe 태그 활용)
			String content = dto.getBoard().getContent();
			content =HtmlParser.getContentYoutube(content);
			dto.getBoard().setContent(content);
			
			
			request.setAttribute("dto", dto);	// detail.jsp로 dto 객체를 넘기기 위함
			RequestDispatcher dis = request.getRequestDispatcher("board/detail.jsp");
			dis.forward(request, response);
		} else {
			// 상세보기를 클릭했을 때 이 분기를 타고 온다는 건 뭔가 정상적인 접근이 아님을 추정할 수
			Script.back("잘못된 접근입니다", response);
		}
	}
	
	

}
