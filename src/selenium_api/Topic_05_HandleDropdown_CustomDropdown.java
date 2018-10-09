package selenium_api;

import org.testng.annotations.Test;


import org.testng.annotations.BeforeClass;

import java.util.List;
import java.util.concurrent.TimeUnit;

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

public class Topic_05_HandleDropdown_CustomDropdown {
	WebDriver driver;
	WebDriverWait wait = new WebDriverWait(driver, 10000);
	
	@BeforeClass
	public void beforeClass() {
		// Start Browser
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

			
	}
	
	@Test
	public void TC_01_HTLMDropdownlist() {
		// Navigate to page
		driver.get("https://daominhdam.github.io/basic-form/");
		
		Select select = new Select(driver.findElement(By.xpath("//select[@id='job1']")));
		
		// Check do not support for multi-select
		Assert.assertFalse(select.isMultiple());
		
		//Step 03 - Chọn giá trị Automation Tester trong dropdown bằng phương thức selectVisible
		// Nên chọn các Index cúi cùng - ko nên dùng
		select.selectByIndex(0);
		
		// chỉ sử dụng dc nếu có value
		select.selectByValue("manual");
		
		//
		select.selectByVisibleText("Automation Tester");	
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Automation Tester");
		
		select.selectByValue("manual");	
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Manual Tester");
		
		select.selectByIndex(3);
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Mobile Tester");
		
		// Kiem tra dropfown list có đủ 5 giá trị
		Assert.assertEquals(select.getOptions().size(), 5);
		
	}
	
	@Test
	public void TC_02_CustomDropdownlist() throws Exception {
		//JQUERY
		// Navigate to page
		driver.get("http://jqueryui.com/resources/demos/selectmenu/default.html");
		selectItemInCustomDropdown("//span[@id='number-button']", "//ul[@id='number-menu']/li[@class='ui-menu-item']", "19");
		
		Thread.sleep(3000);

		Assert.assertTrue(driver.findElement(By.xpath("//span[@id='number-button']/span[@class='ui-selectmenu-text' and text ='19']")).isDisplayed());

		// ANGULAR
		// Navigate to page
		driver.get("https://material.angular.io/components/select/examples");
		selectItemInCustomDropdown("//div[@class='mat-form-field-infix']/mat-select[@placeholder='State']", "//span[@class='mat-option-text']", "Colorado");
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='mat-form-field-infix']/mat-select[@placeholder='State']/span[text()='Colorado']")).isDisplayed());
		
		// VUE 
		driver.get("https://mikerodham.github.io/vue-dropdowns/");
		selectItemInCustomDropdown("//li[@class='dropdown-toggle']", "//ul[@class='dropdown-menu hide']", "Second Option");
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='dropdown-toggle' and text = 'Second Option']")).isDisplayed());
		
		// JQUERY EDITABLE AND SELECT
		driver.get("http://indrimuska.github.io/jquery-editable-select/");
		sendkey_and_selectItemInCustomDropdown("//div[@id='default-place']","Fiat", "//div[@id='default-place']/ul[@class='es-list']");
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='default-place']/ul[@class='es-list']/li[@class='es-visible selected' and text ='Fiat']")).isDisplayed());
		
		// MULTIPLE SELECT
		driver.get("http://wenzhixin.net.cn/p/multiple-select/docs/");
		sendkey_and_selectItemInCustomDropdown("//div[@id='default-place']","Fiat", "//div[@id='default-place']/ul[@class='es-list']");
		 Assert.assertTrue(driver.findElement(By.xpath("//div[@id='default-place']/ul[@class='es-list']/li[@class='es-visible selected' and text ='Fiat']")).isDisplayed());
				
	}
	
	public void sendkey_and_selectItemInCustomDropdown (String parentLocator, String key, String allItemsLocator) throws Exception {
		// 1. Check thằng tag name bọc ngoài
		// 2. Click cho xổ ra tất cả các items
		WebElement parentDropdown = driver.findElement(By.xpath(parentLocator));
		
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", parentDropdown);
		Thread.sleep(500);
		parentDropdown.clear();
		parentDropdown.sendKeys(key);
		
				
		// 3. Xác định các item có chung locator là gì
		List <WebElement> allItemsDropdownList = driver.findElements(By.xpath(allItemsLocator));
		int itemNumber = allItemsDropdownList.size();
		
		// Wait until all items visible
		wait.until(ExpectedConditions.visibilityOfAllElements(allItemsDropdownList));
				
		// 4. Get Text của tất cả các item
		// 5. So sánh với text mà mình cần truyền vào
		// 6. Nếu như = text cần thì click
		for (int i = 0; i< itemNumber; i++) {
			if (allItemsDropdownList.get(i).getText().equals(key)) {
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", allItemsDropdownList.get(i));
				Thread.sleep(500); 
				allItemsDropdownList.get(i).click();
				break;
			}
		}		
	}
	
	public void selectItemInCustomDropdown (String parentLocator, String allItemsLocator, String expectedValue) throws Exception {
		// 1. Check thằng tag name bọc ngoài
		// 2. Click cho xổ ra tất cả các items
		WebElement parentDropdown = driver.findElement(By.xpath(parentLocator));
		
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", parentDropdown);
		Thread.sleep(500); 
		parentDropdown.click();
		
				
		// 3. Xác định các item có chung locator là gì
		List <WebElement> allItemsDropdownList = driver.findElements(By.xpath(allItemsLocator));
		int itemNumber = allItemsDropdownList.size();
		
		// Wait until all items visible
		wait.until(ExpectedConditions.visibilityOfAllElements(allItemsDropdownList));
				
		// 4. Get Text của tất cả các item
		// 5. So sánh với text mà mình cần truyền vào
		// 6. Nếu như = text cần thì click
		for (int i = 0; i< itemNumber; i++) {
			if (allItemsDropdownList.get(i).getText().equals(expectedValue)) {
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", allItemsDropdownList.get(i));
				Thread.sleep(500); 
				allItemsDropdownList.get(i).click();
				break;
			}
		}		
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
