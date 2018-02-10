package com.netChain2.selenium.pageObjects.accountsPayable.createInvoice;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import com.netChain2.engine.Common;


public class CheckTwoWayMatchInvoice {

	
	//Select product
  public void SelectProduct_Invoice(String value) {
	 Common.select("CREATE_INVOICE_SELECT_PRODUCT_XPATH", value);
  }

 //Select department
   public void SelectDepartment_Invoice(String value) {
	   Common.select("CREATE_INVOICE_ITEM_DETAILS_DEPARTMENT_XPATH", value);
   }
  
   //Select Booking account
   public void SelectBookingAccount_Invoice(String value) {
	Common.select("CREATE_INVOICE_BOOKING_ACCOUNT_XPATH", value);
  }

    //Select PO number for invoice
   public void SelectPONumber_Invoice(String value) {
	Common.select("CREATE_INVOICE_PO_NUMBER_XPATH", value);
	  } 
 
   //Select Quantity
   public void SelectQuantity_Invoice(String value) {
	Common.getElement("CREATE_INVOICE_QTY_XPATH").clear();
	Common.click("INVOICE_ITEM_AMOUNT_TEXT_XPATH");
	Common.sendKeys("CREATE_INVOICE_QTY_XPATH", value);
    Common.sleep(2000);
    Common.click("INVOICE_ITEM_AMOUNT_TEXT_XPATH");
   }

 //Select Rate
   public void SelectRate_Invoice(String value) {
	Common.getElement("CREATE_INVOICE_RATE_XPATH").clear();
	Common.click("INVOICE_ITEM_AMOUNT_TEXT_XPATH");
	Common.sendKeys("CREATE_INVOICE_RATE_XPATH", value);
    Common.sleep(2000);
    Common.click("INVOICE_ITEM_AMOUNT_TEXT_XPATH");
   }
   //Search Invoice
   public void searchInvoice(String searchValue)
	{
		Common.sendKeys("SEARCH_INPUT_XPATH", searchValue);
	}
	
   //Check Invoice status
   public boolean CheckInvoiceStatus_CreatePayment(String VendorName, String InvoiceNumber)
	{
		String InvoiceLocator="//div[text()='"+InvoiceNumber+"']/ancestor::div[2]/div[2]/div[text()='"+VendorName+"']/ancestor::div[2]/div[9]/div[@class='text']/div/div/a[2]";
		WebElement element=Common.findElement(InvoiceLocator);
		System.out.println("ele"+element);
        String ActualValue= element.getText();
	    String ExpectedValue="Create Payment";
	    System.out.println(ActualValue);
	   if(ActualValue.equals(ExpectedValue))
			{
				return true;
				
			}else {
				
				return false;
				    }
			
		     }
	 
   //Accept link on invoice
   public void InvoiceAcceptLinkClick(String VendorName, String InvoiceNumber) {
	   String InvoiceStatusAcceptInvoice="//div[text()='"+InvoiceNumber+"']/ancestor::div[2]/div[2]/div[text()='"+VendorName+"']/ancestor::div[2]/div[9]/div[@class='text']/div/div/a[2]";
	   WebElement Element2=Common.findElement(InvoiceStatusAcceptInvoice);
	   Element2.click();
  }
   
   //Accept modal button on accept link
  public void ModalAcceptButton() {
	 Common.click("MODAL_ACCEPT_BUTTON_XPATH");
  }
    
  //Scroll up
  public static void scrollUp()
  {
   JavascriptExecutor jse = (JavascriptExecutor)Common.getDriver();
   jse.executeScript("scroll(0, -250);");
  }
  
  //Check Status discrepant
  public boolean CheckInvoiceStatus_Discrepant(String VendorName, String InvoiceNumber) {
      String InvoiceStatusLocaterDiscrepant="//div[text()='"+InvoiceNumber+"']/ancestor::div[2]/div[2]/div[text()='"+VendorName+"']/ancestor::div[2]/div[5]/div[@class='text']";
       WebElement ele=Common.findElement(InvoiceStatusLocaterDiscrepant);
       String ActualValue= ele.getText();
        Common.sleep(3000);
	    String ExpectedValue="Discrepant";

	   if(ActualValue.equals(ExpectedValue))
			{
				return true;
				
			}else {
				
				return false;
				    }
			
		     }
  
//Check Status accepted for threshhold
  public boolean CheckInvoiceStatus_accepted(String VendorName, String InvoiceNumber) {
      String InvoiceStatusLocaterAccepted="//div[text()='"+InvoiceNumber+"']/ancestor::div[2]/div[2]/div[text()='"+VendorName+"']/ancestor::div[2]/div[5]/div[@class='text']";
       WebElement ele=Common.findElement(InvoiceStatusLocaterAccepted);
       String ActualValue= ele.getText();
        Common.sleep(3000);
	    String ExpectedValue="Accepted";

	   if(ActualValue.equals(ExpectedValue))
			{
				return true;
				
			}else {
				
				return false;
				    }
			
		     }
	    
	    

       }

