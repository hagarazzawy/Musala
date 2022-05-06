package pages;

import java.awt.AWTException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.github.javafaker.Faker;

import base.PageBase;

public class ApplicationForm extends PageBase {

	static WebDriver driver;
	WebDriverWait wait;
	Faker faker;

	By nameTextbox = By.xpath("//input[@name='your-name']");
	By emailTextbox = By.xpath("//input[@name='your-email']");	
	By mobileTextbox = By.xpath("//input[@name='mobile-number']");	
	By uploadTextbox = By.xpath("//input[@name='uploadtextfield']");	
	By linkedinTextbox = By.xpath("//input[@name='linkedin']");	
	By messageTextbox = By.xpath("//textarea[@name='your-message']");
	By agreeCheckbox = By.xpath("//*[@id='adConsentChx']");	
	By fieldErrorMessage= By.xpath("//p/span/span");
	By subjectTextbox = By.xpath("//input[@name='your-subject']");
	By reCaptchaIframe = By.xpath("//iframe[@title='reCAPTCHA']");
	By reCaptchaCheckbox = By.xpath("//div[@class='recaptcha-checkbox-border']");
	By sendButton = By.xpath("//input[@value='Send']");
	By outputMessage= By.xpath("//div[contains(@class,'response-output')]");
	By closeButton = By.xpath("//button[text()='Close']");



	public ApplicationForm(WebDriver driver) {
		ApplicationForm.driver = driver;
		wait = new WebDriverWait(driver, 30);
		faker = new Faker();
	}
	public void AcceptReCaptcha() throws InterruptedException
	{
		String 	parentWindow=SwitchtoFrame(driver,reCaptchaIframe,"Frame");
		wait.until(ExpectedConditions.elementToBeClickable(reCaptchaCheckbox));
		ClickElement_JS(driver, reCaptchaCheckbox, "reCaptcha Checkbox");
//				Thread.sleep(12000);
		SwitchtoWindow(driver, parentWindow, "Window");

	}
	public void SendContactUsForm(String name , String email,String subject , String message) throws AWTException, InterruptedException
	{
		SetElement(driver, nameTextbox, name, "name");
		//		String email = faker.internet().emailAddress();;
		SetElement(driver, emailTextbox, email, "Email");
		SetElement(driver, subjectTextbox, subject, "Subject");
		SetElement(driver, messageTextbox, message, "Message");
		AcceptReCaptcha();
		ClickElement(driver, sendButton, "Send button");

	}
	public String GetOutputMessage()
	{
		wait.until(ExpectedConditions.visibilityOfElementLocated(outputMessage));
		return GetTextFromElement(driver, outputMessage, "output Message");
	}
	public String GetErrorMessageUnderField()
	{
		wait.until(ExpectedConditions.visibilityOfElementLocated(fieldErrorMessage));
		return GetTextFromElement(driver, fieldErrorMessage, "email error Message");
	}
	public void SendApplicationForm(String name , String email,String mobile , String path,String linkedinURL, String message) throws AWTException, InterruptedException
	{
		SetElement(driver, nameTextbox, name, "name");
		SetElement(driver, emailTextbox, email, "Email");
		SetElement(driver, mobileTextbox, mobile, "Mobile");
		SetElement(driver, uploadTextbox, path, "cv");
		SetElement(driver, linkedinTextbox, linkedinURL, "linkedin URL");
		SetElement(driver, messageTextbox, message, "message");
		ClickElement(driver, agreeCheckbox, "agree Checkbox");
		AcceptReCaptcha();
		ClickElement(driver, sendButton, "Send button");
		ClickElement_JS(driver, closeButton, "Close button");

		}
	}


