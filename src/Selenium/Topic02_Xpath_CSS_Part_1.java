package Selenium;

import static org.junit.Assert.assertEquals;

import java.util.Random;

//import static org.junit.Assert.assertEquals;

//import static org.junit.Assert.assertArrayEquals;

import java.util.concurrent.TimeUnit;

import org.eclipse.jetty.util.security.Password;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.server.handler.SendKeys;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.sun.xml.internal.bind.v2.model.core.ID;
import com.sun.xml.internal.ws.model.ReflectAnnotationReader;

//import junit.framework.Assert;

public class Topic02_Xpath_CSS_Part_1 {
	WebDriver driver;
	String email;
	

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Phuc\\Downloads\\chromedriver_win32_74\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("http://live.guru99.com/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
	}

	@Test
	public void TC_01_Login_empty() {
		driver.findElement(By.xpath("//*[@id=\"top\"]/body/div/div/div[3]/div/div[4]/ul/li[1]/a")).click();

		driver.findElement(By.id("email")).sendKeys("");
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("");
		driver.findElement(By.id("send2")).click();
		String userErroMessage = driver.findElement(By.id("advice-required-entry-email")).getText();
		String passwordErroMessager = driver.findElement(By.id("advice-required-entry-pass")).getText();
		Assert.assertEquals("This is a required field.", userErroMessage);
		Assert.assertEquals("This is a required field.", passwordErroMessager);
	}

	@Test
	public void TC_02_Login_With_Email_Invalid() {
		driver.findElement(By.xpath("//*[@id=\"top\"]/body/div/div/div[3]/div/div[4]/ul/li[1]/a")).click();
		driver.findElement(By.id("email")).sendKeys("123455@12223.222");
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("1234567");
		driver.findElement(By.id("send2")).click();
		String userErrorMessage = driver.findElement(By.id("advice-validate-email-email")).getText();
		Assert.assertEquals("Please enter a valid email address. For example johndoe@domain.com.", userErrorMessage);
	}
	
	@Test
	public void TC_03_Login_with_password_smaller_6character() {
		driver.findElement(By.xpath("//*[@id=\"top\"]/body/div/div/div[3]/div/div[4]/ul/li[1]/a")).click();
		driver.findElement(By.id("email")).sendKeys("automation@gmail.com");
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("123");
		driver.findElement(By.id("send2")).click();
		String passwordErrorMessage = driver.findElement(By.id("advice-validate-password-pass")).getText();
		Assert.assertEquals("Please enter 6 or more characters without leading or trailing spaces.", passwordErrorMessage);
	}
	
	@Test
	public void TC_04_Login_with_password_incorrect() {
		driver.findElement(By.xpath("//*[@id=\"top\"]/body/div/div/div[3]/div/div[4]/ul/li[1]/a")).click();
		driver.findElement(By.id("email")).sendKeys("automation@gmail.com");
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("123123123");
		driver.findElement(By.id("send2")).click();
		} 
	
	@Test
	public void TC_05_Create_an_Account() {
		Random random = new Random();
		email = "autmationtest" + random.nextInt(999999) + "@gmail.com";
		
		driver.findElement(By.xpath("//*[@id=\"top\"]/body/div/div/div[3]/div/div[4]/ul/li[1]/a")).click();
		driver.findElement(By.xpath("//*[@id=\"login-form\"]/div/div[1]/div[2]/a/span/span")).click();
		driver.findElement(By.xpath("//input[@id='firstname']")).sendKeys("Hang");
		driver.findElement(By.xpath("//input[@id='middlename']")).sendKeys("Van");
		driver.findElement(By.xpath("//input[@id='lastname']")).sendKeys("hoang");
		driver.findElement(By.xpath("//input[@id='email_address']")).sendKeys(email);
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys("123123");
		driver.findElement(By.xpath("//input[@id='confirmation']")).sendKeys("123123");
		driver.findElement(By.xpath("//button[@title='Register']")).click();
		String successfullyMessage = driver.findElement(By.xpath("//*[@id=\"top\"]/body/div/div/div[2]/div/div[2]/div/div/ul/li/ul/li/span")).getText();
	    Assert.assertEquals(successfullyMessage, "Thank you for registering with Main Website Store.");
	    driver.findElement(By.xpath("//*[@id=\"header\"]/div/div[2]/div/a/span[2]")).click();
	    driver.findElement(By.xpath("//*[@id=\"header-account\"]/div/ul/li[5]/a")).click();
	    Assert.assertTrue(driver.findElement(By.xpath("//*[@id=\"top\"]/body/div/div/div[2]/div/div[1]/div/div/h2")).isDisplayed());
	    
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
