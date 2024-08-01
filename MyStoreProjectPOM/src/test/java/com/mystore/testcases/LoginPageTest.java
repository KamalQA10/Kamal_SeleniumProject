package com.mystore.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.mystore.base.BaseClass;
import com.mystore.pageobjects.HomePage;
import com.mystore.pageobjects.IndexPage;
import com.mystore.pageobjects.LoginPage;
import com.mystore.dataprovider.DataProviders;
import com.mystore.utility.Log;

public class LoginPageTest extends BaseClass{

	IndexPage indexPage;
	LoginPage loginPage;
	HomePage homePage;
    
	@Parameters("browser")
	@BeforeMethod(groups = {"Smoke","Sanity"})
	public void setup(String browser) throws InterruptedException {
		launchApp(browser); 
	}
	
	@AfterMethod(groups = {"Smoke","Sanity"})
	public void tearDown() {
		getDriver().quit();
	}
	
	/* Method For Read Data from properties File */	
			 /*
			 	@Test
				public void verifyLogIn() throws InterruptedException { 
							Log.startTestCase("loginTest");
					indexPage = new IndexPage();
							Log.info("user is going to click on SignIn");
					loginPage = indexPage.clickOnsignIn();
							Log.info("Enter Username and Password");
					homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
					
					String homePageURL = homePage.getCurrURL();
					String expectedURL="http://www.automationpractice.pl/index.php?controller=my-account";
					String Text = homePage.getMyAccountText();
							Log.info("Verifying if user is able to login");
				    Assert.assertEquals(homePageURL, expectedURL);
				    Log.info("Login is Sucess");
				    Assert.assertEquals(Text, "MY ACCOUNT");
				    		Log.endTestCase("loginTest");		    		 
				}
			  */
	
	@Test(groups = {"Smoke","Sanity"},dataProvider = "credentials", dataProviderClass = DataProviders.class)
	public void loginTest(String uname, String pswd) throws Throwable {
				
				Log.startTestCase("loginTest");
		indexPage = new IndexPage();
				Log.info("user is going to click on SignIn");
		loginPage = indexPage.clickOnsignIn();
				Log.info("Enter Username and Password");
		homePage = loginPage.login(uname,pswd);
		String homePageURL = homePage.getCurrURL();
		String expectedURL="http://www.automationpractice.pl/index.php?controller=my-account";
		String Text = homePage.getMyAccountText();
				Log.info("Verifying if user is able to login");
	    Assert.assertEquals(homePageURL, expectedURL);
	    		Log.info("Login is Sucess");
	    Assert.assertEquals(Text, "MY ACCOUNT");
	    		Log.endTestCase("loginTest");
	}

}