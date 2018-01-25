	package com.netChain2.selenium.pageObjects.accountsPayable.createPurchaseOrder;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.netChain2.engine.BaseTestCase;
import com.netChain2.engine.Common;
import com.netChain2.selenium.pageObjects.common.apCreation.APModuleCreation;


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
	private static int secondFlag=1, thirdFlag=1;
	private static double previousAmount;
	private static double currentAmount;
	private static int poNumber;
	private static WebElement qualityElement;
	private static WebElement rateElement;

	
	
	private String amountElement;
	
	
	public String getAmountElement() {
		return amountElement;
	}
	public static String getPreviousAmount() {
		return String.valueOf(previousAmount);
	}
	/*public static double getPreviousAmount() {
		return previousAmount;

	}*/
	
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

	public static String getAmount() {
		return String.valueOf(amount);
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
		qualityElement=Common.findElement(descLocator);
		qualityElement.sendKeys(quantity);
	}
	
	public static String getQualtity()
	{
		String descLocator="//div[@class='productService']/div[@class='Line']["+secondFlag+"]/input[contains(@id,'selectedProductQuantity')]";
		qualityElement=Common.findElement(descLocator);
		System.out.println("Quality Element : "+qualityElement);
		secondFlag=secondFlag+1;
		
		return qualityElement.getAttribute("value");
	}
	
	public void setRate(String rate, int flag)
	{
		String descLocator="//div[@class='productService']/div[@class='Line']["+flag+"]/input[contains(@id,'selectedProductRate')]";
		rateElement=Common.findElement(descLocator);
		rateElement.sendKeys(rate);
		Common.sleep(1000);
		Common.click("PO_ITEM_AMOUNT_TEXT_XPATH");
	}
	
	public static String getRate()
	{
		String descLocator="//div[@class='productService']/div[@class='Line']["+thirdFlag+"]/input[contains(@id,'selectedProductRate')]";
		rateElement=Common.findElement(descLocator);
		thirdFlag=thirdFlag+1;
		
		return rateElement.getAttribute("value");
	}
	

	public String getAmountForLine(int flag)
	{
		String descLocator="//div[@class='productService']/div[@class='Line']["+flag+"]/input[contains(@id,'product')]";
		return Common.findElement(descLocator).getAttribute("value");
		
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
	
	public boolean verifyRoundingOfNumbers(String actualValue, double expectedValue)
	{
		
		System.out.println("actualValue--"+actualValue+"_");
		System.out.println("expectedValue--"+expectedValue+"_");
		
		if(actualValue.equals(String.valueOf(expectedValue)))
		{
			return true;
			
		}else {
			
			return false;
			}
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

		Common.sleep(3000);
		amountElement=getAmountForLine(flag);

		Common.sleep(5000);
		currentAmount=Double.parseDouble(getTotalAmountCalculated(flag));
		currentAmount=previousAmount+currentAmount;
		previousAmount=currentAmount;
		System.out.println("Current Amount"+currentAmount);
		System.out.println("Previous Amount"+previousAmount);
		flag=flag+1;
		
		calculateTotalAmount(quantity, rate);
	}
	
	public String getTotalAmountCalculated(int flag)
	{
		String descLocator="//div[@class='productService']/div[@class='Line']["+flag+"]/input[contains(@id,'product')]";
		qualityElement=Common.findElement(descLocator);
		return qualityElement.getAttribute("value");
	}
	
	public boolean verifyTotalAmountCalculatedAndShown(String Amount)
	{
		String amountDisplayed=getTotalAmountDisplayed();
		String appendDollarSign="$"+Common.roundNumberToTwoDecimalValue(Double.parseDouble(Amount));
		System.out.println("appendDollarSign"+appendDollarSign);
		System.out.println("amountDisplayed"+amountDisplayed);
		if(appendDollarSign.equals(amountDisplayed))
		{
			return true;
		}
		else
		{
			return false;
		}
		
	}
	

	public String getTotalAmountDisplayed()
	{
		return Common.getText("PO_AMOUNT_XPATH");
	}

	
	public void addProductRuntime(String runtimeProdName,String value)
	{
		System.out.println("Into addProductRuntime");
		openModal();
		enterProductNameInModal(runtimeProdName);
		Common.sleep(2000);
		selectCategory(value);
		
		
	}
	
	public void openModal() 
	{
		System.out.println("Into openModal");
		String dropLocator="//div[@class='productService']/div[@class='Line'][1]/select[1]";
		
		String valueLocatorRuntimeProduct="//div[@class='productService']/div[@class='Line'][1]/select[1]/option[text()=' ADD NEW PRODUCT/SERVICE ']";
		selectDropdownValues(dropLocator, valueLocatorRuntimeProduct);
	}
	
	public void enterProductNameInModal(String runtimeProdName) 
	{
		Common.sleep(2000);
		Common.sendKeys("PO_RUNTIME_PROD_NAME_IN_MODAL_XPATH", runtimeProdName);
	}
	
	public void selectCategory(String value) 
	{
		System.out.println("value"+value);
		Common.click("PO_RUNTIME_INVENTORY_TEXT_XPATH");
		Common.click("PO_RUNTIME_OPEN_CATEGORY_DROPDOWN_XPATH");
		Common.click("PO_RUNTIME_SELECT_CAT_FROM_DROPDOWN_XPATH");
		//selectDropdownValues("PO_RUNTIME_OPEN_CATEGORY_DROPDOWN_XPATH", "PO_RUNTIME_SELECT_CAT_FROM_DROPDOWN_XPATH");
		Common.sleep(3000);
		Common.sendKeys("PO_RUNTIME_CATEGORY_TEXTBOX_XPATH", value);
				
	}
	
	
	
	

}
