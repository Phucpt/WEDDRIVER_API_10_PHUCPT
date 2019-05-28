package Selenium;

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

public class Topic04Dropdown {
	
	WebDriver driver;


	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\Phuc\\Downloads\\chromedriver_win32_74\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://daominhdam.github.io/basic-form/index.html");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void TC_02_HTML_Dropdownlist() {

		// Check dropdown list Job Role 01 is Single
		WebElement jobRole01DropdownList = driver.findElement(By.id("job1"));
		Select select = new Select(jobRole01DropdownList);
		Assert.assertFalse(select.isMultiple());

		select.selectByVisibleText("Automation Tester");
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Automation Tester");
		select.selectByValue("manual");

		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Manual Tester");
		select.selectByIndex(3);

		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Mobile Tester");
		Assert.assertEquals(5, select.getOptions().size());
	}

	@AfterClass
	public void afterClass() {
		driver.close();
	}

}
