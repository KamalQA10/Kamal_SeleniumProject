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

public class HomePageTest extends BaseClass{
	
	LoginPage loginPage;
	IndexPage indexPage;
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
	
	@Test(groups="Smoke")
	public void VerifyOrderHistoryAndDetails() throws InterruptedException {

		indexPage = new IndexPage();
		loginPage = indexPage.clickOnsignIn();
		homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));

	    Assert.assertEquals(homePage.validateOrderHistory(), true);		
	}

}
