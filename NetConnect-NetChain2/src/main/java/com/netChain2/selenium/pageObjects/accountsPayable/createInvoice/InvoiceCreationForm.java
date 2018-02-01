package com.netChain2.selenium.pageObjects.accountsPayable.createInvoice;

import org.openqa.selenium.WebElement;

import com.netChain2.engine.Common;
import com.netChain2.selenium.pageObjects.common.apCreation.APModuleCreation;
import com.netChain2.selenium.pageObjects.common.components.CommonMethods;

public class InvoiceCreationForm {
	
	
	public APModuleCreation createNew() {
		CommonMethods.scrollUp();
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
	public String getAttributeValueInvoiceNo(){
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
	 Common.sendKeys("CREATE_INVOICE_DESC_TEXT_BOX_XPATH", value);
    }
	
	//Select Account amount 
	public String AccountDetails_Amount(String value) {
		Common.sendKeys("CREATE_INVOICE_AMOUNT_FIELD_XPATH", value);
		return value;
		 }
	
	//Select Product and services for product1
	public void SelectProductAndServicesDrp(String value) {
		Common.select("CREATE_INVOICE_SELECT_PRODUCT_XPATH", value); 
	}
	
	// Select Item details department for product1
	public void SelectItemDetailsDepartment(String value) {
			Common.select("CREATE_INVOICE_ITEM_DETAILS_DEPARTMENT_XPATH", value);
		}
	 
	//Select Booking Amount for product1
	 public void SelectBookingAccount_Item(String value) {
			Common.select("CREATE_INVOICE_BOOKING_ACCOUNT_XPATH", value);
			}
	 
	/* //Select PO number
	 public void SelectPONumber(String value) {
		Common.select("CREATE_INVOICE_PO_NO_XPATH", value);
	}*/
	
	 // Select Invoice description for product1
	public void Invoice_Description(String value) {
    Common.sendKeys("CREATE_INVOICE_DESC_FIELD_XPATH", value);
    }
	
	//Send Measure for product1
	 public void Invoice_SelectMeasure(String value) {
	 Common.sendKeys("CREATE_INVOICE_MEASURE_XPATH", value);
   }
	 //Select Quantity for product1
    public void Invoice_Quantity(String value) {
	 Common.sendKeys("CREATE_INVOICE_QTY_XPATH", value);
   }
  
    //Select Invoice Rate for 1st product
    public void Invoice_Rate(String value) {
	 Common.sendKeys("CREATE_INVOICE_RATE_XPATH", value);
   }

    // Add line button for adding new product
    public void Add_Line_Button() {
   	 Common.click("CREATE_INVOICE_ADD_LINE_ITEM_DETAILS_XPATH");
     Common.sleep(5000);
    }

    //Select 2nd product from product dropdown
    public void SelectProductAndServicesDrp2(String value) {
		Common.select("CREATE_INVOICE_SELECT_PRODUCT_DROPDOWN2_XPATH", value); 
	}
    

     //Select Booking Amount for 2nd product
     public void SelectBookingAccount2_Item(String value) {
		Common.select("CREATE_INVOICE_BOOKING_ACCOUNT_2_XPATH", value);
		}

     //Send description for 2nd product
    public void Invoice_Description2(String value) {
        Common.sendKeys("CREATE_INVOICE_SELECT_DESCRIPTION_2_XPATH", value);
        }
  
    //Send Measure for 2nd product
  	 public void Invoice_SelectMeasure2(String value) {
  	 Common.sendKeys("CREATE_INVOICE_MEASURE_2_XPATH", value);
  	}
    
  	 //Select Quantity for 2nd product
      public void Invoice_Quantity2(String value) {
  	  Common.sendKeys("CREATE_INVOICE_QTY_2_XPATH", value);
  	  }
    
      //Select Rate for 2nd product
       public void Invoice_Rate2(String value) {
  	   Common.sendKeys("CREATE_INVOICE_RATE_2_XPATH", value);
  	  }
  	 
      // Add line button for adding 3rd product
       public void Add_Line_Button_2() {
      	 Common.click("CREATE_INVOICE_ADD_LINE_ITEM_DETAILS_2_XPATH");
         Common.sleep(5000);
       } 
     
       //Select 3rd product from item dropdown 
    public void SelectProductAndServicesDrp3(String value) {
		Common.select("CREATE_INVOICE_SELECT_PRODUCT_DROPDOWN3_XPATH", value); 
	}
    
      //Select Booking Amount for 3rd product
	 public void SelectBookingAccount3_Item(String value) {
			Common.select("CREATE_INVOICE_BOOKING_ACCOUNT_3_XPATH", value);
			}
    
	//Send description for 3rd product
	 public void Invoice_Description3(String value) {
	        Common.sendKeys("CREATE_INVOICE_SELECT_DESCRIPTION_3_XPATH", value);
	        }
	 
	 //Send measure for 3rd product
	 public void Invoice_SelectMeasure3(String value) {
	  	 Common.sendKeys("CREATE_INVOICE_MEASURE_3_XPATH", value);
	  	}
    
	//Select Quantity for 3rd product
     public void Invoice_Quantity3(String value) {
 	  Common.sendKeys("CREATE_INVOICE_QTY_3_XPATH", value);
 	  }
   

     //Select Rate for 3rd product
      public void Invoice_Rate3(String value) {
 	   Common.sendKeys("CREATE_INVOICE_RATE_3_XPATH", value);
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

	public void setVendorDetails(String vendorName, String netTermValue, String locationName) {
		SelectVendor(vendorName);
		SelectNetTerm(netTermValue);
		SelectLocation(locationName);
		
		
	}

	public void setAccountDetails(String bookingAccountType, String description, String amount) {
		SelectBookingAccount(bookingAccountType);
		AccountDetails_Description(description);
		AccountDetails_Amount(amount);
		
	}

	public boolean verifyStatusAndActionForInvoice(String vendorName, String invoiceNo) 
	{
		String xpath="//div[text()='"+vendorName+"']/ancestor::div[2]/div[3]/div[text()='"+invoiceNo+"']/ancestor::div[2]/div[9]/div";
		WebElement ele=Common.findElement(xpath);
		return ele.isDisplayed();
	}
  }


