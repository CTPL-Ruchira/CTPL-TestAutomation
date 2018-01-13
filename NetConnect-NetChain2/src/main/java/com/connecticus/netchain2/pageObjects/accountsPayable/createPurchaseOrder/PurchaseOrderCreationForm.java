package com.connecticus.netchain2.pageObjects.accountsPayable.createPurchaseOrder;

import com.connecticus.engine.Common;
import com.connecticus.netchain2.pageObjects.common.apCreation.APModuleCreation;

/**
 * API's for Purchase Order Creation Form
 * @author Manoj.Kumar
 * 
 *
 */


public class PurchaseOrderCreationForm 
{
	public APModuleCreation createNew() {
		Common.click("CREATENEW_BUTTON_XPATH");
		return new APModuleCreation();
	}
	
	public void selectVendor(String vendorName)
	{
		Common.select("VENDOR_DROPDOWN_XPATH", vendorName);
	}
	
	public void selectLocation(String locationName)
	{
		Common.select("LOCATION_DROPDOWN_XPATH", locationName);
	}
	
	public void selectProductOrServices(String productName)
	{
		Common.select("PRODUCT_SERVICES_DROPDOWN_XPATH", productName);
	}
	
	public void selectDepartment(String departmentName)
	{
		Common.select("PURCHASE_ORDER_DEPARTMENT_DROPDOWN_XPATH", departmentName);
	}
	
	public void selectBookingAccount(String bookingAccountName)
	{
		Common.select("BOOKING_ACCOUNT_DROPDOWN_XPATH", bookingAccountName);
	}
	
	public void setDescription(String description)
	{
		Common.sendKeys("DESCRIPTION_FIELD_XPATH", description);
	}
	
	public void setQualtity(String quantity)
	{
		Common.sendKeys("QTY_FIELD_XPATH", quantity);
	}
	
	public void setRate(String rate)
	{
		Common.sendKeys("RATE_FIELD_XPATH", rate);
	}
	
	public void setMessageToVendor(String message)
	{
		Common.sendKeys("VENDOR_MESSAGE_XPATH", message);
	}
	
	public void setMemo(String memo)
	{
		Common.sendKeys("MEMO_FIELD_XPATH", memo);
	}
	
	public void setApprovalBy(String name)
	{
		Common.select("PO_APPROVALBY_XPATH", name);
	}
	
	public void setShipBy(String name)
	{
		Common.select("PO_SHIPBY_XPATH", name);
	}
	
	//Save purchase order
	public void savePurchaseOrder()
	{
		Common.click("PO_SAVE_BUTTON_XPATH");
	}
	
	
}
