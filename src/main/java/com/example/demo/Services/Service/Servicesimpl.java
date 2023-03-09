package com.example.demo.Services.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Services.Model.Appointment;
import com.example.demo.Services.Model.Services;
import com.example.demo.Services.Repositery.AppointmentRepo;
import com.example.demo.Services.Repositery.ServicesRepo;

@Service

public class Servicesimpl {
	@Autowired
	private ServicesRepo sr;
	private AppointmentRepo ar;
	public Services saveservice(Services S)
	{
		
//		System.out.println(S)	;
		return sr.save(S);
	}
//		public List<Services> GetBycategory(String category)
//		{
//			return sr.findByCategory(category);
//		}
		public List<Services> GetadminServicres(String username)
		{
			return sr.findByUsername(username);
		}
		public Services getSid(String sid)
		{
			return sr.findBySID(sid);
		}
		public List<Services> filter(String c,String l,String p)
		{
			
			return sr.findByCategoryAndCityAndPincode(c, l, p);
		}
		public Appointment getAID(String aid)
		{
			System.out.println("enter in AID");
			return ar.findByAID(aid);
		}
}	
