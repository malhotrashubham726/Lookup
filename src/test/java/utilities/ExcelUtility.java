package utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {
	public String inputPath;
	public FileInputStream fis;
	public FileOutputStream fos;
	public XSSFWorkbook wb;
	public XSSFSheet sheet;

	public ExcelUtility(String inputPath) {
		this.inputPath=inputPath;
	}
	
	public int getRowCount(String sheetName) throws IOException {
		try {
			fis=new FileInputStream(inputPath);
			wb=new XSSFWorkbook(fis);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		sheet=wb.getSheet(sheetName);
		int rows=sheet.getLastRowNum();
		wb.close();
		fis.close();
		return rows;
	}
	
	public int getCellCount(String sheetName, int row) throws IOException {
		fis=new FileInputStream(inputPath);
		wb=new XSSFWorkbook(fis);
		sheet=wb.getSheet(sheetName);
		return sheet.getRow(row).getLastCellNum();
		
	}
	
	public String getCellData(String sheetName, int row, int col) throws IOException {
		fis=new FileInputStream(inputPath);
		wb=new XSSFWorkbook(fis);
		sheet=wb.getSheet(sheetName);
		String data=sheet.getRow(row).getCell(col).toString();
//		System.out.println(sheet.getRow(row).getCell(col).toString());
		
		wb.close();
		fis.close();
		return data;
		
	}
	
	public String setCellData(String sheetName, int row, int col, String value, String outputPath) throws IOException {
		try {
			int rowCount=getRowCount(sheetName);
			fis=new FileInputStream(inputPath);
			wb=new XSSFWorkbook(fis);
			sheet=wb.getSheet(sheetName);
	
			if(row>rowCount) {
				sheet.createRow(row).createCell(col).setCellValue(value);
			}
		
			else {
				sheet.getRow(row).createCell(col).setCellValue(value);
			}
		
			fis.close();
			fos=new FileOutputStream(outputPath);
			wb.write(fos);
			wb.close();
			fos.close();
		
			return "Row gets updated";
		}
		
		catch(Exception e) {
			return e.toString();
		}
	}
}
