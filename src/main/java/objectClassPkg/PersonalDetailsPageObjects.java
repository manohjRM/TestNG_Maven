package objectClassPkg;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PersonalDetailsPageObjects {
	WebDriver driver;
	public PersonalDetailsPageObjects(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//a[@id='menu_pim_viewMyDetails']")
	public WebElement myInfoTab;
	@FindBy(xpath="//input[@id='otherId']")
	public WebElement pit;
	@FindBy(xpath="//div[@id='emp_marital_status_inputfileddiv']//input[@type='text']")
	public WebElement martialStatus;
	@FindBy(xpath="//button[@type='submit']")
	public WebElement savePersonalDetails;
	@FindBy(xpath="//a[@id='top-menu-trigger']")
	public WebElement more;
	
	public void fillForm() {
		myInfoTab.click();
		pit.clear();
		pit.sendKeys("515461216565");
		Actions n = new Actions(driver);
		n.moveToElement(martialStatus).click().sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).build().perform();
		savePersonalDetails.click();
	}
}

