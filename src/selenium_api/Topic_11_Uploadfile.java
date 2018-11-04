package selenium_api;

import org.testng.annotations.Test;


import org.testng.annotations.BeforeClass;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.server.handler.ImplicitlyWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_11_Uploadfile {
	WebDriver driver;
	String projectFolder = System.getProperty("user.dir");
	String filename01 = "001.PNG";
	String filename02 = "002.PNG";
	String filename03 = "003.PNG";
	
	String filePath01 = projectFolder + "\\Upload\\" + filename01;
	String filePath02 = projectFolder + "\\Upload\\" + filename02;
	String filePath03 = projectFolder + "\\Upload\\" + filename03;
	
	String email;
	String folderName;
	String firstName = "Thanh Ha";
	
	@BeforeClass
	public void beforeClass() {
		// Start Browser
		driver = new FirefoxDriver();
		// Cài Chrome driver.exe
		//System.setProperty("webdriver.crome.driver",".\\lib\\chromedriver.exe");
		//driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		email = "selenium06" + randomData() + "@gmail.com";
		folderName = "autotest" + randomData();
	}
	
	@Test
	public void TC_01_Uploadfile_MultipleOneTime_By_Sendkey() throws Exception {
		// Navigate to page
		driver.get("http://blueimp.github.com/jQuery-File-Upload/");
		
		WebElement upload =  driver.findElement(By.xpath("//input[@type='file']"));
		upload.sendKeys(filePath01 + "\n" + filePath02  + "\n" + filePath03);
		
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + filename01 + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + filename02 + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + filename03 + "']")).isDisplayed());
		
		Thread.sleep(5000);
		
		// Click 3 Start buttons
		
		List<WebElement> startButtons = driver.findElements(By.xpath("//table//button[@class='btn btn-primary start']"));
		
		//For each
		for (WebElement start : startButtons) {
			start.click();
			Thread.sleep(1000);
		}
		
		// Check 3 images uploaded successfully
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title='" + filename01 + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title='" + filename02 + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title='" + filename03 + "']")).isDisplayed());
		
		// Check 3 images real
		List<WebElement> images = driver.findElements(By.xpath("//table[@class='table table-striped']//img"));
		for (WebElement image : images) {
			Assert.assertTrue(checkAnyImageLoaded(image));
		}
		
		
	}
	
	@Test
	public void TC_02_Uploadfile_Multiple_By_Sendkey() throws Exception {
		// Navigate to page
		driver.get("http://blueimp.github.com/jQuery-File-Upload/");
		
		//Upload duyệt qua mảng
		String files[] = { filePath01, filePath02, filePath03 };
		
		int filelength = files.length;
		// Duyet qua tung phan tu cua mang
		// Mang 3 phan tu
		
		//For
		for (int i = 0; i < files.length; i++) {
			WebElement upload =  driver.findElement(By.xpath("//input[@type='file']"));
			upload.sendKeys(files[i]);
		}
		
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + filename01 + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + filename02 + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + filename03 + "']")).isDisplayed());
		
		Thread.sleep(5000);
		
		// Click 3 Start buttons
		
		List<WebElement> startButtons = driver.findElements(By.xpath("//table//button[@class='btn btn-primary start']"));
		
		//For each
		for (WebElement start : startButtons) {
			start.click();
			Thread.sleep(1000);
		}
		
		// Check 3 images uploaded successfully
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title='" + filename01 + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title='" + filename02 + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title='" + filename03 + "']")).isDisplayed());
		
		// Check 3 images real
		List<WebElement> images = driver.findElements(By.xpath("//table[@class='table table-striped']//img"));
		for (WebElement image : images) {
			Assert.assertTrue(checkAnyImageLoaded(image));
		}
		
		
	}
	
	@Test
	public void TC_03_Uploadfile_AutoIT() throws Exception {
		// Navigate to page
		driver.get("http://blueimp.github.com/jQuery-File-Upload/");
		
		String browserName = driver.toString().toLowerCase();
		
		if (browserName.contains("chrome")) {
			driver.findElement(By.cssSelector(".fileinput-button")).click();
		} else if (browserName.contains("firefox")) {
			clickToElementByJS("//input[@type='file'])");
		}
		
		Thread.sleep(3000);
		
		// Thực thi 1 file exe trong code java
		if (browserName.contains("firefox")) {
			Runtime.getRuntime().exec(new String[] { ".\\Upload\\firefox.exe", filePath01 });
		} else if (browserName.contains("chrome")){
			Runtime.getRuntime().exec(new String[] { ".\\Upload\\chrome.exe", filePath01 });
		} else {
			Runtime.getRuntime().exec(new String[] { ".\\Upload\\ie.exe", filePath01 });
		}
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + filename01 + "']")).isDisplayed());
		
		Thread.sleep(5000);
		
		// Click 3 Start buttons
		
		clickToElementByJS("//table//button[@class='btn btn-primary start']");
		
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title='" + filename01 + "']")).isDisplayed());
		
		
	}
	
	@Test
	public void TC_04_Uploadfile_Robot() throws Exception {
		// Navigate to page
		driver.get("http://blueimp.github.com/jQuery-File-Upload/");
		
		//Specify the file location with extension
		StringSelection select = new StringSelection("C:\\Users\\DucNguyen\\eclipse-workspace\\WEBDRIVER_API_06_THANHG\\Upload\\001.PNG");

		//Copy to clipboard
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(select, null);

		//Click
		String browserName = driver.toString().toLowerCase();
		
		if (browserName.contains("chrome")) {
			driver.findElement(By.cssSelector(".fileinput-button")).click();
		} else if (browserName.contains("firefox")) {
			clickToElementByJS("//input[@type='file'])");
		}
		
		Thread.sleep(3000);

		Robot robot = new Robot();
		Thread.sleep(1000);
		
		// Enter: set con trỏ chuột focus input file path
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		
		// Crt V: Nhấn xuống
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		
		// Crtl V: nhả phím
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyRelease(KeyEvent.VK_V);
		Thread.sleep(1000);
		
		// Enter: thoát khỏi window dialog
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		
		
		// Thực thi 1 file exe trong code java
		if (browserName.contains("firefox")) {
			Runtime.getRuntime().exec(new String[] { ".\\Upload\\firefox.exe", filePath01 });
		} else if (browserName.contains("chrome")){
			Runtime.getRuntime().exec(new String[] { ".\\Upload\\chrome.exe", filePath01 });
		} else {
			Runtime.getRuntime().exec(new String[] { ".\\Upload\\ie.exe", filePath01 });
		}
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + filename01 + "']")).isDisplayed());
		
		Thread.sleep(5000);
		
		// Click 3 Start buttons
		
		clickToElementByJS("//table//button[@class='btn btn-primary start']");
		
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title='" + filename01 + "']")).isDisplayed());
		
		
	}
	
	@Test
	public void TC_05_Uploadfile_Exercise04() throws Exception {
		// Navigate to page
		driver.get("https://encodable.com/uploaddemo/");
		
		WebElement browse = driver.findElement(By.xpath("//input[@id='uploadname1']"));
		browse.sendKeys(filePath01);
		
		WebElement efolderName = driver.findElement(By.xpath("//input[@id='newsubdir1']"));
		efolderName.sendKeys(folderName);
		
		WebElement eEmail = driver.findElement(By.xpath("//input[@name='email_address']"));
		eEmail.sendKeys(email);
		
		WebElement efirstName = driver.findElement(By.xpath("//input[@name='first_name']"));
		efirstName.sendKeys(firstName);
		
		driver.findElement(By.xpath("//input[@id='uploadbutton']")).click();
		
		Assert.assertTrue(driver.findElement(By.xpath("//dd[text()='Email Address: "+ email +"']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//dd[text()='First Name: "+ firstName +"']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()=': "+ filename01 +"']")).isDisplayed());
		
		driver.findElement(By.xpath("//a[text()='View Uploaded Files']")).click();
		driver.findElement(By.xpath("//a[text()= " + folderName + "']")).click();
		
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()=': "+ filename01 +"']")).isDisplayed());
		
	}

	public Object clickToElementByJS(String locator) {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			WebElement element =driver.findElement(By.xpath(locator));
			return js.executeScript("arguments[0].click();", element);
		} catch (Exception e) {
			e.getMessage();
			return null;
		}
	}
	
	public boolean checkAnyImageLoaded(WebElement image) {
		  JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		  return (boolean) jsExecutor.executeScript("return arguments[0].complete && " + "typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0", image);
	}
	
	public int randomData() {
		Random random = new Random();
		int number = random.nextInt(999999999);
		return number;

	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
