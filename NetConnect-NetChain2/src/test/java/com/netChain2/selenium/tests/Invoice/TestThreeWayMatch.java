package com.netChain2.selenium.tests.Invoice;

import java.util.ArrayList;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.netChain2.engine.BaseTestCase;
import com.netChain2.engine.Common;
import com.netChain2.selenium.pageObjects.accountsPayable.createGoodsReceipt.GoodsReceiptForm;
import com.netChain2.selenium.pageObjects.accountsPayable.createInvoice.InvoiceCreationForm;
import com.netChain2.selenium.pageObjects.accountsPayable.createInvoice.ThreeWayMatch;
import com.netChain2.selenium.pageObjects.accountsPayable.createPurchaseOrder.EditPurchaseOrder;
import com.netChain2.selenium.pageObjects.accountsPayable.createPurchaseOrder.PurchaseOrderCreationForm;
import com.netChain2.selenium.pageObjects.common.components.CommonMethods;
import com.netChain2.selenium.pageObjects.common.loginPage.LoginPage;
import com.netChain2.selenium.pageObjects.common.logout.LogoutFromPage;

public class TestThreeWayMatch extends BaseTestCase
{
	private ArrayList<String> poTestData;
	private ArrayList<String> poQtyRateData;
	private ArrayList<String> goodsReceiptTestData;
	PurchaseOrderCreationForm purchaseOrderCreationForm;
	InvoiceCreationForm invoiceCreationForm;
	GoodsReceiptForm goodsReceiptForm;
	ThreeWayMatch threeWayMatch;
	
	@BeforeClass
	public void setUp() {
		poTestData 		= Common.getTestData("NetchainTest.PO");
		poQtyRateData 	= Common.getTestData("NetchainTest.POdataForThreewaymatch");
		goodsReceiptTestData = Common.getTestData("NetchainTest.GoodsReceipt");
		
	}
	
	/*@Test
	public void testThreewaymatch()
	{
		LoginPage loginPage = new LoginPage();
		loginPage.login("threewaymatch", "qwerty@123");
		
		CommonMethods.gotoRightSideAPLink("NEW PURCHASE ORDER");
		purchaseOrderCreationForm = new PurchaseOrderCreationForm();
		purchaseOrderCreationForm.createPurchaseOrder(poTestData.get(0), poTestData.get(1), poTestData.get(2), poTestData.get(3), poTestData.get(4), poTestData.get(5), poTestData.get(6), poTestData.get(7), poQtyRateData.get(0),  poQtyRateData.get(1), poTestData.get(10),poTestData.get(11), poTestData.get(12), poTestData.get(13), 1);
		String poNumber=String.valueOf(PurchaseOrderCreationForm.getPoNumber());
		
		CommonMethods.gotoRightSideAPLink("NEW GOODS RECEIPT");
		goodsReceiptForm=new GoodsReceiptForm();
		goodsReceiptForm.createGoodsReceipt(goodsReceiptTestData.get(0), goodsReceiptTestData.get(1), poNumber, goodsReceiptTestData.get(4), goodsReceiptTestData.get(5));
		
		CommonMethods.gotoRightSideAPLink("NEW INVOICE");
		invoiceCreationForm=new InvoiceCreationForm();
		invoiceCreationForm.createInvoiceForThreewaymatch(poTestData.get(0), "30", poTestData.get(1), poTestData.get(3), poNumber, "messageToVendor", "memo", "1");
		
		CommonMethods.scrollUp();
		String invoiceNumber=invoiceCreationForm.getInvoiceNumberAutopopulated();
		ThreeWayMatch threeWayMatch=new ThreeWayMatch();
		threeWayMatch.acceptInvoiceAndVerifyStatus(invoiceNumber, poTestData.get(0));
		
		LogoutFromPage.logout();
		
	
	
	@Test
	public void testThreewaymatch2()
	{
		LoginPage loginPage = new LoginPage();
		loginPage.login("threewaymatch", "qwerty@123");
		
		CommonMethods.gotoRightSideAPLink("NEW PURCHASE ORDER");
		purchaseOrderCreationForm = new PurchaseOrderCreationForm();
		purchaseOrderCreationForm.createPurchaseOrder(poTestData.get(0), poTestData.get(1), poTestData.get(2), poTestData.get(3), poTestData.get(4), poTestData.get(5), poTestData.get(6), poTestData.get(7), poQtyRateData.get(2),  poQtyRateData.get(3), poTestData.get(10),poTestData.get(11), poTestData.get(12), poTestData.get(13), 1);
		String poNumber=String.valueOf(PurchaseOrderCreationForm.getPoNumber());
		
		CommonMethods.gotoRightSideAPLink("NEW GOODS RECEIPT");
		goodsReceiptForm=new GoodsReceiptForm();
		goodsReceiptForm.createGoodsReceipt(goodsReceiptTestData.get(0), goodsReceiptTestData.get(1), poNumber, "100", goodsReceiptTestData.get(5));
	
		CommonMethods.gotoRightSideAPLink("NEW INVOICE");
		invoiceCreationForm=new InvoiceCreationForm();
		invoiceCreationForm.createInvoiceForThreewaymatch(poTestData.get(0), "30", poTestData.get(1), poTestData.get(3), poNumber, "messageToVendor", "memo", "1");
		
	}
	*/
	
	@Test
	public void testThreewaymatch3()
	{
		LoginPage loginPage = new LoginPage();
		loginPage.login("userone", "qwerty@123");
		
		CommonMethods.gotoRightSideAPLink("NEW PURCHASE ORDER");
		purchaseOrderCreationForm = new PurchaseOrderCreationForm();
		purchaseOrderCreationForm.createPurchaseOrder(poTestData.get(0), poTestData.get(1), poTestData.get(2), poTestData.get(3), poTestData.get(4), poTestData.get(5), poTestData.get(6), poTestData.get(7), "100",  "10", poTestData.get(10),poTestData.get(11), poTestData.get(12), poTestData.get(13), 1);
		String poNumber=String.valueOf(PurchaseOrderCreationForm.getPoNumber());
		
		EditPurchaseOrder epo=new EditPurchaseOrder();
		epo.verifyCreatedPurchaseOrder(poTestData.get(0), poNumber);
		
		CommonMethods.gotoRightSideAPLink("NEW GOODS RECEIPT");
		goodsReceiptForm=new GoodsReceiptForm();
		goodsReceiptForm.createGoodsReceipt("Techie", goodsReceiptTestData.get(1), poNumber, "100", goodsReceiptTestData.get(5));
		String grNumber=goodsReceiptForm.getGrNumber();
		threeWayMatch=new ThreeWayMatch();
		boolean isGrCreated=threeWayMatch.verifyGoodReceiptOnList(grNumber, "Techie");
		
		assertTrue(isGrCreated, "GR is not showing on list");
		
		CommonMethods.gotoRightSideAPLink("NEW INVOICE");
		invoiceCreationForm=new InvoiceCreationForm();
		invoiceCreationForm.createInvoiceForThreewaymatch(poTestData.get(0), "30", poTestData.get(1), poTestData.get(3), poNumber, "messageToVendor", "memo", "1");
		
	}
}

