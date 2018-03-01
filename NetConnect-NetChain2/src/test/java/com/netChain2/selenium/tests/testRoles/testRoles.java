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
import com.netChain2.utils.CustomAnnotation.TestDetails;

public class testRoles extends BaseTestCase {
	private ArrayList<String> loginTestData;
	//private ArrayList<String> customWorkflowValues;
	private ArrayList<String> setRolesWorkFlow;
	
	InvoiceCreationForm icf=new InvoiceCreationForm();
	PurchaseOrderCreationForm pocf=new PurchaseOrderCreationForm();
	Settings customWorkFlowSet=new Settings();
	InvoiceCreationListActions ica=new InvoiceCreationListActions();
	
	@BeforeClass
	public void setUp() 
	{
		loginTestData = Common.getTestData("NetchainTest.Login");
        //customWorkflowValues=Common.getTestData("NetchainTest.CustomWorkflow");	
	    setRolesWorkFlow=Common.getTestData("Netchain.CheckRoles");
	}
   
   @Test
	@TestDetails(author="Roshni Mehta", description="This methods sets custom workflow for invoice")
   public void setCustomWorkFlow() {	
	  //Login
	   LoginPage loginPage = new LoginPage();
		loginPage.login(loginTestData.get(10), loginTestData.get(11));

		//Click on settings
		customWorkFlowSet.openSettings();
        
		//set custom WorkFlow
		customWorkFlowSet.createNewCustomWorkflow();
		
		//set auto accept value
		customWorkFlowSet.autoAcceptValue(setRolesWorkFlow.get(0));
		
		Roles role=new Roles();
		
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

		//Scroll up
		Settings.scrollUp();
   
		//Go to new invoice
	   CommonMethods.gotoRightSideAPLink("NEW INVOICE");
   
	   //Select vendor,netterm,location
	   icf.setVendorDetails(setRolesWorkFlow.get(8), setRolesWorkFlow.get(9), setRolesWorkFlow.get(10));
     
	   String invoiceid=CommonMethods.generateRandomStringForInvoiceID(setRolesWorkFlow.get(8));
	   icf.setInvoiceNo(invoiceid);
	   
	  String getInvoiceId= icf.getAttributeValueInvoiceNo();
		   
	   //Select product name,
	   pocf.setItemDetailsForSingleLine(setRolesWorkFlow.get(11),setRolesWorkFlow.get(12),setRolesWorkFlow.get(13),setRolesWorkFlow.get(14),setRolesWorkFlow.get(15),setRolesWorkFlow.get(16),setRolesWorkFlow.get(17),1);
       
	   //Enter message ,memo
	  role.invoiceCreationFormMessageMemo(setRolesWorkFlow.get(18), setRolesWorkFlow.get(19));
   
	  ica.searchInvoice(getInvoiceId);
   
   
   }




}