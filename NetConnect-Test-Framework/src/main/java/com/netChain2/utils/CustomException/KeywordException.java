package com.netChain2.utils.CustomException;

public class KeywordException extends RuntimeException {
	
	
	
	 /**
	 *Custom Exception
	 */
	private static final long serialVersionUID = 1L;

	public KeywordException(String message, Exception e) {
		 //super(message);
		   System.out.println(message);
		     e.printStackTrace();
	 }

}
