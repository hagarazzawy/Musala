package pages;

import java.awt.AWTException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.PageBase;

public class HomePage extends PageBase {
	
	static WebDriver driver;
	WebDriverWait wait;
	
	By contactUs = By.xpath("//span[text()='Contact us']/..");
	By company = By.xpath("//div[@id='menu']//a[text()='Company']");
	By careers = By.xpath("//div[@id='menu']//a[text()='Careers']");

	public HomePage(WebDriver driver) {
		HomePage.driver = driver;
		wait = new WebDriverWait(driver, 30);
	}
	public void OpenContactUs() throws AWTException, InterruptedException
	{
		ScrollDownToTheEndOfPage(driver);
		wait.until(ExpectedConditions.elementToBeClickable(contactUs));
		ClickElement_JS(driver, contactUs, "Contact Us");
		
	}
	public void OpenCompany() throws AWTException, InterruptedException
	{
		wait.until(ExpectedConditions.elementToBeClickable(company));
		ClickElement_JS(driver, company, "company");
		
	}
	public void OpenCareers() throws AWTException, InterruptedException
	{
		wait.until(ExpectedConditions.elementToBeClickable(careers));
		ClickElement_JS(driver, careers, "Careers");
		
	}
}
