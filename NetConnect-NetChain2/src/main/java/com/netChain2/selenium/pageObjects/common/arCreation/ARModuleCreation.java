package com.netChain2.selenium.pageObjects.common.arCreation;

import com.netChain2.engine.Common;

public class ARModuleCreation {

	public void clickCreateNewButton() {
		Common.click("AR_CREATE_NEW_BUTTON_XPATH");
		
	}

	public void clickARLink() {
		Common.click("AR_CLICK_ACCOUNT_RECEIVABLE_XPATH");
		
	}

		public void clickNewClientLink() {

		Common.click("AR_CLICK_NEW_CLIENT_XPATH");
		
	}
	
	public void clickNewSalesOrderLink() {
	    Common.click("AR_MENU_NEW_SALES_ORDER_LINKTEXT");
	}
}
