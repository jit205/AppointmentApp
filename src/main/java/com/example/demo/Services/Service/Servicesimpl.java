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

	@Autowired

	private AppointmentRepo ar;

	public Services saveservice(Services S) {

		// System.out.println(S) ;

		return sr.save(S);

	}

	// public List<Services> GetBycategory(String category)

	// {

	// return sr.findByCategory(category);

	// }

	public List<Services> GetadminServicres(String username) {

		return sr.findByUsername(username);

	}

	public Services getSid(String sid) {

		return sr.findBySID(sid);

	}

	public List<Services> filter(String c, String l, String p) {

		return sr.findByCategoryAndCityAndPincode(c, l, p);

	}

	public Appointment getAID(String aid) {

		return ar.findByAID(aid);

	}

	public Services getbyaid(String AID) {

		System.out.println("getbyaid = " + AID);

		return sr.hello(AID);

	}

	// public void updateDemo(Services s,String status) {

	//// System.out.println(status+"........................");

	// List<Appointment> x = s.getAppoinmentList();

	// x.get(0).setStatus(status);

	// s.setAppoinmentList(x);

	// sr.save(s);

	// }

	public Services gettodayappionment(String a, String b) {

		System.out.println("hello");

		return sr.getallbookappionmet(a, b);

	}

	public Services getalldata(String SID) {

		return sr.all(SID);

	}

}