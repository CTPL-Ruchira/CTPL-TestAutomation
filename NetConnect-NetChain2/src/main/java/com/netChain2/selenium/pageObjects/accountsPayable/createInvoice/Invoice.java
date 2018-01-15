package com.netChain2.selenium.pageObjects.accountsPayable.createInvoice;

import com.connecticus.engine.Common;
import com.connecticus.netchain2.pageObjects.common.apCreation.APModuleCreation;

public class Invoice {
	
	
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
	
	//select Net Term from dropdown 
	public void SelectNetTerm(String value) {
		Common.select("SELECT_INVOICE_NET_TERM_XPATH", value);
		}
	
	//Select Location from dropdown
	public void SelectLocation(String value) {
		Common.select("SELECT_INVOICE_LOCATION_XPATH", value);
		}
	
}
