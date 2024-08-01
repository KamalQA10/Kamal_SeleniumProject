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
import com.mystore.pageobjects.SearchResultPage;

public class SearchResultPageTest extends BaseClass {
	
	IndexPage indexPage;
	LoginPage loginPage;
	HomePage homePage;
	SearchResultPage searchResultPage;
	
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
	public void productAvailabilityTest() throws Throwable {
		indexPage = new IndexPage();
		loginPage = indexPage.clickOnsignIn();
		homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		searchResultPage = indexPage.searchProduct(prop.getProperty("searchResult"));
		boolean flag = searchResultPage.isProductAvailable();
		Assert.assertTrue(flag);
		Assert.assertTrue( 0 < searchResultPage.CountTotalSearchProducts());
		
	}
	

}
