package com.netChain2.selenium.pageObjects.accountsPayable.createInvoice;

import org.openqa.selenium.WebElement;

import com.netChain2.engine.Common;

public class ThreeWayMatch 
{
	public void acceptInvoiceAndVerifyStatus(String invoiceNumber, String vendorName)
	{
		System.out.println("invoiceNumber"+invoiceNumber);
		Common.sleep(3000);
		Common.click("SORTING_ARROW_XPATH");
		String acceptInvoiceXpath="//div[text()='"+invoiceNumber+"']/ancestor::tr[1]/td[4]//div[text()='"+vendorName+"']/ancestor::tr[1]/td[10]//a[text()='Accept Invoice']";
		WebElement ele=Common.findElement(acceptInvoiceXpath);
		ele.click();
		Common.click("YES_ACCEPT_ONLY_BUTTON_XPATH");
		Common.sleep(5000);
		String createPaymentXpath="//div[text()='"+invoiceNumber+"']/ancestor::tr[1]/td[4]//div[text()='"+vendorName+"']/ancestor::tr[1]/td[10]//a[text()='Create Payment']";
		WebElement ele2=Common.findElement(createPaymentXpath);
		System.out.println("Ghhah"+ele2.getText());
				
	}
}
