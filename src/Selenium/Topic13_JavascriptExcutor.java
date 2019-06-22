package Selenium;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic13_JavascriptExcutor {
	WebDriver driver;
	Actions actions;
	String customerNameSystem, dateOfBirthSystem, addressSystem, citySystem, sateSystem, pinSystem, telephoneSystem,
			emailSystem, passwordSystem, userNameSystem, passWordSystem;
	String customerIDRow, customerNameRow, dateOfBirthRow, addressRow, cityRow, sateRow, pinRow, telephoneRow, emailRow;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\Phuc\\Downloads\\chromedriver_win32_74\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

	}

	@Test
	public void TC01JavascriptExcutetor() {

		navigateToUrlByJS("http://live.guru99.com/");

		String homePageDomain = (String) executeForBrowser("return document.domain");
		Assert.assertEquals(homePageDomain, "live.guru99.com");

		String homePageUrl = (String) executeForBrowser("return document.URL");
		Assert.assertEquals(homePageUrl, "http://live.guru99.com/");

		WebElement mobileLink = driver.findElement(By.xpath("//a[text()='Mobile']"));
		highlightElement(mobileLink);
		clickToElementByJS(mobileLink);

		WebElement samsungAddToCardButton = driver.findElement(
				By.xpath("//a[@title='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']/button"));
		highlightElement(samsungAddToCardButton);
		clickToElementByJS(samsungAddToCardButton);

		String samsungSuccessMessage = (String) executeForBrowser("return document.documentElement.innerText");
		Assert.assertTrue(samsungSuccessMessage.contains("Samsung Galaxy was added to your shopping cart."));
		Assert.assertTrue(verifyTextInInnerText("Samsung Galaxy was added to your shopping cart."));

		clickToElementByJS(driver.findElement(By.xpath("//a[text()='Privacy Policy']")));

		String privacyTitlePage = (String) executeForBrowser("return document.title");
		Assert.assertEquals(privacyTitlePage, "Privacy Policy");

		scrollToBottomPage();

		WebElement wishListRow = driver.findElement(By.xpath(
				"//th[text()='WISHLIST_CNT']/following-sibling::td[text()='The number of items in your Wishlist.']"));
		highlightElement(wishListRow);
		Assert.assertTrue(wishListRow.isDisplayed());

		navigateToUrlByJS("http://demo.guru99.com/v4/");

		String demoGuruDomain = (String) executeForBrowser("return document.domain");
		Assert.assertEquals(demoGuruDomain, "demo.guru99.com");
	}

	@Test
	public void TC02_RemoveAttribute() throws Exception {

		Random random = new Random();
		String randomEmail = "phucautmation" + random.nextInt(999999) + "@gmail.com";

		By customerNameTextbox = By.xpath("//input[@name='name']");
		By dateOfBirthTextbox = By.id("dob");
		By addressTextArea = By.xpath("//textarea[@name='addr']");
		By cityTextbox = By.xpath("//input[@name='city']");
		By sateTextbox = By.xpath("//input[@name='state']");
		By pinTextbox = By.xpath("//input[@name='pinno']");
		By telephoneTextbox = By.xpath("//input[@name='telephoneno']");
		By emailTextbox = By.xpath("//input[@name='emailid']");
		By passWordTextbox = By.xpath("//input[@name='password']");
		By submitButton = By.xpath("//input[@type='submit']");

		userNameSystem = "mngr203711";
		passWordSystem = "EgEmaqe";
		customerNameSystem = "Selenium";
		dateOfBirthSystem = "2000-01-01";
		addressSystem = "123 Address";
		citySystem = "Ho Chi Minh";
		sateSystem = "Thu Duc";
		pinSystem = "123456";
		telephoneSystem = "0123456978";
		emailSystem = randomEmail;
		passwordSystem = "qAnUdar";

		By customerNameRow = By.xpath("//td[text()='Customer Name']/following-sibling::td");
		By dateOfBirthRow = By.xpath("//td[text()='Birthdate']/following-sibling::td");
		By addressRow = By.xpath("//td[text()='Address']/following-sibling::td");
		By cityRow = By.xpath("//td[text()='City']/following-sibling::td");
		By sateRow = By.xpath("//td[text()='State']/following-sibling::td");
		By pinRow = By.xpath("//td[text()='Pin']/following-sibling::td");
		By telephoneRow = By.xpath("//td[text()='Mobile No.']/following-sibling::td");
		By emailRow = By.xpath("//td[text()='Email']/following-sibling::td");

		driver.get("http://demo.guru99.com/v4");

		driver.findElement(By.xpath("//input[@name='uid']")).sendKeys(userNameSystem);
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys(passWordSystem);
		driver.findElement(By.xpath("//input[@name='btnLogin']")).click();

		driver.findElement(By.xpath("//a[text()='New Customer']")).click();

		driver.findElement(customerNameTextbox).sendKeys(customerNameSystem);

		removeAttributeInDOM(driver.findElement(dateOfBirthTextbox), "type");
		Thread.sleep(3000);
		driver.findElement(dateOfBirthTextbox).sendKeys(dateOfBirthSystem);

		driver.findElement(addressTextArea).sendKeys(addressSystem);
		driver.findElement(cityTextbox).sendKeys(citySystem);
		driver.findElement(sateTextbox).sendKeys(sateSystem);
		driver.findElement(pinTextbox).sendKeys(pinSystem);
		driver.findElement(telephoneTextbox).sendKeys(telephoneSystem);
		driver.findElement(emailTextbox).sendKeys(emailSystem);
		driver.findElement(passWordTextbox).sendKeys(passwordSystem);
		driver.findElement(submitButton).click();

		Assert.assertEquals(driver.findElement(customerNameRow).getText(), customerNameSystem);
		Assert.assertEquals(driver.findElement(dateOfBirthRow).getText(), dateOfBirthSystem);
		Assert.assertEquals(driver.findElement(addressRow).getText(), addressSystem);
		Assert.assertEquals(driver.findElement(cityRow).getText(), citySystem);
		Assert.assertEquals(driver.findElement(sateRow).getText(), sateSystem);
		Assert.assertEquals(driver.findElement(pinRow).getText(), pinSystem);
		Assert.assertEquals(driver.findElement(telephoneRow).getText(), telephoneSystem);
		Assert.assertEquals(driver.findElement(emailRow).getText(), emailSystem);

	}

	@Test
	public void TC03_CreateAnAccount() {

		Random random = new Random();
		String randomEmail = "phucautmation" + random.nextInt(999999) + "@gmail.com";

		navigateToUrlByJS("http://live.guru99.com/");

		WebElement myAccount = driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']"));
		clickToElementByJS(myAccount);

		clickToElementByJS(driver.findElement(By.xpath("//span[text()='Create an Account']")));

		WebElement firstNameTextbox = driver.findElement(By.xpath("//input[@id='firstname']"));
		sendkeyToElementByJS(firstNameTextbox, "Phan");

		WebElement midleNameTextbox = driver.findElement(By.xpath("//input[@id='middlename']"));
		sendkeyToElementByJS(midleNameTextbox, "Thi");

		WebElement lastNameTextbox = driver.findElement(By.xpath("//input[@id='lastname']"));
		sendkeyToElementByJS(lastNameTextbox, "Phuc");

		WebElement emailAddressTextbox = driver.findElement(By.xpath("//input[@id='email_address']"));
		sendkeyToElementByJS(emailAddressTextbox, randomEmail);

		WebElement passwwordTextbox = driver.findElement(By.xpath("//input[@id='password']"));
		sendkeyToElementByJS(passwwordTextbox, "123456");

		WebElement confirmPassword = driver.findElement(By.xpath("//input[@id='confirmation']"));
		sendkeyToElementByJS(confirmPassword, "123456");

		clickToElementByJS(driver.findElement(By.xpath(".//*[@id='form-validate']/div[2]/button")));
		Assert.assertEquals(driver
				.findElement(By.xpath(".//*[@id='top']/body/div[1]/div/div[2]/div/div[2]/div/div/ul/li/ul/li/span"))
				.getText(), "Thank you for registering with Main Website Store.");

		clickToElementByJS(driver.findElement(By.xpath(".//*[@id='header']/div/div[2]/div/a/span[2]")));
		clickToElementByJS(driver.findElement(By.xpath("//a[@title='Log Out']")));

		navigateToUrlByJS("http://live.guru99.com/index.php/"); 

	}

	public void highlightElement(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String originalStyle = element.getAttribute("style");
		js.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style",
				"border: 5px solid red; border-style: dashed;");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		js.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);

	}

	public Object executeForBrowser(String javaSript) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		return js.executeScript(javaSript);
	}

	public boolean verifyTextInInnerText(String textExpected) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String textActual = (String) js
				.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0]");
		System.out.println("Text actual = " + textActual);
		return textActual.equals(textExpected);
	}

	public Object clickToElementByJS(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		return js.executeScript("arguments[0].click();", element);
	}

	public Object scrollToElement(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		return js.executeScript("arguments[0].scrollIntoView(true);", element);
	}

	public Object sendkeyToElementByJS(WebElement element, String value) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		return js.executeScript("arguments[0].setAttribute('value', '" + value + "')", element);
	}

	public Object scrollToBottomPage() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		return js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	public Object removeAttributeInDOM(WebElement element, String attributeRemove) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		return js.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", element);
	}

	public Object navigateToUrlByJS(String url) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		return js.executeScript("window.location = '" + url + "'");
	}

	@AfterClass
	public void afterClass() {
		driver.close();
	}
}
