package com.netChain2.selenium.pageObjects.common.apCreation;

import com.netChain2.engine.Common;

public class APModuleCreation {
	
	public void clickAPLink() {
		Common.click("AP_LINK_PARTIALLINKTEXT");
		
	}
	
	public void clickNewVendorLink() {
		Common.click("NEW_VENDOR_LINKTEXT");
		
	}
	
	public void clickNewInvoicelink() {
		Common.click("NEW_INVOICE_XPATH");
	}

}
