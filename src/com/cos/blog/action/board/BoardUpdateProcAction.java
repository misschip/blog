package com.cos.blog.action.board;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cos.blog.action.Action;
import com.cos.blog.model.Board;
import com.cos.blog.model.Users;
import com.cos.blog.repository.BoardRepository;
import com.cos.blog.util.Script;

public class BoardUpdateProcAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 0번. 인증 확인
		HttpSession session = request.getSession();
		Users user = (Users)session.getAttribute("principal");
		if (user == null) {
			Script.getMessage("잘못된 접근입니다", response);
			return;
		}
		
		// 1번. request에 id, title값과 content값 null인지 공백인지 확인
		String boardId = request.getParameter("id");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		if (
			boardId == null || boardId.equals("") ||
			title == null || title.equals("") ||	
			content == null || content.equals("")
			) return;	// id, title이나 content가 비었을 때는 조용히 종료하도록!
			
		// 2번. request에 title값과 content값 받기
		// 3번. title과 content, principal.getId() 값을 Board 오브젝트에 담기
		
		int id = Integer.parseInt(boardId);
		
		// 3번. BoardRepository 연결해서 save(board 객체) 함수 호출
		Board board = Board.builder()
				.id(id)
				.title(title)
				.content(content)
				.build();
				
		BoardRepository boardRepository = BoardRepository.getInstance();
		int result = boardRepository.update(board);
		// System.out.println("result :" + result);
		
		// 4번. result == 1이면 성공 (index.jsp로 이동)
		// 5번. result != 1 이면 실패 (history.back())
		if (result == 1) {
			Script.href("수정 성공", "/blog/board?cmd=detail&id=" + id, response);
		} else {
		
			Script.back("수정에 실패하였습니다", response);
		}
	}

}
