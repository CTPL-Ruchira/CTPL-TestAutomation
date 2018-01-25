package com.netChain2.selenium.pageObjects.accountsPayable.createInvoice;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import com.netChain2.engine.Common;

public class InvoiceDispute 
{
	private String amount;
	private String balanceDue;
	private String currentAmount;
	private String originalAmount;
	private String bookingAccountAmount;
	
	
	
		
	public String getBookingAccountAmount() {
		return bookingAccountAmount;
	}

	public String getAmount() {
		return amount;
	}

	public String getBalanceDue() {
		return balanceDue;
	}

	public void clickAndOpenInvoicePreview()
	{
		Common.click("INVOICE_SEARCH_VENDOR_NAME_XPATH");
		Common.sleep(2000);
		Common.click("INVOICE_PREVIEW_BANNER_XPATH");
	}
	
	public void openDispute(String productServiceName)
	{
		
		Common.click("INVOICE_OPEN_DISPUTE_BUTTON_XPATH");
		Common.sleep(5000);
		getDisputeButtonLocator(productServiceName).click();
		getBookingAccountAmountFromModal();
		
	}
	
	public void cancelDispute()
	{
		
		Common.click("INVOICE_CANCEl_DISPUTE_XPATH");
		Common.sleep(5000);
		//getDisputeButtonLocator(productServiceName).click();
		//getBookingAccountAmountFromModal();
		
	}
	
	public void approveDispute()
	{
		
		Common.click("INVOICE_RESOLVE_DISPUTE_XPATH");
		Common.sleep(5000);
		//getDisputeButtonLocator(productServiceName).click();
		//getBookingAccountAmountFromModal();
		
	}
	
	private void getBookingAccountAmountFromModal() 
	{
		bookingAccountAmount=Common.getText("INVOICE_OPEN_DISPUTE_BOOKING_AMOUNT_XPATH");
	}

	public WebElement getDisputeButtonLocator(String productServicesName)
	{
		String xpath="//div[@class='Modal']//p[text()='"+productServicesName+"']/ancestor::div[1]/p//a[text()='Dispute']";
		return Common.findElement(xpath);
	}
	
	public void editFieldsForDispute(String prodServicesName, String valueForQuantity, String valueForRate, String valueForComment)
	{
		editQuantity(valueForQuantity, prodServicesName);
		editRate(valueForRate, prodServicesName);
		addCommentToDispute(valueForComment, prodServicesName);
		saveEditedDisputeValues(prodServicesName);
		Common.sleep(2000);
		getAmountAndBalanceDue(prodServicesName);
	}

	private void getAmountAndBalanceDue(String prodServicesName) 
	{
		amount=Common.getText("INVOICE_OPEN_DISPUTE_BALANCE_DUE_XPATH");
		String xpath="//input[@value='"+prodServicesName+"']/ancestor::div[1]/p[8]";
		balanceDue=Common.findElement(xpath).getText();
		
	}

	private void saveEditedDisputeValues(String prodServicesName) 
	{
		String xpathForSaveButton="//input[@value='"+prodServicesName+"']/ancestor::div[1]//p[@class='input-Dispute']";
		Common.findElement(xpathForSaveButton).click();
	}

	private void addCommentToDispute(String valueForComment, String prodServicesName) 
	{
		String xpathForCommentInputField="//input[@value='"+prodServicesName+"']/ancestor::div[1]//input[@name='resonForDesputed']";
		Common.findElement(xpathForCommentInputField).sendKeys(Keys.HOME,Keys.chord(Keys.SHIFT,Keys.END),valueForComment);
	}

	private void editRate(String valueForRate, String prodServicesName) 
	{
		String xpathForQuantityInputField="//input[@value='"+prodServicesName+"']/ancestor::div[1]//input[@name='UnitPrice']";
		Common.findElement(xpathForQuantityInputField).sendKeys(Keys.HOME,Keys.chord(Keys.SHIFT,Keys.END),valueForRate);
	}

	private void editQuantity(String valueForQuantity, String prodServicesName) 
	{
		String xpathForQuantityInputField="//input[@value='"+prodServicesName+"']/ancestor::div[1]//input[@name='Qty']";
		//Common.findElement(xpathForQuantityInputField).clear();
		Common.findElement(xpathForQuantityInputField).sendKeys(Keys.HOME,Keys.chord(Keys.SHIFT,Keys.END),valueForQuantity);	
	}

	public void sendDispute() 
	{
		Common.click("INVOICE_SEND_DISPUTE_PLUS_BTN_XPATH");
		Common.click("INVOICE_SELECT_USER_DIALOG_EMAIL_ADDR_XPATH");
		Common.click("INVOICE_OPEN_DISPUTE_SAVE_SEND_BTN_XPATH");
		
	}

	public boolean verifyValuesAfterSendingDisputeInInVoicePreviewPage(String actualValue, String expectedValue) 
	{
		System.out.println("actualValue--"+actualValue);
		System.out.println("expectedValue--"+expectedValue);
		
		if(actualValue.equals(expectedValue))
		{
			return true;
		}
		else
		{
			return false;
		}
		
		
	}
	
	public boolean verifyRateAfterSendingDisputeInInVoicePreviewPage(String actualValue, String expectedValue) 
	{
		System.out.println("actualValue--"+actualValue+"_");
		System.out.println("expectedValue--"+expectedValue+"_");
		
		expectedValue="$"+expectedValue;
		if(actualValue.equals(expectedValue))
		{
			return true;
		}
		else
		{
			return false;
		}
		
		
	}
	
	public String getQuantityFromProductLineInInvoicePreviewPage(String productName)
	{
		String xpath="//p[text()='"+productName+"']/ancestor::div[1]/p[7]";
		System.out.println("xpath--"+xpath);
		return Common.findElement(xpath).getText();
	}
	
	public String getRateFromProductLineInInvoicePreviewPage(String productName)
	{
		String xpath="//p[text()='"+productName+"']/ancestor::div[1]/p[8]";
		return Common.findElement(xpath).getText();
	}
	
	public String getAmountFromProductLineInInvoicePreviewPage(String productName)
	{
		String xpath="//p[text()='"+productName+"']/ancestor::div[1]/p[9]";
		return Common.findElement(xpath).getText();
	}
	
	public String getCurrentAmountFromInvoiceBannerInInvoicePreviewPage()
	{
		return Common.getText("INVOICE_CURRENT_AMOUNT_XPATH");
	}
	
	public String getOriginalAmountFromInvoiceBannerInInvoicePreviewPage()
	{
		return Common.getText("INVOICE_ORIGINAL_AMOUNT_XPATH");
	}
	
	public String getBalanceDueFromInvoiceBannerInInvoicePreviewPage()
	{
		return Common.getText("INVOICE_BALANCE_DUE_DATE_XPATH");
	}

	public boolean verifyBalanceDueAndOriginalAmountOnModal(String amount2, String bookingAccountAmount2,
			String originalAmount2) {
		System.out.println("amount2"+amount2);
		System.out.println("bookingAccountAmount2"+bookingAccountAmount2);
		amount2=amount2.substring(1);
		bookingAccountAmount2=bookingAccountAmount2.substring(1);
		System.out.println(amount2+"--"+bookingAccountAmount2);
		double calculatedBalanceDue=Double.parseDouble(amount2)+Double.parseDouble(bookingAccountAmount2);
		System.out.println("Amount from ui"+amount2);
		System.out.println("Amount from ui"+bookingAccountAmount2);
		System.out.println("Amount from ui"+originalAmount2);
		System.out.println("Calculated amount"+calculatedBalanceDue);
		System.out.println("originalAmount2"+originalAmount2);
		
		String originalValueCalculated="$"+String.valueOf(calculatedBalanceDue);
		if(originalAmount2.equals(originalValueCalculated))
		{
			return true;
		}
		else
		{
			return false;
		}
		
		
		
	}

		
	public String getOriginalAmountOnList()
	{
		return Common.getText("INVOICE_LIST_ORG_AMOUNT_XPATH");
	}
	
	public String getOriginalBalanceOnList()
	{
		return Common.getText("INVOICE_LIST_BAL_AMOUNT_XPATH");
	}

}
