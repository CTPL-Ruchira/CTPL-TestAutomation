package com.netChain2.utils.fileUtils.fileOperation;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Properties;

public class PropertyFileReader {
	private final String configurationPropertyPath;
	private Hashtable<String, String> hashTable= new Hashtable<String, String>();
	public PropertyFileReader(String configurationPropertyPath) {
		this.configurationPropertyPath=configurationPropertyPath;
		
		Properties prop = new Properties();
		try {
			prop.load(new FileInputStream(new File(this.configurationPropertyPath)));
			hashTable = (Hashtable)prop;
			
		} catch (FileNotFoundException e) {
		       e.printStackTrace();
		} catch (IOException e) {
			       e.printStackTrace();
		}
	}

	
	public String getValue(String key) {
		return hashTable.get(key);
	}
	
}
