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
     @TestDetails(author="Roshni.Mehta", description="Create PO then create invoice and then CreatePayment for Two Way match ")
	  public void testCreateInvoice_CreatePayment() 
	    {
		    //Login
			LoginPage loginPage = new LoginPage();
			loginPage.login(testData.get(4), testData.get(5));
		  
			//scroll up
			CheckTwoWayMatchInvoice.scrollUp();
	 		
			//click on new purchase order
			CommonMethods.gotoRightSideAPLink("NEW PURCHASE ORDER");

			//Create Purchase form
			purchaseOrder.poCreation(testPurchaseOrder.get(0), testPurchaseOrder.get(1), testPurchaseOrder.get(2),testPurchaseOrder.get(3),testPurchaseOrder.get(4),testPurchaseOrder.get(5), testPurchaseOrder.get(6),testPurchaseOrder.get(7), testPurchaseOrder.get(8), testPurchaseOrder.get(9), testPurchaseOrder.get(10), testPurchaseOrder.get(11), testPurchaseOrder.get(12));

			// get Ponumber 
			poNumber=Integer.toString(PurchaseOrderCreationForm.getPoNumber());
			
			//Search Invoice number
			CommonMethods.searchByNumberOrName(poNumber);
			Common.sleep(2000);
			
			//Get vendor name
			vendorNameInPo=TwoWayMatch.getVendorNameFromPoList();
			
			//approve po
			TwoWayMatch.clickapprovePoLinkInPo(vendorNameInPo, poNumber);
			Common.sleep(2000);
			
			//Scroll up
			CheckTwoWayMatchInvoice.scrollUp();
			Common.sleep(5000);
			
			//click to create new
			CommonMethods.gotoRightSideAPLink("NEW INVOICE");

			//Get Invoice number
			invoiceNo=invoice.getAttributeValueInvoiceNo();
		
			//create invoice
			TwoWayMatch.invoiceCreation(testdatatwowaymatch.get(0), testdatatwowaymatch.get(1), testdatatwowaymatch.get(2), testdatatwowaymatch.get(6), testdatatwowaymatch.get(7), testdatatwowaymatch.get(8));
			
			//Select Po number
			TwoWayMatch.selectPONumber_Invoice(poNumber);
			Common.sleep(3000);

			//Select quantity
			TwoWayMatch.selectQuantity_Invoice(testdatatwowaymatch.get(11));

			//Click on save button
			invoice.Invoice_SaveButton();

			//Click on create rule button
			invoice.CreateRule_CancelButton();

			//Search invoice
			TwoWayMatch.searchInvoice(invoiceNo);
			Common.sleep(3000);
			
			//Create payment link verification
			boolean isCreatePaymentLinkVisible=TwoWayMatch.checkInvoiceStatus_CreatePayment(vendorNameInPo,invoiceNo,testdatatwowaymatch.get(29));
			assertTrue(isCreatePaymentLinkVisible, "Custom Workflow is not set");
			Common.sleep(5000);
			Reporter.log("Create payment link is visible as per custom workflow");
			LogoutFromPage.logout();
	 }
	
	@Test(dependsOnMethods="testCreateInvoice_CreatePayment")
	 @TestDetails(author="Roshni.Mehta",description="QtyMisMatchedByEditPo") 
	public void  CreateInvoice_DiscrepantQtyMisMatchedByEditPo()
	 {
        //Login
		LoginPage loginPage = new LoginPage();
 		loginPage.login(testData.get(4), testData.get(5));

 		//scroll up
 		CheckTwoWayMatchInvoice.scrollUp();
 		
 		//click on new purchase order
		CommonMethods.gotoRightSideAPLink("NEW PURCHASE ORDER");

		//Create Purchase form
		purchaseOrder.poCreation(testPurchaseOrder.get(0), testPurchaseOrder.get(1), testPurchaseOrder.get(2),testPurchaseOrder.get(3),testPurchaseOrder.get(4),testPurchaseOrder.get(5), testPurchaseOrder.get(6),testPurchaseOrder.get(7), testPurchaseOrder.get(8), testPurchaseOrder.get(9), testPurchaseOrder.get(10), testPurchaseOrder.get(11), testPurchaseOrder.get(12));
        poNumber=Integer.toString(PurchaseOrderCreationForm.getPoNumber());
		
        //Search Invoice number
		CommonMethods.searchByNumberOrName(poNumber);
		Common.sleep(2000);
	  
		//Get vendor name
		vendorNameInPo=TwoWayMatch.getVendorNameFromPoList();

		//approve po
		TwoWayMatch.clickapprovePoLinkInPo(vendorNameInPo, poNumber);
		Common.sleep(4000);
		
		//Scroll up
		CheckTwoWayMatchInvoice.scrollUp();
		Common.sleep(5000);
		
		//click to create new
		CommonMethods.gotoRightSideAPLink("NEW INVOICE");
	    
	    //Get Invoice number
	    invoiceNo=invoice.getAttributeValueInvoiceNo();
		
	  	//create invoice
		TwoWayMatch.invoiceCreation(testdatatwowaymatch.get(0), testdatatwowaymatch.get(1), testdatatwowaymatch.get(2), testdatatwowaymatch.get(6), testdatatwowaymatch.get(7), testdatatwowaymatch.get(8));
			
	    //select PO number
		TwoWayMatch.selectPONumber_Invoice(poNumber);
		Common.sleep(4000);
		
		//Change Quantity
		TwoWayMatch.selectQuantity_Invoice(testDataInvoice2.get(11));
		
		TwoWayMatch.selectRate_Invoice(testdatatwowaymatch.get(28));
		
		//Select Save button
		invoice.Invoice_SaveButton();
		
		//Select Cancel button on Create rule button
		invoice.CreateRule_CancelButton();
		
		//Search Invoice number
		TwoWayMatch.searchInvoice(invoiceNo);
		Common.sleep(5000);
		
		//Invoice Accept link click
		TwoWayMatch.invoiceAcceptLinkClick(vendorNameInPo,invoiceNo);
		Common.sleep(3000);
		
		//Invoice Accept button on link
		TwoWayMatch.ModalAcceptButton();
		Common.sleep(3000);
		
		//Refresh the page
		 Common.getDriver().navigate().refresh();
		
		//Search Invoice number
		CommonMethods.searchByNumberOrName(invoiceNo);
		Common.sleep(3000);		
		
		//check status 
		boolean IsDiscrepantStatusVisible=TwoWayMatch.checkInvoiceStatus_Discrepant(vendorNameInPo,invoiceNo,testdatatwowaymatch.get(30));
	    assertTrue(IsDiscrepantStatusVisible,"Discrepant status is not visible");
	    Reporter.log("Status is discrepant as per custom workflow");
	    Common.sleep(2000);
	   
	    //Search Invoice number
	    CommonMethods.searchByNumberOrName(invoiceNo);
	    
	    TwoWayMatch.editPoLinkClick(vendorNameInPo,invoiceNo);
	 
	    //Change Quantity   
	    TwoWayMatch.selectQuantity_Invoice(testPurchaseOrder.get(19));
		
	    //Rate
		TwoWayMatch.selectRate_Invoice(testdatatwowaymatch.get(28));
	    
		//update po button
	    TwoWayMatch.updatePoButton();
	    Common.sleep(3000);

	    //Search Invoice number
	    CommonMethods.searchByNumberOrName(poNumber);
	    Common.sleep(3000);

	    TwoWayMatch.clickOnUnapproveUpdate(vendorNameInPo, poNumber);
	    Common.sleep(2000);
	   
	    //Invoices
	    CommonMethods.gotoLeftAPLink("Invoices");
		
	    //Scroll up
		CheckTwoWayMatchInvoice.scrollUp();
		
	    //Search Invoice number
	    CommonMethods.searchByNumberOrName(invoiceNo);
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
        icl.searchInvoice(payId);

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
	       //Login
            LoginPage loginPage = new LoginPage();
	 		loginPage.login(testData.get(4), testData.get(5));
	 		
	 		//scroll up
	 		CheckTwoWayMatchInvoice.scrollUp();
	 		
	 		//click on new purchase order
			CommonMethods.gotoRightSideAPLink("NEW PURCHASE ORDER");

			//Create Purchase form
			purchaseOrder.poCreation(testPurchaseOrder.get(0), testPurchaseOrder.get(1), testPurchaseOrder.get(2),testPurchaseOrder.get(3),testPurchaseOrder.get(4),testPurchaseOrder.get(5), testPurchaseOrder.get(6),testPurchaseOrder.get(7), testPurchaseOrder.get(8), testPurchaseOrder.get(9), testPurchaseOrder.get(10), testPurchaseOrder.get(11), testPurchaseOrder.get(12));
            
			poNumber=Integer.toString(PurchaseOrderCreationForm.getPoNumber());
			
			//Search Invoice number
			CommonMethods.searchByNumberOrName(poNumber);
			Common.sleep(2000);
		    
			//get Vendor
			vendorNameInPo=TwoWayMatch.getVendorNameFromPoList();
			
			//approve po
			TwoWayMatch.clickapprovePoLinkInPo(vendorNameInPo, poNumber);
			Common.sleep(2000);
			
			//Scroll up
			CheckTwoWayMatchInvoice.scrollUp();
			Common.sleep(5000);
			   
			//click to create new
			CommonMethods.gotoRightSideAPLink("NEW INVOICE");

		    //Get Invoice number
		    invoiceNo=invoice.getAttributeValueInvoiceNo();
			
			//create invoice
			TwoWayMatch.invoiceCreation(testdatatwowaymatch.get(0), testdatatwowaymatch.get(1), testdatatwowaymatch.get(2), testdatatwowaymatch.get(6), testdatatwowaymatch.get(7), testdatatwowaymatch.get(8));
			
		    //select PO number
			TwoWayMatch.selectPONumber_Invoice(poNumber);
			Common.sleep(4000);
			
			//Change Quantity
			TwoWayMatch.selectQuantity_Invoice(testDataInvoice2.get(11));
			
			//Change Rate
			TwoWayMatch.selectRate_Invoice(testdatatwowaymatch.get(28));
			
			//Select Save button
			invoice.Invoice_SaveButton();
			
			//Select Cancel button on Create rule button
			invoice.CreateRule_CancelButton();
			
			//Search Invoice number
			TwoWayMatch.searchInvoice(invoiceNo);
			Common.sleep(5000);
			
			//Invoice Accept link click
			TwoWayMatch.invoiceAcceptLinkClick(vendorNameInPo,invoiceNo);
			Common.sleep(3000);
			
			//Invoice Accept button on link
			TwoWayMatch.ModalAcceptButton();
			Common.sleep(3000);
			
			//Refresh the page
			 Common.getDriver().navigate().refresh();
			
			//Search Invoice number
			CommonMethods.searchByNumberOrName(invoiceNo);
					
			//check status 
			boolean IsDiscrepantStatusVisible=TwoWayMatch.checkInvoiceStatus_Discrepant(testDataVendorList.get(0),invoiceNo,testdatatwowaymatch.get(30));
		    assertTrue(IsDiscrepantStatusVisible,"Discrepant status is not visible");
		    Reporter.log("Status is discrepant as per custom workflow");
		    Common.sleep(3000);
		   
		    //Search Invoice number
		    CommonMethods.searchByNumberOrName(invoiceNo);

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
			
		    //Search
			CommonMethods.searchByNumberOrName(invoiceNo);
			Common.sleep(2000);

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
	         icl.searchInvoice(payId);
	        
	         //Check approve status
	         boolean isApprovePayment =  TwoWayMatch.checkApprovePayment(vendorNameInPo, payId,testPurchaseOrder.get(22));
	         assertTrue(isApprovePayment, "approve payment lik is not visible");
	         Reporter.log("approve payment link is visible as per custom workflow");
	        
	         //LogOut
	         LogoutFromPage.logout();
	 }
	
	@Test(dependsOnMethods="CreateInvoice_DiscrepantQtyMisMatchedByEditInvoice")
	@TestDetails(author="Roshni.Mehta",description="Po not found")
	public void poNotFound() 
	{
		 //Login
		LoginPage loginPage = new LoginPage();
		loginPage.login(testData.get(4), testData.get(5));
		
		//scroll up
		CheckTwoWayMatchInvoice.scrollUp();
		
		//click on new purchase order
		CommonMethods.gotoRightSideAPLink("NEW PURCHASE ORDER");

		//Create Purchase form
		purchaseOrder.poCreation(testPurchaseOrder.get(0), testPurchaseOrder.get(1), testPurchaseOrder.get(2),testPurchaseOrder.get(3),testPurchaseOrder.get(4),testPurchaseOrder.get(5), testPurchaseOrder.get(6),testPurchaseOrder.get(7), testPurchaseOrder.get(8), testPurchaseOrder.get(9), testPurchaseOrder.get(10), testPurchaseOrder.get(11), testPurchaseOrder.get(12));

		poNumber=Integer.toString(PurchaseOrderCreationForm.getPoNumber());
		
		
		//Search Invoice number
		CommonMethods.searchByNumberOrName(poNumber);
		Common.sleep(2000);
		
		// get Vendor name
		vendorNameInPo=TwoWayMatch.getVendorNameFromPoList();
		
		//approve po
		TwoWayMatch.clickapprovePoLinkInPo(vendorNameInPo, poNumber);
		Common.sleep(2000);
		
		//Scroll up
		CheckTwoWayMatchInvoice.scrollUp();
	    Common.sleep(5000);
		
	   //click to create new invoice 
	    CommonMethods.gotoRightSideAPLink("NEW INVOICE");
		
		//Get Invoice number
		invoiceNo=invoice.getAttributeValueInvoiceNo();
		
		//create invoice
		TwoWayMatch.invoiceCreation(testdatatwowaymatch.get(0), testdatatwowaymatch.get(1), testdatatwowaymatch.get(2), testdatatwowaymatch.get(6), testdatatwowaymatch.get(7), testdatatwowaymatch.get(8));
			
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
	    CommonMethods.searchByNumberOrName(invoiceNo);
	    Common.sleep(5000);
	   
	   //Invoice Accept link click
	    TwoWayMatch.invoiceAcceptLinkClick(vendorNameInPo,invoiceNo);

	    //Invoice Accept button on link
	    TwoWayMatch.ModalAcceptButton();
	    Common.sleep(3000);

	    //Refresh the page
	    Common.getDriver().navigate().refresh();

	    //Search Invoice number
	    CommonMethods.searchByNumberOrName(invoiceNo);
	    Common.sleep(3000);
		
	    //Verification
	    boolean IsDiscrepantStatusVisible=TwoWayMatch.checkInvoiceStatus_Discrepant(vendorNameInPo,invoiceNo,testdatatwowaymatch.get(30));
	    assertTrue(IsDiscrepantStatusVisible,"Discrepant status is not visible");
	    Reporter.log("Status is discrepant as per custom workflow");

	    //Search Invoice number
	    CommonMethods.searchByNumberOrName(invoiceNo);
	    
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
	   
		CheckTwoWayMatchInvoice.scrollUp();
	    
		//Search Invoice number
	    CommonMethods.searchByNumberOrName(invoiceNo);
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
         icl.searchInvoice(payId);
         Common.sleep(3000);
         
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
	 		
			//click on new purchase order
			CommonMethods.gotoRightSideAPLink("NEW PURCHASE ORDER");

			//Create Purchase form
			purchaseOrder.poCreation(testPurchaseOrder.get(0), testPurchaseOrder.get(1), testPurchaseOrder.get(2),testPurchaseOrder.get(3),testPurchaseOrder.get(4),testPurchaseOrder.get(5), testPurchaseOrder.get(6),testPurchaseOrder.get(7), testPurchaseOrder.get(8), testPurchaseOrder.get(9), testPurchaseOrder.get(10), testPurchaseOrder.get(11), testPurchaseOrder.get(12));

			poNumber=Integer.toString(PurchaseOrderCreationForm.getPoNumber());
			
			//Search Invoice number
			CommonMethods.searchByNumberOrName(poNumber);
			
			vendorNameInPo=TwoWayMatch.getVendorNameFromPoList();
				
			//approve po
			TwoWayMatch.clickapprovePoLinkInPo(vendorNameInPo, poNumber);
			Common.sleep(2000);
			
			//Scroll up
			CheckTwoWayMatchInvoice.scrollUp();
			Common.sleep(5000);

			//click to create new invoice 
			CommonMethods.gotoRightSideAPLink("NEW INVOICE");
		    
		    //Get Invoice number
		    invoiceNo=invoice.getAttributeValueInvoiceNo();
		    
			//create invoice
			TwoWayMatch.invoiceCreation(testdatatwowaymatch.get(0), testdatatwowaymatch.get(1), testdatatwowaymatch.get(2), testdatatwowaymatch.get(6), testdatatwowaymatch.get(7), testdatatwowaymatch.get(8));
			
		    //select PO number
			TwoWayMatch.selectPONumber_Invoice(poNumber);
			Common.sleep(6000);
			
			//Change Quantity
			TwoWayMatch.selectRate_Invoice(testPurchaseOrder.get(20));
			
			//Select Save button
			invoice.Invoice_SaveButton();
			
			//Select Cancel button on Create rule button
			invoice.CreateRule_CancelButton();
			
			//Search Invoice number
			TwoWayMatch.searchInvoice(invoiceNo);
			Common.sleep(5000);		
			
			//Invoice Accept link click
			TwoWayMatch.invoiceAcceptLinkClick(vendorNameInPo,invoiceNo);
			
			//Invoice Accept button on link
			TwoWayMatch.ModalAcceptButton();
			Common.sleep(3000);
			
			//Refresh the page
			 Common.getDriver().navigate().refresh();
			
			//Search Invoice number
			CommonMethods.searchByNumberOrName(invoiceNo);
			Common.sleep(3000);		
			
			//check status 
			boolean IsDiscrepantStatusVisible=TwoWayMatch.checkInvoiceStatus_Discrepant(vendorNameInPo,invoiceNo,testdatatwowaymatch.get(30));
		    assertTrue(IsDiscrepantStatusVisible,"Discrepant status is not visible");
		    Reporter.log("Status is discrepant as per custom workflow");
		    Common.sleep(3000);
		   
		    //Search Invoice number
		    CommonMethods.searchByNumberOrName(invoiceNo);

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
		    CommonMethods.searchByNumberOrName(invoiceNo);
		    Common.sleep(2000);

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
		    icl.searchInvoice(payId);

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
	 		
	 		CheckTwoWayMatchInvoice.scrollUp();
	 		
	 		//click on new purchase order
			CommonMethods.gotoRightSideAPLink("NEW PURCHASE ORDER");

			//Create Purchase form
			purchaseOrder.poCreation(testPurchaseOrder.get(0), testPurchaseOrder.get(1), testPurchaseOrder.get(2),testPurchaseOrder.get(3),testPurchaseOrder.get(4),testPurchaseOrder.get(5), testPurchaseOrder.get(6),testPurchaseOrder.get(7), testPurchaseOrder.get(8), testPurchaseOrder.get(9), testPurchaseOrder.get(10), testPurchaseOrder.get(11), testPurchaseOrder.get(12));
            poNumber=Integer.toString(PurchaseOrderCreationForm.getPoNumber());
		
			//Search Invoice number
			CommonMethods.searchByNumberOrName(poNumber);
			
			//get Vendor
			vendorNameInPo=TwoWayMatch.getVendorNameFromPoList();
			
			//approve po
			TwoWayMatch.clickapprovePoLinkInPo(vendorNameInPo, poNumber);
			Common.sleep(4000);
			
			//Scroll up
			CheckTwoWayMatchInvoice.scrollUp();
			Common.sleep(3000);

			//click to create new invoice 
			CommonMethods.gotoRightSideAPLink("NEW INVOICE");
			
		    //Get Invoice number
		    invoiceNo=invoice.getAttributeValueInvoiceNo();
		    
			//create invoice
			TwoWayMatch.invoiceCreation(testdatatwowaymatch.get(0), testdatatwowaymatch.get(1), testdatatwowaymatch.get(2), testdatatwowaymatch.get(6), testdatatwowaymatch.get(7), testdatatwowaymatch.get(8));
			
		    //select PO number
			TwoWayMatch.selectPONumber_Invoice(poNumber);
			Common.sleep(3000);
			
			//Change Quantity
			TwoWayMatch.selectRate_Invoice(testPurchaseOrder.get(20));
			Common.sleep(3000);
			
			//Select Save button
			invoice.Invoice_SaveButton();
			
			//Select Cancel button on Create rule button
			invoice.CreateRule_CancelButton();
			
			//Search Invoice number
			TwoWayMatch.searchInvoice(invoiceNo);
			Common.sleep(5000);
			
			//Invoice Accept link click
			TwoWayMatch.invoiceAcceptLinkClick(vendorNameInPo,invoiceNo);
			
			//Invoice Accept button on link
			TwoWayMatch.ModalAcceptButton();
			Common.sleep(3000);
			
			//Refresh the page
			 Common.getDriver().navigate().refresh();
			
			//Search Invoice number
			CommonMethods.searchByNumberOrName(invoiceNo);
			Common.sleep(3000);		
			
			//check status 
			boolean IsDiscrepantStatusVisible=TwoWayMatch.checkInvoiceStatus_Discrepant(vendorNameInPo,invoiceNo,testdatatwowaymatch.get(30));
		    assertTrue(IsDiscrepantStatusVisible,"Discrepant status is not visible");
		    Reporter.log("Status is discrepant as per custom workflow");
		    Common.sleep(2000);
		   
		    //Search Invoice number
		    CommonMethods.searchByNumberOrName(invoiceNo);
		    Common.sleep(3000);
		    
		    TwoWayMatch.editPoLinkClick(vendorNameInPo,invoiceNo);
		    Common.sleep(2000);
		   
		    //Change Quantity   
		    TwoWayMatch.enterQuantityRatePO(testPurchaseOrder.get(20));
		    
		    //update po button
		    TwoWayMatch.updatePoButton();
		    Common.sleep(3000);

		    //Search Invoice number
		    CommonMethods.searchByNumberOrName(poNumber);
		    Common.sleep(3000);

		    TwoWayMatch.clickOnUnapproveUpdate(vendorNameInPo, poNumber);
		    Common.sleep(2000);
		   
		    //Invoices
		    CommonMethods.gotoLeftAPLink("Invoices");
			
			CheckTwoWayMatchInvoice.scrollUp();
		    
			//Search Invoice number
		    CommonMethods.searchByNumberOrName(invoiceNo);
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
	        icl.searchInvoice(payId);
	        Common.sleep(2000);

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
		 System.out.println("Into testCreateInvoice_QtyWithinThresholdLimit");
		 //Login
		 LoginPage loginPage = new LoginPage();
		 loginPage.login(testData.get(4), testData.get(5));

		 //Scroll up
		 CheckTwoWayMatchInvoice.scrollUp();

		 //click on new purchase order
		 CommonMethods.gotoRightSideAPLink("NEW PURCHASE ORDER");

		 purchaseOrder.poCreation(testPurchaseOrder.get(16), testPurchaseOrder.get(1), testPurchaseOrder.get(2),testPurchaseOrder.get(3),testPurchaseOrder.get(4),testPurchaseOrder.get(5), testPurchaseOrder.get(6),testPurchaseOrder.get(17), testPurchaseOrder.get(18), testPurchaseOrder.get(9), testPurchaseOrder.get(10), testPurchaseOrder.get(11), testPurchaseOrder.get(12));
		
		 poNumber=Integer.toString(PurchaseOrderCreationForm.getPoNumber());

		 //Search invoice
		 CommonMethods.searchByNumberOrName(poNumber);
		 Common.sleep(3000);

		 //Click 'approve' action on PO list
		 vendorNameInPo=TwoWayMatch.getVendorNameFromPoList();
		 TwoWayMatch.clickapprovePoLinkInPo(vendorNameInPo, poNumber);
		 Common.sleep(3000);

         //click to create new invoice 
		 CommonMethods.gotoRightSideAPLink("NEW INVOICE");

		//Get Invoice number
		 invoiceNo=invoice.getAttributeValueInvoiceNo();
		 
		 //create invoice
		 TwoWayMatch.invoiceCreation(testdatatwowaymatch.get(16), testdatatwowaymatch.get(17), testdatatwowaymatch.get(18), testdatatwowaymatch.get(19), testdatatwowaymatch.get(20), testdatatwowaymatch.get(21));
			
		 //Select Po number from the dropdown
		 TwoWayMatch.selectPONumber_Invoice(poNumber);
		 Common.sleep(2000);

		 //Change the qty within the threshold limit
		 TwoWayMatch.selectQuantity_Invoice(testdatatwowaymatch.get(22));
		 Common.sleep(2000);

		 //Enter rate
		 TwoWayMatch.selectRate_Invoice(testdatatwowaymatch.get(23));
		 Common.sleep(2000);

		 //Enter message
		 invoice.Invoice_MessageToVendor(testdatatwowaymatch.get(24));

		 //Enter memo
		 invoice.Invoice_Memo(testdatatwowaymatch.get(25));

		 //Click on Save button
		 invoice.Invoice_SaveButton();

		 //Click Cancel button on CreateRule modal
		 invoice.CreateRule_CancelButton();

		 //Search invoice
		 CommonMethods.searchByNumberOrName(invoiceNo);
		 Common.sleep(5000);

		 //Invoice Accept link click
		 TwoWayMatch.invoiceAcceptLinkClick(vendorNameInPo,invoiceNo);

		 //Click 'accept only' on invoice modal
		 TwoWayMatch.ModalAcceptButton();
		 Common.sleep(3000);

		 //Search invoice
		 CommonMethods.searchByNumberOrName(invoiceNo);
		 Common.sleep(3000);

		 //Create payment link verification
		 boolean isCreatePaymentLinkVisible=TwoWayMatch.checkInvoiceStatus_CreatePayment(vendorNameInPo,invoiceNo,testdatatwowaymatch.get(29));
		 assertTrue(isCreatePaymentLinkVisible, "Custom Workflow is not set");
		 Common.sleep(5000);
		 Reporter.log("Create payment link is visible as per custom workflow");

		 //Check 'approved' status for Threshold verification
		 boolean isAcceptedStatusVisible=TwoWayMatch.checkInvoiceStatus_approved(vendorNameInPo,invoiceNo);
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
		 Common.sleep(4000);

		 //Search invoice
		 icl.searchInvoice(payId);

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
		 
		 //click on new purchase order
		 CommonMethods.gotoRightSideAPLink("NEW PURCHASE ORDER");
		 
		 purchaseOrder.poCreation(testPurchaseOrder.get(16), testPurchaseOrder.get(1), testPurchaseOrder.get(2),testPurchaseOrder.get(3),testPurchaseOrder.get(4),testPurchaseOrder.get(5), testPurchaseOrder.get(6),testPurchaseOrder.get(17), testPurchaseOrder.get(18), testPurchaseOrder.get(9), testPurchaseOrder.get(10), testPurchaseOrder.get(11), testPurchaseOrder.get(12));
        
		 poNumber=Integer.toString(PurchaseOrderCreationForm.getPoNumber());

		 //Search invoice
		 CommonMethods.searchByNumberOrName(poNumber);
		 Common.sleep(3000);

		 //Click 'approve' action on PO list
		 vendorNameInPo=TwoWayMatch.getVendorNameFromPoList();
		
		 TwoWayMatch.clickapprovePoLinkInPo(vendorNameInPo, poNumber);
		 Common.sleep(3000);

		 //Scroll up
		 CommonMethods.scrollUp();
		 Common.sleep(2000);

		 //click to create new invoice 
		 CommonMethods.gotoRightSideAPLink("NEW INVOICE");
		 
		 //Get Invoice number
		 invoiceNo=invoice.getAttributeValueInvoiceNo();
		 System.out.println("printtt"+invoiceNo);
 
         //create invoice
		 TwoWayMatch.invoiceCreation(testdatatwowaymatch.get(16), testdatatwowaymatch.get(17), testdatatwowaymatch.get(18), testdatatwowaymatch.get(19), testdatatwowaymatch.get(20), testdatatwowaymatch.get(21));
			
		//Select Po number from the dropdown
		 TwoWayMatch.selectPONumber_Invoice(poNumber);

		 //Change the qty such that the amt calculated is within the threshold limit
		 TwoWayMatch.selectQuantity_Invoice(testdatatwowaymatch.get(26));
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
		 CommonMethods.searchByNumberOrName(invoiceNo);
		 Common.sleep(5000);

		 //Invoice Accept link click
		 TwoWayMatch.invoiceAcceptLinkClick(vendorNameInPo,invoiceNo);

		 //Click 'accept only' on invoice modal
		 TwoWayMatch.ModalAcceptButton();
		 Common.sleep(3000);

		 //Search invoice
		 CommonMethods.searchByNumberOrName(invoiceNo);
		 Common.sleep(3000);

		 //Create payment link verification
		 boolean isCreatePaymentLinkVisible=TwoWayMatch.checkInvoiceStatus_CreatePayment(vendorNameInPo,invoiceNo,testdatatwowaymatch.get(29));
		 assertTrue(isCreatePaymentLinkVisible, "Custom Workflow is not set");
		 Common.sleep(5000);
		 Reporter.log("Create payment link is visible as per custom workflow");

		 //Check 'approved' status for Threshold verification
		 boolean isAcceptedStatusVisible=TwoWayMatch.checkInvoiceStatus_approved(vendorNameInPo,invoiceNo);
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
		 Common.sleep(4000);

		 //Search invoice
		 icl.searchInvoice(payId);

		 //Check approve status
		 boolean isApprovePayment = TwoWayMatch.checkApprovePayment(vendorNameInPo, payId,testPurchaseOrder.get(22));
		 assertTrue(isApprovePayment, "approve payment lik is not visible");
		 Reporter.log("approve payment link is visible as per custom workflow");

		 LogoutFromPage.logout();
	 }

}
	
	
	
	