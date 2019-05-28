package Selenium;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import java.util.List;

public class Topic04CustomDrodownListJquery {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() throws Exception {
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\Phuc\\Downloads\\chromedriver_win32_74\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("http://jqueryui.com/resources/demos/selectmenu/default.html");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		selectItemIndropDown("//span[@id='number-button']", ".//ul[@id='number-menu']/li", "19",
				"//*[@id='number-button']/span[@class='ui-selectmenu-text']");
		Thread.sleep(1000);
	}

	public void selectItemIndropDown(String parentLocator, String allItemsInDropdown, String selectedText,
			String valueXpath) throws Exception {
		WebElement parentElement = driver.findElement(By.xpath(parentLocator));
		parentElement.click();

		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(allItemsInDropdown)));

		List<WebElement> allItemsElements = driver.findElements(By.xpath(allItemsInDropdown));
		System.out.println("All Items" + allItemsElements.size());

		for (int i = 0; i < allItemsElements.size(); i++) {
			WebElement webElement = allItemsElements.get(i);

			if (webElement.getText().equals(selectedText)) {
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", webElement);
				Thread.sleep(1000);
				webElement.click();
				break;
			}
		}

		Assert.assertEquals(driver.findElement(By.xpath(valueXpath)).getText(), selectedText);
		/*
		 * 
		 * for (WebElement webElement : allItemsElements) { if
		 * (webElement.getText().equals(selectedText)) { ((JavascriptExecutor)
		 * driver).executeScript("arguments[0].scrollIntoView(true);", webElement);
		 * Thread.sleep(1000); webElement.click(); break; } }
		 */
	}

	@AfterClass
	public void afterClass() {
		driver.close();
	}

}
