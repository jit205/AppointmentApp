package com.example.demo.Services.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.*;
import com.example.demo.Services.Model.*;
import com.example.demo.Services.Service.Servicesimpl;
import com.mongodb.client.result.UpdateResult;
import java.text.SimpleDateFormat;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.*;

@RestController
public class ServiceController {

	@Autowired
	private MongoTemplate mongoTemplate;

	@Autowired
	private Servicesimpl si;

	@GetMapping("/s")
	public String start() {
		return "server is running";
	}

	@PostMapping("/Appointmentservice")
	public String setService(@RequestBody Services s) {
		Query query = new Query();
		query.addCriteria(Criteria.where("_id").is(s.getSID()));
		Services ss = mongoTemplate.findOne(query, Services.class);
		// System.out.println("ss "+ss);
		if (ss == null) {
			si.saveservice(s);
			System.out.println(s.toString());
			return "new Services is added ";
		} else {
			return "service is allready there ";
		}
	}

	// @GetMapping("getAllcategory/{category}")
	// public List<Services> Allcategory(@PathVariable String category)
	// {
	// return si.GetBycategory(category);
	// }

	@GetMapping("servicesusername/{username}")
	public List<Services> Allusrname(@PathVariable String username) {
		return si.GetadminServicres(username);
	}

	/*************************************************************/
	Date d = new Date();

	@PostMapping("getSID/{SID}")
	public Services getSID(@PathVariable String SID, @RequestBody Appointment aa) {
		Services s = si.getSid(SID);
		System.out.println(s.toString());
		// si.saveservice(s);
		List<Appointment> xa = s.getAppoinmentList();
		int x = (int) (d.getTime() / 1000);
		String t = Integer.toString(x);
		String sx = SID + "_" + t;
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
		// a.setStatus(aa.getStatus());
		xa.add(a);
		s.setAppoinmentList(xa);
		return si.saveservice(s);
	}
	// @PostMapping("addAppoimentlist")
	// public Services addappoiment(@RequestBody Services s)
	// {
	// Services x=getSID();
	// return ss;
	// }

	@GetMapping("filter/{category}/{city}/{pincode}")
	public List<Services> getfilter(@PathVariable String category, @PathVariable String city,
			@PathVariable String pincode) {
		return si.filter(category, city, pincode);
	}

	@PostMapping("updateStatus/{AID}")
	public UpdateResult updatestatus(@PathVariable String AID, @RequestBody UpdateStatusRequest aa) {
		System.out.println(AID);
		String[] r = AID.split("_");
		String sid = r[0] + "_" + r[1];
		System.out.println(sid);
		Query query = new Query();
		query.addCriteria(Criteria.where("_id").is(sid)
				.and("appoinmentList._id").is(AID));
		Update update = new Update();
		update.set("appoinmentList.$.status", aa.getStatus());
		UpdateResult s = mongoTemplate.updateFirst(query, update, Services.class);
		return s;
	}

	@GetMapping("/checkwetherallreadybookornot/{SID}/{contactNumber}")
	public String check(@PathVariable String SID, @PathVariable String contactNumber) {
		Services s = si.getSid(SID);
		List<Appointment> li = s.getAppoinmentList();
		Date date = new Date();
		SimpleDateFormat f = new SimpleDateFormat("dd/MM/yy");
		String todayDate = f.format(date);
		System.out.println(todayDate);
		for (Appointment x : li) {
			DateTime dt = x.getDateTime();
			if (x.getContactNumber().equals(contactNumber)) {
				if ((dt.getDate()).equals(todayDate)) {
					return "Allready book";
				}
			}
		}
		return "not book yet";
	}

	@GetMapping("/getSingleBookedData/{AID}")
	public Appointment getbookdata(@PathVariable String AID, @RequestBody UpdateStatusRequest a) {
		String[] r = AID.split("_");
		String sid = r[0] + "_" + r[1];
		Services ss = si.getSid(sid);
		List<Appointment> fl = ss.getAppoinmentList();
		Services s = si.getbyaid(AID);
		// si.updateDemo(s, a.getStatus());
		List<Appointment> li = s.getAppoinmentList();
		System.out.println("appo list " + li);
		Appointment aa = li.get(0);
		return aa;
	}

	@PostMapping("gettodayappoinment/{SID}")
	public List<Appointment> getallappionmetoftoday(@PathVariable String SID, @RequestBody DateTime d) {
		Query query = new Query();
		query.addCriteria(Criteria.where("_id").is(SID)
				.and("appoinmentList.dateTime.date").is(d.getDate()));
		List<Services> ss = mongoTemplate.find(query, Services.class);
		System.out.println(ss);
		List<Services> newss = new ArrayList<>();
		ss.forEach((item) -> {
			java.util.stream.Stream<Appointment> newApt = item.getAppoinmentList().stream().filter((apt) -> {
				return apt.getDateTime().getDate().equals(d.getDate());
			});
			item.setAppoinmentList(newApt.toList());
			newss.add(item);
		});
		Services s = newss.get(0);
		List<Appointment> li = s.getAppoinmentList();
		return li;
	}

	@GetMapping("getall/{SID}")
	public Services all(@PathVariable String SID) {
		return si.getalldata(SID);
	}
}