package com.netChain2.selenium.pageObjects.accountsReceivable.createSalesOrder;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.netChain2.engine.Common;
import com.netChain2.selenium.pageObjects.accountsPayable.createPurchaseOrder.PurchaseOrderCreationForm;

public class SOCreationForm {
	
		private String soNumber;
	
		public String getSoNumber() {
			return soNumber;
		}

		WebDriver driver=Common.getDriver();
		
		//To select client name from dropdown
		public void selectClientFromDropdown(String clientName) {
			Common.selectFromDropdown("AR_SO_FORM_CLIENT_DROPDOWN_XPATH", "AR_SO_FORM_CLIENT_DROPDOWN_VALUES_XPATH", clientName);
			
		}
		
		//To select location from dropdown 
		public void selectLocFromdropdown(String loc) {
			Select locdrpdown=new Select(driver.findElement(By.id("location")));
			locdrpdown.selectByValue(loc);
			setSoNumber();
		}
		
		private void setSoNumber() {
			soNumber=Common.getAttribute("AR_SO_NUMBER_FIELD_XPATH");
			
		}
		
		//To select mailing address checkbox
		public void tickTheAddressCheckbox() 
		{
			Common.click("AR_ADDRESS_BULLETIN_BUTTON_XPATH");
		}
		
		//To type message in textbox
		public void typeMessage(String msg) 
		{
			Common.sendKeys("AR_SO_MSG_TEXTBOX_XPATH", msg);
		}
		
		//To type memo in textbox
		public void typeMemo(String memo )
		{
			Common.sendKeys("AR_SO_MEMO_TEXTBOX_XPATH", memo);
		}
		
		//To select name from approval by dropdown
		public void approvalBy(String approval)
		{
			Common.select("APPROVALBY_XPATH", approval);
			
		}
		
		//To select shipping company from dropdown
		public void shipBy(String shipping) 
		{
			Select shipByDrpdown=new Select(driver.findElement(By.id("shipBy")));
			shipByDrpdown.selectByValue(shipping);
		}
		
		//To hit SAVE button on the form
		public void clickSaveButton() 
		{
			Common.click("AR_SO_FORM_SAVE_BUTTON_XPATH");
		}
		
		public boolean verifyTotalAmountCalculatedAndShown(double Amount)
		{
			
			String amountDisplayed=Common.getText("AR_SO_AMOUNT_XPATH");
			
			String appendDollarSign="$"+Common.roundNumberToTwoDecimalValue(Amount);
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
		

}
