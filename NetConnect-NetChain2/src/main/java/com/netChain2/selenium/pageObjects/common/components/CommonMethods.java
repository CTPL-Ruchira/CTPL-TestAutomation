package com.netChain2.selenium.pageObjects.common.components;

import org.openqa.selenium.JavascriptExecutor;

import com.netChain2.engine.Common;

/*
 * This method enters value in the search input field
 * parameters=searchTerm
 * */
public class CommonMethods 
{
	public static void searchByNumberOrName(String searchTerm)
	{
		Common.sleep(2000);
		Common.sendKeys("SEARCH_INPUT_XPATH", searchTerm);
	}
	
	public static void scrollUp()
	{
		JavascriptExecutor jse = (JavascriptExecutor)Common.getDriver();
		jse.executeScript("scroll(0, -650);");
	}
	
	public static void scrollDown()
	{
		JavascriptExecutor jse = (JavascriptExecutor)Common.getDriver();
		jse.executeScript("scroll(0, 950);");
	}
	
	public static boolean compareTwoValues(String actualValue, String expectedValue)
	{
		if(actualValue.equals(expectedValue))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public static String getAlertMessage()
	{
		return Common.getText("INVOICE_ALERT_MESSAGE_SUCCESSFULLY_XPATH");
	}
	public static void gotoLeftARLink(String linkName)
	 {
	  Common.click("CLICK_MENU_BUTTON_CLASSNAME");
	  Common.sleep(1000);
	  Common.click("AR_MENU_AR_XPATH");
	  Common.sleep(1000);
	  Common.click("CLICK_MENU_BUTTON_CLASSNAME");
	  Common.sleep(1000);
	  String xpathForLink="//ul[@class='sub-ar']/li/a[text()='"+linkName+"']";
	  Common.findElement(xpathForLink).click();
	 }
	
	public static void gotoLeftAPLink(String linkName)
	 {
	  Common.click("CLICK_MENU_BUTTON_CLASSNAME");
	  Common.sleep(1000);
	  Common.click("AP_MENU_AP_XPATH");
	  Common.sleep(1000);
	  Common.click("CLICK_MENU_BUTTON_CLASSNAME");
	  Common.sleep(1000);
	  String xpathForLink="//ul[@class='sub-ap']/li/a[text()='"+linkName+"']";
	  Common.findElement(xpathForLink).click();
	 }
}
