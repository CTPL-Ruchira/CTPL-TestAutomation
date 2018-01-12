package com.connecticus.netchain2.pageObjects.accountsPayable.createInvoice;

import com.connecticus.engine.Common;
import com.connecticus.netchain2.pageObjects.common.apCreation.APModuleCreation;

public class InvoiceCreationForm {
	
	
	public APModuleCreation createNew() {
		Common.click("CREATENEW_BUTTON_XPATH");
		return new APModuleCreation();
	}
	
	//set product and services 
	public void setProductsAndServices(String value){
    	Common.sendKeys("NEW_VENDOR_COMPANY_PROFILE3_XPATH", value);
    }
	
	//select vendor name from dropdown in invoice page 
	public void SelectVendor(String value) {
	Common.select("SELECT_INVOICE_VENDOR_XPATH", value);
	}
	
	//select invoice number
	public String getTextValueInvoiceNo() {
  		return Common.getText("INVOICE_NUMBER_XPATH");
    
    }
	
	//select Net Term from dropdown 
	public void SelectNetTerm(String value) {
		Common.select("SELECT_INVOICE_NET_TERM_XPATH", value);
		}
	
	//Select Location from dropdown
	public void SelectLocation(String value) {
		Common.select("SELECT_INVOICE_LOCATION_XPATH", value);
		}
	 
	// Select Account Description
	public void AccountDetails_Description(String value) {
	 Common.sendKeys("CREATE_INVOICE_DESC_TEXT_BOX_XPATH", value);
    }
	
	//Select Account amount 
	public void AccountDetails_Amount(String value) {
	Common.sendKeys("CREATE_INVOICE_AMOUNT_FIELD_XPATH", value);
	 }
	
	//Select Product and services 
	public void SelectProductAndServicesDrp(String value) {
		Common.select("CREATE_INVOICE_SELECT_PRODUCT_XPATH", value); 
	}
	
	// Select Item details department
	public void SelectItemDetailsDepartment(String value) {
			Common.select("CREATE_INVOICE_ITEM_DETAILS_DEPARTMENT_XPATH", value);
		}
	 //Select Booking Amount
	 public void SelectBookingAmount(String value) {
			Common.select("CREATE_INVOICE_BOOKING_AMOUNT_XPATH", value);
			}
	 
	/* //Select PO number
	 public void SelectPONumber(String value) {
		Common.select("CREATE_INVOICE_PO_NO_XPATH", value);
	}*/
	
	 // Select Invoice description
	public void Invoice_Description(String value) {
    Common.sendKeys("CREATE_INVOICE_DESC_FIELD_XPATH", value);
    }
	
	//select Measure
	 public void Invoice_SelectMeasure(String value) {
	 Common.sendKeys("CREATE_INVOICE_MEASURE_XPATH", value);
   }
	 //Select Quantity
    public void Invoice_Quantity(String value) {
	 Common.sendKeys("CREATE_INVOICE_QTY_XPATH", value);
   }
  
    //Select Invoice Rate
    public void Invoice_Rate(String value) {
	 Common.sendKeys("CREATE_INVOICE_RATE_XPATH", value);
   }

    //Send Message to Vendor 
    public void Invoice_MessageToVendor(String value) {
	   Common.sendKeys("INVOICE_MSG_TO_VENDOR_XPATH", value);
   }
  
    //Send memo
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
    
    //Create Rule invoice
    public void CreateRule_CancelButton() {
    	Common.click("Invoice_CreateRule_Cancel_Button_XPATH");
  	}
  }


