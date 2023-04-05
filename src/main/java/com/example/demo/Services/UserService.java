package com.example.demo.Services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import org.springframework.stereotype.Service;

import com.example.demo.Model.Users;
import com.example.demo.Repositery.AuthRepositery;

import org.springframework.data.mongodb.core.MongoTemplate;

@Service
public class UserService {
	@Autowired
	AuthRepositery ur;

	@Autowired
	private MongoTemplate mongoTemplate;

//	public List<Users> getAllUers() {
//		
//		List<Users> user = new ArrayList<Users>();
//		ur.findAll().forEach(Users -> user.add(Users));
//		ur.findAll();
//		return user;
//	}

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
	private JavaMailSender jm;

	public void sendEmail(String email, String body, String subject) {

		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("jittamsakhia02@gmail.com");
		message.setTo(email);
		message.setText(body);
		message.setSubject(subject);
		jm.send(message);
		System.out.print("mail is send");
	}

	public String getForgetpassword(String email, String password) {
		Query query = new Query();
		query.addCriteria(Criteria.where("email").is(email));

		Users u = mongoTemplate.findOne(query, Users.class);
		if (u != null) {

			Update update = new Update();
			update.set("password", password);

			mongoTemplate.updateFirst(query, update, Users.class);

			return "password is changed";
		}
		return "email is not valid";
	}
//	public String  upade(String VaribleType ,String updateName ,String username)
//	{
//		Query query = new Query();
//		query.addCriteria(Criteria.where("username").is(username));
//
//		Users u = mongoTemplate.findOne(query, Users.class);
//		Update update = new Update();
//		if(u!=null)
//		{
//			update.set(VaribleType,updateName );
//
//			mongoTemplate.updateFirst(query, update, Users.class);
//			return "update is done in"+VaribleType;
//		}
//		else {
//			return "not valid users/not valid User";
//		}
//		
//	}
//	
}
