package harry.test;

import org.junit.Test;

import harry.utils.DatabaseUtil;

public class DatabaseUtilTest {
	@Test
	public void createDatabase() {
		DatabaseUtil.execute("CREATE DATABASE DbTest",null,false);
	}
	
	@Test
	public void dropDatabase() {
		DatabaseUtil.execute("DROP DATABASE DbTest", null,false);
	}
	
	@Test
	public void createTable() {
		DatabaseUtil.execute("CREATE TABLE tableTest(username VARCHAR(20),age INT)","DbTest",false);
	}
	
	@Test
	public void dropTable() {
		DatabaseUtil.execute("DROP TABLE tableTest", "DbTest",false);
	}
	
	@Test
	public void insertData() {
		DatabaseUtil.execute("INSERT INTO tableTest VALUES('harry',15)", "DbTest",false);
		DatabaseUtil.execute("INSERT INTO tableTest VALUES('marry',16)", "DbTest",false);
	}
	
	@Test
	public void updateData() {
		DatabaseUtil.execute("UPDATE tableTest SET username='marry' WHERE username='harry'", "DbTest",false);
	}
	
	@Test
	public void query() {
		DatabaseUtil.execute("SELECT * FROM tableTest", "DbTest",false);
	}
	
	@Test
	public void delete() {
		DatabaseUtil.execute("DELETE FROM tableTest WHERE username='marry'", "DbTest",false);
	}
}
