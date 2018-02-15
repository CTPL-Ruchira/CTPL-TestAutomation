package com.netChain2.selenium.tests.automationSetUp;

import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.netChain2.engine.Common;




public class AutomationSetup {
	
	@BeforeSuite
	@Parameters({"browser", "profile", "driverFile", "url", "objectMapFile", "testDataFile", "pageLoadTimeOutInSec", 
		"elementLoadingTimeout", "screenshotsFolder"})
	public void setup(String browser, String profile, String driverFile, String url, String objectMapFile, 
			String testDataFile, String pageLoadTimeOutInSec, String elementLoadingTimeout, String screenshotsFolder){
		
	Common.setTestDataFile(testDataFile);
		
		Common.setObjectMapFile(objectMapFile);		

		Common.launchBrowser(browser, profile, driverFile);
		
		Common.setTimeOuts(Long.parseLong(pageLoadTimeOutInSec), Long.parseLong(elementLoadingTimeout));
		
		Common.launchUrl(url);
		
		Common.setScreenshotFolder(screenshotsFolder);
	}
}
