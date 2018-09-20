package selenium_api;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_02_XPATH_CSS {
	WebDriver driver;
	
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		
	}
	
	@Test
	public void TC_01() {
		//<input id="email" 
		//class="input-text required-entry validate-email" 
		//type="email" 
		//title="Email Address" 
		//value="" name="login[username]"
		
		// Element = tagname[attribute='value']
		driver.findElement(By.id("email")).sendKeys("Automation");
		driver.findElement(By.className("f")).sendKeys("Automation");
		driver.findElement(By.name("f")).sendKeys("Automation");
		
		
	}
	
	@AfterClass
	public void afterClass() {
		
	}

}
