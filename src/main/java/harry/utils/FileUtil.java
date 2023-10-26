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
	private static final String BLANK = " ";
	private static final String EQUALS = "=";

	private FileUtil() {}
	
	public static final void append(File file,String content) throws IOException{
		try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file,true)))) {
			writer.write(content);
			writer.write("\r\n");
		}
	}
	
	private static final String getContent(File file) throws IOException{
		StringBuffer sb = new StringBuffer();
		char[] buffer = new char[1024];
		int len;
		try (Reader reader = new FileReader(file)) {
			while ((len = IOUtils.read(reader, buffer)) > 0) {
				sb.append(buffer,0,len);
			}
		}
		
		return sb.toString();
	}
	
	public static final String getResult(String srcFile, Map<String, String> map,String prefContent) throws IOException {
		File file = new File(srcFile);
		if (!file.exists())
			throw new FileNotFoundException();
		else if (!file.isFile())
			throw new FileNotFoundException();

		if (CheckUtil.isNullOrZero(map))
			throw new IllegalArgumentException();
		
		
		Map<String,String> linkedHashMap = new LinkedHashMap<String,String>();
		for (String blankResults: getContent(file).split(BLANK)) {
			linkedHashMap.put(blankResults.split(EQUALS)[0], blankResults.split(EQUALS)[1]);
		}
		
		for (Entry<String, String> entry : map.entrySet()) {
			if(linkedHashMap.containsKey(entry.getKey()))
				linkedHashMap.put(entry.getKey(), entry.getValue());
		}
		
		StringBuffer sb = new StringBuffer(prefContent);
		for (Entry<String,String> entry : linkedHashMap.entrySet()) {
			sb.append(entry.getKey()).append(EQUALS).append(entry.getValue()).append(BLANK);
		}
		
		return sb.toString();
	}
	
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

	public static final void copy(String sourceFile, String... destDirectories) throws IOException {
		File srcFile = new File(sourceFile);
		String fileName;
		if (!srcFile.exists())
			throw new FileNotFoundException();
		else if (srcFile.isFile())
			fileName = srcFile.getName();
		else
			return;

		if (CheckUtil.isNullOrZero(destDirectories))
			throw new IllegalArgumentException();

		for (String destDir : destDirectories) {
			if (!new File(destDir, fileName).exists()) {
				FileUtils.copyFileToDirectory(srcFile, new File(destDir));
			}
		}
	}
}
