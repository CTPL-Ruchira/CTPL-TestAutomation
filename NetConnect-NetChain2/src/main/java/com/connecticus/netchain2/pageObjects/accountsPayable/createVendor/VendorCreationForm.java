package com.connecticus.netchain2.pageObjects.accountsPayable.createVendor;

import com.connecticus.engine.Common;

/**
 * API's for Vendor Creation Form
 * @author Ruchira.Mhaisurkar
 * 
 *
 */
public class VendorCreationForm {
	
	public void setOurCompany(String value) {
	Common.sendKeys("NEW_VENDOR_COMPANY_PROFILE1_XPATH", value);
	}
	
       
	
	
	
    public void setOurMission(String value) {
    	Common.sendKeys("NEW_VENDOR_COMPANY_PROFILE2_XPATH", value);
    	
    }
    
    public void setProductsAndServices(String value){
    	Common.sendKeys("NEW_VENDOR_COMPANY_PROFILE3_XPATH", value);
    }
    
    /**
     * To set values for Company Profile all in one go and click next
     * @param value1
     * @param value2
     * @param value3
     */
    public void setCompanyProfileTab(String value1, String value2,String value3) {
    	Common.sendKeys("NEW_VENDOR_COMPANY_PROFILE1_XPATH", value1);
    	Common.sendKeys("NEW_VENDOR_COMPANY_PROFILE2_XPATH", value2);
    	Common.sendKeys("NEW_VENDOR_COMPANY_PROFILE3_XPATH", value3);
    	
    	Common.click("COMPANY_PROFILE_NEXT_BUTTON_XPATH");
    	 }
    
    public void clickNextButton() {
    	Common.click("COMPANY_PROFILE_NEXT_BUTTON_XPATH");
    }
    
    
}
