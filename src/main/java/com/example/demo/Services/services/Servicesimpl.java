package com.example.demo.Services.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Model.appointment.Appointment;
import com.example.demo.Model.appointment.DateTime;
import com.example.demo.Model.service.Services;
import com.example.demo.Repositery.ServicesRepo;

@Service
public class Servicesimpl {

	@Autowired
	private ServicesRepo sr;


	public Services saveservice(Services S) {
		S.setSID(S.getUsername() + "_" + S.getCategory());
		return sr.save(S);
	}

	public List<Services> GetadminServicres(String username) {
		return sr.findByUsername(username);
	}

	public Services getServiceData(String sid) {
		return sr.getServiceData(sid);
	}

	public List<Services> filter(String category, String city, String pincode) {
		if (city == "")
			return sr.findByCategoryAndPincode(category, pincode);
		if (pincode == "")
			return sr.findByCategoryAndCity(category, city);
		return sr.findByCategoryAndCityAndPincode(category, city, pincode);
	}


	public Services getbyaid(String aid) {
		System.out.println("getbyaid = " + aid);
		return sr.hello(aid);
	}

	public Services gettodayappionment(String a, String b) {
		// System.out.println("hello");
		return sr.getallbookappionmet(a, b);
	}

	public Services getAllServicedata(String sid) {
		return sr.all(sid);
	}

	public Map<String, List<String>> getAllNonBookedSlotes(String sid) {
		Map<String, List<String>> bookedTimeSlote = new HashMap<String, List<String>>();
		Services s = sr.getAppointmentList(sid);
		List<String> li;
		for (Appointment appointment : s.getAppointmentList()) {
			DateTime dt = appointment.getDateTime();
			try {
				bookedTimeSlote.get(dt.getDate()).add(dt.getTime());
			} catch (Exception e) {
				li = new ArrayList<>();
				li.add(dt.getTime());
				bookedTimeSlote.put(dt.getDate(), li);
			}
		}
		return new TimeArray().createTimeArray(
				Integer.parseInt(s.getAppoinmentTime()),
				s.getServiceTime().getStart(),
				s.getServiceTime().getEnd(),
				s.getBreakTime().getStart(),
				s.getBreakTime().getEnd(),
				bookedTimeSlote);
	}

	public String getYYYYMMDD(String date) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
		Date dt = formatter.parse(date);
		SimpleDateFormat newFormatter = new SimpleDateFormat("yyyy-MM-dd");
		return newFormatter.format(dt);
	}
}