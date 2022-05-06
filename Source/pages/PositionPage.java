package pages;

import java.awt.AWTException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.PageBase;

public class PositionPage extends PageBase {
	
	static WebDriver driver;
	WebDriverWait wait;
	
	By responsibilities = By.xpath("//h2[text()='Responsibilities']");	
	By whatWeOffer = By.xpath("//h2[text()='What we offer']");	
	By generalDescription = By.xpath("//h2[text()='General description']");	
	By requirements = By.xpath("//h2[text()='Requirements']");	
	By applyButton = By.xpath("//input[@value='Apply']");	

	
	public PositionPage(WebDriver driver) {
		PositionPage.driver = driver;
		wait = new WebDriverWait(driver, 30);
	}
	public Boolean AllSectionsDisplayed()
	{
		Boolean result=false;
		wait.until(ExpectedConditions.visibilityOfElementLocated(responsibilities));
		if( ElementIsDisplayed(driver, responsibilities, "responsibilities Section") && ElementIsDisplayed(driver, generalDescription, "general Description section ")
			&&	ElementIsDisplayed(driver, requirements, "requirements Section") && ElementIsDisplayed(driver, whatWeOffer, "what We Offer section"))
			result= true;
		else 
			result=false;
		return result;
	}
	public Boolean ApplyButtonDisplayed()
	{
		Boolean result=false;
		wait.until(ExpectedConditions.visibilityOfElementLocated(applyButton));
		if( ElementIsDisplayed(driver, applyButton, "apply Button "))	
				result= true;
		else 
			result=false;
		return result;
	}
	public void ClickOnApplyButton() throws AWTException, InterruptedException
	{
		wait.until(ExpectedConditions.elementToBeClickable(applyButton));
		ClickElement_JS(driver, applyButton, "apply Button Button");
		
	}

}
