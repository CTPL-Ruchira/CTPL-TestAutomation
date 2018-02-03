package com.netChain2.utils.gmailReader;

import java.util.Properties;

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

	public String readEmail(String userName, String password) {
		Properties props = System.getProperties();
		props.setProperty("mail.store.protocol", "imaps");
		String mailContent=null;
		Store store = null;
		Folder inbox = null;
		try {
			Session session = Session.getDefaultInstance(props, null);
			store = session.getStore("imaps");
			store.connect("imap.gmail.com", userName, password);
			System.out.println(store);

			inbox = store.getFolder("Inbox");
			inbox.open(Folder.READ_ONLY);
			Flags seen = new Flags(Flags.Flag.SEEN);
			FlagTerm unseenFlagTerm = new FlagTerm(seen, false);
			Message messages[] = inbox.search(unseenFlagTerm);

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
				
				
                if(store !=null) {
                	 store.close();
                 }
                 
                 
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			

		} catch (NoSuchProviderException e) {
			e.printStackTrace();
			
		} catch (MessagingException e) {
			e.printStackTrace();
		
		}
		
		return mailContent;

	}
	
}
