package Selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_03_Selenium_API_Exercise {
	WebDriver driver;
	By emailTexbox = By.xpath("//input[@id='mail']");
	By ageUnder18Radio = By.xpath(".//*[@id='under_18']");
	By education = By.xpath("//textarea[@id='edu']");
	By jobRole01Dropdown = By.xpath("//select[@id='job1']");
	By passWordTextbox = By.xpath("//input[@disabled='disabled']");
	By ageRadioButton = By.xpath("/html/body/form/fieldset[2]/label[6]");
	By byographyArea = By.xpath("//textarea[@id='bio']");
	By interesteCheckBoxIsDisable = By.xpath("//input[@id='check-disbaled']");
	By slider02 = By.xpath("//label[@for='slider-02']");
	By buttonIsDisable = By.id("button-disabled");
	By slider01 = By.id("slider-1");
	By jobRole02 = By.xpath(".//*[@id='job2']");
	By buttonIsEnable = By.id("button-enabled");
	By interestedDevelopment = By.xpath(".//*[@id='development']");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\Phuc\\Downloads\\chromedriver_win32_74\\chromedriver.exe");
		driver = new ChromeDriver();

		driver.get("https://daominhdam.github.io/basic-form/index.html");

	}

	@Test
	public void TC_01_Display() {
		if (driver.findElement(emailTexbox).isDisplayed()) {
			driver.findElement(emailTexbox).sendKeys("Automation Testing");
		}
		if (driver.findElement(education).isDisplayed()) {
			driver.findElement(education).sendKeys("Automation Tetsting");
		}
		if (driver.findElement(ageUnder18Radio).isDisplayed()) {
			driver.findElement(ageUnder18Radio).click();
		}

	}

	@Test
	public void TC_02_Disable_Enable() {

		WebElement emailElement = driver.findElement(emailTexbox);
		boolean isEmailEnabled = emailElement.isEnabled();
		System.out.println("Element email is " + (isEmailEnabled ? "enabled" : "disabled"));
		Assert.assertTrue(isEmailEnabled, "expected email element is enabled");

		WebElement ageUnder18Element = driver.findElement(ageUnder18Radio);
		boolean isAgeUnder18RaioEnabled = ageUnder18Element.isEnabled();
		System.out.println("Element Age under 18 is " + (isAgeUnder18RaioEnabled ? "enabled" : "disabled"));
		Assert.assertTrue(isAgeUnder18RaioEnabled, "expected age under 18 element is enabled");

		WebElement jobRole01DropdownElement = driver.findElement(jobRole01Dropdown);
		boolean isjobRole01DropdownEnabled = jobRole01DropdownElement.isEnabled();
		System.out.println("Element Job Role 01 is " + (isjobRole01DropdownEnabled ? "enabled" : "disabled"));
		Assert.assertTrue(isjobRole01DropdownEnabled, "expected age under 18 element is enabled");

		WebElement interesteCheckBoxIsDisableElement = driver.findElement(interesteCheckBoxIsDisable);
		boolean isinteresteCheckBoxIsDisableEnabled = interesteCheckBoxIsDisableElement.isEnabled();
		System.out.println(
				"Element Interested checkbox is " + (isinteresteCheckBoxIsDisableEnabled ? "enabled" : "disabled"));
		Assert.assertFalse(isinteresteCheckBoxIsDisableEnabled, "expected interested checkbox is disable");

		WebElement slider01Element = driver.findElement(slider01);
		boolean isSlider01Enabled = slider01Element.isEnabled();
		System.out.println("Element Slider 01 is " + (isSlider01Enabled ? "enabled" : "disabled"));
		Assert.assertTrue(isSlider01Enabled, "expecter Slider 01 is disable");

		WebElement buttonIsDisableElement = driver.findElement(buttonIsDisable);
		boolean isbuttonIsDisableEnabled = buttonIsDisableElement.isEnabled();
		System.out.println("Element Button is disable is " + (isbuttonIsDisableEnabled ? "enabled" : "disabled"));
		Assert.assertFalse(isbuttonIsDisableEnabled, "expected Button is disable is disable");

		WebElement passWordTextboxElement = driver.findElement(passWordTextbox);
		boolean ispassWordTextboxEnabled = passWordTextboxElement.isEnabled();
		System.out.println("Element password Textbox is " + (ispassWordTextboxEnabled ? "enabled" : "disabled"));
		Assert.assertFalse(ispassWordTextboxEnabled, "expected Password Textbox is disable");

		WebElement ageRadiobuttonElement = driver.findElement(ageRadioButton);
		boolean isageRadiobuttonEnable = ageRadiobuttonElement.isEnabled();
		System.out.println("Element Age radio button is " + (isageRadiobuttonEnable ? "enabled" : "disabled"));
		Assert.assertTrue(isageRadiobuttonEnable, "expected Age radio button is disable");

		WebElement byographyAreaElement = driver.findElement(byographyArea);
		boolean isbyographyAreaEnable = byographyAreaElement.isEnabled();
		System.out.println("Element byography area is " + (isbyographyAreaEnable ? "enabled" : "disabled"));
		Assert.assertFalse(isbyographyAreaEnable, "expected Byography area is disable");

		WebElement jobRole02Element = driver.findElement(jobRole02);
		boolean isjobRole02Enable = jobRole02Element.isEnabled();
		System.out.println("Element job Role 02 is " + (isjobRole02Enable ? "enabled" : "disabled"));
		Assert.assertFalse(isjobRole02Enable, "expected Job role 02 is disable");

		WebElement buttonIsEnableElement = driver.findElement(buttonIsEnable);
		boolean isbuttonIsEnableEnable = buttonIsEnableElement.isEnabled();
		System.out.println("Element button Is Enable is " + (isbuttonIsEnableEnable ? "enabled" : "disabled"));
		Assert.assertTrue(isbuttonIsEnableEnable, "expected button is enable is disable");
	}

	@Test
	public void TC_03_Check_Selected() {
		WebElement ageUnder18Element = driver.findElement(ageUnder18Radio);
		ageUnder18Element.click();
		WebElement interestedDevelopmentElement = driver.findElement(interestedDevelopment);
		interestedDevelopmentElement.click();

		Assert.assertTrue(ageUnder18Element.isSelected(), "exected age under 18 is selected");
		Assert.assertTrue(interestedDevelopmentElement.isSelected(),
				"exected interested Development element is selected");
	}

	@AfterClass
	public void afterClass() {
		driver.close();

	}

}
