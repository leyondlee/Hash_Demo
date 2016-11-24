package util;

import java.sql.*;

public class DB {
	public static Connection getConnection() {
		Connection conn = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String connURL = ""; //Put connection URL here
			conn = DriverManager.getConnection(connURL);
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return conn;
	}
}
