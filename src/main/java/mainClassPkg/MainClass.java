package mainClassPkg;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import dataProviderClassPkg.DataProviderClass;

public class MainClass {
public WebDriver driver;
	
	public Properties ConfigProp() throws IOException {
		Properties config = new Properties();
		FileInputStream file = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\resources\\config.properties");
		config.load(file);
		return config;
	}
	
	@BeforeTest
	public void LaunchBrowser() throws IOException {
		System.setProperty("webdriver.chrome.driver", "H:\\selenium\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(ConfigProp().getProperty("URL"));
	}
	@AfterTest
	public void CloseBrowser() {
		//driver.quit();
	}
	
	public Object[][] ReadExcelData(String SheetName, String[] header) throws Exception{
		File file = new File(System.getProperty("user.dir")+"\\src\\test\\resources\\TestData.xlsx");
		DataProviderClass obj1 = new DataProviderClass();
		Object[][] data = obj1.ReadExcel(SheetName, file, header);
		return data;
	}
	
	public void WriteExcelData(String Result) throws Exception {
		File file = new File(System.getProperty("user.dir")+"\\src\\test\\resources\\TestData.xlsx");
		DataProviderClass obj1 = new DataProviderClass();
		obj1.WriteData(Result, file);
	}
	

}
