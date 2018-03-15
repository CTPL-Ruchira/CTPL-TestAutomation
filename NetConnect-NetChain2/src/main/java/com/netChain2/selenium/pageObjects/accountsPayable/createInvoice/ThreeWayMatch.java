package com.netChain2.selenium.pageObjects.accountsPayable.createInvoice;

import org.openqa.selenium.WebElement;

import com.netChain2.engine.Common;
import com.netChain2.selenium.pageObjects.common.components.CommonMethods;

public class ThreeWayMatch 
{
	public void acceptInvoiceAndVerifyStatus(String invoiceNumber, String vendorName)
	{
		System.out.println("invoiceNumber"+invoiceNumber);
		Common.sleep(3000);
		Common.click("SORTING_ARROW_XPATH");
		String acceptInvoiceXpath="//div[text()='"+invoiceNumber+"']/ancestor::tr[1]/td[4]//div[text()='"+vendorName+"']/ancestor::tr[1]/td[10]//a[text()='Accept Invoice']";
		WebElement ele=Common.findElement(acceptInvoiceXpath);
		ele.click();
		Common.click("YES_ACCEPT_ONLY_BUTTON_XPATH");
		Common.sleep(5000);
		String createPaymentXpath="//div[text()='"+invoiceNumber+"']/ancestor::tr[1]/td[4]//div[text()='"+vendorName+"']/ancestor::tr[1]/td[10]//a[text()='Create Payment']";
		WebElement ele2=Common.findElement(createPaymentXpath);
		System.out.println("Ghhah"+ele2.getText());
				
	}
	

	public boolean verifyGoodReceiptOnList(String grNumber, String vendorName)
	{
		Common.click("SORTING_ARROW_XPATH");
		Common.click("GR_DATE_SORTING_ARROW_XPATH");
	    Common.sleep(4000);
		String vendorNameFromList=Common.getText("GR_LIST_VENDORNAME_XPATH");
		System.out.println();
		if(vendorName.equals(vendorNameFromList))
			return true;
		else
			return false;
		
		
	}

	//To go on inv preview page by clicking on discrepant
	public void clickDiscrepant(String invoiceNo,String vendorName) {
		String xpath="//div[text()='"+invoiceNo+"']/ancestor::tr[1]/td[4]//div[text()='"+vendorName+"']/ancestor::tr[1]/td[6]//div[@class='text']";
		WebElement des=Common.findElement(xpath);
		des.click();
	}
	//To edit the rate field on invoice preview page
	public void editRateInvPreviewPage(String value)
	{
		Common.getElement("INVOICE_PREVIEW_PAGE_RATE_FIELD_XPATH").clear();
		Common.sendKeys("INVOICE_PREVIEW_PAGE_RATE_FIELD_XPATH", value);
		Common.click("INVOICE_PREVIEW_PAGE_RANDOM_CLICK_XPATH");
		Common.click("INVOICE_PREVIEW_PAGE_PRODUCT_LINE_SAVE_BUTTON_XPATH");
		CommonMethods.scrollDown();
		Common.click("INVOICE_PREVIEW_PAGE_MATCH_BUTTON_XPATH");
		Common.sleep(8000);
	}
	
	public void clickInvoiceEditPoLink(String vendorName, String invoiceNumber)
	{
		String InvoiceStatusEditPo="//div[text()='"+invoiceNumber+"']/ancestor::tr[1]/td[4]//div[text()='"+vendorName+"']/ancestor::tr[1]/td[10]//a[text()='Edit PO']";
		   WebElement Element2=Common.findElement(InvoiceStatusEditPo);
		   Element2.click();
	}
	public void clickUnapproveOnPoList(String poNumber,String vendorName)
	{
		String PoUnapproveStatus="//table[@class='table']//tr/td[3]//div[text()='"+poNumber+"']/ancestor::tr/td[5]//div[text()='"+vendorName+"']/ancestor::tr/td[11]//div/a[text()='Unapprove/Update']";
		WebElement element3=Common.findElement(PoUnapproveStatus);
		element3.click();
	}
	
	//Verification to check Approved status of Purchase Order
	public boolean checkApprovedStatusOnPoList(String poNumber,String vendorName,String expectedValue)
	{
		String poStatusApproved="//table[@class='table']//tr/td[3]//div[text()='"+poNumber+"']/ancestor::tr/td[5]//div[text()='"+vendorName+"']/ancestor::tr/td[11]//div/p[text()='Approved']";
		WebElement ele=Common.findElement(poStatusApproved);
		String actualValue=ele.getText();
		String expectedvalue="Approved";
		 if(actualValue.equals(expectedvalue))
		 {
			 return true;
		 }
		 else
		 {
			 return false;
		 }
	}
	
	
	
	//Verification to check GR Accural Account
	public boolean checkGrAccuralAccount(String actualValue,String expectedValue)
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

}

