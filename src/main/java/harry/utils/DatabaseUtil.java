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
	
	public static Result execute(String sql,String dbName,boolean isOpenDebugMode) {
		Long startTime = retrieveTimeWithDebugMode(isOpenDebugMode);
		selectDatabase(dbName);
		Result result = executeSQL(sql,dbName);
		if(isOpenDebugMode) {
			Long endTime = retrieveTimeWithDebugMode(isOpenDebugMode);
			retrieveDurationTime(result,startTime,endTime);
		}
		
		return result;
	}
	
	private static Long retrieveTimeWithDebugMode(boolean isOpenDebugMode) {
		if(isOpenDebugMode) {
			return System.currentTimeMillis();
		}
		
		return null;
	}

	private static void retrieveDurationTime(Result result, long startTime, long endTime) {
		// TODO Auto-generated method stub
		result.setMsg(result.getMsg().concat(" ").concat("time=").concat(String.valueOf(endTime - startTime)));
	}

	private static Result executeSQL(String sql,String dbName) {
		String resultMessage = initMsg();
		
		try(Connection conn = DriverManager.getConnection(url, username, password);
	             PreparedStatement stmt = conn.prepareStatement(sql)){
			if(IsQuery(sql)) {
				ResultSet resultSet = stmt.executeQuery(sql);
				while(resultSet.next()) {
					ResultSetMetaData metaData = resultSet.getMetaData();
					for(int i = 1;i <= metaData.getColumnCount();i++) {
						resultMessage = retrieveData(resultMessage,metaData.getColumnLabel(i),resultSet.getObject(i));
					}
				}
			}else {
				stmt.execute();
			}
		}catch(Exception e) {
			return new Result(e.getMessage());
		}
		
		return new Result(resultMessage);
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
		if(dbName != null && !dbName.equals("")) {
			DatabaseUtil.url = url.concat("/" + dbName);
		}
	}
}
