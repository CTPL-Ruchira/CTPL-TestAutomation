package com.netChain2.selenium.tests.goodsAndServices;

import java.util.ArrayList;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.netChain2.engine.Common;
import com.netChain2.selenium.pageObjects.accountsPayable.createGoodsAndServices.GoodsAndServicesCreationForm;
import com.netChain2.selenium.pageObjects.common.landingPage.LandingPage;
import com.netChain2.selenium.pageObjects.common.loginPage.LoginPage;
import com.netChain2.utils.CustomAnnotation.TestDetails;


public class CreateGoodsAndServices {
	private ArrayList<String> testData;
	private ArrayList<String> testDataGoodsAndServices;
	private String expectedProdName;
	
	
	@BeforeClass
	public void setUp() {
		testData = Common.getTestData("NetchainTest.Login");
		testDataGoodsAndServices=Common.getTestData("NetchainTest.CreateGoodsAndServices");
	}
	 @Test
     @TestDetails(author="Shital.Patil", description="Create Goods And Services")
	 
	public void createGoodsAndServices() {
		LandingPage landingPage = new LandingPage();				
		landingPage.clickLogInButton();
		
		LoginPage loginPage = new LoginPage();
		loginPage.login(testData.get(0), testData.get(1));
		Common.sleep(2000);
		
		GoodsAndServicesCreationForm goodsAndServices=new GoodsAndServicesCreationForm();
		//click menu button
		goodsAndServices.clickMenuButton();
		Common.sleep(1000);
		
		//click goods and services lable
		goodsAndServices.clickGoodsAndServices();
		Common.sleep(1000);
		
		//click things we buy lable
		goodsAndServices.clickThingsWeBuy();
		//click Add New Goods 
		goodsAndServices.clickAddNewGoods();
		
		//click Product Bundle 
		goodsAndServices.clickProductBundleNo();
		
		//click inventory item 
		goodsAndServices.clickInventoryItemNO();
		
		//set product name
		expectedProdName=Common.generateRandomString(testDataGoodsAndServices.get(0));
		goodsAndServices.setProductName(expectedProdName);
		Common.sleep(2000);
		
		//select category
		goodsAndServices.selectCategory("ADD NEW CATEGORY");
		
		//SET add new category
		goodsAndServices.setAddNewCategory(testDataGoodsAndServices.get(2));
		
		//set sku value
		goodsAndServices.setSKUValue(testDataGoodsAndServices.get(3));
		
		//click po number
		goodsAndServices.clickPOnumberRequired();
		
		//Select Location,Department,BookingAccount,Setcost and Margine
		goodsAndServices.selectLocation(testDataGoodsAndServices.get(4));
		
		goodsAndServices.selectDepartment(testDataGoodsAndServices.get(5));
		
		goodsAndServices.selectBookingAccount(testDataGoodsAndServices.get(6));
		
		goodsAndServices.setCost(testDataGoodsAndServices.get(7));
		
		goodsAndServices.setMargine(testDataGoodsAndServices.get(8));
		
		//click save Button
		goodsAndServices.clickSaveButton();
		Common.sleep(7000);
		
		//Check Product is Added Or Not	
		goodsAndServices.verifyProductName("CHECK_PRODUCT_ADDED_XPATH", expectedProdName);		
	 }
	 @AfterClass
	 public void closeBrowser() {
		 Common.getDriver().close();
	 }

}
