package com.netChain2.selenium.tests.Invoice;

import java.util.ArrayList;

import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.netChain2.engine.BaseTestCase;
import com.netChain2.engine.Common;
import com.netChain2.selenium.pageObjects.accountsPayable.createInvoice.Invoice;
import com.netChain2.selenium.pageObjects.common.apCreation.APModuleCreation;
import com.netChain2.selenium.pageObjects.common.landingPage.LandingPage;
import com.netChain2.selenium.pageObjects.common.loginPage.LoginPage;
import com.netChain2.utils.CustomAnnotation.TestDetails;

public class CreateInvoice {
	private ArrayList<String> testData;
	private ArrayList<String> testData1;
	private ArrayList<String> testDataInvoice;

	//Date  date=new Date();
	

	@BeforeClass
	public void setUp() {
		testData = Common.getTestData("NetchainTest.Login");
		//testData1=Common.getTestData("NetchainTest.CreateVendor");
		testDataInvoice=Common.getTestData("NetchainTest.CreateInvoice");
	}

	 @Test
     @TestDetails(author="Shital.Patil", description="Create New Invoice")
	 
	public void launchLoginPage() {
		 
		
		LandingPage landingPage = new LandingPage();
		//boolean check1 = landingPage.isLoginButtonDisplayed();
				
		landingPage.clickLogInButton();
		
		LoginPage loginPage = new LoginPage();
		loginPage.login(testData.get(0), testData.get(1));
		Common.sleep(2000);
		
		//click to create new
		Invoice invoice = new Invoice();
		APModuleCreation apModule = invoice.createNew();
		Common.sleep(2000);
		
		//click to AP()
		apModule.clickAPLink();
		Common.sleep(2000);
		
		//Click to New Invoice
		apModule.clickNewInvoicelink();
		Common.sleep(2000);
		//Check and test title of the invoice page 
		String ActualTitleValue=Common.getDriver().getTitle();
		System.out.println("Actual Value Is :"+Common.getDriver().getTitle());
		
		String ExpectedtitleValue=testDataInvoice.get(14);
		System.out.println("ExpectTitle is:"+testDataInvoice.get(14));
		
		
		if(ActualTitleValue.equals(ExpectedtitleValue)) {
			BaseTestCase.assertTrue(true, "Page title are same");
		}
		else {
			BaseTestCase.assertTrue(false, "Page title are not same");
		}
		Reporter.log("invoice Page redirect successfully", true);
		
		
		//Select value from Vender DropDown
		invoice.SelectVendor(testDataInvoice.get(0));
		Common.sleep(2000);
		
		//Select value from Net Term 
		invoice.SelectNetTerm(testDataInvoice.get(1));
		
		//select value from Location dropdown
		invoice.SelectLocation(testDataInvoice.get(2));	
	 }
}
