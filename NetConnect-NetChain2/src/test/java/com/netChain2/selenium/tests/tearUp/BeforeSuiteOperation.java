package com.netChain2.selenium.tests.tearUp;

import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeSuite;

import com.netChain2.engine.Common;

public class BeforeSuiteOperation {
	
	
	@BeforeSuite(alwaysRun=true)
	public void navigateToLoginPage() {
		WebElement loginbutton=Common.getElement("BASE_LOGIN_BUTTON_PARTIALLINKTEXT");
		loginbutton.click();
	}

}
