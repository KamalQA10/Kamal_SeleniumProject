package com.mystore.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.mystore.base.BaseClass;
import com.mystore.pageobjects.AccountCreationPage;
import com.mystore.pageobjects.IndexPage;
import com.mystore.pageobjects.LoginPage;

public class AccountCreationPageTest extends BaseClass{

	IndexPage indexPage;
	LoginPage loginPage;
	AccountCreationPage accountCreationPage;
	
	@Parameters("browser")
	@BeforeMethod(groups = {"Smoke","Sanity"})
	public void setup(String browser) throws InterruptedException {
		launchApp(browser); 
	}
	
	@AfterMethod(groups = {"Smoke","Sanity"})
	public void tearDown() {
		getDriver().quit();
	}
	
	@Test (groups = "Sanity")
	public void verifyCreateAccount() throws Throwable {
		indexPage = new IndexPage();
		loginPage = indexPage.clickOnsignIn();
		accountCreationPage = loginPage.createNewAccount("Praveen@abc.com");
		loginPage.ClickOnCreateNewAccountBtn();
		Assert.assertEquals(accountCreationPage.validateAcountCreatePage(), true);
	}
	
}
