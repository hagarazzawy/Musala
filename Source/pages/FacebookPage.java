package pages;

import java.awt.AWTException;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;


import base.PageBase;

public class FacebookPage extends PageBase {

	static WebDriver driver;
	WebDriverWait wait;

	By contactUs = By.xpath("//span[text()='Contact us']/..");
	By profilePictureLink = By.xpath("//a[@aria-label='Musala Soft profile photo']");
	By profilePicture = By.xpath("//img[@data-visualcompletion='media-vc-image']");
	By profilePictureInNewTab = By.xpath("//img");


	public FacebookPage(WebDriver driver) {
		FacebookPage.driver = driver;
		wait = new WebDriverWait(driver, 30);
	}
	public String getURL()
	{
		return GetCurrentURL(driver);
	}
	public Boolean IsProfilePicturLinkePresent()
	{
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(profilePictureLink));
		return ElementIsDisplayed(driver, profilePictureLink, "profile Picture");
	}
	public void SwitchToCompanyWindow(String window)
	{
		SwitchtoWindow(driver, window, "Company page ");

	}

	public void OpenProfilePictureLink() throws AWTException, InterruptedException
	{
		wait.until(ExpectedConditions.elementToBeClickable(profilePictureLink));
		ClickElement_JS(driver, profilePictureLink, "profile Picture Link");

	}
	public void OpenProfilePictureInNewTab() throws AWTException, InterruptedException {
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(profilePicture));
		OpenImageSourceInNewTab(driver ,profilePicture);
	}
	public BufferedImage getScreenShotOfProfilePictue() throws IOException, AWTException, InterruptedException
	{
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(profilePictureInNewTab));
		WebElement profilePictureScreenshot = driver.findElement(profilePictureInNewTab);
		File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		BufferedImage fullScreen = ImageIO.read(screenshot);
		Point location = profilePictureScreenshot.getLocation();
		int width = profilePictureScreenshot.getSize().getWidth();
		int height = profilePictureScreenshot.getSize().getHeight();
		 BufferedImage logoImage= fullScreen.getSubimage(location.getX(), location.getY(),width, height);
		  ImageIO.write(logoImage, "jpg", screenshot);
	        FileUtils.copyFile(screenshot, new File(System.getProperty("user.dir") +"\\Source\\files\\MusalaProfilePicture11.jpg"));
	        return logoImage;



	}
}
