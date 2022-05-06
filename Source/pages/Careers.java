package pages;

import java.awt.AWTException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.PageBase;

public class Careers extends PageBase {
	
	static WebDriver driver;
	WebDriverWait wait;
	
	By checkOpenPositionsButton = By.xpath("//span[text()='Check our open positions']/..");

	public Careers(WebDriver driver) {
		Careers.driver = driver;
		wait = new WebDriverWait(driver, 30);
	}
	public void ClickOnOpenPositionsButton() throws AWTException, InterruptedException
	{
		wait.until(ExpectedConditions.elementToBeClickable(checkOpenPositionsButton));
		ClickElement(driver, checkOpenPositionsButton, " check Open Positions Button");
		
	}
	public String getURL()
	{
		return GetCurrentURL(driver);
	}
	public String getPageTitle()
	{
		return driver.getTitle();
	}
}
