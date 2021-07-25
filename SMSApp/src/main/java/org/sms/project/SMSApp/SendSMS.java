package org.sms.project.SMSApp;

import java.util.Iterator;
import java.util.Set;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class SendSMS {

	public static void send(String twilioNumber, String twilioToken, String twilioSid, String number) {
	
		Twilio.init(twilioSid, twilioToken);
		
        Message message = Message.creator(
                new PhoneNumber("+91"+ number),
                new PhoneNumber(twilioNumber),
                "Test from JAVA app")
            .create();

        System.out.println( "Message SID: " + message.getSid() + ", For : "+"6379971782");
        
        
	}

	public static void send(String twilioNumber, String twilioToken, String twilioSid, Set<String> phoneNumbers) {
	
		Twilio.init(twilioSid, twilioToken);
		
		// get the iterator of the phoneNumber set
		Iterator<String> iter = phoneNumbers.iterator();
		int i = phoneNumbers.size();
		if (i > 5) {
			System.out.println("you cannot send message to more than 5 numbers. "
					+ "\nTotal phone numbers given: "+i);
			return;
		}
		
		while (iter.hasNext() && i < 5) {
			
			// send message to numbers one by one 
			String number = iter.next();
			
	        Message message = Message.creator(
	                new PhoneNumber("+91"+number),
	                new PhoneNumber(twilioNumber),
	                "Test from JAVA app")
	            .create();

	        System.out.println( "Message SID: " + message.getSid() + ", For : "+number);
//	        System.out.println( "Message sending For : "+number);
	        
			i++;
		}
        
        
	}
}
