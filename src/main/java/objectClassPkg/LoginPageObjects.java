package objectClassPkg;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import mainClassPkg.MainClass;

public class LoginPageObjects {
	WebDriver driver;
	public LoginPageObjects(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(id="txtUsername")
	WebElement username;
	@FindBy(id="txtPassword")
	WebElement password;
	@FindBy(xpath="//button[@type='submit']")
	WebElement loginBtn;
	@FindBy(xpath="//div[@class='toast-message']")
	WebElement tmsg;
	@FindBy(xpath="//span[@id='account-job']//following::i[@class='material-icons']")
	WebElement acc_drpdwn;
	@FindBy(xpath="//ul[@id='user_menu']//li//a[@id='logoutLink']")
	WebElement logoutBtn;
	@FindBy(id="btnLogin")
	WebElement retryLogin;
	@FindBy(xpath="//span[@class='restricted']")
	WebElement restrictPage;
	@FindBy(xpath="//div[@class='dashboardCard-title-for-card']")
	WebElement retryPage;
	
	public void login(String UName, String Pwd) throws Exception {
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		username.sendKeys(UName);
		password.sendKeys(Pwd);
		try {
			loginBtn.click();
		}catch(Exception e) {
			String retry = retryPage.getText();
			retryLogin.click();	
		}
		
	}
	public void logout() {
		MainClass obj = new MainClass();
		try {
			acc_drpdwn.click();
			logoutBtn.click();
		}
		catch(Exception e) {
			System.out.println(tmsg.getText());
		}
	}
}
