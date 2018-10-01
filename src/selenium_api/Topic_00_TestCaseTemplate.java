package selenium_api;

import org.testng.annotations.Test;


import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.server.handler.ImplicitlyWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_00_TestCaseTemplate {
	WebDriver driver;
	
	@BeforeClass
	public void beforeClass() {
		// Start Browser
		driver = new FirefoxDriver();
		
	}
	
	@Test
	public void TC_01() {
		// Navigate to page
		driver.get("");
	
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
