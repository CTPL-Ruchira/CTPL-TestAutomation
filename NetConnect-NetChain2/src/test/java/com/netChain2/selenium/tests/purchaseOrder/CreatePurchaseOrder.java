package com.netChain2.selenium.tests.purchaseOrder;

import java.util.ArrayList;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.netChain2.engine.BaseTestCase;
import com.netChain2.engine.Common;
import com.netChain2.selenium.pageObjects.accountsPayable.createPurchaseOrder.EditPurchaseOrder;
import com.netChain2.selenium.pageObjects.accountsPayable.createPurchaseOrder.PurchaseOrderCreationForm;
import com.netChain2.selenium.pageObjects.common.apCreation.APModuleCreation;
import com.netChain2.selenium.pageObjects.common.landingPage.LandingPage;
import com.netChain2.selenium.pageObjects.common.loginPage.LoginPage;
import com.netChain2.selenium.pageObjects.common.logout.LogoutFromPage;
import com.netChain2.utils.CustomAnnotation.TestDetails;



public class CreatePurchaseOrder extends BaseTestCase{
	private ArrayList<String> testData;
	private ArrayList<String> invoiceData;
	@BeforeClass
	public void setUp() {
		System.out.println("Into Demo setup");
		testData = Common.getTestData("NetchainTest.Login");
		invoiceData = Common.getTestData("NetchainTest.CreateInvoice");
	}

	 @Test
     @TestDetails(author="Manoj.Kumar", description="Create Purchase Order")
	 
	public void createPurchaseOrder() {
		 
		
		LandingPage landingPage = new LandingPage();
				
		landingPage.clickLogInButton();
				
		LoginPage loginPage = new LoginPage();
		loginPage.login(testData.get(0), testData.get(1));
		Common.sleep(2000);
		
		PurchaseOrderCreationForm purchaseOrder = new PurchaseOrderCreationForm();
		APModuleCreation apModule = purchaseOrder.createNew();
		Common.sleep(2000);
		apModule.clickAPLink();
		Common.sleep(2000);
		apModule.clickPurchaseLink();
		Common.sleep(2000);
		
		Common.selectFromDropdown("VENDOR_DROPDOWN_XPATH", "PO_VENDOR_ALL_DROPDOWN_VALUES_XPATH", invoiceData.get(0));
		purchaseOrder.selectLocation(invoiceData.get(2));
		
		purchaseOrder.setItemDetails(invoiceData.get(6),invoiceData.get(7),invoiceData.get(8),invoiceData.get(9), invoiceData.get(11), invoiceData.get(12));
		purchaseOrder.addMoreItem();
		purchaseOrder.verifyCalculatedTotalAmmount(PurchaseOrderCreationForm.getQty(), PurchaseOrderCreationForm.getRt(), PurchaseOrderCreationForm.getAmount());
		purchaseOrder.setItemDetails(invoiceData.get(6),invoiceData.get(7),invoiceData.get(8),invoiceData.get(9), invoiceData.get(11), invoiceData.get(12));
		purchaseOrder.addMoreItem();
		purchaseOrder.verifyCalculatedTotalAmmount(PurchaseOrderCreationForm.getQty(), PurchaseOrderCreationForm.getRt(), PurchaseOrderCreationForm.getAmount());
		purchaseOrder.setItemDetails(invoiceData.get(6),invoiceData.get(7),invoiceData.get(8),invoiceData.get(9), invoiceData.get(11), invoiceData.get(12));
		purchaseOrder.verifyCalculatedTotalAmmount(PurchaseOrderCreationForm.getQty(), PurchaseOrderCreationForm.getRt(), PurchaseOrderCreationForm.getAmount());
				
		purchaseOrder.setMessageToVendor(invoiceData.get(13));
		purchaseOrder.setMemo(invoiceData.get(14));
		purchaseOrder.setApprovalBy(invoiceData.get(15));
		purchaseOrder.setShipBy(invoiceData.get(16));
		
		purchaseOrder.verifyCalculatedTotalAmmount(PurchaseOrderCreationForm.getQty(), PurchaseOrderCreationForm.getRt(), PurchaseOrderCreationForm.getAmount());
		purchaseOrder.savePurchaseOrder();
		
		Common.sleep(9000);
		
		LogoutFromPage.logout();
	}
	 
	@Test	 
	public void verifyPurchaseOrderCreated()
	{
		System.out.println("Into verifyPurchaseOrderCreated7");
		Common.sleep(3000);
	
		LoginPage loginPage = new LoginPage();
		loginPage.login(testData.get(0), testData.get(1));
		Common.sleep(2000);
		Common.click("NAVIGATION_MENU_PARTIAL_LINK");
		Common.sleep(2000);
		Common.click("NAVIGATION_MENU_PURCHASE_ORDER_XPATH");
		
		EditPurchaseOrder epo=new EditPurchaseOrder();
		epo.searchPurchaseOrder(Integer.toString(PurchaseOrderCreationForm.getPoNumber()));
		
		epo.verifyCreatedPurchaseOrder(invoiceData.get(0), Integer.toString(PurchaseOrderCreationForm.getPoNumber()));
		
	}
	
	
}