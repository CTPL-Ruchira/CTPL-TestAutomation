package com.netChain2.selenium.pageObjects.accountsPayable.settings;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import com.netChain2.engine.Common;
import com.netChain2.selenium.pageObjects.common.JavaScriptOperation.JavaScriptUtils;
import com.netChain2.selenium.pageObjects.common.components.CommonMethods;

public class Settings 
{
	public void openSettings()
	{
		Common.click("CLICK_MENU_BUTTON_CLASSNAME");
		Common.sleep(1000);
		Common.click("AP_SETTINGS_XPATH");
	}

	public void createNewCustomWorkflow() 
	{
		clickOnCreateNewCustomWorkflowButton();
		Common.sleep(1000);
		customizeWorkflow();
		}

	private void customizeWorkflow() 
	{
		setInvoiceAcceptance();
		setInvoiceApproval();
		setCreatePayment();
		setSiningAuthorityPaymentApprover();
		setPaymentRelease();
	}
	
	private void setPaymentRelease() 
	{
		String sourceXPath=getLocatorValue("INVOICE_PAYMENT_RELEASE_SOURCE_PATH");
		String targetXpath=getLocatorValue("INVOICE_WORKFLOW_DESTINATION_PATH");
		JavaScriptUtils.jQueryDragAndDrop(sourceXPath, targetXpath);
		
	}

	private void setSiningAuthorityPaymentApprover() 
	{
		String sourceXPath=getLocatorValue("INVOICE_SININGAUTHORITY_PAYMENTAPPROVER_SOURCE_PATH");
		String targetXpath=getLocatorValue("INVOICE_WORKFLOW_DESTINATION_PATH");
		JavaScriptUtils.jQueryDragAndDrop(sourceXPath, targetXpath);
	}

	private void setCreatePayment() 
	{
		String sourceXPath=getLocatorValue("INVOICE_CREATE_PAYMENT_SOURCE_PATH");
		String targetXpath=getLocatorValue("INVOICE_WORKFLOW_DESTINATION_PATH");
		JavaScriptUtils.jQueryDragAndDrop(sourceXPath, targetXpath);
		
	}

	private void setInvoiceApproval() 
	{
		String sourceXPath=getLocatorValue("INVOICE_APPROVAL_SOURCE_PATH");
		String targetXpath=getLocatorValue("INVOICE_WORKFLOW_DESTINATION_PATH");
		JavaScriptUtils.jQueryDragAndDrop(sourceXPath, targetXpath);
	}

	private void setInvoiceAcceptance()
	{
		String sourceXPath=getLocatorValue("INVOICE_ACCEPTANCE_SOURCE_PATH");
		String targetXpath=getLocatorValue("INVOICE_WORKFLOW_DESTINATION_PATH");
		JavaScriptUtils.jQueryDragAndDrop(sourceXPath, targetXpath);
	}
	
	public void autoAcceptValue(String value) {
      Common.sendKeys("INVOICE_WORKFLOW_AUTOACCEPT_INVOICE_TEXTBOX_XPATH", value);
      Common.sleep(2000);
	}
	

	public void autoAprroveValue(String value) {
      Common.sendKeys("INVOICE_WORKFLOW_AUTOAPPROVE_INVOICE_TEXTBOX_XPATH", value);
      Common.sleep(2000);
	}
	
	public void autocreatePaymentValue(String value) {
	      Common.sendKeys("INVOICE_WORKFLOW_AUTOCREATEPAYMENT_INVOICE_TEXTBOX_XPATH", value);
	      Common.sleep(2000);
	}
	public void autoapprovePaymentValue(String value) {
	      Common.sendKeys("INVOICE_WORKFLOW_AUTOAPPROVEPAYMENT_INVOICE_TEXTBOX_XPATH", value);
	      Common.sleep(3000);
	}

	private String getLocatorValue(String xpathName)
	{
		return Common.getObjectValue(xpathName);
	}

	private void clickOnCreateNewCustomWorkflowButton() 
	{
		Common.click("CREATE_NEW_CUSTOM_WORKFLOW_BTN_XPATH");
	}
	
	public void clickOnFinishButton()
	{
		Common.click("INVOICE_CUSTOMWORKFLOW_FINISH_BUTTON_XPATH");
	    Common.sleep(3000);
	}

	public boolean verificationForAutoApproveLink(String vendorName, String invoiceNumber,String expectedValue)
	{
		//String invoiceApproveLocator="//div[text()='"+invoiceNumber+"']/ancestor::div[2]/div[2]/div[text()='"+vendorName+"']/ancestor::div[2]/div[9]/div[@class='text']/div[1]/div[1]/a[2]";
		String invoiceApproveLocator="//div[text()='"+invoiceNumber+"']/ancestor::tr[1]/td[4]//div[text()='"+vendorName+"']/ancestor::tr[1]/td[10]//div[@class='text']//a[text()='Approve Invoices']";
		WebElement elementApprove=Common.findElement(invoiceApproveLocator);
		 String ActualValue= elementApprove.getText();
	    String ExpectedValue=expectedValue;
	    if(ActualValue.equals(ExpectedValue))
			{
				return true;
				
			}else {
				
				return false;
				    }
			
		     }
	 
		
	public static  void scrollUp()
	{
		JavascriptExecutor jse = (JavascriptExecutor)Common.getDriver();
		jse.executeScript("scroll(0, -250);");
	}
  
	public void selectProductFromDropdown(String productName) {
    Common.select("CREATE_INVOICE_SELECT_PRODUCT_XPATH", productName);
   }
	
	public void selectDepartment(String department) {
	    Common.select("CREATE_INVOICE_ITEM_DETAILS_DEPARTMENT_XPATH", department);
	   }
	
	public void selectBookingAccount(String bookingAccount) {
	    Common.select("CREATE_INVOICE_BOOKING_AMOUNT_XPATH", bookingAccount);
	   }
	
	public void itemDescription(String description) {
	    Common.sendKeys("CREATE_INVOICE_DESC_FIELD_XPATH", description);
	   }

	public void itemMeasure(String measure) {
	    Common.sendKeys("CREATE_INVOICE_MEASURE_XPATH", measure);
	   }
	
	public void itemQuantity(String quantity) {
	    Common.sendKeys("CREATE_INVOICE_QTY_XPATH", quantity);
	   }
	
	public void itemRate(String rate) {
	    Common.sendKeys("CREATE_INVOICE_RATE_XPATH", rate);
	   }



	public boolean verificationForCreatePayment(String vendorName, String invoiceNumber,String expectedValue)
	{
		String createPaymentLocator="//div[text()='"+invoiceNumber+"']/ancestor::div[2]/div[2]/div[text()='"+vendorName+"']/ancestor::div[2]/div[9]/div[@class='text']/div[1]/div[1]/a[2]";
	
		WebElement elementApprove=Common.findElement(createPaymentLocator);
	    String ActualValue= elementApprove.getText();
	    String ExpectedValue=expectedValue;
	    if(ActualValue.equals(ExpectedValue))
			{
				return true;
				
			}else {
				
				return false;
				    }
			
		     }
	 

	public void verificationForViewPayment(String vendorName, String invoiceNumber)
	{
		String viewPaymentLocator="//div[text()='"+invoiceNumber+"']/ancestor::div[2]/div[2]/div[text()='"+vendorName+"']/ancestor::div[2]/div[9]/div[@class='text']/div[1]/div[1]/a[2]";
	
		WebElement elementViewPayment=Common.findElement(viewPaymentLocator);
		elementViewPayment.click();
	 }
	
	public String getPaymentId() {
		WebElement id=Common.getElement("INVOICE_GET_PAYMENTID_XPATH");
	      String payId= id.getText();
	     
	        String[] l=payId.split("\\|");
			String m=l[0];
			String []p=m.split("\\#");
			String pay=p[1].trim();
			return pay;
	}
	
	public boolean verificationForSendPayment(String vendorName, String paymentId,String expectedValue)
	{
		String sendPaymentLocator="//div[text()='"+paymentId+"']/ancestor::div[2]/div[2]/div[text()='"+vendorName+"']/ancestor::div[2]/div[9]/div[@class='text']/a[1]";
	
		WebElement elementSend=Common.findElement(sendPaymentLocator);
	    String ActualValue= elementSend.getText();
	    String ExpectedValue=expectedValue;
	    
	   if(ActualValue.equals(ExpectedValue))
			{
				return true;
				
			}else {
				
				return false;
				    }
			
		     }
	
	public void clickOnOpenMenu()
	{
      Common.click("NAVIGATION_MENU_PARTIAL_LINK");
      Common.sleep(2000);
	}
	
	public void clickOnPaymentMenu()
	{
      Common.click("INVOICE_MENU_PAYMENT_OPTION_XPATH");
      Common.sleep(2000);
	}
	public boolean verificationForApprovePayment(String vendorName, String paymentId,String expectedValue)
	{
		String approvePaymentLocator="//div[text()='"+paymentId+"']/ancestor::div[2]/div[2]/div[text()='"+vendorName+"']/ancestor::div[2]/div[9]/div[@class='text']/a[1]";
	    WebElement elementApprove=Common.findElement(approvePaymentLocator);
	    String ActualValue= elementApprove.getText();
	    String ExpectedValue=expectedValue;
	   if(ActualValue.equals(ExpectedValue))
			{
				return true;
				
			}else {
				
				return false;
				    }
     }
	
	public void clickCreateNewGrCustomWorkflow() 
	{
		Common.click("SETTINGS_CREATE_NEW_GR_CUSTOM_WORKFLOW_BUTTON_XPATH");
	}
	private void setCreateGoodsReceipt()
	{
		String sourceXPath=getLocatorValue("GR_WORKFLOW_SOURCE_XPATH");
		String targetXPath=getLocatorValue("INVOICE_WORKFLOW_DESTINATION_PATH");
		JavaScriptUtils.jQueryDragAndDrop(sourceXPath, targetXPath);
	}
	private void clickOnIsGoodsReceiptAllowedWithoutPOYes()
	{
		Common.click("GR_WORKFLOW_IS_GOODS_RECEIPT_ALLOWED_WITHOUT_PO_YES_XPATH");
	}
	private void selectBookingCodeForGrAccural(String value)
	{
		{
			Common.select("GR_WORKFLOW_BOOKING_ACCOUNT_DROPDOWN_XPATH", value);
		}
	}
	private void clickOnIsGrEditAllowedYes()
	{
		Common.click("GR_WORKFLOW_IS_GR_EDIT_ALLOWED_YES_XPATH");
	}
	private void selectRoleToAdjustGrFromDropdown()
	{
		Common.click("GR_WORKFLOW_SELECT_ROLE_DROPDOWN_OPEN_XPATH");
		Common.click("GR_WORKFLOW_SELECT_ADMIN_ROLE_FROM_DROPDOWN_XPATH");
		Common.click("GR_WORKFLOW_RANDOM_CLICK_XPATH");
	}
	
	public boolean verificationForGrWorkflowCreation()
	{
		boolean status=Common.isElementPresent("GR_WORKFLOW_VERIFICATION_POINT_XPATH");
		System.out.println("Status"+status);
		
		return status;
		
		
	}
	
	public void CreateGRCustomWorkflow(String value)
	{
		openSettings();
	    clickCreateNewGrCustomWorkflow();
		setCreateGoodsReceipt();
		clickOnIsGoodsReceiptAllowedWithoutPOYes();
		selectBookingCodeForGrAccural(value);
		clickOnIsGrEditAllowedYes();
		selectRoleToAdjustGrFromDropdown();
		clickOnFinishButton();
		clickCreateNewGrCustomWorkflow();
		Common.sleep(2000);
		CommonMethods.logoutFromWorkflowPage();
		
	}
}


