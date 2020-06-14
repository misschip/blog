package com.cos.blog.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.cos.blog.db.DBConn;
import com.cos.blog.dto.BoardResponseDto;
import com.cos.blog.model.Board;

// 예전에는 DAO라 칭하던 클래스
public class BoardRepository {
		private static final String TAG = "BoardRepository : ";

		private static BoardRepository instance = new BoardRepository();
		private BoardRepository() {}
		public static BoardRepository getInstance() {
			return instance;
		}
		
		private Connection conn = null;
		private PreparedStatement pstmt = null;
		private ResultSet rs = null;
		
		public int save(Board board) {
			final String SQL = "INSERT INTO board (id,userId,title,content,readCount,createDate) "
						+ " VALUES (board_seq.nextval,?,?,?,0,SYSDATE)";
			
			try {
				conn = DBConn.getConnection();
				pstmt = conn.prepareStatement(SQL);
				// 물음표 완성하기
				pstmt.setInt(1, board.getUserId());
				pstmt.setString(2, board.getTitle());
				pstmt.setString(3, board.getContent());
				
				return pstmt.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println(TAG + "save : " + e.getMessage());
			} finally {
				DBConn.close(conn, pstmt);
			}
			return -1;
		}
		
		public int update(Board board) {
			final String SQL = "";
			
			try {
				conn = DBConn.getConnection();
				pstmt = conn.prepareStatement(SQL);
				// 물음표 완성하기
				
				
				return pstmt.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println(TAG + "update : " + e.getMessage());
			} finally {
				DBConn.close(conn, pstmt);
			}
			return -1;
		}
		
		public int deleteById(int id) {
			final String SQL = "";
			
			try {
				conn = DBConn.getConnection();
				pstmt = conn.prepareStatement(SQL);
				// 물음표 완성하기
				
				
				return pstmt.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println(TAG + "deleteById : " + e.getMessage());
			} finally {
				DBConn.close(conn, pstmt);
			}
			return -1;
		}
		
		public List<BoardResponseDto> findAll() {
			final String SQL = "SELECT board.id,userId,title,content,readCount,board.createDate, username "
							+ " FROM board "
							+ " INNER JOIN users "
							+ " ON board.userId = users.id";
			List<BoardResponseDto> boardDtos = new ArrayList<>();
			
			try {
				conn = DBConn.getConnection();
				pstmt = conn.prepareStatement(SQL);
				// 물음표 완성하기
				
				rs = pstmt.executeQuery();
				// while 돌려서 리스트에 넣기
				while (rs.next()) {
					Board board = Board.builder()
							.id(rs.getInt("id"))
							.userId(rs.getInt("userId"))
							.title(rs.getString("title"))
							.content(rs.getString("content"))
							.readCount(rs.getInt("readCount"))
							.createDate(rs.getTimestamp("createDate"))
							.build();
					
					BoardResponseDto boardDto = BoardResponseDto.builder()
							.board(board)
							.username(rs.getString("username"))
							.build();
					
					boardDtos.add(boardDto);
				}
				
				return boardDtos;
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println(TAG + "findAll : " + e.getMessage());
			} finally {
				DBConn.close(conn, pstmt, rs);
			}
			return null;
		}
		
		
		public Board findById(int id) {
			final String SQL = "";
			Board board = new Board();
			
			try {
				conn = DBConn.getConnection();
				pstmt = conn.prepareStatement(SQL);
				// 물음표 완성하기
				
				// if 해서 rs
				
				return board;
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println(TAG + "findById : " + e.getMessage());
			} finally {
				DBConn.close(conn, pstmt, rs);
			}
			return null;
		}
}
