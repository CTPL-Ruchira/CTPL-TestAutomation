package com.netChain2.selenium.pageObjects.accountsReceivable.createClients;

import org.openqa.selenium.Keys;
import org.testng.Reporter;

import com.netChain2.engine.Common;
import com.netChain2.selenium.pageObjects.accountsPayable.createPurchaseOrder.PurchaseOrderCreationForm;
import com.netChain2.selenium.pageObjects.common.arCreation.ARModuleCreation;
import com.netChain2.selenium.pageObjects.common.components.CommonMethods;

public class ClientsCreationForm {
	private static int count=1;
	private static int flag=1;
	
	public ARModuleCreation createNew() {
		CommonMethods.scrollUp();
		Common.click("AR_CREATE_NEW_PLUSE_BUTTON_XPATH");
		return new ARModuleCreation();
	}
	
	//Set Company Profile Our Company tab
	public void setCompanyProfile(String ourCmp, String ourMission,String productService){
    	Common.sendKeys("AR_COMP_PROFILE_OUR_COMPANY_XPATH", ourCmp);
    	Common.sendKeys("AR_COMP_PROFILE_OUR_MISSION_XPATH", ourMission);
    	Common.sendKeys("AR_COMP_PROFILE_PRODUCT_SERVICES_XPATH", productService);	
    }
	
	//click Company Profile Next Button
	public void clickCompanyProfileNextButton() {
    	Common.click("AR_COMP_PROFILE_NEXT_BUTTON_XPATH");
    }
	
	public void clickAddEmpButton() {
    	Common.click("AR_ASSOCIATE_EMP_ADD_EMPLOYEE_XPATH");
    }
	
	public void EnterName(String entername) {
		Common.sleep(2000);
		String enterNameTextLoc="//table[@class='table table-bordered']//tr["+count+"]/td[1]";
		Common.findElement(enterNameTextLoc);
		Common.click("ASSOCIATED_EMPLOYEE_NAME_FIELD_XPATH");
		Common.sendKeys("ASSOCIATED_EMPLOYEE_ENTER_NAMEE_XPATH", entername);
	}

	//Enter Email
	public void EnterEmail(String value5) {
		Common.sleep(2000);
		String enterEmailTextLoc="//table[@class='table table-bordered']//tr["+count+"]/td[2]";
		Common.findElement(enterEmailTextLoc);
		Common.click("ASSOCIATED_EMPLOYEE_ENTER_EMAIL_XPATH");
		Common.sendKeys("ASSOCIATED_EMPLOYEE_ENTER_NAMEE_XPATH", value5);
	}
	
	public void EnterPhoneNumber(String value5) {
		Common.sleep(1000);
		String enterPhoneNoLoc="//table[@class='table table-bordered']//tr["+count+"]/td[3]";
		Common.findElement(enterPhoneNoLoc).sendKeys(value5);
		Common.click("ASSOCIATED_EMPLOYEE_ENTER_PHONE_XPATH");
		Common.sleep(1000);
		Common.sendKeys("ASSOCIATED_EMPLOYEE_ENTER_NAMEE_XPATH", value5);
	}
	
	public void EnterTitle(String value5) {
		Common.sleep(1000);
		String enterTitleLoc="//table[@class='table table-bordered']//tr["+count+"]/td[4]";
		Common.findElement(enterTitleLoc).sendKeys(value5);
		Common.click("ASSOCIATED_EMPLOYEE_ENTER_TITLE_XPATH");
		Common.sleep(1000);
		Common.sendKeys("ASSOCIATED_EMPLOYEE_ENTER_NAMEE_XPATH", value5);
	}
	
	public void SetAssociateEmp(String eNname,String email,String phoneNo,String title)
	{
		Common.sleep(2000);
		EnterName(eNname);
		Common.sleep(2000);
		EnterEmail(email);
		EnterPhoneNumber(phoneNo);
		EnterTitle(title);
		count=count+1;
	}
	
	public void selectPaymentReceiver() {
		Common.sleep(1000);
		Common.click("AR_ASSOCIATE_EMP_CLICK_PAYMENT_RECEIVABLE_XPATH");
	}
	
	//click Associate Employee Next Button
	public void clickAssociateEmpNextButton() {
	    	Common.click("AR_ASSOCIATE_EMP_CLICK_NEXT_XPATH");
    }
	
	//click Booking Account Add new Line 
	public void clickBookingAccntAddLineButton() {
		Common.click("BOOKING_ACCOUNT_ADD_LINE_BUTTON_XPATH");
	}
	
	//Select Location On Booking Account
	public void SelectLocation(String value) {
		String selectLocation="//table[@class='table table-bordered']//tr["+flag+"]//td[1]";
		String xpath="//table[@class='table table-bordered']//tr["+flag+"]//td[1]/center/select/option[text()='"+value+"']";
		new PurchaseOrderCreationForm().selectDropdownValues(selectLocation, xpath);
	}

	//select Department on booking Account
	public void SelectDepartment(String value) {
		String selectDept="//table[@class='table table-bordered']//tr["+flag+"]//td[2]";
		String xpath="//table[@class='table table-bordered']//tr["+flag+"]//td[2]/center/select/option[text()='"+value+"']";
		new PurchaseOrderCreationForm().selectDropdownValues(selectDept, xpath);
	}
	
	//select Booking Account 
	public void SelectBookingAcc(String value) {
		String selectBookingAcc="//table[@class='table table-bordered']//tr["+flag+"]//td[3]";
		String xpath="//table[@class='table table-bordered']//tr["+flag+"]//td[3]/center/select/option[text()='"+value+"']";
		new PurchaseOrderCreationForm().selectDropdownValues(selectBookingAcc, xpath);
	}
	
	public void SetBookingAccount(String location,String dept,String bookingAcc)
	{
		Common.sleep(2000);
		SelectLocation(location);
		SelectDepartment(dept);
		SelectBookingAcc(bookingAcc);
		flag=flag+1;
		Common.sleep(2000);	
	}
	
	//click Next button 
	public void clickBookingAccountNextButton() {
		Common.click("BOOKING_ACCOUNT_TAB_NEXT_BUTTON_XPATH");
	}
	
	//GOTO Transaction Details Tab And Select terms
	public void transactionDetails(String terms,String taxNo,String paymentmethod) {
		Common.selectFromDropdown("AR_TRANSACTION_DETAIL_SELECT_TERMS_XPATH","AR_TRANSACTION_DETAIL_SELECT_TERMS_OPTION_XPATH", terms);
		Common.sendKeys("AR_TRANSACTION_DETAIL_ENTER_TAX_REG_NO_XPATH", taxNo);
		Common.selectFromDropdown("AR_TRANSACTION_DETAIL_SELECT_PAYMENT_METHOD_XPATH","AR_TRANSACTION_DETAIL_SELECT_PAYMENT_METHOD_OPTION_XPATH", paymentmethod);
	}
	
	//click TRANSACTION DETAIL Next button 
	public void clickTransDetailNextButton() {
		Common.click("AR_TRANSACTION_DETAIL_CLICK_NEXT_BUTTON_XPATH");
	}
	
	//Client Details
	public void setClientIdentity(String title, String fname,String lname,String suffix,String CompanyName,String Dname,String BType,String other){
    	Common.sendKeys("AR_CLIENT_DETAILS_TITLE_XPATH", title);
    	Common.sendKeys("AR_CLIENT_DETAILS_FIRST_NAME_XPATH", fname);
    	Common.sendKeys("AR_CLIENT_DETAILS_LAST_NAME_XPATH", fname);	
     	Common.sendKeys("AR_CLIENT_DETAILS_SUFFIX_XPATH", suffix);
     	Common.sendKeys("AR_CLIENT_DETAILS_COMPANY_NAME_XPATH", CompanyName+Keys.TAB);
    	Common.sendKeys("AR_CLIENT_DETAILS_DISPLAY_NAME_XPATH", Dname);	
     	Common.sendKeys("AR_CLIENT_DETAILS_BUSINESS_NAME_XPATH", BType);
    	Common.sendKeys("AR_CLIENT_DETAILS_OTHER_FEILD_XPATH", other);	
    }
	
	//Click On print on Check
	public void clickPrintOnCheck() {
		Common.click("AR_CLIENT_DETAILS_PRINT_ON_CHECK_CHECK_XPATH");
	}
	
	//Billing Address
	public void setBillingAddress(String street, String city,String state,String zip){
    	Common.sendKeys("AR_CLIENT_DETAILS_BILLING_ADDRESS_STREET_ADD_XPATH", street);
    	Common.sendKeys("AR_CLIENT_DETAILS_BILLING_ADDRESS_CITY_XPATH", city);
    	Common.sendKeys("AR_CLIENT_DETAILS_BILLING_ADDRESS_STATE_XPATH", state);
    	Common.sendKeys("AR_CLIENT_DETAILS_BILLING_ADDRESS_ZIP_XPATH", zip);	
    }
	
	//click same as billing address
	public void clickSameAsBillingAddress() {
		Common.click("AR_CLIENT_DETAILS_SAME_AS_BILLINGADD_XPATH");
	}
	
	//Contact Details
	public void setContactDetails(String email, String phone,String mobile,String fax, String website,String accNo)
	{
    	Common.sendKeys("AR_CLIENT_DETAILS_CONTACT_EMAIL_XPATH", email+Keys.TAB);
    	Common.sendKeys("AR_CLIENT_DETAILS_CONTACT_PHONE_XPATH", phone);
    	Common.sendKeys("AR_CLIENT_DETAILS_CONTACT_MOBILE_XPATH", mobile);	
     	Common.sendKeys("AR_CLIENT_DETAILS_CONTACT_FAX_XPATH", fax);
    	Common.sendKeys("AR_CLIENT_DETAILS_CONTACT_WEBSITE_XPATH", website);
    	Common.sendKeys("AR_CLIENT_DETAILS_CONTACT_ACCOUNT_NO_XPATH", accNo);		
    }
	
	//Select Company Tire
	public void SelectCompanyTire(String companyTire) {
		Common.selectFromDropdown("AR_CLIENT_DETAILS_SELECT_COMPANY_TIRE_XPATH","AR_CLIENT_DETAILS_SELECT_COMPANY_TIRE_OPTION_XPATH", companyTire);
	}
	
	//Invoice Delivery Method
	public void clickInvoiceDeliveryMethod() {
		Common.click("AR_CLIENT_DETAILS_INVOICE_DELIVERY_METHOD_XPATH");
	}
	
	//Set Email on Invoice Delivery Method 
	public void setEmailOnInvoiceDelivery(String value) {
		Common.sendKeys("AR_CLIENT_DETAILS_EMAIL_INVOICE_DELIVERY_XPATH", value);
	}
	
	public void setNotes(String value) {
		Common.sendKeys("AR_CLIENT_DETAILS_NOTES_XPATH", value);
	}
	
	//click client Save Button
	public void clickSaveButton() {
		Common.click("AR_CLIENT_DETAILS_SAVE_BUTTON_XPATH");
	}
	
	//Create non connected Client in One Method
	public void createClientMethod(String ourCompany,String OurMission,String ProductService,String name,String email,String phone,String title,String location,String dept,String bookingAcc,String terms,String regNo,String paymentMethod,String ctitle,String fname,String lname,String suffix,String cName,String dName,String bussinesstype,String other,String streetAdd,String city,String state,String zip,String cemail,String cphone,String mobile,String fax,String website,String accNo,String cTire,String E_mail,String notes) 
	{
		setCompanyProfile(ourCompany,OurMission,ProductService);
		clickCompanyProfileNextButton();
		clickAddEmpButton();
		EnterName(name);
		EnterEmail(email);
		EnterPhoneNumber(phone);
		EnterTitle(title);
		selectPaymentReceiver();
		clickAssociateEmpNextButton();
		SetBookingAccount(location,dept,bookingAcc);
		clickBookingAccountNextButton();
		transactionDetails(terms,regNo,paymentMethod);
		clickTransDetailNextButton();
		setClientIdentity(ctitle,fname,lname,suffix,cName,dName,bussinesstype,other);
		clickPrintOnCheck();
		setBillingAddress(streetAdd,city,state,zip);
		clickSameAsBillingAddress();
		setContactDetails(cemail,cphone,mobile,fax,website,accNo);
		SelectCompanyTire(cTire);
		clickInvoiceDeliveryMethod();
		setEmailOnInvoiceDelivery(E_mail);
		setNotes(notes);
		clickSaveButton();
	}
	//String SetBookingAccount,String transactionDetails,String setClientIdentity,String setBillingAddress,String setContactDetails ,String SelectCompanyTire,String setEmailOnInvoiceDelivery) {
	
	
	//Verify Client List 	
	public Boolean verifyClientOnList(String expectedClientName) 
	 {
	  boolean flag=false;
	  Common.sleep(6000);
	  System.out.println("expectedClientName"+expectedClientName);
	  CommonMethods.searchByNumberOrName(expectedClientName);
	  Common.sleep(2000);
	  
	  String actualClientNameOnList=Common.getText("AR_CLIENT_LIST_XPATH");
	  if(actualClientNameOnList.equals(expectedClientName))
	  {
	   flag=true;
	   System.out.println("Client displayed on list");
	   Reporter.log("Client present on list and verified");
	   Reporter.log("Client added is present on Netchain Platform and The Connection is done successfully");
	  }
	  else
	  {
	   System.out.println("Client not created");
	  }

	  return flag;

	 }

	public boolean verifyTitleMatched(String actualTitleValue, String expectedTitleValue) {
		if(actualTitleValue.equals(expectedTitleValue)) {
			return true;
		}
		else {
			return false;
		}
		
		
		
	}	 
}
