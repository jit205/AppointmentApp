package com.example.demo.Controller;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.Model.Users;
import com.example.demo.Services.UserService;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/auth")
public class AuthController {

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
			query.addCriteria(new Criteria().orOperator(Criteria.where("_id").is(u.getUsername()),
					Criteria.where("email").is(u.getEmail())));
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

	@PostMapping("/update/{UID}")
	private Map<String, Object> UpdateUser(@ModelAttribute Users u, @PathVariable("UID") String UID) {
		res = new HashMap<String, Object>();
		try {
			Query query = new Query();
			query.addCriteria(Criteria.where("_id").is(UID));
			Users user = mongoTemplate.findOne(query, Users.class);

			Query queryE = new Query();
			queryE.addCriteria(Criteria.where("email").is(u.getEmail()));
			Users userE = mongoTemplate.findOne(queryE, Users.class);
			System.out.println(user + " -> " + userE + " -> " + UID+" -> " + u);
			if (u.getEmail().equals(user.getEmail()) && userE == null) {
				res.put("msg", "Email alredy used !");
			} else {
				us.SaveUser(u);
				res.put("user", u);
			}
		} catch (Exception e) {
			res.put("msg", "failed to update user");
		}
		return res;
	}
}

// try {
// if (user == null) {
// us.SaveUser(u);
// res.put("msg", "Registered Successfully");
// } else {
// res.put("msg", "User Already Exist");
// }
// } catch (Exception e) {
// res.put("msg", "Server error");
// }
// return res