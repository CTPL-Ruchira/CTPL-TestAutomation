package com.netChain2.selenium.tests.Invoice;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.netChain2.engine.BaseTestCase;
import com.netChain2.engine.Common;
import com.netChain2.selenium.pageObjects.accountsPayable.createInvoice.InvoiceCreationForm;
import com.netChain2.selenium.pageObjects.common.apCreation.APModuleCreation;
import com.netChain2.selenium.pageObjects.common.landingPage.LandingPage;
import com.netChain2.selenium.pageObjects.common.loginPage.LoginPage;
import com.netChain2.utils.CustomAnnotation.TestDetails;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;


public class CreateInvoice {
	private ArrayList<String> testData;
	private ArrayList<String> testDataInvoice;
    private ArrayList<String> testDataInvoiceList;
	private ArrayList<String> testDataVendorList;
	private ArrayList<String> testDataInvoice2;
	private ArrayList<String> testDataInvoice3;
	private String invoiceNo;
	
	@BeforeClass
	public void setUp() {
		testData = Common.getTestData("NetchainTest.Login");
		//testData1=Common.getTestData("NetchainTest.CreateVendor");
		testDataInvoice=Common.getTestData("NetchainTest.CreateInvoice");
	    testDataInvoiceList=Common.getTestData("NetchainTest.invListSearchBar");
	    testDataVendorList=Common.getTestData("NetchainTest.InvoiceListVendor");
	    testDataInvoice2=Common.getTestData("NetchainTest.CreateInvoice2");
	    testDataInvoice3=Common.getTestData("NetchainTest.CreateInvoice3");
	}

	 @Test
     @TestDetails(author="Roshni.Mehta", description="Create New Invoice")
	 
	
	  public void testCreateInvoice() {
		 
		
		LandingPage landingPage = new LandingPage();
		//boolean check1 = landingPage.isLoginButtonDisplayed();
				
		landingPage.clickLogInButton();
		
		LoginPage loginPage = new LoginPage();
		loginPage.login(testData.get(0), testData.get(1));
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
		invoice.SelectVendor(testDataInvoice.get(0));
	    Common.sleep(6000);
	    
	    //Get Invoice number
	     String invoiceNo=invoice.getAttributeValueInvoiceNo();
		System.out.println("Invoice number"+invoiceNo);
	    
		//Select value from Net Term 
		invoice.SelectNetTerm(testDataInvoice.get(1));
		
		//select value from Location dropdown
		invoice.SelectLocation(testDataInvoice.get(2));	
		
		//Invoice Account details booking account
		invoice.SelectBookingAccount(testDataInvoice.get(3));
		     
		//Invoice Account Description
		invoice.AccountDetails_Description(testDataInvoice.get(4));
			 
	    //Invoice Account Amount
		invoice.AccountDetails_Amount(testDataInvoice.get(5));
				
	    //Invoice Select Item Product and services Dropdown
		invoice.SelectProductAndServicesDrp(testDataInvoice.get(6));
				
		//Invoice Select department
		invoice.SelectItemDetailsDepartment(testDataInvoice.get(7));
			
	    //Select invoice Booking amount
		invoice.SelectBookingAccount_Item(testDataInvoice.get(8));
		
	     //item detail description
		 invoice.Invoice_Description(testDataInvoice.get(9));
			 
		 //Enter invoice measure
		 invoice.Invoice_SelectMeasure(testDataInvoice.get(10));
			 
		 //Enter invoice Quantity
		 invoice.Invoice_Quantity(testDataInvoice.get(11));
			 
		 //Invoice Enter rate
		  invoice.Invoice_Rate(testDataInvoice.get(12));
		  
		 //Invoice add line 
		 invoice.Add_Line_Button();
		  
		 //Invoice Select 2nd Item Product and services Dropdown 
		 invoice.SelectProductAndServicesDrp2(testDataInvoice2.get(0));

		  //Select invoice booking amount for 2nd product
		  invoice.SelectBookingAccount2_Item(testDataInvoice2.get(1));
		  
		 //Invoice Select 2nd Item description details
		 invoice.Invoice_Description2(testDataInvoice2.get(2));
		  
		 //Enter invoice measure for 2nd product Quantity
		 invoice.Invoice_SelectMeasure2(testDataInvoice2.get(3));
		 
		 //Enter Quantity for 2nd item
		 invoice.Invoice_Quantity2(testDataInvoice2.get(4));
		  
		  //Enter rate for 2nd item
		  invoice.Invoice_Rate2(testDataInvoice2.get(5));
		  
		  //Invoice add line button
		  invoice.Add_Line_Button_2();
		  
		  //Invoice Select 3rd Item Product and services Dropdown 
		  invoice.SelectProductAndServicesDrp3(testDataInvoice3.get(0));	
		  
		  //Select invoice booking amount for 2nd product
		  invoice.SelectBookingAccount3_Item(testDataInvoice3.get(1));
		  
		  //Invoice Select 3rd Item description details
		  invoice.Invoice_Description3(testDataInvoice3.get(2));
			  
		  //Enter invoice measure for 3rd product 
		  invoice.Invoice_SelectMeasure3(testDataInvoice3.get(3));
		  
		  //Enter Quantity for 3rd item
		  invoice.Invoice_Quantity3(testDataInvoice3.get(4));
		  
		  //Invoice Enter rate for 3rd product
		  invoice.Invoice_Rate3(testDataInvoice3.get(5));
			 
		  //Invoice Enter Message to vendor
		  invoice.Invoice_MessageToVendor(testDataInvoice.get(13));
			 
		  //Invoice Enter memo
		  invoice.Invoice_Memo(testDataInvoice.get(14));
		   
		  //Invoice Click on save button
		  invoice.Invoice_SaveButton();
		 
		  
		 //Invoice assert message
		  String ExpectedAlertMessage="Invoice was created";
		  String ActualAlertMessage=invoice.gettextValue();			   
		  System.out.println("actual value is" +ActualAlertMessage);
		  
		  boolean check2= ExpectedAlertMessage.equals(ActualAlertMessage);
		  BaseTestCase.assertTrue(check2, "Invoice creation failed");
		  Common.sleep(6000);
		  Reporter.log("Invoice was created successfully");
		  
		  //Invoice Create rule click on cancel button
		   invoice.CreateRule_CancelButton();
	 }
	
	
	/*@Test 
	public void verifyInvoiceCreatedInList() {
		 
			
			LandingPage landingPage = new LandingPage();
			//boolean check1 = landingPage.isLoginButtonDisplayed();
					
			landingPage.clickLogInButton();
			
			LoginPage loginPage = new LoginPage();
			loginPage.login(testData.get(0), testData.get(1));
			Common.sleep(7000);
			
			//side menu bar
			Common.click("NAVIGATION_MENU_CLOSE_XPATH");
			Common.click("NAVIGATION_MENU_INVOICE_XPATH");
			Common.sleep(1000);
			Common.click("NAVIGATION_MENU_CLOSE_XPATH");
			Common.sleep(3000);
		
			InvoiceCreationList icl=new InvoiceCreationList();
			
			//Search invoice in list
			icl.searchInvoice(testDataInvoiceList.get(0));
			Common.sleep(1000);
			
			//Accept invoice
			icl.clickOnAcceptInvoice(testDataVendorList.get(0),"12");
	        
	
	}*/


	 
	
	 
	 

}

