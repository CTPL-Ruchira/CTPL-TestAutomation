package com.netChain2.selenium.pageObjects.accountsPayable.createGoodsAndServices;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Reporter;

import com.netChain2.engine.BaseTestCase;
import com.netChain2.engine.Common;


public class GoodsAndServicesCreationForm {
	//Click Menu button
	public void clickMenuButton() {
		Common.click("CLICK_MENU_BUTTON_CLASSNAME");
	}
	//click Goods and Services lable
	public void clickGoodsAndServices() {
		Common.click("CLICK_GOODS_AND_SERVICES_XPATH");
	}
	//click things we buy lable
	public void clickThingsWeBuy() {
		Common.click("CLICK_THINKS_WE_BYE_XPATH");
	}
	//click Add new(Goods) redioButton
	public void clickAddNewGoods() {
		Common.click("CLICK_ADD_NEW_GOODS_REDIOBUTTON_XPATH");
	}
	//click Add new(Service)redioButton
	public void clickAddNewService() {
		Common.click("CLICK_ADD_NEW_SERVICES_REDIOBUTTON_XPATH");
	}
	//click Product Bundle (No)Option
	public void clickProductBundleNo() {
		Common.click("CLICK_PRODUCT_BUNDLE_NO_XPATH");
	}
	//CLICK product bundle(Yes)Option
	public void selectProductBundleYes(String value) {
		Common.selectFromDropdown("SELECT_PRODUCT_BUNDLE_XPATH", "SELECT_PRODUCT_BUNDLE_OPTION_XPATH", value);
	}
	//click inventory item(Yes)Option
	public void clickInventoryItemYes() {
		Common.click("CLICK_INVENTORY_ITEM_YES_XPATH");
	}
	//click inventory item(no)Option
	public void clickInventoryItemNO() {
		Common.click("CLICK_INVENTORY_ITEM_NO_XPATH");
	}
	//Set Product Name
	public void setProductName(String value) {
		Common.sendKeys("SET_PRODUCT_NAME_ID",value);
	}
	//Select Category Dropdown field
	public void selectCategory(String value) {
		Common.selectFromDropdown("SELECT_CATEGORY_XPATH","SELECT_CATEGORY_OPTION_XPATH", value);
	}
	//Set Add New Category
	public void setAddNewCategory(String value) {
		Common.sendKeys("ADD_NEW_CATEGORY_XPATH", value);
	}
	//Set SKU Value
	public void setSKUValue(String value) {
		Common.sendKeys("SET_SKU_VALUE_ID", value);
	}
	//click PO Number Required option
	public void clickPOnumberRequired() {
		Common.click("CLICK_PO_NUMBER_REQUIRED_XPATH");
	}
	//click taxable option
	public void clickTaxable() {
		Common.click("CLICK_TAXABLE_FEILD_XPATH");
	}
	//select Location for registering product details
	public void selectLocation(String value) {
		Common.selectFromDropdown("SELECT_LOCATION_ID", "SELECT_LOCATION_VALUE_XPATH", value);
	}
	//Select Department
	public void selectDepartment(String value) {
		Common.selectFromDropdown("SELECT_DEPARTMENT_ID","SELECT_DEPT_VALUE_XPATH", value);
	}
	//Select Booking Account Details 
	public void selectBookingAccount(String value) {
		Common.selectFromDropdown("SELECT_BOOKING_ACCOUNT_ID","SELECT_BOOKING_ACCOUNT_OPTION_VALUE_XPATH", value);
	}
	//Set Cost
	public void setCost(String value) {
		Common.sendKeys("SET_COST_ID", value);
	}
	//Set Margin
	public void setMargine(String value) {
		Common.sendKeys("SET_MARGIN_ID", value);
	}
	//click SAVE Button
	public void clickSaveButton() {
		Common.click("GOODS_AND_SERVICES_SAVE_BUTTON_XPATH");
	} 
	//Check Product Is Matched or not
	public void verifyProductName(String valueLoc,String value1) {
		
		boolean flag=false;
		List<WebElement> prodList=Common.getElements(valueLoc);			
		for(int i=1; i<=prodList.size(); i++)
		  {
			String AddedProdLocator="//div[@class='goods']/div"+"["+i+"]";
			WebElement ele=Common.findElement(AddedProdLocator);
			String actualValue=ele.getText().trim();
			
			if(value1.equalsIgnoreCase(actualValue))
			{
				System.out.println("Product is added Successfully");
				Reporter.log("Product is added Successfully");
				flag=true;
				break;
			}
			
		  }
		BaseTestCase.assertTrue(flag, "Product name is not present");
		  }		
	}
