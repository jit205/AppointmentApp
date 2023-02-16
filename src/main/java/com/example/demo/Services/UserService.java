package com.example.demo.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.logging.log4j.message.SimpleMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.mail.javamail.JavaMailSenderImpl;
//import org.springframework.stereotype.Service;
import org.springframework.stereotype.Service;

import com.example.demo.Model.Users;
import com.example.demo.Repositery.UserRepositery;

@Service
public class UserService {
	@Autowired
	UserRepositery ur;

	public List<Users> getAllUers() {
		List<Users> user = new ArrayList<Users>();
		ur.findAll().forEach(Users -> user.add(Users));
		return user;
	}

	public Users getUsersById(String id) {
		return ur.findById(id).get();
	}

	public Users SaveUser(Users u) {

		return ur.save(u);
	}

	public List<String> getAllUserName() {
		List<String> li = new ArrayList<String>();
		ur.findAll().forEach(Users -> li.add(Users.getUsername()));
		return li;
	}

	@Autowired
	public static int generatePin() throws Exception {
		Random generator = new Random();
		generator.setSeed(System.currentTimeMillis());

		int num = generator.nextInt(99999) + 99999;
		if (num < 100000 || num > 999999) {
			num = generator.nextInt(99999) + 99999;
			if (num < 100000 || num > 999999) {
				throw new Exception("Unable to generate PIN at this time..");
			}
		}
		return num;
	}

	@Autowired
	private JavaMailSender jm;

	public void sendEmail(String email, String body, String subject) {
		
//		int b = (int) Math.random() * 1000000;
//		body = Integer.toString(b);

		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("jittamsakhia02@gmail.com");
		message.setTo(email);
		message.setText(body);
		message.setSubject(subject);
		jm.send(message);
		System.out.print("mail is send");
	}
}
