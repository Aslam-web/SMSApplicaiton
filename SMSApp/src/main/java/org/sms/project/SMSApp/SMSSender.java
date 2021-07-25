package org.sms.project.SMSApp;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.StringJoiner;

public class SMSSender {
	
	private static final String link = "https://www.fast2sms.com/dev/bulkV2?"
			+ "authorization=MnAoOe265Xl8uNxSGpCDjUTPYFZ7IK4s1BLfE30RWVwychkt9gtjbI59lW8FVMKRhDYX0GuxHa3JeonB"
			+ "&route=v3"
			+ "&sender_id=TXTIND"
			+ "&message=";
	
	private static final String balanceLink = "https://www.fast2sms.com/dev/wallet?"
			+ "authorization=MnAoOe265Xl8uNxSGpCDjUTPYFZ7IK4s1BLfE30RWVwychkt9gtjbI59lW8FVMKRhDYX0GuxHa3JeonB";
	
	private static final HttpClient httpClient = HttpClient.newHttpClient();
	
	public static void send() {
		
		System.out.println("Processing ...");

		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the numbers (tap enter twice to exit)");
		
		StringJoiner numbers = new StringJoiner(",");
		String s=sc.nextLine();
		while (!s.equals("")) {
			numbers.add(s);
			s=sc.nextLine();
		}
		
		System.out.println("What would you like to send ? ");
		String messageString = sc.nextLine();
		String message = URLEncoder.encode(messageString, StandardCharsets.UTF_8);
		System.out.println("\nSending message ...");
		
		try {

			HttpRequest req = HttpRequest.newBuilder()
					.GET()
					.uri(URI.create(link+message+"&numbers="+numbers))
					.build();
			
			HttpResponse<String> res = httpClient.send(req, HttpResponse.BodyHandlers.ofString());
			

			System.out.println("------------------------------------------------"
					+ "\nMessagesSent successfully to : "+numbers
					+ "\nSent Message : " + messageString
					+ "\nResponse from the Server : ");
			System.out.println(res.body());
			System.out.println("------------------------------------------------");
			
		} catch (Exception e) {
			System.out.println("Error - " + e);
		}
		
		
		sc.close();
	}

	public static void checkBalance() {
		try {

			HttpRequest req = HttpRequest.newBuilder()
					.GET()
					.uri(URI.create(balanceLink))
					.build();
			
			HttpResponse<String> res = httpClient.send(req, HttpResponse.BodyHandlers.ofString());
			
			System.out.print("Balance is : $");
			System.out.println(res.body().substring(25, res.body().toString().lastIndexOf("\"")));
			
		} catch (Exception e) {
			System.out.println("Error - " + e);
		}
	}
}