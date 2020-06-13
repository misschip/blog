package com.cos.blog.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.cos.blog.db.DBConn;
import com.cos.blog.model.Users;

// 예전에는 DAO라 칭하던 클래스
public class UsersRepository {
	private static final String TAG = "UsersRepository : ";

	private static UsersRepository instance = new UsersRepository();

	private UsersRepository() {
	}

	public static UsersRepository getInstance() {
		return instance;
	}

	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	public Users findByUsernameAndPassword(String username, String password) {
		final String SQL = "SELECT id,username,password,email,address,userProfile,userRole,createDate "
				+ " FROM users WHERE username = ? AND password = ?";
		Users user = null;

		try {
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(SQL);
			// 물음표 완성하기
			pstmt.setString(1, username);
			pstmt.setString(2, password);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				user = Users.builder().id(rs.getInt("id")).username(rs.getString("username"))
						.password(rs.getString("password")).email(rs.getString("email"))
						.address(rs.getString("address")).userProfile(rs.getString("userProfile"))
						.userRole(rs.getString("userRole")).createDate(rs.getTimestamp("createDate")).build();
			}
			return user;

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(TAG + "findByUsernameAndPassword : " + e.getMessage());
		} finally {
			DBConn.close(conn, pstmt, rs);
		}
		return null;
	}

	public int save(Users user) {
		final String SQL = "INSERT INTO users(id,username,password,email,address,userRole,createDate) VALUES (USERS_SEQ.NEXTVAL, ?,?,?,?,?,SYSDATE)";

		try {
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(SQL);
			// 물음표 완성하기
			pstmt.setString(1, user.getUsername());
			pstmt.setString(2, user.getPassword());
			pstmt.setString(3, user.getEmail());
			pstmt.setString(4, user.getAddress());
			pstmt.setString(5, user.getUserRole());

			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(TAG + "save : " + e.getMessage());
		} finally {
			DBConn.close(conn, pstmt);
		}
		return -1;
	}

	public int update(Users user) {
		final String SQL = "UPDATE users SET username = ?, password = ? "
				+ "email = ?, address = ?, userProfile = ?, userRole = ? " + "WHERE id = ?";

		try {
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(SQL);
			// 물음표 완성하기
			pstmt.setString(1, user.getUsername());
			pstmt.setString(2, user.getPassword());
			pstmt.setString(3, user.getEmail());
			pstmt.setString(4, user.getAddress());
			pstmt.setString(5, user.getUserProfile());
			pstmt.setString(6, user.getUserRole());
			pstmt.setInt(7, user.getId());

			int result = pstmt.executeUpdate();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(TAG + "update : " + e.getMessage());
		} finally {
			DBConn.close(conn, pstmt);
		}
		return -1;
	}

	public int deleteById(int id) {
		final String SQL = "DELETE FROM users WHERE id = ?";

		try {
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(SQL);
			// 물음표 완성하기
			pstmt.setInt(1, id);

			int result = pstmt.executeUpdate();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(TAG + "deleteById : " + e.getMessage());
		} finally {
			DBConn.close(conn, pstmt);
		}
		return -1;
	}

	// 반환값 : users 객체
	// null인 경우 : 수행 중 예외 발생
	// users.size() == 0 인 경우 : 테이블에 사용자가 0명
	// users.size() > 0인 경우 : 사용자가 1명 이상 존재
	public List<Users> findAll() {
		final String SQL = "SELECT id,username,password,email,address,userProfile,userRole,createDate " + "FROM users";
		List<Users> users = new ArrayList<>();

		try {
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(SQL);
			rs = pstmt.executeQuery();

			// while 돌려서 리스트에 넣기
			while (rs.next()) {
				Users u = Users.builder().id(rs.getInt("id")).username(rs.getString("username"))
						.password(rs.getString("password")).email(rs.getString("email"))
						.address(rs.getString("address")).userProfile(rs.getString("userProfile"))
						.userRole(rs.getString("userRole")).createDate(rs.getTimestamp("createDate")).build();

				users.add(u);
			}

			return users;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(TAG + "findAll : " + e.getMessage());
		} finally {
			DBConn.close(conn, pstmt, rs);
		}
		return null;
	}

	public Users findById(int id) {
		final String SQL = "SELECT id,username,password,email,address,userProfile,userRole,createDate "
				+ " FROM users WHERE id = ?";
		Users user = null;

		try {
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(SQL);
			// 물음표 완성하기
			pstmt.setInt(1, id);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				user = Users.builder().id(rs.getInt("id")).username(rs.getString("username"))
						.password(rs.getString("password")).email(rs.getString("email"))
						.address(rs.getString("address")).userProfile(rs.getString("userProfile"))
						.userRole(rs.getString("userRole")).createDate(rs.getTimestamp("createDate")).build();
			}
			return user;

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(TAG + "findById : " + e.getMessage());
		} finally {
			DBConn.close(conn, pstmt, rs);
		}
		return null;
	}
}
