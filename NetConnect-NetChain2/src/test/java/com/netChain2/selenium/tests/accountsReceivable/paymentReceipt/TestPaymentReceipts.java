package com.netChain2.selenium.tests.accountsReceivable.paymentReceipt;

import java.util.ArrayList;

import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.netChain2.engine.BaseTestCase;
import com.netChain2.engine.Common;
import com.netChain2.selenium.pageObjects.accountsReceivable.createRefundFlow.RefundFlowCreationForm;
import com.netChain2.selenium.pageObjects.common.components.CommonMethods;
import com.netChain2.selenium.pageObjects.common.loginPage.LoginPage;
import com.netChain2.selenium.pageObjects.common.logout.LogoutFromPage;
import com.netChain2.utils.CustomAnnotation.TestDetails;

public class TestPaymentReceipts extends BaseTestCase{
	
	private ArrayList<String> testData;
	private ArrayList<String> testDataPaymentReceipt;

	RefundFlowCreationForm refund = new RefundFlowCreationForm();

	@BeforeClass
	public void setUp() {
		testData = Common.getTestData("NetchainTest.Login");
		testDataPaymentReceipt = Common.getTestData("AR.NetchainTest.PaymentReceipt");
	}

	@Test
	@TestDetails(author = "Shital.Patil", description = "This method will create a new Payment Receipt Account receivable > Payment Receipt")
	public void testCreatePaymentReceipt()
	{
		
		LoginPage loginPage = new LoginPage();
		loginPage.login(testData.get(6), testData.get(7));
		
		// Go To AR new Payment Receipt Link
		CommonMethods.gotoRightSideARLink("PAYMENT RECEIPTS");
		Common.sleep(2000);

		refund.setPaymentReceiptvalues(testDataPaymentReceipt.get(0), testDataPaymentReceipt.get(10), testDataPaymentReceipt.get(2),
				testDataPaymentReceipt.get(3), testDataPaymentReceipt.get(4), testDataPaymentReceipt.get(5),
				testDataPaymentReceipt.get(6));

		String prId = refund.getPaymentReceiptID();
		
		refund.saveButton();

		// verification on payment Receipt page
		// CommonMethods.searchByNumberOrName(prId);
		CommonMethods.scrollUp();
		
		Common.click("SORTING_ARROW_PAYMENTRECEIPT_XPATH");
		Common.sleep(1000);

		boolean status = refund.verifyPaymentReceiptOnList(prId);
		assertTrue(status, "payment receipt not created");
		Reporter.log("AR Payment Receipt created successfully", true);
		
		LogoutFromPage.logout();

	}

}
