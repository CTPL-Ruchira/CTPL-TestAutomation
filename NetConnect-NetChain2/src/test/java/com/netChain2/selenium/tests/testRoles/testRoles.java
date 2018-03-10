package com.netChain2.selenium.tests.testRoles;

import java.util.ArrayList;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.netChain2.engine.BaseTestCase;
import com.netChain2.engine.Common;
import com.netChain2.selenium.pageObjects.accountsPayable.Roles.Roles;
import com.netChain2.selenium.pageObjects.accountsPayable.createInvoice.InvoiceCreationForm;
import com.netChain2.selenium.pageObjects.accountsPayable.createInvoice.InvoiceCreationListActions;
import com.netChain2.selenium.pageObjects.accountsPayable.createPurchaseOrder.PurchaseOrderCreationForm;
import com.netChain2.selenium.pageObjects.accountsPayable.settings.Settings;
import com.netChain2.selenium.pageObjects.common.components.CommonMethods;
import com.netChain2.selenium.pageObjects.common.loginPage.LoginPage;
import com.netChain2.selenium.pageObjects.common.logout.LogoutFromPage;
import com.netChain2.utils.CustomAnnotation.TestDetails;

public class testRoles extends BaseTestCase 
{
	private ArrayList<String> loginTestData;
    private ArrayList<String> setRolesWorkFlow;
	private String getInvoiceId;
	
	InvoiceCreationForm icf=new InvoiceCreationForm();
	PurchaseOrderCreationForm pocf=new PurchaseOrderCreationForm();
	Settings customWorkFlowSet=new Settings();
	InvoiceCreationListActions ica=new InvoiceCreationListActions();
	Roles role=new Roles();
	LoginPage loginPage = new LoginPage();
	
	@BeforeClass
	public void setUp() 
	{
		loginTestData = Common.getTestData("NetchainTest.Login");
        setRolesWorkFlow=Common.getTestData("Netchain.CheckRoles");
	}
   
  /* @Test
	@TestDetails(author="Roshni Mehta", description="This methods sets custom workflow for invoice")
   public void setCustomWorkFlow() 
   {	
	   //Login
	    loginPage.login(loginTestData.get(10), loginTestData.get(11));

		//Click on settings
		customWorkFlowSet.openSettings();
        
		//set custom WorkFlow
		customWorkFlowSet.createNewCustomWorkflow();
		
		//set auto accept value
		customWorkFlowSet.autoAcceptValue(setRolesWorkFlow.get(0));
	
		//Select role for auto accept from dropdown
		role.autoAcceptRoleDropdown(setRolesWorkFlow.get(1));
		
		//set auto accept value
		customWorkFlowSet.autoAprroveValue(setRolesWorkFlow.get(2));
   
		//Select role for auto accept from dropdown
		role.autoApproveRoleDropdown(setRolesWorkFlow.get(3));
		
		//set auto accept value
		customWorkFlowSet.autocreatePaymentValue(setRolesWorkFlow.get(4));
   
		//Select role for auto accept from dropdown
		role.autoCreatePaymentRoleDropdown(setRolesWorkFlow.get(5));
   
		//set auto accept value
		customWorkFlowSet.autoapprovePaymentValue(setRolesWorkFlow.get(6));
  
		//Select role for auto accept from dropdown
		role.autoApprovePaymentRoleDropdown(setRolesWorkFlow.get(7));
   
		//Click on finish button
		customWorkFlowSet.clickOnFinishButton();

		 //Logout
		  LogoutFromPage.logout();
	}	  
		  
   @Test(dependsOnMethods="setCustomWorkFlow")
   @TestDetails(author="Roshni Mehta", description="This methods verify action acceptance pending for differenr roles")
   public void checkActionAcceptancePending() 
   {
	   //Login
	   loginPage.login(loginTestData.get(10), loginTestData.get(11));

	   //Scroll up
	   Settings.scrollUp();

	   //Go to new invoice
	   CommonMethods.gotoRightSideAPLink("NEW INVOICE");

	   //Select vendor,netterm,location
	   icf.setVendorDetails(setRolesWorkFlow.get(8), setRolesWorkFlow.get(9), setRolesWorkFlow.get(10));
	   
	   //get invoice id 
	   getInvoiceId= icf.getAttributeValueInvoiceNo();

	   //Select product name,department ,booking account,description,measure,quantity,rate
	   pocf.setItemDetailsForSingleLine(setRolesWorkFlow.get(11),setRolesWorkFlow.get(12),setRolesWorkFlow.get(13),setRolesWorkFlow.get(14),setRolesWorkFlow.get(15),setRolesWorkFlow.get(16),setRolesWorkFlow.get(17),1);

	   //Enter message ,memo
	   role.invoiceCreationFormMessageMemo(setRolesWorkFlow.get(18), setRolesWorkFlow.get(19));

	   //Search invoice
	   //ica.searchInvoice(getInvoiceId);

	   //Scroll up
	   Settings.scrollUp();

	   //Click on sorting arrow
	   Common.click("SORTING_ARROW_XPATH");
	   Common.sleep(4000);

	   //Verification of acceptance 
	   role.verificationForAcceptancePending(setRolesWorkFlow.get(8), getInvoiceId, setRolesWorkFlow.get(20));

	   //Logout
	   LogoutFromPage.logout();

	   //Login
	   loginPage.login(loginTestData.get(14), loginTestData.get(15));

	   //Scroll up
	   Settings.scrollUp();

	   //Search invoice
	   //ica.searchInvoice(getInvoiceId);

	   //Click on sorting arrow
	   Common.click("SORTING_ARROW_XPATH");
	   Common.sleep(4000);

	   //Verfication of accept invoice
	   role.verificationForAcceptInvoice(setRolesWorkFlow.get(8), getInvoiceId,  setRolesWorkFlow.get(21));

	   //Logout
	   LogoutFromPage.logout();

	   //Login
	   loginPage.login(loginTestData.get(16), loginTestData.get(17));

	   //Scroll up
	   Settings.scrollUp();

	   //Click on sorting arrow
	   Common.click("SORTING_ARROW_XPATH");
	   Common.sleep(4000);

	   //Verification of acceptance 
	   role.verificationForAcceptancePending(setRolesWorkFlow.get(8), getInvoiceId, setRolesWorkFlow.get(20));
	   Common.sleep(1000);

	   //Logout
	   LogoutFromPage.logout();
   }

   @Test(dependsOnMethods="checkActionAcceptancePending")
   @TestDetails(author="Roshni Mehta", description="This methods verify action approve pending for different roles")
   public void checkActionApprovalPending() 
   {
	   //Login
	   loginPage.login(loginTestData.get(10), loginTestData.get(11));

	   //Scroll up
	   Settings.scrollUp();

	   //Go to new invoice
	   CommonMethods.gotoRightSideAPLink("NEW INVOICE");

	   //Select vendor,netterm,location
	   icf.setVendorDetails(setRolesWorkFlow.get(8), setRolesWorkFlow.get(9), setRolesWorkFlow.get(10));
	   
	   //get invoice id 
	   getInvoiceId= icf.getAttributeValueInvoiceNo();

	   //Select product name,department ,booking account,description,measure,quantity,rate
	   pocf.setItemDetailsForSingleLine(setRolesWorkFlow.get(11),setRolesWorkFlow.get(12),setRolesWorkFlow.get(13),setRolesWorkFlow.get(14),setRolesWorkFlow.get(15),setRolesWorkFlow.get(22),setRolesWorkFlow.get(23),1);

	   //Enter message ,memo
	   role.invoiceCreationFormMessageMemo(setRolesWorkFlow.get(18), setRolesWorkFlow.get(19));

	   //Search invoice
	   //ica.searchInvoice(getInvoiceId);

	   //Scroll up
	   Settings.scrollUp();

	   //Click on sorting arrow
	   Common.click("SORTING_ARROW_XPATH");
	   Common.sleep(4000);

	   //Verfication of accept invoice
	   role.verificationForApprovalPending(setRolesWorkFlow.get(8), getInvoiceId, setRolesWorkFlow.get(24));
      
	   //Logout
	   LogoutFromPage.logout();

	   //Login
	   loginPage.login(loginTestData.get(14), loginTestData.get(15));

	   //Scroll up
	   Settings.scrollUp();

	   //Search invoice
	   //ica.searchInvoice(getInvoiceId);

	   //Click on sorting arrow
	   Common.click("SORTING_ARROW_XPATH");
	   Common.sleep(4000);
   
	   //Verfication of accept invoice
	   role.verificationForApprovalPending(setRolesWorkFlow.get(8), getInvoiceId, setRolesWorkFlow.get(24));
	  
	   //Logout
	   LogoutFromPage.logout();
	  
	   //Login
	   loginPage.login(loginTestData.get(16), loginTestData.get(17));

	   //Scroll up
	   Settings.scrollUp();

	   //Click on sorting arrow
	   Common.click("SORTING_ARROW_XPATH");
	   Common.sleep(4000);
	 
	   //Verfication of accept invoice
	   customWorkFlowSet.verificationForAutoApproveLink(setRolesWorkFlow.get(8), getInvoiceId, setRolesWorkFlow.get(25));
	  
	   //Logout
	   LogoutFromPage.logout();
    }
   
   @Test(dependsOnMethods="checkActionApprovalPending")
   @TestDetails(author="Roshni Mehta", description="This method verify action create payment pending for different roles")
   public void checkActionPendingPayment() 
   {
	   //Login
	   loginPage.login(loginTestData.get(10), loginTestData.get(11));

	   //Scroll up
	   Settings.scrollUp();

	   //Go to new invoice
	   CommonMethods.gotoRightSideAPLink("NEW INVOICE");

	   //Select vendor,netterm,location
	   icf.setVendorDetails(setRolesWorkFlow.get(8), setRolesWorkFlow.get(9), setRolesWorkFlow.get(10));
	   
	   //get invoice id 
	   getInvoiceId= icf.getAttributeValueInvoiceNo();

	   //Select product name,department ,booking account,description,measure,quantity,rate
	   pocf.setItemDetailsForSingleLine(setRolesWorkFlow.get(11),setRolesWorkFlow.get(12),setRolesWorkFlow.get(13),setRolesWorkFlow.get(14),setRolesWorkFlow.get(15),setRolesWorkFlow.get(26),setRolesWorkFlow.get(27),1);

	   //Enter message ,memo
	   role.invoiceCreationFormMessageMemo(setRolesWorkFlow.get(18), setRolesWorkFlow.get(19));

	   //Search invoice
	   //ica.searchInvoice(getInvoiceId);

	   //Scroll up
	   Settings.scrollUp();

	   //Click on sorting arrow
	   Common.click("SORTING_ARROW_XPATH");
	   Common.sleep(4000);

	   //Verfication of action payement pending 
	   role.verificationForPaymentPending(setRolesWorkFlow.get(8), getInvoiceId, setRolesWorkFlow.get(28));
      
	   //Logout
	   LogoutFromPage.logout();

	   //Login
	   loginPage.login(loginTestData.get(14), loginTestData.get(15));

	   //Scroll up
	   Settings.scrollUp();

	   //Search invoice
	   //ica.searchInvoice(getInvoiceId);

	   //Click on sorting arrow
	   Common.click("SORTING_ARROW_XPATH");
	   Common.sleep(4000);
   
	   //Verfication of create payment
	   customWorkFlowSet.verificationForCreatePayment(setRolesWorkFlow.get(8), getInvoiceId, setRolesWorkFlow.get(29));
	   
	   //Logout
	   LogoutFromPage.logout();
	  
	   //Login
	   loginPage.login(loginTestData.get(16), loginTestData.get(17));

	   //Scroll up
	   Settings.scrollUp();

	   //Click on sorting arrow
	   Common.click("SORTING_ARROW_XPATH");
	   Common.sleep(4000);
	 
	   //Verification for action payment pending
	   role.verificationForPaymentPending(setRolesWorkFlow.get(8), getInvoiceId, setRolesWorkFlow.get(28));
	      
	   //Logout
	   LogoutFromPage.logout();
     }*/
   
   @Test
	//@Test(dependsOnMethods="checkActionPendingPayment")
   @TestDetails(author="Roshni Mehta", description="This methods verify action approve payment pending for different roles")
   public void checkActionapprovePayment() 
   {
	   //Login
	   loginPage.login(loginTestData.get(10), loginTestData.get(11));

	   //Scroll up
	   Settings.scrollUp();

	   //Go to new invoice
	   CommonMethods.gotoRightSideAPLink("NEW INVOICE");

	   //Select vendor,netterm,location
	   icf.setVendorDetails(setRolesWorkFlow.get(8), setRolesWorkFlow.get(9), setRolesWorkFlow.get(10));

	   //get invoice id 
	   getInvoiceId= icf.getAttributeValueInvoiceNo();

	   //Select product name,department ,booking account,description,measure,quantity,rate
	   pocf.setItemDetailsForSingleLine(setRolesWorkFlow.get(11),setRolesWorkFlow.get(12),setRolesWorkFlow.get(13),setRolesWorkFlow.get(14),setRolesWorkFlow.get(15),setRolesWorkFlow.get(30),setRolesWorkFlow.get(31),1);

	   //Enter message ,memo
	   role.invoiceCreationFormMessageMemo(setRolesWorkFlow.get(18), setRolesWorkFlow.get(19));
	   
	   //Search invoice
	   //ica.searchInvoice(getInvoiceId);
	    Common.sleep(6000);
	
	   //Click on payments
	   CommonMethods.gotoLeftAPLink("Payments");
	 
	  //Verification for action payment pending
	   role.verificationForApprovalPending(setRolesWorkFlow.get(8), getInvoiceId,setRolesWorkFlow.get(24));
	   
	   //Logout
	   LogoutFromPage.logout();

	   //Login
	   loginPage.login(loginTestData.get(14), loginTestData.get(15));

        //Search invoice
	   //ica.searchInvoice(getInvoiceId);

	   //Click on payments
	   CommonMethods.gotoLeftAPLink("Payments");
      
	   //Scroll up
	   Settings.scrollUp();
	   Common.sleep(3000); 
	   
	   //Verification for action payment pending
	   role.verificationForApprovalPending(setRolesWorkFlow.get(8), getInvoiceId,setRolesWorkFlow.get(24));

	   //Logout
	   LogoutFromPage.logout();

	   //Login
	   loginPage.login(loginTestData.get(16), loginTestData.get(17));

	   //Click on payments
	   CommonMethods.gotoLeftAPLink("Payments");
	  
	   //Scroll up
	   Settings.scrollUp();
	   
	   //Verification for approve payment
	   customWorkFlowSet.verificationForApprovePayment(setRolesWorkFlow.get(8), getInvoiceId,setRolesWorkFlow.get(24));

	   //Logout
	   LogoutFromPage.logout();
   }

}