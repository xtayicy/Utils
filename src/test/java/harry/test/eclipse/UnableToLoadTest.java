package harry.test.eclipse;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.ibatis.reflection.SystemMetaObject;
import org.junit.Test;

public class UnableToLoadTest {
	@Test
	public void test() throws IOException {
		File file = new File("eclipse/unableToLoad.txt");
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
		String line;
		while((line = br.readLine()) != null) {
			String template = "<dependency>\r\n"
					+ "  <groupId>GROUPID</groupId>\r\n"
					+ "  <artifactId>ARTIFACTID</artifactId>\r\n"
					+ "  <version>VERSION</version>\r\n"
					+ "</dependency>";
			//System.out.println(line);
			line = line.substring(line.indexOf("'") + 1, line.lastIndexOf("'"));
			line = line.substring(0, line.lastIndexOf("/"));
			String version;
			version = line.substring(line.lastIndexOf("/") + 1);
			//System.out.println("version = " + version);
			line = line.substring(0, line.lastIndexOf("/"));
			String artifactId;
			artifactId = line.substring(line.lastIndexOf("/") + 1);
			//System.out.println("artifactId = " + artifactId);
			line = line.substring(0,line.lastIndexOf("/"));
			line = line.substring(line.indexOf("/") + 1);
			String[] arrs = line.split("/");
			String groupId = "/";
			for (String s : arrs) {
				groupId = groupId.concat(s);
				groupId = groupId.concat(".");
			}
			groupId = groupId.substring(1, groupId.lastIndexOf("."));
			//System.out.println("groupId = " + groupId);
			template = template.replace("GROUPID", groupId).replace("ARTIFACTID", artifactId).replace("VERSION", version);
			System.out.println(template);
			System.out.println();
		}
		
		br.close();
	}
}
