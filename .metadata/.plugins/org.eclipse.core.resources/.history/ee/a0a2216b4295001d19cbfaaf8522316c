package templateMethod.client;

import java.util.List;

import templateMethod.framework.JDBCRepository;

public class Run {

	public static void main(String[] args) {
		
		JDBCRepository repository = new JDBCRepository(new ClientJDBCTemplate());
		String sql = "select * from member where user_id = 'admin'";
		System.out.println(repository.selectOne(sql, List.of("user_id", "tell")));
		

	}
}
