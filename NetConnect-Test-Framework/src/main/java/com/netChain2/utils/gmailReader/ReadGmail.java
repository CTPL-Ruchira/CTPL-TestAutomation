package com.netChain2.utils.gmailReader;

import java.io.IOException;
import java.util.Properties;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.search.FlagTerm;
import javax.mail.*;

public class ReadGmail {

	public String readEmail(String userName, String password) {
		Properties props = System.getProperties();
		props.setProperty("mail.store.protocol", "imaps");
		String mailContent=null;
		try {
			Session session = Session.getDefaultInstance(props, null);
			Store store = session.getStore("imaps");
			store.connect("imap.gmail.com", userName, password);
			System.out.println(store);

			Folder inbox = store.getFolder("Inbox");
			inbox.open(Folder.READ_ONLY);
			Flags seen = new Flags(Flags.Flag.SEEN);
			FlagTerm unseenFlagTerm = new FlagTerm(seen, false);
			Message messages[] = inbox.search(unseenFlagTerm);

			for (int readCount = 0; readCount <= 10; readCount++) {
				if (messages.length == 0 || !messages[0].getSubject().contains("Netchain2")) {
					messages = inbox.search(unseenFlagTerm);
					continue;
				}
				break;
			}

			try {
				mailContent = messages[0].getContent().toString();
			} catch (IOException e) {
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
