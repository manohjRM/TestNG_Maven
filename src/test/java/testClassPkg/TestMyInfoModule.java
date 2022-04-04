package testClassPkg;

import org.testng.annotations.Test;
import java.time.Duration;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import dataProviderClassPkg.DataProviderClass;
import objectClassPkg.LoginPageObjects;
import objectClassPkg.MyInfoPageObjects;

@Listeners(listenerClassPkg.ListenerClass.class)

public class TestMyInfoModule extends TestLoginPage{
	
	@Test(dataProvider = "PersonalDetails", dataProviderClass = DataProviderClass.class)
	void loginToApplication(String UserName, String Password, String FirstName, String LastName, 
			String MartialStatus, String Gender, String Nation, String PIT) throws Exception {
		
		LoginPageObjects obj = new LoginPageObjects(driver);
		obj.login(UserName, Password);

	}
	
	@Test(dataProvider = "PersonalDetails", dataProviderClass = DataProviderClass.class, dependsOnMethods = "loginToApplication")
	void personalDetailsform(String UserName, String Password, String FirstName, String LastName, 
			String MartialStatus, String Gender, String Nation, String PIT) throws Exception {
		
		MyInfoPageObjects obj1 = new MyInfoPageObjects(driver);
		obj1.fillPersonalDetailsForm(FirstName, LastName, MartialStatus, Gender, Nation, PIT);
		Thread.sleep(3000);
		System.out.println(obj1.employeeName.getText());
		CaptureScreen(driver, driver.getTitle());
		Assert.assertEquals(obj1.employeeName.getText(), FirstName + " " + LastName);
	}
	
	@Test(dataProvider = "ContactDetails", dataProviderClass = DataProviderClass.class, dependsOnMethods = "loginToApplication")
	void contactDetailsForm(String UserName, String Password, String AddressS1, String AddressS2, String City, 
			String Country, String ZIP, double Mobile, 
			String WorkEmail, String OtherEmail, String State) throws Exception {
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		MyInfoPageObjects obj1 = new MyInfoPageObjects(driver);
		obj1.fillContactDetails(AddressS1, AddressS2, City, Country, ZIP, Mobile, WorkEmail, OtherEmail, State);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		System.out.println(driver.getTitle());
		Thread.sleep(3000);
		wait.until(ExpectedConditions.visibilityOf(obj1.tmsg));
		Assert.assertEquals(obj1.tmsg.getText(), "Successfully Updated");
		System.out.println(obj1.tmsg.getText());
		CaptureScreen(driver, driver.getTitle());
	}
	
	@Test(dataProvider = "EmergencyContacts", dataProviderClass = DataProviderClass.class, dependsOnMethods = "loginToApplication")
	void EmergencyContactForm(String UserName, String Password, String Name, String Relationship, 
			double Mobile) throws Exception {
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		MyInfoPageObjects obj1 = new MyInfoPageObjects(driver);
		obj1.fillEmergencyContacts(Name, Relationship, Mobile);
		System.out.println(driver.getTitle());
		Thread.sleep(3000);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(obj1.tmsg));
		Assert.assertEquals(obj1.tmsg.getText(), "Successfully Saved");
		System.out.println(obj1.tmsg.getText());
		CaptureScreen(driver, driver.getTitle());
	}
	
	@Test(dataProvider = "SocialMedia", dataProviderClass = DataProviderClass.class, dependsOnMethods = "loginToApplication")
	void SocialMediaForm(String UserName, String Password, String SocialType, String Handle, String Link, double Share) throws Exception {
		MyInfoPageObjects obj1 = new MyInfoPageObjects(driver);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		obj1.fillSocialMediadetails(SocialType, Handle, Link, Share);
		System.out.println(driver.getTitle());
		Thread.sleep(3000);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(obj1.tmsg));
		Assert.assertEquals(obj1.tmsg.getText(), "Successfully Saved");
		System.out.println(obj1.tmsg.getText());
		CaptureScreen(driver, driver.getTitle());
	}
	
	@Test(dataProvider = "Dependents", dataProviderClass = DataProviderClass.class, dependsOnMethods = "loginToApplication")
	void DependentForm(String UserName, String Password, String Name, String DoB, String Relation, String Others) throws Exception {
		MyInfoPageObjects obj1 = new MyInfoPageObjects(driver);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		obj1.fillDependents(Name, DoB, Relation, Others);
		System.out.println(driver.getTitle());
		Thread.sleep(2000);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(obj1.tmsg));
		Assert.assertEquals(obj1.tmsg.getText(), "Successfully Saved");
		System.out.println(obj1.tmsg.getText());
		CaptureScreen(driver, driver.getTitle());
	}
	
	@Test(dataProvider = "Immigration", dataProviderClass = DataProviderClass.class, dependsOnMethods = "loginToApplication")
	void ImmigrationForm(String UserName, String Password, String Document, String DNumber, String IssueDate, 
			String ExpiryDate, String ReviewDate, String Status, String Comments, String IssueCountry) throws Exception {
		
		MyInfoPageObjects obj1 = new MyInfoPageObjects(driver);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		obj1.fillImmigrationDetails(Document, DNumber, IssueDate, ExpiryDate, ReviewDate, Status, Comments, IssueCountry);
		System.out.println(driver.getTitle());
		Thread.sleep(2500);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(obj1.tmsg));
		Assert.assertEquals(obj1.tmsg.getText(), "Successfully Saved");
		System.out.println(obj1.tmsg.getText());
		CaptureScreen(driver, driver.getTitle());
		Thread.sleep(2000);
	}
	
	@Test(dataProvider = "Membership", dataProviderClass = DataProviderClass.class, dependsOnMethods = "loginToApplication")
	void MembershipForm(String UserName, String Password, String Membership) throws Exception {
		MyInfoPageObjects obj1 = new MyInfoPageObjects(driver);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		obj1.fillMembershipForm(Membership);
		System.out.println(driver.getTitle());
		Thread.sleep(2500);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(obj1.tmsg));
		Assert.assertEquals(obj1.tmsg.getText(), "Successfully Saved");
		System.out.println(obj1.tmsg.getText());
		CaptureScreen(driver, driver.getTitle());
		Thread.sleep(2000);
	}
	@Test(dataProvider = "Education", dataProviderClass = DataProviderClass.class, dependsOnMethods = "loginToApplication")
	void AddEducationForm(String UserName, String Password, String Level, String Institute, String Major) throws Exception {
		MyInfoPageObjects obj1 = new MyInfoPageObjects(driver);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		obj1.fillEducationForm(Level, Institute, Major);
		System.out.println(driver.getTitle());
		Thread.sleep(2500);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(obj1.tmsg));
		Assert.assertEquals(obj1.tmsg.getText(), "Successfully Saved");
		System.out.println(obj1.tmsg.getText());
		Thread.sleep(2000);
	}
	@Test(dataProvider = "WorkExperience", dataProviderClass = DataProviderClass.class, dependsOnMethods = "loginToApplication")
	void AddWorkExperienceForm(String UserName, String Password, String Employer, String Role) throws Exception {
		MyInfoPageObjects obj1 = new MyInfoPageObjects(driver);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		obj1.fillWorkExperience(Employer, Role);
		System.out.println(driver.getTitle());
		Thread.sleep(2500);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(obj1.tmsg));
		Assert.assertEquals(obj1.tmsg.getText(), "Successfully Saved");
		System.out.println(obj1.tmsg.getText());
		Thread.sleep(2000);
	}
	
	@Test(dataProvider = "Skills", dataProviderClass = DataProviderClass.class, dependsOnMethods = "loginToApplication")
	void AddSkillsForm(String UserName, String Password, String Skills) throws Exception {
		MyInfoPageObjects obj1 = new MyInfoPageObjects(driver);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		obj1.fillSkillsForm(Skills);
		System.out.println(driver.getTitle());
		Thread.sleep(2500);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(obj1.tmsg));
		Assert.assertEquals(obj1.tmsg.getText(), "Successfully Saved");
		System.out.println(obj1.tmsg.getText());
		Thread.sleep(2000);
	}
	
	@Test(dataProvider = "Language", dataProviderClass = DataProviderClass.class, dependsOnMethods = "loginToApplication")
	void AddLanguage(String UserName, String Password, String Language, String Fluency, String Competency) throws Exception {
		MyInfoPageObjects obj1 = new MyInfoPageObjects(driver);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		obj1.fillLanguageForm(Language, Fluency, Competency);
		System.out.println(driver.getTitle());
		Thread.sleep(2500);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(obj1.tmsg));
		Assert.assertEquals(obj1.tmsg.getText(), "Successfully Saved");
		System.out.println(obj1.tmsg.getText());
		Thread.sleep(2000);
	}
}
