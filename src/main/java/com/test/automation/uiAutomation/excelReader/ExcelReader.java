package com.test.automation.uiAutomation.excelReader;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {
	
	public FileOutputStream fos;
	public FileInputStream fis;
	public String path;
	public XSSFWorkbook workbook;
	public XSSFSheet sheet;
	public XSSFRow row;
	public XSSFCell cell;
	
	
	public ExcelReader(String path) {
		this.path = path;
		try {
			fis = new FileInputStream(path);
			workbook = new XSSFWorkbook(fis);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String[][] getTestDataFromExcel(String fileName, String sheetName) {
		String dataset [][] = null;
		try {
			sheet = workbook.getSheet(sheetName);
			int rowCount = sheet.getLastRowNum()+1;
			int colCount = sheet.getRow(0).getLastCellNum();
			dataset = new String[rowCount-1][colCount];
			for (int i = 0; i < rowCount-1; i++) {
				row = sheet.getRow(i+1);
				for (int j = 0; j < colCount; j++) {
					cell = row.getCell(j);
					dataset[i][j] = cell.getStringCellValue();
				}
			}
			return dataset;
		} catch (Exception e) {
			System.out.println("Exception in reading excel file" + e.getMessage());
			e.printStackTrace();
		}
		return dataset;
	}

}
