package com.example.demo.Controller;

import java.util.Map;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Model.appointment.Appointment;
import com.example.demo.Model.appointment.DateTime;
import com.example.demo.Model.appointment.UpdateStatusRequest;
import com.example.demo.Model.service.Services;
import com.example.demo.Services.services.Servicesimpl;

import org.springframework.data.mongodb.core.query.*;

@RequestMapping("/appointment")
@RestController
@CrossOrigin(origins = "*")
public class AppointmentController {

	@Autowired
	private MongoTemplate mongoTemplate;

	@Autowired
	private Servicesimpl si;

	Map<String, Object> res;

	Date d = new Date();

	@PostMapping("/post/{sid}")
	public Map<String, Object> saveAppointment(@PathVariable String sid, @RequestBody Appointment aa) {
		res = new HashMap<String, Object>();
		try {
			System.out.println(sid + " " + aa);
			if (aa != null) {
				Services s = si.getAllServicedata(sid);
				System.out.println(s.toString());
				// si.saveservice(s);
				List<Appointment> xa = s.getAppointmentList();
				int x = (int) (d.getTime() / 1000);
				String t = Integer.toString(x);
				String sx = sid + "_" + t;
				DateTime dt = new DateTime();
				dt.setDate(aa.getDateTime().getDate());
				dt.setTime(aa.getDateTime().getTime());
				Appointment a = new Appointment();
				a.setAID(sx);
				a.setName(aa.getName());
				a.setMessage(aa.getMessage());
				a.setEmail(aa.getEmail());
				a.setContactNumber(aa.getContactNumber());
				a.setDateTime(dt);
				a.setBookedOn(new Date().toString());
				xa.add(a);
				s.setAppointmentList(xa);
				si.saveservice(s);
				res.put("msg", "You have booked an Appoinment and your Appoinment Number is " + sx);
				res.put("id", sx);
			} else {
				res.put("msg", "Sorry Server Error ");
			}
		} catch (Exception e) {
			res.put("msg", "Error");
		}
		return res;
	}

	@GetMapping("/getsinglebookeddata/{aid}")
	public Map<String, Object> getbookdata(@PathVariable String aid) {
		res = new HashMap<String, Object>();
		try {
			Services s = si.getbyaid(aid);
			List<Appointment> li = s.getAppointmentList();
			Appointment aa = li.get(0);
			res.put("data", aa);
		} catch (Exception e) {
			res.put("data", "Error");
		}
		return res;
	}

	@PostMapping("/checkWhetherAppointmentAlredyBooked/{sid}")
	public Map<String, Object> check(@PathVariable String sid, @RequestBody Map<String, Object> bdy) {
		res = new HashMap<String, Object>();
		res.put("alredyBooked", false);
		try {
			Services s = si.getAllServicedata(sid);
			List<Appointment> li = s.getAppointmentList();
			for (Appointment x : li) {
				System.out.println(x.getContactNumber() + " " + (bdy.get("contactNumber")) + " "
						+ (si.getYYYYMMDD(x.getBookedOn())) + " " + (si.getYYYYMMDD((new Date().toString()))));
				if (x.getContactNumber().equals(bdy.get("contactNumber"))
						&& (si.getYYYYMMDD(x.getBookedOn())).equals(si.getYYYYMMDD((new Date().toString())))) {
					res.put("msg", "You have already booked Appoinment in last 24 hours");
					res.put("alredyBooked", true);
					break;
				}
			}
		} catch (Exception e) {
			res.put("msg", "Error");
		}
		return res;
	}

	@GetMapping("/getremainingtimeslotes/{sid}")
	public Map<String, List<String>> getTime(@PathVariable String sid) {
		try {
			return si.getAllNonBookedSlotes(sid);
		} catch (Exception e) {
			return null;
		}
	}

	/******************************************************** */
	@GetMapping("/getallbookeddata/{sid}/{date}")
	public Map<String, Object> getallappionmetoftoday(@PathVariable String sid, @PathVariable String date) {
		res = new HashMap<String, Object>();
		try {
			Query query = new Query();
			query.addCriteria(Criteria.where("_id").is(sid)
					.and("appointmentList.dateTime.date").is(date));
			List<Services> ss = mongoTemplate.find(query, Services.class);
			// System.out.println(ss);
			List<Services> newss = new ArrayList<>();
			if (ss.size() == 0)
				res.put("data", ss);
			else {
				ss.forEach((item) -> {
					java.util.stream.Stream<Appointment> newApt = item.getAppointmentList().stream().filter((apt) -> {
						return apt.getDateTime().getDate().equals(date);
					});
					item.setAppointmentList(newApt.toList());
					newss.add(item);
				});
				Services s = newss.get(0);
				List<Appointment> li = s.getAppointmentList();
				res.put("data", li);
			}
		} catch (Exception e) {
			res.put("data", "Error");
		}
		return res;
	}

	@GetMapping("/getall/{sid}")
	public Services all(@PathVariable String sid) {
		return si.getAllServicedata(sid);
	}

	@PostMapping("/changeAppointmentStatus/{aid}")
	public String updatestatus(@PathVariable String aid, @RequestBody UpdateStatusRequest status) {
		try {
			System.out.println(status.getStatus());
			Query query = new Query(Criteria.where("appointmentList._id").is(aid));
			System.out.println(query);
			Update update = new Update().set("appointmentList.$.status", status.getStatus());
			mongoTemplate.updateFirst(query, update, Services.class);
			System.out.println(update);

			return "update status is done";
		} catch (Exception e) {
			return "exception";
		}

	}

	@GetMapping("/cancelAppointment/{aid}")
	public String updatestatus(@PathVariable String aid) {
		try {
			Query query = new Query(Criteria.where("appointmentList._id").is(aid));
			Update update = new Update().set("appointmentList.$.status", -1);
			mongoTemplate.updateFirst(query, update, Services.class);
			return "update status is done";
		} catch (Exception e) {
			return "exception";
		}

	}
}