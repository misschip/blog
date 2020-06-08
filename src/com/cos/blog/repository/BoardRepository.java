package com.cos.blog.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.cos.blog.db.DBConn;
import com.cos.blog.dto.DetailResponseDto;
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
			final String SQL = "UPDATE board SET title = ?, content = ? WHERE id = ?";
			
			try {
				conn = DBConn.getConnection();
				pstmt = conn.prepareStatement(SQL);
				// 물음표 완성하기
				pstmt.setString(1, board.getTitle());
				pstmt.setString(2, board.getContent());
				pstmt.setInt(3, board.getId());
				
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
			final String SQL = "DELETE FROM board WHERE id = ?";
			
			try {
				conn = DBConn.getConnection();
				pstmt = conn.prepareStatement(SQL);
				// 물음표 완성하기
				pstmt.setInt(1, id);
				
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
		
		
		public DetailResponseDto findById(int id) {
			StringBuilder sb = new StringBuilder();
			sb.append("SELECT b.id, b.userId, b.title, b.content, b.readCount, b.createDate, u.username ");
			sb.append("FROM board b INNER JOIN users u ");
			sb.append("ON b.userId = u.id ");
			sb.append("WHERE b.id = ?");
			
			final String SQL = sb.toString();
			
			DetailResponseDto dto = null;
			
			try {
				conn = DBConn.getConnection();
				pstmt = conn.prepareStatement(SQL);
				// 물음표 완성하기
				pstmt.setInt(1, id);
				
				rs = pstmt.executeQuery();
				
				// if 해서 rs
				if (rs.next()) {
					dto = new DetailResponseDto();
					Board board = Board.builder()
							.id(rs.getInt(1))
							.userId(rs.getInt(2))
							.title(rs.getString(3))
							.content(rs.getString(4))
							.readCount(rs.getInt(5))
							.createDate(rs.getTimestamp(6))
							.build();
					dto.setBoard(board);
					dto.setUsername(rs.getString(7));
				}
				
				return dto;
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println(TAG + "findById : " + e.getMessage());
			} finally {
				DBConn.close(conn, pstmt, rs);
			}
			return null;
		}
}
