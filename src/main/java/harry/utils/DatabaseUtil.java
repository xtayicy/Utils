package harry.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import harry.constant.Constant;
import harry.domain.Result;

public class DatabaseUtil {
	private static String url = "jdbc:mysql://localhost:3306"; 
	private static String username = "root";
	private static String password = "";
	
	public static Result execute(String sql,String dbName) {
		
		return executeSQL(sql,dbName);
	}
	
	private static Result executeSQL(String sql,String dbName) {
		try(Connection conn = DriverManager.getConnection(url, username, password);
	             PreparedStatement stmt = conn.prepareStatement(sql)){
			if(IsQuery(sql)) {
				String resultMessage = initMsg();
				ResultSet resultSet = stmt.executeQuery(sql);
				while(resultSet.next()) {
					ResultSetMetaData metaData = resultSet.getMetaData();
					for(int i = 1;i <= metaData.getColumnCount();i++) {
						String columnLabel = metaData.getColumnLabel(i);
						Object result = resultSet.getObject(i);
						resultMessage = retrieveData(resultMessage,columnLabel,result);
					}
				}
				
				return new Result(resultMessage);
			}else {
				stmt.execute();
			}
		}catch(Exception e) {
			//for debug,can delete
			if(!e.getMessage().equals(Result.ERROR_MESSAGE_DATABASE)) {
				e.printStackTrace();
			}
			
			if(e.getMessage().equals(Result.ERROR_MESSAGE_DATABASE)) {
				selectDatabase(dbName);
				executeSQL(sql,dbName);
			}
			
			return new Result(e.getMessage());
		}
		
		return new Result();
	}

	private static String retrieveData(String resultMessage,String columnLabel, Object result) {
		resultMessage = resultMessage.concat(columnLabel + "=" + result).concat("\t");
		
		return resultMessage;
	}

	private static String initMsg() {
		return new Result().getMsg();
	}

	private static boolean IsQuery(String sql) {
		
		return sql.toLowerCase().contains(Constant.SELECT_STRING);
	}

	private static void selectDatabase(String dbName) {
		DatabaseUtil.url = url.concat("/" + dbName);
	}
}
