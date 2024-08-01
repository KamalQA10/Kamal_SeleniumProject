package com.mystore.testcases;

import org.testng.annotations.*;

import com.mystore.base.BaseClass;
import com.mystore.pageobjects.IndexPage;

import org.testng.Assert;

public class IndexPageTest extends BaseClass {
	
	private IndexPage indexPage;
    
	@Parameters("browser")
	@BeforeMethod(groups = {"Smoke","Sanity"})
	public void setup(String browser) throws InterruptedException {
		launchApp(browser); 
	}
	
	@AfterMethod (groups = {"Smoke","Sanity"})
	public void tearDown() {
		getDriver().quit();
	}
	
	@Test (groups="Smoke")
	public void verifyLogo() throws Throwable {
		indexPage= new IndexPage();
		boolean result = indexPage.validateLogo();
		Assert.assertTrue(result);
	}
	
	@Test (groups="Smoke")
	public void verifyTitle() {
		String actTitle = indexPage.getMyStoreTitle();
		Assert.assertEquals(actTitle, "My Shop");
	}
	
}
