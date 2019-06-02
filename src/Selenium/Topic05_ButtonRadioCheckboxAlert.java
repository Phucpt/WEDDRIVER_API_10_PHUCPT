package Selenium;

import java.sql.Driver;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic05_ButtonRadioCheckboxAlert {
	WebDriver driver;
	JavascriptExecutor javaExecutor;
	Alert alert;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\Phuc\\Downloads\\chromedriver_win32_74\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		javaExecutor = (JavascriptExecutor) driver;

	}

	@Test
	public void TC01_JavascriptExecutor() {
		driver.get("http://live.guru99.com/");

		WebElement myAccountLink = driver
				.findElement(By.xpath(".//*[@id='top']/body/div[1]/div/div[3]/div/div[4]/ul/li[1]/a"));
		javaExecutor.executeScript("arguments[0].click()", myAccountLink);
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.guru99.com/index.php/customer/account/login/");

		WebElement createAccountLink = driver
				.findElement(By.xpath(".//*[@id='login-form']/div/div[1]/div[2]/a/span/span"));
		javaExecutor.executeScript("arguments[0].click()", createAccountLink);
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.guru99.com/index.php/customer/account/create/");

	}

	@Test
	public void TC02_Checkbox() throws Exception {
		driver.get("https://demos.telerik.com/kendo-ui/styling/checkboxes");

		WebElement dualZoneCheckbox = driver
				.findElement(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input"));

		javaExecutor.executeScript("arguments[0].click()", dualZoneCheckbox);
		Thread.sleep(2000);
		Assert.assertTrue(dualZoneCheckbox.isSelected());

		javaExecutor.executeScript("arguments[0].click()", dualZoneCheckbox);
		Thread.sleep(2000);
		Assert.assertFalse(dualZoneCheckbox.isSelected());
	}

	@Test
	public void TC03_RadioButton() throws Exception {
		driver.get("https://demos.telerik.com/kendo-ui/styling/radios");

		WebElement petrol147kWCheckbox = driver
				.findElement(By.xpath("//label[text()='2.0 Petrol, 147kW']/preceding-sibling::input"));
		javaExecutor.executeScript("arguments[0].click()", petrol147kWCheckbox);
		Thread.sleep(2000);
		Assert.assertTrue(petrol147kWCheckbox.isSelected());
	}

	@Test
	public void TC04_AcceptAlert() {
		driver.get("https://daominhdam.github.io/basic-form/index.html");

		driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
		alert = driver.switchTo().alert();
		Assert.assertEquals(alert.getText(), "I am a JS Alert");
		alert.accept();
		Assert.assertEquals(driver.findElement(By.xpath("//p[@id='result']")).getText(),
				"You clicked an alert successfully");
	}

	@Test
	public void TC05_ConfirmAlert() {
		driver.get("https://daominhdam.github.io/basic-form/index.html");

		driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
		alert = driver.switchTo().alert();
		Assert.assertEquals(alert.getText(), "I am a JS Confirm");

		alert.dismiss();
		Assert.assertEquals(driver.findElement(By.xpath("//p[@id='result']")).getText(), "You clicked: Cancel");
	}

	@Test
	public void TC06_PromptAlert() {
		driver.get("https://daominhdam.github.io/basic-form/index.html");

		driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
		alert = driver.switchTo().alert();
		Assert.assertEquals(alert.getText(), "I am a JS prompt");

		alert.sendKeys("daominhdam");
		alert.accept();
		Assert.assertEquals(driver.findElement(By.xpath("//p[@id='result']")).getText(), "You entered: daominhdam");
	}

	@Test
	public void TC07_AuthenticationAlert() {
		String url = "http://the-internet.herokuapp.com/basic_auth";
		url = "http://admin:admin@the-internet.herokuapp.com/basic_auth";
		driver.get(url);
		Assert.assertEquals(driver.findElement(By.xpath("//h3[text()='Basic Auth']/following-sibling::p")).getText(), "Congratulations! You must have the proper credentials.");

	}

	@AfterClass
	public void afterClass() {
		driver.close();
	}

}
