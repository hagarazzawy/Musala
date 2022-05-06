package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.PageBase;

public class Company extends PageBase {
	
	static WebDriver driver;
	WebDriverWait wait;
	
	By leadership = By.xpath("//h2[text()='Leadership']");	
	By facebookLink = By.xpath("//a[contains(@href,'facebook')]");

	
	public Company(WebDriver driver) {
		Company.driver = driver;
		wait = new WebDriverWait(driver, 30);
	}
	public Boolean IsLeadershipSectionShown()
	{
		wait.until(ExpectedConditions.visibilityOfElementLocated(leadership));
		return ElementIsDisplayed(driver, leadership, "leadership");
	}
	public void OpenFacebookLink() 
	{
		wait.until(ExpectedConditions.elementToBeClickable(facebookLink));
		ClickElement_JS(driver, facebookLink, "company");
	}
	public String SwitchToFacebookWindow() {
		String winHandler = driver.getWindowHandle();
		for(String winHandle : driver.getWindowHandles()){
			SwitchtoWindow(driver, winHandle, "Musala facebook page ");
		}
		return winHandler;
		
	}
	public String getURL()
	{
		return GetCurrentURL(driver);
	}

}
