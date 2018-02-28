	package com.netChain2.selenium.pageObjects.accountsPayable.createPurchaseOrder;

import org.openqa.selenium.WebElement;
import com.netChain2.engine.BaseTestCase;
import com.netChain2.engine.Common;
import com.netChain2.selenium.pageObjects.common.apCreation.APModuleCreation;
import com.netChain2.selenium.pageObjects.common.components.CommonMethods;


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
	private static int secondFlag=1, thirdFlag=1;
	private static double previousAmount;
	private static double currentAmount;
	private static int poNumber;
	private static WebElement qualityElement;
	private static WebElement rateElement;

	private String amountElement;
	private String vendorName;
	private String vendorNameDropdown;
	private String productName;
	private String productNameDropdown;
	
	
	public String getProductNameDropdown() {
		return productNameDropdown;
	}
	public String getProductName() {
		return productName;
	}
	public String getVendorNameDropdown() {
		return vendorNameDropdown;
	}
	public String getVendorName() {
		return vendorName;

	}
	public String getAmountElement() {
		return amountElement;
	}


	
	public static double getPreviousAmount() {
		return previousAmount;
	}
	
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
		CommonMethods.scrollUp();
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
		String dropLocator="//div[@class='productService']/div[contains(@class,'Line')]["+flag+"]/select[1]";
		
		String valueLocator="//div[@class='productService']/div[contains(@class,'Line')]["+flag+"]/select[1]/option[text()='"+productName+"']";
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
		String dropLocator="//div[@class='productService']/div[contains(@class,'Line')]["+flag+"]/select[2]";
		
		String valueLocator="//div[@class='productService']/div[contains(@class,'Line')]["+flag+"]/select[2]/option[text()='"+departmentName+"']";
		selectDropdownValues(dropLocator, valueLocator);
	}
	
	public void selectBookingAccount(String bookingAccountName, int flag)
	{
		String dropLocator="//div[@class='productService']/div[contains(@class,'Line')]["+flag+"]/select[3]";
		
		String valueLocator="//div[@class='productService']/div[contains(@class,'Line')]["+flag+"]/select[3]//option[text()='"+bookingAccountName+"']";
		selectDropdownValues(dropLocator, valueLocator);
	}
	
	public void setDescription(String description, int flag)
	{
		String descLocator="//div[@class='productService']/div[contains(@class,'Line')]["+flag+"]/input[contains(@id,'selectedProductDescription')]";
		WebElement descElement=Common.findElement(descLocator);
		descElement.sendKeys(description);
	}
	
	public void setQualtity(String quantity, int flag)
	{
		String descLocator="//div[@class='productService']/div[contains(@class,'Line')]["+flag+"]/input[contains(@id,'selectedProductQuantity')]";
		qualityElement=Common.findElement(descLocator);
		qualityElement.sendKeys(quantity);
	}
	
	public static String getQualtity(int rowNum)
	{
		String descLocator="//div[@class='productService']/div[contains(@class,'Line')]["+rowNum+"]/input[contains(@id,'selectedProductQuantity')]";
		qualityElement=Common.findElement(descLocator);
		System.out.println("Quality Element : "+qualityElement);
		secondFlag=secondFlag+1;
		
		return qualityElement.getAttribute("value");
	}
	
	public void setRate(String rate, int flag)
	{
		String descLocator="//div[@class='productService']/div[contains(@class,'Line')]["+flag+"]/input[contains(@id,'selectedProductRate')]";
		rateElement=Common.findElement(descLocator);
		rateElement.sendKeys(rate);
		Common.sleep(1000);
		Common.click("PO_AMOUNT_INPUTFIELD_XPATH");
	}
	
	public static String getRate(int rowNum)
	{
		Common.click("PO_AMOUNT_INPUTFIELD_XPATH");
		
		String descLocator="//div[@class='productService']/div[contains(@class,'Line')]["+rowNum+"]/input[contains(@id,'selectedProductRate')]";
		rateElement=Common.findElement(descLocator);
		thirdFlag=thirdFlag+1;
		
		return rateElement.getAttribute("value");
	}
	

	public String getAmountForLine(int flag)
	{
		String descLocator="//div[@class='productService']/div[contains(@class,'Line')]["+flag+"]/input[contains(@id,'product')]";
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
		Common.sleep(2000);
		Common.click("PO_ITEMS_DETAIL_PLUS_BUTTON_XPATH");
	}
	
	//Save purchase order
	public void savePurchaseOrder()
	{
		Common.click("PO_SAVE_BUTTON_XPATH");
	}
	public void saveAndSharePurchaseOrder()
	{
		Common.click("AP_PURCHASE_ORDER_SAVE_SHARE_BUTTON_XPATH");
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
	
	public boolean verifyVendorNameInModalAndDropdown(String value1, String value2)
	{
		if(value2.contains(value1))
		{
			return true;
			
		}else {
			
			return false;
			}
	}
	
	public void setItemDetails(int rowNum, String productName, String departmentName, String bookingAccountName, String description, String measure, String quantity, String rate)
	{
		
		selectProductOrServices(productName, rowNum);
		selectDepartment(departmentName, rowNum);
		selectBookingAccount(bookingAccountName, rowNum);
		setMeasure(measure, rowNum);
		setDescription(description, rowNum);
		setQualtity(quantity, rowNum);
		setRate(rate, rowNum);
		amountElement=getAmountForLine(rowNum);
		Common.sleep(2000);
		currentAmount=Double.parseDouble(getTotalAmountCalculated(rowNum));
		currentAmount=previousAmount+currentAmount;
		previousAmount=currentAmount;
		System.out.println("Current Amount"+currentAmount);
		System.out.println("Previous Amount"+previousAmount);
		calculateTotalAmount(quantity, rate);
	}
	
	public void setItemDetailsWithoutMeasure(int rowNum,String productName, String departmentName, String bookingAccountName, String description, String quantity, String rate)
	{
		
		selectProductOrServices(productName, rowNum);
		selectDepartment(departmentName, rowNum);
		selectBookingAccount(bookingAccountName, rowNum);
		setDescription(description, rowNum);
		setQualtity(quantity, rowNum);
		setRate(rate, rowNum);
		amountElement=getAmountForLine(rowNum);

		Common.sleep(2000);
		currentAmount=Double.parseDouble(getTotalAmountCalculated(rowNum));
		currentAmount=previousAmount+currentAmount;
		previousAmount=currentAmount;
		System.out.println("Current Amount"+currentAmount);
		System.out.println("Previous Amount"+previousAmount);
		calculateTotalAmount(quantity, rate);
	}
	
	
	private void setMeasure(String measure, int flag) 
	{
		String measureLoc="//div[@class='productService']/div[contains(@class,'Line')]["+flag+"]/input[2]";
		WebElement descElement=Common.findElement(measureLoc);
		descElement.sendKeys(measure);
		
	}
	public String getTotalAmountCalculated(int flag)
	{
		String descLocator="//div[@class='productService']/div[contains(@class,'Line')]["+flag+"]/input[contains(@id,'product')]";
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

	public void addProductRuntime(String runtimeProdName,String addCategory,String sku,String selectLoc,String selectDept,String bookingAccnt,String cost,String margin, String attributeName,String attributeName1 )
	{
		//System.out.println("Into addProductRuntime");
		openModal();
		enterProductNameInModal(runtimeProdName);
		setVendornameInModal(attributeName);
		setVendorNameInDrpdown();
		setProductnameInModal(attributeName1);
		setProductNameinDropdown();
		Common.sleep(2000);
		selectCategory(addCategory);
		Common.sleep(2000);
		enterSKU(sku);
		clickTaxablecheckbox();
		Common.sleep(1000);
		setLocationInModal(selectLoc);
		setdepartmentInModal(selectDept);
		setBookingAccountInModal(bookingAccnt);
		enterCost(cost);
		enterMargin(margin);
		Common.sleep(3000);
		clickPoModalSaveButton();
		
		
		
	}
	
	public void openModal() 
	{
		System.out.println("Into openModal");
		String dropLocator="//div[@class='productService']/div[contains(@class,'Line')][1]/select[1]";
		
		String valueLocatorRuntimeProduct="//div[@class='productService']/div[contains(@class,'Line')][1]/select[1]/option[text()=' ADD NEW PRODUCT/SERVICE ']";
		selectDropdownValues(dropLocator, valueLocatorRuntimeProduct);
	}
	
	public void enterProductNameInModal(String runtimeProdName) 
	{
		Common.sleep(2000);
		Common.sendKeys("PO_RUNTIME_PROD_NAME_IN_MODAL_XPATH", runtimeProdName);
	}
	
	public void selectCategory(String addCategory) 
	{
		//System.out.println("value"+value);
		Common.click("PO_RUNTIME_INVENTORY_TEXT_XPATH");
		Common.click("PO_RUNTIME_OPEN_CATEGORY_DROPDOWN_XPATH");
		Common.click("PO_RUNTIME_SELECT_CAT_FROM_DROPDOWN_XPATH");
		Common.sleep(3000);
		Common.sendKeys("PO_RUNTIME_CATEGORY_TEXTBOX_XPATH", addCategory);
				
	}
	
	public void enterSKU(String sku)
	{
		Common.sendKeys("PO_RUNTIME_SKU_FIELD_XPATH", sku);
	}
	
	public void clickTaxablecheckbox() 
	{
		Common.click("PO_RUNTIME_TAXABLE_CHECKBOX_XPATH");
	}
	
	public void setLocationInModal(String selectLoc) 
	{
		Common.select("PO_RUNTIME_LOC_DROPDOWN_IN_MODAL_XPATH", selectLoc);
	}
	
	public void setdepartmentInModal(String selectDept)
	{
		Common.select("PO_RUNTIME_DEPT_DROPDOWN_IN_MODAL_XPATH", selectDept);
	}
	
	public void setBookingAccountInModal(String bookingAccnt)
	{
		Common.select("PO_RUNTIME_BOOKING_ACCOUNT_DROPDOWN_IN_MODAL_XPATH", bookingAccnt);
	}
	
	public void enterCost(String cost)
	{
		Common.sendKeys("PO_RUNTIME_COST_TEXTBOX_XPATH", cost);
	}
	
	public void enterMargin(String margin)
	{
		Common.sendKeys("PO_RUNTIME_MARGIN_TEXTBOX_XPATH", margin);
	}
	
	public void clickPoModalSaveButton()
	{
		Common.click("PO_RUNTIME_SAVE_BUTTON_ON_MODAL_XPATH");
	}
	
	public String setVendornameInModal(String value)
	{
		vendorName=Common.getAttribute("PO_RUNTIME_VENDOR_NAME_FIELD_XPATH", value);
		System.out.println("setVendornameInModal--"+vendorName);
		return vendorName;
	}
	
	public String setVendorNameInDrpdown()
	{
		vendorNameDropdown=Common.getText("PO_RUNTIME_VERIFY_VENDOR_NAME_FROM_DROPDOWN_XPATH");
		System.out.println("setVendorNameInDrpdown--"+vendorNameDropdown);
		return vendorNameDropdown;
	}
	
	public String setProductnameInModal(String value)
	{
		productName=Common.getAttribute("PO_RUNTIME_PROD_NAME_IN_MODAL_XPATH", value);
		System.out.println(" into setProductnameInModal"+productName);
		return productName;
		
	}
	
	public String setProductNameinDropdown()
	{
		productNameDropdown=Common.getText("PO_RUNTIME_VERIFY_PRODUCT_NAME_FROM_DROPDOWN_XPATH");
		//System.out.println("into setProductNameinDropdown"+productNameDropdown);
		return productNameDropdown;
	}
	
	public String getProductNamePresentInDropdown(String productNameFromModal) 
	{
		System.out.println("Selected value : "+Common.getSelecedValue("PRODUCT_SERVICES_DROPDOWN_XPATH"));
		return productNameFromModal;
		
	}
	public void setItemDetailsForSingleLine(String productName, String departmentName, String bookingAccountName, String description, String measure, String quantity, String rate, int flag)
	{
		
		selectProductOrServices(productName, flag);
		selectDepartment(departmentName, flag);
		selectBookingAccount(bookingAccountName, flag);
		setMeasure(measure, flag);
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
	
	public void poCreation(String vendorName, String locationName, String productName, String departmentName, String bookingAccount, String description, String measure, String quantity, String rate, String messageToVendor, String memo, String approvedBy, String shipBy) {

		//select vendor
		Common.selectFromDropdown("VENDOR_DROPDOWN_XPATH", "PO_VENDOR_ALL_DROPDOWN_VALUES_XPATH", vendorName);
	     
		//Select Location
		selectLocation(locationName);
						
		//set details
		setItemDetailsForSingleLine(productName,departmentName,bookingAccount,description, measure,quantity, rate, 1);
		
		setMessageToVendor(messageToVendor);
		setMemo(memo);
		setApprovalBy(approvedBy);
		setShipBy(shipBy);

       //Click on save button
        savePurchaseOrder();
		Common.sleep(7000);
		

	}


}
