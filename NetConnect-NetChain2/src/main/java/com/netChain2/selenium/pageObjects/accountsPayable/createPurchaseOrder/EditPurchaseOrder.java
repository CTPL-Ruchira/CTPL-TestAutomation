package com.netChain2.selenium.pageObjects.accountsPayable.createPurchaseOrder;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import com.netChain2.engine.BaseTestCase;
import com.netChain2.engine.Common;
import com.netChain2.selenium.pageObjects.common.components.CommonMethods;



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
		CommonMethods.scrollUp();
		Common.click("SORTING_ARROW_XPATH");
		Common.click("PO_DATE_SORTING_ARROW_XPATH");
		String xPath= "//div[text()='"+poNumber+"']/ancestor::tr[1]/td[5]//div[text()='"+vendorName+"']/ancestor::tr[1]//td[11]//div//a[text()='Approve']";
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
	
	public boolean verifyPurchaseOrderForConnectedVendor(String vendorName, String poNumber)
	{
		 boolean flag=false;
		  Common.sleep(2000);
		  String xpath="//table[@class='table']//tr/td[3]//div[text()='"+poNumber+"']/ancestor::tr/td[5]//div[text()='"+vendorName+"']";
		  WebElement actualInvoice=Common.findElement(xpath);
		  String actualinvoiceList=actualInvoice.getText();
		  if(vendorName.equals(actualinvoiceList))
		  {
		   flag=true;
		  }
		  else
		  {
		   return false;
		  }

		  return flag;		
	}
}

