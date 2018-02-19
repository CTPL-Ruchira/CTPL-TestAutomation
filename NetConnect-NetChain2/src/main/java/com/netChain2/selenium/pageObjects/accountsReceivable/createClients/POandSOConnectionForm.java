package com.netChain2.selenium.pageObjects.accountsReceivable.createClients;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

import com.netChain2.engine.BaseTestCase;
import com.netChain2.engine.Common;
import com.netChain2.selenium.pageObjects.common.components.CommonMethods;

public class POandSOConnectionForm {
	private static int flag=1;
	public void clickAccept(String ClientName, String poNumber) {
		String soSearchAccept="//div[text()='"+poNumber+"']/ancestor::div[2]/div[4]/div[text()='"+ClientName+"']/ancestor::div[2]/div[11]/div/div/button[@id='share']";
		WebElement eleAccept=Common.findElement(soSearchAccept);
		eleAccept.click();
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
		System.out.println(eleAccept);
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
	  CommonMethods.searchByNumberOrName(expectedClientName);
	  Common.sleep(2000);
	  
	  String actualClientNameOnList=Common.getText("AR_CLIENT_LIST_XPATH");
	  if(actualClientNameOnList.equals(expectedClientName))
	  {
	   flag=true;
	   System.out.println("Invoice displayed on list");
	   Reporter.log("Invoice present on list and verified");
	   Reporter.log("Invoice added is present on Netchain Platform and The Connection is done successfully");
	  }
	  else
	  {
	   System.out.println("Invoice not created");
	  }

	  return flag;

	 }
	
}
