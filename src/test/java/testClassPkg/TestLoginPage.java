package testClassPkg;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import dataProviderClassPkg.DataProviderClass;
import mainClassPkg.MainClass;
import objectClassPkg.LoginPageObjects;

@Listeners(listenerClassPkg.ListenerClass.class)

public class TestLoginPage extends MainClass{
	
	@Test(dataProvider = "Login", dataProviderClass = DataProviderClass.class, priority = 0)
	void loginPage(String Username, String Password) throws Exception {
		LoginPageObjects obj = new LoginPageObjects(driver);
		
		obj.login(Username,Password);
		String PageTitle = driver.getTitle();
		System.out.println(PageTitle);
		Assert.assertEquals("Dashboard", PageTitle);
		obj.logout();
		
	}
	
	@Test(dataProvider = "InvalidLogin", dataProviderClass = DataProviderClass.class, dependsOnMethods = "loginPage")
	//, dependsOnMethods = "loginPage"
	void invalidLoginPage(String Username, String Password) throws Exception {
		LoginPageObjects obj = new LoginPageObjects(driver);
		driver.get(ConfigProp().getProperty("URL"));
		obj.login(Username,Password);
		String PageTitle = driver.getTitle();
		System.out.println(PageTitle);
		Assert.assertEquals("OrangeHRM", PageTitle);
		//String cardTitle = driver.findElement(By.xpath("//div[@class='dashboardCard-title-for-card']")).getText();
		//Assert.assertEquals("Retry Login", cardTitle);
		driver.get(ConfigProp().getProperty("URL"));
	}
	
}
