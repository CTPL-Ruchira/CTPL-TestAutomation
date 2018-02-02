package com.netChain2.selenium.tests.tearDown;

import org.testng.annotations.AfterSuite;

import com.netChain2.engine.Common;

/**
 * 
 * To Close Selenium Session
 *
 */
public class CloseDriverSession {
	
	@AfterSuite(alwaysRun=true)
	public void quitBrowser() {
		System.out.println("Closing Selenium Session..");
		Common.getDriver().quit();
	}

}
