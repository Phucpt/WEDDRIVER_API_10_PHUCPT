package Selenium;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.thoughtworks.selenium.Selenium;

import sun.launcher.resources.launcher;

public class Topic_04_textbox_textarea_dropdown {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\Phuc\\Downloads\\chromedriver_win32_74\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("http://demo.guru99.com/v4/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void TC_01_Textbox_Textarea() {
		driver.get("http://demo.guru99.com/v4/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		// random email
		Random random = new Random();
		String randomEmail = "phucautmation" + random.nextInt(999999) + "@gmail.com";

		// login
		driver.findElement(By.xpath("/html/body/form/table/tbody/tr[1]/td[2]/input")).sendKeys("mngr196719");
		driver.findElement(By.xpath("/html/body/form/table/tbody/tr[2]/td[2]/input")).sendKeys("qAnUdar");
		driver.findElement(By.xpath("/html/body/form/table/tbody/tr[3]/td[2]/input[1]")).click();
		// verify home page text
		String homepage = driver.findElement(By.xpath("/html/body/div[2]/h2")).getText();
		Assert.assertEquals(homepage, "Guru99 Bank");

		// navigate to new customer
		driver.findElement(By.xpath("//a[text()='New Customer']")).click();

		// input all field
		driver.findElement(By.xpath("//input[@value='m']")).click();
		driver.findElement(By.xpath("html/body/table/tbody/tr/td/table/tbody/tr[4]/td[2]/input"))
				.sendKeys("Selenium online");
		driver.findElement(By.id("dob")).sendKeys("10/01/2000");
		driver.findElement(By.xpath("//textarea[@name='addr']")).sendKeys("123 Address");
		driver.findElement(By.xpath("//input[@name='city']")).sendKeys("Ho Chi Minh");
		driver.findElement(By.xpath("//input[@name='state']")).sendKeys("Thu Duc");
		driver.findElement(By.xpath("//input[@name='pinno']")).sendKeys("123456");
		driver.findElement(By.xpath("//input[@name='telephoneno']")).sendKeys("0123456978");
		driver.findElement(By.xpath("//input[@name='emailid']")).sendKeys(randomEmail);
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("qAnUdar");

		// submit
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		// get information Customer ID
		String customerID = driver.findElement(By.xpath("//td[text()='Customer ID']/following-sibling::td")).getText();

		// Verify all information register
		String customerName = driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td"))
				.getText();
		Assert.assertEquals(customerName, "Selenium online");
		String dateOfBirth = driver.findElement(By.xpath("//td[text()='Birthdate']/following-sibling::td")).getText();
		Assert.assertEquals(dateOfBirth, "2000-10-01");
		String address = driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText();
		Assert.assertEquals(address, "123 Address");
		String city = driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText();
		Assert.assertEquals(city, "Ho Chi Minh");
		String sate = driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText();
		Assert.assertEquals(sate, "Thu Duc");
		String pin = driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText();
		Assert.assertEquals(pin, "123456");
		String telephone = driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText();
		Assert.assertEquals(telephone, "0123456978");
		String email = driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText();
		Assert.assertEquals(email, randomEmail);

		// Select Edit customer--> input CustomerID-->click button submit
		driver.findElement(By.xpath("//a[text()='Edit Customer']")).click();
		driver.findElement(By.xpath("html/body/div[2]/table/tbody/tr/td/table/tbody/tr[6]/td[2]/input"))
				.sendKeys(customerID);
		driver.findElement(By.xpath("html/body/div[2]/table/tbody/tr/td/table/tbody/tr[11]/td[2]/input[1]")).click();

		// Verify value at 2 field Customer name, address
		String editCustomeName = driver.findElement(By.xpath("html/body/table/tbody/tr/td/table/tbody/tr[4]/td[2]/input"))
				.getAttribute("value");
		Assert.assertEquals(editCustomeName, "Selenium online");
		
		String addresss = driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td/textarea"))
				.getText();
		Assert.assertEquals(addresss, "123 Address");
		
		

		//edit page constant
		String editAddressXpath = "html/body/table/tbody/tr/td/table/tbody/tr[7]/td[2]/textarea";
		String editCityXpath = "html/body/table/tbody/tr/td/table/tbody/tr[8]/td[2]/input";
		String editStateXpath = "html/body/table/tbody/tr/td/table/tbody/tr[9]/td[2]/input";
		String editPhoneNumberXpath = "html/body/table/tbody/tr/td/table/tbody/tr[11]/td[2]/input";
		String editPinXpath = "html/body/table/tbody/tr/td/table/tbody/tr[10]/td[2]/input";
		String editEmailXpath = "html/body/table/tbody/tr/td/table/tbody/tr[12]/td[2]/input";
		
		
		// Edit filed enable
		WebElement addressElement = driver.findElement(By.xpath(editAddressXpath));
		addressElement.clear();
		addressElement.sendKeys("234 Edit Address");
		
		WebElement cityElement = driver.findElement(By.xpath(editCityXpath));
		cityElement.clear();
		cityElement.sendKeys("Edit Ho Chi Minh");
		
		
		WebElement stateElement  = driver.findElement(By.xpath(editStateXpath));
		stateElement.clear();
		stateElement.sendKeys("Edit Thu Duc");
		
		
		WebElement phoneNumberElement = driver.findElement(By.xpath(editPhoneNumberXpath));
		phoneNumberElement.clear();
		phoneNumberElement.sendKeys("9876543221");
		
		
		WebElement pinElement = driver.findElement(By.xpath(editPinXpath));
		pinElement.clear();
		pinElement.sendKeys("654321");
		
		
		WebElement emailElement = driver.findElement(By.xpath(editEmailXpath));
		emailElement.clear();
		emailElement.sendKeys("selenium7890@gmail.com");
		
		driver.findElement(By.xpath("html/body/table/tbody/tr/td/table/tbody/tr[13]/td[2]/input[1]")).click();
	

		// Verify filed input
		String address1 = driver.findElement(By.xpath(".//*[@id='customer']/tbody/tr[8]/td[2]")).getText();
		Assert.assertEquals(address1, "234 Edit Address");
		String city2 = driver.findElement(By.xpath(".//*[@id='customer']/tbody/tr[9]/td[2]")).getText();
		Assert.assertEquals(city2, "Edit Ho Chi Minh");
		String state1 = driver.findElement(By.xpath(".//*[@id='customer']/tbody/tr[10]/td[2]")).getText();
		Assert.assertEquals(state1, "Edit Thu Duc");
		String pin1 = driver.findElement(By.xpath(".//*[@id='customer']/tbody/tr[11]/td[2]")).getText();
		Assert.assertEquals(pin1, "654321");
		String mobile1 = driver.findElement(By.xpath(".//*[@id='customer']/tbody/tr[12]/td[2]")).getText();
		Assert.assertEquals(mobile1, "9876543221");
		String email1 = driver.findElement(By.xpath(".//*[@id='customer']/tbody/tr[13]/td[2]")).getText();
		Assert.assertEquals(email1, "selenium7890@gmail.com");
		
		

	}
	

	@AfterClass
	public void afterClass() {
		driver.close();
	}

}
