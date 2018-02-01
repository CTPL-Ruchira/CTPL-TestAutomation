package com.netChain2.selenium.pageObjects.accountsPayable.createInvoice;

import com.netChain2.engine.Common;
import com.netChain2.selenium.pageObjects.common.apCreation.APModuleCreation;

public class InvoiceCreationForm {
	
	public APModuleCreation createNew() {
		Common.click("CREATENEW_BUTTON_XPATH");
		return new APModuleCreation();
	}
	
	//Set product and services 
	public void setProductsAndServices(String value){
    	Common.sendKeys("NEW_VENDOR_COMPANY_PROFILE3_XPATH", value);
    }
	
	//select vendor name from dropdown in invoice page 
	public void SelectVendor(String value) {
	Common.select("SELECT_INVOICE_VENDOR_XPATH", value);
	}
	
	//select invoice number
	public String getAttributeValueInvoiceNo() {
  		return Common.getAttribute("INVOICE_NUMBER_XPATH");
    
    }
	
	//select Net Term from dropdown 
	public void SelectNetTerm(String value) {
		Common.select("SELECT_INVOICE_NET_TERM_XPATH", value);
		}
	
	//Select Location from dropdown
	public void SelectLocation(String value) {
		Common.select("SELECT_INVOICE_LOCATION_XPATH", value);
		}
	//Select account booking account 
	public void SelectBookingAccount(String value) {
		Common.select("DROPDOWN_BOOKING_ACCOUNT_INVOICE_XPATH", value);
		} 
	
	// Select Account Description
	public void AccountDetails_Description(String value) {
	 Common.sendKeys("CREATE_INVOICE_ACCOUNT_DESCRIPTION_XPATH", value);
    }
	
	//Select Account amount 
	public String AccountDetails_Amount(String value) {
	Common.sendKeys("CREATE_INVOICE_AMOUNT_FIELD_XPATH", value);
	return value;
	 }
	
	
	 //Send Measure for product1
	 public void Invoice_SelectMeasure(String value) {
	 Common.sendKeys("CREATE_INVOICE_MEASURE_XPATH", value);
   }
	 

    // Add line button for adding new product
    public void Add_Line_Button() {
   	 Common.click("CREATE_INVOICE_ADD_LINE_ITEM_DETAILS_XPATH");
     Common.sleep(5000);
    }
   
    //Send Measure for 2nd product
  	 public void Invoice_SelectMeasure2(String value) {
  	 Common.sendKeys("CREATE_INVOICE_MEASURE_2_XPATH", value);
  	}
    
  	 
	//Send measure for 3rd product
	 public void Invoice_SelectMeasure3(String value) {
	  	 Common.sendKeys("CREATE_INVOICE_MEASURE_3_XPATH", value);
	  	}
    
	 //Send Message to Vendor 
      public void Invoice_MessageToVendor(String value) {
	   Common.sendKeys("INVOICE_MSG_TO_VENDOR_XPATH", value);
   }
  
    //Send Memo
    public void Invoice_Memo(String value) {
	   Common.sendKeys("INVOICE_MEMO_XPATH", value);
  }   
  	//Click on Save button
    public void Invoice_SaveButton() {
     Common.click("INVOICE_SAVE_BUTTON_XPATH");
  	}
  	
    //Alert Message 
    public String gettextValue() {
  		return Common.getText("INVOICE_ALERT_MESSAGE_SUCCESSFULLY_XPATH");
    
    }
    
    //Create Rule invoice cancel button
    public void CreateRule_CancelButton() {
    	Common.click("INVOICE_CREATE_RULE_CANCEL_BUTTON_XPATH");
  	}
    

    public boolean verifyTotalAmountCalculatedAndShown(double Amount,double PreviousAmount)
	{
    	PreviousAmount=Common.roundNumberToTwoDecimalValue(PreviousAmount);
		System.out.println("AmountAmount--"+Amount);
		System.out.println("PreviousAmountPreviousAmount--"+PreviousAmount);
		String amountDisplayed=Common.getText("INVOICE_AMOUNT_XPATH");
		double temp=Amount+PreviousAmount;
		String appendDollarSign1="$"+temp;
		System.out.println("appendDollarSign1--"+appendDollarSign1);
		System.out.println("amountDisplayed--"+amountDisplayed);
		if(appendDollarSign1.equals(amountDisplayed))
		{
			return true;
		}
		else
		{
			return false;
		}
    }
    
    
}

