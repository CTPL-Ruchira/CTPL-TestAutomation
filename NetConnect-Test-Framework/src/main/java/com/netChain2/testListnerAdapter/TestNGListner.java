package com.netChain2.testListnerAdapter;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.LinkedHashSet;
import java.util.Set;

import org.testng.ITestContext;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.netChain2.engine.Common;

public class TestNGListner extends TestListenerAdapter {
	
	public static String currentTestClass;
	public static String currentTestMethod;
	public static int totalTestMethodCount;
	public static int executionCount;
	public static int passCount;
	public static int failCount;
	public static int skipCount;
	
	
	
	@Override
	public void onStart(ITestContext context) {
	        ITestNGMethod[] testMethods = context.getAllTestMethods();
		    int totalTestMethod= testMethods.length;
		    totalTestMethodCount=totalTestMethod;
		    Set<String> classes = new LinkedHashSet<String>();
		    for(ITestNGMethod method: testMethods) {
		    	String className = method.getRealClass().getCanonicalName();
		    	classes.add(className);
		    	}
	          int totalTestClasses = classes.size();
	          
	          
	          System.out.println("**************************************************************************************");
	          System.out.println("Suit Statastics: " + "Total Test Classes:" +totalTestClasses+ " Total Test Methods:" +totalTestMethod);
	          System.out.println("**************************************************************************************");
		
		
	}
	
	@Override
	public void onTestStart(ITestResult result) {
		 currentTestClass = result.getTestClass().getName();
		 currentTestMethod = result.getName();
		System.out.println("Starting Test " +currentTestClass+"."+currentTestMethod);
		
		
	}
	
	@Override
	public void onTestSuccess(ITestResult result) {
		passCount++;
		executionCount++;
		logMessage();
		
	}
	
	
	@Override
	public void onTestFailure(ITestResult result) {
		failCount++;
		executionCount++;
		logMessage();
		takeScreenshot(result);
	}
	
	
	@Override
	public void onTestSkipped(ITestResult result) {
		skipCount++;
		executionCount++;
		logMessage();
	}
	
	public void onFinish(ITestContext context) {
		System.out.println("**************************************************************************************");
		System.out.println("Final Statastics: " + " Total Test Cases:" +totalTestMethodCount+ " Total Passed Test:" +context.getPassedTests().size()+ " Total Failed Test:" +context.getFailedTests().size()+ " Total Skipped Test:"+ context.getSkippedTests().size());
		System.out.println("**************************************************************************************");
	}
	
	public static void logMessage() {
		
		System.out.println("Run Statistics: " +"Total Executed: " +executionCount+ " , Pass Count: " +passCount+ " , Fail Count: " +failCount+ " , Skip Count: " + skipCount );
	}
	
	public void takeScreenshot(ITestResult result)
	{
		currentTestClass = result.getTestClass().getName();
		currentTestMethod = result.getName();
		String callerClass = currentTestClass.substring(currentTestClass.lastIndexOf(".")+1);
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy_HH-mm-ss");
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());			
		String timeStamp = sdf.format(timestamp);
		Common.captureScreenshot(callerClass + "_" + currentTestMethod + "_" + timeStamp);
	}

}
