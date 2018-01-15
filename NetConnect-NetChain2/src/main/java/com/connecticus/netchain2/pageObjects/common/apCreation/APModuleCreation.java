package com.connecticus.netchain2.pageObjects.common.apCreation;

import com.connecticus.engine.Common;

public class APModuleCreation {
	
	public void clickAPLink() {
		Common.click("AP_LINK_PARTIALLINKTEXT");
		
	}
	
	public void clickNewVendorLink() {
		Common.click("NEW_VENDOR_LINKTEXT");
		}
	public void clickNewInvoicelink() {
		Common.click("CREATE_NEW_INVOICE_LINK_XPATH");
	}
	
	public void clickNewInvoicelink() {
		Common.click("NEW_INVOICE_XPATH");
	}

	public void clickPurchaseLink() {
		Common.click("NEW_PURCHASE_ORDER_LINK");
		
	}

}
