package com.netChain2.selenium.pageObjects.accountsReceivable.createRefundFlow;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Reporter;

import com.netChain2.engine.Common;

public class RefundFlowCreationForm {

	// Redirect to Ar payment receipt page
	public void setPaymentReceiptvalues(String batchNo, String selectClient, String paymentMethod, String RefNo,
			String desc, String currancy, String Amount) {
		Common.sendKeys("AR_PAYMENT_RECEIPT_BATCHNO_XPATH", batchNo);
		Common.select("AR_PAYMENT_RECEIPT_SELECT_CLIENT_XPATH", selectClient);

		/*
		 * Common.click("AR_PAYMENT_RECEIPT_PAYMENT_DATE_XPATH");
		 * Common.sendKeys("AR_PAYMENT_RECEIPT_PAYMENT_DATE_XPATH", "09252013");
		 */
		Common.select("AR_PAYMENT_RECEIPT_PAYMENT_METHOD_XPATH", paymentMethod);
		Common.sendKeys("AR_PAYMENT_RECEIPT_REF_NO_XPATH", RefNo);
		Common.sendKeys("AR_PAYMENT_RECEIPT_DESC_XPATH", desc);
		Common.sendKeys("AR_PAYMENT_RECEIPT_CURRENCY_XPATH", currancy);
		Common.sendKeys("AR_PAYMENT_RECEIPT_AMOUNT_XPATH", Amount);
	}

	// click on Checkbox of Accordian
	public void clickOnCheckbox() {
		Common.click("AR_PAYMENT_RECEIPT_CLICK_CHECKBOX_XPATH");
	}

	// set Amount paid
	public void setAmountPaid(String amtPaid) {
		Common.sendKeys("AR_PAYMENT_RECEIPT_AMT_PAID_XPATH", amtPaid);
	}

	// click Save button
	public void saveButton() {
		Common.click("AP_CREDIT_MEMO_SAVE_BUTTON_XPATH");
	}

	// GET payment Id
	public String getPaymentReceiptID() {
		return Common.getAttribute("AR_PAYMENT_RECEIPT_PAYMENT_ID_XPATH");
	}

	// get Refund Id
	public String getRefundID() {
		return Common.getText("AR_GET_REFUND_ID_XPATH");
	}

	// verification payment receipt page
	public boolean verifyPaymentReceiptOnList(String paymentreceiptId) {
		boolean flag = false;
		Common.sleep(2000);
		String xpath = "//table[@class='table']//tr/td[2]//div[text()='" + paymentreceiptId + "']";
		WebElement actualInvoice = Common.findElement(xpath);
		String actualinvoiceList = actualInvoice.getText();
		if (actualinvoiceList.equals(paymentreceiptId)) {
			flag = true;
			Reporter.log("Payment receipt genrate on list and verified");
		} else {
			System.out.println("Invoice Present on list");
		}

		return flag;
	}

	// New Refund Page Methods
	public void setRefund(String selectVendor, String selectlocation) {
		Common.select("AP_CREDIT_MEMO_SELECT_VENDOR_XPATH", selectVendor);
		Common.click("AR_NEW_REFUND_CLICK_PAYMENT_XPATH");
		Common.sleep(1000);
		Common.click("AR_NEW_REFUND_NONSELECT_BUTTON_XPATH");
		Common.sleep(1000);
		Common.click("AR_NEW_REFUND_SELECT_PAYMENT_VALUE_XPATH");

		Common.select("LOCATION_DROPDOWN_XPATH", selectlocation);
		Common.click("CLICK_SAME_AS_MAILING_ADDRESS_XPATH");
		Common.sleep(1000);
		Common.click("AR_NEW_REFUND_SAVE_BUTTON_XPATH");
	}

	public void clickAccordian(int rowNum) {
		String rowVal = new Integer(rowNum).toString();
		WebElement ele = Common.findElement("//div[@class='productService']//p[text()='" + rowVal + "']//following-sibling::select[1]");
		Actions action = new Actions(Common.getDriver());
		action.moveToElement(ele, -10, 0).click().perform();
	}

	// verify refund payment status
	public boolean verifyStatusOnPaymentPage(String clientName, String refundid, String status) {
		String refundStatusProcessingLocator = "//table[@class='table']//tr/td[2]//div[text()='" + clientName+ "']/ancestor::tr/td[3]//div[text()='" + refundid+ "']/ancestor::tr/td[8]//div/span[text()=' Processing ']";
		WebElement Pro = Common.findElement(refundStatusProcessingLocator);
		String refundPayment = Pro.getText();
		if (refundPayment.equals(status)) {
			return true;
		} else {
			return false;
		}
	}
}
