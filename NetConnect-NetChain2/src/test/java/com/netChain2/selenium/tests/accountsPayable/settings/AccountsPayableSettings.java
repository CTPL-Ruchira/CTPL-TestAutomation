package com.netChain2.selenium.tests.accountsPayable.settings;

import java.util.ArrayList;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.netChain2.engine.BaseTestCase;
import com.netChain2.engine.Common;
import com.netChain2.selenium.pageObjects.accountsPayable.createInvoice.InvoiceCreationForm;
import com.netChain2.selenium.pageObjects.accountsPayable.createInvoice.InvoiceCreationListActions;
import com.netChain2.selenium.pageObjects.accountsPayable.settings.Settings;
import com.netChain2.selenium.pageObjects.common.loginPage.LoginPage;
import com.netChain2.utils.CustomAnnotation.TestDetails;

public class AccountsPayableSettings extends BaseTestCase {
	private ArrayList<String> loginTestData;
	private ArrayList<String>CustomWorkflowValues;
	
	WebDriver driver=Common.getDriver();
	
	
	@BeforeClass
	public void setUp() 
	{
		loginTestData = Common.getTestData("NetchainTest.Login");
		CustomWorkflowValues=Common.getTestData("NetchainTest.CustomWorkflow");		
		
		
	}
	
	
	@Test
	@TestDetails(author="Manoj Kumar", description="This methods creates custom workflow for invoice")
	public void createCustomWorkflowForInvoice() 
	{

		LoginPage loginPage = new LoginPage();
		loginPage.login(loginTestData.get(6), loginTestData.get(7));

		/*Settings settings=new Settings();

		LoginPage loginPage = new LoginPage();
		loginPage.login(loginTestData.get(6), loginTestData.get(7));
		
		Settings settings=new Settings();

		settings.openSettings();
		
		settings.createNewCustomWorkflow();
		/*settings.autoAcceptValue(CustomWorkflowValues.get(0));
		settings.autoAprroveValue(CustomWorkflowValues.get(1));
		settings.autocreatePaymentValue(CustomWorkflowValues.get(2));
		settings.autoapprovePaymentValue(CustomWorkflowValues.get(3));*/
		//settings.clickOnFinishButton();
		
	}
	

@Test()
public void checkautoCreate() {

	/*LoginPage loginPage = new LoginPage();
	loginPage.login(loginTestData.get(9), loginTestData.get(10));
	
	InvoiceCreationForm invoice = new InvoiceCreationForm();
	InvoiceCreationListActions icl=new InvoiceCreationListActions();
    Common.sleep(3000);
	
    Settings settings=new Settings();

	 //Scroll up
    Settings.scrollUp();
   
    APModuleCreation apModule = invoice.createNew();
	Common.sleep(3000);

	//click to AP()
	apModule.clickAPLink();
	Common.sleep(2000);

	//Click to New Invoice
	apModule.clickNewInvoice();
	Common.sleep(2000);

	//Select value from Vender DropDown
	invoice.SelectVendor(CustomWorkflowValues.get(4));
	Common.sleep(6000);

	//Get Invoice number
	invoiceNo=invoice.getAttributeValueInvoiceNo();
	System.out.println("Invoice number" +invoiceNo);

	//Select value from Net Term 
	invoice.SelectNetTerm(CustomWorkflowValues.get(5));

	//select value from Location dropdown
	invoice.SelectLocation(CustomWorkflowValues.get(6));	

	//Invoice Account details booking account
	invoice.SelectBookingAccount(CustomWorkflowValues.get(7));

	//Invoice Account Description
	invoice.AccountDetails_Description(CustomWorkflowValues.get(8));

	//Invoice Account Amount
	invoice.AccountDetails_Amount(CustomWorkflowValues.get(9));
	
	Common.sleep(2000);

	//Set items for First line
	//purchaseOrder.setItemDetails(CustomWorkflowValues.get(10),CustomWorkflowValues.get(11),CustomWorkflowValues.get(12),CustomWorkflowValues.get(13), CustomWorkflowValues.get(14),CustomWorkflowValues.get(15), CustomWorkflowValues.get(16));
	//
	settings.selectProductFromDropdown(CustomWorkflowValues.get(10));
	Common.sleep(2000);
	
	//
	settings.selectDepartment(CustomWorkflowValues.get(11));
	Common.sleep(2000);
	
	//
	settings.selectBookingAccount(CustomWorkflowValues.get(12));
	Common.sleep(2000);
	
	//
	settings.itemDescription(CustomWorkflowValues.get(13));
	Common.sleep(2000);
	
	// 
	settings.itemMeasure(CustomWorkflowValues.get(14));
	Common.sleep(2000);
	
	//
	
	settings.itemQuantity(CustomWorkflowValues.get(15));
	Common.sleep(2000);
	
	//
	
	settings.itemRate(CustomWorkflowValues.get(16));
	Common.sleep(2000);
	
	//Invoice Enter Message to vendor
	invoice.Invoice_MessageToVendor(CustomWorkflowValues.get(17));

	//Invoice Enter memo
	invoice.Invoice_Memo(CustomWorkflowValues.get(18));



	//Invoice Click on save button
	invoice.Invoice_SaveButton();


	//Invoice assert message verfication
	String ExpectedAlertMessage="Invoice was created";
	String ActualAlertMessage=invoice.gettextValue();			   

	boolean check2= ExpectedAlertMessage.equals(ActualAlertMessage);
	BaseTestCase.assertTrue(check2, "Invoice creation failed");
	Common.sleep(6000);
	Reporter.log("Invoice was created successfully");

	//Invoice Create rule click on cancel button
	invoice.CreateRule_CancelButton();

	//Search invoice
	icl.searchInvoice(invoiceNo);
	Common.sleep(2000);


}}*/
	
}

}

