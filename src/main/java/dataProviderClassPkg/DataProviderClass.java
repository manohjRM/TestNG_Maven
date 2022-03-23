package dataProviderClassPkg;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Arrays;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

import mainClassPkg.MainClass;

public class DataProviderClass extends MainClass{
	static String shName;
	@DataProvider(name="Login")
	public Object[][] loginData() throws Exception{
		shName = ConfigProp().getProperty("Valid");
		String[] header = {"UserName", "Password"};
		Object[][] data = ReadExcelData(shName, header);
		return data;
	}
	
	@DataProvider(name="InvalidLogin")
	public Object[][] invalidLoginData() throws Exception{
		shName = ConfigProp().getProperty("Invalid");
		String[] header = {"UserName", "Password"};
		Object[][] data = ReadExcelData(shName, header);
		return data;
	}
	
	public String[][] ReadExcel(String SheetName, File FilePath, String[] header) throws Exception {
		String[][] data = null;
		FileInputStream fis = new FileInputStream(FilePath);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet = wb.getSheet(SheetName);
		int rowCount = wb.getSheet(SheetName).getPhysicalNumberOfRows();
		XSSFRow row0 = sheet.getRow(0);
		
		data = new String[rowCount-1][header.length];
		
		for(int i=0; i<header.length;i++) {
			for(int j = 0; j<row0.getLastCellNum();j++) {
				String head = row0.getCell(j).getStringCellValue();
				if(header[i].equalsIgnoreCase(head)) {
					for(int k=1;k<rowCount;k++) {
						String cellData = sheet.getRow(k).getCell(j).getStringCellValue();
						data[k-1][i]=cellData;
						//System.out.println("data["+(k-1)+"]["+i+"] = "+cellData);
					}
				}
			}
		}
		for(String[] row : data) {
			System.out.println(Arrays.toString(row));
		}
		wb.close();
		return data; 
	}
	public void WriteData(String Result, File file) throws Exception {
		FileInputStream fis = new FileInputStream(file);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet = wb.getSheet(shName);
		XSSFRow row0 = sheet.getRow(0);
		int rowCount = sheet.getLastRowNum()-sheet.getFirstRowNum();
		int colCount = row0.getLastCellNum();
		for(int i=1;i<=rowCount;i++) {
			for(int j=0;j<colCount;j++) {
				if(row0.getCell(j).getStringCellValue().equalsIgnoreCase("Result")) {
					sheet.getRow(i).getCell(j).setCellValue(Result);
				}
			}
		}
		FileOutputStream fos = new FileOutputStream(file);
		wb.write(fos);
		wb.close();
		
	}
	
	
}
