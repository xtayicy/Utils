package harry.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

/**
 * 生活文件工具
 * 
 * @author harry
 *
 */
public final class FileUtil {
	private FileUtil() {}
	
	public static final void getPathOfFileBySize(String pathName,int size) {
		File file = new File(pathName);
		if(file.isDirectory()) {
			for (File f : file.listFiles()) {
				try {
					getPathOfFileBySize(f.getAbsolutePath(),size);
				}catch (NullPointerException e) {
					continue;
				}
			}
		}else {
			if(file != null && file.isFile() && file.length() / 1024 / 1024 >= size) {
				System.out.println(file.getAbsolutePath());
				System.out.println(file.length() / 1024 / 1024);
				System.out.println();
			}
		}
	}
}
