package com.netChain2.selenium.pageObjects.accountsPayable.createVendor;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

import com.netChain2.engine.Common;
import com.netChain2.selenium.pageObjects.common.arCreation.ARModuleCreation;
import com.netChain2.selenium.pageObjects.common.components.CommonMethods;



/**
 * API's for Vendor Creation Form
 * @author Ruchira.Mhaisurkar
 * 
 *
 */
public class VendorCreationForm {

	private String companyName;
	private String displayName;

	WebDriver driver=Common.getDriver();
	public ARModuleCreation createNew() {
		Common.click("AR_CREATE_NEW_PLUSE_BUTTON_XPATH");
		return new ARModuleCreation();
	}

	//Company profile tab-->To enter first field
	public void setOurCompany(String value) {
		Common.sendKeys("NEW_VENDOR_COMPANY_PROFILE1_XPATH", value);
	}

	//Company profile tab-->To enter second field
	public void setOurMission(String value) {
		Common.sendKeys("NEW_VENDOR_COMPANY_PROFILE2_XPATH", value);
	}

	//Company profile tab-->To enter third field
	public void setProductsAndServices(String value){
		Common.sendKeys("NEW_VENDOR_COMPANY_PROFILE3_XPATH", value);
	}


	//Associated employee tab-->To enter name 
	public void EnterName(String value4) {
		Common.sleep(1000);
		Common.click("ASSOCIATED_EMPLOYEE_NAME_FIELD_XPATH");
		Common.sleep(2000);
		Common.sendKeys("ASSOCIATED_EMPLOYEE_ENTER_NAMEE_XPATH", value4);

	}

	//Associated employee tab-->To enter Email
	public void EnterEmail(String value5) {
		Common.sleep(1000);
		Common.click("ASSOCIATED_EMPLOYEE_ENTER_EMAIL_XPATH");
		Common.sleep(1000);
		Common.sendKeys("ASSOCIATED_EMPLOYEE_ENTER_NAMEE_XPATH", value5);
	}

	//Associated employee tab-->To enter Phone
	public void EnterPhoneNumber(String value5) {
		Common.sleep(1000);
		Common.click("ASSOCIATED_EMPLOYEE_ENTER_PHONE_XPATH");
		Common.sleep(1000);
		Common.sendKeys("ASSOCIATED_EMPLOYEE_ENTER_NAMEE_XPATH", value5);
	}

	//Associated employee tab-->To enter Title
	public void EnterTitle(String value5) {
		Common.sleep(1000);
		Common.click("ASSOCIATED_EMPLOYEE_ENTER_TITLE_XPATH");
		Common.sleep(1000);
		Common.sendKeys("ASSOCIATED_EMPLOYEE_ENTER_NAMEE_XPATH", value5);
	}

	//Associated employee tab-->To select the payment receiver checkbox
	public void selectPaymentReceiver() {
		Common.sleep(1000);
		Common.click("ASSOCIATED_EMPLOYEE_PAYMENT_RECEIVER_XPATH");
	}


	/**
	 * To set values for Company Profile all in one go and click next(Alternate method)
	 * @param value1
	 * @param value2
	 * @param value3
	 */
	public void setCompanyProfileTab(String value1, String value2,String value3) {
		Common.sendKeys("NEW_VENDOR_COMPANY_PROFILE1_XPATH", value1);
		Common.sendKeys("NEW_VENDOR_COMPANY_PROFILE2_XPATH", value2);
		Common.sendKeys("NEW_VENDOR_COMPANY_PROFILE3_XPATH", value3);

		Common.click("COMPANY_PROFILE_NEXT_BUTTON_XPATH");
	}

	//Company profile tab-->To click on next button
	public void clickNextButton() {
		Common.click("COMPANY_PROFILE_NEXT_BUTTON_XPATH");
	}

	//Associated employee tab-->To click Add Employee button
	public void clickAddEmployeeButton() {
		((JavascriptExecutor) driver).executeScript("scroll(0,-250);");

		Common.click("ASSOCIATED_EMPLOYEE_ADD_BUTTON_XPATH");

	}

	//Associated employee tab-->To click next button
	public void clickNextButton2() {
		Common.click("ASSOCIATED_EMPLOYEE_NEXT_BUTTON_XPATH");
	}

	//Booking Account tab-->To click Add line button
	public void bookingAccntAddLineButton() {
		Common.click("BOOKING_ACCOUNT_ADD_LINE_BUTTON_XPATH");
	}

	//Booking Account tab-->To select location from the dropdown
	public void selectLocation(String value7) 
	{
		WebElement locdrpdown=Common.getElement("BOOKING_ACCOUNT_TAB_LOC_DROPDOWN_XPATH");
		String locid=locdrpdown.getAttribute("id");
		String xPath="//td[@tabindex='1']/center/select[@id='"+locid+"']";
		String xPath1="//select[@id='"+locid+"']/option[2]";

		WebElement locdrpdown1=driver.findElement(By.xpath(xPath));
		locdrpdown1.click();
		Common.sleep(1000);
		WebElement locdrpdown2=driver.findElement(By.xpath(xPath1));
		locdrpdown2.click();

		WebElement vendorcreationheader1=Common.getElement("VENDOR_HEADER_XPATH");
		vendorcreationheader1.click();

	}

	//Booking Account tab-->To select department from the dropdown
	public void selectDepartment(String value8)
	{
		WebElement deptdrpdown=Common.getElement("BOOKING_ACCOUNT_TAB_DEPT_DROPDOWN_XPATH");
		String deptid=deptdrpdown.getAttribute("id");
		String xPath1="//td[@tabindex='2']/center/select[@id='"+deptid+"']";
		String xPath2="//select[@id='"+deptid+"']/option[2]";

		WebElement deptdrpdown1=driver.findElement(By.xpath(xPath1));
		deptdrpdown1.click();

		WebElement deptdrpdown2=driver.findElement(By.xpath(xPath2));
		deptdrpdown2.click();

		WebElement vendorcreationheader1=Common.getElement("VENDOR_HEADER_XPATH");
		vendorcreationheader1.click();

		Common.sleep(2000);
	}

	//Booking Account tab-->To select department from the dropdown
	public void selectBookingAccount(String value9) {
		WebElement bookinaccntdrpdown=Common.getElement("BOOKING_ACCOUNT_TAB_BOOKINGACCNT_DROPDOWN_XPATH");
		//Common.sleep(1000);
		String bookingaccntid=bookinaccntdrpdown.getAttribute("id");
		String xPath2="//td[@tabindex='3']/center/select[@id='"+bookingaccntid+"']";
		String xPath3="//select[@id='"+bookingaccntid+"']/option[2]";

		WebElement bookinaccntdrpdown1=driver.findElement(By.xpath(xPath2));
		bookinaccntdrpdown1.click();
		WebElement bookinaccntdrpdown2=driver.findElement(By.xpath(xPath3));
		bookinaccntdrpdown2.click();

		WebElement vendorcreationheader2=Common.getElement("VENDOR_HEADER_XPATH");
		vendorcreationheader2.click();
		Common.sleep(2000);

	}

	//Booking Account tab-->To click next button 
	public void clickNextButton3() 
	{
		Common.click("BOOKING_ACCOUNT_TAB_NEXT_BUTTON_XPATH");
	}

	//Booking Account tab-->To go to Product Details tab
	public void goToProductDetailsTab() 
	{
		Common.click("PRODUCT_DETAILS_TAB_XPATH");
		Common.sleep(3000);

	}

	//Booking Account tab-->To select the product from product list
	public void selectProduct() 
	{
		WebElement prodlistbox=Common.getElement("PRODUCT_DETAILS_PROD_LIST_BOX_XPATH");
		prodlistbox.click();
		Common.sleep(1000);
		WebElement rightarrowbtn=Common.getElement("RIGHT_ARROW_BUTTON_XPATH");
		rightarrowbtn.click();
		Common.sleep(2000);
	}
	public void selectProduct1(String value) 
	{
		Common.selectFromDropdown("PRODUCT_DETAILS_PROD_LIST_BOX_XPATH", "PRODUCT_DETAILS_PROD_LIST_BOX_OPTION_XPATH", value);
		/*WebElement prodlistbox=Common.getElement("PRODUCT_DETAILS_PROD_LIST_BOX_XPATH");
		prodlistbox.click();*/
		Common.sleep(1000);
		WebElement rightarrowbtn=Common.getElement("RIGHT_ARROW_BUTTON_XPATH");
		rightarrowbtn.click();
		Common.sleep(2000);
	}

	//Booking Account tab-->To click next button
	public void clickNextButton4() {
		Common.click("PRODUCT_DETAILS_TAB_NEXT_BUTTON_XPATH");
		Common.sleep(1000);
	}

	//Setting the company name 
	public String setCompanyName(String value)
	{
		companyName=Common.generateRandomString(value);
		return companyName;
	}

	//Getting the company name
	public String getCompanyName()
	{
		
		return companyName;
	}

	//Setting display name 
	public String setDisplayName(String value)
	{
		displayName=Common.generateRandomString(value);
		
		return displayName;
	}

	//Getting display name
	public String getDisplayName()
	{
		
		return displayName;
	}


	//Filling all the fields in Vendor details field
	public void vendorDetailsTab(String value10,String value11,String value12,String value,String value13,String value14,String value15,String value16,String value17,String value18,String value19,String value20,String value21,String value22,String value23,String value24,String value25,String value26) 
	{

		Common.sendKeys("TITLE_FIELD_XPATH", value10);
		Common.sleep(1000);

		Common.sendKeys("FIRST_NAME_XPATH", value11);
		Common.sleep(1000);

		Common.sendKeys("LAST_NAME_XPATH", value12);
		Common.sleep(1000);
		
		Common.sendKeys("AP_VENDER_CREATION_SUFFIX_NAME_XPATH", value);
		Common.sleep(1000);

		Common.sendKeys("COMPANY_NAME_XPATH",value13);
		Common.sleep(1000);

		Common.sendKeys("DISPLAY_NAME_XPATH", value14);
		Common.sleep(1000);

		Common.sendKeys("BUSINESS_TYPE_XPATH", value15);
		Common.sleep(1000);

		Common.sendKeys("OTHER_FIELD_XPATH", value16);
		Common.sleep(1000);

		WebElement displaynamechkbox=Common.getElement("DISPLAY_NAME_CHECKBOX_XPATH");
		displaynamechkbox.click();

		Common.sendKeys("STREET_ADD_XPATH", value17);
		Common.sleep(1000);


		Common.sendKeys("CITY_XPATH", value18);
		Common.sleep(1000);

		Common.sendKeys("STATE_XPATH", value19);
		Common.sleep(1000);

		Common.sendKeys("ZIP_XPATH", value20);
		Common.sleep(1000);


		((JavascriptExecutor) driver).executeScript("scroll(0,-250);");

		Common.sleep(1000);

		Common.sendKeys("EMAIL_ID_XPATH", value21);
		Common.sleep(1000);

		Common.sendKeys("PHONE_XPATH", value22);
		Common.sleep(1000);

		Common.sendKeys("MOBILE_XPATH", value23);
		Common.sleep(1000);

		Common.sendKeys("FAX_XPATH", value24);
		Common.sleep(1000);

		Common.sendKeys("WEBSITE_XPATH", value25);
		Common.sleep(1000);

		Common.sendKeys("NOTES_FIELD_XPATH", value26);
		Common.sleep(1000);

		WebElement savebutton=Common.getElement("VENDOR_SAVE_BUTTON_XPATH");
		savebutton.click();

	}

	//Verification point to check whether the vendor is present in the list
	public Boolean verifyVendorOnList(String expectedVendorName) 
	{
		boolean flag=false;
		Common.sleep(6000);
		Common.sendKeys("SEARCH_INPUT_XPATH", expectedVendorName);
		Common.sleep(1000);
		String actualVendorNameOnList=Common.getText("CREATED_VENDOR_NAME_IN_LIST_XPATH");
		//String actualVendorNameOnList="companyName";
		if(actualVendorNameOnList.equals(expectedVendorName))
		{
			flag=true;
			System.out.println("Vendor displayed on list");
			Reporter.log("Vendor present on list");
		}
		else
		{
			System.out.println("Vendor not created");

		}

		return flag;

	}
	public Boolean verifyVendorOnList1(String expectedClientName) 
	 {
	  boolean flag=false;
	  Common.sleep(6000);
	  System.out.println("expectedVendorName"+expectedClientName);
	  CommonMethods.searchByNumberOrName(expectedClientName);
	  Common.sleep(1000);
	  
	  String actualClientNameOnList=Common.getText("AR_CLIENT_LIST_XPATH");
	  if(actualClientNameOnList.equals(expectedClientName))
	  {
	   flag=true;
	   System.out.println("Vendor displayed on list");
	   Reporter.log("Vendor present on list and verified");
	   Reporter.log("Vendor added is present on Netchain Platform and The Connection is done successfully");
	  }
	  else
	  {
	   System.out.println("Vendor not created");
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
