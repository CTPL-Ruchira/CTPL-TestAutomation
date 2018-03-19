package com.netChain2.selenium.pageObjects.accountsReceivable.createClients;

import org.openqa.selenium.WebElement;
import org.testng.Reporter;
import com.netChain2.engine.Common;

public class POandSOConnectionForm {
	
	public void clickAccept(String clientName, String poNumber) {
		String soSearchAccept="//div[text()='"+poNumber+"']/ancestor::div[2]/div[4]/div[text()='"+clientName+"']/ancestor::div[2]/div[11]/div/div/button[@id='share']";
		WebElement eleAccept=Common.findElement(soSearchAccept);
		eleAccept.click();
	}
	
	//Get invoice number
	public String getinvoiceNumber() {
		return Common.getAttribute("INVOICE_NUMBER_XPATH");
	}
	
	public void verifySOList(String clientName, String poNumber)
	{
		Common.sleep(3000);
		String st="//table[@class='table']//tr/td[5]//div[text()='"+clientName+"']/ancestor::tr/td[7]//div[text()='"+poNumber+"']//ancestor::tr/td[12]//div/button[@data-tip='Accept']";
		WebElement ele=Common.findElement(st);
		ele.click();
	}
	
	//click on plus sign
	public void clickPlusbutton(String clientName, String poNumber) {
		String plusbutton="//table[@class='table']//tr/td[5]//div[text()='"+clientName+"']/ancestor::tr/td[7]//div[text()='"+poNumber+"']//ancestor::tr/td[12]//div//button/i[@data-tip='Create Invoice']";
		WebElement ele=Common.findElement(plusbutton);
		ele.click();	
	}
	
	public void getClientName(String clientName, String poNumber) 
	{		
	    String soGetClientName="//div[text()='"+poNumber+"']/ancestor::div[2]/div[4]/div[text()='"+clientName+"']";
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
	  Common.sleep(2000);
	  String acceptXpath="//table[@class='table']//tr[1]/td[1]/ancestor::tr/td[4]//div[text()='"+clientName+"']/ancestor::tr/td[10]//div/button[@data-tip='Accept']";
	  //String acceptXpath="//table[@class='table']//tr/td[3]//div[text()='"+ino+"']/ancestor::tr/td[4]//div[text()='"+clientName+"']/ancestor::tr/td[10]//div/button[@data-tip='Accept']";
	  WebElement ele=Common.findElement(acceptXpath);
	  ele.click();
	 }
	
	public void clickApproveInvoices(String clientName,String ino) {
		 String st="//div[@class='LineItemDataList']//div[contains(text(),'"+clientName+"')]/..//following-sibling::div/div[text()='"+ino+"']/..//following-sibling::div//a[@title='Approve Invoices']";
		  WebElement ele=Common.findElement(st);
		  ele.click();
	}
	
	//verify AR Payment Status matched or not 
	public boolean verifyPaymetStatusMatched(String cname,String paymentid,String status) {
		boolean flag=false;
		  Common.sleep(2000);
		  String xpathForARPayment="//table[@class='table']//tr/td[3]//div[text()='"+cname+"']/ancestor::tr/td[4]//div[text()='"+paymentid+"']/ancestor::tr/td[6]//div[text()='matched']";
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
	//
	public void clickOnApproveInvoice(String VendorName, String InvoiceNumber) {
		//String InvoiceStatusApproveInvoice="//div[text()='"+InvoiceNumber+"']/ancestor::div[2]/div[2]/div[text()='"+VendorName+"']/ancestor::div[2]/div[9]/div[@class='text']/div/div/a[text()='Approve Invoices']";
		String InvoiceStatusApproveInvoice="//table[@class='table']//tr[1]/td[1]/ancestor::tr[1]//div[text()='"+VendorName+"']/ancestor::tr[1]//a[text()='Approve Invoices']";
		WebElement Element3=Common.findElement(InvoiceStatusApproveInvoice);
		Element3.click();
	}
	//create payment
	public void clickOnCreatePaymentLink(String VendorName, String InvoiceNumber)
	{
		
		//String InvoiceLocator="//div[text()='"+InvoiceNumber+"']/ancestor::div[2]/div[2]/div[text()='"+VendorName+"']/ancestor::div[2]/div[9]/div[@class='text']/div[@class='text']/div/a[text()='Create Payment']";
		String InvoiceLocator="//table[@class='table']//tr[1]/td[1]/ancestor::tr[1]//div[text()='"+VendorName+"']/ancestor::tr[1]//a[text()='Create Payment']";
		WebElement element=Common.findElement(InvoiceLocator);
		element.click();
	} 
}
