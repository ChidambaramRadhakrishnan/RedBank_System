package UtilityOperations.Operations;

import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Properties;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import java.util.Properties;

import jakarta.servlet.http.HttpServletRequest;



public class Operations {
	
	static String OTP="";
	
	/*
	 * This is for generate 8 digit random number
	 */
	public static String accountNumberGenerator() {
		
		String reference = "0123456789";
        StringBuilder sb = new StringBuilder();
        Random random = new Random(); // 
        while(sb.length()<8){ // 8 times execute
            int index = (int) (random.nextFloat() * reference.length());  // (0.0 * 0-9 )random will generate float number with reference of 0-9. and type cast to int.
            sb.append(reference.charAt(index)); // generated numbers will append(add) into string builder 
        }
        String randomNum = sb.toString();  // index is a integer, here we converted into String
        return randomNum;
        
	}
	
	
	/*
	 * Pattern matcher
	 */
	public static Boolean ValidateInputValue(String InputText,String pattern) {
		
		boolean status = false;
		try{
			Matcher pat = Pattern.compile(pattern).matcher(InputText);

	        while (pat.find()){
	            status = true;
	        }
	        
		}catch (NumberFormatException e) {
			status = false;
		}
		
        return status;
	}
	
	
	/*
	 * This method is convert number into IndianRupee
	 * used Numberformat Class
	 */
	public static String IndianCurrency(String Number) {
		
		NumberFormat Rupee = NumberFormat.getCurrencyInstance(new Locale("en", "IN"));
		
		int InputNumber = Integer.parseInt(Number);
			
		String INR = Rupee.format(InputNumber);
		
		return INR;
	}
	
	
	/*
	 * This method convert String to int
	 */
	public static int GetInteger(String Text) {
		
		int outputInteger = Integer.parseInt(Text);
		
		return outputInteger;
	}
	
	/*
	 * 
	 */
	public static String getCurrentTime() {
		
		LocalDateTime localTime = LocalDateTime.now();
		
		DateTimeFormatter formattedTime = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss a");
		
		String LocalTimeDate =localTime.format(formattedTime);
		
		return LocalTimeDate;
	}
	
	/*
	 * 
	 * 
	 */
	public static String getOTP() {
		String referenceNumber = "1234567890";
        StringBuilder stringBuilder = new StringBuilder();
        Random random = new Random();
        while(stringBuilder.length()<4){
            int RandomNumber = (int) (random.nextFloat() * referenceNumber.length()); // (0.5 * 9) == 4.5  = 4
            stringBuilder.append(referenceNumber.charAt(RandomNumber));
        }
        String OTP =stringBuilder.toString();

        return OTP;
	}
	
	
	
	/*
	 * 
	 * 
	 */
	public static String SentAccountDetails(String Email,String accountNo, String password) {
		
		Properties props = new Properties();

        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable","true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("chidambaramrd333@gmail.com","memh ysdm ocgm yhms");
            }
        });
		
		try{

            Message msg = new MimeMessage(session);

            msg.setFrom(new InternetAddress("chidambaramrd333@gmail.com","RedBank Mail"));

            msg.addRecipient(Message.RecipientType.TO, new InternetAddress(String.format("%s", Email)));

            msg.setSubject("Welcome to RedBank");
            
            MimeBodyPart htmlPart = new MimeBodyPart();
            htmlPart.setContent("<h1>Your Account has been created in RedBank.</h1>", "text/html");

            // Create the account number part
            MimeBodyPart accountPart = new MimeBodyPart();
            accountPart.setContent(String.format("<h3> Account Number is : %s </h3>", accountNo),"text/html");

            // Create the plain text part
            MimeBodyPart textPart = new MimeBodyPart();
            textPart.setContent(String.format("<h3> Password is : %s </h3>", password),"text/html");
            
         // Create the plain text part
            MimeBodyPart text2 = new MimeBodyPart();
            text2.setContent("<h3> Login your acconut with this credentials.</h3>","text/html");
            
            MimeBodyPart time = new MimeBodyPart();
            time.setContent(String.format("<h3> Time & Date : %s </h3>",Operations.getCurrentTime()),"text/html");
            
         // Create the plain text part
            MimeBodyPart text3 = new MimeBodyPart();
            text3.setContent("<h3>Do not share your account password.</h3>","text/html");
            
            // Create the plain text part
            MimeBodyPart text4 = new MimeBodyPart();
            text4.setContent("<h1>Welcome to Redbank. Do Banking.</h1>","text/html");
            
            

            // Combine the parts into a multipart
            
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(htmlPart);
            multipart.addBodyPart(accountPart);
            multipart.addBodyPart(textPart);
            multipart.addBodyPart(time);
            multipart.addBodyPart(text2);
            multipart.addBodyPart(text3);
            multipart.addBodyPart(text4);
            

            msg.setContent(multipart);

            new Thread(new Runnable() {
				
				@Override
				public void run() {
					 try {
						Transport.send(msg);
					} catch (MessagingException e) {
						e.printStackTrace();
					}
				}
			}).start();

        }catch (Exception e){
            e.printStackTrace();
        }
		
		return OTP; 
		
	}
	
	/*
	 * 
	 * 
	 */
	public static String SendFundTransferMail(String Email,String From, String To,String Amount) {
		
		Properties props = new Properties();

        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable","true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("chidambaramrd333@gmail.com","memh ysdm ocgm yhms");
            }
        });
		
		try{

            Message msg = new MimeMessage(session);

            msg.setFrom(new InternetAddress("chidambaramrd333@gmail.com","RedBank Mail"));

            msg.addRecipient(Message.RecipientType.TO, new InternetAddress(String.format("%s", Email)));

            msg.setSubject("RedBank Alert");
            
            MimeBodyPart htmlPart = new MimeBodyPart();
            htmlPart.setContent("<h1>U made transaction on your account.</h1>", "text/html");

            // Create the from part
            MimeBodyPart accountPart = new MimeBodyPart();
            accountPart.setContent(String.format("<h3> From : %s </h3>", From),"text/html");

            // Create the plain text part
            MimeBodyPart textPart = new MimeBodyPart();
            textPart.setContent(String.format("<h3> To  : %s </h3>", To),"text/html");
            
         // Create the plain text part
            MimeBodyPart text2 = new MimeBodyPart();
            text2.setContent(String.format("<h3> Amount %s.</h3>",Amount),"text/html");
            
            MimeBodyPart time = new MimeBodyPart();
            time.setContent(String.format("<h3> Transaction Time & Date : %s </h3>",Operations.getCurrentTime()),"text/html");
            
         // Create the plain text part
            MimeBodyPart text3 = new MimeBodyPart();
            text3.setContent("<h3>If that's was not you. Visit your branch immediately.</h3>","text/html");
            
            // Create the plain text part
            MimeBodyPart text4 = new MimeBodyPart();
            text4.setContent("<h1>Thank You Banking. Do Banking.</h1>","text/html");
            
            

            // Combine the parts into a multipart
            
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(htmlPart);
            multipart.addBodyPart(accountPart);
            multipart.addBodyPart(textPart);
            multipart.addBodyPart(text2);
            multipart.addBodyPart(text3);
            multipart.addBodyPart(time);
            multipart.addBodyPart(text4);
            

            msg.setContent(multipart);
            
            new Thread(new Runnable() {
				
				@Override
				public void run() {
					 try {
						Transport.send(msg);
					} catch (MessagingException e) {
						e.printStackTrace();
					}
				}
			}).start();

        }catch (Exception e){
            e.printStackTrace();
        }
		
		return OTP; 
		
	}
	
	
	/*
	 * 
	 * 
	 */
	public static String SendDepositMail(String Email,String From,String Amount) {
		
		Properties props = new Properties();

        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable","true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("chidambaramrd333@gmail.com","memh ysdm ocgm yhms");
            }
        });
		
		try{

            Message msg = new MimeMessage(session);

            msg.setFrom(new InternetAddress("chidambaramrd333@gmail.com","RedBank Mail"));

            msg.addRecipient(Message.RecipientType.TO, new InternetAddress(String.format("%s", Email)));

            msg.setSubject("RedBank Alert");
            
            MimeBodyPart htmlPart = new MimeBodyPart();
            htmlPart.setContent("<h1>Money Deposited into your account.</h1>", "text/html");
            
         // Create the plain text part
            MimeBodyPart text3 = new MimeBodyPart();
            text3.setContent(String.format("<h3> Deposit Time & Date : %s </h3>",Operations.getCurrentTime()),"text/html");

            // Create the deposit part
            MimeBodyPart accountPart = new MimeBodyPart();
            accountPart.setContent(String.format("<h3> Self Deposit : %s </h3>", From),"text/html");

            
         // Create the plain text part
            MimeBodyPart text2 = new MimeBodyPart();
            text2.setContent(String.format("<h3> Amount %s.</h3>",Amount),"text/html");
            
         
            
            // Create the plain text part
            MimeBodyPart text4 = new MimeBodyPart();
            text4.setContent("<h1>Thank You Banking. Do Banking.</h1>","text/html");
            
            

            // Combine the parts into a multipart
            
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(htmlPart);
            multipart.addBodyPart(accountPart);
            multipart.addBodyPart(text2);
            multipart.addBodyPart(text3);
            multipart.addBodyPart(text4);
            

            msg.setContent(multipart);

            new Thread(new Runnable() {
				
				@Override
				public void run() {
					 try {
						Transport.send(msg);
					} catch (MessagingException e) {
						e.printStackTrace();
					}
				}
			}).start();

        }catch (Exception e){
            e.printStackTrace();
        }
		
		return OTP; 
		
	}
	
}
