package com.netChain2.selenium.pageObjects.accountsPayable.createInvoice;

import com.netChain2.engine.Common;

public class RuntimeAddLocationAndDepartmentForm {
	private String runtimeDepartmentName;
	public String getRuntimeDepartmentName() {
		return runtimeDepartmentName;
	}
	//Select Location Dropdown
	public void selectLocationRuntime(String selectlocation) 
	{
			Common.select("SELECT_INVOICE_LOCATION_XPATH", selectlocation);
	}
	public void manageMailingAddress(String setLocation,String StreetAdd,String city,String state,String pin) {
		Common.sendKeys("AP_INVOICE_LOCATION_ADD_LOCATION_NAME_XPATH", setLocation);
		Common.sendKeys("AP_INVOICE_lOCATION_STREET_ADDRESS_XPATH", StreetAdd);
		Common.sendKeys("AP_INVOICE_LOCATION_CITY_XPATH", city);
		Common.sendKeys("AP_INVOICE_LOCATION_STATE_XPATH", state);
		Common.sendKeys("AP_INVOICE_LOCATION_PINCODE_XPATH", pin);
	}
	public void manageShippingAddress(String StreetAdd,String city,String state,String pin,String dept) {
		Common.sendKeys("AP_INVOICE_LOCATION_SHIPING_ADDRESS_XPATH", StreetAdd);
		Common.sendKeys("AP_INVOICE_LOCATION_SHIPING_CITY_XPATH", city);
		Common.sendKeys("AP_INVOICE_LOCATION_SHIPING_STATE_XPATH", state);
		Common.sendKeys("AP_INVOICE_LOCATION_SHIPING_PINCODE_XPATH", pin);
		Common.sendKeys("AP_INVOICE_LOCATION_ADD_DEPT_XPATH", dept);
	}
	public void clickAddDept() {
		Common.click("AP_INVOICE_LOCATION_CLICK_ADD_DEPT_XPATH");
	}
	public void clickSave() {
		Common.click("AP_INVOICE_LOCATION_CLICK_SAVE_XPATH");
	}
	public boolean ckecklocationAddedSuccessfully(String loc) {
		 boolean flag=false;
		String actuallocationval=Common.getSelecedValue("LOCATION_DROPDOWN_XPATH");
		if(loc.equalsIgnoreCase(actuallocationval)) 
		{
			 flag=true;
			 
		}
		  else
		  {
		   System.out.println("location not created");
		  }
		  return flag;
	}
	
	//Select location dropdown
	/*public void selectDepartmentRuntime(String selectdept) 
	{
		Common.select("AP_INVOICE_DEPARTMENT_DROPDOWN_XPATH", selectdept);
	}*/
	public void SelectItemDetailsDepartment(String value) {
		Common.selectFromDropdown("AP_INVOICE_DEPARTMENT_DROPDOWN_XPATH", "AP_INVOICE_DEPARTMENT_DROPDOWN_OPTION_XPATH", value);
	}
	//set department
	public void setDepartmentruntime(String dept) {
		Common.sendKeys("AP_INVOICE_LOCATION_ADD_DEPT_XPATH", dept);
	}
	public void saveDepartment() {
		Common.click("AP_INVOICE_LOCATION_CLICK_SAVE_XPATH");
	}
	public void addRuntimeDepartmentFromDepartmentDropdown(String runtimeDepartmentName) 
	{
		selectFirstDepartmentFromDropdown();	//remove this method only if >>//This line is added due to having issue in adding department directly. For now we have to first select one existing department then only we can open add department runtime
		addDepartmentFromDeptDropdown(runtimeDepartmentName);
	}
	
	private void addDepartmentFromDeptDropdown(String departmentName) 
	{
		Common.click("ADD_DEPARTMENT_FROM_DROPDOWN_XPATH");
		runtimeDepartmentName=Common.generateRandomString(departmentName);
		Common.sendKeys("ADD_DEPARTMENT_INPUT_DEPARTMENT_FIELD_XPATH", runtimeDepartmentName);
		Common.click("ADD_DEPARTMENT_SAVE_BUTTON_XPATH");
	}
	private void selectFirstDepartmentFromDropdown() 
	{
		Common.click("CREATE_INVOICE_ITEM_DETAILS_DEPARTMENT_XPATH");
		Common.click("INVOICE_FIRST_DROPDOWN_VALUE_XPATH");
		
	}
	
	public boolean verifyDepartmentAddedAfterAddingRuntime(String runtimeDepartmentName) {
		  
		  
		  if(runtimeDepartmentName.equals(Common.getSelecedValue("AP_INVOICE_DEPARTMENT_DROPDOWN_XPATH")))
		  {
		   return true;
		  }else
		  {
		   return false;
		  }
		 }
	
	
	

}
