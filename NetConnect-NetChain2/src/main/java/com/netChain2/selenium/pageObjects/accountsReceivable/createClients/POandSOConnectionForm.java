package com.netChain2.selenium.pageObjects.accountsReceivable.createClients;

import org.openqa.selenium.WebElement;
import org.testng.Reporter;
import com.netChain2.engine.Common;
import com.netChain2.selenium.pageObjects.common.components.CommonMethods;

public class POandSOConnectionForm {
	
	public void clickAccept(String ClientName, String poNumber) {
		String soSearchAccept="//div[text()='"+poNumber+"']/ancestor::div[2]/div[4]/div[text()='"+ClientName+"']/ancestor::div[2]/div[11]/div/div/button[@id='share']";
		WebElement eleAccept=Common.findElement(soSearchAccept);
		eleAccept.click();
	}
	//select vendor
	public void selectvendor(String vendor) {
		Common.selectFromDropdown("VENDOR_DROPDOWN_XPATH", "PO_VENDOR_ALL_DROPDOWN_VALUES_XPATH", vendor);
	}
	
	//Get invoice number
	public String getinvoiceNumber() {
		return Common.getAttribute("INVOICE_NUMBER_XPATH");
	}
	
	public void verifySOList(String ClientName, String poNumber)
	{
		Common.sleep(3000);
		System.out.println("geting so list");
		String st="//div[@class='LineItemDataList']//div[contains(text(),'"+ClientName+"')]/..//following-sibling::div/div[text()='"+poNumber+"']/..//following-sibling::div//button[@data-tip='Accept']";
		WebElement ele=Common.findElement(st);
		ele.click();
	}
	
	//click on plus sign
	public void clickPlusbutton(String clientName, String poNumber) {
		String plusbutton="//div[@class='LineItemDataList']//div[contains(text(),'"+clientName+"')]/..//following-sibling::div/div[text()='"+poNumber+"']/..//following-sibling::div//button/i[@data-tip='Create Invoice']";
		WebElement ele=Common.findElement(plusbutton);
		ele.click();	
	}
	
	public void getClientName(String ClientName, String poNumber) 
	{		
	    String soGetClientName="//div[text()='"+poNumber+"']/ancestor::div[2]/div[4]/div[text()='"+ClientName+"']";
		WebElement eleAccept=Common.findElement(soGetClientName);
		eleAccept.getText();
	}
	
	public String getAttributeValuePoNo(){
  		return Common.getAttribute("PO_NUMBER_XPATH");
    }
	
	//click save and share button on invoice page
	public void clickSaveAndShareBtn() {
		Common.click("INVOICE_SO_SAVE_AND_SHARE_BUTTON_XPATH");
	}
	
	//verfiy Invoice title
	public boolean verifyTitleMatched(String actualTitleValue, String expectedTitleValue) {
		if(actualTitleValue.equals(expectedTitleValue)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	//verify invoice list 
	public Boolean verifyInvoiceOnList(String expectedClientName) 
	 {
	  boolean flag=false;
	  Common.sleep(2000);	  
	  String actualClientNameOnList=Common.getText("AR_CLIENT_LIST_XPATH");
	  if(actualClientNameOnList.equals(expectedClientName))
	  {
	   flag=true;
	   Reporter.log("Invoice present on list and verified");
	  }
	  else
	  {
	   System.out.println("Invoice not created");
	  }

	  return flag;

	 }
	
	public void verifyAPInvoiceOnListandClickAccept(String clientName,String ino) 
	 {
	  String acceptXpath="//div[@class='LineItemDataList']//div[contains(text(),'"+clientName+"')]/..//following-sibling::div/div[text()='"+ino+"']/..//following-sibling::div//button[@data-tip='Accept']";
	  WebElement ele=Common.findElement(acceptXpath);
	  ele.click();
	 }
	
	public void clickApproveInvoices(String clientName,String ino) {
		Common.sleep(2000);
		 String st="//div[@class='LineItemDataList']//div[contains(text(),'"+clientName+"')]/..//following-sibling::div/div[text()='"+ino+"']/..//following-sibling::div//a[@title='Approve Invoices']";
		  WebElement ele=Common.findElement(st);
		  ele.click();
	}
	
	//verify AR Payment Status matched or not 
	public boolean verifyPaymetStatusMatched(String cname,String paymentid,String status) {
		boolean flag=false;
		  Common.sleep(2000);
		  String xpathForARPayment="//div[@class='LineItemDataList']//div[contains(text(),'"+cname+"')]/..//following-sibling::div/div[text()='"+paymentid+"']/..//following-sibling::div//div[text()='matched']";
		  WebElement findele=Common.findElement(xpathForARPayment);
		  String matchXpath=findele.getText();		  
		  if(matchXpath.equals(status))
		  {
		   flag=true;
		   Reporter.log(" AR Payment status matched on list and verified");
		  }
		  else
		  {
		   System.out.println("payment status not matched");
		  }

		  return flag;
	}
	public void clickOnCreatePaymentLink(String clientName, String invoiceNumber)
	{
		String invoiceLocator ="//div[@class='LineItemDataList']//div[contains(text(),'"+clientName+"')]/..//following-sibling::div/div[text()='"+invoiceNumber+"']/..//following-sibling::div//a[@title='Create Payment']";
		WebElement element=Common.findElement(invoiceLocator);
		element.click();
		Common.sleep(5000);
	} 
	
}
