package Selenium;

import java.awt.Frame;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.server.handler.GetAllWindowHandles;
import org.openqa.selenium.remote.server.handler.SwitchToFrame;
import org.openqa.selenium.remote.server.handler.SwitchToWindow;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import bsh.commands.dir;
import sun.management.counter.Variability;
import sun.reflect.generics.tree.Tree;

import java.util.List;

public class Topic07_PopupIfameWindow {
	WebDriver driver;
	Actions actions;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\Phuc\\Downloads\\chromedriver_win32_74\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}

	@Test
	public void TC01Iframe() throws Exception {
		driver.get("https://www.hdfcbank.com/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		List<WebElement> popupImages = driver
				.findElements(By.xpath("//div[@id='parentdiv']//img[@class='popupbanner at-element-click-tracking']"));

		System.out.println("Popup displayed " + popupImages.size());

		if (popupImages.get(0).isDisplayed()) {
			driver.findElement(By.xpath(".//*[@id='parentdiv']//img[@class='popupCloseButton']")).click();
			Thread.sleep(2000);
			Assert.assertFalse(popupImages.get(0).isDisplayed());
		}

		WebElement lookingForIframe = driver.findElement(By.xpath("//iframe[starts-with(@id, 'viz_iframe')]"));
		driver.switchTo().frame(lookingForIframe);
		Assert.assertEquals(driver.findElement(By.xpath("//span[@id='messageText']")).getText(),
				"What are you looking for?");
		
		driver.switchTo().defaultContent();
		
		List<WebElement> rightBannerImages = driver
				.findElements(By.xpath("//div[@id='rightbanner']//div[@class='owl-item']//img"));
		
		Thread.sleep(2000);
		Assert.assertEquals(rightBannerImages.size(), 6);

		List<WebElement> flipBanner = driver
				.findElements(By.xpath("//div[@class='flipBanner']//img[@class='front icon']"));
		
		Thread.sleep(2000);
		Assert.assertEquals(flipBanner.size(), 8);
	}

	@Test
	public void TC02Windown() throws Exception {
		driver.get("https://daominhdam.github.io/basic-form/index.html");
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		String parentID = driver.getWindowHandle();
		System.out.println("Parent ID = " + parentID);

		driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();

		switchToByTitle("Google");
		Assert.assertEquals(driver.getTitle(), "Google");

		switchToByTitle("SELENIUM WEBDRIVER FORM DEMO");
		Assert.assertEquals(driver.getTitle(), "SELENIUM WEBDRIVER FORM DEMO");

		driver.findElement(By.xpath("//a[text()='FACEBOOK']")).click();
		switchToByTitle("Facebook - Đăng nhập hoặc đăng ký");

		switchToByTitle("SELENIUM WEBDRIVER FORM DEMO");
		Assert.assertEquals(driver.getTitle(), "SELENIUM WEBDRIVER FORM DEMO");

		driver.findElement(By.xpath("//a[text()='TIKI']")).click();
		switchToByTitle("Mua Hàng Trực Tuyến Uy Tín với Giá Rẻ Hơn tại Tiki.vn");

		closeAllWindownWsWithoutParent(parentID);

		switchToByTitle("SELENIUM WEBDRIVER FORM DEMO");
		Assert.assertEquals(driver.getTitle(), "SELENIUM WEBDRIVER FORM DEMO");

	}

	@Test
	public void TC03() throws Exception {
		driver.get("https://www.hdfcbank.com/");
		String parentId = driver.getWindowHandle();
		List<WebElement> popupImages = driver
				.findElements(By.xpath("//div[@id='parentdiv']//img[@class='popupbanner at-element-click-tracking']"));

		System.out.println("Popup displayed " + popupImages.size());

		if (popupImages.get(0).isDisplayed()) {
			driver.findElement(By.xpath(".//*[@id='parentdiv']//img[@class='popupCloseButton']")).click();
			Thread.sleep(2000);
			Assert.assertFalse(popupImages.get(0).isDisplayed());
		}

		driver.findElement(By.xpath("//a[text()='Agri']")).click();
		switchToByTitle("HDFC Bank Kisan Dhan Vikas e-Kendra");

		driver.findElement(By.xpath("//a[@target='_blank']//p[text()='Account Details']")).click();
		switchToByTitle("Welcome to HDFC Bank NetBanking");

		driver.switchTo().frame(driver.findElement(By.xpath("//frame[@name='footer']")));
		driver.findElement(By.xpath("//a[text()='Privacy Policy']")).click();
		switchToByTitle(
				"HDFC Bank - Leading Bank in India, Banking Services, Private Banking, Personal Loan, Car Loan");

		driver.findElement(By.xpath("//a[text()='CSR']")).click();

		closeAllWindownWsWithoutParent(parentId);

	}
 
	@Test
	public void TC04() {
		driver.get("http://live.guru99.com/index.php/");
		String parentId = driver.getWindowHandle();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	
		
		driver.findElement(By.xpath("//a[text()='Mobile']")).click();
		
		clickProductAddToCompareButton("Sony Xperia");
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		Assert.assertTrue(verifyProductAddToCompareSuccess("Sony Xperia"));
		Assert.assertEquals(verifyProductAddToCompareSuccess("Sony Xperia"), true);
		Assert.assertEquals(getTextProductAddToComparesuccessMsg(), "The product Sony Xperia has been added to comparison list.");
				
		clickProductAddToCompareButton("Samsung Galaxy");
		Assert.assertTrue(verifyProductAddToCompareSuccess("Samsung Galaxy"));
		Assert.assertEquals(verifyProductAddToCompareSuccess("Samsung Galaxy"), true);
		Assert.assertEquals(getTextProductAddToComparesuccessMsg(), "The product Samsung Galaxy has been added to comparison list.");
		
		driver.findElement(By.xpath("//button[@title='Compare']")).click();
		switchToByTitle("Products Comparison List - Magento Commerce");
		Assert.assertEquals(driver.getTitle(), "Products Comparison List - Magento Commerce");

		closeAllWindownWsWithoutParent(parentId);
		
	}
	
	public String getTextProductAddToComparesuccessMsg () {
		WebElement element = driver.findElement(By.xpath("//li[@class='success-msg']//span"));
		String successText = element.getText(); 
		return successText;
	}
	
	public void clickProductAddToCompareButton (String productName) {
		driver.findElement(By.xpath("//a[@title='"+ productName + "']/parent::h2/following-sibling::div[@class='actions']//a[text()='Add to Compare']")).click();;
	}
	
	public boolean verifyProductAddToCompareSuccess (String productname) {
		WebElement element = driver.findElement(By.xpath("//li[@class='success-msg']//span[text()='The product "+ productname + " has been added to comparison list.']"));
		return element.isDisplayed();
	}
	
	
	public void SwitchTochildByID(String parent) {
		Set<String> allWindow = driver.getWindowHandles();

		for (String runWindown : allWindow) {
			if (!runWindown.equals(parent)) {
				driver.switchTo().window(runWindown);
				break;
			}
		}

	}

	public void closeAllWindownWsWithoutParent(String parentID) {

		Set<String> allWindows = driver.getWindowHandles();

		for (String runWindown : allWindows) {
			if (!runWindown.equals(parentID)) {
				driver.switchTo().window(runWindown);
				driver.close();
			}
		}
		driver.switchTo().window(parentID);
	
	
	}

	public void switchToByTitle(String title) {
		Set<String> allWindows = driver.getWindowHandles();

		for (String runWindown : allWindows) {

			driver.switchTo().window(runWindown);

			String currentWin = driver.getTitle();

			if (currentWin.equals(title)) {
				break;

			}
		}
	}

	@AfterClass
	public void afterClass() {
		driver.close();
	}
	

}
