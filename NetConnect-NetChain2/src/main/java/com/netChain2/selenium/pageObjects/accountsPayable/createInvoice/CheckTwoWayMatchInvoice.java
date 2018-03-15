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
   public void SelectQuantity_Invoice(String quantity) {
	Common.getElement("CREATE_INVOICE_QTY_XPATH").clear();
	Common.click("INVOICE_ITEM_AMOUNT_TEXT_XPATH");
	Common.sendKeys("CREATE_INVOICE_QTY_XPATH", quantity);
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

   //Accept link on invoice
   public void InvoiceAcceptLinkClick(String vendorName, String invoiceNumber) {
	   String InvoiceStatusAcceptInvoice="//div[text()='"+invoiceNumber+"']/ancestor::tr[1]/td[4]//div[text()='"+vendorName+"']/ancestor::tr[1]/td[10]//a[text()='Accept Invoice']";
	   WebElement Element2=Common.findElement(InvoiceStatusAcceptInvoice);
	   Element2.click();
  }
   
   //Accept modal button on accept link
  public void ModalAcceptButton() {
	 Common.click("MODAL_ACCEPT_BUTTON_XPATH");
  }
    
  //UPDATE modal button on PO
  public void updatePoButton() {
	  Common.click("PURCHASE_ORDER_UPDATE_BUTTON_XPATH");
  }
  //Scroll up
  public static void scrollUp()
  {
	  JavascriptExecutor jse = (JavascriptExecutor)Common.getDriver();
	  jse.executeScript("scroll(0, -800);");
  }

  //Check Status discrepant
  public boolean CheckInvoiceStatus_Discrepant(String VendorName, String InvoiceNumber,String expectedvalue) {
	  String invoiceStatusLocaterDiscrepant="//div[text()='"+InvoiceNumber+"']/ancestor::tr[1]/td[4]//div[text()='"+VendorName+"']/ancestor::tr[1]/td[6]//div[@class='text']";
	  WebElement ele=Common.findElement(invoiceStatusLocaterDiscrepant);
	  String ActualValue= ele.getText();
	  Common.sleep(3000);
	  String ExpectedValue=expectedvalue;

	  if(ActualValue.equals(ExpectedValue))
	  {
		  return true;
				
			}else {
				
				return false;
				    }
			
		     }
  
//Check Status accepted for threshhold
  public boolean CheckInvoiceStatus_accepted(String VendorName, String InvoiceNumber) {
      String invoiceStatusLocaterAccepted="//div[text()='"+InvoiceNumber+"']/ancestor::div[2]/div[2]/div[text()='"+VendorName+"']/ancestor::div[2]/div[5]/div[@class='text']";
       WebElement ele=Common.findElement(invoiceStatusLocaterAccepted);
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
//Approve po in invoice
  public void clickOnUnapproveUpdate(String vendorName, String poNumber) {
	  String approvePo="//table[@class='table']//tr/td[3]//div[text()='"+poNumber+"']/ancestor::tr/td[5]//div[text()='"+vendorName+"']/ancestor::tr/td[10]//a[text()='Unapprove/Update']";
	  WebElement Element2=Common.findElement(approvePo);
	  Element2.click();
  }
  //Approve po in invoice
  public void approvePoLinkClick(String VendorName, String InvoiceNumber) {
	  String approvePo="//div[text()='"+InvoiceNumber+"']/ancestor::div[2]/div[2]/div[text()='"+VendorName+"']/ancestor::div[2]/div[9]/div[@class='text']/div/div/a[text()='Approve PO']";
	  WebElement Element2=Common.findElement(approvePo);
	  Element2.click();
  }

  //Approve po link in po
  public void clickapprovePoLinkInPo(String VendorName, String PONumber) {
	  String approvePo="//div[text()='"+PONumber+"']/ancestor::div[2]/div[4]/div[text()='"+VendorName+"']/ancestor::div[2]/div[9]/div[1]/a[text()='Approve']";
	  WebElement Element2=Common.findElement(approvePo);
	  Element2.click();
  }
  public void editPoLinkClick(String VendorName, String InvoiceNumber) {
	   String editPo="//div[text()='"+InvoiceNumber+"']/ancestor::tr[1]/td[4]//div[text()='"+VendorName+"']/ancestor::tr[1]/td[10]//a[text()='Edit PO']";
	   WebElement Element2=Common.findElement(editPo);
	   Element2.click();
}
  public void clickOnVendor(String vendorName, String invoiceNumber) {
	  String clickVendor="//div[text()='"+invoiceNumber+"']/ancestor::tr[1]/td[4]//div[text()='"+vendorName+"']";
	  WebElement Element2=Common.findElement(clickVendor);
	  Element2.click();
  }
  //Select Quantity
  public void enterQuantityEdit_Invoice(String quantity) {
	Common.getElement("INVOICE_EDIT_QUANTITY_XPATH").clear();
	Common.click("INVOICE_ITEM_EDIT_AMOUNT_TEXT_XPATH");
	Common.sendKeys("INVOICE_EDIT_QUANTITY_XPATH", quantity);
   Common.sleep(2000);
   Common.click("INVOICE_ITEM_EDIT_AMOUNT_TEXT_XPATH");
  }
   
  //Select Quantity
  public void enterQuantityRate_Invoice(String quantity) {
	  Common.getElement("INVOICE_EDIT_RATE_XPATH").clear();
	  Common.click("INVOICE_ITEM_EDIT_AMOUNT_TEXT_XPATH");
	  Common.sendKeys("INVOICE_EDIT_RATE_XPATH", quantity);
	  Common.sleep(2000);
	  Common.click("INVOICE_ITEM_EDIT_AMOUNT_TEXT_XPATH");
  }
  public void enterQuantityRatePO(String rate) {
	  Common.getElement("RATE_FIELD_XPATH").clear();
	  Common.click("PO_ITEM_AMOUNT_TEXT_XPATH");
	  Common.sendKeys("RATE_FIELD_XPATH",rate);
	  Common.sleep(5000);
	  Common.click("PO_ITEM_AMOUNT_TEXT_XPATH");
  }
  
  
  //banner click
  public void bannerClick() {
	  Common.click("PAYMENT_BANNER_XPATH");
   }

  //click on edit icon
  public void editInvoiceClick() {
	  Common.click("INVOICE_BANNER_EDIT_INVOICE_XPATH");
  }
  //Edit invoice select po number
  public void editInvoiceSelectPODropdown(String poNumber) {
	  Common.selectFromDropdown("INVOICE_BANNER_EDIT_SELECTPO_DROPDOWN_XPATH","INVOICE_BANNER_EDIT_SELECTPO_DROPDOWN_OPTION_XPATH", poNumber);
	  Common.sleep(4000);
     }
  
  //Edit invoice savebutton
  public void editSaveButton() {
	  Common.click("INVOICE_BANNER_EDIT_SAVE_BUTTON_XPATH");
     Common.sleep(2000);
  }
  
  //Edit invoice MATCH BUTTON
  public void editMatchButton() {
	  Common.click("INVOICE_EDIT_MATCH_BUTTON_XPATH");
      Common.sleep(2000);
  }

   //Get vendor name from PO list
  public String getVendorNameFromPoList()
  {
   String vendorNameFromList1=Common.getText("GET_VENDOR_NAME_FROM_PO_LIST_XPATH");
   return vendorNameFromList1;
   }
  
  //Check Invoice status
  public boolean CheckInvoiceStatus_CreatePayment(String VendorName, String InvoiceNumber,String expectedvalue)
	{
		String invoiceLocator="//div[text()='"+InvoiceNumber+"']/ancestor::tr[1]/td[4]//div[text()='"+VendorName+"']/ancestor::tr[1]/td[10]//a[text()='Create Payment']";
		WebElement element=Common.findElement(invoiceLocator);
	    String ActualValue= element.getText();
	    String expectedValue=expectedvalue;
	   
	   if(ActualValue.equals(expectedValue))
			{
				return true;
				
			}else {
				
				return false;
				    }
			
		     }
//Check Status approved for threshhold
	public boolean CheckInvoiceStatus_approved(String vendorName, String invoiceNumber) {
		//String InvoiceStatusLocaterAccepted="//div[text()='"+InvoiceNumber+"']/ancestor::div[2]/div[2]/div[text()='"+VendorName+"']/ancestor::div[2]/div[5]/div[@class='text']";
		String invoiceStatusLocaterAccepted="//div[text()='"+invoiceNumber+"']/ancestor::tr[1]/td[4]//div[text()='"+vendorName+"']/ancestor::tr[1]/td[6]//div[@class='text']";
		WebElement ele=Common.findElement(invoiceStatusLocaterAccepted);
		String ActualValue= ele.getText();
		Common.sleep(3000);
		String ExpectedValue="Approved";

		if(ActualValue.equals(ExpectedValue))
		{
			return true;

		}else {

			return false;
		}

	}
	
	//Click on approve payment 
		public boolean checkApprovePayment(String vendorName, String paymentId,String expectedValue) {
			String invoiceStatusApprovePayment="//div[text()='"+paymentId+"']/ancestor::tr[1]/td[4]//div[text()='"+vendorName+"']/ancestor::tr[1]/td[10]//a[text()='Approve Payment']";
			WebElement Element4=Common.findElement(invoiceStatusApprovePayment);
			String ActualValue= Element4.getText();
			Common.sleep(3000);
			String ExpectedValue=expectedValue;

			if(ActualValue.equals(ExpectedValue))
			{
				return true;

			}else {

				return false;
			}

		}  
	
	
}