package testCases;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.awt.AWTException;
import java.io.IOException;
import java.util.ArrayList;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.TestBase;
import io.qameta.allure.Description;
import pages.ApplicationForm;
import pages.Careers;
import pages.Company;
import pages.FacebookPage;
import pages.HomePage;
import pages.JoinUs;
import pages.PositionPage;
import testData.ConfigFileReader;
import testData.TestData;

public class TestCases extends TestBase {
	HomePage homePage;
	Company companyPage;
	FacebookPage facebookPage;
	Careers careersPage;
	JoinUs joinUsPage;
	PositionPage positionPage;
	ApplicationForm applicationFormPage;
	ConfigFileReader configFileReader;


	@BeforeClass
	public void beforeMethod() throws InterruptedException {

		SetupBrowser();
		configFileReader= new ConfigFileReader();
		homePage = new HomePage(driver);
		companyPage = new Company(driver);
		facebookPage = new FacebookPage(driver);
		careersPage = new Careers(driver);
		joinUsPage = new JoinUs(driver);
		positionPage = new PositionPage(driver);
		applicationFormPage = new ApplicationForm(driver);
	}

	@Description("Submit contact us form using invalid emails ")
	@Test(priority = 1 , dataProvider = "TestCase_1_dataProvider" )
	public void TestCase_1(String name,String email,String subject,String message , String emailErrorMessage ) throws InterruptedException, AWTException  {

		logger.info("============== TestCase 1 -  Start ==============");
		OpenWebsite();
		homePage.OpenContactUs();
		applicationFormPage.SendContactUsForm(name, email,subject, message);
		assertEquals(applicationFormPage.GetErrorMessageUnderField(), emailErrorMessage);

		logger.info("============== TestCase 1 -  End ==============");
	}

	@DataProvider
	public Object[][] TestCase_1_dataProvider() throws Exception {

		Object[][] testObjArray = TestData.fetchData("TestCase_1","TestCase_1");

		return (testObjArray);
	}
	@Description("Access Company page and open Musala facebook page ")
	@Test(priority = 2 , dataProvider = "TestCase_2_dataProvider" )
	public void TestCase_2(String companyURL, String facebookURL ) throws InterruptedException, AWTException, IOException  {

		logger.info("============== TestCase 2 -  Start ==============");
		OpenWebsite();
		homePage.OpenCompany();
		assertTrue(companyPage.IsLeadershipSectionShown() );
		assertEquals(companyPage.getURL(),companyURL);
		companyPage.OpenFacebookLink();
		String companyPageWindowHandler= companyPage.SwitchToFacebookWindow();
		assertEquals(facebookPage.getURL(),facebookURL);
		assertTrue(facebookPage.IsProfilePicturLinkePresent());
		driver.close();
		facebookPage.SwitchToCompanyWindow(companyPageWindowHandler);

		logger.info("============== TestCase 2 -  End ==============");
	}

	@DataProvider
	public Object[][] TestCase_2_dataProvider() throws Exception {

		Object[][] testObjArray = TestData.fetchData("TestCase_2","TestCase_2");

		return (testObjArray);
	}
	@Description("Access Careers page and apply for a position using invalid data ")
	@Test(priority = 3 , dataProvider = "TestCase_3_dataProvider" )
	public void TestCase_3(String pageTitle, String pageURL , String location , String position,String name , String email,String mobile , String linkedinURL, String message ,String errorMessage) throws InterruptedException, AWTException  {

		logger.info("============== TestCase 3 -  Start ==============");
		OpenWebsite();
		homePage.OpenCareers();
		careersPage.ClickOnOpenPositionsButton();
		assertEquals(careersPage.getURL(),pageURL);
		assertTrue(careersPage.getPageTitle().contains(pageTitle));
		joinUsPage.selectLocation(location);
		joinUsPage.OpenPosition(position);
		assertTrue(positionPage.AllSectionsDisplayed());
		assertTrue(positionPage.ApplyButtonDisplayed());
		positionPage.ClickOnApplyButton();
		String path=System.getProperty("user.dir") + configFileReader.getCVPath();
		applicationFormPage.SendApplicationForm( name ,  email, mobile , path, linkedinURL,  message);
		assertEquals(applicationFormPage.GetErrorMessageUnderField(), errorMessage);


		logger.info("============== TestCase 3 -  End ==============");
	}

	@DataProvider
	public Object[][] TestCase_3_dataProvider() throws Exception {

		Object[][] testObjArray = TestData.fetchData("TestCase_3","TestCase_3");

		return (testObjArray);
	}

	@Description("Access Careers page and view posistions in Sofia and Skopje ")
	@Test(priority = 4 , dataProvider = "TestCase_4_dataProvider" )
	public void TestCase_4(String pageTitle, String pageURL , String location  ) throws InterruptedException, AWTException  {

		logger.info("============== TestCase 4 -  Start ==============");
		OpenWebsite();
		homePage.OpenCareers();
		careersPage.ClickOnOpenPositionsButton();
		assertEquals(careersPage.getURL(),pageURL);
		assertTrue(careersPage.getPageTitle().contains(pageTitle));
		joinUsPage.selectLocation(location);
		System.out.println(location);
		ArrayList<String> Postions = joinUsPage.GetAllPositionsNames();
		ArrayList<String> PostionsLinks = joinUsPage.GetAllPositionsLinks();
		if(Postions.size()== PostionsLinks.size())
		{
			for (int i=0;i<Postions.size();i++) {
				System.out.println("Position: "+Postions.get(i));
				System.out.println("More info: "+PostionsLinks.get(i));

			}

		}	

		logger.info("============== TestCase 4 -  End ==============");
	}

	@DataProvider
	public Object[][] TestCase_4_dataProvider() throws Exception {

		Object[][] testObjArray = TestData.fetchData("TestCase_4","TestCase_4");

		return (testObjArray);
	}
	@AfterTest
	public void AfterMethod()
	{
		CloseBrowser();
	}


}
