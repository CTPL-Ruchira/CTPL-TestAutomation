package com.netChain2.selenium.tests.purchaseOrder;

import java.util.ArrayList;

import org.testng.Reporter;
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
	private ArrayList<String> invoiceData,invoiceData2,invoiceData3;
	private ArrayList<String> poRuntimeData;
	@BeforeClass
	public void setUp() {
		System.out.println("Into Demo setup");
		testData = Common.getTestData("NetchainTest.Login");
		invoiceData = Common.getTestData("NetchainTest.CreateInvoice");
		invoiceData = Common.getTestData("NetchainTest.CreateInvoice");
		invoiceData2 = Common.getTestData("NetchainTest.CreateInvoice2");
		invoiceData3 = Common.getTestData("NetchainTest.CreateInvoice3");
		poRuntimeData = Common.getTestData("NetchainTest.PORuntimeProd");
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
		
		//Set items for First line
		String runtimeProductName=Common.generateRandomString(poRuntimeData.get(0));
		purchaseOrder.addProductRuntime(runtimeProductName,poRuntimeData.get(1),poRuntimeData.get(2),poRuntimeData.get(3),poRuntimeData.get(4),poRuntimeData.get(5),poRuntimeData.get(6),poRuntimeData.get(7),"value","value");
		String vendorNameFromModal=purchaseOrder.getVendorName();
		String vendorNameFromDropdown=purchaseOrder.getVendorNameDropdown();
		System.out.println("vendorNameFromModal"+vendorNameFromModal);
		System.out.println("vendorNameFromDropdown"+vendorNameFromDropdown);
		String productNameFromModal=purchaseOrder.getProductName();
		//String productNameFromDropdown=purchaseOrder.getProductNameDropdown();
		String productNameFromDropdown=purchaseOrder.getProductNamePresentInDropdown(productNameFromModal);
		System.out.println("productNameFromModal:"+productNameFromModal);
		System.out.println("productNameFromDropdown:"+productNameFromDropdown);
		
		boolean status=purchaseOrder.compareTwoValues(vendorNameFromModal, vendorNameFromDropdown);
		assertTrue(status, "Vendor name in modal does not match with the vendor name in dropdown ");
		Reporter.log("runtime product is added for the vendor");
		
		boolean status1=purchaseOrder.compareTwoValues(productNameFromModal, productNameFromDropdown);
		assertTrue(status1, "Product name entered in modal is not present in the dropdown");
		Reporter.log("Product added from the modal is seen in the dropdown");
		
		Common.sleep(2000);
		System.out.println("Vendor name---"+purchaseOrder.getVendorName());
		purchaseOrder.setItemDetails(runtimeProductName,invoiceData.get(7),invoiceData.get(8),invoiceData.get(9), invoiceData.get(11), invoiceData.get(12));
		purchaseOrder.getProductNamePresentInDropdown(productNameFromModal);
		purchaseOrder.addMoreItem();
		boolean isQuantityRoundedForFirstLine=purchaseOrder.verifyRoundingOfNumbers(PurchaseOrderCreationForm.getQualtity(), PurchaseOrderCreationForm.getQty());
		assertTrue(isQuantityRoundedForFirstLine, "Quantity is not rounded in two decimal digits for first Line");
		boolean isRateRoundedForFirstLine=purchaseOrder.verifyRoundingOfNumbers(PurchaseOrderCreationForm.getRate(), PurchaseOrderCreationForm.getRt());
		assertTrue(isRateRoundedForFirstLine, "Amount is not rounded in two decimal digits for first Line");
		
		boolean isAmountRoundedForFirstLine=purchaseOrder.verifyTotalAmountCalculatedAndShown(PurchaseOrderCreationForm.getPreviousAmount());
		assertTrue(isAmountRoundedForFirstLine, "Amount is not rounded in two decimal digits for first Line");
				
		//Set items for Second line
		purchaseOrder.setItemDetails(invoiceData2.get(6),invoiceData2.get(7),invoiceData2.get(8),invoiceData2.get(9), invoiceData2.get(11), invoiceData2.get(12));
		purchaseOrder.addMoreItem();
		boolean isQuantityRoundedForSecondLine=purchaseOrder.verifyRoundingOfNumbers(PurchaseOrderCreationForm.getQualtity(), PurchaseOrderCreationForm.getQty());
		assertTrue(isQuantityRoundedForSecondLine, "Quantity is not rounded in two decimal digits for second Line");
		boolean isRateRoundedForSecondLine=purchaseOrder.verifyRoundingOfNumbers(PurchaseOrderCreationForm.getRate(), PurchaseOrderCreationForm.getRt());
		assertTrue(isRateRoundedForSecondLine, "Rate is not rounded in two decimal digits for second Line");
		boolean isAmountRoundedForSecondLine=purchaseOrder.verifyTotalAmountCalculatedAndShown(PurchaseOrderCreationForm.getPreviousAmount());
		assertTrue(isAmountRoundedForSecondLine, "Amount is not rounded in two decimal digits for second Line");
		
		//Set items for Third line
		purchaseOrder.setItemDetails(invoiceData3.get(6),invoiceData3.get(7),invoiceData3.get(8),invoiceData3.get(9), invoiceData3.get(11), invoiceData3.get(12));
		boolean isQuantityRoundedForThirdLine=purchaseOrder.verifyRoundingOfNumbers(PurchaseOrderCreationForm.getQualtity(), PurchaseOrderCreationForm.getQty());
		assertTrue(isQuantityRoundedForThirdLine, "Quantity is not rounded in two decimal digits for third Line");
		boolean isRateRoundedForThirdLine=purchaseOrder.verifyRoundingOfNumbers(PurchaseOrderCreationForm.getRate(), PurchaseOrderCreationForm.getRt());
		assertTrue(isRateRoundedForThirdLine, "Rate is not rounded in two decimal digits for third Line");
		boolean isAmountRoundedForThirdLine=purchaseOrder.verifyTotalAmountCalculatedAndShown(PurchaseOrderCreationForm.getPreviousAmount());
		assertTrue(isAmountRoundedForThirdLine, "Amount is not rounded in two decimal digits for third Line");
		
		
		purchaseOrder.setMessageToVendor(invoiceData.get(13));
		purchaseOrder.setMemo(invoiceData.get(14));
		purchaseOrder.setApprovalBy(invoiceData.get(15));
		purchaseOrder.setShipBy(invoiceData.get(16));
				
		purchaseOrder.savePurchaseOrder();
		
		Common.sleep(9000);
		
		LogoutFromPage.logout();
	}
	 
	@Test(enabled=false)	 
	public void verifyPurchaseOrderCreated()
	{
		//System.out.println("Into verifyPurchaseOrderCreated7");
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