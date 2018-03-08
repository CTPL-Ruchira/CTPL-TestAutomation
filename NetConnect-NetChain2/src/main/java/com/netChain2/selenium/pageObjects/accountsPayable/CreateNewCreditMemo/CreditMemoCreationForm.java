package com.netChain2.selenium.pageObjects.accountsPayable.CreateNewCreditMemo;

import org.openqa.selenium.WebElement;
import com.netChain2.engine.Common;

public class CreditMemoCreationForm {

	// select vendor name from dropdown in Credit memo
	public void selectVendor(String selectvendor) {
		Common.sleep(2000);
		Common.select("AP_CREDIT_MEMO_SELECT_VENDOR_XPATH", selectvendor);
	}

	// Select Invoice No for credit memo
	public void selectInvoiceNo(String selectInvoiceNo) {
		Common.selectFromDropdown("AP_CREDIT_MEMO_SELECT_INVOICE_NO_XPATH","AP_CREDIT_MEMO_SELECT_INVOICE_NO_OPTION_XPATH", selectInvoiceNo);
		Common.sleep(2000);
	}

	// set credit memo id
	public void setcreditMemoNo(String invoiceNo) {
		Common.getElement("AP_CREDIT_MEMO_GET_CREDIT_ID_XPATH").clear();
		Common.click("INVOICE_ITEM_AMOUNT_TEXT_XPATH");
		Common.sendKeys("AP_CREDIT_MEMO_GET_CREDIT_ID_XPATH", invoiceNo);
		Common.click("INVOICE_ITEM_AMOUNT_TEXT_XPATH");
	}

	// set setMemo value for item deatils
	public void setmemo(String memo) {
		Common.sendKeys("AP_CREDIT_MEMO_SET_MEMO_XPATH", memo);
	}

	// SET Credit Qty
	public void setCreditQty(String creditQty) {
		Common.sendKeys("AP_CREDIT_MEMO_CREDIT_QTY_XPATH", creditQty);
	}

	// Set Rate
	public void setCreditRate(String creditRate) {
		Common.sendKeys("AP_CREDIT_MEMO_CREDIT_RATE_XPATH", creditRate);
	}

	// click Save button on credit memo
	public void saveButton() {
		Common.click("AP_CREDIT_MEMO_SAVE_BUTTON_XPATH");
	}

	// click on Apply
	public void clickApply() {
		Common.click("OPEN_ACCORDIAN_AND_CLICK_APPLY_XPATH");
	}

	// verify Title
	public boolean verifyTitleMatched(String actualTitleValue, String expectedTitleValue) {
		if (actualTitleValue.equals(expectedTitleValue)) {
			return true;
		}
		else 
		{
			return false;
		}
	}

	public Boolean verifyCreditMemoOnList(String creditNo) 
	{
		boolean flag = false;
		Common.sleep(2000);
		String xpath = "//table[@class='table']//tr/td[2]//div[text()='" + creditNo + "']";
		WebElement actualCredit = Common.findElement(xpath);
		String actualCreditMemoList = actualCredit.getText();
		String[] crid = actualCreditMemoList.split("#");
		String part2 = crid[1].trim();

		if (creditNo.equalsIgnoreCase(part2))
		{
			flag = true;
		}
		else
		{
			System.out.println("Credit Memo is not created");
		}

		return flag;

	}

	// Click on Accordian
	public void clickAccordian(String vendorName, String invoiceNumber)
	{
		String accordian = "//table[@class='table']/tbody/tr/td[4]//div[text()='" + vendorName+ "']/ancestor::tr/td[3]//div[text()='" + invoiceNumber + "']/ancestor::tr/td[1]//i";
		WebElement ele = Common.findElement(accordian);
		ele.click();

	}

	// verify invoice on list
	public boolean verifyInvoiceOnList(String invoiceNumber, String vendorname) 
	{
		boolean flag = false;
		Common.sleep(2000);
		String xpath = "//table[@class='table']//tr/td[3]//div[text()='"+invoiceNumber+"']/ancestor::tr/td[4]//div[text()='" + vendorname + "']";
		WebElement actualInvoice = Common.findElement(xpath);
		String actualinvoiceList = actualInvoice.getText();
		if (vendorname.equals(actualinvoiceList)) {
			flag = true;
		} 
		else 
		{
			System.out.println("Invoice Present on list");
		}

		return flag;
	}

}
