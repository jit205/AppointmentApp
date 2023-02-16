package com.example.demo.Controller;

import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Model.Users;
import com.example.demo.Services.UserService;

@RestController
public class UserController {
	@Autowired
	UserService us;

	@GetMapping("/")
	public String hello() {
		return "Server Running";
	}

//	@GetMapping("/auth/usernames")
//	private List<Users> getAllUser() {
//	
//		return us.getAllUers();
//	}

	@GetMapping("Userid/{id}")
	private Users getUser(@PathVariable("id") String id) {
		return us.getUsersById(id);
	}

	@PostMapping("/auth/register")
	private String saveUsers(@RequestBody Users u) {
		us.SaveUser(u);
		return "SAVE SUCCESFULLY ";
	}
	@GetMapping("/auth/usernames")
	private List<String> getAllUserName()
	{
		return us.getAllUserName();
	}
	@GetMapping("/ValidUser")
	private String getValid(@RequestBody Map<String, String> params)
	{
		String email = params.get("email");
	    String password = params.get("password");
	
	   
		List<Users> u=us.getAllUers();
		for (Users x : u) {
			
			if(email.equals(x.getEmail())&&password.equals(x.getPassword()))
			{
				return "yes login is done";
			}
		
		}
		return "no login";
	}
//	@GetMapping("/forgetpass")
//	private String ForgetPass(String email)
//	{
//		
//	}
	@PostMapping("/mail")
	
	public String sendmail()
	{
		   int b = new Random().nextInt(900000) + 100000;// 000010
		String body = Integer.toString(b);
//		if(body.length()!=6) body= (6- body.length()) * "0"+body;
		us.sendEmail("dhruv20345@gmail.com", body, "This is subject");
		return "email is send";
	}
	
}
