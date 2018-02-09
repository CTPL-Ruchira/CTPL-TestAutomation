package com.netChain2.selenium.pageObjects.common.logout;

import com.netChain2.engine.Common;

public class LogoutFromPage 
{
	public static void logout()
	{
		Common.click("NAVIGATION_MENU_PARTIAL_LINK");
		Common.click("LOGOUT_BUTTON_XPATH");
	}
}
