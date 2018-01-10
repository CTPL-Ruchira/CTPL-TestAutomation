package com.connecticus.netchain2.tests.demo;

import java.util.ArrayList;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.connecticus.engine.BaseTestCase;
import com.connecticus.engine.Common;
import com.connecticus.netchain2.pageObjects.accountsPayable.createInvoice.Invoice;
import com.connecticus.netchain2.pageObjects.accountsPayable.createVendor.VendorCreationForm;
import com.connecticus.netchain2.pageObjects.common.apCreation.APModuleCreation;
import com.connecticus.netchain2.pageObjects.common.landingPage.LandingPage;
import com.connecticus.netchain2.pageObjects.common.loginPage.LoginPage;
import com.connecticus.utils.CustomAnnotation.TestDetails;


public class DemoTest extends BaseTestCase{
	private ArrayList<String> testData;
	private ArrayList<String> testData1;
	@BeforeClass
	public void setUp() {
		testData = Common.getTestData("NetchainTest.Login");
		testData1 = Common.getTestData("NetchainTest.CreateVendor");
	}

	 @Test
     @TestDetails(author="Ruchira.Mhaisurkar", description="demo test")
	 
	public void launchLoginPage() {
		 
		
		LandingPage landingPage = new LandingPage();
		boolean check1 = landingPage.isLoginButtonDisplayed();
		
		
		landingPage.clickLogInButton();
		
		
		LoginPage loginPage = new LoginPage();
		loginPage.login(testData.get(0), testData.get(1));
		Common.sleep(2000);
		
		
		Invoice invoice = new Invoice();
		
		APModuleCreation apModule = invoice.createNew();
		Common.sleep(2000);
		apModule.clickAPLink();
		Common.sleep(2000);
		apModule.clickNewVendorLink();
		
		VendorCreationForm vendorCreatio = new VendorCreationForm();
		
		vendorCreatio.setCompanyProfileTab(testData1.get(0), testData1.get(1), testData1.get(2));
		BaseTestCase.assertTrue(check1, "Log In Button not displayed");
		
		
	}
}