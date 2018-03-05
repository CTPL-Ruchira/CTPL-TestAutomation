package com.netChain2.selenium.pageObjects.accountsPayable.Roles;

import org.openqa.selenium.WebElement;

import com.netChain2.engine.Common;

public class Roles {

	//select value from auto accept dropdown
	public void autoAcceptRoleDropdown(String dropdownValue) {
		Common.select("INVOICE_AUTO_ACCEPT_ROLE_DROPDOWN_XPATH", dropdownValue);
		Common.sleep(2000);	
	}

	//select value from auto approve dropdown
	public void autoApproveRoleDropdown(String dropdownValue) {
		Common.select("INVOICE_AUTO_APPROVE_ROLE_DROPDOWN_XPATH", dropdownValue);
		Common.sleep(2000);	
	}

	//select value from auto create payment dropdown
	public void autoCreatePaymentRoleDropdown(String dropdownValue) {
		Common.select("INVOICE_AUTO_CREATE_PAYMENT_DROPDOWN_XPATH", dropdownValue);
		Common.sleep(2000);	
	}	

	//select value from auto approve payment dropdown	
	public void autoApprovePaymentRoleDropdown(String dropdownValue) {
		Common.select("INVOICE_AUTO_SIGNING_AUTHORITY_DROPDOWN_XPATH", dropdownValue);
		Common.sleep(2000);	
	}

	public void setMessageToVendor(String message)
	{
		Common.sendKeys("VENDOR_MESSAGE_XPATH", message);
	}
	
	public void setMemo(String memo)
	{
		Common.sendKeys("MEMO_FIELD_XPATH", memo);
	}
	  
  	//Click on Save button
    public void Invoice_SaveButton() {
     Common.click("INVOICE_SAVE_BUTTON_XPATH");
  	}
  	
    //Create Rule invoice cancel button
    public void CreateRule_CancelButton() {
    	Common.click("INVOICE_CREATE_RULE_CANCEL_BUTTON_XPATH");
  	}

  public void invoiceCreationFormMessageMemo(String messageToVendor,String memo) {
    
	  //Message to vendor
	   setMessageToVendor(messageToVendor);
	 
	   //Memo
	   setMemo(memo);
	  
	   //Click on save button
	   Invoice_SaveButton();
       
	   //Click on cancel button
	   CreateRule_CancelButton();
   }

  public boolean verificationForAcceptancePending(String vendorName,String invoiceId,String expectedValue ) {
         String acceptancePendingXpath="//table[@class='table']//tr/td[3]//div[text()='"+invoiceId+"']/ancestor::tr/td[4]//div[text()='"+vendorName+"']/ancestor::tr/td[10]//div/a[@title=\"Acceptance Pending\"]";
         WebElement acceptancePending=Common.findElement(acceptancePendingXpath);
         String actualValue=acceptancePending.getText();
         String action=expectedValue;
         if(actualValue.equals(action))
 		{
 			return true;
 			
 		}else {
 			
 			return false;
 			    }
 		
 	}
         }