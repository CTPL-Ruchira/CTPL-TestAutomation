package com.connecticus.netchain2.pageObjects.accountsPayable.invoice;

import com.connecticus.engine.Common;
import com.connecticus.netchain2.pageObjects.common.apCreation.APModuleCreation;

public class Invoice {

	
	public APModuleCreation createNew() {
		Common.click("CREATENEW_BUTTON_XPATH");
		return new APModuleCreation();
	}
	
}
