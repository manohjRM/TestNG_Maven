package objectClassPkg;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


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
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		username.sendKeys(UName);
		password.sendKeys(Pwd);
		try {
			loginBtn.click();
		}catch(Exception e) {
			//String retry = retryPage.getText();
			retryLogin.click();	
		}
		
	}
	
}
