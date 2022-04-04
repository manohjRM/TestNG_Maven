package mainClassPkg;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ReUseClass extends MainClass{
	public void Select(WebElement element, String value) {
		Select select = new Select(element);
		select.selectByVisibleText(value);
	}
	public void WaitSelectable(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public void SelectAction(WebDriver driver, String check, String Xpath) {
		List<WebElement> li = driver.findElements(By.xpath(Xpath));
		//System.out.println(li.size());
		for(WebElement typ : li) {
			//System.out.println(typ.getText());
			if(check.equalsIgnoreCase(typ.getText())) {
				//System.out.println(typ.getText());
				Actions act = new Actions(driver);
				act.moveToElement(typ).click().build().perform();
				break;
			}
		}
	}
	
	public void jsExecutor(WebDriver driver, String script) {
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript(script);
	}
}
