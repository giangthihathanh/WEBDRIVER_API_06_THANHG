package selenium_api;

import org.testng.annotations.Test;


import org.testng.annotations.BeforeClass;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.server.handler.ImplicitlyWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;


public class Topic_06_ButtonRadioCheckbox {
	WebDriver driver;
	
	@BeforeClass
	public void beforeClass() {
		// Start Browser
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}
	
	@Test(enabled=false)
	public void TC_01_Button() {
		// Navigate to page
		driver.get("http://live.guru99.com/");
		WebElement linkMyAccount = driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']"));
		clickElementByJavascript(linkMyAccount);
		
		WebElement formLogin = driver.findElement(By.xpath("//form[@id='login-form']"));
		Assert.assertTrue(isControlDisplayed(formLogin));
		
		WebElement btnCreateAccount = driver.findElement(By.xpath("//a[@title='Create an Account']"));
		clickElementByJavascript(btnCreateAccount);
		
		WebElement formRegister = driver.findElement(By.xpath("//form[@id='form-validate']"));
		Assert.assertTrue(isControlDisplayed(formRegister));	
	
	}
	
	@Test
	public void TC_02_Checkbox() throws Exception {
		// Navigate to page
		driver.get("https://demos.telerik.com/kendo-ui/styling/checkboxes");
		
		WebElement checkDualZone = driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input"));
		clickElementByJavascript(checkDualZone);
		Thread.sleep(5000);
		
		// Thẻ label ko thẻ check isSelected
		Assert.assertTrue(checkDualZone.isSelected());
		
	}
	
	@Test
	public void TC_03_Radio() throws Exception {
		// Navigate to page
		driver.get("http://demos.telerik.com/kendo-ui/styling/radios");
		
		WebElement rad20Petrol = driver.findElement(By.xpath("//label[text()='2.0 Petrol, 147kW']/preceding-sibling::input"));
		clickElementByJavascript(rad20Petrol);
		Thread.sleep(5000);
		
		// Thẻ label ko thẻ check isSelected
		Assert.assertTrue(rad20Petrol.isSelected());
		
	}
	
	@Test
	public void TC_04_JSAlert() throws Exception {
		// Navigate to page
		driver.get("http://daominhdam.890m.com/#");
		
		WebElement btnJSAlert = driver.findElement(By.xpath("//button[text()='Click for JS Alert']"));
		clickElementByJavascript(btnJSAlert);
		
		Alert alert = driver.switchTo().alert();
		
		String JSAlert = alert.getText();
		Assert.assertEquals(JSAlert, "I am a JS Alert");
		
		alert.accept();
		Assert.assertTrue(driver.findElement(By.xpath("//*[@id='result' and text()='You clicked an alert successfully ']")).isDisplayed());
		Thread.sleep(5000);		
	}
	
	@Test
	public void TC_05_JSConfirm() throws Exception {
		// Navigate to page
		driver.get("http://daominhdam.890m.com/#");
		
		WebElement btnJSConfirm = driver.findElement(By.xpath("//button[text()='Click for JS Confirm']"));
		clickElementByJavascript(btnJSConfirm);
		
		Alert alertConfirm = driver.switchTo().alert();
		

		alertConfirm.dismiss();
		Assert.assertTrue(driver.findElement(By.xpath("//*[@id='result' and text()='You clicked: Cancel']")).isDisplayed());
		Thread.sleep(5000);	
		
		alertConfirm.accept();
		Assert.assertTrue(driver.findElement(By.xpath("//*[@id='result' and text()='You clicked: Ok']")).isDisplayed());
		Thread.sleep(5000);		
	}
	
	@Test
	public void TC_06_JSPromt() throws Exception {
		// Navigate to page
		driver.get("http://daominhdam.890m.com/#");
		
		WebElement btnJSPromt = driver.findElement(By.xpath("//button[text()='Click for JS Prompt']"));
		clickElementByJavascript(btnJSPromt);
		
		Alert alertPromt = driver.switchTo().alert();
		
		alertPromt.sendKeys("Thanh");
		alertPromt.accept();
		Assert.assertTrue(driver.findElement(By.xpath("//*[@id='result' and text()='You entered: thanh']")).isDisplayed());
		Thread.sleep(5000);		
	}
	
	@Test
	public void TC_07_AuthenticationAlert() throws Exception {
		// Navigate to page
		driver.get("http://admin:admin@the-internet.herokuapp.com/basic_auth");
		
		//Alert alertPromt = driver.switchTo().alert();
		
		//alertPromt.sendKeys("Thanh");
		//alertPromt.accept();
		Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text()='Congratulations! You must have the proper credentials.']")).isDisplayed());
		Thread.sleep(5000);		
	}
	
		
	
	
	public void clickElementByJavascript(WebElement element) {
	    JavascriptExecutor je = (JavascriptExecutor) driver;
	    je.executeScript("arguments[0].click();", element);
	}
	
	public boolean isControlDisplayed(WebElement element) {
		return element.isDisplayed();
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
