package com.netChain2.selenium.pageObjects.common.logout;

import com.netChain2.engine.Common;

public class LogoutFromPage 
{
	public static void logout()
	{
		Common.sleep(2000);
		Common.click("NAVIGATION_MENU_PARTIAL_LINK");
		Common.sleep(2000);
		Common.click("LOGOUT_BUTTON_XPATH");
	}
}
