package selenium_api;

import org.testng.annotations.Test;


import org.testng.annotations.BeforeClass;

import java.util.List;
import java.util.Set;
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
public class Topic_09_Window_Popup {
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
	public void TC_01_SwitchwindowbyID() {
		// Navigate to page
		driver.get("https://daominhdam.github.io/basic-form/index.html");
		
		String sParentGUID = driver.getWindowHandle();
				
		driver.findElement(By.xpath("//a[text()='Click here']")).click();
		
		switchToChildWindowID(sParentGUID);
		String googleTitle = driver.getTitle();
		Assert.assertEquals(googleTitle, "Google");
		
		driver.close();
		
		driver.switchTo().window(sParentGUID);
		String seleniumTitle = driver.getTitle();
		Assert.assertEquals(googleTitle, "SELENIUM WEBDRIVER FORM DEMO");
		
	}
	
	@Test
	public void TC_02_SwitchwindowbyTitle() {
		// Navigate to page
		driver.get("https://daominhdam.github.io/basic-form/index.html");
		
		String sParentGUID = driver.getWindowHandle();
				
		driver.findElement(By.xpath("//a[text()='Click here']")).click();
		
		switchToWindowByTitle("Google");
		
		String googleTitle = driver.getTitle();
		Assert.assertEquals(googleTitle, "Google");
		
		switchToWindowByTitle("SELENIUM WEBDRIVER FORM DEMO");
		String seleniumTitle = driver.getTitle();
		Assert.assertEquals(googleTitle, "SELENIUM WEBDRIVER FORM DEMO");
		
	}
	
	@Test
	public void TC_03_SwitchwindowbyTitle() {
		// Navigate to page
		driver.get("http://www.hdfcbank.com/");
		
		String sParentGUID = driver.getWindowHandle();
				
		driver.findElement(By.xpath("//a[text()='Agri']")).click();
		
		switchToWindowByTitle("HDFC Bank Kisan Dhan Vikas e-Kendra");
		
		String newWin = driver.getTitle();
		Assert.assertEquals(newWin, "HDFC Bank Kisan Dhan Vikas e-Kendra");
		
		
		driver.findElement(By.xpath("//a[text()='Account Details']")).click();
		String WelcomeWin = driver.getTitle();
		Assert.assertEquals(WelcomeWin, "Welcome to HDFC Bank NetBanking");
		
		
		
	}
	
	
	//Switch to child Windows (only 2 windows)

    public void switchToChildWindowID(String parent) {
                Set<String> allWindows = driver.getWindowHandles();
                for (String runWindow : allWindows) {
                            if (!runWindow.equals(parent)) {
                                        driver.switchTo().window(runWindow);
                                        break;
                            }
                }
    }

    //Switch to child Windows (greater than 2 windows and title of the pages are unique)

    public void switchToWindowByTitle(String title) {
                Set<String> allWindows = driver.getWindowHandles();
                for (String runWindows : allWindows) {
                            driver.switchTo().window(runWindows);
                            String currentWin = driver.getTitle();
                            if (currentWin.equals(title)) {
                                        break;
                            }
                }
    }
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	

}
