package com.netChain2.selenium.pageObjects.common.loginPage;

import com.connecticus.engine.Common;

public class LoginPage {
	
	
	public void setUserName(String userName) {
		Common.sendKeys("LOGIN_USERNAME_XPATH", userName);
		
	}
	
	public void setPassword(String password) {
		Common.sendKeys("LOGIN_PASSWORD_XPATH", password);	
	}
	
	
	public void clickLoginButton() {
		Common.click("LOGIN_BUTTON_XPATH");
	}
	
	public void login(String userName, String password) {
		Common.sendKeys("LOGIN_USERNAME_XPATH", userName);
		Common.sendKeys("LOGIN_PASSWORD_XPATH", password);
		Common.click("LOGIN_BUTTON_XPATH");
	}

}
