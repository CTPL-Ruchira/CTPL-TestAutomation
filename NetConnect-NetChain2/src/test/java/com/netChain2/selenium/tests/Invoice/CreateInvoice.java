package com.netChain2.selenium.tests.Invoice;

import java.util.ArrayList;


import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.netChain2.engine.BaseTestCase;
import com.netChain2.engine.Common;
import com.netChain2.selenium.pageObjects.accountsPayable.createInvoice.InvoiceCreationForm;
import com.netChain2.selenium.pageObjects.accountsPayable.createPurchaseOrder.PurchaseOrderCreationForm;
import com.netChain2.selenium.pageObjects.common.apCreation.APModuleCreation;
import com.netChain2.selenium.pageObjects.common.landingPage.LandingPage;
import com.netChain2.selenium.pageObjects.common.loginPage.LoginPage;
import com.netChain2.selenium.pageObjects.common.logout.LogoutFromPage;
import com.netChain2.utils.CustomAnnotation.TestDetails;
import org.testng.Reporter;


public class CreateInvoice extends BaseTestCase {
	private ArrayList<String> testData;
	private ArrayList<String> testDataInvoice;
	private ArrayList<String> testDataInvoice2;
	private ArrayList<String> testDataInvoice3;
	private static String invoiceNo;
	
	

	@BeforeClass
	public void setUp() {
		testData = Common.getTestData("NetchainTest.Login");
	    testDataInvoice=Common.getTestData("NetchainTest.CreateInvoice");
        testDataInvoice2=Common.getTestData("NetchainTest.CreateInvoice2");
	    testDataInvoice3=Common.getTestData("NetchainTest.CreateInvoice3");
	}

     @Test
	@TestDetails(author="Roshni.Mehta", description="Create New Invoice") 
	
	  public void testCreateInvoice() {
		
    	LandingPage landingPage = new LandingPage();
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
	    invoiceNo=invoice.getAttributeValueInvoiceNo();
		System.out.println("Invoice number" +invoiceNo);
		
		//Select value from Net Term 
		invoice.SelectNetTerm(testDataInvoice.get(1));
		
		//select value from Location dropdown
		invoice.SelectLocation(testDataInvoice.get(2));	
		
		//Invoice Account details booking account
		invoice.SelectBookingAccount(testDataInvoice.get(3));
		     
		//Invoice Account Description
		invoice.AccountDetails_Description(testDataInvoice.get(4));
			 
	    //Invoice Account Amount
		
		String PreviousAmount=invoice.AccountDetails_Amount(testDataInvoice.get(5));
		double Amount= Double.parseDouble(PreviousAmount);
			
		PurchaseOrderCreationForm purchaseOrder=new PurchaseOrderCreationForm();
		
		//Enter invoice measure
		 invoice.Invoice_SelectMeasure(testDataInvoice.get(10));
			 
		
		//Set items for First line
		 purchaseOrder.setItemDetails(testDataInvoice.get(6),testDataInvoice.get(7),testDataInvoice.get(8),testDataInvoice.get(9), testDataInvoice.get(11), testDataInvoice.get(12));
			
		 //Invoice add line 
		 invoice.Add_Line_Button();

		 boolean isQuantityRoundedForFirstLine=purchaseOrder.verifyRoundingOfNumbers(PurchaseOrderCreationForm.getQualtity(), PurchaseOrderCreationForm.getQty());
		 assertTrue(isQuantityRoundedForFirstLine, "Quantity is not rounded in two decimal digits for first Line");
		 boolean isRateRoundedForFirstLine=purchaseOrder.verifyRoundingOfNumbers(PurchaseOrderCreationForm.getRate(), PurchaseOrderCreationForm.getRt());
		 assertTrue(isRateRoundedForFirstLine, "Rate is not rounded in two decimal digits for first Line");
		 
		 boolean isAmountRoundedForFirstLine=invoice.verifyTotalAmountCalculatedAndShown( Double.parseDouble(PurchaseOrderCreationForm.getPreviousAmount()),Amount);
         assertTrue(isAmountRoundedForFirstLine, "Amount is not rounded in two decimal digits for first Line");
          
         
         //Enter invoice measure for 2nd product Quantity
		 invoice.Invoice_SelectMeasure2(testDataInvoice2.get(10));

		 //Set items for Second line
		 purchaseOrder.setItemDetails(testDataInvoice2.get(6),testDataInvoice2.get(7),testDataInvoice2.get(8),testDataInvoice2.get(9), testDataInvoice2.get(11), testDataInvoice2.get(12));

		 //Invoice add line 
		 invoice.Add_Line_Button();

		 boolean isQuantityRoundedForSecondLine=purchaseOrder.verifyRoundingOfNumbers(PurchaseOrderCreationForm.getQualtity(), PurchaseOrderCreationForm.getQty());
		 assertTrue(isQuantityRoundedForSecondLine, "Quantity is not rounded in two decimal digits for second Line");
		 boolean isRateRoundedForSecondLine=purchaseOrder.verifyRoundingOfNumbers(PurchaseOrderCreationForm.getRate(), PurchaseOrderCreationForm.getRt());
		 assertTrue(isRateRoundedForSecondLine, "Rate is not rounded in two decimal digits for second Line");
		 boolean isAmountRoundedForSecondLine=invoice.verifyTotalAmountCalculatedAndShown( Double.parseDouble(PurchaseOrderCreationForm.getPreviousAmount()),Amount);
		 assertTrue(isAmountRoundedForSecondLine, "Amount is not rounded in two decimal digits for second Line");


		//Enter invoice measure
		 invoice.Invoice_SelectMeasure3(testDataInvoice3.get(10));

		 //Set items for Third line
		 purchaseOrder.setItemDetails(testDataInvoice3.get(6),testDataInvoice3.get(7),testDataInvoice3.get(8),testDataInvoice3.get(9), testDataInvoice3.get(11), testDataInvoice3.get(12));

		 boolean isQuantityRoundedForThirdLine=purchaseOrder.verifyRoundingOfNumbers(PurchaseOrderCreationForm.getQualtity(), PurchaseOrderCreationForm.getQty());
		 assertTrue(isQuantityRoundedForThirdLine, "Quantity is not rounded in two decimal digits for third Line");
		 boolean isRateRoundedForThirdLine=purchaseOrder.verifyRoundingOfNumbers(PurchaseOrderCreationForm.getRate(), PurchaseOrderCreationForm.getRt());
		 assertTrue(isRateRoundedForThirdLine, "Rate is not rounded in two decimal digits for third Line");
		 boolean isAmountRoundedForThirdLine=invoice.verifyTotalAmountCalculatedAndShown( Double.parseDouble(PurchaseOrderCreationForm.getPreviousAmount()),Amount);
		 assertTrue(isAmountRoundedForThirdLine, "Amount is not rounded in two decimal digits for third Line");

		
		 //Invoice Enter Message to vendor
		 invoice.Invoice_MessageToVendor(testDataInvoice.get(13));

		 //Invoice Enter memo
		 invoice.Invoice_Memo(testDataInvoice.get(14));
		
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
		
}	

