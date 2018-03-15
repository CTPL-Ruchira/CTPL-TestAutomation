package com.netChain2.selenium.tests.accountsReceivable.SalesOrder;

import java.util.ArrayList;

import org.openqa.selenium.WebDriver;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.netChain2.engine.BaseTestCase;
import com.netChain2.engine.Common;
import com.netChain2.selenium.pageObjects.accountsPayable.createInvoice.InvoiceCreationListActions;
import com.netChain2.selenium.pageObjects.accountsPayable.createPurchaseOrder.PurchaseOrderCreationForm;
import com.netChain2.selenium.pageObjects.accountsReceivable.createSalesOrder.SOCreationForm;
import com.netChain2.selenium.pageObjects.common.components.CommonMethods;
import com.netChain2.selenium.pageObjects.common.loginPage.LoginPage;
import com.netChain2.selenium.pageObjects.common.logout.LogoutFromPage;
import com.netChain2.utils.CustomAnnotation.TestDetails;

public class CreateSalesOrder extends BaseTestCase {
	private ArrayList<String> testData;
	private ArrayList<String> testData3;
	private ArrayList<String> testData4;
	private ArrayList<String> testData5;
	WebDriver driver=Common.getDriver();
	boolean check1;
	InvoiceCreationListActions listActions=new InvoiceCreationListActions();
	SOCreationForm SOCreation=new SOCreationForm();
	PurchaseOrderCreationForm pocf=new PurchaseOrderCreationForm();
	
	//to launch the browser and log-in
	@BeforeClass
	public void setUp() {
		testData = Common.getTestData("NetchainTest.Login");
		testData3=Common.getTestData("AR.NetchainTest.CreateSalesOrder");
		testData4=Common.getTestData("NetchainTest.CreateSalesOrder1");
		testData5=Common.getTestData("NetchainTest.CreateSalesOrder2");
		}
	
	@Test
	@TestDetails(author="Ruchira.Mhaisurkar",description="Sales Order Creation")
	public void salesOrderCreation() 
	{
		LoginPage loginPage = new LoginPage();
		loginPage.login(testData.get(0), testData.get(1));
		
		CommonMethods.gotoRightSideARLink("NEW SALES ORDER");
		
		SOCreation.selectClientFromDropdown(testData3.get(0));
		
		SOCreation.selectLocFromdropdown(testData3.get(1));
		Common.sleep(1000);
		
		//SOCreation.tickTheAddressCheckbox();
		String soId=Common.getAttribute("AR_SO_FORM_SONUMBER_XPATH");
			
		//First Line
		pocf.setItemDetailsWithoutMeasure(1,testData3.get(2), testData3.get(3), testData3.get(4), testData3.get(5), testData3.get(6), testData3.get(7));
		
		pocf.addMoreItem();
		
		boolean statusForQuality=pocf.verifyRoundingOfNumbers(PurchaseOrderCreationForm.getQualtity(1), PurchaseOrderCreationForm.getQty());
		BaseTestCase.assertTrue(statusForQuality, "Quantity is not rounded");
		
		boolean statusForRate=pocf.verifyRoundingOfNumbers(PurchaseOrderCreationForm.getRate(1), PurchaseOrderCreationForm.getRt());
		BaseTestCase.assertTrue(statusForRate, "Rate is not rounded");
		
		
		double calculatedAmountForFirstLine=PurchaseOrderCreationForm.getPreviousAmount();
		boolean isAmountRoundedForFirstLine=SOCreation.verifyTotalAmountCalculatedAndShown(calculatedAmountForFirstLine);
		assertTrue(isAmountRoundedForFirstLine, "Amount is not rounded in two decimal digits for first Line");
		
		//Second Line
		pocf.setItemDetailsWithoutMeasure(2,testData4.get(2), testData4.get(3), testData4.get(4), testData4.get(5), testData4.get(6), testData4.get(7));
		pocf.addMoreItem();
		boolean statusForQuality2=pocf.verifyRoundingOfNumbers(PurchaseOrderCreationForm.getQualtity(2), PurchaseOrderCreationForm.getQty());
		BaseTestCase.assertTrue(statusForQuality2, "Quantity is not rounded");
		
		boolean statusForRate2=pocf.verifyRoundingOfNumbers(PurchaseOrderCreationForm.getRate(2), PurchaseOrderCreationForm.getRt());
		BaseTestCase.assertTrue(statusForRate2, "Rate is not rounded");
		
		double calculatedAmountForSecondLine=PurchaseOrderCreationForm.getPreviousAmount();
		boolean isAmountRoundedForSecondLine=SOCreation.verifyTotalAmountCalculatedAndShown(calculatedAmountForSecondLine);
		assertTrue(isAmountRoundedForSecondLine, "Amount is not rounded in two decimal digits for second Line");
		
		//Third Line
		pocf.setItemDetailsWithoutMeasure(3,testData5.get(2), testData5.get(3), testData5.get(4), testData5.get(5), testData5.get(6), testData5.get(7));
		
		boolean statusForQuality3=pocf.verifyRoundingOfNumbers(PurchaseOrderCreationForm.getQualtity(3), PurchaseOrderCreationForm.getQty());
		BaseTestCase.assertTrue(statusForQuality3, "Quantity is not rounded");
		
		boolean statusForRate3=pocf.verifyRoundingOfNumbers(PurchaseOrderCreationForm.getRate(3), PurchaseOrderCreationForm.getRt());
		BaseTestCase.assertTrue(statusForRate3, "Rate is not rounded");
		
		double calculatedAmountForThirdLine=PurchaseOrderCreationForm.getPreviousAmount();
		boolean isAmountRoundedForThirdLine=SOCreation.verifyTotalAmountCalculatedAndShown(calculatedAmountForThirdLine);
		assertTrue(isAmountRoundedForThirdLine, "Amount is not rounded in two decimal digits for third Line");
	
		SOCreation.typeMessage(testData3.get(8));
		SOCreation.typeMemo(testData3.get(9));
		SOCreation.approvalBy(testData3.get(10));
		SOCreation.shipBy(testData3.get(11));
		
		SOCreation.clickSaveButton();
		Common.sleep(3000);
		
		//Search by So Id
		listActions.searchInvoice(soId);
		
		//Sort So list
		Common.click("SORTING_ARROW_XPATH");
		
		//SO search to check whether the SO created is present on the list
		Boolean status=SOCreation.verifySalesOrderOnList(soId);
		BaseTestCase.assertTrue(status, "Sales order created not present on list");
		Reporter.log("SO present on list and verified");
		Common.sleep(2000);
		
		//Logout from page
		LogoutFromPage.logout();	
		
	}
	
}
