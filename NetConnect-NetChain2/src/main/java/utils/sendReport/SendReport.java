package utils.sendReport;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import com.netChain2.utils.fileUtils.fileOperation.PropertyFileReader;
import com.netChain2.utils.javaMailClient.JavaMailClient;
import com.netChain2.utils.xsltTransformer.XSLTTransformer;

public class SendReport {
	
	
	public void SendTransformedEmail() {
		
		PropertyFileReader propReader = new PropertyFileReader(new File(".\\ConfigurationPropertyFile.properties"));
		String testngResult = propReader.getValue("testng-result");
		String xsltTransformer = propReader.getValue("xslt-transformer");
		String transformedHTML= propReader.getValue("html-transformed");
		
		XSLTTransformer xsltTrans = new XSLTTransformer();
		xsltTrans.xsltTransformer(testngResult, xsltTransformer, transformedHTML);
		String reportBody=null;
		BufferedReader buffReader=null;
		
		try {
			buffReader = new BufferedReader(new FileReader(new File(transformedHTML)));
			StringBuilder builder = new StringBuilder();
			String line;
			while((line=buffReader.readLine())!=null) {
				builder.append(line);
			}
			
			reportBody=builder.toString();
			
			} catch (IOException e) {
			e.printStackTrace();
		}
		
		if(buffReader !=null) {
			try {
				buffReader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		String mailHost = propReader.getValue("mail_host");
		String mailPort = propReader.getValue("mail_port");
		String user = propReader.getValue("sender_user");
		String password = propReader.getValue("sender_password");
		String[] recipientTo = propReader.getValue("recipient_to").split(",");
		
		Calendar grgCal = GregorianCalendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm a");
		String reportTime = dateFormat.format(grgCal.getTime());
		String mailSubject = "NetChain2 Automation Result - "+reportTime;
		
		JavaMailClient mailClient = new JavaMailClient(mailHost, mailPort, user, password, mailSubject, reportBody, user, recipientTo, null, null);
		mailClient.sendEmail();
	}
	
	public static void main(String args[]){
	new SendReport().SendTransformedEmail();
	}
	
}
