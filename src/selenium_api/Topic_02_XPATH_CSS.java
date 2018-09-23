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
		driver.findElement(By.id("email")).sendKeys("giangthihathanh@gmail.com");
		driver.findElement(By.className("input-text required-entry validate-email")).sendKeys("giangthihathanh@gmail.com");
		
		driver.findElement(By.linkText("About Us")).click();
		
		// By CSS
		driver.findElement(By.cssSelector("input[id='email']")).sendKeys("giangthihathanh@gmail.com\"");
		
		// Xpath
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("giangthihathanh@gmail.com");
		
		
		//Parent node
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		
		//Contain Text
		driver.findElement(By.xpath("//input[contains(@id, 'ser-mess']")).sendKeys("Error 404");
		
		//Start with
		driver.findElement(By.xpath("//div[@class='flipBannerWrap']//iframe[start-with(@id,'viz_iframe']")).click();
		
		//Or
		driver.findElement(By.xpath("//@class='form-control' or @id='user-message']")).click();		
		
		//And
		driver.findElement(By.xpath("//@id='user-message' and @class='form-control']")).click();
		
		//Following Sibling
		driver.findElement(By.xpath("//td[text()='Customer ID']/following-sibling::td")).click();

		//Preceding Sibling
		driver.findElement(By.xpath("//span[text()='Total']/preceding-sibling::span")).click();
	
	
	}
	
	@AfterClass
	public void afterClass() {
		
	}

}
