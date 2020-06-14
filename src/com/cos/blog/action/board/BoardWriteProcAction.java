package com.cos.blog.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cos.blog.action.Action;
import com.cos.blog.model.Board;
import com.cos.blog.model.Users;
import com.cos.blog.repository.BoardRepository;
import com.cos.blog.util.Script;

public class BoardWriteProcAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		if (
				title == null || title.equals("") ||
				content == null || content.equals("")
			) {
				Script.back("제목과 내용 모두 입력해야 합니다.", response);
				return;
			
		}
		
		HttpSession session = request.getSession();
		Users u = (Users) session.getAttribute("principal");
		int userId = u.getId();
		
		Board board = Board.builder()
				.userId(userId)
				.title(title)
				.content(content)
				.build();
		
		BoardRepository boardRepository = BoardRepository.getInstance();
		int result = boardRepository.save(board);
		
		if (result==1) {
			Script.href("글쓰기 성공", "index.jsp", response);
		} else {
			Script.back("글쓰기 실패", response);
		}
	}

}
