package com.example.demo.Services.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Services.Model.Services;
import com.example.demo.Services.Service.Servicesimpl;

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
	@GetMapping("/Appointmentservice")
	public String setSetvice(@ModelAttribute Services s)
	{
		Query query = new Query();
		query.addCriteria(Criteria.where("_id").is(s.getSID()));
		Services ss = mongoTemplate.findOne(query, Services.class);
		if(ss==null)
		{
			si.save(ss);
			return "new Services is added ";
		}
		else
		{
			return "service is allready there ";
		}
	}
}
