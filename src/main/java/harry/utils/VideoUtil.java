package harry.utils;

import java.io.File;

import harry.domain.Video;

public class VideoUtil {
	public static void main(String[] args) {
		String directory = "E:/eclipse/workspaces/Utils/video";
		VideoUtil.listConvert(new File(directory));
	}
	
	public static void listConvert(File directory) {
		File[] files = directory.listFiles();
		for (File f : files) {
			if(f.getAbsolutePath().endsWith(".avi")) {
				String outputPath = f.getAbsolutePath().replace(".avi", ".mp4");
				if(!new File(outputPath).exists()) {
					new Video(f.getAbsolutePath(),outputPath).convert();
				}
			}else if(f.isDirectory()) {
				listConvert(f);
			}
		}
	}
}
