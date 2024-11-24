package com.nt.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nt.Beans.ShopingItems;
import com.nt.Service.MinSender;



@RestController
public class MailsenderController {
    
	@Autowired
   private MinSender mail;	
	
	
	@PostMapping("/send")
	public String SendMailtoCustomer(@RequestBody ShopingItems shopingItems ) throws Exception {
		
		String[] items = shopingItems.getItems();
		Double[] price = shopingItems.getPrice();
		String[] mails = shopingItems.getMails();
		boolean shooping = mail.Shooping(items, price, mails);
		
		if (shooping) {
			return "Shooping Success pls check mail ::";
		} else {
			return " Faild:  pls try again:: ";
		}
		
	}
	
	
}
