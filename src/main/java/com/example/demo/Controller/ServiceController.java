package com.example.demo.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.*;

import com.example.demo.Model.appointment.Appointment;
import com.example.demo.Model.service.Services;
import com.example.demo.Services.services.Servicesimpl;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.*;

@RequestMapping("/service")
@RestController
@CrossOrigin(origins = "*")
public class ServiceController {

	@Autowired
	private MongoTemplate mongoTemplate;

	@Autowired
	private Servicesimpl si;
	Map<String, Object> res;

	@PostMapping("/postss")
	public Map<String, Object> setService(@RequestBody Services s) {
		res = new HashMap<String, Object>();
		System.out.println(s);
		try {
			Query query = new Query();
			query.addCriteria(Criteria.where("_id").is(s.getUsername() + "_" + s.getCategory()));
			Services ss = mongoTemplate.findOne(query, Services.class);
			System.out.println("ss " + ss);
			if (s.getSID() == null && ss == null) {
				List<Appointment> tmp = new ArrayList<>();
				s.setAppointmentList(tmp);
				si.saveservice(s);
				res.put("services", si.GetadminServicres(s.getUsername()));
			} else if (ss != null) {
				System.out.println(ss.getAppointmentList());
				s.setAppointmentList(ss.getAppointmentList());
				si.saveservice(s);
				res.put("services", si.GetadminServicres(s.getUsername()));
			} else {
				res.put("msg", "Service Alredy Exist");
			}
		} catch (Exception e) {
			res.put("msg", "Server error");
		}
		return res;
	}

	// @GetMapping("getAllcategory/{category}")
	// public List<Services> Allcategory(@PathVariable String category)
	// {
	// return si.GetBycategory(category);
	// }

	@GetMapping("/get/{username}")
	public List<Services> allUsername(@PathVariable String username) {
		try {
			return si.GetadminServicres(username);
		} catch (Exception e) {
			return null;
		}
	}

	@GetMapping("/getServDtOnBookPage/{sid}")
	public Services getServDtOnBookPage(@PathVariable String sid) {
		try {
			return si.getServiceData(sid);
		} catch (Exception e) {
			return null;
		}
	}

	@PostMapping("/getfiltereddata")
	public List<Services> getfilter(@RequestBody Map<String, String> qry) {
		try {
			String category = qry.get("Category"), city = qry.get("SelectCity"), pincode = qry.get("pincode");
			return si.filter(category, city, pincode);
		} catch (Exception e) {
			return null;
		}
	}
}