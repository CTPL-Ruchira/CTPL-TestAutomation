package com.netChain2.selenium.tests.accountsPayable.settings;

import java.util.ArrayList;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.netChain2.engine.BaseTestCase;
import com.netChain2.engine.Common;
import com.netChain2.selenium.pageObjects.accountsPayable.createInvoice.InvoiceCreationForm;
import com.netChain2.selenium.pageObjects.accountsPayable.createPurchaseOrder.PurchaseOrderCreationForm;
import com.netChain2.selenium.pageObjects.accountsPayable.settings.Settings;
import com.netChain2.selenium.pageObjects.common.apCreation.APModuleCreation;
import com.netChain2.selenium.pageObjects.common.components.CommonMethods;
import com.netChain2.selenium.pageObjects.common.landingPage.LandingPage;
import com.netChain2.selenium.pageObjects.common.loginPage.LoginPage;
import com.netChain2.selenium.pageObjects.common.logout.LogoutFromPage;
import com.netChain2.utils.CustomAnnotation.TestDetails;

public class AccountsPayableSettings extends BaseTestCase {
	private ArrayList<String> loginTestData;
	private ArrayList<String> invoiceTestData;
	private String invoiceNo;
	
	WebDriver driver=Common.getDriver();
	
	
	@BeforeClass
	public void setUp() 
	{
		loginTestData = Common.getTestData("NetchainTest.Login");
		invoiceTestData=Common.getTestData("NetchainTest.CreateInvoice");
				
		LandingPage landingPage = new LandingPage();
		landingPage.clickLogInButton();
		
		LoginPage loginPage = new LoginPage();
		loginPage.login(loginTestData.get(0), loginTestData.get(1));
	}
	
	
	@Test
	@TestDetails(author="Manoj Kumar", description="This methods creates custom workflow for invoice, sets value for Invoice acceptance ")
	public void createCustomWorkflowForInvoice() 
	{
		Settings settings=new Settings();
		settings.openSettings();
		
		settings.createNewCustomWorkflow();
		settings.enterValueForInvoiceAcceptance(2030);
		settings.clickOnFinishButton();
		
		System.out.println("Message :"+CommonMethods.getAlertMessage());
		Common.sleep(6000);
		LogoutFromPage.logout();	
	}
	
	@Test(dependsOnMethods= {"createCustomWorkflowForInvoice"})
	@TestDetails(author="Manoj Kumar", description="This methods creates invoice and checks custom workflow")
	public void createInvoiceAndVerifyCustomWorkflow()
	{
		LoginPage loginPage = new LoginPage();
		loginPage.login(loginTestData.get(0), loginTestData.get(1));
		
		InvoiceCreationForm invoiceCreationForm = new InvoiceCreationForm();
		PurchaseOrderCreationForm pocf= new PurchaseOrderCreationForm();

		APModuleCreation apModule = invoiceCreationForm.createNew();
		apModule.clickAPLink();
		apModule.clickNewInvoice();
		Common.sleep(4000);
		
		invoiceCreationForm.setVendorDetails(invoiceTestData.get(0), invoiceTestData.get(1), invoiceTestData.get(2));
		invoiceNo=invoiceCreationForm.getAttributeValueInvoiceNo();
		invoiceCreationForm.setAccountDetails(invoiceTestData.get(3), invoiceTestData.get(4), invoiceTestData.get(5));
		
		pocf.setItemDetails(invoiceTestData.get(6), invoiceTestData.get(7), invoiceTestData.get(8), invoiceTestData.get(9), invoiceTestData.get(10), invoiceTestData.get(11), invoiceTestData.get(12));
		
		invoiceCreationForm.Invoice_MessageToVendor(invoiceTestData.get(13));
		invoiceCreationForm.Invoice_Memo(invoiceTestData.get(14));
		
		invoiceCreationForm.Invoice_SaveButton();
		invoiceCreationForm.CreateRule_CancelButton();
		
		CommonMethods.searchByNumberOrName( invoiceNo);
		boolean isElementDisplayed=invoiceCreationForm.verifyStatusAndActionForInvoice(invoiceTestData.get(0),invoiceNo);
		assertTrue(isElementDisplayed, "Invoice is not auto accepted, please check values in custom workflow");
		LogoutFromPage.logout();
	}
	
}
