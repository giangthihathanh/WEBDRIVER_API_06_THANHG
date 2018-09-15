package selenium_api;

import org.testng.annotations.Test;


import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.server.handler.ImplicitlyWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_01_CheckEnvironment {
	WebDriver driver;
	
	@BeforeClass
  public void beforeClass() {
	// Khởi tạo data/ pre-condition
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		////
	}

@Test
  public void TC_01_CheckBrowser() {
	  // Function for Test Case
	driver.get("http://live.guru99.com/");
	String homePageTitle = driver.getTitle();
	Assert.assertEquals(homePageTitle, "Home page");
  }


  @AfterClass
  public void afterClass() {
	  // Clear data
	  driver.quit();
  }

}
