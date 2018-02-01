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
		Common.sleep(2000);
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
}
