package com.netChain2.selenium.pageObjects.accountsReceivable.createSalesOrder;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;
import com.netChain2.engine.Common;
import com.netChain2.selenium.pageObjects.common.components.CommonMethods;

public class SOCreationForm {
		private String clientName;
	
		public String getClientName() 
		{
			return clientName;
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
		
		public Boolean verifySalesOrderOnList(String expectedSoNo) 
		 {
		  boolean flag=false;
		  Common.sleep(6000);
		  CommonMethods.searchByNumberOrName(expectedSoNo);
		  Common.sleep(2000);
		  
		  String actualSoNoOnList=Common.getText("AR_SO_NUMBER_ON_LIST_XPATH");
		  if(actualSoNoOnList.equals(expectedSoNo))
		  {
		   flag=true;
		   System.out.println("SO displayed on list");
		   Reporter.log("SO present on list and verified");
		  }
		  else
		  {
		   System.out.println("SO not created");
		  }

		  return flag;

		 }
		public boolean verifyTotalAmountCalculatedAndShown(double Amount)
		{
			String amountDisplayed=Common.getText("AR_SO_AMOUNT_XPATH");
			String appendDollarSign="$"+Common.roundNumberToTwoDecimalValue(Amount);
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
