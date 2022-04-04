package objectClassPkg;

import java.time.Duration;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import mainClassPkg.ReUseClass;

public class MyInfoPageObjects {
	WebDriver driver;
	static String saveCountry;

	//WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	ReUseClass obj = new ReUseClass();
	public MyInfoPageObjects(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//a[@id='menu_pim_viewMyDetails']")
	public WebElement myInfoTab;
	@FindBy(xpath="//input[@id='otherId']")
	public WebElement pit;
	@FindBy(id="emp_marital_status")
	public WebElement martialStatus;
	@FindBy(xpath="//button[@type='submit']")
	public WebElement savePersonalDetails;
	@FindBy(xpath="//a[@id='top-menu-trigger']")
	public WebElement more;
	@FindBy(id="firstName")
	public WebElement fname;
	@FindBy(id="lastName")
	public WebElement lname;
	@FindBy(id="emp_gender")
	public WebElement gender;
	@FindBy(id="nation_code")
	public WebElement nationality;
	@FindBy(id="pim.navbar.employeeName")
	public WebElement employeeName;
	
	public void fillPersonalDetailsForm(String FirstName, String LastName, String MartialStatus, String Gender, String Nation, String PIT) {
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		myInfoTab.click();
		fname.clear();
		fname.sendKeys(FirstName);
		lname.clear();
		lname.sendKeys(LastName);
		pit.clear();
		pit.sendKeys(PIT);
		obj.Select(martialStatus, MartialStatus);
		obj.Select(gender, Gender);
		obj.Select(nationality, Nation);
		savePersonalDetails.click();
	}
	
	@FindBy(xpath="//input[@id='street1']")
	public WebElement address1;
	@FindBy(xpath="//input[@id='street2']")
	public WebElement address2;
	@FindBy(xpath="//input[@id='city']")
	public WebElement city;
	@FindBy(xpath="//div[@class='input-field col s12 m12 l4']//div")
	public WebElement countryField;
	@FindBy(css = "ul#select-options-c9fe75d2-60f9-01d9-47ed-c54b97f40338")
	public WebElement country;
	@FindBy(xpath="//div[@id='province_inputfileddiv']")
	public WebElement state;
	@FindBy(id="province")
	public WebElement province;
	@FindBy(id="emp_zipcode")
	public WebElement zip;
	@FindBy(xpath="//input[@id='emp_hm_telephone']")
	public WebElement hmTele;
	@FindBy(xpath="//input[@id='emp_mobile']")
	public WebElement mobile;
	@FindBy(xpath="//input[@id='emp_work_telephone']")
	public WebElement workTele;
	@FindBy(xpath="//input[@id='emp_work_email']")
	public WebElement workEmail;
	@FindBy(xpath="//input[@id='emp_oth_email']")
	public WebElement otherEmail;
	@FindBy(xpath="//button[@id='modal-save-button']")
	public WebElement save;
	@FindBy(xpath="//div[@class='toast-message']")
	public WebElement tmsg;
	@FindBy(xpath="//ul[@id='top-menu']//following::a[@id='top-menu-trigger']")
	public WebElement menuMod;
	@FindBy(xpath="//span[@translate='Contact Details']")
	public WebElement conDetails;
	
	public void fillContactDetails(String AddressS1, String AddressS2, String City, String Country, String ZIP, double Mobile, String WorkEmail, String OtherEmail, String State) throws Exception {
		myInfoTab.click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOf(menuMod));
		//obj.WaitSelectable(menuMod);
		menuMod.click();
		conDetails.click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		address1.clear();
		address1.sendKeys(AddressS1);
		address2.clear();
		address2.sendKeys(AddressS2);
		city.clear();
		city.sendKeys(City);
		zip.clear();
		zip.sendKeys(ZIP);
		countryField.click();
		
		
		List<WebElement> liCon = driver.findElements(By.xpath("//div[@id='country_inputfileddiv']//div//ul/li/span"));
		//System.out.println(liCon.size());
		try {
			for(WebElement s : liCon) {
				if(s.getText().equalsIgnoreCase(Country)) {
					this.saveCountry = s.getText();
					System.out.println(s.getText());
					//wait.until(ExpectedConditions.refreshed(ExpectedConditions.stalenessOf(s)));
					Actions act = new Actions(driver);
					act.moveToElement(s).click().build().perform();
					break;
				}
			}
		}catch(Exception e) {
			//e.printStackTrace();
			//System.out.println(e.getMessage());
		}
		int m = (int) Mobile;
		hmTele.clear();
		hmTele.sendKeys(String.valueOf(m));
		mobile.clear();
		mobile.sendKeys(String.valueOf(m));
		workTele.clear();
		workTele.sendKeys(String.valueOf(m));
		workEmail.clear();
		workEmail.sendKeys(WorkEmail);
		otherEmail.clear();
		otherEmail.sendKeys(OtherEmail);
		
		
		if(saveCountry.equalsIgnoreCase("United States")) {
			state.click();
			Thread.sleep(3000);
			try {				
				List<WebElement> liState = driver.findElements(By.xpath("//div[@id='province_inputfileddiv']//div//ul/li/span"));
				for(WebElement s : liState) {
					if(s.getText().equalsIgnoreCase(State)) {
						System.out.println(s.getText());
						Actions act = new Actions(driver);
						act.moveToElement(s);
						act.moveToElement(s).click().build().perform();
						break;
					}
					
				}
			}catch(Exception en) {
				en.printStackTrace();
			}
			
		}
		else {
			Thread.sleep(3000);
			System.out.println(State);
			province.clear();
			province.sendKeys(State);
		}
		Thread.sleep(3000);
		save.click();
	}
	
	@FindBy(xpath="//span[@translate='Emergency Contacts']")
	public WebElement emConatct;
	@FindBy(xpath="//a[@ng-click='emergencyContact.addEmergencyContact()']//i[@class='material-icons'][normalize-space()='add']")
	public WebElement add;
	@FindBy(xpath="//input[@ng-model=\"model['name']\"]")
	public WebElement contactName;
	@FindBy(xpath="//input[@ng-model=\"model['relationship']\"]")
	public WebElement relationName;
	@FindBy(xpath="//input[@ng-model=\"model['home_phone']\"]")
	public WebElement homePhone;
	@FindBy(xpath="//input[@ng-model=\"model['mobile_phone']\"]")
	public WebElement mobilePhone;
	@FindBy(xpath="//input[@ng-model=\"model['office_phone']\"]")
	public WebElement workPhone;
	@FindBy(xpath="//button[normalize-space()='Save']")
	public WebElement saveForm;
	
	public void fillEmergencyContacts(String Name, String Relationship, double Mobile) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		myInfoTab.click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOf(menuMod));
		menuMod.click();
		emConatct.click();
		add.click();
		contactName.sendKeys(Name);
		relationName.sendKeys(Relationship);
		int m = (int) Mobile;
		homePhone.sendKeys(String.valueOf(m));
		mobilePhone.sendKeys(String.valueOf(m));
		workPhone.sendKeys(String.valueOf(m));
		saveForm.click();
	}
	
	@FindBy(xpath="//span[@translate='Social Media Details']")
	public WebElement socialMedia;
	@FindBy(xpath="//a[@ng-click='socialMedia.addSocialMedia()']//i[@class='material-icons'][normalize-space()='add']")
	public WebElement addSocial;
	@FindBy(xpath="//button[@role='combobox']")
	public WebElement socialType;
	@FindBy(xpath="//input[@id='profileName']")
	public WebElement handle;
	@FindBy(xpath="//input[@id='profileLink']")
	public WebElement socialLink;
	@FindBy(xpath="//label[@for='allowShare']")
	public WebElement allowShare;
	public String socialList = "//div[@class='dropdown-menu show']//div//ul/li/a/span";
	
	public void fillSocialMediadetails(String SocialType, String Handle, String Link, double Share) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		myInfoTab.click();
		menuMod.click();
		socialMedia.click();
		addSocial.click();
		socialType.click();
		obj.SelectAction(driver, SocialType, socialList);
		handle.sendKeys(Handle);
		socialLink.sendKeys(Link);
		int sh = (int) Share;
		if(sh == 1) {
			allowShare.click();
		}
		saveForm.click();
	}
	
	@FindBy(xpath="//span[@translate='Dependents']")
	public WebElement dependent;
	@FindBy(xpath="//a[@ng-click='dependents.addDependent()']//i[@class='material-icons'][normalize-space()='add']")
	public WebElement addDependent;
	@FindBy(xpath="//input[@id='name']")
	public WebElement nameDependent;
	@FindBy(xpath="//input[@id='date_of_birth']")
	public WebElement dobDependent;
	@FindBy(xpath="//div[@class='filter-option-inner-inner']")
	public WebElement selectRelation;
	@FindBy(xpath="//input[@id='relationship']")
	public WebElement specifyRelation;
	public String relationList = "//div[@class='dropdown-menu show']//div//ul/li/a/span";
	
	public void fillDependents(String Name, String DoB, String Relation, String Others) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		myInfoTab.click();
		menuMod.click();
		dependent.click();
		addDependent.click();
		nameDependent.sendKeys(Name);
		dobDependent.sendKeys(String.valueOf(DoB));
		selectRelation.click();
		obj.SelectAction(driver, Relation, relationList);
		try {
			specifyRelation.sendKeys(Others);
		}catch(Exception relation) {
			
		}
		saveForm.click();
	}
	
	@FindBy(xpath="//span[@translate='Immigration']")
	public WebElement immigration;
	@FindBy(xpath="//a[@ng-click='immigrations.addImmigrations()']//i[@class='material-icons'][normalize-space()='add']")
	public WebElement addImmigration;
	@FindBy(xpath="//label[normalize-space()='Passport']")
	public WebElement passport;
	@FindBy(xpath="//label[normalize-space()='Visa']")
	public WebElement visa;
	@FindBy(xpath="//input[@id='number']")
	public WebElement docNumber;
	@FindBy(xpath="//input[@id='issuedDate']")
	public WebElement issueDate;
	@FindBy(xpath="//input[@id='expiryDate']")
	public WebElement expiryDate;
	@FindBy(xpath="//input[@id='reviewDate']")
	public WebElement reviewDate;
	@FindBy(xpath="//input[@id='status']")
	public WebElement eligibleStatus;
	@FindBy(xpath="//textarea[@id='notes']")
	public WebElement comments;
	@FindBy(xpath="//div[@class='filter-option']")
	public WebElement selectCountry;
	public String issueCon = "(//div[@class='form-group col-6'])[2]//following::div[@class='dropdown-menu show']//div//ul/li/a/span";
	
	public void fillImmigrationDetails(String Document, String DNumber, String IssueDate, 
			String ExpiryDate, String ReviewDate, String Status, String Comments, String IssueCountry) {
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		myInfoTab.click();
		menuMod.click();
		immigration.click();
		addImmigration.click();
		if(Document.equalsIgnoreCase("Passport")) {
			passport.click();
		}else if(Document.equalsIgnoreCase("Visa")) {
			visa.click();
		}
		docNumber.sendKeys(DNumber);
		issueDate.sendKeys(IssueDate);
		expiryDate.sendKeys(ExpiryDate);
		reviewDate.sendKeys(ReviewDate);
		eligibleStatus.sendKeys(Status);
		comments.sendKeys(Comments);
		selectCountry.click();
		obj.SelectAction(driver, IssueCountry, issueCon);
		saveForm.click();
	}
	
	@FindBy(xpath="//span[@translate='Memberships']")
	public WebElement membership;
	@FindBy(xpath="//a[@ng-click='memberships.addMembership()']//i[@class='material-icons'][normalize-space()='add']")
	public WebElement addMembership;
	@FindBy(xpath="//div[@class='modal-body']//following::div[@class='form-group col-12']//button[@data-id='membership']")
	public WebElement selectMembership;
	public String membershipLists = "//div[@class='dropdown-menu show']//div//ul/li/a/span";
	@FindBy(xpath="//div[@class='modal-body']//following::div[@class='form-group col-12']//button[@data-id='subscriptionPaidBy']")
	public WebElement paidBy;
	
	public void fillMembershipForm(String Membership) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		myInfoTab.click();
		menuMod.click();
		membership.click();
		addMembership.click();
		selectMembership.click();
		obj.SelectAction(driver, Membership, membershipLists);
		saveForm.click();
	}
	
	@FindBy(xpath="//span[@translate='Qualifications']")
	public WebElement qualification;
	@FindBy(xpath="//*[@id='addButton']/div/a")
	public WebElement addQualification;
	String addQual = "//*[@id='additem-options-dropdown-qualifications']/li/a";
	@FindBy(xpath="//input[@id='employer']")
	public WebElement employerName;
	@FindBy(xpath="//input[@id='jobtitle']")
	public WebElement jobTitle;
	
	public void fillWorkExperience(String Employer, String Job) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		myInfoTab.click();
		menuMod.click();
		qualification.click();
		addQualification.click();
		obj.SelectAction(driver, "Work Experience", addQual);
		employerName.sendKeys(Employer);
		jobTitle.sendKeys(Job);
		saveForm.click();
	}
	
	@FindBy(xpath="//button[@data-id='educationId']")
	public WebElement educationLevel;
	String eduLevel = "//div[@class='dropdown-menu show']//div//ul/li/a/span";
	@FindBy(xpath="//input[@id='institute']")
	public WebElement institute;
	@FindBy(xpath="//input[@id='major']")
	public WebElement major;
	
	public void fillEducationForm(String Level, String Institute, String Major) throws Exception {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		myInfoTab.click();
		menuMod.click();
		qualification.click();
		addQualification.click();
		obj.SelectAction(driver, "Education", addQual);
		educationLevel.click();
		obj.SelectAction(driver, Level, eduLevel);
		institute.sendKeys(Institute);
		major.sendKeys(Major);
		Thread.sleep(3000);
		saveForm.click();
	}
	
	@FindBy(xpath="//button[@data-id='skillId']")
	public WebElement skillList;
	String skill = "//*[@id=\"bs-select-1\"]/ul/li";
	public void fillSkillsForm(String Skill) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		myInfoTab.click();
		menuMod.click();
		qualification.click();
		addQualification.click();
		obj.SelectAction(driver, "Skill", addQual);
		skillList.click();
		obj.SelectAction(driver, Skill, skill);
		saveForm.click();
	}
	
	@FindBy(xpath="//button[@data-id='langId']")
	public WebElement language;
	String lang = "//div[@class='dropdown-menu show']//div//ul/li/a/span";
	@FindBy(xpath="//button[@data-id='fluency']")
	public WebElement fluency;
	String fluent = "//div[@class='dropdown-menu show']//div//ul/li/a/span";
	@FindBy(xpath="//button[@data-id='competency']")
	public WebElement competency;
	String compete = "//div[@class='dropdown-menu show']//div//ul/li/a/span";
	public void fillLanguageForm(String Language, String Fluency, String Competency) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		myInfoTab.click();
		menuMod.click();
		qualification.click();
		addQualification.click();
		obj.SelectAction(driver, "Language", addQual);
		language.click();
		obj.SelectAction(driver, Language, lang);
		fluency.click();
		obj.SelectAction(driver, Fluency, fluent);
		competency.click();
		obj.SelectAction(driver, Competency, compete);
		saveForm.click();
	}
}

