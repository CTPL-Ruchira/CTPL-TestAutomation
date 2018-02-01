package com.netChain2.utils.fileUtils.readMail;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.netChain2.utils.fileUtils.fileOperation.FileOperation;


/**
 * 
 * API's to Read EMail File
 *
 */
public class ReadMail {
	
	private final String mailServerHomePath; 
	
	public ReadMail(String mailServerRootPath) {
		this.mailServerHomePath=mailServerRootPath;
	}


	/**
	 * Get All URLs from Email
	 * @param userAccount
	 * @return
	 */
	public List<String> getAllURLFromEmail(String userAccount) {
		
		String eMailPath=this.mailServerHomePath+"\\apps\\james\\var\\mail\\inboxes\\"+userAccount;
		String fileContent=null;
		FileOperation fileOps=null;
		File emails = new File(eMailPath);
	      File[] files = emails.listFiles();
		      for(File file:files) {
		    	  
		    	  if(file.getAbsolutePath().contains(".FileStreamStore")) {
		    		   fileOps = new FileOperation();
		    		  fileContent = fileOps.getFileContent(file);
		    		  break;
		    	  }
		      }
		
		      List<String> containedUrls = new ArrayList<String>();
		      String urlRegex = "((https?|ftp|gopher|telnet|file):((//)|(\\\\))+[\\w\\d:#@%/;$()~_?\\+-=\\\\\\.&]*)";
		      Pattern pattern = Pattern.compile(urlRegex, Pattern.CASE_INSENSITIVE);
		      Matcher urlMatcher = pattern.matcher(fileContent);

		      while (urlMatcher.find())
		      {
		    	String url =fileContent.substring(urlMatcher.start(0),urlMatcher.end(0));
		    	 if( url.substring(url.length()-2, url.length()).equals("If")) {
		    		 url=url.substring(0,url.length()-2);
		    	 }
		    	 containedUrls.add(url);
		      }
		      
		      //fileOps.backUpJamesEmail(eMailPath);
		      return containedUrls;
		
		}
	
	
	/**
	 * To Get Accept URL From Email
	 * @param userAccount
	 * @return
	 */
	public String getAcceptURL(String userAccount) {
		return getAllURLFromEmail(userAccount).get(0);
	}
	
	/**
	 * To get Reject URL from Email
	 * @param userAccount
	 * @return
	 */
	public String getRejectURL(String userAccount) {
		return getAllURLFromEmail(userAccount).get(1);
	}
	
	/**
	 * To Back up Emails 
	 * Make Sure that after getting required URL Call this method to backup Emails.
	 * @param userAccount
	 */
	public void backupJamesEmail(String userAccount) {
		String eMailPath=this.mailServerHomePath+"\\apps\\james\\var\\mail\\inboxes\\"+userAccount;
		FileOperation ops = new FileOperation();
		ops.backUpJamesEmail(eMailPath);
		
	}
	
}
