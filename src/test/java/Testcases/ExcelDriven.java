package Testcases;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelDriven {
	
		 
	public static void main(String[] args) throws Exception {
		File file = new File(System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\Details.xlsx");
		System.out.println(file.exists());
		FileInputStream fs = new FileInputStream(file);
		XSSFWorkbook workbook = new XSSFWorkbook(fs);
		XSSFSheet sheet =workbook.getSheet("Sheet1");
		int noOfRows = sheet.getPhysicalNumberOfRows();
		int noOfColumns = sheet.getRow(0).getLastCellNum();
		
		for(int i=1;i<noOfRows;i++) {
			for (int j=0;j<noOfColumns;j++) {
				System.out.println(sheet.getRow(i).getCell(j).getStringCellValue());
			}
		}
		
		
		workbook.close();
 
	}
	 

}
