package harry.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import harry.domain.Result;

public class DatabaseUtil {
	private static String url = "jdbc:mysql://localhost:3306"; 
	private static String username = "root";
	private static String password = "";
	
	public static void main(String[] args) {
		//DatabaseUtil.execute("CREATE DATABASE DbTest",null);
		//DatabaseUtil.execute("CREATE TABLE tableTest(username VARCHAR(20))","DbTest");
	}
	
	public static Result execute(String sql,String dbName) {
		
		return executeSQL(sql,dbName);
	}
	
	private static Result executeSQL(String sql,String dbName) {
		try(Connection conn = DriverManager.getConnection(url, username, password);
	             PreparedStatement stmt = conn.prepareStatement(sql)){
			stmt.execute();
		}catch(Exception e) {
			e.printStackTrace();
			if(e.getMessage().equals(Result.ERROR_MESSAGE_DATABASE)) {
				selectDatabase(dbName);
				executeSQL(sql,dbName);
			}
			
			return new Result(e.getMessage());
		}
		
		return new Result();
	}

	private static void selectDatabase(String dbName) {
		DatabaseUtil.url = url.concat("/" + dbName);
	}
}
