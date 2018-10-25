package selenium_api;

import org.testng.annotations.Test;


import org.testng.annotations.BeforeClass;

import java.util.List;
import java.util.Random;
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
public class Topic_10_JAVASCRIPTEXECUTOR {
	WebDriver driver;
	Actions action;
	Alert alert;
	JavascriptExecutor javascript;
	
	@BeforeClass
	public void beforeClass() {
		// Start Browser
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}
	
	@Test
	public void TC_01() {

		//Step 01 - Truy cập vào trang: http://live.guru99.com/
		driver.get("http://live.guru99.com/");

		//Step 02 - Sử dụng JE để get domain của page => Verify domain = live.guru99.com
		//javascript.executeScript("document.domain");
		String domain = (String) executeForBrowser("return document.domain;");
		Assert.assertEquals(domain, "live.guru99.com");

		// Step 03 - Sử dụng JE để get URL của page
		// Verify URL = http://live.guru99.com/
		String URL = (String) executeForBrowser("return document.URL;");
		Assert.assertEquals(URL, "Home page");

		// Step 04 - Open MOBILE page (Sử dụng JE)
		WebElement mobileLink = driver.findElement(By.xpath("//a[text=()'Mobile']"));
		highlightElement(mobileLink);
		clickToElementByJS(mobileLink);

		// Step 05 - Add sản phẩm SAMSUNG GALAXY vào Cart (click vào ADD TO CART button bằng JE)
		WebElement samsungAddtoCart = driver.findElement(By.xpath("//h2[a[text()='Samsung Galaxy']]/following-sibling::div[@class='actions']//button"));
		highlightElement(samsungAddtoCart);
		clickToElementByJS(samsungAddtoCart);

		//Example: //h5[contain(.,'WebDriver Testing in python')]/parent::a[@class='card-body']/following-sibling::div//a[contains(text(),'Add to cart')]


		// Step 06 - Verify message được hiển thị: Samsung Galaxy was added to your shopping cart. (Sử dụng JE - Get innertext of the entire webpage )
		String successMessage = (String) executeForBrowser("return document.documentElement.innerText;");
		Assert.assertTrue(successMessage.contains("Samsung Galaxy was added to your shopping cart"));

		// Step 07 - Open PRIVACY POLICY page (Sử dụng JE)
		WebElement PRIVACYPOLICY = driver.findElement(By.xpath("//a[text=()'Privacy Policy']"));
		highlightElement(PRIVACYPOLICY);
		clickToElementByJS(PRIVACYPOLICY);

		// Verify title của page = Privacy Policy (Sử dụng JE)
		String title = (String) executeForBrowser("return document.title;");
		Assert.assertEquals(title, "Privacy Policy");

		// Step 08 - Srcoll xuống cuối page
		scrollToBottomPage(driver);

		// Step 09 - Verify dữ liệu có hiển thị với chỉ 1 xpath
		// Hướng dẫn: sử dụng following-sibling
		Assert.assertTrue(driver.findElement(By.xpath("//th[text()='WISHLIST_CNT']/following-sibling::td[text()='The number of items in your Wishlist.']")).isDisplayed());

		// Step 10 - Navigate tới domain: http://demo.guru99.com/v4/ (Sử dụng JE)

		navigatetodomain("http://demo.guru99.com/v4/");

		// Verify domain sau khi navigate = demo.guru99.com
		String newdomain = (String) executeForBrowser("return document.domain;");
		Assert.assertEquals(newdomain, "demo.guru99.com");
		}

		public void TC_02() {
		String sFirstname = "Automation", sLastname = "Testing";

		//Step 01 - Truy cập vào trang: https://www.w3schools.com/tags/tryit.asp…
		driver.get("https://www.w3schools.com/tags/tryit.asp…");

		//Step 02 - Remove thuộc tính disabled của field Last name
		//Switch qua iframe nếu có
		WebElement iframResult = driver.findElement(By.xpath("//ifram[@id='iframeResult']"));
		driver.switchTo().frame(iframResult);


		//Step 03 - Sendkey vào field Last name
		//Eg. Automation Testing
		WebElement firstName = driver.findElement(By.xpath("//input[@name='fname']"));
		sendkeyToElementByJS(firstName, sFirstname);

		WebElement lastName = driver.findElement(By.xpath("//input[@name='lname']"));
		removeAttributeInDOM(lastName, "disable");
		sendkeyToElementByJS(lastName, sLastname);

		//Step 04 - Click Submit button
		WebElement btnSubmit = driver.findElement(By.xpath("//input[@value='Submit']"));
		clickToElementByJS(btnSubmit);

		//Step 05 - Verify dữ liệu sau khi submit chứa đoạn text đã fill trong field Lastname (Automation Testing)
		Assert.assertTrue(driver.findElement(By.xpath("//div[contains(text(),'fname=Automation&lname=Testing')]")).isDisplayed());
		}

		public void TC_03() {
			String sFirstname = "Thanh", sMiddleName = "Ha", sLastname = "Giang", sPassword = "automation#123", sConfirmation = "automation#123";

		//Step 01 - Truy cập vào trang: http://live.guru99.com/
		driver.get("http://live.guru99.com/");

		//Step 02 - Click vào link "My Account" để tới trang đăng nhập (Sử dụng JE)
		WebElement MyAccount = driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']"));
		clickToElementByJS(MyAccount);

		//Step 03 - Click CREATE AN ACCOUNT button để tới trang đăng kí tài khoản (Sử dụng JE)
		WebElement CreateAccount = driver.findElement(By.xpath("//a[@title='Create an Account']"));
		clickToElementByJS(CreateAccount);

		//Step 04 - Nhập thông tin hợp lệ vào tất cả các field: First Name/ Last Name/ Email Address/ Password/ Confirm Password (Sử dụng JE)
		String sEmail = "selenium06"+randomData()+"@gmail.com";
		
		WebElement eFirstname = driver.findElement(By.xpath("//input[@id='firstname']"));
		WebElement eMiddlename = driver.findElement(By.xpath("//input[@id='middlename']"));
		WebElement eLastname = driver.findElement(By.xpath("//input[@id='lastname']"));
		WebElement eEmail = driver.findElement(By.xpath("//input[@id='email_address']"));
		WebElement ePassword = driver.findElement(By.xpath("//input[@id='password']"));
		WebElement eConfirmation = driver.findElement(By.xpath("//input[@id='confirmation']"));
		
		
		sendkeyToElementByJS(eFirstname, sFirstname);
		sendkeyToElementByJS(eMiddlename, sMiddleName);
		sendkeyToElementByJS(eLastname, sLastname);
		sendkeyToElementByJS(eEmail, sEmail);
		sendkeyToElementByJS(ePassword, sPassword);
		sendkeyToElementByJS(eConfirmation, sConfirmation);

		//Step 05 - Click REGISTER button (Sử dụng JE)
		
		WebElement btnRegis = driver.findElement(By.xpath("//button[@title='Register']"));
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		clickToElementByJS(btnRegis);
		
		
		//Step 06 - Verify message xuất hiện khi đăng kí thành công: Thank you for registering with Main Website Store. (Sử dụng JE)
		String successMessage = (String) executeForBrowser("return document.documentElement.innerText;");
		Assert.assertEquals(successMessage, "Thank you for registering with Main Website Store.");
		
		// Step 07 - Logout khỏi hệ thống
		WebElement AccHeader = driver.findElement(By.xpath("//div[@class='page-header-container']//span[text()='Account']"));
		clickToElementByJS(AccHeader);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		WebElement Logout = driver.findElement(By.xpath("//a[@title='Log Out']"));
		clickToElementByJS(AccHeader);
		driver.manage().timeouts().implicitlyWait(320, TimeUnit.SECONDS);
		
		// Step 08 - Kiểm tra hệ thống navigate về Home page sau khi logout thành công

		String homepageTitle = (String) executeForBrowser("return document.documentElement.innerText;");
		Assert.assertEquals(homepageTitle, "Home page");

		String urlLoginPage = (String) executeForBrowser("return document.URL");
		Assert.assertEquals(urlLoginPage,"http://live.guru99.com/index.php/");		
		}
		
		public int randomData() {
			Random random = new Random() ;
			int number = random.nextInt(999999999);
			return number;
			
		}

		public void highlightElement(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].style.border='6px groove red'", element);
		}

		public Object executeForBrowser(String javaSript) {
		try {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		return js.executeScript(javaSript);
		} catch (Exception e) {
		e.getMessage();
		return null;
		}
		}

		public Object navigatetodomain(String domain) {
		try {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		return js.executeScript("window.location = '" + domain + "'");
		} catch (Exception e) {
		e.getMessage();
		return null;
		}
		}

		public Object clickToElementByJS(WebElement element) {
		try {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		return js.executeScript("arguments[0].click();", element);
		} catch (Exception e) {
		e.getMessage();
		return null;
		}
		}

		public Object sendKeyToElementByJS(String value, WebElement element) {
		try {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		return js.executeScript("arguments[0].setAttribute('value','" + value + "')", element);
		} catch (Exception e) {
		e.getMessage();
		return null;
		}
		}

		public Object sendkeyToElementByJS(WebElement element, String value) {
		try {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		return js.executeScript("arguments[0].setAttribute('value', '" + value + "')", element);
		} catch (Exception e) {
		e.getMessage();
		return null;
		}
		}

		public Object removeAttributeInDOM(WebElement element, String attribute) {
		try {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		return js.executeScript("arguments[0].removeAttribute('" + attribute + "');", element);
		} catch (Exception e) {
		e.getMessage();
		return null;
		}
		}

		public Object scrollToBottomPage(WebDriver driver) {
		try {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		return js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
		} catch (Exception e) {
		e.getMessage();
		return null;
		}
		}

		public Object navigateToUrlByJS(String url) {
		try {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		return js.executeScript("window.location = '" + url + "'");
		} catch (Exception e) {
		e.getMessage();
		return null;
		}
		}


	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	

}
