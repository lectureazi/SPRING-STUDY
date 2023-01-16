package templateMethod.client;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import templateMethod.framework.common.JDBCTemplate;

public class ClientJDBCTemplate extends JDBCTemplate{

	@Override
	public Connection getConnection() {
		String url = "jdbc:mysql://localhost:3306/bookmanager?useUnicode=true&characterEncoding=utf8";
		
		Connection conn = null;
		
		try {
			conn = DriverManager.getConnection(url, "bm", "123qwe!@#QWE");
			conn.setAutoCommit(false);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return conn;
	}
}
