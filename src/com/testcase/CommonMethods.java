package com.testcase;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

public class CommonMethods {
	public static WebDriver driver;
	PropertyLoader property = new PropertyLoader("Resources/Environment.properties");

	public WebElement waitForElementToLoadByXpath(String xpath, int delay) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(delay, java.util.concurrent.TimeUnit.SECONDS)
				.pollingEvery(10, java.util.concurrent.TimeUnit.SECONDS).ignoring(WebDriverException.class);

		WebElement webElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
		return webElement;
	}

	public WebElement getWebElementByXpathWithExplicitWait(String xpath, int delay) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(delay, java.util.concurrent.TimeUnit.SECONDS)
				.pollingEvery(5, java.util.concurrent.TimeUnit.SECONDS).ignoring(WebDriverException.class);

		try {
			WebElement webElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
			// WebElement webElement = webDriver.findElement(By.xpath(xpath));
			return webElement;
		} catch (Exception e) {
			// log.error(e);
			return null;
		}
	}

	public void inputTextByXpath(String xPath, String text) {
		WebElement webElement = getWebElementByXpathWithExplicitWait(xPath, 60);
		if (webElement != null) {

			webElement.clear();
			webElement.click();
			webElement.sendKeys(text);
		}
	}

	public boolean clickElementByXpath(String xpath) {
		WebElement webElementToClick = getWebElementByXpathWithExplicitWait(xpath, 15);
		boolean isElemenPresent = false;
		if (webElementToClick != null) {
			webElementToClick.click();
			isElemenPresent = true;
		}
		return isElemenPresent;
	}

	public List<WebElement> getWebElementsByXpath(String xpath) {
		try {

			List<WebElement> webElement = driver.findElements(By.xpath(xpath));
			return webElement;
		} catch (Exception nsee) {
			return null;
		}
	}

	public WebElement waitForElementToLoadByXpath(String xpath, int delay, int interval) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(delay, java.util.concurrent.TimeUnit.SECONDS)
				.pollingEvery(interval, java.util.concurrent.TimeUnit.SECONDS).ignoring(WebDriverException.class);

		WebElement webElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
		return webElement;
	}

	public void selectValueFromDropdown(String xpath_dropdown, String xpath_ListValue, String selectValueFromList)
			throws InterruptedException {
		WebElement divClick = driver.findElement(By.xpath(xpath_dropdown));
		divClick.click();

		WebElement dropdown = driver.findElement(By.xpath(xpath_ListValue));
		waitForElementToLoadByXpath(xpath_ListValue, 20);
		List<WebElement> list = driver.findElements(By.xpath(xpath_ListValue));
		for (WebElement webElement : list) {

			if (webElement.getText().contains(selectValueFromList)) {
				System.out.println("Selected Value in DropDown ::" + webElement.getText());
				webElement.click();
				break;
			}
		}

	}

	public void selectRadioButton(String xpath_radioButton, String Value) {
		waitForElementToLoadByXpath(xpath_radioButton, 20);
		List<WebElement> list = driver.findElements(By.xpath(xpath_radioButton));
		for (WebElement webElement : list) {

			if (webElement.getAttribute("value").contains(Value)) {
				System.out.println("Selected Value in Radio Button ::" + webElement.getAttribute("value"));
				webElement.click();
				break;
			}
		}
	}

	public void verifyCommunity() {
		WebElement selectedCommunity = driver.findElement(By.xpath(
				"//div[@class='Dropdown-control mother_tongue_selector Dropdown-disabled']//child::div[@class='Dropdown-placeholder is-selected']"));
		String community = selectedCommunity.getText().toLowerCase();
		System.out.println("Selected Community::" + community);
		String urlEnviornment = property.pro.getProperty("Url");
		if (urlEnviornment.contains(community)) {
			System.out.println("Pass");
		} else {
			System.out.println("Failed---Different commnutiy selected");
		}

	}

	public void selectCommunity() throws InterruptedException {
		String urlEnviornment = property.pro.getProperty("Url");
		if (urlEnviornment.contains("marathi")) {
			selectValueFromDropdown("(//div[@class='Dropdown-control'])[5]", Locators.SELECT_VALUE_FROM_DROPDOWN,
					"Marathi");
			System.out.println("Pass");
		} else if (urlEnviornment.contains("gujarati")) {
			selectValueFromDropdown("(//div[@class='Dropdown-control'])[5]", Locators.SELECT_VALUE_FROM_DROPDOWN,
					"Gujarati");
		} else {
			System.out.println("Failed---Different commnutiy selected");
		}

	}

}
