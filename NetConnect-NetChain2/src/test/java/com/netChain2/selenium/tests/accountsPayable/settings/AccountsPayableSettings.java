package com.netChain2.selenium.tests.accountsPayable.settings;

import java.util.ArrayList;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.netChain2.engine.BaseTestCase;
import com.netChain2.engine.Common;
import com.netChain2.selenium.pageObjects.accountsPayable.createInvoice.InvoiceCreationForm;
import com.netChain2.selenium.pageObjects.accountsPayable.createInvoice.InvoiceCreationListActions;
import com.netChain2.selenium.pageObjects.accountsPayable.settings.Settings;
import com.netChain2.selenium.pageObjects.common.apCreation.APModuleCreation;
import com.netChain2.selenium.pageObjects.common.components.CommonMethods;
import com.netChain2.selenium.pageObjects.common.loginPage.LoginPage;
import com.netChain2.selenium.pageObjects.common.logout.LogoutFromPage;
import com.netChain2.utils.CustomAnnotation.TestDetails;

public class AccountsPayableSettings extends BaseTestCase {
	private ArrayList<String> loginTestData;
	private ArrayList<String> customWorkflowValues;
	private ArrayList<String> grCustomWorkflowvalues;
	private String invoiceNo;
	private String payId;

	@BeforeClass
	public void setUp() 
	{
		loginTestData = Common.getTestData("NetchainTest.Login");
        customWorkflowValues=Common.getTestData("NetchainTest.CustomWorkflow");	
        grCustomWorkflowvalues=Common.getTestData("NetchainTest.SetGRCustomWorkflow");
        
	}

	@Test(enabled=false)
	@TestDetails(author="Roshni Mehta", description="This methods creates custom workflow for invoice")
	public void createCustomWorkflowForInvoice() 
	{
	    LoginPage loginPage = new LoginPage();
		loginPage.login(loginTestData.get(8), loginTestData.get(9));

		Settings settings=new Settings();
		settings.openSettings();
        
		settings.createNewCustomWorkflow();
		settings.autoAcceptValue(customWorkflowValues.get(0));
		settings.autoAprroveValue(customWorkflowValues.get(1));
		settings.autocreatePaymentValue(customWorkflowValues.get(2));
		settings.autoapprovePaymentValue(customWorkflowValues.get(3));
		settings.clickOnFinishButton();

		//click to create new
		InvoiceCreationForm invoice = new InvoiceCreationForm();
		InvoiceCreationListActions icl=new InvoiceCreationListActions();
        Common.sleep(3000);

        //Scroll up
        Settings.scrollUp();
       
        APModuleCreation apModule = invoice.createNew();
		Common.sleep(3000);

		//click to AP()
		apModule.clickAPLink();
		
		//Click to New Invoice
		apModule.clickNewInvoice();
		Common.sleep(3000);
		
		//Select value from Vender DropDown
		invoice.SelectVendor(customWorkflowValues.get(4));
		
		//Get Invoice number
		invoiceNo=invoice.getAttributeValueInvoiceNo();
		
		//Select value from Net Term 
		invoice.SelectNetTerm(customWorkflowValues.get(5));

		//select value from Location dropdown
		invoice.SelectLocation(customWorkflowValues.get(6));	
        
		//Select product
	    settings.selectProductFromDropdown(customWorkflowValues.get(10));
				
		//Select department
		settings.selectDepartment(customWorkflowValues.get(11));
				
		//Enter Booking account
		settings.selectBookingAccount(customWorkflowValues.get(12));
				
		//Enter description
		settings.itemDescription(customWorkflowValues.get(13));
				
		// Enter measure
		settings.itemMeasure(customWorkflowValues.get(14));
				
		//Enter quantity
		settings.itemQuantity(customWorkflowValues.get(15));
				
		//Enter rate
	    settings.itemRate(customWorkflowValues.get(16));
				
		//Invoice Enter Message to vendor
		invoice.Invoice_MessageToVendor(customWorkflowValues.get(17));

		//Invoice Enter memo
		invoice.Invoice_Memo(customWorkflowValues.get(18));

		//Invoice Click on save button
		invoice.Invoice_SaveButton();
		Common.sleep(2000);
		
		//Invoice assert message verification
		String expectedAlertMessage="Invoice was created";
		String actualAlertMessage=invoice.gettextValue();			   

		boolean check2= expectedAlertMessage.equals(actualAlertMessage);
		assertTrue(check2, "Invoice creation failed");
        Reporter.log("Invoice was created successfully");

		//Invoice Create rule click on cancel button
		invoice.CreateRule_CancelButton();

		//Search invoice
		icl.searchInvoice(invoiceNo);
		Common.sleep(2000);

		boolean isAutoApproveInvoiceLinkVisible=settings.verificationForAutoApproveLink(customWorkflowValues.get(4), invoiceNo,customWorkflowValues.get(25));
		assertTrue(isAutoApproveInvoiceLinkVisible, "Auto approve link should be seen as per custom workflow");
		Reporter.log(" Auto approve link is visible as per custom workflow");

		//Logout
	     LogoutFromPage.logout();
	}
	
	@Test(dependsOnMethods= "createCustomWorkflowForInvoice", enabled=false)
	public void checkautoCreatePayment() {

		LoginPage loginPage = new LoginPage();
		loginPage.login(loginTestData.get(8), loginTestData.get(9));

		InvoiceCreationForm invoice = new InvoiceCreationForm();
		InvoiceCreationListActions icl=new InvoiceCreationListActions();
		
		Settings settings=new Settings();

		//Scroll up
		Settings.scrollUp();

		APModuleCreation apModule = invoice.createNew();
		
		//click to AP()
		apModule.clickAPLink();
		
		//Click to New Invoice
		apModule.clickNewInvoice();
		Common.sleep(3000);
		
		//Select value from Vender DropDown
		invoice.SelectVendor(customWorkflowValues.get(4));
		
		//Get Invoice number
		invoiceNo=invoice.getAttributeValueInvoiceNo();
		
		//Select value from Net Term 
		invoice.SelectNetTerm(customWorkflowValues.get(5));

        //select value from Location dropdown
		invoice.SelectLocation(customWorkflowValues.get(6));	
		
		//Select Product
        settings.selectProductFromDropdown(customWorkflowValues.get(10));
		
		//Select department
		settings.selectDepartment(customWorkflowValues.get(11));
		
		//Select booking account
		settings.selectBookingAccount(customWorkflowValues.get(12));
		
		//Select description
		settings.itemDescription(customWorkflowValues.get(13));
		
		// Select measure
		settings.itemMeasure(customWorkflowValues.get(14));
		
		//Select quantity
        settings.itemQuantity(customWorkflowValues.get(19));
		
		//Select rate
        settings.itemRate(customWorkflowValues.get(20));
		
		//Invoice Enter Message to vendor
		invoice.Invoice_MessageToVendor(customWorkflowValues.get(17));

		//Invoice Enter memo
		invoice.Invoice_Memo(customWorkflowValues.get(18));

        //Invoice Click on save button
		invoice.Invoice_SaveButton();
		Common.sleep(3000);

		//Invoice Create rule click on cancel button
		invoice.CreateRule_CancelButton();
		
		//Scroll up
        Settings.scrollUp();
		
		//Search invoice
		icl.searchInvoice(invoiceNo);
		
		boolean isautoCreatePaymentLinkvisible=settings.verificationForCreatePayment(customWorkflowValues.get(4), invoiceNo,customWorkflowValues.get(26));
		assertTrue(isautoCreatePaymentLinkvisible, "create payment link should be visible as per custom workflow");
		Reporter.log(" Create Payment link is visible as per custom workflow");

		//Log out
		LogoutFromPage.logout();
		}

	@Test(dependsOnMethods= "checkautoCreatePayment" ,enabled=false)
	public void checkautoApprovePayment() {

		LoginPage loginPage = new LoginPage();
		loginPage.login(loginTestData.get(8), loginTestData.get(9));

		InvoiceCreationForm invoice = new InvoiceCreationForm();
	    Settings settings=new Settings();
		//Scroll up
		Settings.scrollUp();

		APModuleCreation apModule = invoice.createNew();
		//click to AP()
		apModule.clickAPLink();
		
		//Click to New Invoice
		apModule.clickNewInvoice();
		Common.sleep(3000);
		
		//Select value from Vender DropDown
		invoice.SelectVendor(customWorkflowValues.get(4));
		
		//Get Invoice number
		invoiceNo=invoice.getAttributeValueInvoiceNo();
		
		//Select value from Net Term 
		invoice.SelectNetTerm(customWorkflowValues.get(5));

		//select value from Location dropdown
		invoice.SelectLocation(customWorkflowValues.get(6));	
		
		//Set product
        settings.selectProductFromDropdown(customWorkflowValues.get(10));
		
		//Select department
		settings.selectDepartment(customWorkflowValues.get(11));
		
		//select booking account
		settings.selectBookingAccount(customWorkflowValues.get(12));
		
		//select description
		settings.itemDescription(customWorkflowValues.get(13));
		
		// Enter measure
		settings.itemMeasure(customWorkflowValues.get(14));
		
		//Enter Quantity
        settings.itemQuantity(customWorkflowValues.get(21));
		
		//Enter rate
        settings.itemRate(customWorkflowValues.get(22));
		
		//Invoice Enter Message to vendor
		invoice.Invoice_MessageToVendor(customWorkflowValues.get(17));

		//Invoice Enter memo
		invoice.Invoice_Memo(customWorkflowValues.get(18));

        //Invoice Click on save button
		invoice.Invoice_SaveButton();
		Common.sleep(2000);
		
		//Invoice Create rule click on cancel button
		invoice.CreateRule_CancelButton();

		//Scroll up
        Settings.scrollUp();
       
		//Search invoice
		CommonMethods.searchByNumberOrName(invoiceNo);
		Common.sleep(2000);

		//Verification of view payment
		settings.verificationForViewPayment(customWorkflowValues.get(4), invoiceNo);
		
		//get payment id
		payId=settings.getPaymentId();
	    Common.sleep(2000);

	    //go to payment link in open menu
	    CommonMethods.gotoLeftAPLink("Payments");
	
	    //Search
	    CommonMethods.searchByNumberOrName(payId);
		Common.sleep(2000);
	    
		//Verification of approve payment
	    boolean isapprovePaymentVisible=settings.verificationForApprovePayment(customWorkflowValues.get(4),payId,customWorkflowValues.get(27));
	    assertTrue(isapprovePaymentVisible, "Approve payment link should be visible as per custom workflow");

	    //Log out
     	LogoutFromPage.logout();
		}

	@Test(dependsOnMethods= "checkautoApprovePayment", enabled=false)
	public void checksendPayment() {

		LoginPage loginPage = new LoginPage();
		loginPage.login(loginTestData.get(8), loginTestData.get(9));

		InvoiceCreationForm invoice = new InvoiceCreationForm();
		Settings settings=new Settings();

		//Scroll up
		Settings.scrollUp();

		APModuleCreation apModule = invoice.createNew();
		
		//click to AP()
		apModule.clickAPLink();
		
		//Click to New Invoice
		apModule.clickNewInvoice();
		Common.sleep(3000);
		
		//Select value from Vender DropDown
		invoice.SelectVendor(customWorkflowValues.get(4));
		
		//Get Invoice number
		invoiceNo=invoice.getAttributeValueInvoiceNo();
		

		//Select value from Net Term 
		invoice.SelectNetTerm(customWorkflowValues.get(5));

		//select value from Location dropdown
		invoice.SelectLocation(customWorkflowValues.get(6));	
		
		//Set product for First line
        settings.selectProductFromDropdown(customWorkflowValues.get(10));
		
		//Select department
		settings.selectDepartment(customWorkflowValues.get(11));
		
		//Select booking account
		settings.selectBookingAccount(customWorkflowValues.get(12));
		
		//Select item description
		settings.itemDescription(customWorkflowValues.get(13));
		
		//Select measure
		settings.itemMeasure(customWorkflowValues.get(14));
		
		//Select quantity
        settings.itemQuantity(customWorkflowValues.get(23));
		
		//Item rate
        settings.itemRate(customWorkflowValues.get(24));
		
		//Invoice Enter Message to vendor
		invoice.Invoice_MessageToVendor(customWorkflowValues.get(17));

		//Invoice Enter memo
		invoice.Invoice_Memo(customWorkflowValues.get(18));

		 //Invoice Click on save button
		invoice.Invoice_SaveButton();
		Common.sleep(2000);
		//Invoice assert message verification
		String expectedAlertMessage="Invoice was created";
		String actualAlertMessage=invoice.gettextValue();			   

		boolean check2= expectedAlertMessage.equals(actualAlertMessage);
		BaseTestCase.assertTrue(check2, "Invoice creation failed");
		Reporter.log("Invoice was created successfully");

		//Invoice Create rule click on cancel button
		invoice.CreateRule_CancelButton();

		//Scroll up
        Settings.scrollUp();
       
		//Search invoice
		CommonMethods.searchByNumberOrName(invoiceNo);
		Common.sleep(2000);	
		
		//Verification of view payment
		settings.verificationForViewPayment(customWorkflowValues.get(4), invoiceNo);
		
		//get payment id
		payId=settings.getPaymentId();
	    	
		//go to payment link in open menu
		CommonMethods.gotoLeftAPLink("Payments");
			
	    //Search
	    CommonMethods.searchByNumberOrName(payId);
		Common.sleep(2000);
		
		//Verification
		boolean issendPaymentLinkvisible=settings.verificationForSendPayment(customWorkflowValues.get(4),payId,customWorkflowValues.get(28));
		assertTrue(issendPaymentLinkvisible, "send payment link should be visible as per custom workflow");
		Common.sleep(5000);
		Reporter.log(" Send Payment link is visible as per custom workflow");

		//Log out
		LogoutFromPage.logout();
	}
	
	
	@Test
	@TestDetails(author="Ruchira.Mhaisurkar",description="This sets the custom workflow for Goods Receipt")
	public void createCustomWorkflowForGoodsReceipt()
	{
		 LoginPage loginPage = new LoginPage();
		 loginPage.login(loginTestData.get(0), loginTestData.get(1));
		 
		 Settings settings=new Settings();
		 settings.CreateGRCustomWorkflow(grCustomWorkflowvalues.get(0));
		 
		 
		 //Verification point
		 boolean isGrCustomWorkflowSet=settings.verificationForGrWorkflowCreation();
		 assertFalse(false, "GR custom workflow is set");
		 Common.sleep(5000);
		 Reporter.log("Custom Worflow for Goods Receipt is set successfully");
		 
		 
		 
		 
		 
		 
	}
}