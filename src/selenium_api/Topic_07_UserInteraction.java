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
public class Topic_07_UserInteraction {
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
	
	@Test(enabled=false)
	public void TC_01_HoverMouse() {
		// Navigate to page
		driver.get("https://www.myntra.com/");

		WebElement iconAccount = driver.findElement(By.xpath("//div[@class='desktop-userIconsContainer']"));
		action.moveToElement(iconAccount).perform();
		
		WebElement btnLogin = driver.findElement(By.xpath("//a[@data-track='login']"));
		btnLogin.click();	
		
		WebElement formLogin = driver.findElement(By.xpath("//div[@class='login-container']"));
		Assert.assertTrue(formLogin.isDisplayed());
		  
		// Nhả chuột: click and hold/ click/ drag/drop
		//action.release();
		  
		// Run action
		//action.perform();		
	
	}
	
	@Test(enabled=false)
	public void TC_02_ClickandHold() throws Exception {
		// Navigate to page
		driver.get("http://jqueryui.com/resources/demos/selectable/display-grid.html");
		
		List <WebElement> numberList = driver.findElements(By.xpath("//ol[@class='ui-selectable']/li"));
		action.clickAndHold(numberList.get(0)).moveToElement(numberList.get(3)).release().perform();
		
		Thread.sleep(5000);
		
		List <WebElement> selectedList = driver.findElements(By.xpath("//ol[@class='ui-selectable']/li[@class='ui-state-default ui-selectee ui-selected']"));
		Assert.assertEquals(selectedList.size(), "4");
	}
	
	@Test(enabled=false)
	public void TC_03_ClickRandom() throws Exception {
		// Navigate to page
		driver.get("http://jqueryui.com/resources/demos/selectable/display-grid.html");
		
		List <WebElement> numberList = driver.findElements(By.xpath("//ol[@class='ui-selectable']/li"));
		action.keyDown(Keys.LEFT_CONTROL).click(numberList.get(0)).click(numberList.get(2)).click(numberList.get(4)).click(numberList.get(5)).keyUp(Keys.LEFT_CONTROL).perform();
		Thread.sleep(5000);
		
		List <WebElement> selectedList = driver.findElements(By.xpath("//ol[@class='ui-selectable']/li[@class='ui-state-default ui-selectee ui-selected']"));
		Assert.assertEquals(selectedList.size(), "4");
	}
	
	@Test
	public void TC_03_DoubleClick() throws Exception {
		// Navigate to page
		driver.get("http://www.seleniumlearn.com/double-click");
		
		WebElement btnDoubleClick = driver.findElement(By.xpath("//button[text()='Double-Click Me!']"));
		action.doubleClick(btnDoubleClick);
		
		alert = driver.switchTo().alert();
		
		Assert.assertEquals(alert.getText(), "The Button was double-clicked.");
		
		alert.accept();
	}
	
	@Test
	public void TC_04_RightClick() throws Exception {
		// Navigate to page
		driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");
		
		WebElement btnRightClick = driver.findElement(By.xpath("//span[text()='right click me']"));
		action.contextClick(btnRightClick).perform();
		
		WebElement btnQuit = driver.findElement(By.xpath("//ul[@class='context-menu-list context-menu-root']/li[contains(@class,'context-menu-icon-quit')]"));
		Assert.assertTrue(btnQuit.isDisplayed());
		
		action.moveToElement(btnQuit).perform();
		
		WebElement btnNewQuit = driver.findElement(By.xpath("//ul[@class='context-menu-list context-menu-root']/li[contains(@class,'context-menu-icon-quit') and contains(@class,'context-menu-hover') and contains(@class,'context-menu-visible')]"));
		Assert.assertTrue(btnNewQuit.isDisplayed());
		
		action.click(btnNewQuit).perform();
		
		alert = driver.switchTo().alert();
		Assert.assertEquals(alert.getText(), "clicked: quit");
		alert.accept();
	}
	
	@Test
	public void TC_05_DragandDrop() throws Exception {
		// Navigate to page
		driver.get("http://demos.telerik.com/kendo-ui/dragdrop/angular");
		WebElement dragTarget = driver.findElement(By.xpath("//div[@id='draggable']"));
		WebElement dropTarget = driver.findElement(By.xpath("//div[@id='droptarget']"));
		
		action.dragAndDrop(dragTarget, dropTarget).perform();		
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='droptarget'] and text()='You did great!'")).isDisplayed());	
	}
	
	public void TC_06_DragandDrop() throws Exception {
		// Navigate to page
		driver.get("http://jqueryui.com/resources/demos/droppable/default.html");
		WebElement dragTarget = driver.findElement(By.xpath("//div[@id='draggable']"));
		WebElement dropTarget = driver.findElement(By.xpath("//div[@id='droppable']"));
		
		action.dragAndDrop(dragTarget, dropTarget).perform();		
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='droppable']/p[text()='Dropped!']")).isDisplayed());
		
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	

}
