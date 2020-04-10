package it.objectmethod.worldmvcspring.config;

import java.sql.Connection;
import java.sql.DriverManager;

import org.springframework.stereotype.Component;

@Component
public class ConnectionFactory {
	private static final String URL = "jdbc:mysql://localhost:3306/world";
	private static final String USER = "root";
	private static final String PASS = "ciao1234";

	public static Connection getConnection() {
		Connection conn = null;

		try {
			// Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(URL, USER, PASS);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return conn;
	}
}
