package selenium_api;

import org.testng.annotations.Test;


import org.testng.annotations.BeforeClass;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.server.handler.ImplicitlyWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
public class Topic_08_Frame_iFrame {
	WebDriver driver;
	Actions action;
	Alert alert;
	
	@BeforeClass
	public void beforeClass() {
		// Start Browser
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}
	
	@Test
	public void TC_01_iFrame() {
		// Navigate to page
		driver.get("http://www.hdfcbank.com/");
		// Issue 1: switch qua iframe nếu có
		// Issue 2: Change từ WebElement qua ListElement theo trường hợp element xuất hiện random
		
		// Issue 03 Change global Timeout
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		List <WebElement> iframe = driver.findElements(By.xpath("//iframe[@id='vizury-notification-template']"));
		if (iframe.size()>0) {
			driver.switchTo().frame(iframe.get(0));
			WebElement iconClose = driver.findElement(By.xpath("//div[@id='div-close']"));
			// Issue 03: use Java click
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", iconClose);
			
			//Issue 05: Switch to default content
			driver.switchTo().defaultContent();
			
		}
		System.out.println("step3");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		// Issue 04: Handle dynamic xpath
		WebElement iframeLookingFor = driver.findElement(By.xpath("//div[@class='flipBannerWrap']//iframe[contains(@id,'viz_iframe')]"));
		driver.switchTo().frame(iframeLookingFor);
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='What are you looking for?']")).isDisplayed());
		
		driver.switchTo().defaultContent();
		WebElement iframeSlidingBanner = driver.findElement(By.xpath("//div[@class='slidingbanners']//iframe"));
		
		List <WebElement> bannerImages = driver.findElements(By.xpath("//div[@id='productcontainer']//img[@class='bannerimage']"));
		Assert.assertEquals(bannerImages.size(), "6");
		
		
		List <WebElement> flipImages = driver.findElements(By.xpath("//div[@class='flipBanner']//img[@class='front icon']"));
		Assert.assertEquals(flipImages.size(), "8");
		
		// Check Display
		for (int i=0; i < flipImages.size(); i++) {
			Assert.assertTrue(flipImages.get(i).isDisplayed());
		}
		
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	

}
