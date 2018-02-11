package com.netChain2.selenium.pageObjects.common.logout;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.netChain2.engine.Common;

public class LogoutFromPage 
{
	public static void logout()
	{
		WebElement menuELe= Common.getElement("NAVIGATION_MENU_PARTIAL_LINK");
		Actions action = new Actions(Common.getDriver());
		action.click(menuELe).perform();
		Common.sleep(2000);
		action = new Actions(Common.getDriver());
		WebElement logOutEle = Common.getElement("LOGOUT_BUTTON_XPATH");
		action.click(logOutEle).perform();
		Common.sleep(2000);
	}
}
