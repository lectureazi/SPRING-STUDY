package strategy.framework;

import java.sql.Connection;

@FunctionalInterface
public interface ConnectionCreator {
	
	public Connection getConnection();

}
