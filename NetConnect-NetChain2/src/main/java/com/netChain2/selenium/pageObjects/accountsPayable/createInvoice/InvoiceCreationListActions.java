package com.netChain2.selenium.pageObjects.accountsPayable.createInvoice;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import com.netChain2.engine.Common;

public class InvoiceCreationListActions
{
	public String invmoney;

	public String invoiceAmount() {
		WebElement createinvamt=Common.getElement("CREATE_INVOICE_INVAMT_XPATH");
		invmoney=createinvamt.getText();
		return invmoney;
	}
	
	public void searchInvoice(String searchValue)
	{
		Common.sendKeys("INVOICE_SEARCH_BAR_XPATH", searchValue);
		Common.sleep(2000);
}

	public void clickOnAcceptInvoice(String VendorName, String InvoiceNumber) {
		String InvoiceStatusAcceptInvoice="//div[text()='"+InvoiceNumber+"']/ancestor::div[2]/div[2]/div[text()='"+VendorName+"']/ancestor::div[2]/div[9]/div[@class='text']/div/div/a[text()='Accept Invoice']";
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
		String InvoiceStatusApproveInvoice="//div[text()='"+InvoiceNumber+"']/ancestor::div[2]/div[2]/div[text()='"+VendorName+"']/ancestor::div[2]/div[9]/div[@class='text']/div/div/a[text()='Approve Invoices']";
		WebElement Element3=Common.findElement(InvoiceStatusApproveInvoice);
		Element3.click();
	}
	
	public void ModalApproveButton() {
		Common.click("MODAL_APPROVE_BUTTON_XPATH");
	}

	//Create payment link
	public void clickOnCreatePaymentLink(String VendorName, String InvoiceNumber)
	{
		String InvoiceLocator="//div[text()='"+InvoiceNumber+"']/ancestor::div[2]/div[2]/div[text()='"+VendorName+"']/ancestor::div[2]/div[9]/div[@class='text']/div[@class='text']/div/a[text()='Create Payment']";
		WebElement element=Common.findElement(InvoiceLocator);
		element.click();
	} 
	
	//Create payment button
	public void createPaymentButton() {
		Common.click("MODAL_CREATEPAYMENT_BUTTON_XPATH");
	}

	//Alert Message 
	public String gettextValue() {
		return Common.getText("INVOICE_ALERT_MESSAGE_SUCCESSFULLY_XPATH");
	} 
	
	//Click on approve payment 
	public void clickOnApprovePayment(String VendorName, String payid) {
		String InvoiceStatusApprovePayment="//div[text()='"+payid+"']/ancestor::div[2]/div[2]/div[2][text()='"+VendorName+"']/ancestor::div[2]/div[9]/div[@class='text']/a[1]";
		WebElement Element4=Common.findElement(InvoiceStatusApprovePayment);
		Element4.click();
	}  
	
	//Send payment button
	public void sendPaymentButton() {
		Common.click("INVOICE_SEND_PAYMENT_BUTTON_XPATH");
	}

	//Validation of the modal(invoice number,vendor name & )//
	public boolean verificationModalAcceptButton(String modalinvoicenumber, String vname, String invoicetotalAmount ) {
		WebElement modalinvnmbr=Common.getElement("MODAL_INVNUMBER_XPATH");
		String invnumber=modalinvnmbr.getText();

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
   
	//Verification of modal approve button
	public boolean verificationModalApproveButton(String modalinvoicenumber, String vname, String invoicetotalAmount ) {
		WebElement modalinvnmbr=Common.getElement("MODAL_INVNUMBER_XPATH");
		String invnumber=modalinvnmbr.getText();
	
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

	//banner click
	public void bannerClick() {
		Common.click("PAYMENT_BANNER_XPATH");

	}

	//Payment Verification on banner
	public boolean paymentVerfication(String invoicenumber,String vendorName,String invoiceAmount ) {

		WebElement bannerInvoiceId=Common.getElement("PAYMENT_BANNER_INVOICE_ID_XPATH");
		String m=bannerInvoiceId.getText();
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

	//get payment id
	public String getPaymentId() {
       WebElement paymentId=Common.getElement("PAYMENT_BANNER_PAYMENTID_XPATH");
       String paytid=paymentId.getText();
       return paytid;
 }

	//Verification of payment approve module
	public boolean verificationPaymentApprove(String modalinvoicenumber, String vname, String invoicetotalAmount ) {
		WebElement modalinvnmbr=Common.getElement("MODAl_APPROVE_PAYMENT_INVOICE_ID_XPATH");
		String invnumber=modalinvnmbr.getText();
		
		WebElement balamount=Common.getElement("MODAL_APPROVE_PAYMENT_AMOUNT_XPATH");		
		String balamount2=balamount.getText();
        
        String[] l=invoicetotalAmount.split("\\$");
		String totalAmount=l[1];
        
		WebElement modalVendor=Common.getElement("MODAL_APPROVE_PAYMENT_VENDOR_NAME_XPATH");
		String modalVendor1=modalVendor.getText();
		
		//verification point for vendor name,balance amount and invoice number
		if(invnumber.equalsIgnoreCase(modalinvoicenumber) && modalVendor1.equalsIgnoreCase(vname) && totalAmount.equalsIgnoreCase(balamount2))
		{

			return true;	

		}
		else {

			return false;	
		}
	
		}
	
	
	public void sendPaymentButton_ScheduleModal() {
		Common.click("INVOICE_SEND_PAYMENT_BUTTON_SCHEDULEMODULE_XPATH");
	}

    //Search Payment by id
	public void SearchPaymentId(String value) {
		Common.sendKeys("PAYMENT_SEARCH_XPATH", value);
		Common.sleep(3000);
	}
		

	//Verification on processing link
	public boolean verificationOnProcessingLink(String VendorName, String Payid,String expectedValue)
	{
		String invoiceProcessingLocator="//div[text()='"+Payid+"']/ancestor::div[2]/div[2]/div[text()='"+VendorName+"']/ancestor::div[2]/div[9]/div[@class='text']";
		WebElement Pro=Common.findElement(invoiceProcessingLocator);
	    String actionPayment=Pro.getText();
	    String action=expectedValue;
        if(actionPayment.equals(action))
		{
			return true;
			
		}else {
			
			return false;
			    }
		
	}
	
	//verification of Status sent
	public boolean verificationOfStatusSent(String VendorName, String Payid,String expectedValue) {
		String actionStatusLocator="//div[text()='"+Payid+"']/ancestor::div[2]/div[2]/div[text()='"+VendorName+"']/ancestor::div[2]/div[6]/div[2][text()='sent']";
		WebElement sent=Common.findElement(actionStatusLocator);		
		String statusSent=sent.getText();
        String status=expectedValue;
        if(statusSent.equals(status))
		{
			return true;
			
		}else {
			
			return false;
			    }
	
	}

	//schedule payment button
	public void schedulePaymentButton() {
		Common.click("SCHEDULE_PAYMENT_BUTTON_XPATH");
	    
	}

 //Verification of status schedule
	public boolean verificationOfStatusSchedule(String VendorName, String PayId) {
		String actionStatusLocator="//div[text()='"+PayId+"']/ancestor::div[2]/div[2]/div[text()='"+VendorName+"']/ancestor::div[2]/div[6]/div[2]";
		WebElement schedule=Common.findElement(actionStatusLocator);		
		String statuschedule=schedule.getText();
        String status="scheduled";
        if(statuschedule.equals(status))
		{
			return true;
			
		}else {
			
			return false;
			    }
	
	}

	       //Verification of create payment link
	public void verificationOnSendPaymentLink(String VendorName, String PayId)
	{
		String invoiceSendPaymentLocator="//div[text()='"+VendorName+"']/ancestor::div[2]/div[3]/div[text()='"+PayId+"']/ancestor::div[2]/div[9]/div/a[1]";
		WebElement sendPayment=Common.findElement(invoiceSendPaymentLocator);		
		sendPayment.click();
       }

	  //Verification schedule payment
	   public boolean verificationSchedulePayment(String modalinvoicenumber, String vname, String invoicetotalAmount,String payid) {
		WebElement modalinvnmbr=Common.getElement("MODAl_APPROVE_PAYMENT_INVOICE_ID_XPATH");
		String invnumber=modalinvnmbr.getText();
	
		WebElement balamount=Common.getElement("MODAL_APPROVE_PAYMENT_AMOUNT_XPATH");		
		String balamount2=balamount.getText();
       
        String[] l=invoicetotalAmount.split("\\$");
		String totalAmount=l[1];
	
		WebElement pid=Common.getElement("PAYMENT_ID_NO_XPATH");
		String paymentid=pid.getText();
		
		WebElement modalVendor=Common.getElement("MODAL_APPROVE_PAYMENT_VENDOR_NAME_XPATH");
		String modalVendor1=modalVendor.getText();
		
        //verification point for vendor name,balance amount and invoice number
		if(invnumber.equalsIgnoreCase(modalinvoicenumber) && modalVendor1.equalsIgnoreCase(vname) && totalAmount.equalsIgnoreCase(balamount2) && paymentid.equals(payid))
		{

			return true;	

		}
		else {

			return false;	
		}
	
	}
}
	

