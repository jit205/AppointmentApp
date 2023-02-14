package com.example.demo.Controller;

import java.util.List;

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
	@GetMapping("ValidUser")
	private String getValid(@PathVariable String email,@PathVariable String pass)
	{
		Users u=us.validUser(email);
		if(u!=null)
		{
			String e=u.getEmail();
			String p=u.getPassword();
			Users uu=us.ValidPass(pass);
			String pp =uu.getPassword();
			String  ee=uu.getEmail();
			
			if(e.equals(ee)&&p.equals(pp))
			{
				return"yes valid user";
			}
		
		}
		return "not valid user";
	}
}
