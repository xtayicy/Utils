package harry.domain;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class Video {
	private String ffmpegPath = "E:/ffmpeg-master-latest-win64-gpl/bin/ffmpeg.exe";
	private String inputPath;
	private String outputPath;
	
	public Video(String inputPath,String outputPath) {
		this.inputPath = inputPath;
		this.outputPath = outputPath;
	}
	
	public void convert() {
		List<String> command = new ArrayList<String>();
		command.add(ffmpegPath);
		command.add("-i");
		command.add(inputPath);
		command.add("-c:v");
		command.add("libx264");
		command.add("-mbd");
		command.add("0");
		command.add("-c:a");
		command.add("aac");
		command.add("-strict");
		command.add("-2");
		command.add("-pix_fmt");
		command.add("yuv420p");
		command.add("-movflags");
		command.add("faststart");
		command.add(outputPath);
		try {
			Process process = new ProcessBuilder(command).redirectErrorStream(true).start();
			new PrintStream(process.getErrorStream()).start();
			new PrintStream(process.getInputStream()).start();
			process.waitFor();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

class PrintStream extends Thread{
	private InputStream is;
	
	public PrintStream(InputStream is) {
		this.is = is;
	}
	
	public void run() {
		while(this != null) {
			try {
				int ch = is.read();
				if(ch != -1) {
					System.out.print((char)ch);
				}else {
					break;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
