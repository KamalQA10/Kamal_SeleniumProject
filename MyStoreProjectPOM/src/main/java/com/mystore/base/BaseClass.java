package com.mystore.base;
/*Kamal*/
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import com.mystore.actiondriver.Action;
import com.mystore.utility.ExtentManager;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	
	public static Properties prop;
	//public static WebDriver driver;
	
	/*
	 * Changed Code for parallel execution(Declear thread loacal driver)
	*/
	public static ThreadLocal<RemoteWebDriver> driver = new ThreadLocal<>();
	
	public static WebDriver getDriver() {
		/* Get Driver From threadLocalmap */
		return driver.get();
	}
	
	@BeforeSuite(groups = {"Smoke","Sanity"})
	public void loadConfig() {
		
		ExtentManager.setExtent();
		DOMConfigurator.configure("log4j.xml");
			
		try {
			FileInputStream configFileReader = new FileInputStream(System.getProperty("user.dir") + "\\Configuration\\config.properties");
			prop = new Properties();
			prop.load(configFileReader);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/*
	@BeforeTest(groups = {"Smoke","Sanity"})
	public static void loadConfig() throws IOException {
		String configFilePath = "C:/Users/kamal.bisht/eclipse-workspace/MyStoreProjectPOM/Configuration/Config.properties";		
		
		try {
			FileInputStream configFileReader = new FileInputStream(configFilePath);
			prop = new Properties();
			prop.load(configFileReader);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	*/
	
	
	public static void launchApp(String browserName) throws InterruptedException {

		//String browserName = prop.getProperty("browser");
		
		if (browserName.equalsIgnoreCase("Chrome"))
		{
			WebDriverManager.chromedriver().setup();
			//driver = new ChromeDriver();
			/*
			 * Changed Code for parallel execution(Declear thread loacal driver)
			*/
			driver.set(new ChromeDriver());
		}
		else if (browserName.equalsIgnoreCase("FireFox"))
		{
			WebDriverManager.firefoxdriver().setup();
			//driver = new FirefoxDriver();
			/*Changed Code for parallel execution(Declear thread loacal driver)*/
			driver.set(new FirefoxDriver());
		}else if (browserName.equalsIgnoreCase("IE"))
		{
			WebDriverManager.iedriver().setup();
			//driver = new InternetExplorerDriver();
			/*
			 * Changed Code for parallel execution(Declear thread loacal driver)
			*/
			driver.set(new InternetExplorerDriver());
		}
		getDriver().manage().window().maximize();
		Action.implicitWait(getDriver(),10);
		//Action.pageLoadTimeOut(getDriver(),30);
		getDriver().get(prop.getProperty("url"));
	}
	
	@AfterSuite(groups = { "Smoke","Sanity" })
	public void afterSuite() {
		ExtentManager.endReport();
	}
}