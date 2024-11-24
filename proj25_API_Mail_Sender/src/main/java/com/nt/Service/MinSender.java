package com.nt.Service;

import java.util.Arrays;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.internet.MimeMessage;

@Service
public class MinSender{
	
    @Autowired
   private JavaMailSender sender;
	@Value("${spring.mail.username}")
    private String fromto;	
    
    public boolean Shooping(String items[], Double price[] , String sendTo[]) throws Exception {
    	
    	Double totalprice=null;
    	for(Double total :price)
    		totalprice=+total;
    	
    	String msg="Thanks for purchesing "+ Arrays.toString(items)+ "items price "+ Arrays.toString(price)+ "total price is"
    			+ totalprice;
    	
    	if (sendMail(msg,sendTo)) {
			
    		return true;
		} else {

	    	return false;
		}
    	
    }
    
	public Boolean sendMail( String msg , String[] mails) throws Exception  {
		try {
			MimeMessage mime = sender.createMimeMessage();  // creating Empty mail
            
			MimeMessageHelper mimeHelper = new MimeMessageHelper(mime, true);
			mime.setFrom(fromto);
			mimeHelper.setSubject("Bill Amount :");
			mimeHelper.setCc(mails);
			mimeHelper.setText(msg);
			mimeHelper.setSentDate(new Date());
	        mimeHelper.addAttachment("thanks.jpeg", new ClassPathResource("thanks.jpeg"));
			sender.send(mime);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	
	}
	
}
