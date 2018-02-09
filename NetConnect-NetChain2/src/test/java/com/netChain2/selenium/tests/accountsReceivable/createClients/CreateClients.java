package com.netChain2.selenium.tests.accountsReceivable.createClients;

	import java.util.ArrayList;

import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.netChain2.engine.BaseTestCase;
import com.netChain2.engine.Common;
import com.netChain2.selenium.pageObjects.accountsReceivable.createClients.ClientsCreationForm;
import com.netChain2.selenium.pageObjects.common.arCreation.ARModuleCreation;
import com.netChain2.selenium.pageObjects.common.landingPage.LandingPage;
import com.netChain2.selenium.pageObjects.common.loginPage.LoginPage;
import com.netChain2.selenium.pageObjects.common.logout.LogoutFromPage;
import com.netChain2.utils.CustomAnnotation.TestDetails;

	public class CreateClients {
		private ArrayList<String> testData;
		private ArrayList<String> testDataClients;
		private String expectedClientName;
		
		@BeforeClass
		public void setUp() {
			testData = Common.getTestData("NetchainTest.Login");
			testDataClients=Common.getTestData("AR.NetchainTest.CreateClients");
		}
		 @Test
	     @TestDetails(author="Shital.Patil", description="This method will create a new client from Account receivable > New client")
		 
		public void createARClients() {
			
			LoginPage loginPage = new LoginPage();
			loginPage.login(testData.get(6), testData.get(7));
			Common.sleep(2000);
			
			ClientsCreationForm arClients=new ClientsCreationForm();
			ARModuleCreation arModule = arClients.createNew();
			Common.sleep(2000);
			
			//click to AR()
			arModule.clickARLink();
			
			//Click to New Client
			arModule.clickNewClientLink();
			
			//set Company profile Values
			arClients.setCompanyProfile(testDataClients.get(0), testDataClients.get(1), testDataClients.get(2));
			
			//click next button
			arClients.clickCompanyProfileNextButton();
			Common.sleep(3000);
			//click Add Employee Button
			arClients.clickAddEmpButton();
				
			//set Associate Employee values
			arClients.EnterName(testDataClients.get(3));
			Common.sleep(2000);
			arClients.EnterEmail(testDataClients.get(4));
			arClients.EnterPhoneNumber(testDataClients.get(5));
			arClients.EnterTitle(testDataClients.get(6));
			arClients.selectPaymentReceiver();
			Common.sleep(2000);
			
			//click Associate emplyoee Next Button
			arClients.clickAssociateEmpNextButton();
			
			//click Booking Account Add Line Button
			arClients.clickBookingAccntAddLineButton();
			
			//Select Location,Department,Booking Account 
			arClients.SetBookingAccount(testDataClients.get(7), testDataClients.get(8), testDataClients.get(9));
			
			//Click on next Button
			arClients.clickBookingAccountNextButton();
			
			//Transaction Tab 
			arClients.transactionDetails(testDataClients.get(10), testDataClients.get(11), testDataClients.get(12));
			Common.sleep(2000);
			
			arClients.clickTransDetailNextButton();
			
			//Set Client Details
			arClients.setClientIdentity(testDataClients.get(13), testDataClients.get(14), testDataClients.get(15), testDataClients.get(16), testDataClients.get(18), testDataClients.get(19), testDataClients.get(20));
			Common.sleep(2000);
			
			//set Company Name
			expectedClientName=(testDataClients.get(17));
			arClients.SetCompanyName(expectedClientName);
			arClients.clickPrintOnCheck();
			Common.sleep(2000);
			
			//set Billing Address
			arClients.setBillingAddress(testDataClients.get(21), testDataClients.get(22), testDataClients.get(23), testDataClients.get(24));
			Common.sleep(2000);
			
			arClients.clickSameAsBillingAddress();
			Common.sleep(2000);
			
			//Set Contact Details
			String ExpectedEmail=testDataClients.get(25);
			arClients.setContactDetails(ExpectedEmail, testDataClients.get(26), testDataClients.get(27), testDataClients.get(28), testDataClients.get(29), testDataClients.get(30));
			Common.sleep(2000);
			
			arClients.SelectCompanyTire(testDataClients.get(31));
			Common.sleep(2000);
			
			arClients.clickInvoiceDeliveryMethod();
			arClients.setEmailOnInvoiceDelivery(testDataClients.get(32));
			Common.sleep(2000);
			
			arClients.setNotes(testDataClients.get(33));
			Common.sleep(2000);
		
			arClients.clickSaveButton();
			Common.sleep(5000);
		
			//Verify Title And ckeck client in present or not
			String ActualTitleValue=Common.getDriver().getTitle();		
			String ExpectedTitleValue=testDataClients.get(34);
			
			boolean istitleCorrect=arClients.verifyTitleMatched(ActualTitleValue, ExpectedTitleValue);
			BaseTestCase.assertTrue(istitleCorrect, "Redirected to Client list,client created succesfully");
					
			Boolean status=arClients.verifyClientOnList(expectedClientName);
			BaseTestCase.assertTrue(status, "Client not created");
			Reporter.log("Client created successfully",true);
			Common.sleep(6000);
			LogoutFromPage.logout();
		 }
		 

}
