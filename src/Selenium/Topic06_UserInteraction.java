package Selenium;

import java.sql.Driver;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.TargetLocator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.sun.prism.impl.Disposer.Target;

import jdk.nashorn.internal.scripts.JS;

import java.util.List;

public class Topic06_UserInteraction {
	WebDriver driver;
	Actions actions;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\Phuc\\Downloads\\chromedriver_win32_74\\chromedriver.exe");
		driver = new ChromeDriver();
		actions = new Actions(driver);

	}

	@Test
	public void TC01_MoveMoseToElement() {
		driver.get("https://www.myntra.com/");

		WebElement profileText = driver.findElement(By.xpath("//span[text()='Profile']"));
		actions.moveToElement(profileText).perform();

		WebElement loginButton = driver.findElement(By.xpath("//a[text()='log in']"));
		Assert.assertTrue(loginButton.isDisplayed());
		loginButton.click();

		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='login-box']")).isDisplayed());

	}

	@Test
	public void TC02_clickAndHoldElementSelectMultipleIem() throws Exception {
		driver.get("http://jqueryui.com/resources/demos/selectable/display-grid.html");

		List<WebElement> numberItems = driver.findElements(By.xpath("//ol[@id='selectable']/li"));
		System.out.println("Tong Items =" + numberItems.size());

		Actions dragDropActions = actions.clickAndHold(numberItems.get(0)).moveToElement(numberItems.get(3)).release();

		dragDropActions.perform();
		
		Thread.sleep(5000);
	}

	@Test
	public void TC03_ClickAndSelectElementSelectRandom() throws Exception {
		driver.get("http://jqueryui.com/resources/demos/selectable/display-grid.html");
		actions.keyDown(Keys.CONTROL);

		List<WebElement> numberItems = driver.findElements(By.xpath("//ol[@id='selectable']/li"));
		
		actions.click(numberItems.get(0)).perform();
		
		actions.click(numberItems.get(2)).perform();
		
		actions.click(numberItems.get(5)).perform();
		
		actions.click(numberItems.get(10)).perform();
		
		actions.keyUp(Keys.CONTROL);
		Thread.sleep(1000);
	
		}

	@Test
	public void TC04_DoubleClick() {
		driver.get("http://www.seleniumlearn.com/double-click");
		WebElement doubleCLickMe = driver.findElement(By.xpath("//button[text()='Double-Click Me!']"));
		actions.doubleClick(doubleCLickMe).perform();
		Alert alert = driver.switchTo().alert();
		Assert.assertEquals(alert.getText(), "The Button was double-clicked.");
		alert.accept();
		
	}
	
	@Test
	public void TC05_RightClickToElement() throws Exception {
		driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");
		
		WebElement rightClickMebutton = driver.findElement(By.xpath("//span[text()='right click me']"));
		actions.contextClick(rightClickMebutton).perform();
		
		WebElement quitBeforeHover = driver.findElement(By.xpath("//li[contains(@class,'context-menu-icon-quit') and not(contains(@class,'context-menu-hover'))]"));
		actions.moveToElement(quitBeforeHover).perform();
		
		WebElement quitAfterHover = driver.findElement(By.xpath("//li[contains(@class, 'context-menu-icon-quit') and contains(@class, 'context-menu-hover') and contains(@class, 'context-menu-visible')]"));
		Assert.assertTrue(quitAfterHover.isDisplayed());
		
	}

	@Test
	public void TC06_DragDrop() throws Exception {
		driver.get("https://demos.telerik.com/kendo-ui/dragdrop/angular");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		WebElement soureButton = driver.findElement(By.xpath("//div[@id='draggable']"));
		WebElement targetButton = driver.findElement(By.xpath("//div[@id='droptarget']"));

		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView()", targetButton);

		actions.dragAndDrop(soureButton, targetButton).perform();

		Thread.sleep(1000);
		Assert.assertTrue(targetButton.getText().equals("You did great!"));
	}


	

	@AfterClass
	public void afterClass() {
		driver.close();
	}
}
