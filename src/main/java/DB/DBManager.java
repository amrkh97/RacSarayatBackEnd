package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.dbcp2.BasicDataSource;


public class DBManager {

	private static BasicDataSource ds =new BasicDataSource();
	static{
		ds.setUrl("jdbc:sqlserver://AMRKHALED97;");
		ds.setUsername("admin");
		ds.setPassword("1234");
		ds.setMinIdle(1);
		ds.setMaxIdle(10);
		ds.setMaxOpenPreparedStatements(100);
	}
	
	private static String db_class_string = "com.microsoft.sqlserver.jdbc.SQLServerDriver";

	private static String db_server = "jdbc:sqlserver://AMRKHALED97;";
	private static String db_name = "databaseName=RAC_SARAYAT";
	private static String db_userid = "admin";
	private static String db_password = "1234";
	private static Connection conn;

	public static ResultSet ExecuteQuery(String SelectStatement) {

		return null;
	}

	public static int ExecuteNonQuery(String DMLStatement) {
		return 0;
	}

	public static Connection getDBConn() {
		
		try {
			System.out.println("NEW METHOD");
			System.out.println("Connection Open");
			return ds.getConnection();
		} catch (Exception e) {
			
			try {
				Class.forName(db_class_string);
				conn = DriverManager.getConnection(db_server + db_name, db_userid, db_password);
				System.out.println("OLD METHOD");
				System.out.println("Connection Open");
			} catch (Exception e2) {

				e2.printStackTrace();
			}
			return conn;
			
		}

	}
	
	public static Connection newConn() throws SQLException
	{
		return ds.getConnection();
	}
	

}
