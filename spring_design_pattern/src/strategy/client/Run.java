package strategy.client;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import strategy.framework.JDBCRepository;

public class Run {

	public static void main(String[] args) {
		
		JDBCRepository repository = new JDBCRepository(() -> {
			
			String url = "jdbc:mysql://localhost:3306/bookmanager?useUnicode=true&characterEncoding=utf8";
			Connection conn = null;
			
			try {
				conn = DriverManager.getConnection(url, "bm", "123qwe!@#QWE");
				conn.setAutoCommit(false);
			} catch (SQLException e) {
				e.printStackTrace();
			}

			return conn;
		});
		
		String sql = "select * from member where user_id = 'admin'";
		System.out.println(repository.selectOne(sql, List.of("user_id", "email")));

	}
}
