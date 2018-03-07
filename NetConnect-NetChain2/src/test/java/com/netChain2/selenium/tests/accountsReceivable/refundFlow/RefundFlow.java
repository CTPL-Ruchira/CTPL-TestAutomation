package com.netChain2.selenium.tests.accountsReceivable.refundFlow;

import java.util.ArrayList;

import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.netChain2.engine.BaseTestCase;
import com.netChain2.engine.Common;
import com.netChain2.selenium.pageObjects.accountsPayable.CreateNewCreditMemo.CreditMemoCreationForm;
import com.netChain2.selenium.pageObjects.accountsPayable.createInvoice.InvoiceCreationForm;
import com.netChain2.selenium.pageObjects.accountsPayable.createInvoice.InvoiceCreationListActions;
import com.netChain2.selenium.pageObjects.accountsPayable.createPurchaseOrder.PurchaseOrderCreationForm;
import com.netChain2.selenium.pageObjects.accountsReceivable.createClients.ClientsCreationForm;
import com.netChain2.selenium.pageObjects.accountsReceivable.createRefundFlow.RefundFlowCreationForm;
import com.netChain2.selenium.pageObjects.common.components.CommonMethods;
import com.netChain2.selenium.pageObjects.common.loginPage.LoginPage;
import com.netChain2.selenium.pageObjects.common.logout.LogoutFromPage;
import com.netChain2.utils.CustomAnnotation.TestDetails;

public class RefundFlow {
	private ArrayList<String> testData;
	private ArrayList<String> testDataClients;
	private ArrayList<String> testDataInvoice;
	private ArrayList<String> testDataPaymentReceipt;
	private ArrayList<String>testDataCreatePO;
	public String clientName;
	InvoiceCreationForm ic = new InvoiceCreationForm();
	ClientsCreationForm arClients = new ClientsCreationForm();
	InvoiceCreationListActions action = new InvoiceCreationListActions();
	PurchaseOrderCreationForm po = new PurchaseOrderCreationForm();
	RefundFlowCreationForm refund = new RefundFlowCreationForm();
	CreditMemoCreationForm creditmemo = new CreditMemoCreationForm();

	@BeforeClass
	public void setUp() {
		testData = Common.getTestData("NetchainTest.Login");
		testDataClients = Common.getTestData("AR.NetchainTest.CreateClients");
		testDataInvoice = Common.getTestData("AR.NetchainTest.CreateInvoice");
		testDataPaymentReceipt = Common.getTestData("AR.NetchainTest.PaymentReceipt");
		testDataCreatePO=Common.getTestData("AP.NetchainTest.CreatePO");
	}

	@Test
	@TestDetails(author = "Shital.Patil", description = "This method will create a new client from Account receivable > New client")
	public void createclient() {

		LoginPage loginPage = new LoginPage();
		loginPage.login(testData.get(6), testData.get(7));

		// Go To AR new Client Link
		CommonMethods.gotoRightSideARLink("NEW CLIENT");

		// create Non Connected Client
		clientName = Common.generateRandomString(testDataClients.get(17));

		arClients.createClientMethod(testDataClients.get(0), testDataClients.get(1), testDataClients.get(2),
				testDataClients.get(3), testDataClients.get(4), testDataClients.get(5), testDataClients.get(6),
				testDataClients.get(7), testDataClients.get(8), testDataClients.get(9), testDataClients.get(10),
				testDataClients.get(11), testDataClients.get(12), testDataClients.get(13), testDataClients.get(14),
				testDataClients.get(15), testDataClients.get(16), clientName, testDataClients.get(18),
				testDataClients.get(19), testDataClients.get(20), testDataClients.get(21), testDataClients.get(22),
				testDataClients.get(23), testDataClients.get(24), testDataClients.get(25), testDataClients.get(26),
				testDataClients.get(27), testDataClients.get(28), testDataClients.get(29), testDataClients.get(30),
				testDataClients.get(31), testDataClients.get(32), testDataClients.get(33));
 
		// search client and verify client in list
		action.searchInvoice(clientName);

		Boolean status = arClients.verifyClientOnList(clientName);
		BaseTestCase.assertTrue(status, "Client not created");
		Reporter.log("Client created successfully", true);
		Reporter.log("Client present on list and verified");
	}

	@Test(dependsOnMethods = { "createclient" })
	public void createARInvoice() 
	{
		// create AR invoice of that client and Go To AR new invoice Link
		CommonMethods.gotoRightSideARLink("NEW INVOICE");
		
		Common.sleep(2000);
        String invoiceno = ic.getAttributeValueInvoiceNo();
	
		ic.createInvoice(clientName, testDataInvoice.get(0), testDataInvoice.get(1));
		ic.clickSameAsMailingAdd();

		po.setItemDetailsWithoutMeasure(1, testDataInvoice.get(5), testDataInvoice.get(6), testDataInvoice.get(7),
				testDataInvoice.get(8), testDataInvoice.get(9), testDataInvoice.get(10));

		ic.Invoice_SaveButton();
		Common.sleep(2000);

		// invoice verification
		CommonMethods.scrollUp();
		CommonMethods.scrollUp();
		Common.click("SORTING_ARROW_XPATH");
		//CommonMethods.scrollDown();
		Common.sleep(2000);
        Boolean status = creditmemo.verifyInvoiceOnList(invoiceno,clientName);
		BaseTestCase.assertTrue(status, "Invoice not created");
		Reporter.log("AR Invoice created successfully", true);

	}

	@Test(dependsOnMethods = { "createARInvoice" })
	public void createARPaymentReceipt()
	{
		CommonMethods.scrollUp();
		
		// Go To AR new Payment Receipt Link
		CommonMethods.gotoRightSideARLink("PAYMENT RECEIPTS");
		Common.sleep(2000);

		refund.setPaymentReceiptvalues(testDataPaymentReceipt.get(0), clientName, testDataPaymentReceipt.get(2),
				testDataPaymentReceipt.get(3), testDataPaymentReceipt.get(4), testDataPaymentReceipt.get(5),
				testDataPaymentReceipt.get(6));

		String prId = refund.getPaymentReceiptID();
		Common.sleep(2000);

		// click on accordian
		refund.clickAccordian(1);
		refund.setAmountPaid(testDataPaymentReceipt.get(7));

		refund.clickOnCheckbox();
		
		refund.saveButton();

		// verification on payment Receipt page
		// CommonMethods.searchByNumberOrName(prId);
		CommonMethods.scrollUp();
		Common.click("SORTING_ARROW_XPATH");
		Common.sleep(2000);

		Boolean status = refund.verifyPaymentReceiptOnList(prId);
		BaseTestCase.assertTrue(status, "payment receipt not created");
		Reporter.log("AR Payment Receipt created successfully", true);

	}

	@Test(dependsOnMethods = { "createARPaymentReceipt" })
	public void createARNewRefund() 
	{
		// Go to AR new refund
		CommonMethods.gotoRightSideARLink("NEW REFUND");
		Common.sleep(2000);
		// get refund Id
		String refundId = refund.getRefundID();
		refund.setRefund(clientName, testDataPaymentReceipt.get(9));

		CommonMethods.scrollUp();
		Common.click("SORTING_ARROW_XPATH");
		Common.sleep(2000);

		Boolean status = arClients.verifyClientOnList(clientName);
		BaseTestCase.assertTrue(status, "Refund not created");
		Reporter.log("AR Payment Receipt created successfully and Verified", true);
		
		action.searchInvoice(clientName);
		Common.sleep(2000);
		
		//click on Approve Payment
		action.clickOnApprovePayment(clientName, refundId);
		
		//Click on send Payment
		action.sendPaymentButton();
		
		Common.getDriver().navigate().refresh();
		
		String paymentStatus=testDataCreatePO.get(16);
		action.searchInvoice(clientName);
		
		//check status is processing or not 
		Boolean status1 = refund.verifyStatusOnPaymentPage(clientName,refundId,paymentStatus);
		BaseTestCase.assertTrue(status1, "Refund payment status not matched");
		
		LogoutFromPage.logout();

	}

}
