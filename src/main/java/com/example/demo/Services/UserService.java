package com.example.demo.Services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
		List<String> li=new ArrayList<String>();
		ur.findAll().forEach(Users -> li.add(Users.getUsername()));
		return li;
	}
	public Users validUser(String email)
	{
		
		return ur.findById(email).get();
		
	}
	public Users ValidPass(String pass)
	{
		return ur.findById(pass).get();
	}

}
