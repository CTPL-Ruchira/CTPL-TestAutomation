package com.netChain2.selenium.tests.accountsPayable.settings;

import java.util.ArrayList;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.netChain2.engine.BaseTestCase;
import com.netChain2.engine.Common;
import com.netChain2.selenium.pageObjects.accountsPayable.settings.Settings;
import com.netChain2.selenium.pageObjects.common.landingPage.LandingPage;
import com.netChain2.selenium.pageObjects.common.loginPage.LoginPage;
import com.netChain2.utils.CustomAnnotation.TestDetails;

public class AccountsPayableSettings extends BaseTestCase {
	private ArrayList<String> loginTestData;
	
	WebDriver driver=Common.getDriver();
	
	
	@BeforeClass
	public void setUp() 
	{
		loginTestData = Common.getTestData("NetchainTest.LoginTwoWayMatch");
				
		LandingPage landingPage = new LandingPage();
		landingPage.clickLogInButton();
		
		LoginPage loginPage = new LoginPage();
		loginPage.login(loginTestData.get(0), loginTestData.get(1));
	}
	
	
	@Test
	@TestDetails(author="Manoj Kumar", description="This methods creates custom workflow for invoice")
	public void createCustomWorkflowForInvoice() 
	{
		Settings settings=new Settings();
		settings.openSettings();
		
		settings.createNewCustomWorkflow();
		
		settings.clickOnFinishButton();
		
		
		
		
	}
	
}
