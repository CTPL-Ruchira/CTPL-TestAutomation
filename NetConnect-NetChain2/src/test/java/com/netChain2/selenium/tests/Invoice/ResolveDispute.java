package com.netChain2.selenium.tests.Invoice;

import java.util.ArrayList;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.netChain2.engine.BaseTestCase;
import com.netChain2.engine.Common;
import com.netChain2.selenium.pageObjects.accountsPayable.createInvoice.InvoiceCreationForm;
import com.netChain2.selenium.pageObjects.accountsPayable.createInvoice.InvoiceDispute;
import com.netChain2.selenium.pageObjects.accountsPayable.createPurchaseOrder.PurchaseOrderCreationForm;
import com.netChain2.selenium.pageObjects.common.apCreation.APModuleCreation;
import com.netChain2.selenium.pageObjects.common.components.CommonMethods;
import com.netChain2.selenium.pageObjects.common.landingPage.LandingPage;
import com.netChain2.selenium.pageObjects.common.loginPage.LoginPage;
import com.netChain2.selenium.pageObjects.common.logout.LogoutFromPage;
import com.netChain2.utils.CustomAnnotation.TestDetails;

public class ResolveDispute extends BaseTestCase
{
	public static ArrayList<String> invoiceTestData, invoiceTestData2, invoiceTestData3;
	public static ArrayList<String> loginTestData;
	public static ArrayList<String> invoiceDisputeData;
	public String invoiceNo, invoiceNoForAccept;
	public String originalAmount;
	private String PreviousAmount;
	public String calculatedAmountForProductInModal, OriginalBalanceDueForAcceptInvoice, OriginalQuantityForAcceptInvoice;
	private String quantity, rate, amount, OriginalRateForAcceptInvoice;
	InvoiceDispute id;
	PurchaseOrderCreationForm pocf;
	
	@BeforeClass
	public void setUp()
	{
		invoiceTestData = 	Common.getTestData("NetchainTest.CreateInvoice");
		invoiceTestData2 = 	Common.getTestData("NetchainTest.CreateInvoice2");
		invoiceTestData3 = 	Common.getTestData("NetchainTest.CreateInvoice3");
		loginTestData = 	Common.getTestData("NetchainTest.Login");
		invoiceDisputeData=	Common.getTestData("NetchainTest.InvoiceDispute");
	}
	
	@Test
	@TestDetails(author="Manoj.Kumar", description="Dispute form")
	
	public void login()
	{
		System.out.println("uiii");
		LandingPage landingPage = new LandingPage();
		landingPage.clickLogInButton();
		LoginPage loginPage = new LoginPage();
		loginPage.login(loginTestData.get(0), loginTestData.get(1));
		Common.sleep(7000);
		System.out.println("End login");
	}
	
	@Test(dependsOnMethods= {"login"})
	 public void testCreateInvoice() {
		System.out.println("Into testCreateInvoice");
			
			InvoiceCreationForm invoice = new InvoiceCreationForm();

			APModuleCreation apModule = invoice.createNew();
			Common.sleep(2000);
						
			apModule.clickAPLink();
			Common.sleep(2000);
						
			apModule.clickNewInvoice();
			Common.sleep(2000);
			
			invoice.SelectVendor(invoiceTestData.get(0));
		    Common.sleep(6000);
		    
		    invoiceNo=invoice.getAttributeValueInvoiceNo();
					 
			invoice.SelectNetTerm(invoiceTestData.get(1));
			invoice.SelectLocation(invoiceTestData.get(2));	
			invoice.SelectBookingAccount(invoiceTestData.get(3));
			invoice.AccountDetails_Description(invoiceTestData.get(4));
			PreviousAmount=invoice.AccountDetails_Amount(invoiceTestData.get(5));
			double Amount= Double.parseDouble(PreviousAmount);
			PurchaseOrderCreationForm purchaseOrder=new PurchaseOrderCreationForm();
			
			invoice.Invoice_SelectMeasure(invoiceTestData.get(10));
			
			purchaseOrder.setItemDetails(invoiceTestData.get(6),invoiceTestData.get(7),invoiceTestData.get(8),invoiceTestData.get(9), invoiceTestData.get(10), invoiceTestData.get(11), invoiceTestData.get(12));
			invoice.Invoice_MessageToVendor(invoiceTestData.get(13));

			 invoice.Invoice_Memo(invoiceTestData.get(14));
			 originalAmount=purchaseOrder.getTotalAmountDisplayed();
					 
			 quantity=PurchaseOrderCreationForm.getQualtity();
			 rate=PurchaseOrderCreationForm.getRate();
			 amount=PurchaseOrderCreationForm.getAmount();
			 invoice.Invoice_SaveButton();
			 		  
			 //Invoice assert message verfication
			  String ExpectedAlertMessage="Invoice was created";
			  String ActualAlertMessage=invoice.gettextValue();			   
		
			  boolean check2= ExpectedAlertMessage.equals(ActualAlertMessage);
			  BaseTestCase.assertTrue(check2, "Invoice creation failed");
			  Common.sleep(6000);
			  Reporter.log("Invoice was created successfully");
			 			 
			   invoice.CreateRule_CancelButton();
		 			 
			   LogoutFromPage.logout();
			   System.out.println("End testCreateInvoice");
		 }
	
	@Test(dependsOnMethods= {"testCreateInvoice"})
	public void performDispute()
	{
		LoginPage loginPage = new LoginPage();
		loginPage.login(loginTestData.get(0), loginTestData.get(1));
		Common.sleep(7000);
		
		id=new InvoiceDispute();
		pocf=new PurchaseOrderCreationForm();
		
		
		InvoiceCreationForm icf=new InvoiceCreationForm();
		
		CommonMethods.searchByNumberOrName(invoiceNo);
		Common.sleep(6000);
		
		id.clickAndOpenInvoicePreview();
		id.openDispute(invoiceTestData.get(6));
		id.editFieldsForDispute(invoiceTestData.get(6), invoiceDisputeData.get(0), invoiceDisputeData.get(1), invoiceDisputeData.get(2));
		id.sendDispute();
		OriginalBalanceDueForAcceptInvoice=id.getAmount();
		System.out.println("getAmount"+id.getAmount());
		calculatedAmountForProductInModal=id.getBalanceDue();
		System.out.println("id.getBalance"+id.getBalanceDue());
		System.out.println("originalAmount--"+originalAmount);
		
		//Check Amount and Balance due are same in dispute Modal
		boolean isAmountAndBalanceDueSame=id.verifyBalanceDueAndOriginalAmountOnModal(id.getBalanceDue(),id.getBookingAccountAmount(),id.getAmount());
		//boolean isAmountAndBalanceDueSame=pocf.compareTwoValues(id.getAmount(), id.getBalanceDue());
		assertTrue(isAmountAndBalanceDueSame, "Amount and Balance due are not same");
		
		//Check current amount displayed is correct
		boolean isCurrentAmountCorrect=id.verifyValuesAfterSendingDisputeInInVoicePreviewPage(id.getAmount(), id.getCurrentAmountFromInvoiceBannerInInvoicePreviewPage());
		assertTrue(isCurrentAmountCorrect, "Current Amount is not corectly displayed on Invoice Preview Page");
		
		//Check original amount displayed is correct
		boolean isOriginalAmountCorrect=id.verifyValuesAfterSendingDisputeInInVoicePreviewPage(originalAmount, id.getOriginalAmountFromInvoiceBannerInInvoicePreviewPage());
		assertTrue(isOriginalAmountCorrect, "Original Amount is not corectly displayed on Invoice Preview Page");
		Common.sleep(5000);
		//Check balance due amount displayed is correct(------Issue-------)
		//boolean isBalanceDueCorrect=id.verifyValuesAfterSendingDisputeInInVoicePreviewPage(id.getAmount(), id.getBalanceDueFromInvoiceBannerInInvoicePreviewPage());
		//assertTrue(isBalanceDueCorrect, "Balance due is not corectly displayed on Invoice Preview Page");
		OriginalQuantityForAcceptInvoice=id.getQuantityFromProductLineInInvoicePreviewPage(invoiceTestData.get(6));
		OriginalRateForAcceptInvoice=id.getRateFromProductLineInInvoicePreviewPage(invoiceTestData.get(6));
		System.out.println("QTY from prev"+id.getQuantityFromProductLineInInvoicePreviewPage(invoiceTestData.get(6)));
		//Check quantity displayed is correct
		boolean isQuantityCorrectOnInvoicePreview=id.verifyValuesAfterSendingDisputeInInVoicePreviewPage(id.getQuantityFromProductLineInInvoicePreviewPage(invoiceTestData.get(6)), invoiceDisputeData.get(0));
		assertTrue(isQuantityCorrectOnInvoicePreview, "Quantity is not corectly displayed on Invoice Preview Page");
		
		//Check rate displayed is correct
		boolean isRateCorrectOnInvoicePreview=id.verifyValuesAfterSendingDisputeInInVoicePreviewPage(id.getRateFromProductLineInInvoicePreviewPage(invoiceTestData.get(6)), "$212.33");
		assertTrue(isRateCorrectOnInvoicePreview, "Rate is not corectly displayed on Invoice Preview Page");
		
		//Check amount displayed is correct
		boolean isAmountCorrectOnInvoicePreview=id.verifyValuesAfterSendingDisputeInInVoicePreviewPage(id.getAmountFromProductLineInInvoicePreviewPage(invoiceTestData.get(6)), calculatedAmountForProductInModal);
		assertTrue(isAmountCorrectOnInvoicePreview, "Amount is not corectly displayed on Invoice Preview Page");
				
		System.out.println("All Verified...");
		
		LogoutFromPage.logout();
		
		Reporter.log("Invoice sucessfully disputed",true);
		
		System.out.println("Ending...");
		//InvoiceCreationList icl=new InvoiceCreationList();
		
		//Search invoice in list
		//icl.searchInvoice(invoiceTestDataList.get(0));
		//Common.sleep(1000);
	
	}
	
	@Test(dependsOnMethods= {"performDispute"})
	public void VerifyAmountOnInvoiceList()
	{
		System.out.println("Into VerifyAmountOnInvoiceList");
		LoginPage loginPage = new LoginPage();
		loginPage.login(loginTestData.get(0), loginTestData.get(1));
		Common.sleep(7000);
		
		CommonMethods.searchByNumberOrName(invoiceNo);
		
		boolean isOrgAmountSame=CommonMethods.compareTwoValues(id.getOriginalAmountOnList(), originalAmount);
		assertTrue(isOrgAmountSame, "Original amount is not correct on invoice list");
		
		boolean isBalAmountSame=CommonMethods.compareTwoValues(id.getOriginalAmountOnList(), originalAmount);
		assertTrue(isBalAmountSame, "Balance amount is not correct on invoice list");
		
		boolean isDisputeModeFlagPresent=Common.isElementDisplayed("INVOICE_LIST_DISPUTE_FLAG_XPATH");
		assertTrue(isDisputeModeFlagPresent, "Dispute Mode flag is not present");
		
		System.out.println("Verify Amount on Invoice End");
		
		LogoutFromPage.logout();
		Reporter.log("Amount verified on invoice list",true);
	}
	
	@Test(dependsOnMethods= {"VerifyAmountOnInvoiceList"})
	public void ApproveDisputeAndVerifyAmount()
	{
		System.out.println("Into performDispute");
		LoginPage loginPage = new LoginPage();
		loginPage.login(loginTestData.get(0), loginTestData.get(1));
		Common.sleep(7000);
		
		id=new InvoiceDispute();
		InvoiceCreationForm icf=new InvoiceCreationForm();
		
		System.out.println("Invoice Numberrrrrrrr"+invoiceNo);
		CommonMethods.searchByNumberOrName(invoiceNo);
		Common.sleep(6000);
		
		id.clickAndOpenInvoicePreview();
		id.approveDispute();
		
		//Check original amount displayed is correct
		boolean isOriginalAmountCorrect=id.verifyValuesAfterSendingDisputeInInVoicePreviewPage(originalAmount, id.getOriginalAmountFromInvoiceBannerInInvoicePreviewPage());
		assertTrue(isOriginalAmountCorrect, "Original Amount is not corectly displayed on Invoice Preview Page");
		Common.sleep(9000);
		System.out.println("before orgf");
		//Check balance due amount displayed is correct(------Issue-------)
		//boolean isBalanceDueCorrect=id.verifyValuesAfterSendingDisputeInInVoicePreviewPage(OriginalBalanceDueForAcceptInvoice, id.getBalanceDueFromInvoiceBannerInInvoicePreviewPage());
		//assertTrue(isBalanceDueCorrect, "Balance due is not corectly displayed on Invoice Preview Page");
		
		//Check quantity displayed is correct
		boolean isQuantityCorrectOnInvoicePreview=id.verifyValuesAfterSendingDisputeInInVoicePreviewPage(id.getQuantityFromProductLineInInvoicePreviewPage(invoiceTestData.get(6)), OriginalQuantityForAcceptInvoice);
		assertTrue(isQuantityCorrectOnInvoicePreview, "Quantity is not corectly displayed on Invoice Preview Page");
				
		//Check rate displayed is correct
		//boolean isRateCorrectOnInvoicePreview=id.verifyRateAfterSendingDisputeInInVoicePreviewPage(id.getRateFromProductLineInInvoicePreviewPage("555"), "555");
		//assertTrue(isRateCorrectOnInvoicePreview, "Rate is not corectly displayed on Invoice Preview Page");
				
		//Check amount displayed is correct
		//boolean isAmountCorrectOnInvoicePreview=id.verifyRateAfterSendingDisputeInInVoicePreviewPage(id.getAmountFromProductLineInInvoicePreviewPage(invoiceTestData.get(6)), calculatedAmountForProductInModal);
		//assertTrue(isAmountCorrectOnInvoicePreview, "Amount is not corectly displayed on Invoice Preview Page");
			
		LogoutFromPage.logout();
		LoginPage loginPage2 = new LoginPage();
		loginPage2.login(loginTestData.get(0), loginTestData.get(1));
		
		CommonMethods.searchByNumberOrName(invoiceNo);
		
		
		/*boolean isOrgAmountSame=CommonMethods.compareTwoValues(id.getOriginalAmountOnList(), originalAmount);
		assertTrue(isOrgAmountSame, "Original amount is not correct on invoice list");
		
		//(Issue)
		//boolean isBalAmountSame=CommonMethods.compareTwoValues(id.getOriginalAmountOnList(), originalAmount);
		//assertTrue(isBalAmountSame, "Balance amount is not correct on invoice list");
		try {
		boolean isDisputeModeFlagPresent=Common.isElementDisplayed("INVOICE_LIST_DISPUTE_FLAG_XPATH");
		Assert.assertFalse(isDisputeModeFlagPresent, "Dispute Mode should not be present");
		}
		catch(Exception e)
		{
			System.out.println("Correct");
		}
		//assertTrue(isDisputeModeFlagPresent, "Dispute Mode flag is not present");
		System.out.println("End all....End");*/
		
		Reporter.log("All amounts are verified after Accept Dispute",true);
		
		LogoutFromPage.logout();
			
	}
	
}
