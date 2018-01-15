package com.netChain2.selenium.pageObjects.common.landingPage;

import org.openqa.selenium.WebElement;

import com.netChain2.engine.Common;

public class LandingPage {
	
	public void clickLogInButton()
	{
		WebElement loginbutton=Common.getElement("BASE_LOGIN_BUTTON_PARTIALLINKTEXT");
		loginbutton.click();
	}
	
	public boolean isLoginButtonDisplayed() {
		WebElement loginbutton=Common.getElement("BASE_LOGIN_BUTTON_PARTIALLINKTEXT");
		return loginbutton.isDisplayed();
	}

	 	

}
