package strategy.framework;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import strategy.framework.common.JDBCTemplate;
import strategy.framework.common.exception.DataAccessException;

public class JDBCRepository {
	
	private JDBCTemplate jdt;

	public JDBCRepository(ConnectionCreator connectionCreator) {
		super();
		jdt = new JDBCTemplate(connectionCreator);
	}

	public Map<String,Object> selectOne(String sql, List<String> columns){
		Connection conn = jdt.getConnection();
		PreparedStatement pstm;
		ResultSet rset;
		Map<String, Object> res = new HashMap<>();  
		
		try {
			pstm = conn.prepareStatement(sql);
			rset = pstm.executeQuery();
			
			while(rset.next()) {
				for ( String key : columns) {
					res.put(key, rset.getObject(key));
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataAccessException(sql + "쿼리 실행 중 문제가 발생하였습니다.");
		}finally {
			jdt.close(conn);
		}
		
		return res;
	}
}
