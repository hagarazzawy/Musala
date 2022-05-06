package pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.PageBase;

public class JoinUs extends PageBase {
	
	static WebDriver driver;
	WebDriverWait wait;
	
	By locationDropdownList = By.xpath("//select[@id='get_location']");
	By positionTitles=By.xpath("//a[contains(@class,'card-jobsHot')]//h2");
	By positionLinks=By.xpath("//a[@class='card-jobsHot__link']");



	public JoinUs(WebDriver driver) {
		JoinUs.driver = driver;
		wait = new WebDriverWait(driver, 30);
	}
	public void selectLocation(String value)
	{
		SelectItemFromList(driver, locationDropdownList, value, "Location dropdown list");
	}
	public void OpenPosition(String positionName)
	{
		wait.until(ExpectedConditions.elementToBeClickable(positionTitles));
		List<WebElement> positionsList=driver.findElements(positionTitles);
		for (WebElement position : positionsList) {
			if (position.getText().contains(positionName)) {
				JavascriptExecutor executor = (JavascriptExecutor) driver;
				executor.executeScript("arguments[0].click();", position);				
				break;
			}
			
		}
	}
		public ArrayList<String> GetAllPositionsLinks()
		{
			ArrayList<String> positionsLinksList = new ArrayList<String>() ;
			wait.until(ExpectedConditions.visibilityOfElementLocated(positionLinks));
			List<WebElement> positionsLinks=driver.findElements(positionLinks);
			for (WebElement positionLink : positionsLinks) {
				positionsLinksList.add(positionLink.getAttribute("href"));
			}
				return positionsLinksList;
		}
		public ArrayList<String> GetAllPositionsNames()
		{
			ArrayList<String> positionsNamesList = new ArrayList<String>() ;
			wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(positionTitles));
			List<WebElement> positionsList=driver.findElements(positionTitles);
			for (WebElement position : positionsList) {
				positionsNamesList.add(position.getText());
			}
				return positionsNamesList;
		}
	}

