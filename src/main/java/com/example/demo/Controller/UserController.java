package com.example.demo.Controller;


import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Model.otp;

//import com.example.demo.Model.UserUpdate;
import com.example.demo.Model.Users;
import com.example.demo.Services.UserService;


import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@RestController
public class UserController {
	@Autowired
	UserService us;

	@Autowired
	private MongoTemplate mongoTemplate;

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
//	@PostMapping("/auth/register")
//	private String saveUsers(@ModelAttribute("Users") Users u) {
////		Query query = new Query();
////		query.addCriteria(Criteria.where("email").is(u.getEmail()));
////		Users user = mongoTemplate.findOne(query, Users.class);
////		if (user == null) {
////			us.SaveUser(u);
////			return "SAVE SUCCESFULLY";
////		} else {
////			return "User Already Exist";
////		}
//		System.out.println(u);
//		return null;
//	}
	
	@PostMapping("/auth/register")
	private String saveUsers(@ModelAttribute Users u) {
//		Query query = new Query();
//		query.addCriteria(Criteria.where("username").is(u.getUsername()));
//		Users user = mongoTemplate.findOne(query, Users.class);
		if (u != null) {

			us.SaveUser(u);
			System.out.println("Data= "+u.getImage().getData());
			return "SAVE SUCCESFULLY";
		} else {
			return "User Already Exist";
		}
	}
	

	@GetMapping("/auth/usernames")
	private List<String> getAllUserName() {
		return us.getAllUserName();
	}

	@GetMapping("/ValidUser")
	private Users getValid(@RequestBody Map<String, String> params) {
		String email = params.get("email");
		String password = params.get("password");

		Query query = new Query();
		query.addCriteria(Criteria.where("email").is(email));

		Users u = mongoTemplate.findOne(query, Users.class);

		if (u == null) {
			return null;
		} else if (u.getPassword().equals(password)) {
			return u;
		}

		return null;
	}

	String otp;

	@PostMapping("/mail")
	public String sendmail() {
		int b = new Random().nextInt(900000) + 100000;
		String body = Integer.toString(b);
		otp = body;
		System.out.print(body);

		us.sendEmail("dhruv20345@gmail.com", body, "This is subject");
		return "email is send ";
	}

	@GetMapping("/otp")
	public Boolean checkOtp(@RequestBody otp u) {

		
		if (otp.equals(u.getUotp()))
			return true;
		else
			return false;
	}

	@PostMapping("/forgotPassword")
	private String forgotpassword(@RequestBody Map<String, String> params) {
		return us.getForgetpassword(params.get("email"),params.get("password"));
	}
	@GetMapping("/UpadeUser")
	private String UpdateUser(@RequestBody Users u)
	{
		
//		 us.SaveUser(u);
		 
		 return"update data";
		
	}
	
/*********************************************image*******************************/	
	
//		private ImageRepository ir;

//		@Autowired
//		private MongoTemplate mongoTemplate;

//		@PostMapping("/save")
//		public String uploadImage(@RequestParam("file") MultipartFile file) throws IOException {
//			ImageData image = new ImageData();
//			image.setName(file.getOriginalFilename());
//			image.setContentType(file.getContentType());
//			image.setData(file.getBytes());
//			ir.save(image);
//			return "Image uploaded successfully";
//		}



		@GetMapping("/retrive/{id}")
		public ResponseEntity<byte[]> getimage(@PathVariable("id") String id) {
			System.out.println(id);
			Query query = new Query();
			query.addCriteria(Criteria.where("_id").is(id));
			Users i = mongoTemplate.findOne(query, Users.class);
			byte[] newdata =i.getImage().getData();
			org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
			headers.setContentType(MediaType.IMAGE_PNG);
//			System.out.println(i);
//			return null;
			return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(newdata);
//			return new ResponseEntity<>(newdata, headers,));
		}
}
