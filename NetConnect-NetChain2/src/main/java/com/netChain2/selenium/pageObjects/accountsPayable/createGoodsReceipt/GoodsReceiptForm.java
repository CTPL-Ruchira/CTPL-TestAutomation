package com.netChain2.selenium.pageObjects.accountsPayable.createGoodsReceipt;

import com.netChain2.engine.Common;

public class GoodsReceiptForm 
{

	public void createGoodsReceipt(String vendorName, String location, String poNumber, String okQty) 
	{
		Common.sleep(2000);
		selectVendorFromDropdown(vendorName);
		selectLocation(location);
		selectPoNumberFromDropdown(poNumber);
		Common.sleep(5000);
		enterOkQualityForItemDetails(okQty);
		//enterDefectiveQtyForItemDetails(defectiveQty);
		saveGR();
	}
	
	private void saveGR() 
	{
		Common.click("SAVE_BUTTON_XPATH");
	}

	private void enterDefectiveQtyForItemDetails(String defectiveQty) 
	{
		Common.sendKeys("DEFECTIVE_QTY_XPATH", defectiveQty);
	}

	private void enterOkQualityForItemDetails(String okQuality) 
	{
		Common.sendKeys("OK_QTY_XPATH", okQuality);
	}

	private boolean selectPoNumberFromDropdown(String poNumber) 
	{
		Common.select("PO_NUMBER_DROPDOWN_XPATH", poNumber);
		String poNumberFromPage=Common.getSelecedValue("PO_NUMBER_DROPDOWN_XPATH");
		System.out.println("poNumberFromPage"+poNumberFromPage);
		if(poNumberFromPage.equals(poNumber))
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	public void selectVendorFromDropdown(String vendorName)
	{
		Common.selectFromDropdown("VENDOR_DROPDOWN_XPATH", "PO_VENDOR_ALL_DROPDOWN_VALUES_XPATH", vendorName);
	}
	
	public void selectLocation(String locationName)
	{
		Common.select("LOCATION_DROPDOWN_XPATH", locationName);
	}

	public boolean verifyAutopopulatedValuesForVendor(String emailAddress, String location, String streetAddress, String city, String state, String zip) 
	{
		String emailAddrFromPage=(Common.getText("VENDOR_INPUT_ADDRESS_ID"));
		String locationFromPage=Common.getSelecedValue("LOCATION_DROPDOWN_XPATH");
		String mailingStreetAddressFromPage=Common.getText("MAILING_STREET_ADDRESS_ID");
		String mailingCityAddressFromPage=Common.getText("MAILING_ADDRESS_CITY_ID");
		String mailingStateAddressFromPage=Common.getText("MAILING_ADDRESS_STATE_ID");
		String mailingZipAddressFromPage=Common.getText("MAILING_ADDRESS_ZIP_ID");
		
		String shippingStreetAddressFromPage=Common.getText("SHIPPING_STREET_ADDRESS_ID");
		String shippingCityAddressFromPage=Common.getText("SHIPPING_ADDRESS_CITY_ID");
		String shippingStateAddressFromPage=Common.getText("SHIPPING_ADDRESS_STATE_ID");
		String shippingZipAddressFromPage=Common.getText("SHIPPING_ADDRESS_ZIP_ID");
		
		if(emailAddrFromPage.equals(emailAddress))
		{
			return true;
		}
		else if(locationFromPage.equals(location))
		{
			return true;
		}
		else if(mailingStreetAddressFromPage.equals(shippingStreetAddressFromPage) && mailingStreetAddressFromPage.equals(streetAddress))
		{
			return true;
		}
		
		else if(mailingCityAddressFromPage.equals(shippingCityAddressFromPage) && mailingCityAddressFromPage.equals(city))
		{
			return true;
		}
		
		else if(mailingStateAddressFromPage.equals(shippingStateAddressFromPage) && mailingStateAddressFromPage.equals(state))
		{
			return true;
		}
		
		else if(mailingZipAddressFromPage.equals(shippingZipAddressFromPage) && mailingZipAddressFromPage.equals(zip))
		{
			return true;
		}
		return false;
	}

	public void verifyAutopopulatedValuesForPoNumber(String productName, String departmentName, String description, String measure, String poQty) 
	{
		//String getProductNameFromPage=Common.getSelecedValue(locator)
		
	}

}
