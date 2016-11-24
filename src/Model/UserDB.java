package Model;

import util.*;

import java.sql.*;

public class UserDB {
	public void addUser(String username, String password, String salt) {
		Connection conn = DB.getConnection();
		if (conn != null) {
			try {
				PreparedStatement pstmt = conn.prepareStatement("INSERT INTO user VALUES (?,?,?)");
				pstmt.setString(1, username);
				pstmt.setString(2, password);
				pstmt.setString(3, salt);
				pstmt.execute();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}
	
	public User getUser(String username) {
		User user = null;
		Connection conn = DB.getConnection();
		if (conn != null) {
			try {
				PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM user WHERE username = ?");
				pstmt.setString(1, username);

				ResultSet rs = pstmt.executeQuery();
				if (rs.next()) {
					user = new User(rs.getString("username"),rs.getString("password"),rs.getString("salt"));
				}
			} catch (Exception e) {
				System.out.println(e);
			}
		}
		
		return user;
	}
}
