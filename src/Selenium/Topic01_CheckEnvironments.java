package Selenium;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic01_CheckEnvironments {
	WebDriver driver;

  @BeforeClass
  public void beforeClass() {
	 String url = "https://www.guru99.com/";
	 
    driver = new FirefoxDriver();
    driver.get(url);
    driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
  }
  
  @Test
  public void TC_01_checkUrl() {
	  String homePageUrl = driver.getCurrentUrl();
	  String strToPrint = "Home page url is: " + homePageUrl;
	  System.out.println(strToPrint);
	
	  Assert.assertEquals(homePageUrl, "https://www.guru99.com/");
	  
  }
  
  @Test
  public void TC_03_checkTitle() {
	  String homePageTitle = driver.getTitle();
	  String expectedUrl = "Meet Guru99 - Free Training Tutorials & Video for IT Courses";
	  Assert.assertEquals(homePageTitle, expectedUrl);
	  System.out.println("title is " + homePageTitle);
	  
  }
  


  @AfterClass
  public void afterClass() {
	  driver.close();
  }

}
