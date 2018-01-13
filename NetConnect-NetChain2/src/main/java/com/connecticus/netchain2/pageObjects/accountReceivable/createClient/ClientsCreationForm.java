package com.connecticus.netchain2.pageObjects.accountReceivable.createClient;

import com.connecticus.engine.Common;
import com.connecticus.netchain2.pageObjects.common.arCreation.ARModuleCreation;

public class ClientsCreationForm {
	
	public ARModuleCreation createNew() {
		Common.click("AR_CREATE_NEW_PLUSE_BUTTON_XPATH");
		return new ARModuleCreation();
	}
	//Set Company Profile Our Comapny tab
	public void setCompanyProfile(String value1, String value2,String value3){
    	Common.sendKeys("AR_COMP_PROFILE_OUR_COMPANY_XPATH", value1);
    	Common.sendKeys("AR_COMP_PROFILE_OUR_MISSION_XPATH", value2);
    	Common.sendKeys("AR_COMP_PROFILE_PRODUCT_SERVICES_XPATH", value3);	
    }
	public void clickNextButton() {
    	Common.click("AR_COMP_PROFILE_NEXT_BUTTON_XPATH");
    }
	
	
	

}
