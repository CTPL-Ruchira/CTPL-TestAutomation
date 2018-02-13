package com.netChain2.selenium.pageObjects.accountsReceivable.createClients;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

import com.netChain2.engine.BaseTestCase;
import com.netChain2.engine.Common;

public class POandSOConnectionForm {
	private static int flag=1;
	public void clickAccept(String ClientName, String poNumber) {
		String soSearchAccept="//div[text()='"+poNumber+"']/ancestor::div[2]/div[4]/div[text()='"+ClientName+"']/ancestor::div[2]/div[11]/div/div/button[@id='share']";
		WebElement eleAccept=Common.findElement(soSearchAccept);
		eleAccept.click();
	}
	
	//Get invoice number
	public String getinvoiceNumber() {
		return Common.getText("AR_GET_INVOICE_NUMBER_FROM_INVOCE_XPATH");
	}
	
	public void verifySOList(String ClientName, String poNumber)
	{
		Common.sleep(3000);
		System.out.println("geting so list");
		String st="//div[@class='LineItemDataList']/div/div[4]/div[text()='"+ClientName+"']";
		WebElement ele=Common.findElement(st);
		List<WebElement> list=ele.findElements(By.xpath(st));
		System.out.println(list);
		
		System.out.println("size of list"+list.size());
		int count=7;
		//System.out.println("sghkhfildaf"+ele);
		for(int i=0; i<list.size(); i++)
		{
			String soGetClientName="//div[@class='LineItemDataList']/div["+count+"]/div[4]/div[text()='"+ClientName+"']/ancestor::div[2]/div[6]/div[text()='"+poNumber+"']/ancestor::div[2]/div[11]//button[@data-tip='Accept']";
			System.out.println("soGetClientName --"+soGetClientName);
			WebElement elem=Common.findElement(soGetClientName);
			elem.click();
			/*System.out.println("WebElement"+elem);
			if(elem.isDisplayed())
			{
				elem.click();
			}
			else
			{
			count++;
			}*/
		}
	}
	//click on plus sign
	public void clickPlusbutton(String ClientName, String poNumber) {
		Common.sleep(3000);
		String st="//div[@class='LineItemDataList']/div/div[4]/div[text()='"+ClientName+"']";
		WebElement ele=Common.findElement(st);
		List<WebElement> list=ele.findElements(By.xpath(st));
		System.out.println(list);
		
		System.out.println("size of list"+list.size());
		int count=7;
		//System.out.println("sghkhfildaf"+ele);
		for(int i=0; i<list.size(); i++)
		{
									//div[@class='LineItemDataList']/div[7]/div[4]/div[text()='TechBite']/ancestor::div[2]/div[6]/div[text()='1']/ancestor::div[2]/div[11]/div/button/i[@data-tip='Create Invoice']
			String soGetClientName="//div[@class='LineItemDataList']/div["+count+"]/div[4]/div[text()='"+ClientName+"']/ancestor::div[2]/div[6]/div[text()='"+poNumber+"']/ancestor::div[2]/div[11]/div/button/i[@data-tip='Create Invoice']";
			System.out.println("soGetClientName --"+soGetClientName);
			WebElement elem=Common.findElement(soGetClientName);
			elem.click();
			/*System.out.println("WebElement"+elem);
			if(elem.isDisplayed())
			{
				elem.click();
			}
			else
			{
			count++;
			}*/
		}
			
	}
	public void getClientName(String ClientName, String poNumber) 
	{		
	    String soGetClientName="//div[text()='"+poNumber+"']/ancestor::div[2]/div[4]/div[text()='"+ClientName+"']";
		WebElement eleAccept=Common.findElement(soGetClientName);
		eleAccept.getText();
		System.out.println(eleAccept);
	}
	public String getAttributeValuePoNo(){
  		return Common.getAttribute("PO_NUMBER_XPATH");
    }
	//click save and share button on invoice page
	public void clickSaveAndShareBtn() {
		Common.click("INVOICE_SO_SAVE_AND_SHARE_BUTTON_XPATH");
	}
	
}
