package com.netChain2.selenium.tests.Invoice;

import java.util.ArrayList;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.netChain2.engine.BaseTestCase;
import com.netChain2.engine.Common;
import com.netChain2.selenium.pageObjects.accountsPayable.createInvoice.CheckTwoWayMatchInvoice;
import com.netChain2.selenium.pageObjects.accountsPayable.createInvoice.InvoiceCreationForm;
import com.netChain2.selenium.pageObjects.accountsPayable.createInvoice.InvoiceCreationListActions;
import com.netChain2.selenium.pageObjects.accountsPayable.createPurchaseOrder.PurchaseOrderCreationForm;
import com.netChain2.selenium.pageObjects.common.components.CommonMethods;
import com.netChain2.selenium.pageObjects.common.loginPage.LoginPage;
import com.netChain2.selenium.pageObjects.common.logout.LogoutFromPage;
import com.netChain2.utils.CustomAnnotation.TestDetails;

public class POTwoWayMatchInvoiceByQuantity extends BaseTestCase {
	private ArrayList<String> testData;
	

	private ArrayList<String> testDataVendorList;
    private ArrayList<String> testDataInvoice2;
    private ArrayList<String>testPurchaseOrder;
	private ArrayList<String> testdatatwowaymatch;
	private String invoiceNo;
	private String poNumber;
	private String vendorNameInPo;
	private String payId;
	InvoiceCreationForm invoice = new InvoiceCreationForm();
	CheckTwoWayMatchInvoice TwoWayMatch=new CheckTwoWayMatchInvoice();
	PurchaseOrderCreationForm purchaseOrder=new PurchaseOrderCreationForm();
	InvoiceCreationListActions icl=new InvoiceCreationListActions();
	
	@BeforeClass
	public void setUp() {
		testData = Common.getTestData("NetchainTest.Login");
        testDataVendorList=Common.getTestData("NetchainTest.InvoiceListVendor");
        testDataInvoice2=Common.getTestData("NetchainTest.CreateInvoice2");
        testdatatwowaymatch=Common.getTestData("NetchainTest.TwoWayMatch");
	  	testPurchaseOrder=Common.getTestData("Netchain.NewPurchaseOrder");
	}

	@Test
	@TestDetails(author="Roshni.Mehta", description="Two Way Match CreatePayment")
	public void testCreateInvoice_CreatePayment()
	{
        //Login
		LoginPage loginPage = new LoginPage();
		loginPage.login(testData.get(4), testData.get(5));

		CheckTwoWayMatchInvoice.scrollUp();

		//Click on purchase order
		CommonMethods.gotoRightSideAPLink("NEW PURCHASE ORDER");

		//Create Purchase form
		purchaseOrder.poCreation(testPurchaseOrder.get(0), testPurchaseOrder.get(1), testPurchaseOrder.get(2),testPurchaseOrder.get(3),testPurchaseOrder.get(4),testPurchaseOrder.get(5), testPurchaseOrder.get(6),testPurchaseOrder.get(7), testPurchaseOrder.get(8), testPurchaseOrder.get(9), testPurchaseOrder.get(10), testPurchaseOrder.get(11), testPurchaseOrder.get(12));

		//Get Po number
		poNumber=Integer.toString(PurchaseOrderCreationForm.getPoNumber());

		//Scroll up
		CheckTwoWayMatchInvoice.scrollUp();
		Common.sleep(2000);

		//Search Invoice number
		//CommonMethods.searchByNumberOrName(poNumber);
		//Common.sleep(2000);

		//Verify approve po
		CommonMethods.verifyAndClickOnActionForPo(poNumber, testPurchaseOrder.get(0),testPurchaseOrder.get(23));

		//vendorNameInPo=TwoWayMatch.getVendorNameFromPoList();
		vendorNameInPo=testPurchaseOrder.get(0);

		//approve po
		//TwoWayMatch.clickapprovePoLinkInPo(vendorNameInPo, poNumber);
		//.sleep(2000);

		//Scroll up
		CheckTwoWayMatchInvoice.scrollUp();
		Common.sleep(2000);

		//Click on invoice
		CommonMethods.gotoRightSideAPLink("NEW INVOICE");

		//Select value from Vender DropDown
		invoice.SelectVendor(testdatatwowaymatch.get(0));
		Common.sleep(6000);

		//Get Invoice number
		invoiceNo=invoice.getAttributeValueInvoiceNo();

		//Select value from Net Term 
		invoice.SelectNetTerm(testdatatwowaymatch.get(1));

		//select value from Location dropdown
		invoice.SelectLocation(testdatatwowaymatch.get(2));	

		//Invoice select product for that PO
		TwoWayMatch.SelectProduct_Invoice(testdatatwowaymatch.get(6));

		//Invoice select department
		TwoWayMatch.SelectDepartment_Invoice(testdatatwowaymatch.get(7));

		//Select bookin account
		TwoWayMatch.SelectBookingAccount_Invoice(testdatatwowaymatch.get(8));

		//Select Po number
		TwoWayMatch.SelectPONumber_Invoice(poNumber);
		Common.sleep(5000);

		//Select quantity
		TwoWayMatch.SelectQuantity_Invoice(testdatatwowaymatch.get(11));

		//Click on save button
		invoice.Invoice_SaveButton();

		//Click on create rule button
		invoice.CreateRule_CancelButton();

		//Search invoice
		//TwoWayMatch.searchInvoice(invoiceNo);

		//Scroll up
		CheckTwoWayMatchInvoice.scrollUp();
		Common.sleep(2000);

		//Click on sorting arrow
		Common.click("SORTING_ARROW_XPATH");
		Common.sleep(4000);

		//Create payment link verification
		boolean isCreatePaymentLinkVisible=TwoWayMatch.CheckInvoiceStatus_CreatePayment(vendorNameInPo,invoiceNo,testPurchaseOrder.get(24));
		assertTrue(isCreatePaymentLinkVisible, "Custom Workflow is not set");
		Common.sleep(5000);
		Reporter.log("Create payment link is visible as per custom workflow");
		LogoutFromPage.logout();
	}

	@Test(dependsOnMethods="testCreateInvoice_CreatePayment")
	@TestDetails(author="Roshni.Mehta",description="QtyMisMatchedByEditPo")
	public void  CreateInvoice_DiscrepantQtyMisMatchedByEditPo()
	{

		LoginPage loginPage = new LoginPage();
		loginPage.login(testData.get(4), testData.get(5));

		CheckTwoWayMatchInvoice.scrollUp();

		//Click on purchase order link
		CommonMethods.gotoRightSideAPLink("NEW PURCHASE ORDER");

		//Create Purchase form
		purchaseOrder.poCreation(testPurchaseOrder.get(0), testPurchaseOrder.get(1), testPurchaseOrder.get(2),testPurchaseOrder.get(3),testPurchaseOrder.get(4),testPurchaseOrder.get(5), testPurchaseOrder.get(6),testPurchaseOrder.get(7), testPurchaseOrder.get(8), testPurchaseOrder.get(9), testPurchaseOrder.get(10), testPurchaseOrder.get(11), testPurchaseOrder.get(12));

		poNumber=Integer.toString(PurchaseOrderCreationForm.getPoNumber());

		//Search Invoice number
		//CommonMethods.searchByNumberOrName(poNumber);
		//Common.sleep(2000);

		//vendorNameInPo=TwoWayMatch.getVendorNameFromPoList();
		vendorNameInPo=testPurchaseOrder.get(0);

		//approve po
		//TwoWayMatch.clickapprovePoLinkInPo(vendorNameInPo, poNumber);
		//Common.sleep(4000);

		//Scroll up
		CheckTwoWayMatchInvoice.scrollUp();
		Common.sleep(2000);

		//Verify approve po
		CommonMethods.verifyAndClickOnActionForPo(poNumber, testPurchaseOrder.get(0),testPurchaseOrder.get(23));

		//Click on invoice
		CommonMethods.gotoRightSideAPLink("NEW INVOICE");

		//Select value from Vender DropDown
		invoice.SelectVendor(testdatatwowaymatch.get(0));
		Common.sleep(3000);

		//Get Invoice number
		invoiceNo=invoice.getAttributeValueInvoiceNo();

		//Select value from Net Term 
		invoice.SelectNetTerm(testdatatwowaymatch.get(1));

		//select value from Location dropdown
		invoice.SelectLocation(testdatatwowaymatch.get(2));	

		//Invoice product Selection
		TwoWayMatch.SelectProduct_Invoice(testdatatwowaymatch.get(6));

		//Invoice select department
		TwoWayMatch.SelectDepartment_Invoice(testdatatwowaymatch.get(7));

		//Invoice select Booking account
		TwoWayMatch.SelectBookingAccount_Invoice(testdatatwowaymatch.get(8));

		//select PO number
		TwoWayMatch.SelectPONumber_Invoice(poNumber);
		Common.sleep(6000);

		//Change Quantity
		TwoWayMatch.SelectQuantity_Invoice(testDataInvoice2.get(11));

		TwoWayMatch.SelectRate_Invoice(testdatatwowaymatch.get(28));

		//Select Save button
		invoice.Invoice_SaveButton();

		//Select Cancel button on Create rule button
		invoice.CreateRule_CancelButton();

		//Search Invoice number
		//TwoWayMatch.searchInvoice(invoiceNo);

		//Scroll up
		CheckTwoWayMatchInvoice.scrollUp();
		Common.sleep(2000);

		//Click on sorting arrow
		Common.click("SORTING_ARROW_XPATH");
		Common.sleep(4000);

		//Invoice Accept link click
		TwoWayMatch.InvoiceAcceptLinkClick(vendorNameInPo,invoiceNo);
		Common.sleep(5000);

		//Invoice Accept button on link
		TwoWayMatch.ModalAcceptButton();
		Common.sleep(5000);

		//Refresh the page
		Common.getDriver().navigate().refresh();
		Common.sleep(4000);

		//Search Invoice number
		//CommonMethods.searchByNumberOrName(invoiceNo);
		//Common.sleep(3000);		

		//Check status 
		boolean IsDiscrepantStatusVisible=TwoWayMatch.CheckInvoiceStatus_Discrepant(vendorNameInPo,invoiceNo,testdatatwowaymatch.get(29));
		assertTrue(IsDiscrepantStatusVisible,"Discrepant status is not visible");
		Reporter.log("Status is discrepant as per custom workflow");
		Common.sleep(2000);

		//Search Invoice number
		// CommonMethods.searchByNumberOrName(invoiceNo);

		//Click on sorting arrow
		Common.click("SORTING_ARROW_XPATH");
		Common.sleep(4000);

		//Click edit PO link
		TwoWayMatch.editPoLinkClick(vendorNameInPo,invoiceNo);

		//Change Quantity   
		TwoWayMatch.SelectQuantity_Invoice(testPurchaseOrder.get(19));

		//Rate
		TwoWayMatch.SelectRate_Invoice(testdatatwowaymatch.get(28));

		//update po button
		TwoWayMatch.updatePoButton();
		Common.sleep(3000);

		//Search Invoice number
		//CommonMethods.searchByNumberOrName(poNumber);
		//Common.sleep(3000);

		//Click on sorting arrow
		Common.click("SORTING_ARROW_XPATH");
		Common.sleep(4000);

		TwoWayMatch.clickOnUnapproveUpdate(vendorNameInPo, poNumber);
		Common.sleep(2000);

		//Invoices
		CommonMethods.gotoLeftAPLink("Invoices");
		Common.sleep(3000);

		//Search Invoice number
		// CommonMethods.searchByNumberOrName(invoiceNo);

		//Click payment link
		icl.clickOnCreatePaymentLink(vendorNameInPo,invoiceNo);
		Common.sleep(8000);

		//Get payment id
		payId= icl.getPaymentId();

		//Click on payment banner 
		icl.bannerClick();
		Common.sleep(5000);

		//Click payment button
		icl.createPaymentButton();
		Common.sleep(2000);

		//Search invoice
		// icl.searchInvoice(payId);

		//Check approve status
		boolean isApprovePayment =  TwoWayMatch.checkApprovePayment(vendorNameInPo, payId,testPurchaseOrder.get(22));
		assertTrue(isApprovePayment, "approve payment link is not visible");
		Reporter.log("approve payment link is visible as per custom workflow");

		//Logout
		LogoutFromPage.logout();
	} 

	@Test(dependsOnMethods="CreateInvoice_DiscrepantQtyMisMatchedByEditPo")
	@TestDetails(author="Roshni.Mehta",description="QtyMisMatchedByEditInvoice")
	public void CreateInvoice_DiscrepantQtyMisMatchedByEditInvoice() 
	{
		LoginPage loginPage = new LoginPage();
		loginPage.login(testData.get(4), testData.get(5));

		//scroll up
		CheckTwoWayMatchInvoice.scrollUp();

		//Click on purchase order link
		CommonMethods.gotoRightSideAPLink("NEW PURCHASE ORDER");

		//Create Purchase form
		purchaseOrder.poCreation(testPurchaseOrder.get(0), testPurchaseOrder.get(1), testPurchaseOrder.get(2),testPurchaseOrder.get(3),testPurchaseOrder.get(4),testPurchaseOrder.get(5), testPurchaseOrder.get(6),testPurchaseOrder.get(7), testPurchaseOrder.get(8), testPurchaseOrder.get(9), testPurchaseOrder.get(10), testPurchaseOrder.get(11), testPurchaseOrder.get(12));

		poNumber=Integer.toString(PurchaseOrderCreationForm.getPoNumber());

		//Search Invoice number
		//CommonMethods.searchByNumberOrName(poNumber);
		//Common.sleep(2000);
		// vendorNameInPo=TwoWayMatch.getVendorNameFromPoList();
		vendorNameInPo=testPurchaseOrder.get(0);

		//approve po
		//TwoWayMatch.clickapprovePoLinkInPo(vendorNameInPo, poNumber);
		//Common.sleep(2000);

		//Scroll up
		CheckTwoWayMatchInvoice.scrollUp();
		Common.sleep(2000);

		//Verify approve po
		CommonMethods.verifyAndClickOnActionForPo(poNumber, testPurchaseOrder.get(0),testPurchaseOrder.get(23));

		//Click on invoice
		CommonMethods.gotoRightSideAPLink("NEW INVOICE");

		//Select value from Vender DropDown
		invoice.SelectVendor(testdatatwowaymatch.get(0));
		Common.sleep(6000);

		//Get Invoice number
		invoiceNo=invoice.getAttributeValueInvoiceNo();

		//Select value from Net Term 
		invoice.SelectNetTerm(testdatatwowaymatch.get(1));

		//select value from Location dropdown
		invoice.SelectLocation(testdatatwowaymatch.get(2));	

		//Invoice product Selection
		TwoWayMatch.SelectProduct_Invoice(testdatatwowaymatch.get(6));

		//Invoice select department
		TwoWayMatch.SelectDepartment_Invoice(testdatatwowaymatch.get(7));

		//Invoice select Booking account
		TwoWayMatch.SelectBookingAccount_Invoice(testdatatwowaymatch.get(8));

		//select PO number
		TwoWayMatch.SelectPONumber_Invoice(poNumber);
		Common.sleep(6000);

		//Change Quantity
		TwoWayMatch.SelectQuantity_Invoice(testDataInvoice2.get(11));

		//Change Rate
		TwoWayMatch.SelectRate_Invoice(testdatatwowaymatch.get(28));

		//Select Save button
		invoice.Invoice_SaveButton();

		//Select Cancel button on Create rule button
		invoice.CreateRule_CancelButton();

		//Search Invoice number
		//TwoWayMatch.searchInvoice(invoiceNo);

		//Scroll up
		CheckTwoWayMatchInvoice.scrollUp();
		Common.sleep(5000);

		//Click on sorting arrow
		Common.click("SORTING_ARROW_XPATH");
		Common.sleep(4000);

		//Invoice Accept link click
		TwoWayMatch.InvoiceAcceptLinkClick(vendorNameInPo,invoiceNo);

		//Invoice Accept button on link
		TwoWayMatch.ModalAcceptButton();
		Common.sleep(5000);

		//Refresh the page
		Common.getDriver().navigate().refresh();

		//Search Invoice number
		//CommonMethods.searchByNumberOrName(invoiceNo);

		//Scroll up
		CheckTwoWayMatchInvoice.scrollUp();
		Common.sleep(3000);

		//Click on sorting arrow
		Common.click("SORTING_ARROW_XPATH");
		Common.sleep(4000);

		//check status 
		boolean IsDiscrepantStatusVisible=TwoWayMatch.CheckInvoiceStatus_Discrepant(testDataVendorList.get(0),invoiceNo,testdatatwowaymatch.get(29));
		assertTrue(IsDiscrepantStatusVisible,"Discrepant status is not visible");
		Reporter.log("Status is discrepant as per custom workflow");
		Common.sleep(3000);

		//Search Invoice number
		//CommonMethods.searchByNumberOrName(invoiceNo);

		//Click on sorting arrow
		Common.click("SORTING_ARROW_XPATH");
		Common.sleep(3000);

		//Click on vendor
		TwoWayMatch.clickOnVendor(vendorNameInPo,invoiceNo);
		Common.sleep(3000);

		//Click on banner
		TwoWayMatch.bannerClick();

		//Scroll down
		CommonMethods.scrollDown();

		//edit invoice
		TwoWayMatch.editInvoiceClick();

		//edit invoice
		TwoWayMatch.enterQuantityEdit_Invoice(testPurchaseOrder.get(17));

		//Rate
		TwoWayMatch.enterQuantityRate_Invoice(testPurchaseOrder.get(21));

		//Click on save button on edit invoice
		TwoWayMatch.editSaveButton();

		//Invoice assert message verification
		String ExpectedAlertMessage=testPurchaseOrder.get(15);
		String ActualAlertMessage=invoice.gettextValue();			   

		boolean check2= ExpectedAlertMessage.equals(ActualAlertMessage);
		BaseTestCase.assertTrue(check2, "Line not saved");
		Common.sleep(3000);
		Reporter.log("Line saved");

		//Click on match button
		TwoWayMatch.editMatchButton();
		Common.sleep(7000);

		// Click on invoice link
		CommonMethods.gotoLeftAPLink("Invoices");
		Common.sleep(3000);

		//Search
		//CommonMethods.searchByNumberOrName(invoiceNo);
		//Common.sleep(2000);

		//Scroll up
		CheckTwoWayMatchInvoice.scrollUp();
		Common.sleep(3000);

		//Click on sorting arrow
		Common.click("SORTING_ARROW_XPATH");
		Common.sleep(3000);

		//Click payment link
		icl.clickOnCreatePaymentLink(vendorNameInPo,invoiceNo);
		Common.sleep(8000);

		//Get payment id
		payId= icl.getPaymentId();

		//Click on payment banner 
		icl.bannerClick();
		Common.sleep(5000);

		//Click payment button
		icl.createPaymentButton();
		Common.sleep(2000);

		//Search invoice
		//icl.searchInvoice(payId);

		//Click on sorting arrow
		Common.click("SORTING_ARROW_XPATH");
		Common.sleep(3000);

		//Check approve status
		boolean isApprovePayment =  TwoWayMatch.checkApprovePayment(vendorNameInPo, payId,testPurchaseOrder.get(22));
		assertTrue(isApprovePayment, "approve payment lik is not visible");
		Reporter.log("approve payment link is visible as per custom workflow");

		//Logout
		LogoutFromPage.logout();
	}

	@Test(dependsOnMethods="CreateInvoice_DiscrepantQtyMisMatchedByEditInvoice")
	@TestDetails(author="Roshni.Mehta",description="Po not found")
	public void poNotFound()
	{
		//Login
		LoginPage loginPage = new LoginPage();
		loginPage.login(testData.get(4), testData.get(5));

		//Scroll up
		CheckTwoWayMatchInvoice.scrollUp();

		//Click on purchase order link
		CommonMethods.gotoRightSideAPLink("NEW PURCHASE ORDER");

		//Create Purchase form
		purchaseOrder.poCreation(testPurchaseOrder.get(0), testPurchaseOrder.get(1), testPurchaseOrder.get(2),testPurchaseOrder.get(3),testPurchaseOrder.get(4),testPurchaseOrder.get(5), testPurchaseOrder.get(6),testPurchaseOrder.get(7), testPurchaseOrder.get(8), testPurchaseOrder.get(9), testPurchaseOrder.get(10), testPurchaseOrder.get(11), testPurchaseOrder.get(12));

		poNumber=Integer.toString(PurchaseOrderCreationForm.getPoNumber());

		vendorNameInPo=testPurchaseOrder.get(0);
		//Search Invoice number
		//CommonMethods.searchByNumberOrName(poNumber);
		//vendorNameInPo=TwoWayMatch.getVendorNameFromPoList();

		//approve po
		//TwoWayMatch.clickapprovePoLinkInPo(vendorNameInPo, poNumber);
		//Common.sleep(2000);

		//Scroll up
		CheckTwoWayMatchInvoice.scrollUp();
		Common.sleep(2000);

		//Verify approve po
		CommonMethods.verifyAndClickOnActionForPo(poNumber, testPurchaseOrder.get(0),testPurchaseOrder.get(23));	

		//Click on invoice
		CommonMethods.gotoRightSideAPLink("NEW INVOICE");

		//Select value from Vender DropDown
		invoice.SelectVendor(vendorNameInPo);
		Common.sleep(6000);

		//Get Invoice number
		invoiceNo=invoice.getAttributeValueInvoiceNo();

		//Select value from Net Term 
		invoice.SelectNetTerm(testPurchaseOrder.get(13));

		//select value from Location dropdown
		invoice.SelectLocation(testPurchaseOrder.get(1));	

		//Invoice product Selection
		TwoWayMatch.SelectProduct_Invoice(testPurchaseOrder.get(2));

		//Invoice select department
		TwoWayMatch.SelectDepartment_Invoice(testPurchaseOrder.get(3));

		//Invoice select Booking account
		TwoWayMatch.SelectBookingAccount_Invoice(testPurchaseOrder.get(4));

		//Account description
		invoice.Invoice_Description(testPurchaseOrder.get(5));

		//measure
		invoice.Invoice_SelectMeasure(testPurchaseOrder.get(6));

		//quantity
		invoice.Invoice_Quantity(testPurchaseOrder.get(7));

		//Rate
		invoice.Invoice_Rate(testPurchaseOrder.get(8));

		//Message
		purchaseOrder.setMessageToVendor(testPurchaseOrder.get(9));

		//Memo
		purchaseOrder.setMemo(testPurchaseOrder.get(10));

		//Click on save button
		invoice.Invoice_SaveButton();

		//Click on create rule button
		invoice.CreateRule_CancelButton();

		//Search Invoice number
		//CommonMethods.searchByNumberOrName(invoiceNo);
		// Common.sleep(2000);

		//Scroll up
		CheckTwoWayMatchInvoice.scrollUp();
		Common.sleep(2000);

		//Click on sorting arrow
		Common.click("SORTING_ARROW_XPATH");
		Common.sleep(3000);

		//Invoice Accept link click
		TwoWayMatch.InvoiceAcceptLinkClick(vendorNameInPo,invoiceNo);

		//Invoice Accept button on link
		TwoWayMatch.ModalAcceptButton();
		Common.sleep(3000);

		//Refresh the page
		Common.getDriver().navigate().refresh();

		//Search Invoice number
		// CommonMethods.searchByNumberOrName(invoiceNo);

		//Scroll up
		CheckTwoWayMatchInvoice.scrollUp();
		Common.sleep(5000);

		//Click on sorting arrow
		Common.click("SORTING_ARROW_XPATH");
		Common.sleep(3000);

		//Verification
		boolean IsDiscrepantStatusVisible=TwoWayMatch.CheckInvoiceStatus_Discrepant(vendorNameInPo,invoiceNo,testdatatwowaymatch.get(29));
		assertTrue(IsDiscrepantStatusVisible,"Discrepant status is not visible");
		Reporter.log("Status is discrepant as per custom workflow");

		//Search Invoice number
		//CommonMethods.searchByNumberOrName(invoiceNo);

		//Click on vendor
		TwoWayMatch.clickOnVendor(vendorNameInPo,invoiceNo);

		//Click on banner
		TwoWayMatch.bannerClick();

		//Scroll down
		CommonMethods.scrollDown();

		//edit invoice
		TwoWayMatch.editInvoiceClick();
		String ponoline=poNumber+" "+"(LineId-1)";

		//Select po number in invoice
		TwoWayMatch.editInvoiceSelectPODropdown(ponoline);

		//Click on save button on edit invoice
		TwoWayMatch.editSaveButton();

		//Invoice assert message verification
		String ExpectedAlertMessage=testPurchaseOrder.get(15);
		String ActualAlertMessage=invoice.gettextValue();			   

		boolean check2= ExpectedAlertMessage.equals(ActualAlertMessage);
		BaseTestCase.assertTrue(check2, "Line not saved");
		Common.sleep(3000);
		Reporter.log("Line saved");

		//Click on match button
		TwoWayMatch.editMatchButton();
		Common.sleep(3000);

		CommonMethods.gotoLeftAPLink("Invoices");
		Common.sleep(3000);

		CheckTwoWayMatchInvoice.scrollUp();

		//Search Invoice number
		//CommonMethods.searchByNumberOrName(invoiceNo);

		//Click on sorting arrow
		Common.click("SORTING_ARROW_XPATH");
		Common.sleep(3000);


		//Click payment link
		icl.clickOnCreatePaymentLink(vendorNameInPo,invoiceNo);
		Common.sleep(8000);

		//Get payment id
		payId= icl.getPaymentId();

		//Click on payment banner 
		icl.bannerClick();
		Common.sleep(5000);

		//Click payment button
		icl.createPaymentButton();
		Common.sleep(2000);

		//Search invoice
		// icl.searchInvoice(payId);

		//Check approve status
		boolean isApprovePayment =  TwoWayMatch.checkApprovePayment(vendorNameInPo, payId,testPurchaseOrder.get(22));
		assertTrue(isApprovePayment, "approve payment lik is not visible");
		Reporter.log("approve payment link is visible as per custom workflow");

		//logout
		LogoutFromPage.logout();
	}	


	@Test(dependsOnMethods="poNotFound")
	@TestDetails(author="Roshni.Mehta",description="TwoWayMatch with Amount by edit invoice")

	public void CreateInvoice_DiscrepantAmountByEditInvoice() 
	{
		LoginPage loginPage = new LoginPage();
		loginPage.login(testData.get(4), testData.get(5));

		CheckTwoWayMatchInvoice.scrollUp();

		//Click on purchase order link
		CommonMethods.gotoRightSideAPLink("NEW PURCHASE ORDER");

		//Create Purchase form
		purchaseOrder.poCreation(testPurchaseOrder.get(0), testPurchaseOrder.get(1), testPurchaseOrder.get(2),testPurchaseOrder.get(3),testPurchaseOrder.get(4),testPurchaseOrder.get(5), testPurchaseOrder.get(6),testPurchaseOrder.get(7), testPurchaseOrder.get(8), testPurchaseOrder.get(9), testPurchaseOrder.get(10), testPurchaseOrder.get(11), testPurchaseOrder.get(12));

		poNumber=Integer.toString(PurchaseOrderCreationForm.getPoNumber());

		//Search Invoice number
		//CommonMethods.searchByNumberOrName(poNumber);
		//vendorNameInPo=TwoWayMatch.getVendorNameFromPoList();

		//approve po
		//TwoWayMatch.clickapprovePoLinkInPo(vendorNameInPo, poNumber);
		//Common.sleep(2000);
		vendorNameInPo=testPurchaseOrder.get(0);

		//Scroll up
		CheckTwoWayMatchInvoice.scrollUp();
		Common.sleep(2000);

		//Verify approve po
		CommonMethods.verifyAndClickOnActionForPo(poNumber, testPurchaseOrder.get(0),testPurchaseOrder.get(23));	

		//Scroll up
		CheckTwoWayMatchInvoice.scrollUp();
		Common.sleep(5000);

		//Click on invoice
		CommonMethods.gotoRightSideAPLink("NEW INVOICE");

		//Select value from Vender DropDown
		invoice.SelectVendor(testdatatwowaymatch.get(0));
		Common.sleep(6000);

		//Get Invoice number
		invoiceNo=invoice.getAttributeValueInvoiceNo();

		//Select value from Net Term 
		invoice.SelectNetTerm(testdatatwowaymatch.get(1));

		//select value from Location dropdown
		invoice.SelectLocation(testdatatwowaymatch.get(2));	

		//Invoice product Selection
		TwoWayMatch.SelectProduct_Invoice(testdatatwowaymatch.get(6));

		//Invoice select department
		TwoWayMatch.SelectDepartment_Invoice(testdatatwowaymatch.get(7));

		//Invoice select Booking account
		TwoWayMatch.SelectBookingAccount_Invoice(testdatatwowaymatch.get(8));

		//select PO number
		TwoWayMatch.SelectPONumber_Invoice(poNumber);
		Common.sleep(6000);

		//Change Quantity
		TwoWayMatch.SelectRate_Invoice(testPurchaseOrder.get(20));

		//Select Save button
		invoice.Invoice_SaveButton();

		//Select Cancel button on Create rule button
		invoice.CreateRule_CancelButton();

		//Search Invoice number
		//TwoWayMatch.searchInvoice(invoiceNo);

		//Scroll up
		CheckTwoWayMatchInvoice.scrollUp();
		Common.sleep(5000);

		//Click on sorting arrow
		Common.click("SORTING_ARROW_XPATH");
		Common.sleep(3000);

		//Invoice Accept link click
		TwoWayMatch.InvoiceAcceptLinkClick(vendorNameInPo,invoiceNo);

		//Invoice Accept button on link
		TwoWayMatch.ModalAcceptButton();
		Common.sleep(3000);

		//Refresh the page
		Common.getDriver().navigate().refresh();

		//Search Invoice number
		//CommonMethods.searchByNumberOrName(invoiceNo);

		//Click on sorting arrow
		Common.click("SORTING_ARROW_XPATH");
		Common.sleep(3000);

		//check status 
		boolean IsDiscrepantStatusVisible=TwoWayMatch.CheckInvoiceStatus_Discrepant(vendorNameInPo,invoiceNo,testdatatwowaymatch.get(29));
		assertTrue(IsDiscrepantStatusVisible,"Discrepant status is not visible");
		Reporter.log("Status is discrepant as per custom workflow");
		Common.sleep(3000);

		//Search Invoice number
		// CommonMethods.searchByNumberOrName(invoiceNo);

		//Click on vendor
		TwoWayMatch.clickOnVendor(vendorNameInPo,invoiceNo);

		//Click on banner
		TwoWayMatch.bannerClick();

		//Scroll down
		CommonMethods.scrollDown();

		//edit invoice
		TwoWayMatch.editInvoiceClick();

		//edit invoice
		TwoWayMatch.enterQuantityRate_Invoice(testPurchaseOrder.get(21));

		//Click on save button on edit invoice
		TwoWayMatch.editSaveButton();

		//Invoice assert message verification
		String ExpectedAlertMessage=testPurchaseOrder.get(15);
		String ActualAlertMessage=invoice.gettextValue();			   

		boolean check2= ExpectedAlertMessage.equals(ActualAlertMessage);
		BaseTestCase.assertTrue(check2, "Line not saved");
		Common.sleep(3000);
		Reporter.log("Line saved");

		//Click on match button
		TwoWayMatch.editMatchButton();
		Common.sleep(5000);

		//Invoices link
		CommonMethods.gotoLeftAPLink("Invoices");

		//Scroll up
		CheckTwoWayMatchInvoice.scrollUp();

		//Search
		// CommonMethods.searchByNumberOrName(invoiceNo);
		//Common.sleep(2000);

		//Click on sorting arrow
		Common.click("SORTING_ARROW_XPATH");
		Common.sleep(3000);

		//Click payment link
		icl.clickOnCreatePaymentLink(vendorNameInPo,invoiceNo);
		Common.sleep(8000);

		//Get payment id
		payId= icl.getPaymentId();

		//Click on payment banner 
		icl.bannerClick();
		Common.sleep(5000);

		//Click payment button
		icl.createPaymentButton();
		Common.sleep(2000);

		//Search invoice
		// icl.searchInvoice(payId);

		//Check approve status
		boolean isApprovePayment =  TwoWayMatch.checkApprovePayment(vendorNameInPo, payId,testPurchaseOrder.get(22));
		assertTrue(isApprovePayment, "approve payment lik is not visible");
		Reporter.log("approve payment link is visible as per custom workflow");

		LogoutFromPage.logout();
	}


	@Test(dependsOnMethods="CreateInvoice_DiscrepantAmountByEditInvoice")
	@TestDetails(author="Roshni.Mehta",description="TwoWayMatch with Amount by edit Po")
	public void  CreateInvoice_DiscrepantAmountMisMatchedByEditPo()
	{

		LoginPage loginPage = new LoginPage();
		loginPage.login(testData.get(4), testData.get(5));

		//Scroll up
		CheckTwoWayMatchInvoice.scrollUp();

		//Click on purchase order link
		CommonMethods.gotoRightSideAPLink("NEW PURCHASE ORDER");

		//Create Purchase form
		purchaseOrder.poCreation(testPurchaseOrder.get(0), testPurchaseOrder.get(1), testPurchaseOrder.get(2),testPurchaseOrder.get(3),testPurchaseOrder.get(4),testPurchaseOrder.get(5), testPurchaseOrder.get(6),testPurchaseOrder.get(7), testPurchaseOrder.get(8), testPurchaseOrder.get(9), testPurchaseOrder.get(10), testPurchaseOrder.get(11), testPurchaseOrder.get(12));

		//get PO number
		poNumber=Integer.toString(PurchaseOrderCreationForm.getPoNumber());

		//Search Invoice number
		//CommonMethods.searchByNumberOrName(poNumber);

		//vendorNameInPo=TwoWayMatch.getVendorNameFromPoList();

		//Scroll up
		CheckTwoWayMatchInvoice.scrollUp();
		Common.sleep(3000);

		//Click on sorting arrow
		Common.click("SORTING_ARROW_XPATH");
		Common.sleep(3000);


		//approve po
		//TwoWayMatch.clickapprovePoLinkInPo(vendorNameInPo, poNumber);
		//Common.sleep(4000);

		//get vendor name
		//vendorNameInPo=TwoWayMatch.getVendorNameFromPoList();

		//Scroll up
		CheckTwoWayMatchInvoice.scrollUp();
		Common.sleep(3000);

		//Verify approve po
		CommonMethods.verifyAndClickOnActionForPo(poNumber, testPurchaseOrder.get(0),testPurchaseOrder.get(23));	

		//Get vendor name
		vendorNameInPo=testPurchaseOrder.get(0);

		//Scroll up
		CheckTwoWayMatchInvoice.scrollUp();
		Common.sleep(2000);

		//Click on invoice
		CommonMethods.gotoRightSideAPLink("NEW INVOICE");

		//Select value from Vender DropDown
		invoice.SelectVendor(testdatatwowaymatch.get(0));

		//Get Invoice number
		invoiceNo=invoice.getAttributeValueInvoiceNo();

		//Select value from Net Term 
		invoice.SelectNetTerm(testdatatwowaymatch.get(1));

		//select value from Location dropdown
		invoice.SelectLocation(testdatatwowaymatch.get(2));	

		//Invoice product Selection
		TwoWayMatch.SelectProduct_Invoice(testdatatwowaymatch.get(6));

		//Invoice select department
		TwoWayMatch.SelectDepartment_Invoice(testdatatwowaymatch.get(7));

		//Invoice select Booking account
		TwoWayMatch.SelectBookingAccount_Invoice(testdatatwowaymatch.get(8));

		//select PO number
		TwoWayMatch.SelectPONumber_Invoice(poNumber);
		Common.sleep(3000);

		//Change Quantity
		TwoWayMatch.SelectRate_Invoice(testPurchaseOrder.get(20));
		Common.sleep(3000);

		//Select Save button
		invoice.Invoice_SaveButton();

		//Select Cancel button on Create rule button
		invoice.CreateRule_CancelButton();

		//Search Invoice number
		//TwoWayMatch.searchInvoice(invoiceNo);

		//Scroll up
		CheckTwoWayMatchInvoice.scrollUp();
		Common.sleep(3000);

		//Click on sorting arrow
		Common.click("SORTING_ARROW_XPATH");
		Common.sleep(3000);

		//Invoice Accept link click
		TwoWayMatch.InvoiceAcceptLinkClick(vendorNameInPo,invoiceNo);

		//Invoice Accept button on link
		TwoWayMatch.ModalAcceptButton();
		Common.sleep(3000);

		//Refresh the page
		Common.getDriver().navigate().refresh();

		//Search Invoice number
		//CommonMethods.searchByNumberOrName(invoiceNo);
		//Common.sleep(3000);		

		//Click on sorting arrow
		Common.click("SORTING_ARROW_XPATH");
		Common.sleep(3000);

		//check status 
		boolean IsDiscrepantStatusVisible=TwoWayMatch.CheckInvoiceStatus_Discrepant(vendorNameInPo,invoiceNo,testdatatwowaymatch.get(29));
		assertTrue(IsDiscrepantStatusVisible,"Discrepant status is not visible");
		Reporter.log("Status is discrepant as per custom workflow");
		Common.sleep(2000);

		//Search Invoice number
		// CommonMethods.searchByNumberOrName(invoiceNo);

		//Click on edit Po
		TwoWayMatch.editPoLinkClick(vendorNameInPo,invoiceNo);
		Common.sleep(2000);

		//Change Quantity   
		TwoWayMatch.enterQuantityRatePO(testPurchaseOrder.get(20));

		//update po button
		TwoWayMatch.updatePoButton();
		Common.sleep(3000);

		//Search Invoice number
		// CommonMethods.searchByNumberOrName(poNumber);
		// Common.sleep(3000);

		//Click on sorting arrow
		Common.click("SORTING_ARROW_XPATH");
		Common.sleep(3000);

		//Click on unapprove update
		TwoWayMatch.clickOnUnapproveUpdate(vendorNameInPo, poNumber);
		Common.sleep(2000);

		//Invoices
		CommonMethods.gotoLeftAPLink("Invoices");
		Common.sleep(3000);

		//Scroll up
		CheckTwoWayMatchInvoice.scrollUp();

		//Search Invoice number
		//CommonMethods.searchByNumberOrName(invoiceNo);

		//Click on sorting arrow
		Common.click("SORTING_ARROW_XPATH");
		Common.sleep(3000);

		//Click payment link
		icl.clickOnCreatePaymentLink(vendorNameInPo,invoiceNo);
		Common.sleep(8000);

		//Get payment id
		payId= icl.getPaymentId();

		//Click on payment banner 
		icl.bannerClick();
		Common.sleep(5000);

		//Click payment button
		icl.createPaymentButton();
		Common.sleep(2000);

		//Search invoice
		//icl.searchInvoice(payId);

		//Check approve status
		boolean isApprovePayment =  TwoWayMatch.checkApprovePayment(vendorNameInPo, payId,testPurchaseOrder.get(22));
		assertTrue(isApprovePayment, "approve payment lik is not visible");
		Reporter.log("approve payment link is visible as per custom workflow");

		//Logout
		LogoutFromPage.logout();
	}	


	@Test(dependsOnMethods="CreateInvoice_DiscrepantAmountMisMatchedByEditPo")
	@TestDetails(author="Ruchira.Mhaisurkar",description="TwoWayMatch with qty within threshold limit")
	public void testCreateInvoice_QtyWithinThresholdLimit()
	{
		//Login
		LoginPage loginPage = new LoginPage();
		loginPage.login(testData.get(4), testData.get(5));


		//Scroll up
		CheckTwoWayMatchInvoice.scrollUp();

		//Click on purchase order link
		CommonMethods.gotoRightSideAPLink("NEW PURCHASE ORDER");

		purchaseOrder.poCreation(testPurchaseOrder.get(16), testPurchaseOrder.get(1), testPurchaseOrder.get(2),testPurchaseOrder.get(3),testPurchaseOrder.get(4),testPurchaseOrder.get(5), testPurchaseOrder.get(6),testPurchaseOrder.get(17), testPurchaseOrder.get(18), testPurchaseOrder.get(9), testPurchaseOrder.get(10), testPurchaseOrder.get(11), testPurchaseOrder.get(12));

		//Get po number
		poNumber=Integer.toString(PurchaseOrderCreationForm.getPoNumber());


		//Search invoice
		//CommonMethods.searchByNumberOrName(poNumber);
		//Common.sleep(3000);

		//Click 'approve' action on PO list
		//vendorNameInPo=TwoWayMatch.getVendorNameFromPoList();
		//TwoWayMatch.clickapprovePoLinkInPo(vendorNameInPo, poNumber);
		//Common.sleep(3000);

		//Scroll up
		CheckTwoWayMatchInvoice.scrollUp();
		Common.sleep(2000);

		//Verify approve po
		CommonMethods.verifyAndClickOnActionForPo(poNumber, testPurchaseOrder.get(16),testPurchaseOrder.get(23));	

		//Get vendor name
		vendorNameInPo=testPurchaseOrder.get(16);

		//Scroll up
		CheckTwoWayMatchInvoice.scrollUp();
		Common.sleep(3000);

		//Click on invoice
		CommonMethods.gotoRightSideAPLink("NEW INVOICE");

		//Select vendor from dropdown
		invoice.SelectVendor(testdatatwowaymatch.get(16));

		//Get Invoice number
		invoiceNo=invoice.getAttributeValueInvoiceNo();

		//Select NetTerms
		invoice.SelectNetTerm(testdatatwowaymatch.get(17));
		Common.sleep(3000);

		//Select Location
		invoice.SelectLocation(testdatatwowaymatch.get(18));
		Common.sleep(3000);

		//Select product
		TwoWayMatch.SelectProduct_Invoice(testdatatwowaymatch.get(19));
		Common.sleep(2000);

		//Select department
		TwoWayMatch.SelectDepartment_Invoice(testdatatwowaymatch.get(20));
		Common.sleep(2000);

		//Select BookingAccount
		TwoWayMatch.SelectBookingAccount_Invoice(testdatatwowaymatch.get(21));
		Common.sleep(2000);

		//Select Po number from the dropdown
		TwoWayMatch.SelectPONumber_Invoice(poNumber);
		Common.sleep(2000);

		//Change the qty within the threshold limit
		TwoWayMatch.SelectQuantity_Invoice(testdatatwowaymatch.get(22));
		Common.sleep(2000);

		//Enter rate
		TwoWayMatch.SelectRate_Invoice(testdatatwowaymatch.get(23));
		Common.sleep(2000);

		//Enter message
		invoice.Invoice_MessageToVendor(testdatatwowaymatch.get(24));
		Common.sleep(2000);

		//Enter memo
		invoice.Invoice_Memo(testdatatwowaymatch.get(25));
		Common.sleep(2000);

		//Click on Save button
		invoice.Invoice_SaveButton();

		//Click Cancel button on CreateRule modal
		invoice.CreateRule_CancelButton();

		//Search invoice
		//CommonMethods.searchByNumberOrName(invoiceNo);
		//Common.sleep(3000);

		//Scroll up
		CheckTwoWayMatchInvoice.scrollUp();
		Common.sleep(3000);

		//Click on sorting arrow
		Common.click("SORTING_ARROW_XPATH");
		Common.sleep(3000);

		//Invoice Accept link click
		TwoWayMatch.InvoiceAcceptLinkClick(vendorNameInPo,invoiceNo);

		//Click 'accept only' on invoice modal
		TwoWayMatch.ModalAcceptButton();
		Common.sleep(3000);

		//Search invoice
		//CommonMethods.searchByNumberOrName(invoiceNo);
		//Common.sleep(3000);

		//Click on sorting arrow
		Common.click("SORTING_ARROW_XPATH");
		Common.sleep(3000);

		//Create payment link verification
		boolean isCreatePaymentLinkVisible=TwoWayMatch.CheckInvoiceStatus_CreatePayment(vendorNameInPo,invoiceNo,testPurchaseOrder.get(24));
		assertTrue(isCreatePaymentLinkVisible, "Custom Workflow is not set");
		Common.sleep(5000);
		Reporter.log("Create payment link is visible as per custom workflow");


		//Check 'approved' status for Threshold verification
		boolean isAcceptedStatusVisible=TwoWayMatch.CheckInvoiceStatus_approved(vendorNameInPo,invoiceNo);
		assertTrue(isAcceptedStatusVisible, "Status is not seen as approved");
		Common.sleep(5000);
		Reporter.log("Status is seen as approved after the invoice is accepted ");

		//Click payment link
		icl.clickOnCreatePaymentLink(vendorNameInPo,invoiceNo);
		Common.sleep(4000);

		//Get payment id
		payId= icl.getPaymentId();

		//Click on payment banner 
		icl.bannerClick();
		Common.sleep(5000);

		//Click payment button
		icl.createPaymentButton();
		Common.sleep(2000);

		//Search invoice
		// icl.searchInvoice(payId);

		//Check approve status
		boolean isApprovePayment =  TwoWayMatch.checkApprovePayment(vendorNameInPo, payId,testPurchaseOrder.get(22));
		assertTrue(isApprovePayment, "approve payment lik is not visible");
		Reporter.log("approve payment link is visible as per custom workflow");
		LogoutFromPage.logout();
	}


	@Test(dependsOnMethods= {"testCreateInvoice_QtyWithinThresholdLimit"})
	@TestDetails(author="Ruchira.Mhaisurkar",description="TwoWayMatch with Amount within threshold limit")
	public void testCreateInvoice_AmountWithinThresholdLimit()
	{
		//Login
		LoginPage loginPage = new LoginPage();
		loginPage.login(testData.get(4), testData.get(5));

		//Scroll up
		CheckTwoWayMatchInvoice.scrollUp();

		//Click on purchase order link
		CommonMethods.gotoRightSideAPLink("NEW PURCHASE ORDER");

		purchaseOrder.poCreation(testPurchaseOrder.get(16), testPurchaseOrder.get(1), testPurchaseOrder.get(2),testPurchaseOrder.get(3),testPurchaseOrder.get(4),testPurchaseOrder.get(5), testPurchaseOrder.get(6),testPurchaseOrder.get(17), testPurchaseOrder.get(18), testPurchaseOrder.get(9), testPurchaseOrder.get(10), testPurchaseOrder.get(11), testPurchaseOrder.get(12));

		poNumber=Integer.toString(PurchaseOrderCreationForm.getPoNumber());

		//Search invoice
		//CommonMethods.searchByNumberOrName(poNumber);
		//Common.sleep(3000);

		//Click 'approve' action on PO list
		//vendorNameInPo=TwoWayMatch.getVendorNameFromPoList();
		//TwoWayMatch.clickapprovePoLinkInPo(vendorNameInPo, poNumber);
		//Common.sleep(3000);

		//Scroll up
		CheckTwoWayMatchInvoice.scrollUp();
		Common.sleep(2000);

		//Verify approve po
		CommonMethods.verifyAndClickOnActionForPo(poNumber, testPurchaseOrder.get(16),testPurchaseOrder.get(23));	

		//Get vendor name
		vendorNameInPo=testPurchaseOrder.get(16);

		//Scroll up
		CommonMethods.scrollUp();
		Common.sleep(2000);

		//Click on invoice
		CommonMethods.gotoRightSideAPLink("NEW INVOICE");

		//Select vendor from dropdown
		invoice.SelectVendor(testdatatwowaymatch.get(16));

		//Get Invoice number
		invoiceNo=invoice.getAttributeValueInvoiceNo();

		//Select NetTerms
		invoice.SelectNetTerm(testdatatwowaymatch.get(17));
		Common.sleep(3000);

		//Select Location
		invoice.SelectLocation(testdatatwowaymatch.get(18));
		Common.sleep(3000);

		//Select product
		TwoWayMatch.SelectProduct_Invoice(testdatatwowaymatch.get(19));
		Common.sleep(2000);

		//Select department
		TwoWayMatch.SelectDepartment_Invoice(testdatatwowaymatch.get(20));
		Common.sleep(2000);

		//Select BookingAccount
		TwoWayMatch.SelectBookingAccount_Invoice(testdatatwowaymatch.get(21));
		Common.sleep(2000);

		//Select Po number from the dropdown
		TwoWayMatch.SelectPONumber_Invoice(poNumber);

		//Change the qty such that the amt calculated is within the threshold limit
		TwoWayMatch.SelectQuantity_Invoice(testdatatwowaymatch.get(26));
		Common.sleep(2000);

		//Enter rate
		invoice.Invoice_Rate(testdatatwowaymatch.get(27));

		//Enter message
		invoice.Invoice_MessageToVendor(testdatatwowaymatch.get(24));
		Common.sleep(2000);

		//Enter memo
		invoice.Invoice_Memo(testdatatwowaymatch.get(25));
		Common.sleep(2000);

		//Click on Save button
		invoice.Invoice_SaveButton();

		//Click Cancel button on CreateRule modal
		invoice.CreateRule_CancelButton();

		//Search invoice
		//CommonMethods.searchByNumberOrName(invoiceNo);
		//Common.sleep(3000);

		//Scroll up
		CommonMethods.scrollUp();
		Common.sleep(2000);

		//Click on sorting arrow
		Common.click("SORTING_ARROW_XPATH");
		Common.sleep(3000);

		//Invoice Accept link click
		TwoWayMatch.InvoiceAcceptLinkClick(vendorNameInPo,invoiceNo);

		//Click 'accept only' on invoice modal
		TwoWayMatch.ModalAcceptButton();
		Common.sleep(3000);

		//Search invoice
		// CommonMethods.searchByNumberOrName(invoiceNo);
		//Common.sleep(3000);

		//Click on sorting arrow
		Common.click("SORTING_ARROW_XPATH");
		Common.sleep(3000);

		//Create payment link verification
		boolean isCreatePaymentLinkVisible=TwoWayMatch.CheckInvoiceStatus_CreatePayment(vendorNameInPo,invoiceNo,testPurchaseOrder.get(24));
		assertTrue(isCreatePaymentLinkVisible, "Custom Workflow is not set");
		Common.sleep(5000);
		Reporter.log("Create payment link is visible as per custom workflow");

		//Check 'approved' status for Threshold verification
		boolean isAcceptedStatusVisible=TwoWayMatch.CheckInvoiceStatus_approved(vendorNameInPo,invoiceNo);
		assertTrue(isAcceptedStatusVisible, "Status is not seen as approved");
		Common.sleep(5000);
		Reporter.log("Status is seen as approved after the invoice is accepted ");

		//Click payment link
		icl.clickOnCreatePaymentLink(vendorNameInPo,invoiceNo);
		Common.sleep(8000);

		//Get payment id
		payId= icl.getPaymentId();

		//Click on payment banner 
		icl.bannerClick();
		Common.sleep(5000);

		//Click payment button
		icl.createPaymentButton();
		Common.sleep(2000);

		//Search invoice
		//searchInvoice(payId);

		//Check approve status
		boolean isApprovePayment =  TwoWayMatch.checkApprovePayment(vendorNameInPo, payId,testPurchaseOrder.get(22));
		assertTrue(isApprovePayment, "approve payment lik is not visible");
		Reporter.log("approve payment link is visible as per custom workflow");

		LogoutFromPage.logout();
	}

}
	
	
	
	