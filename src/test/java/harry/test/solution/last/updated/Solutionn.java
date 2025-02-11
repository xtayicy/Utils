package harry.test.solution.last.updated;

import java.io.File;

import org.junit.Test;

public class Solutionn {
	@Test
	public void testLastUpdated() {
		File directory = new File("D:\\.m2\\repository");
		listFile(directory);
	}
	
	public void listFile(File directory) {
		for (File file : directory.listFiles()) {
			//System.out.println(file.getAbsolutePath());
			if(file.isFile()) {
				if(file.getName().endsWith(".lastUpdated")) {
					//System.out.println(file.getName());
					file.delete();
				}
				//System.out.println(file.getName());
				//System.out.println("file = " + file.getAbsolutePath());
			}else {
				//System.out.println(file.getAbsolutePath());
				//System.out.println(file.getAbsoluteFile().getAbsolutePath());
				listFile(file.getAbsoluteFile());
			}
		}
	}
}
