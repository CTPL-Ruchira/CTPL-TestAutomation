package com.netChain2.utils.gmailReader;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.BodyPart;
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.search.FlagTerm;

public class ReadGmail {

	public String readFirstUnreadEmail(String userName, String password) {
		Properties props = System.getProperties();
		props.setProperty("mail.store.protocol", "imaps");
		String mailContent=null;
		Store store = null;
		Folder inbox = null;
		Message messages[]=null;
		try {
			Session session = Session.getDefaultInstance(props, null);
			store = session.getStore("imaps");
			store.connect("imap.gmail.com", userName, password);
			System.out.println(store);

			inbox = store.getFolder("Inbox");
			inbox.open(Folder.READ_WRITE);
			Flags seen = new Flags(Flags.Flag.SEEN);
			FlagTerm unseenFlagTerm = new FlagTerm(seen, false);
			messages= inbox.search(unseenFlagTerm);

			for (int readCount = 0; readCount <= 10; readCount++) {
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				if (messages.length == 0 || !messages[0].getSubject().contains("Netchain2")) {
					messages = inbox.search(unseenFlagTerm);
					continue;
				}
				break;
			}

			try {
				
				Object obj = messages[0].getContent();
				Multipart mp = (Multipart) obj;
				BodyPart bp =  mp.getBodyPart(0);
				mailContent =  bp.getContent().toString();       
			} catch (Exception e) {
				System.out.println("Mail Has not yrt arrived, May be its late or Its Product Bug");
				e.printStackTrace();
			}
			
			
			

		} catch (NoSuchProviderException e) {
			e.printStackTrace();
			
		} catch (MessagingException e) {
			e.printStackTrace();
		
		}
		
		try {
			inbox.setFlags(new Message[] {messages[0]}, new Flags(Flags.Flag.SEEN), true);
			if(store !=null) {
	        	 store.close();
	         }

		} catch (MessagingException e) {
			
			e.printStackTrace();
		}
        		
		return mailContent;

	}
	
	public List<String> getAllUrls(String userName, String password) {
		String mailContent = readFirstUnreadEmail(userName, password);
		
		List<String> containedUrls = new ArrayList<String>();
	      String urlRegex = "((https?|ftp|gopher|telnet|file):((//)|(\\\\))+[\\w\\d:#@%/;$()~_?\\+-=\\\\\\.&]*)";
	      Pattern pattern = Pattern.compile(urlRegex, Pattern.CASE_INSENSITIVE);
	      Matcher urlMatcher = pattern.matcher(mailContent);

	      while (urlMatcher.find())
	      {
	    	String url =mailContent.substring(urlMatcher.start(0),urlMatcher.end(0));
	    	 if( url.substring(url.length()-2, url.length()).equals("If")) {
	    		 url=url.substring(0,url.length()-2);
	    	 }
	    	 containedUrls.add(url);
	      }

	      return containedUrls;
	}
	
}
