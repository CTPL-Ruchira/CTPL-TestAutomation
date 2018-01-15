package com.connecticus.netchain2.pageObjects.accountsPayable.createPurchaseOrder;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.connecticus.engine.BaseTestCase;
import com.connecticus.engine.Common;
import com.connecticus.netchain2.pageObjects.common.apCreation.APModuleCreation;

/**
 * API's for Purchase Order Creation Form
 * @author Manoj.Kumar
 * 
 *
 */


public class PurchaseOrderCreationForm extends BaseTestCase
{
	private static double qty;
	private static double rt;
	private static double amount;
	private static int flag;
	
	public static double getQty() {
		return qty;
	}

	public static double getRt() {
		return rt;
	}

	public static double getAmount() {
		return amount;
	}

	
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
	
	public void selectProductOrServicesSecondItem(String productName)
	{
		Common.select("PRODUCT_SERVICES_DROPDOWN_SECOND_XPATH", productName);
	}
	
	public void selectProductOrServicesThirdItem(String productName)
	{
		Common.select("PRODUCT_SERVICES_DROPDOWN_THIRD_XPATH", productName);
	}
	
	public void selectDepartment(String departmentName)
	{
		Common.select("PURCHASE_ORDER_DEPARTMENT_DROPDOWN_XPATH", departmentName);
	}
	
	public void selectDepartmentSecondItem(String departmentName)
	{
		Common.select("PURCHASE_ORDER_DEPARTMENT_DROPDOWN_SECOND_XPATH", departmentName);
	}
	
	public void selectDepartmentThirdItem(String departmentName)
	{
		Common.select("PURCHASE_ORDER_DEPARTMENT_DROPDOWN_THIRD_XPATH", departmentName);
	}
	
	public void selectBookingAccount(String bookingAccountName)
	{
		Common.select("BOOKING_ACCOUNT_DROPDOWN_XPATH", bookingAccountName);
	}
	
	public void selectBookingAccountSecondItem(String bookingAccountName)
	{
		Common.select("BOOKING_ACCOUNT_DROPDOWN_SECOND_XPATH", bookingAccountName);
	}
	
	public void selectBookingAccountThirdItem(String bookingAccountName)
	{
		Common.select("BOOKING_ACCOUNT_DROPDOWN_THIRD_XPATH", bookingAccountName);
	}
	
	public void setDescription(String description)
	{
		Common.sendKeys("DESCRIPTION_FIELD_XPATH", description);
	}
	
	public void setDescriptionSecondItem(String description)
	{
		Common.sendKeys("DESCRIPTION_FIELD_SECOND_XPATH", description);
	}
	
	public void setDescriptionThirdItem(String description)
	{
		Common.sendKeys("DESCRIPTION_FIELD_THIRD_XPATH", description);
	}
	
	public void setQualtity(String quantity)
	{
		Common.sendKeys("QTY_FIELD_XPATH", quantity);
	}
	
	public void setQualtitySecondItem(String quantity)
	{
		Common.sendKeys("QTY_FIELD_SECOND_XPATH", quantity);
	}
	
	public void setQualtityThirdItem(String quantity)
	{
		Common.sendKeys("QTY_FIELD_THIRD_XPATH", quantity);
	}
	
	public void setRate(String rate)
	{
		Common.sendKeys("RATE_FIELD_XPATH", rate);
	}
	
	public void setRateSecondItem(String rate)
	{
		Common.sendKeys("RATE_FIELD_SECOND_XPATH", rate);
	}
	
	public void setRateThirdItem(String rate)
	{
		Common.sendKeys("RATE_FIELD_THIRD_XPATH", rate);
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
	
	public void addMoreItem()
	{
		Common.click("PO_ITEMS_DETAIL_PLUS_BUTTON_XPATH");
	}
	
	//Save purchase order
	public void savePurchaseOrder()
	{
		Common.click("PO_SAVE_BUTTON_XPATH");
	}
	
	public double calculateTotalAmount(String quantity, String rate)
	{
		qty=Common.roundNumberToTwoDecimalValue(Double.parseDouble(quantity));
		rt=Common.roundNumberToTwoDecimalValue(Double.parseDouble(rate));
		System.out.println("Calculated"+Common.roundNumberToTwoDecimalValue(Common.multiplyTwoDoubleNumbers(qty, rt)));
		amount=Common.roundNumberToTwoDecimalValue(Common.multiplyTwoDoubleNumbers(qty, rt));
		return amount;
	}
	
	public void verifyCalculatedTotalAmmount(double enteredQuality, double enteredRate, double calculatedAmount)
	{
		Common.sleep(4000);
		
		System.out.println("QTY = "+Common.getAttribute("QTY_FIELD_XPATH", "value"));
		System.out.println("RATE ="+Common.getAttribute("RATE_FIELD_XPATH", "value"));
		System.out.println("AMMOUNT ="+Common.getAttribute("PO_AMOUNT_INPUTFIELD_XPATH", "value"));
		System.out.println("PO AMMOUNT = "+Common.getText("PO_AMOUNT_XPATH"));
		
		double qualityInDouble=Double.parseDouble(Common.getAttribute("QTY_FIELD_XPATH", "value"));
		double rateInDouble=Double.parseDouble(Common.getAttribute("RATE_FIELD_XPATH", "value"));
		double amountInDouble=Double.parseDouble(Common.getAttribute("PO_AMOUNT_INPUTFIELD_XPATH", "value"));
		
		System.out.println("C_QTY = "+enteredQuality);
		System.out.println("C_RATE ="+enteredRate);
		System.out.println("C_AMMOUNT ="+calculatedAmount);
		
		BaseTestCase.assertTrue(compareTwoValues(enteredQuality,qualityInDouble), "Quality entered in incorrect");
		BaseTestCase.assertTrue(compareTwoValues(enteredRate,rateInDouble), "Rate entered in incorrect");
		BaseTestCase.assertTrue(compareTwoValues(calculatedAmount,amountInDouble), "Amount entered in incorrect");
		
		
	}
	
	public boolean compareTwoValues(double value1, double value2)
	{
		if(value1==value2)
		{
			System.out.println("true is printed");
			return true;
			
		}else {
			System.out.println("false is printed");
			return false;
			}
		
	}
	
	public void setItemDetailsForFirstItem(String productName, String departmentName, String bookingAccountName, String description, String quantity, String rate)
	{
		
		selectProductOrServices(productName);
		selectDepartment(departmentName);
		selectBookingAccount(bookingAccountName);
		setDescription(description);
		setQualtity(quantity);
		setRate(rate);
		
		
	}
	
	public void setItemDetailsforSecondItem(String productName, String departmentName, String bookingAccountName, String description, String quantity, String rate)
	{
		
		selectProductOrServicesSecondItem(productName);
		selectDepartmentSecondItem(departmentName);
		selectBookingAccountSecondItem(bookingAccountName);
		setDescriptionSecondItem(description);
		setQualtitySecondItem(quantity);
		setRateSecondItem(rate);
		
		
	}
	
	public void setItemDetailsforThirdItem(String productName, String departmentName, String bookingAccountName, String description, String quantity, String rate)
	{
		
		selectProductOrServicesThirdItem(productName);
		selectDepartmentThirdItem(departmentName);
		selectBookingAccountThirdItem(bookingAccountName);
		setDescriptionThirdItem(description);
		setQualtityThirdItem(quantity);
		setRateThirdItem(rate);
		
		
	}
	
	
}
