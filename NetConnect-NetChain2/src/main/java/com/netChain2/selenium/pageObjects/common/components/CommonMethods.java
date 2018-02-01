package com.netChain2.selenium.pageObjects.common.components;

import com.netChain2.engine.Common;

/*
 * This method enters value in the search input field
 * parameters=searchTerm
 * */
public class CommonMethods 
{
	public static void searchByNumberOrName(String searchTerm)
	{
		Common.sendKeys("SEARCH_INPUT_XPATH", searchTerm);
	}
}
