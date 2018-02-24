package com.netChain2.selenium.pageObjects.common.JavaScriptOperation;

import java.io.File;
import java.util.Scanner;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import com.netChain2.engine.Common;


public class JavaScriptUtils {
	
	
	public static void jQueryDragAndDrop(String sourceXPath, String targetXpath) {
		
		StringBuilder fileContents=null;
		Scanner scanner = null;
		try {
			File file = new File(".//jQueries//drag-drop.js");
			 fileContents = new StringBuilder((int)file.length());
			scanner = new Scanner(file);
			String lineSeparator = System.getProperty("line.separator");
			while(scanner.hasNextLine()) {        
				fileContents.append(scanner.nextLine() + lineSeparator);
		}
			
		}catch(Exception ex){
			ex.printStackTrace();	    	
		}
		finally {
			scanner.close();
		}
		
		((JavascriptExecutor)Common.getDriver()).executeScript(fileContents.toString());
		
		String java_script = "$('"+sourceXPath+"').simulateDragDrop({dropTarget:'"+targetXpath+"'})";
		
		((JavascriptExecutor)Common.getDriver()).executeScript(java_script);
		
	}
	
	
	public static void clickByJavaScript(WebElement ele) {
	JavascriptExecutor jsExec =	(JavascriptExecutor)Common.getDriver();
	jsExec.executeScript("arguments[0].click()", ele);
	}
	

}
