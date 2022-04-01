package testClassPkg;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import dataProviderClassPkg.DataProviderClass;
import mainClassPkg.MainClass;
import objectClassPkg.LoginPageObjects;
import objectClassPkg.LogoutFunction;

@Listeners(listenerClassPkg.ListenerClass.class)

public class TestLoginPage extends MainClass{
	
	@Test(dataProvider = "Login", dataProviderClass = DataProviderClass.class, priority = 0)
	void loginPage(String Username, String Password) throws Exception {
		LoginPageObjects obj = new LoginPageObjects(driver);
		LogoutFunction obj1 = new LogoutFunction(driver);
		obj.login(Username,Password);
		String PageTitle = driver.getTitle();
		System.out.println(PageTitle);
		Assert.assertEquals("Dashboard", PageTitle);
		CaptureScreen(driver, "Valid");
		obj1.logout();
		
	}
	
	@Test(dataProvider = "InvalidLogin", dataProviderClass = DataProviderClass.class, dependsOnMethods = "loginPage")
	void invalidLoginPage(String Username, String Password) throws Exception {
		LoginPageObjects obj = new LoginPageObjects(driver);
		driver.get(ConfigProp().getProperty("URL"));
		obj.login(Username,Password);
		String PageTitle = driver.getTitle();
		System.out.println(PageTitle);
		Assert.assertNotEquals("Dashboard", PageTitle);
		CaptureScreen(driver, "Invalid");
		driver.get(ConfigProp().getProperty("URL"));
	}
	
}
