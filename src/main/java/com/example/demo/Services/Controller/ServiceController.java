package com.example.demo.Services.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Services.Model.Services;
import com.example.demo.Services.Service.Servicesimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

@RestController
public class ServiceController {
	@Autowired
	private MongoTemplate mongoTemplate;
	
	@Autowired
	private Servicesimpl si;

	@GetMapping("/s")
	public String start()
	{
		return "server is running";
	}
	@PostMapping("/Appointmentservice")
	public String setService(@RequestBody Services s)
	{
		Query query = new Query();
		query.addCriteria(Criteria.where("_id").is(s.getSID()));
		Services ss = mongoTemplate.findOne(query, Services.class);
		System.out.println("ss "+ss);
		if(ss  == null)
		{
			si.saveservice(s);
//			System.out.println(s.toString());
			return "new Services is added";
		}
		else
		{
			return "service is allready there";
		}
	}
	@GetMapping("/Allcategory/{category}")
	public List<Services> getCategory(@PathVariable String  category)
	{
		return si.getcategory(category);
	}
}
