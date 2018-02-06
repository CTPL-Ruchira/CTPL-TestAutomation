package com.netChain2.selenium.tests.Invoice;

import java.util.ArrayList;


import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.netChain2.engine.BaseTestCase;
import com.netChain2.engine.Common;
import com.netChain2.selenium.pageObjects.accountsPayable.createInvoice.InvoiceCreationForm;
import com.netChain2.selenium.pageObjects.accountsPayable.createInvoice.InvoiceCreationListActions;
import com.netChain2.selenium.pageObjects.accountsPayable.createPurchaseOrder.PurchaseOrderCreationForm;
import com.netChain2.selenium.pageObjects.common.apCreation.APModuleCreation;
import com.netChain2.selenium.pageObjects.common.landingPage.LandingPage;
import com.netChain2.selenium.pageObjects.common.loginPage.LoginPage;
import com.netChain2.selenium.pageObjects.common.logout.LogoutFromPage;
import com.netChain2.utils.CustomAnnotation.TestDetails;

public class InvoiceActionsWithPaymentFlow extends BaseTestCase{
	private ArrayList<String> testData;
	private ArrayList<String> testDataInvoiceActions;
    private ArrayList<String> testDataVendorList;
	
	public String invoiceNo;
	public String invoicetotalAmount;
	public String invoicetotalAmountRoundoff;
	
	@BeforeClass
	public void setUp() {
	   testData = Common.getTestData("NetchainTest.Login");
       testDataInvoiceActions=Common.getTestData("NetchainTest.CreateInvoiceActions");
       testDataVendorList=Common.getTestData("NetchainTest.InvoiceListVendor");
	   
	}
	    @Test(priority=1)
		@TestDetails(author="Roshni.Mehta", description="Invoice actions and payment flow") 
		
		  public void testCreateInvoice() {
			 
			
			LandingPage landingPage = new LandingPage();
		    landingPage.clickLogInButton();
			
			LoginPage loginPage = new LoginPage();
			loginPage.login(testData.get(2), testData.get(3));
			Common.sleep(2000);
			
			//click to create new
			InvoiceCreationForm invoice = new InvoiceCreationForm();

			APModuleCreation apModule = invoice.createNew();
			Common.sleep(2000);
			
			//click to AP()
			apModule.clickAPLink();
			Common.sleep(2000);
			
			//Click to New Invoice
			apModule.clickNewInvoice();
			Common.sleep(2000);
			
			//Select value from Vender DropDown
			invoice.SelectVendor(testDataInvoiceActions.get(0));
		    Common.sleep(6000);
		    
		    //Get Invoice number
		    invoiceNo=invoice.getAttributeValueInvoiceNo();
			System.out.println("Invoice number" +invoiceNo);
			
			//Select value from Net Term 
			invoice.SelectNetTerm(testDataInvoiceActions.get(1));
			
			//select value from Location dropdown
			invoice.SelectLocation(testDataInvoiceActions.get(2));	
			
			//Invoice Account details booking account
			invoice.SelectBookingAccount(testDataInvoiceActions.get(3));
			     
			//Invoice Account Description
			invoice.AccountDetails_Description(testDataInvoiceActions.get(4));
				 
		    //Invoice Account Amount
			invoice.AccountDetails_Amount(testDataInvoiceActions.get(5));
		    PurchaseOrderCreationForm purchaseOrder=new PurchaseOrderCreationForm();
			
			
		    //Set items for First line
			purchaseOrder.setItemDetails(testDataInvoiceActions.get(6),testDataInvoiceActions.get(7),testDataInvoiceActions.get(8),testDataInvoiceActions.get(9), testDataInvoiceActions.get(10),testDataInvoiceActions.get(11), testDataInvoiceActions.get(12));
	
			 //Invoice Enter Message to vendor
			 invoice.Invoice_MessageToVendor(testDataInvoiceActions.get(13));

			 //Invoice Enter memo
			 invoice.Invoice_Memo(testDataInvoiceActions.get(14));
			 InvoiceCreationListActions icl=new InvoiceCreationListActions();
			 invoicetotalAmount=icl.invoiceAmount();
			 
			 
			 
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
		 
			   //Log out
			   LogoutFromPage.logout();
	    
	    
	    }

	    @Test(priority=2)
		public void verifyInvoiceCreatedInList_SentPayment() {
			 
				LoginPage loginPage = new LoginPage();
				loginPage.login(testData.get(2), testData.get(3));
				Common.sleep(7000);
				
		        InvoiceCreationListActions icl=new InvoiceCreationListActions();
	            
		        //Search invoice
		        icl.searchInvoice(invoiceNo);
				Common.sleep(3000);
				
				//Accept invoice
				icl.clickOnAcceptInvoice(testDataVendorList.get(3),invoiceNo);
				Common.sleep(3000);
				String vendorName=testDataVendorList.get(3);
				
				
                 //Modal accept verfication
				boolean ismodalVerificationdetailsCorrect=icl.verificationModalAcceptButton(invoiceNo,vendorName,invoicetotalAmount);
				System.out.println("verification"+ ismodalVerificationdetailsCorrect);
				BaseTestCase.assertTrue(ismodalVerificationdetailsCorrect, "Modal does not show correct invoice details");
				Reporter.log("Modal accept details are verified");
				
				 //Accept invoice button
				icl.ModalAcceptButton();
				Common.sleep(3000);
				
				//Refresh the page
				 Common.getDriver().navigate().refresh();
				
				 //search Invoice
				icl.searchInvoice(invoiceNo);
				Common.sleep(3000);
				
				//Approve invoices
			    icl.clickOnApproveInvoice(testDataVendorList.get(3),invoiceNo);
			    Common.sleep(3000);
			    
			    //Modal approve verfication
				boolean ismodalVerificationdetailsCorrectApprove=icl.verificationModalAcceptButton(invoiceNo,vendorName,invoicetotalAmount);
				System.out.println("verification"+ ismodalVerificationdetailsCorrectApprove);
				BaseTestCase.assertTrue(ismodalVerificationdetailsCorrectApprove, "Modal does not show correct invoice details");
				Reporter.log("Modal approve details are verified");
				
				//Approve button
				icl.ModalApproveButton();
				Common.sleep(2000);

				String ExpectedAlertMessage1="Invoices Approved";
	            String ActualAlertMessage1=icl.gettextValue();			   
	            System.out.println("Actual value  " + ActualAlertMessage1);  
	            boolean check4= ExpectedAlertMessage1.equals(ActualAlertMessage1);
	            BaseTestCase.assertTrue(check4, "Invoices are not approved");
	            Common.sleep(6000);
	           
	            //Refresh the page
	            Common.getDriver().navigate().refresh();
	           
	            //Search invoice
	           icl.searchInvoice(invoiceNo);
	           Common.sleep(2000);
	           
	           //Click payment link
	           icl.clickOnCreatePaymentLink(testDataVendorList.get(3),invoiceNo);
	           Common.sleep(2000);
	            
	           //Click on payment banner 
	           icl.bannerClick();
	           Common.sleep(5000);
	          
	          //Get payment id
	          String payId= icl.getPaymentId();
	          System.out.println("payid-----"+ payId);
	           
	           //Payment details verfication
	           boolean isPaymentDetailsverfication=icl.PaymentVerfication(invoiceNo,vendorName,invoicetotalAmount);
	           BaseTestCase.assertTrue(isPaymentDetailsverfication, "Payment Details on banner are not shown correct");
	           
	           //Click payment button
	           icl.createPaymentButton();
	           Common.sleep(2000);
	             
	           //Payment verification
	            String ExpectedAlertMessage2="payment is created";
	            String ActualAlertMessage2=icl.gettextValue();			   
	            System.out.println("Actual value payment  " +ActualAlertMessage2);  
	            boolean check3= ExpectedAlertMessage2.equals(ActualAlertMessage2);
	            BaseTestCase.assertTrue(check3, "Payment creation failed");
	            Common.sleep(6000);
	            Reporter.log("Payment was created successfully");
	           
	            //Search invoice
	            icl.searchInvoice(payId);
	            Common.sleep(4000);
	            
	            //Click on approve button
	            icl.clickOnApprovePayment(testDataVendorList.get(3),payId);
	            
	            boolean isPaymentApproveVerfication=icl.verificationPaymentApprove(invoiceNo, vendorName, invoicetotalAmount);
	            BaseTestCase.assertTrue(isPaymentApproveVerfication, "Payment Details on approve module are not correct");
	            
	            //Click on payment button
	            icl.sendPaymentButton();
	            Common.sleep(3000);
       
	           //refresh page 
	            Common.getDriver().navigate().refresh();
	            Common.sleep(3000);
	           
	            //Payment search by id
	            icl.SearchPaymentId(payId);
	            Common.sleep(4000);
	         
	            //Verfication Action
	            boolean isActionProcessing= icl.verficationOnProcessingLink(vendorName,payId);
	            BaseTestCase.assertTrue(isActionProcessing, "Payment action is not processing");
	            Reporter.log("Payment action is processing for sent payment");
	            
	           //Payment search by id
               icl.SearchPaymentId(payId);
	         
	            //Verfication Status
	            boolean isStatusSent=icl.verficationOfStatusSent(vendorName, payId);
	            BaseTestCase.assertTrue(isStatusSent, "Payment status is not sent");
	            Reporter.log("Payment status sent is verified for sent payment");
	           
	            
	            //Log out
				 LogoutFromPage.logout();
		     
	       }

	    @Test(priority=3)
    public void verifyInvoiceCreatedInList_SchedulePayment() {
	    	LoginPage loginPage = new LoginPage();
			loginPage.login(testData.get(2), testData.get(3));
			Common.sleep(2000);
			
			//click to create new
			InvoiceCreationForm invoice = new InvoiceCreationForm();
			
			APModuleCreation apModule = invoice.createNew();
			Common.sleep(2000);
			
			//click to AP()
			apModule.clickAPLink();
			Common.sleep(2000);
			
			//Click to New Invoice
			apModule.clickNewInvoice();
			Common.sleep(2000);
			
			//Select value from Vender DropDown
			invoice.SelectVendor(testDataInvoiceActions.get(0));
		    Common.sleep(6000);
		    
		    //Get Invoice number
		    invoiceNo=invoice.getAttributeValueInvoiceNo();
			System.out.println("Invoice number" +invoiceNo);
			
			//Select value from Net Term 
			invoice.SelectNetTerm(testDataInvoiceActions.get(1));
			
			//select value from Location dropdown
			invoice.SelectLocation(testDataInvoiceActions.get(2));	
			
			//Invoice Account details booking account
			invoice.SelectBookingAccount(testDataInvoiceActions.get(3));
			     
			//Invoice Account Description
			invoice.AccountDetails_Description(testDataInvoiceActions.get(4));
				 
		    //Invoice Account Amount
			
			invoice.AccountDetails_Amount(testDataInvoiceActions.get(5));

				
			PurchaseOrderCreationForm purchaseOrder=new PurchaseOrderCreationForm();
			
			//Set items for First line
			
			purchaseOrder.selectProductOrServices(testDataInvoiceActions.get(6), 1);
			purchaseOrder.selectDepartment(testDataInvoiceActions.get(7), 1);
			purchaseOrder.selectBookingAccount(testDataInvoiceActions.get(8), 1);
			purchaseOrder.setDescription(testDataInvoiceActions.get(9), 1);
			purchaseOrder.setQualtity(testDataInvoiceActions.get(11), 1);
			purchaseOrder.setRate(testDataInvoiceActions.get(12), 1);

			//Enter invoice measure
			invoice.Invoice_SelectMeasure(testDataInvoiceActions.get(10));

			//Invoice Enter Message to vendor
			invoice.Invoice_MessageToVendor(testDataInvoiceActions.get(13));

			//Invoice Enter memo
			invoice.Invoice_Memo(testDataInvoiceActions.get(14));
			InvoiceCreationListActions icl1=new InvoiceCreationListActions();
			invoicetotalAmount=icl1.invoiceAmount();



			//Invoice Click on save button
			invoice.Invoice_SaveButton();


			//Invoice assert message verification
			String ExpectedAlertMessage="Invoice was created";
			String ActualAlertMessage=invoice.gettextValue();			   

			boolean check2= ExpectedAlertMessage.equals(ActualAlertMessage);
			BaseTestCase.assertTrue(check2, "Invoice creation failed");
			Common.sleep(6000);
			Reporter.log("Invoice was created successfully");

			//Invoice Create rule click on cancel button
			invoice.CreateRule_CancelButton();

			//Scroll up
			InvoiceCreationListActions.scrollUp();

			//Search invoice
			icl1.searchInvoice(invoiceNo);
			Common.sleep(3000);

			//Accept invoice
			icl1.clickOnAcceptInvoice(testDataVendorList.get(3),invoiceNo);
			Common.sleep(3000);
			String vendorName=testDataVendorList.get(3);


			//Modal accept verification
			boolean ismodalVerificationdetailsCorrect=icl1.verificationModalAcceptButton(invoiceNo,vendorName,invoicetotalAmount);
			System.out.println("verification"+ ismodalVerificationdetailsCorrect);
			BaseTestCase.assertTrue(ismodalVerificationdetailsCorrect, "Modal does not show correct invoice details");
			Reporter.log("Modal accept details are verified");

			//Accept invoice button
			icl1.ModalAcceptButton();
			Common.sleep(3000);

			//Refresh the page
			Common.getDriver().navigate().refresh();

			//search Invoice
			icl1.searchInvoice(invoiceNo);
			Common.sleep(3000);

			//Approve invoices
			icl1.clickOnApproveInvoice(testDataVendorList.get(3),invoiceNo);
		    Common.sleep(3000);
		    
		    //Modal approve verification
			boolean ismodalVerificationdetailsCorrectApprove=icl1.verificationModalAcceptButton(invoiceNo,vendorName,invoicetotalAmount);
			System.out.println("verification"+ ismodalVerificationdetailsCorrectApprove);
			BaseTestCase.assertTrue(ismodalVerificationdetailsCorrectApprove, "Modal does not show correct invoice details");
			Reporter.log("Modal approve details are verified");
			
			//Approve button
			icl1.ModalApproveButton();
			Common.sleep(2000);

			String ExpectedAlertMessage1="Invoices Approved";
            String ActualAlertMessage1=icl1.gettextValue();			   
            System.out.println("Actual value  " + ActualAlertMessage1);  
            boolean check4= ExpectedAlertMessage1.equals(ActualAlertMessage1);
            BaseTestCase.assertTrue(check4, "Invoices are not approved");
            Common.sleep(6000);
           

            //Refresh the page
            Common.getDriver().navigate().refresh();
           
           //Search invoice
            icl1.searchInvoice(invoiceNo);
           Common.sleep(2000);
           
           //Click payment link
           icl1.clickOnCreatePaymentLink(testDataVendorList.get(3),invoiceNo);
           Common.sleep(8000);
            
           //Get payment id
           String payId= icl1.getPaymentId();
           System.out.println("payid-----"+ payId);
           
           
           //Click on payment banner 
           icl1.bannerClick();
           Common.sleep(5000);
          
           
           //Payment details verification
           	boolean isPaymentDetailsverfication=icl1.PaymentVerfication(invoiceNo,vendorName,invoicetotalAmount);
           BaseTestCase.assertTrue(isPaymentDetailsverfication, "Payment Details on banner are not shown correct");
           
           //Click payment button
           icl1.createPaymentButton();
           Common.sleep(2000);
             
           //Payment verification
            String ExpectedAlertMessage2="payment is created";
            String ActualAlertMessage2=icl1.gettextValue();			   
            System.out.println("Actual value payment  " +ActualAlertMessage2);  
            boolean check3= ExpectedAlertMessage2.equals(ActualAlertMessage2);
            BaseTestCase.assertTrue(check3, "Payment creation failed");
            Common.sleep(6000);
            Reporter.log("Payment was created successfully");
           
            //Search invoice
            icl1.searchInvoice(payId);
            
            //Click on approve button
            icl1.clickOnApprovePayment(testDataVendorList.get(3),payId);
            
            boolean isPaymentApproveVerfication=icl1.verificationPaymentApprove(invoiceNo, vendorName, invoicetotalAmount);
            BaseTestCase.assertTrue(isPaymentApproveVerfication, "Payment Details on approve module are not correct");
            Reporter.log("Payment details for approve modal verified");
            
            
            //Click on Schedule payment
            icl1.schedulePaymentButton();
            
            //Refresh
            Common.getDriver().navigate().refresh();
	         
            //Search invoice
            icl1.searchInvoice(payId);            
            
            //Verification Of status schedule
            icl1.verficationOfStatusSchedule(vendorName,payId);
            Common.sleep(3000);
           
            //search invoice by payment id
            icl1.searchInvoice(payId);
            Common.sleep(2000);
           
            //Search 
            icl1.verficationOnSendPaymentLink(vendorName,payId);
            Common.sleep(3000);
            
            //Verification
            icl1.verificationSchedulePayment(invoiceNo, vendorName, invoicetotalAmount, payId);
            Common.sleep(3000);
            
            //Send payment
            icl1.sendPaymentButton_ScheduleModal();
            Common.sleep(3000);
            
            //Refresh
            Common.getDriver().navigate().refresh();
            
            //search invoice by payment id
            icl1.searchInvoice(payId);
            Common.sleep(3000);
            
        
            //Verification Action
            boolean isStatusProcessing= icl1.verficationOnProcessingLink(vendorName,payId);
            BaseTestCase.assertTrue(isStatusProcessing, "Payment action is not processing");
            Reporter.log("Payment action is processing for schedule payment");
            
            //search invoice by payment id
            icl1.searchInvoice(payId);
            Common.sleep(3000);
            
        
            //Verification Status
            boolean isStatusSent=icl1.verficationOfStatusSent(vendorName, payId);
            BaseTestCase.assertTrue(isStatusSent, "Payment status is not sent");
            Reporter.log("Payment status is sent for schedule payment");
            
            //Log out
			 LogoutFromPage.logout();
	   }
}