package com.example.demo.Services.Service;

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
		System.out.println(S)	;
		return sr.save(S);
	}
		

}	
