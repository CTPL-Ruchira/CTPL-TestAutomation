package com.netChain2.selenium.pageObjects.accountsPayable.createPurchaseOrder;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import com.netChain2.engine.BaseTestCase;
import com.netChain2.engine.Common;



public class EditPurchaseOrder 
{
	public void searchPurchaseOrder(String valueToSearch)
	{
		JavascriptExecutor js=(JavascriptExecutor) Common.getDriver();
		js.executeScript("window.scrollBy(200,0)");
		Common.sendKeys("PO_SEARCH_BOX_XPATH", valueToSearch);
	}
	
	public void verifyCreatedPurchaseOrder(String vendorName, String poNumber)
	{
		String xPath= "//div[text()='"+vendorName+"']/ancestor::div[@class='item']/div[2]/div[text()='"+poNumber+"']/ancestor::div[@class='item']/div[10]/div/a[text()='Approve']";
		System.out.println("Before displayed");
		try {
		WebElement ele=Common.findElement(xPath);
		BaseTestCase.assertTrue(ele.isDisplayed(), "Product is not created");
		}
		catch(Exception e)
		{
			System.out.println("Element not found");
			BaseTestCase.assertTrue(false, "Product is not created");
			
		}
			
	}
	public void verifyPurchaseOrderForConnectedVendor(String vendorName, String poNumber)
	{
		String xPath="//div[text()='"+vendorName+"']/ancestor::div[@class='item']/div[2]/div[text()='"+poNumber+"']/ancestor::div[@class='item']/div[9]/div/p[text()='Approved / Unaccepted']";
		try {
		WebElement ele=Common.findElement(xPath);
		BaseTestCase.assertTrue(ele.isDisplayed(), "Product is not created");
		}
		catch(Exception e)
		{
			System.out.println("Element not found");
			BaseTestCase.assertTrue(false, "Product is not created");
			
		}
			
	}
}

