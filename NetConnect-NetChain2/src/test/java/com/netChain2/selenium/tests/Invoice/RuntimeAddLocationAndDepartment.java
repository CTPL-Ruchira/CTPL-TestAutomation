package com.netChain2.selenium.tests.Invoice;

import java.util.ArrayList;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.netChain2.engine.BaseTestCase;
import com.netChain2.engine.Common;
import com.netChain2.selenium.pageObjects.accountsPayable.createInvoice.InvoiceCreationForm;
import com.netChain2.selenium.pageObjects.accountsPayable.createInvoice.RuntimeAddLocationAndDepartmentForm;
import com.netChain2.selenium.pageObjects.accountsPayable.createPurchaseOrder.PurchaseOrderCreationForm;
import com.netChain2.selenium.pageObjects.common.apCreation.APModuleCreation;
import com.netChain2.selenium.pageObjects.common.components.CommonMethods;
import com.netChain2.selenium.pageObjects.common.loginPage.LoginPage;
import com.netChain2.selenium.pageObjects.common.logout.LogoutFromPage;
import com.netChain2.utils.CustomAnnotation.TestDetails;

public class RuntimeAddLocationAndDepartment extends BaseTestCase{
	private ArrayList<String> testData;
	private ArrayList<String> testDataInvlocationDept;
	private ArrayList<String> InvoiceTestData;
	@BeforeClass
	public void setUp() {
		testData = Common.getTestData("NetchainTest.Login");
		InvoiceTestData = Common.getTestData("NetchainTest.CreateInvoice");
		testDataInvlocationDept=Common.getTestData("AP.NetchainTest.CreateInvoice.Runtime");
		}

	 @Test
     @TestDetails(author="Shital.Patil", description="Adding runtime Location And department ")
	 
	
	  public void testAddlocationModal() {
		
		LoginPage loginPage = new LoginPage();
		loginPage.login(testData.get(0), testData.get(1));
		
		//click to create new
		InvoiceCreationForm invoice = new InvoiceCreationForm();

		APModuleCreation apModule = invoice.createNew();
		
		//click to AP()
		apModule.clickAPLink();
		
		//Click to New Invoice
		apModule.clickNewInvoice();
		
		//select Location dropdown
		PurchaseOrderCreationForm pocf=new PurchaseOrderCreationForm();
		InvoiceCreationForm invoiceCreationForm = new InvoiceCreationForm();
		invoiceCreationForm.SelectVendor(InvoiceTestData.get(0));
	 
        invoiceCreationForm.SelectNetTerm(InvoiceTestData.get(1));
		
		RuntimeAddLocationAndDepartmentForm runtimeadd=new RuntimeAddLocationAndDepartmentForm();
		
		//Adding location runtime
		runtimeadd.selectLocationRuntime(testDataInvlocationDept.get(0));
		Common.sleep(2000);
		String location=Common.generateRandomString(testDataInvlocationDept.get(1));
		runtimeadd.manageMailingAddress(location, testDataInvlocationDept.get(2), testDataInvlocationDept.get(3), testDataInvlocationDept.get(4), testDataInvlocationDept.get(5));
		String dept=Common.generateRandomString(testDataInvlocationDept.get(10));
		runtimeadd.manageShippingAddress(testDataInvlocationDept.get(6), testDataInvlocationDept.get(7), testDataInvlocationDept.get(8), testDataInvlocationDept.get(9), dept);
		
		runtimeadd.clickSave();
		Common.sleep(2000);
		runtimeadd.selectLocationRuntime(location);
		Boolean status=runtimeadd.ckecklocationAddedSuccessfully(location);
		BaseTestCase.assertTrue(status, "Location not created");
		Reporter.log("Location Added Successfully",true);
		Common.sleep(5000);
		
		
		CommonMethods.scrollDown();
		Common.sleep(2000);

		//Adding department runtime for the location
		runtimeadd.addRuntimeDepartmentFromDepartmentDropdown(testDataInvlocationDept.get(12));
		pocf.setItemDetails(InvoiceTestData.get(6), runtimeadd.getRuntimeDepartmentName(), InvoiceTestData.get(8), InvoiceTestData.get(9), InvoiceTestData.get(10), InvoiceTestData.get(11), InvoiceTestData.get(12));
		
		boolean isDepartmentAddedInRuntime=runtimeadd.verifyDepartmentAddedAfterAddingRuntime(runtimeadd.getRuntimeDepartmentName());
		assertTrue(isDepartmentAddedInRuntime, "Department is not added in runtime in dropdown");
		Reporter.log("Runtime department added successfully",true);
		Common.sleep(1000);
		
		pocf.setMessageToVendor(InvoiceTestData.get(13));
		pocf.setMemo(InvoiceTestData.get(14));
		pocf.savePurchaseOrder();
		Common.sleep(5000);
		invoice.CreateRule_CancelButton();
		Common.sleep(2000);
		LogoutFromPage.logout();
		
		
		

   }
}