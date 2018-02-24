package com.netChain2.selenium.tests.accountsReceivable.apARConnection;

import java.util.ArrayList;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.netChain2.engine.BaseTestCase;
import com.netChain2.engine.Common;
import com.netChain2.selenium.pageObjects.accountsReceivable.createClients.POandSOConnectionForm;
import com.netChain2.selenium.pageObjects.accountsPayable.createInvoice.InvoiceCreationListActions;
import com.netChain2.selenium.pageObjects.accountsPayable.createPurchaseOrder.EditPurchaseOrder;
import com.netChain2.selenium.pageObjects.accountsPayable.createPurchaseOrder.PurchaseOrderCreationForm;
import com.netChain2.selenium.pageObjects.common.components.CommonMethods;
import com.netChain2.selenium.pageObjects.common.loginPage.LoginPage;
import com.netChain2.selenium.pageObjects.common.logout.LogoutFromPage;
import com.netChain2.utils.CustomAnnotation.TestDetails;

public class POtoSOConnection extends BaseTestCase {
	private ArrayList<String> testDatalg;
	private ArrayList<String> testDataARLogin;
	private ArrayList<String> testDataCreatePO;
	public String poNumber;
	public String invoiceno;
	public String payId;
	public String clientname;
	
	@BeforeClass
	public void setUp() {
		testDatalg = Common.getTestData("NetchainTest.Login");
		testDataCreatePO=Common.getTestData("AP.NetchainTest.CreatePO");
		testDataARLogin=Common.getTestData("AR.NetchainTest.Login");
	}
	
	 @Test
     @TestDetails(author="Shital.Patil", description="Create AP Po To AR SO Connection For Connected Company")
	 public void createPOForConnectedCompany()
	 {	 
			LoginPage loginPage = new LoginPage();
			loginPage.login(testDatalg.get(0), testDatalg.get(1));
			
			PurchaseOrderCreationForm purchaseOrder = new PurchaseOrderCreationForm();
			Reporter.log("AP Company(TechBite) Login");
			
			CommonMethods.gotoRightSideAPLink("NEW PURCHASE ORDER");
			
			POandSOConnectionForm soList=new POandSOConnectionForm();
			Common.selectFromDropdown("VENDOR_DROPDOWN_XPATH", "PO_VENDOR_ALL_DROPDOWN_VALUES_XPATH", testDataCreatePO.get(0));
			purchaseOrder.selectLocation(testDataCreatePO.get(1));
			String rate=testDataCreatePO.get(8);
		
			 //Get PO number
		     poNumber=soList.getAttributeValuePoNo();
		
			purchaseOrder.setItemDetails(1,testDataCreatePO.get(2),testDataCreatePO.get(3),testDataCreatePO.get(4),testDataCreatePO.get(5),testDataCreatePO.get(6),testDataCreatePO.get(7), rate);
		
			boolean isQuantityRoundedForFirstLine=purchaseOrder.verifyRoundingOfNumbers(PurchaseOrderCreationForm.getQualtity(1), PurchaseOrderCreationForm.getQty());
			assertTrue(isQuantityRoundedForFirstLine, "Quantity is not rounded in two decimal digits for first Line");
			boolean isRateRoundedForFirstLine=purchaseOrder.verifyRoundingOfNumbers(PurchaseOrderCreationForm.getRate(1), PurchaseOrderCreationForm.getRt());
			assertTrue(isRateRoundedForFirstLine, "Rate is not rounded in two decimal digits for first Line");
			
			purchaseOrder.setMessageToVendor(testDataCreatePO.get(9));
			purchaseOrder.setMemo(testDataCreatePO.get(10));
			purchaseOrder.setApprovalBy(testDataCreatePO.get(11));
			purchaseOrder.setShipBy(testDataCreatePO.get(12));
			
			purchaseOrder.saveAndSharePurchaseOrder();
			Common.sleep(4000);
			
			EditPurchaseOrder epo=new EditPurchaseOrder();
			epo.searchPurchaseOrder(Integer.toString(PurchaseOrderCreationForm.getPoNumber()));
			
			epo.verifyPurchaseOrderForConnectedVendor(testDataCreatePO.get(0), Integer.toString(PurchaseOrderCreationForm.getPoNumber()));
			Reporter.log("AP Create Purchase Order successfully ");
			Common.sleep(3000);
			
			LogoutFromPage.logout();
	  }
	 
	 @Test(dependsOnMethods= {"createPOForConnectedCompany"})
	 public void checkSoList()
	 {		
		 LoginPage loginPage = new LoginPage();
		 loginPage.login(testDataARLogin.get(2), testDataARLogin.get(3));
		 Reporter.log("AR Company(ConnectedCompany) Login");
			
		//click on AR Sales order list
		CommonMethods.gotoLeftARLink("Sales orders");
			
		//handle SO list opration click accept
		POandSOConnectionForm soList=new POandSOConnectionForm();
		clientname=testDataCreatePO.get(15);
		soList.verifySOList(clientname, poNumber);
		Reporter.log("Purchase Order reflected as Sales Order in AR company");
			
		//click + create Invoice
		soList.clickPlusbutton(clientname, poNumber);
		Common.sleep(2000);
			
		//get invoice id and add random string on it
		invoiceno=soList.getinvoiceNumber();
		Common.sleep(2000);
			
		//click on save and share
		soList.clickSaveAndShareBtn();	
			
		//Go to AR Invoice List And Verify That invoice is display on list
		String ActualTitleValue=Common.getDriver().getTitle();	
		String ExpectedTitleValue=testDataCreatePO.get(13);
			
		boolean istitleCorrect=soList.verifyTitleMatched(ActualTitleValue, ExpectedTitleValue);
		BaseTestCase.assertTrue(istitleCorrect, "Redirected to Invoice list,Invoice created succesfully");
		
		CommonMethods.searchByNumberOrName(invoiceno);
			
		Boolean status=soList.verifyInvoiceOnList(clientname);
		BaseTestCase.assertTrue(status, "Invoice not created");
		Reporter.log("AR Invoice created successfully",true);
			
		LogoutFromPage.logout();
	 }
	 
	 @Test(dependsOnMethods= {"checkSoList"})
	 public void invoiceAction()
	 {
		    LoginPage loginPage = new LoginPage();
			loginPage.login(testDatalg.get(0), testDatalg.get(1));
			Reporter.log("AP Company(TechBite) Login ");
			
			CommonMethods.gotoLeftAPLink("Invoices");
			
			String cName=testDataCreatePO.get(0);
			
		    CommonMethods.searchByNumberOrName(invoiceno);
		    
		    POandSOConnectionForm soList=new POandSOConnectionForm();
		    
			//Search Invoice by Company Name and click Accept icon 
			soList.verifyAPInvoiceOnListandClickAccept(cName, invoiceno);
			
			Reporter.log("AP-Invoice Generated successfully",true);	
			
			Common.getDriver().navigate().refresh();
			
			CommonMethods.searchByNumberOrName(invoiceno);
			Common.sleep(2000);
			
			//Click On Approve Invoice Link
			InvoiceCreationListActions actions=new InvoiceCreationListActions();
			soList.clickApproveInvoices(cName, invoiceno);
			
			//click on Yes Accept Only modal PopUp
			actions.ModalApproveButton();
			
			//Refresh the Page
			Common.getDriver().navigate().refresh();
			 
			//search invoice
		    actions.searchInvoice(invoiceno);
			Common.sleep(5000);
           			
			actions.clickOnCreatePaymentLink(testDataCreatePO.get(0),invoiceno);
	           Common.sleep(2000);
	           
	        //Click on payment banner 
	         actions.bannerClick();
	         Common.sleep(4000);
	         
	        //Get payment id
	        payId= actions.getPaymentId();
	        
	        //Click payment button
	        actions.createPaymentButton();
            Common.sleep(2000);
	             
           //Payment verification
            String ExpectedAlertMessage2="payment is created";
            
	        String ActualAlertMessage2=actions.gettextValue();			   
	   
	        boolean check3= ExpectedAlertMessage2.equals(ActualAlertMessage2);
	        BaseTestCase.assertTrue(check3, "Payment creation failed");
	        Reporter.log("AP Payment was created successfully");
	           
	        //Search invoice
	        actions.searchInvoice(payId);
	        Common.sleep(4000);
	            
	        //Click on approve button
	        actions.clickOnApprovePayment(cName,payId);
	        
	        //Click on payment button
            actions.sendPaymentButton();
            Common.sleep(3000);
	   
            //refresh page 
	        Common.getDriver().navigate().refresh();
	        Common.sleep(3000);
	           
	         //Payment search by id
	         actions.SearchPaymentId(payId);
	         Common.sleep(2000);
	         
	         //Verfication Action
	         boolean isActionProcessing= actions.verificationOnProcessingLink(cName,payId,testDataCreatePO.get(16));
	         BaseTestCase.assertTrue(isActionProcessing, " AP Payment action is not processing");
             Reporter.log("AP Payment action is processing for sent payment");		

			 LogoutFromPage.logout();
	 }
	 
	 @Test(dependsOnMethods= {"invoiceAction"})
	 public void arPaymentCheck() 
	 {
		     LoginPage loginPage = new LoginPage();
			 loginPage.login(testDataARLogin.get(2), testDataARLogin.get(3));
			
			//click on payment list
			CommonMethods.gotoLeftARLink("Payments");
			
			InvoiceCreationListActions actions=new InvoiceCreationListActions();
			POandSOConnectionForm soList=new POandSOConnectionForm();
			actions.SearchPaymentId(payId);
			
			//verify the status matched or not 
			String statusAR=testDataCreatePO.get(14);
			
			boolean st=soList.verifyPaymetStatusMatched(clientname, payId,statusAR);
			BaseTestCase.assertTrue(st, " AR-AP Payment status not matched");
	        Reporter.log(" AP-AR Payment status Matched for connected company");
	        
	        LogoutFromPage.logout();
	 }	 
}
