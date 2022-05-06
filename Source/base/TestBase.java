package base;


import java.awt.image.BufferedImage;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;
import testData.ConfigFileReader;

public class TestBase {

	public static WebDriver driver;
	public static Logger logger = Logger.getLogger(TestBase.class);
	ConfigFileReader configFileReader= new ConfigFileReader();

	public void SetupBrowser() {		
		logger = Logger.getLogger("Open browser ");
		try {	
			String browser = configFileReader.getBrowser();
			if (browser.equals("chrome"))
			{
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver();
			}
			if (browser.equals("firefox"))
			{
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
			}
		}
		catch (Exception ex) {
			logger.error("Open browser " + ex.getMessage());
		}
	}
	public void OpenWebsite() {
		logger = Logger.getLogger("Open URL ");
		String URL = configFileReader.getApplicationUrl();
		logger.info("Opening URL ...");
		logger.info("Open URL = " + URL);
		driver.get(URL);
		driver.manage().window().maximize();
	}

	public static void CloseBrowser() {

		logger = Logger.getLogger("Close browser ");
		try {
			driver.quit();
			logger.info("Close Browser ...");
		} catch (Exception ex) {
			logger.error("Close browser " + ex.getMessage());

		}
	}
		public boolean AreImagesTheSame(BufferedImage actualImage,BufferedImage expectedImage) {
			ImageDiffer imgDiff = new ImageDiffer();
			ImageDiff diff = imgDiff.makeDiff(actualImage, expectedImage);
			if(diff.hasDiff())
				return false;
			else 
				return true;
		}


		


	}

