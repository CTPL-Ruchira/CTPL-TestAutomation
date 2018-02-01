package com.netChain2.selenium.pageObjects.accountsPayable.settings;

import com.netChain2.engine.Common;
import com.netChain2.selenium.pageObjects.common.JavaScriptOperation.JavaScriptUtils;

public class Settings 
{
	public void openSettings()
	{
		Common.click("CLICK_MENU_BUTTON_CLASSNAME");
		Common.sleep(1000);
		Common.click("AP_SETTINGS_XPATH");
	}

	public void createNewCustomWorkflow() 
	{
		clickOnCreateNewCustomWorkflowButton();
		Common.sleep(1000);
		customizeWorkflow();
		
		
	}

	private void customizeWorkflow() 
	{
		setInvoiceAcceptance();
		setInvoiceApproval();
		setCreatePayment();
		setSiningAuthorityPaymentApprover();
		setPaymentRelease();
	}
	
	private void setPaymentRelease() 
	{
		String sourceXPath=getLocatorValue("INVOICE_PAYMENT_RELEASE_SOURCE_PATH");
		String targetXpath=getLocatorValue("INVOICE_WORKFLOW_DESTINATION_PATH");
		JavaScriptUtils.jQueryDragAndDrop(sourceXPath, targetXpath);
		
	}

	private void setSiningAuthorityPaymentApprover() 
	{
		String sourceXPath=getLocatorValue("INVOICE_SININGAUTHORITY_PAYMENTAPPROVER_SOURCE_PATH");
		String targetXpath=getLocatorValue("INVOICE_WORKFLOW_DESTINATION_PATH");
		JavaScriptUtils.jQueryDragAndDrop(sourceXPath, targetXpath);
	}

	private void setCreatePayment() 
	{
		String sourceXPath=getLocatorValue("INVOICE_CREATE_PAYMENT_SOURCE_PATH");
		String targetXpath=getLocatorValue("INVOICE_WORKFLOW_DESTINATION_PATH");
		JavaScriptUtils.jQueryDragAndDrop(sourceXPath, targetXpath);
		
	}

	private void setInvoiceApproval() 
	{
		String sourceXPath=getLocatorValue("INVOICE_APPROVAL_SOURCE_PATH");
		String targetXpath=getLocatorValue("INVOICE_WORKFLOW_DESTINATION_PATH");
		JavaScriptUtils.jQueryDragAndDrop(sourceXPath, targetXpath);
	}

	private void setInvoiceAcceptance()
	{
		String sourceXPath=getLocatorValue("INVOICE_ACCEPTANCE_SOURCE_PATH");
		String targetXpath=getLocatorValue("INVOICE_WORKFLOW_DESTINATION_PATH");
		JavaScriptUtils.jQueryDragAndDrop(sourceXPath, targetXpath);
	}
	
	private String getLocatorValue(String xpathName)
	{
		return Common.getObjectValue(xpathName);
	}

	private void clickOnCreateNewCustomWorkflowButton() 
	{
		Common.click("CREATE_NEW_CUSTOM_WORKFLOW_BTN_XPATH");
	}
	
	public void clickOnFinishButton()
	{
		Common.click("INVOICE_CUSTOMWORKFLOW_FINISH_BUTTON_XPATH");
	}

	public void enterValueForInvoiceAcceptance(double amountForAutoAcceptInvoices) 
	{
		enterAmountForAutoAcceptInvoices(amountForAutoAcceptInvoices);
		
	}

	private void enterAmountForAutoAcceptInvoices(double amountForAutoAcceptInvoices) 
	{
		String amountForAutoAccept=String.valueOf(amountForAutoAcceptInvoices);
		Common.sendKeys("AUTO_ACCEPT_INVOICES_INPUT_BOX_XPATH", amountForAutoAccept);
		
	}
}
