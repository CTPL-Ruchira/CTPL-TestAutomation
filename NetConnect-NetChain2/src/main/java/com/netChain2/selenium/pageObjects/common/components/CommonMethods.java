package com.netChain2.selenium.pageObjects.common.components;

import org.apache.commons.lang.RandomStringUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

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
	
	public static void gotoRightSideARLink(String linkName) 
	{
		Common.click("AR_CREATE_NEW_PLUSE_BUTTON_XPATH");
		Common.sleep(1000);
		Common.click("AR_CLICK_ACCOUNT_RECEIVABLE_XPATH");
		Common.sleep(2000);
	    String xpathForLink="//ul[@class='sub-ar']/li/a[text()='"+linkName+"']";
	    Common.findElement(xpathForLink).click();
	}
	
	public static void gotoRightSideAPLink(String linkName) 
	{
		Common.click("AR_CREATE_NEW_PLUSE_BUTTON_XPATH");
		Common.sleep(1000);
		Common.click("AP_LINK_PARTIALLINKTEXT");
		Common.sleep(1000);
	    String xpathForLink="//ul[@class='sub-ap']/li/a[text()='"+linkName+"']";
	    Common.findElement(xpathForLink).click();
	}
	public static void logoutFromWorkflowPage()
	{
		Common.click("GR_WORKFLOW_LOGOUT_BUTTON_XPATH");
	}
	
	public static String generateRandomStringForInvoiceID(String value) {
		   String randomString = RandomStringUtils.randomAlphanumeric(3);
		   return value.concat("_"+randomString);
		   
		  }
   
	public static void verifyAndClickOnActionForPo(String poNumber, String vendorName,String status) {
      Common.click("SORTING_ARROW_XPATH");
      Common.sleep(1000);
      String verificationOfStatus="//div[text()='"+poNumber+"']/ancestor::tr[1]/td[5]//div[text()='"+vendorName+"']/ancestor::tr[1]//td[11]//div//a[text()='"+status+"']";
	  WebElement poStatus=Common.findElement(verificationOfStatus);
	  poStatus.click();
	}
 
	 


}