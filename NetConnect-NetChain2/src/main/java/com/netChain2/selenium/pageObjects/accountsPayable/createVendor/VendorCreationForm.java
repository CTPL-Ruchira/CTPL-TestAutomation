package com.netChain2.selenium.pageObjects.accountsPayable.createVendor;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

import com.netChain2.engine.Common;



/**
 * API's for Vendor Creation Form
 * @author Ruchira.Mhaisurkar
 * 
 *
 */
public class VendorCreationForm {

	WebDriver driver=Common.getDriver();
	private String companyName;

	public void setOurCompany(String value) {
		Common.sendKeys("NEW_VENDOR_COMPANY_PROFILE1_XPATH", value);
	}


	public void setOurMission(String value) {
		Common.sendKeys("NEW_VENDOR_COMPANY_PROFILE2_XPATH", value);

	}

	public void setProductsAndServices(String value){
		Common.sendKeys("NEW_VENDOR_COMPANY_PROFILE3_XPATH", value);
	}



	public void EnterName(String value4) {
		Common.sleep(1000);
		Common.click("ASSOCIATED_EMPLOYEE_NAME_FIELD_XPATH");
		Common.sleep(2000);
		Common.sendKeys("ASSOCIATED_EMPLOYEE_ENTER_NAMEE_XPATH", value4);

	}

	public void EnterEmail(String value5) {
		Common.sleep(1000);
		Common.click("ASSOCIATED_EMPLOYEE_ENTER_EMAIL_XPATH");
		Common.sleep(1000);
		Common.sendKeys("ASSOCIATED_EMPLOYEE_ENTER_NAMEE_XPATH", value5);
	}

	public void EnterPhoneNumber(String value5) {
		Common.sleep(1000);
		Common.click("ASSOCIATED_EMPLOYEE_ENTER_PHONE_XPATH");
		Common.sleep(1000);
		Common.sendKeys("ASSOCIATED_EMPLOYEE_ENTER_NAMEE_XPATH", value5);
	}

	public void EnterTitle(String value5) {
		Common.sleep(1000);
		Common.click("ASSOCIATED_EMPLOYEE_ENTER_TITLE_XPATH");
		Common.sleep(1000);
		Common.sendKeys("ASSOCIATED_EMPLOYEE_ENTER_NAMEE_XPATH", value5);
	}

	public void selectPaymentReceiver() {
		Common.sleep(1000);
		Common.click("ASSOCIATED_EMPLOYEE_PAYMENT_RECEIVER_XPATH");
	}


	/**
	 * To set values for Company Profile all in one go and click next
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

	public void clickNextButton() {
		Common.click("COMPANY_PROFILE_NEXT_BUTTON_XPATH");
	}

	public void clickAddEmployeeButton() {
		((JavascriptExecutor) driver).executeScript("scroll(0,-250);");

		Common.click("ASSOCIATED_EMPLOYEE_ADD_BUTTON_XPATH");

	}


	public void clickNextButton2() {
		Common.click("ASSOCIATED_EMPLOYEE_NEXT_BUTTON_XPATH");
	}

	public void bookingAccntAddLineButton() {
		Common.click("BOOKING_ACCOUNT_ADD_LINE_BUTTON_XPATH");
	}

	public void selectLocation(String value7) {
		WebElement locdrpdown=Common.getElement("BOOKING_ACCOUNT_TAB_LOC_DROPDOWN_XPATH");
		String locid=locdrpdown.getAttribute("id");
		System.out.println("loc Id is");
		System.out.println(locid);
		System.out.println("val"+value7);
		String xPath="//td[@tabindex='1']/center/select[@id='"+locid+"']";
		String xPath1="//select[@id='"+locid+"']/option[2]";

		WebElement locdrpdown1=driver.findElement(By.xpath(xPath));
		locdrpdown1.click();
		Common.sleep(1000);
		WebElement locdrpdown2=driver.findElement(By.xpath(xPath1));
		locdrpdown2.click();

		WebElement vendorcreationheader1=Common.getElement("VENDOR_HEADER_XPATH");
		vendorcreationheader1.click();

		//Select locdrpdownfinal=new Select(driver.findElement(By.xpath("//td[@tabindex='1']/center/select[@id='"+locid+"']")));
		//locdrpdownfinal.selectByVisibleText(value7);

		//WebElement locDrpdownSelect=Common.getElement("")
	}

	public void selectDepartment(String value8) {
		WebElement deptdrpdown=Common.getElement("BOOKING_ACCOUNT_TAB_DEPT_DROPDOWN_XPATH");
		//Common.sleep(1000);
		String deptid=deptdrpdown.getAttribute("id");
		System.out.println("dept Id is");
		System.out.println(deptid);
		String xPath1="//td[@tabindex='2']/center/select[@id='"+deptid+"']";
		String xPath2="//select[@id='"+deptid+"']/option[2]";

		WebElement deptdrpdown1=driver.findElement(By.xpath(xPath1));
		deptdrpdown1.click();


		WebElement deptdrpdown2=driver.findElement(By.xpath(xPath2));
		deptdrpdown2.click();

		//			Select deptdrpdownfinal=new Select(driver.findElement(By.xpath("//td[@tabindex='2']/center/select[@id='"+deptid+"']")));
		//			deptdrpdownfinal.selectByVisibleText(value8);

		WebElement vendorcreationheader1=Common.getElement("VENDOR_HEADER_XPATH");
		vendorcreationheader1.click();

		Common.sleep(2000);
	}


	public void selectBookingAccount(String value9) {
		WebElement bookinaccntdrpdown=Common.getElement("BOOKING_ACCOUNT_TAB_BOOKINGACCNT_DROPDOWN_XPATH");
		//Common.sleep(1000);
		String bookingaccntid=bookinaccntdrpdown.getAttribute("id");
		System.out.println("booking accnt id is");
		System.out.println(bookingaccntid);
		String xPath2="//td[@tabindex='3']/center/select[@id='"+bookingaccntid+"']";
		String xPath3="//select[@id='"+bookingaccntid+"']/option[2]";

		WebElement bookinaccntdrpdown1=driver.findElement(By.xpath(xPath2));
		bookinaccntdrpdown1.click();
		WebElement bookinaccntdrpdown2=driver.findElement(By.xpath(xPath3));
		bookinaccntdrpdown2.click();



		//   	Select bookingaccntdrpdownfinal=new Select(driver.findElement(By.xpath("//td[@tabindex='3']/center/select[@id='"+bookingaccntid+"']")));
		//   	bookingaccntdrpdownfinal.selectByVisibleText(value9);

		WebElement vendorcreationheader2=Common.getElement("VENDOR_HEADER_XPATH");
		vendorcreationheader2.click();
		Common.sleep(2000);

	}

	public void clickNextButton3() {
		Common.click("BOOKING_ACCOUNT_TAB_NEXT_BUTTON_XPATH");
	}


	public void goToProductDetailsTab() {

		Common.click("PRODUCT_DETAILS_TAB_XPATH");
		Common.sleep(3000);

	}

	public void selectProduct() {
		WebElement prodlistbox=Common.getElement("PRODUCT_DETAILS_PROD_LIST_BOX_XPATH");
		prodlistbox.click();
		Common.sleep(1000);
		WebElement rightarrowbtn=Common.getElement("RIGHT_ARROW_BUTTON_XPATH");
		rightarrowbtn.click();
		Common.sleep(2000);
	}

	public void clickNextButton4() {
		Common.click("PRODUCT_DETAILS_TAB_NEXT_BUTTON_XPATH");
		Common.sleep(1000);
	}

	public void vendorDetailsTab(String value10,String value11,String value12,String value13,String value14,String value15,String value16,String value17,String value18,String value19,String value20,String value21,String value22,String value23,String value24,String value25,String value26) {
		Common.sendKeys("TITLE_FIELD_XPATH", value10);
		Common.sleep(1000);

		Common.sendKeys("FIRST_NAME_XPATH", value11);
		Common.sleep(1000);

		Common.sendKeys("LAST_NAME_XPATH", value12);
		Common.sleep(1000);

		Common.sendKeys("COMPANY_NAME_XPATH", value13);
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
	
	public Boolean verifyVendorOnList(String expectedVendorName) 
	 {
	  boolean flag=false;
	  Common.sleep(6000);
	  Common.sendKeys("INVOICE_SEARCH_BAR_XPATH", expectedVendorName);
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
	
	 //Getting the company name
	 public String getCompanyName()
	 {
	  return companyName;
	 }
}
