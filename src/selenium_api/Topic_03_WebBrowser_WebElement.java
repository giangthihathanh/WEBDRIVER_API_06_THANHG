package selenium_api;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_03_WebBrowser_WebElement {
	WebDriver driver;
	
	@BeforeClass
	public void beforeClass() {
		// Start Browser
		driver = new FirefoxDriver();
		
	}
	
	@Test(enabled=false)
	
	public void TC_01_VerifyUrlAndTitle() {
		
 
		// Step 01 - Truy cập vào trang: http://live.guru99.com
		driver.get("http://live.guru99.com");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		// Step 02 - Kiểm tra title của page là: "Home page"
		String homepageTitle = driver.getTitle();
		Assert.assertEquals(homepageTitle, "Home page");
	
		// Step 03 - Click vào link "My Account" để tới trang đăng nhập
		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();

		// Step 04 - Click CREATE AN ACCOUNT button để tới trang đăng kí tài khoản
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();

		// Step 05 - Back lại trang đăng nhập
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.navigate().back();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		// Step 06 - Kiểm tra url của page đăng nhập là: http://live.guru99.com/index.php/customer/account/login/
		String urlLoginPage = driver.getCurrentUrl();
		Assert.assertEquals(urlLoginPage,"http://live.guru99.com/index.php/customer/account/login/");

		// Step 07 - Forward tới trang tạo tài khoản
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.navigate().forward();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		// Step 08 - Kiểm tra url của page tạo tài khoản là: http://live.guru99.com/index.php/customer/account/create/
		String urCreateAcc = driver.getCurrentUrl();
		Assert.assertEquals(urCreateAcc,"http://live.guru99.com/index.php/customer/account/create/"); 
		
	}
	
	@Test(enabled=false)
	
	public void TC_02_Login_EmailandPassword_Empty() {
		// Step 01 - Truy cập vào trang: http://live.guru99.com
		driver.get("http://live.guru99.com");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);	
		// Step 02 - Click vào link "My Account" để tới trang đăng nhập
		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
		
		// Step 4 - Click Login button
		driver.findElement(By.xpath("//button[@id='send2']")).click();
		
		// Step 05 - Verify error message xuất hiện tại 2 field:  This is a required field.
		String sEmailError = driver.findElement(By.xpath("//div[@id='advice-required-entry-email']")).getText();
		String sPassError = driver.findElement(By.xpath("//div[@id='advice-required-entry-pass']")).getText();
		Assert.assertEquals(sEmailError,"This is a required field.");
		Assert.assertEquals(sPassError,"This is a required field.");
		
	}
	
	@Test(enabled=false)
	
	public void TC_03_Login_Email_Invalid() {
		// Step 01 - Truy cập vào trang: http://live.guru99.com
		driver.get("http://live.guru99.com");
	
		// Step 02 - Click vào link "My Account" để tới trang đăng nhập
		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		// Step 03 - Nhập email invalid
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("123434234@12312.123123");
		
		// Step 4 - Click Login button
		driver.findElement(By.xpath("//button[@id='send2']")).click();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		// Step 05 - Verify error message xuất hiện:
		WebElement EmailError = driver.findElement(By.xpath("//div[@id='advice-validate-email-email']"));
		String sEmailError = EmailError.getText();
		Assert.assertEquals(sEmailError,"Please enter a valid email address. For example johndoe@domain.com.");
	}
	
	@Test(enabled=false)
	
	public void TC_04_Login_Password_Invalid() {
		// Step 01 - Truy cập vào trang: http://live.guru99.com
		driver.get("http://live.guru99.com");
	
		// Step 02 - Click vào link "My Account" để tới trang đăng nhập
		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		// Step 03 - Nhập email correct and password incorrect:
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("automation@gmail.com");
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("123");
		
		// Step 4 - Click Login button
		driver.findElement(By.xpath("//button[@id='send2']")).click();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		// Step 05 - Verify error message xuất hiện tại 2 field:  This is a required field.
		String sPassError = driver.findElement(By.xpath("//div[@id='advice-validate-password-pass']")).getText();
		Assert.assertEquals(sPassError,"Please enter 6 or more characters without leading or trailing spaces.");
	}
	
	@Test(enabled=false)
	public void TC_05_Create_An_Account() {
		// Step 01 - Truy cập vào trang: http://live.guru99.com
		driver.get("http://live.guru99.com");
	
		// Step 02 - Click vào link "My Account" để tới trang đăng nhập
		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		// Step 03 - Click CREATE AN ACCOUNT button để tới trang đăng kí tài khoản
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		// Step 04 - Nhập thông tin hợp lệ vào tất cả các field
		String sEmail = "selenium06"+randomData()+"@gmail.com";
		
		driver.findElement(By.xpath("//input[@id='firstname']")).sendKeys("Thanh");
		driver.findElement(By.xpath("//input[@id='middlename']")).sendKeys("Ha");
		driver.findElement(By.xpath("//input[@id='lastname']")).sendKeys("Giang");
		driver.findElement(By.xpath("//input[@id='email_address']")).sendKeys(sEmail);
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys("automation#123");
		driver.findElement(By.xpath("//input[@id='confirmation']")).sendKeys("automation#123");

		// Step 05 - Click REGISTER button
		driver.findElement(By.xpath("//button[@title='Register']")).click();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		// Step 06 - Verify message xuất hiện khi đăng kí thành công: Thank you for registering with Main Website Store.
		WebElement messSucess = driver.findElement(By.xpath("//li[@class='success-msg']"));
		String sMessSucess = messSucess.getText();
		Assert.assertEquals(sMessSucess, "Thank you for registering with Main Website Store.");
		
		// Step 07 - Logout khỏi hệ thống
		driver.findElement(By.xpath("//div[@class='page-header-container']//span[text()='Account']")).click();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		driver.findElement(By.xpath("//a[@title='Log Out']")).click();
		driver.manage().timeouts().implicitlyWait(320, TimeUnit.SECONDS);
		
		// Step 08 - Kiểm tra hệ thống navigate về Home page sau khi logout thành công

		String homepageTitle = driver.getTitle();
		Assert.assertEquals(homepageTitle, "Home page");

		String urlLoginPage = driver.getCurrentUrl();
		Assert.assertEquals(urlLoginPage,"http://live.guru99.com/index.php/");		

	}	
	

	
	@Test(enabled=false)
	public void TC_01_CheckDisplay() {

		driver.get("https://daominhdam.github.io/basic-form/");
		WebElement Email = driver.findElement(By.xpath("//input[@type='email']"));
		if(Email.isDisplayed()) {
			Email.sendKeys("Automation Testing");
		}
		
		WebElement age_Under18 = driver.findElement(By.xpath("//input[@id='under_18']"));
		if(age_Under18.isDisplayed()) {
			age_Under18.click();
		}
		
		WebElement Education = driver.findElement(By.xpath("//textarea[@id='edu']"));
		if(Education.isDisplayed()) {
			Education.sendKeys("Automation Testing");
		}
		
	}
	
	@Test(enabled=false)
	public void TC_02_Check_EnableDisabled() {

		driver.get("https://daominhdam.github.io/basic-form/");
		
		WebElement Email = driver.findElement(By.xpath("//input[@type='email']"));
		
		WebElement age_Under18 = driver.findElement(By.xpath("//input[@id='under_18']"));
		
		WebElement Education = driver.findElement(By.xpath("//textarea[@id='edu']"));
		
		WebElement Job1 = driver.findElement(By.xpath("//select[@id='job1']"));
		
		WebElement Interests_Development = driver.findElement(By.xpath("//input[@id='development']"));
		
		WebElement Slide_01 = driver.findElement(By.xpath("//input[@id='slider-1']"));
		
		WebElement btnEnable = driver.findElement(By.xpath("//button[@id='button-enabled']"));
		
		isElementEnable(Email);
		isElementEnable(age_Under18);
		isElementEnable(Education);
		isElementEnable(Job1);
		isElementEnable(Interests_Development);
		isElementEnable(Slide_01);
		isElementEnable(btnEnable);
		
		//Step 03 - Kiểm tra các phần tử sau disable trên trang
		
		WebElement Password = driver.findElement(By.xpath("//input[@id='password']"));		

		WebElement radDisabled = driver.findElement(By.xpath("//input[@id='radio-disabled']"));		
		
		WebElement Biography = driver.findElement(By.xpath("//textarea[@id='bio']"));		
		
		WebElement Job2 = driver.findElement(By.xpath("//select[@id='job2']"));		
		
		WebElement Slide_02 = driver.findElement(By.xpath("//input[@id='slider-2']"));
		
		WebElement btnDisabled = driver.findElement(By.xpath("//button[@id='button-disabled']"));
		
		isElementDisabled(Password);
		isElementDisabled(radDisabled);
		isElementDisabled(Biography);
		isElementDisabled(Job2);
		isElementDisabled(Slide_02);
		isElementDisabled(btnDisabled);
		
	}
	
	@Test
	public void TC_03_Check_Clicked() {

		driver.get("https://daominhdam.github.io/basic-form/");
		
		WebElement age_Under18 = driver.findElement(By.xpath("//input[@id='under_18']"));
		age_Under18.click();
		
		WebElement Interests_Development = driver.findElement(By.xpath("//input[@id='development']"));
		Interests_Development.click();
		
		// Check selected
		isElementSelected(age_Under18);
		isElementSelected(Interests_Development);

		
		//System.out.println(Email);
	}
	@Test(enabled=false)

	public void TC_Lession_WebBrowserCommands() {
		/* BẤT KÌ COMMAND NÀO CÓ TIỀN TỐ GET ĐỀU PHẢI TRẢ VỀ GIÁ TRỊ
		 * CHỈ CÓ DUY NHẤT driver.get THÌ KHÔNG TRẢ VỀ GIÁ TRỊ
		 */
		// Navigate to page
		driver.get("www.live.guru99.com");
		
		// Only close current tab - If browser opens one tab = quit
		driver.close();
		
		// Close browser - all tabs
		driver.quit();
		
		// Get current link
		String homepageURL = driver.getCurrentUrl();
		System.out.println(homepageURL);
		
		//Get Page Source
		String homepageSource = driver.getPageSource();
		System.out.println(homepageURL);
		// Compare text on Page Source
		Assert.assertTrue(homepageSource.contains("Your Basic Info"));
		
		//Get Page Title
		String homepageTitle = driver.getTitle();
		// Compare Tile on Page Tile
		Assert.assertEquals(homepageTitle, "SELENIUM WEBDRIVER FORM DEMO");
		
		// Handle window/ pop-up
		driver.getWindowHandle();
		driver.getWindowHandles();
		
		// Wait for element được xuất hiện trong 30s
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		// Wait for page load thành công trong 30s
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		
		// Mở trình duyệt full toàn màn hình (F11)
		driver.manage().window().fullscreen();
		
		//Mở cửa sổ của browser
		driver.manage().window().maximize();
		
		// Cookies
		driver.manage().deleteAllCookies();
		driver.manage().getCookies();
		driver.manage().getCookieNamed("");
		
		// Đi tới trang tiếp theo
		driver.navigate().forward();
		// Back lại trang trước
		driver.navigate().back();
		// Tải lại trang
		driver.navigate().refresh();
		
		// Alert
		driver.switchTo().alert();
		// Iframe
		driver.switchTo().frame("");

	
	}
	
	@Test(enabled=false)
	
	public void TC_Lession_WebElementCommands() {
		
		// Thao tác trên 1 element
		driver.findElement(By.xpath("//input[@id='mail']")).sendKeys("giangthihathanh@gmail.com");
		driver.findElement(By.xpath("//input[@id='mail']")).clear();
		driver.findElement(By.xpath("//input[@id='mail']")).click();
		
		// Thao tác với 1 list elements
		driver.findElements(By.xpath("")).get(1);
		
		// Khai báo element để sử dụng nhiều lần
		WebElement emailTextbox = driver.findElement(By.xpath("//input[@id='mail']"));
		WebElement passTextbox = driver.findElement(By.xpath("//input[@id='pass']"));
		WebElement loginButton = driver.findElement(By.xpath("//button[@id='send2']"));
		
		
		// Thao tác trên biến đã khai báo
			// Scenario 1: Invalid email
		emailTextbox.clear();
		emailTextbox.sendKeys("giangthihathanh@.com");
		loginButton.click();
		
		// Get Attribute of an element
		String searchPlaceholder = emailTextbox.getAttribute("placeholder");
		String loginbuttonBackgroundColor = loginButton.getCssValue("background");
		
		// Lấy ra vị trí của element nằm trong độ phân giải màn hình
		emailTextbox.getLocation();
		
		// Lấy chiều rộng chiều/ chiều cao của màn hình
		emailTextbox.getSize();
		
		// Get tagname
		emailTextbox.getTagName();
		
		// Get text
		WebElement emailErrorMessage = driver.findElement(By.xpath("//*[@id='advice-required-entry-email']"));
		String emailError = emailErrorMessage.getText();
		Assert.assertEquals(emailError, "This is required field");
		
		// Check Display for all type of element
		Assert.assertTrue(emailTextbox.isDisplayed());
		
		// Check enable/ disable for textbox/ textarea/ checkbox/ radio button/ dropdown
		Assert.assertTrue(emailTextbox.isEnabled());
		Assert.assertFalse(emailTextbox.isEnabled());
		
		// Check selected for checkbox/ radio button
		Assert.assertTrue(emailTextbox.isSelected());
		Assert.assertFalse(emailTextbox.isSelected());
		
		// Enter
		emailTextbox.submit();
		
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	public int randomData() {
		Random random = new Random() ;
		int number = random.nextInt(999999999);
		return number;
		
	}
	
	public void isElementEnable(WebElement element) {
		if (element.isEnabled()) {
			System.out.println("Element is enable");
		} else {
			System.out.println("Element is NOT enable");
		}
	}
	
	public void isElementDisabled(WebElement element) {
		if (element.isEnabled()) {
			System.out.println("Element is disabled");
		} else {
			System.out.println("Element is NOT disabled");
		}
	}
	
	public void isElementSelected(WebElement element) {
		if (element.isSelected()) {
			System.out.println("Element is selected");
		} else {
			element.click();
		}
	}

}
