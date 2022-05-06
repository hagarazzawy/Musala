package base;

import java.awt.AWTException;
import java.awt.image.BufferedImage;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;


public class PageBase {
	public static Logger logger = Logger.getLogger(PageBase.class);
	WebDriverWait wait;


	public Boolean ElementIsDisplayed(WebDriver driver, By element_locator, String element_log) {
		try {
			driver.findElement(element_locator).isDisplayed();
			logger.info(element_log + " is Displayed successfully");
			return true;

		} catch (Exception e) {
			logger.error(element_log + " is not Displayed");
			return false;
		}
	}

	public void ClickElement(WebDriver driver, By element_locator, String element_log) {
		logger = Logger.getLogger("Click Element ");
		Boolean flag = ElementIsDisplayed(driver, element_locator, element_log);
		if (flag == true) {
			WebElement element = driver.findElement(element_locator);
			element.click();
			logger.info("Click on " + element_log);
		} else {
			logger.error("Cannot Click on " + element_log);
		}
	}
	public void SetElement_JS(WebDriver driver,By element_locator , String value, String element_log)
	{
		logger = Logger.getLogger("Set elemnet text");
		try
		{
			WebElement userNameTxt = driver.findElement( element_locator); 
			JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;  
			jsExecutor.executeScript("arguments[0].value='"+value+"'", userNameTxt);
		} catch (Exception e) {
			logger.error("Can't Set Element :" + element_log);
		}
	}
	public void ClickElement_JS(WebDriver driver, By element_locator, String element_log) {
		logger = Logger.getLogger("Click Element");
		Boolean flag = ElementIsDisplayed(driver, element_locator, element_log);
		if (flag == true) {
			WebElement element = driver.findElement(element_locator);
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", element);
			logger.info("click on " + element_log);
		} else {
			logger.error("Can't Click on " + element_log);
		}
	}
	public void SetElement(WebDriver driver, By element_locator, String value, String element_log) {
		logger = Logger.getLogger("Set Element Text");

		Boolean Flag_element = ElementIsDisplayed(driver, element_locator, element_log);
		try {
			if (Flag_element == true) {
				WebElement element = driver.findElement(element_locator);
				element.clear();
				element.sendKeys(value);
				logger.info("Set " + element_log + " : " + value);
			} else {
				logger.error("Can't Set Element :" + element_log);
			}
		} catch (Exception e) {
			logger.error("Can't Set Element :" + element_log);
		}

	}
	public void SelectItemFromList(WebDriver driver, By element_locator, String value, String element_log) {
		logger = Logger.getLogger("Select Item from List");
		ClickElement_JS(driver, element_locator, element_log);
		try {
			new Select(driver.findElement(element_locator)).selectByVisibleText(value);
			logger.info("Select " + element_log + " :  " + value);
		} catch (Exception e) {
			logger.error(" Cannot find element in list with text  : " + value);

		}
	}
	public void ScrollDownToTheEndOfPage(WebDriver driver) throws AWTException, InterruptedException  {
		logger = Logger.getLogger("Scroll down to the end of page");
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
			logger.info("Scroll down to the end of page");


		} catch (Exception e) {
			logger.error(" Cannot scroll down");

		}
	}
	public String SwitchtoFrame(WebDriver driver ,By element_locator, String element_log)
	{
		logger = Logger.getLogger("Switch to frame ");
		String parentWindow="";
		Boolean Flag_element = ElementIsDisplayed(driver, element_locator, element_log);
		if (Flag_element == true) {
			WebElement iFrame = driver.findElement(  element_locator);
			driver.switchTo().frame(iFrame);
			parentWindow = driver.getWindowHandle();
			logger.info("Switch to frame");

		}else
		{
			logger.error("Cannot switch to " + element_log);
		}
		return parentWindow;

	}
	public void SwitchtoWindow(WebDriver driver , String windowName,String element_log)
	{
		logger = Logger.getLogger("Switch to window ");
		try {
			driver.switchTo().window(windowName);
			logger.info("Switch to window "+element_log);

		}catch(Exception e)
		{
			logger.error("Cannot switch to " + element_log);
		}

	}
	public String GetTextFromElement(WebDriver driver ,By element_locator, String element_log)
	{
		logger = Logger.getLogger("Get Text From Element ");
		String text="";
		Boolean Flag_element = ElementIsDisplayed(driver, element_locator, element_log);
		if (Flag_element == true) {
			WebElement element = driver.findElement(  element_locator);
			text= element.getText();
			logger.info("Get Text From  "+element_log);

		}else
		{
			logger.error("Cannot get text from element  " + element_log);
		}
		return text;
	}
	public String GetCurrentURL(WebDriver driver)
	{
		logger = Logger.getLogger("Get Currenr URL");
		String url="";
		try {
			url= driver.getCurrentUrl();
		}catch(Exception e)
		{
			logger.error("Cannot get current URL " );

		}
		return url;
	}
	public BufferedImage getScreenShot (WebDriver driver ,By element_locator , String element_log) {
		wait= new WebDriverWait(driver, 30);
		logger = Logger.getLogger("Get screenshot ");
		try {
			WebElement element = driver.findElement(  element_locator);
			wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(element_locator));

			Screenshot logoImageScreenshot = new AShot().takeScreenshot(driver, element);
			logger.info("Get screenshot of  "+element_log);
			return logoImageScreenshot.getImage();

		}
		catch(Exception e) {
			System.out.print(e.toString());
			logger.error("Cannot take a screenshot of " + element_log);

		}
		return null;

	}
	public void OpenImageSourceInNewTab(WebDriver driver ,By element_locator) throws AWTException, InterruptedException
	{
		WebElement element = driver.findElement(  element_locator);
		String src = element.getAttribute("src");
		driver.get(src);


		}
	

}
