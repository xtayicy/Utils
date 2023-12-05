package harry.test;

import org.junit.Test;

import harry.utils.DatabaseUtil;

public class DatabaseUtilTest {
	@Test
	public void createDatabase() {
		DatabaseUtil.execute("CREATE DATABASE DbTest",null);
	}
	
	@Test
	public void dropDatabase() {
		DatabaseUtil.execute("DROP DATABASE DbTest", null);
	}
	
	@Test
	public void createTable() {
		DatabaseUtil.execute("CREATE TABLE tableTest(username VARCHAR(20),age INT)","DbTest");
	}
	
	@Test
	public void dropTable() {
		DatabaseUtil.execute("DROP TABLE tableTest", "DbTest");
	}
	
	@Test
	public void insertData() {
		DatabaseUtil.execute("INSERT INTO tableTest VALUES('harry',15)", "DbTest");
		DatabaseUtil.execute("INSERT INTO tableTest VALUES('marry',16)", "DbTest");
	}
	
	@Test
	public void updateData() {
		DatabaseUtil.execute("UPDATE tableTest SET username='marry' WHERE username='harry'", "DbTest");
	}
	
	@Test
	public void query() {
		DatabaseUtil.execute("SELECT * FROM tableTest", "DbTest");
	}
	
	@Test
	public void delete() {
		DatabaseUtil.execute("DELETE FROM tableTest WHERE username='marry'", "DbTest");
	}
}
