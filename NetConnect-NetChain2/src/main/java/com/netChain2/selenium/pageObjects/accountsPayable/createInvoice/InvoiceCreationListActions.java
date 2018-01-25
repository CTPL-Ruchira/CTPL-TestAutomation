package com.netChain2.selenium.pageObjects.accountsPayable.createInvoice;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

import com.netChain2.engine.Common;



public class InvoiceCreationListActions {
	public String invmoney;

	public String invoiceAmount() {
		WebElement createinvamt=Common.getElement("CREATE_INVOICE_INVAMT_XPATH");
		invmoney=createinvamt.getText();
		return invmoney;
	}
	public void searchInvoice(String searchValue)
	{
		Common.sendKeys("SEARCH_INPUT_XPATH", searchValue);
	}

	public void clickOnAcceptInvoice(String VendorName, String InvoiceNumber) {
		String InvoiceStatusAcceptInvoice="//div[text()='"+InvoiceNumber+"']/ancestor::div[2]/div[2]/div[text()='"+VendorName+"']/ancestor::div[2]/div[9]/div[@class='text']/div/a[2]";
		WebElement Element2=Common.findElement(InvoiceStatusAcceptInvoice);
		Element2.click();
	}
	//Scroll up
	public static void scrollUp()
	{
		JavascriptExecutor jse = (JavascriptExecutor)Common.getDriver();
		jse.executeScript("scroll(0, -250);");
	}
	//Accept modal button on accept link
	public void ModalAcceptButton() {
		Common.click("MODAL_ACCEPT_BUTTON_XPATH");
	}

	public void clickOnApproveInvoice(String VendorName, String InvoiceNumber) {
		String InvoiceStatusApproveInvoice="//div[text()='"+InvoiceNumber+"']/ancestor::div[2]/div[2]/div[text()='"+VendorName+"']/ancestor::div[2]/div[9]/div[@class='text']/div/a[2]";
		WebElement Element3=Common.findElement(InvoiceStatusApproveInvoice);
		Element3.click();
	}
	public void ModalApproveButton() {
		Common.click("MODAL_APPROVE_BUTTON_XPATH");
	}

	public void clickOnCreatePaymentLink(String VendorName, String InvoiceNumber)
	{
		String InvoiceLocator="//div[text()='"+InvoiceNumber+"']/ancestor::div[2]/div[2]/div[text()='"+VendorName+"']/ancestor::div[2]/div[9]/div[@class='text']/div/a[2]";
		WebElement element=Common.findElement(InvoiceLocator);
		element.click();

	} 
	public void createPaymentButton() {
		Common.click("MODAL_CREATEPAYMENT_BUTTON_XPATH");
	}

	//Alert Message 
	public String gettextValue() {
		return Common.getText("INVOICE_ALERT_MESSAGE_SUCCESSFULLY_XPATH");

	} 
	public void clickOnApprovePayment(String VendorName, String InvoiceNumber) {
		String InvoiceStatusApprovePayment="//div[text()='"+InvoiceNumber+"']/ancestor::div[2]/div[2]/div[text()='"+VendorName+"']/ancestor::div[2]/div[9]/div[@class='text']/div/a[2]";
		WebElement Element4=Common.findElement(InvoiceStatusApprovePayment);
		Element4.click();
	}  
	public void sendPaymentButton() {
		Common.click("INVOICE_SEND_PAYMENT_BUTTON_XPATH");
	}

	//Validation of the modal(invoice number,vendor name & )//
	public boolean verificationModalAcceptButton(String modalinvoicenumber, String vname, String invoicetotalAmount ) {
		WebElement modalinvnmbr=Common.getElement("MODAL_INVNUMBER_XPATH");
		String invnumber=modalinvnmbr.getText();
		System.out.println(invnumber);

		String[] l=invnumber.split("#");
		String m=l[1];


		WebElement balamount=Common.getElement("MODAL_INVAMOUNT_XPATH");		
		String balamount1=balamount.getText();


		WebElement modalVendor=Common.getElement("MODAL_INVVENDORNAME_XPATH");
		String modalVendor1=modalVendor.getText();

		//verification point for vendor name,balance amount and invoice number
		if(m.equalsIgnoreCase(modalinvoicenumber) && modalVendor1.equalsIgnoreCase(vname) && invoicetotalAmount.equalsIgnoreCase(balamount1))
		{

			return true;	

		}
		else {

			return false;	
		}
	}

	public boolean verificationModalApproveButton(String modalinvoicenumber, String vname, String invoicetotalAmount ) {
		WebElement modalinvnmbr=Common.getElement("MODAL_INVNUMBER_XPATH");
		String invnumber=modalinvnmbr.getText();
		System.out.println(invnumber);

		String[] l=invnumber.split("#");
		String m=l[1];
		System.out.println(m);

		WebElement balamount=Common.getElement("MODAL_INVAMOUNT_XPATH");		
		String balamount1=balamount.getText();


		WebElement modalVendor=Common.getElement("MODAL_INVVENDORNAME_XPATH");
		String modalVendor1=modalVendor.getText();
		;

		//verification point for vendor name,balance amount and invoice number
		if(m.equalsIgnoreCase(modalinvoicenumber) && modalVendor1.equalsIgnoreCase(vname) && invoicetotalAmount.equalsIgnoreCase(balamount1))
		{

			return true;	

		}
		else {

			return false;	
		}
	}

	public void bannerClick() {
		Common.click("PAYMENT_BANNER_XPATH");

	}

	//Payment Verfication
	public boolean PaymentVerfication(String invoicenumber,String vendorName,String invoiceAmount ) {

		WebElement bannerInvoiceId=Common.getElement("PAYMENT_BANNER_INVOICE_ID_XPATH");
		String m=bannerInvoiceId.getText();

		System.out.println("Payment Invoice id "+m);
		String[] l=m.split("#");
		String paymentInvoiceId=l[1];


		WebElement bannervendorName=Common.getElement("CREATE_PAYMENT_BANNER_VENDOR_XPATH");
		bannervendorName.getText();
		String payvendorName=bannervendorName.getText();


		WebElement bannerAmount=Common.getElement("PAYMENT_BANNER_AMOUNT_XPATH");
		String payAmountfield=bannerAmount.getText();


		if(paymentInvoiceId.equalsIgnoreCase(invoicenumber) && payvendorName.equalsIgnoreCase(vendorName) && payAmountfield.equalsIgnoreCase(invoiceAmount))
		{

			return true;	

		}
		else {

			return false;	
		}


	}
}    
