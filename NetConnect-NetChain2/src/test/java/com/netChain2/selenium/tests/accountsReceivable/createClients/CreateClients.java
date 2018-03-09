package com.netChain2.selenium.tests.accountsReceivable.createClients;

	import java.util.ArrayList;

import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.netChain2.engine.BaseTestCase;
import com.netChain2.engine.Common;
import com.netChain2.selenium.pageObjects.accountsPayable.createInvoice.InvoiceCreationListActions;
import com.netChain2.selenium.pageObjects.accountsReceivable.createClients.ClientsCreationForm;
import com.netChain2.selenium.pageObjects.common.components.CommonMethods;
import com.netChain2.selenium.pageObjects.common.loginPage.LoginPage;
import com.netChain2.selenium.pageObjects.common.logout.LogoutFromPage;
import com.netChain2.utils.CustomAnnotation.TestDetails;

	public class CreateClients {
		private ArrayList<String> testData;
		private ArrayList<String> testDataClients;
		private String clientName;
		ClientsCreationForm arClients=new ClientsCreationForm();
		InvoiceCreationListActions action=new InvoiceCreationListActions();
		
		@BeforeClass
		public void setUp() {
			testData = Common.getTestData("NetchainTest.Login");
			testDataClients=Common.getTestData("AR.NetchainTest.CreateClients");
		}
		 @Test
	     @TestDetails(author="Shital.Patil", description="This method will create a new client from Account receivable > New client")
		 
		public void createARClients() 
		 {
			LoginPage loginPage = new LoginPage();
			loginPage.login(testData.get(6), testData.get(7));
	
			//click on new clinet link
			CommonMethods.gotoRightSideARLink("NEW CLIENT");
			
            clientName=Common.generateRandomString(testDataClients.get(17));
			
			arClients.createClientMethod(testDataClients.get(0), testDataClients.get(1), testDataClients.get(2), testDataClients.get(3), testDataClients.get(4), testDataClients.get(5), testDataClients.get(6), testDataClients.get(7), testDataClients.get(8), testDataClients.get(9), testDataClients.get(10), testDataClients.get(11), testDataClients.get(12), testDataClients.get(13),testDataClients.get(14), testDataClients.get(15), testDataClients.get(16),clientName , testDataClients.get(18), testDataClients.get(19), testDataClients.get(20),testDataClients.get(21), testDataClients.get(22), testDataClients.get(23), testDataClients.get(24), testDataClients.get(25), testDataClients.get(26), testDataClients.get(27),testDataClients.get(28), testDataClients.get(29), testDataClients.get(30), testDataClients.get(31), testDataClients.get(32), testDataClients.get(33));
			
			//verify client in list 
			//search By client Name
			action.searchInvoice(clientName);
			
			Boolean status=arClients.verifyClientOnList(clientName);
			BaseTestCase.assertTrue(status, "Client not created");
			Reporter.log("Client created successfully", true);
			Reporter.log("Client present on list and verified");
			
			Common.sleep(5000);
			LogoutFromPage.logout();
		 }
}
