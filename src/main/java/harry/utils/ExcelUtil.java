package harry.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.xml.crypto.dsig.spec.XSLTTransformParameterSpec;

import org.apache.poi.hssf.util.HSSFColor.HSSFColorPredefined;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import harry.domain.Record;

/**
 * 
 * @author harry
 *
 */
public final class ExcelUtil {
	private ExcelUtil(){}
	
	public static void main(String[] args) throws IOException {
		List<String> datas = new ArrayList<String>();
		datas.add("title_score");
		datas.add("name");
		datas.add("age");
		
		//datas.add("title_score");
		//datas.add("name");
		
		List<Record> records = new ArrayList<Record>();
		Record record = new Record();
		List<String> scores = new ArrayList<String>();
		scores.add("A");
		scores.add("B");
		scores.add("C");
		record.setScores(scores);
		record.setName("harry");
		record.setAge(12);
		records.add(record);
		record = new Record();
		scores = new ArrayList<String>();
		scores.add("D");
		scores.add("E");
		scores.add("F");
		record.setScores(scores);
		record.setName("marry");
		record.setAge(11);
		records.add(record);
		
		Map<String,String> map = new HashMap<String,String>();
		for (String key : datas) {
			if(key.equals("title_score")) {
				map.put(key, "打分题");
			}
			
			if(key.equals("name")) {
				map.put(key, "姓名");
			}
			
			if(key.equals("age")) {
				map.put(key, "年龄");
			}
		}
		
		XSSFWorkbook xssfWorkbook = new XSSFWorkbook();
		
		XSSFCellStyle cellStyle = xssfWorkbook.createCellStyle();
		cellStyle.setAlignment(HorizontalAlignment.CENTER);
		cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
		XSSFSheet xssfSheet = xssfWorkbook.createSheet("test");
		xssfSheet.setDefaultColumnWidth(30);
		xssfSheet.setDefaultRowHeightInPoints(20);
		
		CellRangeAddress cellRangeAddress = new CellRangeAddress(0, 1, 0, 0);
		
		xssfSheet.addMergedRegion(cellRangeAddress);
		
		XSSFRow xssfRow = xssfSheet.createRow(0);
		XSSFRow xssfTitileRow = xssfSheet.createRow(1);
		XSSFCell cell = xssfRow.createCell(0);
		cell.setCellStyle(cellStyle);
		cell.setCellValue("候选人面试满意度评价表");
		xssfSheet.setColumnWidth(0, 30 * 256);
		
		int currentCol = 1;
		int size = 0;
		for (String data : datas) {
			cell = xssfRow.createCell(currentCol);
			cell.setCellStyle(cellStyle);
			cell.setCellValue(map.get(data));
			xssfSheet.setColumnWidth(currentCol, 30 * 256);
			if(data.equals("title_score")) {
				size = 2;
				cellRangeAddress = new CellRangeAddress(0, 0, currentCol, currentCol + size);
				for(int i = 0;i <= 2;i++) {
					cell = xssfTitileRow.createCell(currentCol + i);
					cell.setCellStyle(cellStyle);
					cell.setCellValue("打分题" + i);
					xssfSheet.setColumnWidth(currentCol + i, 30 * 256);
				}
			}else {
				cellRangeAddress = new CellRangeAddress(0, 1, currentCol, currentCol);
			}
			
			xssfSheet.addMergedRegion(cellRangeAddress);
			currentCol += size;
			currentCol += 1;
			size = 0;
		}
		
		System.out.println(currentCol);
		
		for(int i = 0;i < records.size();i++) {
			int flag = 0;
			xssfRow = xssfSheet.createRow(i + 2);
			record = records.get(i);
			for(int j = 0;j < currentCol;) {
				if(j == 0) {
					cell = xssfRow.createCell(j++);
					cell.setCellStyle(cellStyle);
					cell.setCellValue(i + 1);
				}else {
					if(flag == 0) {
						scores = record.getScores();
						for(int k = 0;k < scores.size();k++) {
							cell = xssfRow.createCell(j++);
							cell.setCellStyle(cellStyle);
							cell.setCellValue(scores.get(k));
							if(k == scores.size() - 1) {
								flag = 1;
							}
						}
					}
					
					if(flag == 1) {
						cell = xssfRow.createCell(j++);
						cell.setCellStyle(cellStyle);
						cell.setCellValue(record.getName());
						flag = 2;
					}
					
					if(flag == 2) {
						cell = xssfRow.createCell(j++);
						cell.setCellStyle(cellStyle);
						cell.setCellValue(record.getAge());
					}
				}
				xssfSheet.setColumnWidth(j, 30 * 256);
			}
		}
		
		xssfWorkbook.write(new FileOutputStream(new File("test.xlsx")));
		xssfWorkbook.close();
	}
}
