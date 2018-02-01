package com.netChain2.utils.fileUtils.fileOperation;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * 
 * API's To Perform File operation
 *
 */

public class FileOperation {
	
	
	
  /**
   * To Verify whether path is file or Directory
   * @param path
   * @return
   */
  
	public boolean isDirectory(String path) {
		return new File(path).isDirectory();
	}
	
	
	/**
	 * To back the emails from inbox to back up folder
	 * @param inboxDirectory
	 */
	public void backUpJamesEmail(String inboxDirectory) {
		
	File mailBox = new File(inboxDirectory);
	File[] files = mailBox.listFiles();
	ArrayList<File> mailList =  new ArrayList<File>();
	boolean toCreateBackUpDir=true;
	File backuDir=new File(inboxDirectory+"\\backup");
	for(File file: files) {	
		if(file.isDirectory()) {
			if(file.getAbsolutePath().contains("backup")) {
				toCreateBackUpDir=false;
			}
		}
		
		if(file.isFile()) {
			mailList.add(file);
		}
	}
	
	if(toCreateBackUpDir) {
	         new File(inboxDirectory+"\\backup").mkdir();
	     }
	
	for(File file:mailList) {
		file.renameTo(new File(backuDir,file.getName()));
		file.delete();
	}
	
}
	/**
	 * To get Content of File
	 * @param file
	 * @return
	 */
	public String getFileContent(File file) {
		BufferedReader reader=null;
        StringBuilder builder = new StringBuilder();
		String fileContent;
		try {
			  reader = new BufferedReader(new FileReader(file));
               while((fileContent=reader.readLine())!=null) {
			     builder.append(fileContent);
              }
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		finally {
			try {
				if(reader != null) {
				reader.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		    return builder.toString();
	}
	

}
