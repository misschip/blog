package com.cos.blog.action.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cos.blog.action.Action;
import com.cos.blog.dto.BoardResponseDto;
import com.cos.blog.dto.DetailResponseDto;
import com.cos.blog.dto.ReplyResponseDto;
import com.cos.blog.repository.BoardRepository;
import com.cos.blog.repository.ReplyRepository;
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
		int boardId = Integer.parseInt(request.getParameter("id"));
		BoardRepository boardRepository = BoardRepository.getInstance();
		
		//조회수 증가가 상세보기가 되기 전에 실행되는 것이 좋음. MVC 패턴. jsp에서 실제 View로 보여주기 전 단계에서
		//조회수 증가 관련 코드를 자바 코드로 미리 처리하고 jsp에서는 그 결과만 가져와 보여주기
		int result = boardRepository.updateReadCount(boardId);
		


		if(result != 1) {
			Script.back("서버 오류!", response);
			return;
		}
		
		// Board, Users (해당 게시물의 글과 작성자)
		BoardResponseDto boardDto = boardRepository.findById(boardId);	// username 정보가 있어야 하기 때문에 Board, Users 두 테이블
																//  JOIN이 필요하고 그래서 model의 클래스가 아니라 DetailResponseDto에 담아야
		
		
		// Reply, Users (해당 게시물의 댓글과 댓글의 작성자) 리스트
		ReplyRepository replyRepository = ReplyRepository.getInstance();
		List<ReplyResponseDto> replyDtos = replyRepository.findAll(boardId); 
		
		DetailResponseDto detailDto = DetailResponseDto.builder()
										.boardDto(boardDto)
										.replyDtos(replyDtos)
										.build();
		
		
		// detailDto.setReplies(replyDtos);
		
		if (detailDto != null) {
			// 유튜브 파싱하기. 유튜브 영상 링크 주소가 있을 시 그 아래에 미리보기 화면을 추가하기 위함(iframe 태그 활용)
			String content = boardDto.getBoard().getContent();
			content =HtmlParser.getContentYoutube(content);
			boardDto.getBoard().setContent(content);
			
			
			request.setAttribute("detailDto", detailDto);	// detail.jsp로 dto 객체를 넘기기 위함
			RequestDispatcher dis = request.getRequestDispatcher("board/detail.jsp");
			dis.forward(request, response);
		} else {
			// 상세보기를 클릭했을 때 이 분기를 타고 온다는 건 뭔가 정상적인 접근이 아님을 추정할 수
			Script.back("잘못된 접근입니다", response);
		}
	}

}
