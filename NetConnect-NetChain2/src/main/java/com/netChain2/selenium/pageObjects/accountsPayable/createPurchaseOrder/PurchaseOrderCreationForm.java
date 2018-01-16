package com.connecticus.netchain2.pageObjects.accountsPayable.createPurchaseOrder;

import org.openqa.selenium.WebElement;
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
	private static int flag=1;
	private static double previousAmount;
	private static double currentAmount;
	private static int poNumber;
	
	public static int getPoNumber() {
		return poNumber;
	}

	public static void setPoNumber() {
		PurchaseOrderCreationForm.poNumber = Integer.parseInt(Common.getAttribute("PO_NUMBER_XPATH"));
	}

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
		setPoNumber();
	}
	
	public void selectProductOrServices(String productName, int flag)
	{
		String dropLocator="//div[@class='productService']/div[@class='Line']["+flag+"]/select[1]";
		
		String valueLocator="//div[@class='productService']/div[@class='Line']["+flag+"]/select[1]/option[text()='"+productName+"']";
		selectDropdownValues(dropLocator, valueLocator);
		
	}
	
	public void selectDropdownValues(String dropLocator, String valueLocator)
	{
		WebElement dropElement=Common.findElement(dropLocator);
		
		dropElement.click();
		WebElement valElement=Common.findElement(valueLocator);
		
		valElement.click();
		
	}
	
		
	public void selectDepartment(String departmentName, int flag)
	{
		String dropLocator="//div[@class='productService']/div[@class='Line']["+flag+"]/select[2]";
		
		String valueLocator="//div[@class='productService']/div[@class='Line']["+flag+"]/select[2]/option[text()='"+departmentName+"']";
		selectDropdownValues(dropLocator, valueLocator);
		
				
	}
	
	public void selectBookingAccount(String bookingAccountName, int flag)
	{
		String dropLocator="//div[@class='productService']/div[@class='Line']["+flag+"]/select[3]";
		
		String valueLocator="//div[@class='productService']/div[@class='Line']["+flag+"]/select[3]//option[text()='"+bookingAccountName+"']";
		selectDropdownValues(dropLocator, valueLocator);
		
		
	}
	
	public void setDescription(String description, int flag)
	{
		String descLocator="//div[@class='productService']/div[@class='Line']["+flag+"]/input[contains(@id,'selectedProductDescription')]";
		WebElement descElement=Common.findElement(descLocator);
		descElement.sendKeys(description);
		
	}
	
	public void setQualtity(String quantity, int flag)
	{
		String descLocator="//div[@class='productService']/div[@class='Line']["+flag+"]/input[contains(@id,'selectedProductQuantity')]";
		WebElement descElement=Common.findElement(descLocator);
		descElement.sendKeys(quantity);
		
		
	}
	
	public void setRate(String rate, int flag)
	{
		String descLocator="//div[@class='productService']/div[@class='Line']["+flag+"]/input[contains(@id,'selectedProductRate')]";
		WebElement descElement=Common.findElement(descLocator);
		descElement.sendKeys(rate);
		
		Common.click("PO_ITEM_AMOUNT_TEXT_XPATH");
		
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
		
		amount=Common.roundNumberToTwoDecimalValue(Common.multiplyTwoDoubleNumbers(qty, rt));
		return amount;
	}
	
	public void verifyCalculatedTotalAmmount(double enteredQuality, double enteredRate, double calculatedAmount)
	{
		String temp;
		Common.sleep(4000);
		
		System.out.println("QTY = "+Common.getAttribute("QTY_FIELD_XPATH", "value"));
		System.out.println("RATE ="+Common.getAttribute("RATE_FIELD_XPATH", "value"));
		System.out.println("AMMOUNT ="+Common.getAttribute("PO_AMOUNT_INPUTFIELD_XPATH", "value"));
		System.out.println("PO AMMOUNT = "+Common.getText("PO_AMOUNT_XPATH"));
		
		double qualityInDouble=Double.parseDouble(Common.getAttribute("QTY_FIELD_XPATH", "value"));
		double rateInDouble=Double.parseDouble(Common.getAttribute("RATE_FIELD_XPATH", "value"));
		double amountInDouble=Double.parseDouble(Common.getAttribute("PO_AMOUNT_INPUTFIELD_XPATH", "value"));
		String amountDisplayed=Common.getText("PO_AMOUNT_XPATH");
		
		temp="$"+Double.toString(previousAmount);
		
		BaseTestCase.assertTrue(compareTwoValues(enteredQuality,qualityInDouble), "Quality entered in incorrect");
		BaseTestCase.assertTrue(compareTwoValues(enteredRate,rateInDouble), "Rate entered in incorrect");
		BaseTestCase.assertTrue(compareTwoValues(calculatedAmount,amountInDouble), "Amount entered in incorrect");
		BaseTestCase.assertTrue(compareTwoValues(temp, amountDisplayed),"Total Amount didnot matched");
		
	}
	
	public boolean compareTwoValues(double value1, double value2)
	{
		if(value1==value2)
		{
			return true;
			
		}else {
			
			return false;
			}
		
	}
	
	public boolean compareTwoValues(String value1, String value2)
	{
		if(value1.equals(value2))
		{
			return true;
			
		}else {
			
			return false;
			}
		
	}
	
	public void setItemDetails(String productName, String departmentName, String bookingAccountName, String description, String quantity, String rate)
	{
		
		selectProductOrServices(productName, flag);
		selectDepartment(departmentName, flag);
		selectBookingAccount(bookingAccountName, flag);
		setDescription(description, flag);
		setQualtity(quantity, flag);
		setRate(rate, flag);
		Common.sleep(5000);
		currentAmount=Double.parseDouble(Common.getAttribute("PO_AMOUNT_INPUTFIELD_XPATH", "value"));
		currentAmount=previousAmount+currentAmount;
		previousAmount=currentAmount;
		System.out.println("Current Amount"+currentAmount);
		System.out.println("Previous Amount"+previousAmount);
		flag=flag+1;
		
		calculateTotalAmount(quantity, rate);
		
	
	
		
		
	}
	
	
}
