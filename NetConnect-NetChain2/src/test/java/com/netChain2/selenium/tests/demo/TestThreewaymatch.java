package com.netChain2.selenium.tests.demo;

import java.util.ArrayList;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.netChain2.engine.Common;
import com.netChain2.selenium.pageObjects.accountsPayable.createGoodsReceipt.GoodsReceiptForm;
import com.netChain2.selenium.pageObjects.accountsPayable.createInvoice.InvoiceCreationForm;
import com.netChain2.selenium.pageObjects.accountsPayable.createInvoice.ThreeWayMatch;
import com.netChain2.selenium.pageObjects.accountsPayable.createPurchaseOrder.PurchaseOrderCreationForm;
import com.netChain2.selenium.pageObjects.common.components.CommonMethods;
import com.netChain2.selenium.pageObjects.common.loginPage.LoginPage;
import com.netChain2.selenium.pageObjects.common.logout.LogoutFromPage;
import com.netChain2.utils.CustomAnnotation.TestDetails;

public class TestThreewaymatch 
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
	
	@TestDetails(author="Manoj Kumar", description="To test if 3 way match does not apply when we create a PO, GR and Invoice for a “PO required” product .")
	@Test(enabled=false)
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
	}
	
	@Test
	public void testThreewaymatch2()
	{
		LoginPage loginPage = new LoginPage();
		loginPage.login("threewaymatch", "qwerty@123");
		
		CommonMethods.gotoRightSideAPLink("NEW PURCHASE ORDER");
	}
	
	
}
