package com.netChain2.selenium.tests.Invoice;

import java.util.ArrayList;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.netChain2.engine.BaseTestCase;
import com.netChain2.engine.Common;
import com.netChain2.selenium.pageObjects.accountsPayable.createInvoice.CheckTwoWayMatchInvoice;
import com.netChain2.selenium.pageObjects.accountsPayable.createInvoice.InvoiceCreationForm;
import com.netChain2.selenium.pageObjects.common.apCreation.APModuleCreation;
import com.netChain2.selenium.pageObjects.common.landingPage.LandingPage;
import com.netChain2.selenium.pageObjects.common.loginPage.LoginPage;
import com.netChain2.selenium.pageObjects.common.logout.LogoutFromPage;
import com.netChain2.utils.CustomAnnotation.TestDetails;

public class POTwoWayMatchInvoiceByQuantity extends BaseTestCase {
	private ArrayList<String> testData;
	

	private ArrayList<String> testDataVendorList;
	private ArrayList<String> testDataInvoice2;

	private ArrayList<String> testdatatwowaymatch;
	private String invoiceNo;
	InvoiceCreationForm invoice = new InvoiceCreationForm();
	CheckTwoWayMatchInvoice TwoWayMatch=new CheckTwoWayMatchInvoice();
	
	@BeforeClass
	public void setUp() {
		testData = Common.getTestData("NetchainTest.Login");
        testDataVendorList=Common.getTestData("NetchainTest.InvoiceListVendor");
	    testDataInvoice2=Common.getTestData("NetchainTest.CreateInvoice2");
	    testdatatwowaymatch=Common.getTestData("NetchainTest.TwoWayMatch");
	  		
	}

	 @Test
     @TestDetails(author="Roshni.Mehta", description="Two Way Match")
	 
	
	  public void testCreateInvoice_CreatePayment() {
		 
		
		LandingPage landingPage = new LandingPage();
	    landingPage.clickLogInButton();
		
	    //Login
		LoginPage loginPage = new LoginPage();
		loginPage.login(testData.get(4), testData.get(5));
		Common.sleep(2000);
		
		//click to create new
		APModuleCreation apModule = invoice.createNew();
		Common.sleep(2000);
		
		//click to AP()
		apModule.clickAPLink();
		Common.sleep(2000);
		
		//Click to New Invoice
		apModule.clickNewInvoice();
		Common.sleep(2000);
		
		//Select value from Vender DropDown
		invoice.SelectVendor(testdatatwowaymatch.get(0));
	    Common.sleep(6000);
	    
	    //Get Invoice number
	     invoiceNo=invoice.getAttributeValueInvoiceNo();
		System.out.println("Invoice number"+invoiceNo);
	    
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
		TwoWayMatch.SelectPONumber_Invoice(testdatatwowaymatch.get(15));
		Common.sleep(5000);
		
		//Select quantity
		TwoWayMatch.SelectQuantity_Invoice(testdatatwowaymatch.get(11));
	 
		//Click on save button
	   invoice.Invoice_SaveButton();
	   
	   //Click on create rule button
	   invoice.CreateRule_CancelButton();
	   
	   //Search invoice
	   TwoWayMatch.searchInvoice(invoiceNo);
	   
	   //Create payment link verification
	   boolean isCreatePaymentLinkVisible=TwoWayMatch.CheckInvoiceStatus_CreatePayment(testDataVendorList.get(0),invoiceNo);
	   assertTrue(isCreatePaymentLinkVisible, "Custom Workflow is not set");
	   Common.sleep(5000);
	   Reporter.log("Create payment link is visible as per custom workflow");
	   LogoutFromPage.logout();
	 }
	 
	 @Test
	public void  testCreateInvoice_Discrepant (){
        
		LoginPage loginPage = new LoginPage();
 		loginPage.login(testData.get(4), testData.get(5));
 		Common.sleep(5000);
 		CheckTwoWayMatchInvoice.scrollUp();
 		
 		APModuleCreation apModule = invoice.createNew();
		Common.sleep(3000);
		
		//click to AP()
		apModule.clickAPLink();
		Common.sleep(2000);
		
		//Click to New Invoice
		apModule.clickNewInvoice();
		Common.sleep(3000);
		
		//Select value from Vender DropDown
		invoice.SelectVendor(testdatatwowaymatch.get(0));
	    Common.sleep(6000);
	    
	    //Get Invoice number
	     String invoiceNumber=invoice.getAttributeValueInvoiceNo();
		System.out.println("Invoice number"+invoiceNumber);
	    
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
		TwoWayMatch.SelectPONumber_Invoice(testdatatwowaymatch.get(15));
		Common.sleep(6000);
		
		//Select Quantity
		TwoWayMatch.SelectQuantity_Invoice(testDataInvoice2.get(11));
		
		//Select Save button
		invoice.Invoice_SaveButton();
		
		//Select Cancel button on Create rule button
		invoice.CreateRule_CancelButton();
		
		//Search Invoice number
		TwoWayMatch.searchInvoice(invoiceNumber);
		
		//Invoice Accept link click
		TwoWayMatch.InvoiceAcceptLinkClick(testDataVendorList.get(0),invoiceNumber);
		
		//Invoice Accept button on link
		TwoWayMatch.ModalAcceptButton();
		Common.sleep(3000);
		
		//Refresh the page
		 Common.getDriver().navigate().refresh();
		
		//Search Invoice number
		TwoWayMatch.searchInvoice(invoiceNumber);
		
		//check status 
		boolean IsDiscrepantStatusVisible=TwoWayMatch.CheckInvoiceStatus_Discrepant(testDataVendorList.get(0),invoiceNumber);
	    assertTrue(IsDiscrepantStatusVisible,"Discrepant status is not visible");
	    Reporter.log("Status is discrepant as per custom workflow");
	    Common.sleep(3000);
	    LogoutFromPage.logout();
	   
	 } 

}
	


	 

