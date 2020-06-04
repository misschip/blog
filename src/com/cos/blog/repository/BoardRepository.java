package com.cos.blog.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.cos.blog.db.DBConn;
import com.cos.blog.model.Board;
import com.cos.blog.model.Users;

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
			final String SQL = "INSERT INTO board (id,userId,title,content,readCount,createDate) VALUES (BOARD_SEQ.NEXTVAL,?,?,?,?,SYSDATE)";
			
			try {
				conn = DBConn.getConnection();
				pstmt = conn.prepareStatement(SQL);
				// 물음표 완성하기
				pstmt.setInt(1, board.getUserId());
				pstmt.setString(2, board.getTitle());
				pstmt.setString(3, board.getContent());
				pstmt.setInt(4, board.getReadCount());
				// readCount는 0 디폴트로 입력되므로 생략해도 됨
				
				int result = pstmt.executeUpdate();
				return result;
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
		
		public List<Board> findAll() {
			final String SQL = "SELECT id,userId,title,content,readCount,createDate FROM board";
			List<Board> boards = new ArrayList<>();
			
			try {
				conn = DBConn.getConnection();
				pstmt = conn.prepareStatement(SQL);
				// 물음표 완성하기
				
				rs = pstmt.executeQuery();
				// while 돌려서 리스트에 넣기
				while(rs.next()) {
					Board board = Board.builder()
							.id(rs.getInt("id"))
							.userId(rs.getInt("userId"))
							.title(rs.getString("title"))
							.content(rs.getString("content"))
							.readCount(rs.getInt("readCount"))
							.createDate(rs.getTimestamp("createDate"))
							.build();
					
					boards.add(board);
				}
				
				return boards;
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
