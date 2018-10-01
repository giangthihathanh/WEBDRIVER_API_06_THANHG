package selenium_api;

import org.testng.annotations.Test;

import org.testng.annotations.BeforeClass;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.server.handler.ImplicitlyWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_04_Handle_Textbox_TextArea {
	WebDriver driver;
	String sCustomerID;
	String customerName;
	String dateofbirth;
	String address;
	String city;
	String state;
	String pinNO;
	String phoneNO;
	String email;
	String password;
	String gender;
	String editAddress;
	String editCity;
	String editState;
	String editPinNO;
	String editPhoneNO;
	String editEmail;


	@BeforeClass
	public void beforeClass() {
		// Start Browser
		driver = new FirefoxDriver();

		// User ID : mngr155855
		// Password : YpYmYhY
		driver.get("http://demo.guru99.com/v4/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		customerName = "Thanh";
		gender = "female";
		dateofbirth = "1991-11-11";
		address = "440 Nguyen Kiem";
		city = "Ho Chi Minh";
		state = "None";
		pinNO = "123456";
		phoneNO = "0908888888";
		email = "selenium06" + randomData() + "@gmail.com";
		password = "1234567890";
		
		editAddress = "446 Nguyen Kiem";
		editCity = "HCM";
		editState = "Khong biet";
		editPinNO = "654321";
		editPhoneNO = "0908888880";
		editEmail = "selenium06_auto" + randomData() + "@gmail.com";
	}

	@Test
	public void TC_01_NewCustomer() {
		// Navigate to page
		driver.findElement(By.xpath("//input[@name='uid']")).sendKeys("mngr155855");
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("YpYmYhY");
		driver.findElement(By.xpath("//input[@name='btnLogin']")).click();

		// Verify Login successfully
		Assert.assertTrue(driver.findElement(By.xpath("//marquee[text()=\"Welcome To Manager's Page of Guru99 Bank\"]")).isDisplayed());

		// Click on New Customer option
		driver.findElement(By.xpath("//a[text()='New Customer']")).click();

		// Create New Customer
		driver.findElement(By.xpath("//input[@name='name']")).sendKeys(customerName);
		driver.findElement(By.xpath("//input[@value='f']")).click();
		driver.findElement(By.xpath("//input[@name='dob']")).sendKeys(dateofbirth);
		driver.findElement(By.xpath("//textarea[@name='addr']")).sendKeys(address);
		driver.findElement(By.xpath("//input[@name='city']")).sendKeys(city);
		driver.findElement(By.xpath("//input[@name='state']")).sendKeys(state);
		driver.findElement(By.xpath("//input[@name='pinno']")).sendKeys(pinNO);
		driver.findElement(By.xpath("//input[@name='telephoneno']")).sendKeys(phoneNO);
		driver.findElement(By.xpath("//input[@name='emailid']")).sendKeys(email);
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys(password);
		driver.findElement(By.xpath("//input[@name='sub']")).click();

		// Verify create customer successfully message
		Assert.assertTrue(driver.findElement(By.xpath("//p[text()='Customer Registered Successfully!!!']")).isDisplayed());

		sCustomerID = driver.findElement(By.xpath("//td[text()='Customer ID']/following-sibling::td")).getText();
		System.out.println("Customer ID = " + sCustomerID);

		// Verify information of new created customer
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")).getText(), customerName);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Gender']/following-sibling::td")).getText(), gender);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Birthdate']/following-sibling::td")).getText(), dateofbirth);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(), address);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText(), city);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText(), state);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText(), pinNO);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText(), phoneNO);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText(), email);

	}

	@Test
	public void TC_02_EditCustomer() {
		// Click on Edit Customer option
		driver.findElement(By.xpath("//a[text()='Edit Customer']")).click();
		driver.findElement(By.xpath("//input[@name='cusid']")).sendKeys(sCustomerID);
		driver.findElement(By.xpath("//input[@name='AccSubmit']")).click();
		
		// Verify Edit Customer page is displayed
		Assert.assertTrue(driver.findElement(By.xpath("//p[text()='Edit Customer']")).isDisplayed());
		
		// Verify Customer 
		Assert.assertEquals(driver.findElement(By.xpath("//input[@name='name']")).getAttribute("value"), customerName);
		Assert.assertEquals(driver.findElement(By.xpath("//textarea[@name='addr']")).getText(), address);
		
		// Edit Info
		driver.findElement(By.xpath("//textarea[@name='addr']")).clear();
		driver.findElement(By.xpath("//input[@name='city']")).clear();
		driver.findElement(By.xpath("//input[@name='state']")).clear();
		driver.findElement(By.xpath("//input[@name='pinno']")).clear();
		driver.findElement(By.xpath("//input[@name='telephoneno']")).clear();
		driver.findElement(By.xpath("//input[@name='emailid']")).clear();

		driver.findElement(By.xpath("//textarea[@name='addr']")).sendKeys(editAddress);
		driver.findElement(By.xpath("//input[@name='city']")).sendKeys(editCity);
		driver.findElement(By.xpath("//input[@name='state']")).sendKeys(editState);
		driver.findElement(By.xpath("//input[@name='pinno']")).sendKeys(editPinNO);
		driver.findElement(By.xpath("//input[@name='telephoneno']")).sendKeys(editPhoneNO);
		driver.findElement(By.xpath("//input[@name='emailid']")).sendKeys(editEmail);
		driver.findElement(By.xpath("//input[@name='sub']")).click();

		// Verify update customer successfully message
		Assert.assertTrue(driver.findElement(By.xpath("//p[text()='Customer details updated Successfully!!!']")).isDisplayed());

		// Verify information of updated customer
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(), editAddress);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText(), editCity);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText(), editState);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText(), editPinNO);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText(), editPhoneNO);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText(), editEmail);


	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public int randomData() {
		Random random = new Random();
		int number = random.nextInt(999999999);
		return number;

	}

}
