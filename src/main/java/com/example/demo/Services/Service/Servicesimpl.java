package com.example.demo.Services.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Services.Model.Services;
import com.example.demo.Services.Repositery.ServicesRepo;

@Service
public class Servicesimpl {
	@Autowired
	private ServicesRepo sr;
	public Services saveservice(Services S)
	{
		String s=S.getUsername()+"_"+S.getCategory();
		S.setSID(s);
		return sr.save(S);
	}
	public List<Services> getcategory(String category)
	{
		return sr.findByCategory(category);
	}
		

}	
