package com.testcase;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import com.sun.corba.se.spi.activation.Locator;

public class ResistrationTestCase extends CommonMethods {

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "Resources/chromedriver.exe");
		driver = new ChromeDriver();
		CommonMethods comm = new CommonMethods();
		// Loading URL from Enviroment properties file
		PropertyLoader property = new PropertyLoader("Resources/Environment.properties");
		driver.get(property.pro.getProperty("Url"));
		driver.manage().window().maximize();

		comm.selectValueFromDropdown(Locators.LOOKING_FOR_DROPDOWN, Locators.LOOKING_FOR_DROPDOWN_VALUE, "Man");
		comm.selectValueFromDropdown(Locators.AGE_DROPDOWN, Locators.SELECT_VALUE_FROM_DROPDOWN, "29");
		comm.selectValueFromDropdown(Locators.AGE_DROPDOWN_TO, Locators.SELECT_VALUE_FROM_DROPDOWN, "29");
		comm.selectValueFromDropdown("(//div[@aria-haspopup='listbox'])[4]", Locators.SELECT_VALUE_FROM_DROPDOWN,
				"Hindu");
		comm.selectCommunity();
		comm.clickElementByXpath(Locators.LETS_BEGIN_BUTTON);
		comm.inputTextByXpath(Locators.EMAIL_TEXTFIELD, "ladayush@yahoo.com");
		comm.inputTextByXpath(Locators.PASSWORD_TEXTFIELD, "text$321");
		comm.selectValueFromDropdown(Locators.PROFILE_CREATOR, Locators.PROFILE_CREATOR_VALUE, "Self");
		comm.selectRadioButton(Locators.SELECT_GENDER, "Male");
		comm.clickElementByXpath(Locators.NEXT_BUTTON);
		comm.inputTextByXpath(Locators.FIRSTNAME_TEXTFIELD, "Ayush");
		comm.inputTextByXpath(Locators.LASTNAME_TEXTFIELD, "Lad");
		comm.selectValueFromDropdown(Locators.SELCT_DAY_DOB, Locators.SELCT_VALUE, "17");
		comm.selectValueFromDropdown(Locators.SELCT_MONTH_DOB, Locators.SELCT_VALUE, "Jun");
		comm.selectValueFromDropdown(Locators.SELCT_YEAR_DOB, Locators.SELCT_VALUE, "1990");
		comm.selectValueFromDropdown(Locators.SELECT_RELEGION, Locators.SELCT_VALUE, "Hindu");
		comm.verifyCommunity();
		comm.selectValueFromDropdown(Locators.SELECT_COUNTRY, Locators.SELCT_VALUE, "Canada");
	}
}