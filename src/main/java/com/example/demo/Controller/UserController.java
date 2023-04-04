package com.example.demo.Controller;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.Model.otp;
import com.example.demo.Model.Users;
import com.example.demo.Services.UserService;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/auth")
public class UserController {

	Map<String, Object> res;
	@Autowired
	UserService us;

	@Autowired
	private MongoTemplate mongoTemplate;

	@PostMapping("/register")
	private Map<String, Object> saveUsers(@ModelAttribute Users u) {
		res = new HashMap<String, Object>();
		try {
			Query query = new Query();
			query.addCriteria(Criteria.where("_id").is(u.getUsername()));
			Users user = mongoTemplate.findOne(query, Users.class);
			if (user == null) {
				us.SaveUser(u);
				res.put("msg", "Registered Successfully");
			} else {
				res.put("msg", "User Already Exist");
			}
		} catch (Exception e) {
			res.put("msg", "Server error");
		}
		return res;
	}

	@GetMapping("/usernames")
	private List<String> getAllUserName() {
		try {
			return us.getAllUserName();
		} catch (Exception e) {
			return null;
		}
	}

	@PostMapping("/login")
	private Map<String, Object> getValid(@RequestBody Map<String, String> params) {
		res = new HashMap<String, Object>();
		try {
			String email = params.get("email");
			String password = params.get("password");
			System.out.println(email);
			Query query = new Query();
			if (email.contains("@"))
				query.addCriteria(Criteria.where("email").is(email));
			else
				query.addCriteria(Criteria.where("_id").is(email));

			Users u = mongoTemplate.findOne(query, Users.class);
			// System.out.println(email + " " + u);
			if (u == null) {
				res.put("exist", false);
				res.put("mess", "user doesn't exist !");
			} else if (u.getPassword().equals(password)) {
				res.put("user", u);
				res.put("exist", true);
				res.put("token", "token");
			} else {
				res.put("exist", false);
				res.put("mess", "Invalid Credentials");
			}
			return res;
		} catch (Exception e) {
			res.put("exist", false);
			res.put("mess", "Server Error");
		}
		return res;
	}

	@PostMapping("/changepass")
	private Map<String, Object> forgotpassword(@RequestBody Map<String, String> params) {
		res = new HashMap<String, Object>();
		try {
			res.put("msg", us.getForgetpassword(params.get("email"), params.get("password")));
		} catch (Exception e) {
			res.put("msg", "Server Error");
		}
		return res;
	}

	@GetMapping("/UpadeUser")
	private String UpdateUser(@RequestBody Users u) {
		try {
			us.SaveUser(u);
			return "update data";
		} catch (Exception e) {
			return "failed to update user";
		}
	}

	@GetMapping("Userid/{id}")
	private Users getUser(@PathVariable("id") String id) {
		return us.getUsersById(id);
	}

	/*********************************************
	 * image
	 *******************************/

	// private ImageRepository ir;

	// @Autowired
	// private MongoTemplate mongoTemplate;

	// @PostMapping("/save")
	// public String uploadImage(@RequestParam("file") MultipartFile file) throws
	// IOException {
	// ImageData image = new ImageData();
	// image.setName(file.getOriginalFilename());
	// image.setContentType(file.getContentType());
	// image.setData(file.getBytes());
	// ir.save(image);
	// return "Image uploaded successfully";
	// }

	@GetMapping("/retrive/{id}")
	public ResponseEntity<byte[]> getimage(@PathVariable("id") String id) {
		System.out.println(id);
		Query query = new Query();
		query.addCriteria(Criteria.where("_id").is(id));
		Users i = mongoTemplate.findOne(query, Users.class);
		System.out.println(i.getImage().getData());
		byte[] newdata = i.getImage().getData();
		org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
		headers.setContentType(MediaType.IMAGE_PNG);

		return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(newdata);
	}
}
