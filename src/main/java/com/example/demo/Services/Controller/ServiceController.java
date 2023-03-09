package com.example.demo.Services.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Services.Model.Appointment;
import com.example.demo.Services.Model.DateTime;
import com.example.demo.Services.Model.Services;
import com.example.demo.Services.Model.Status;
import com.example.demo.Services.Service.Servicesimpl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
//		System.out.println("ss "+ss);
		if(ss==null)
		{
			si.saveservice(s);
			System.out.println(s.toString());
			return "new Services is added ";
		}
		else
		{
			return "service is allready there ";
		}
	}
//	@GetMapping("getAllcategory/{category}")
//	public List<Services> Allcategory(@PathVariable String category)
//	{
//		return si.GetBycategory(category);
//	}
	@GetMapping("servicesusername/{username}")
	public List<Services> Allusrname(@PathVariable String username)
	{
		return si.GetadminServicres(username);
	}
	/*************************************************************/
	Date d = new Date();
	@PostMapping("getSID/{SID}")
	public Services  getSID(@PathVariable String SID ,@RequestBody Appointment aa )
	{
	Services s=	si.getSid(SID);
	 System.out.println(s.toString());
//	si.saveservice(s);
	 List<Appointment> xa = s.getAppoinmentList();
		int x=(int)(d.getTime()/1000);
	      String t=Integer.toString(x);
	      String sx=SID+"_"+t;
	      DateTime dt= new DateTime();
	      dt.setDate(aa.getDateTime().getDate());
	      dt.setTime(aa.getDateTime().getTime());
	 Appointment a=new Appointment();
	 a.setAID(sx);
	 a.setName(aa.getName());
	 a.setMessage(aa.getMessage());
	 a.setEmail(aa.getEmail());
	 a.setContactNumber(aa.getContactNumber());
	 a.setDateTime(dt);
	 xa.add(a);
	 s.setAppoinmentList(xa);
	return si.saveservice(s);
		
	}
//	@PostMapping("addAppoimentlist")
//	public Services addappoiment(@RequestBody Services s)
//	{
//		Services x=getSID();
//		return ss;
//	}
	
	@GetMapping("filter/{category}/{city}/{pincode}")
	public List<Services> getfilter(@PathVariable String category,@PathVariable String city,@PathVariable String pincode)
	{
		return si.filter(category, city, pincode);
	}
	@PostMapping("updateStatus/{AID}")
	public String updatestatus(@PathVariable String AID,@RequestBody Status aa)
	{
		 String[] r =AID.split("_");
		 String sid=r[0]+"_"+r[1];
		 String f=r[0]+"_"+r[1];
		 System.out.println(f);
		 
//		 Services s=si.getSid(sid);
//		 List<Appointment> xa = s.getAppoinmentList();
//		 Appointment a=new Appointment();
//		 a.setStatus(aa.getStatus());
//		 xa.add(a);
//		 return si.saveservice(s);
		/************/
		
//		(List<Appointment>) si.getAID(AID).setAppoinmentList();
//		List<Appointment> s=s;
//		s.getAppoinmentList().
		 Services a= si.getSid(sid);
		 System.out.println(aa);
		 System.out.println("services"+a);
		 System.out.println(aa.getStatus());
		 List<Appointment> li= a.getAppoinmentList() ;
		 System.out.println(li);
		 /***************************************/
		 Appointment af=si.getAID(AID);
		 System.out.println("appoin ,"+af);         
		
//		 af = mongoTemplate.findOne(
//				  Query.query(Criteria.where("_id").is(AID)), Appointment.class);
		 Status ss=new Status();
		 		ss.setStatus(aa.getStatus());
				af.setUpdatestatus(ss);
				
				li.add(af);
				a.setAppoinmentList(li);
				
				
				si.saveservice(a);
		 
		
//		System.out.println("Service obj"+a);
		
		
//		Appointment al=si.getAID(AID);
//		al.setStatus(aa.getStatus());
//		for (Appointment x : l) {+
//			System.out.println("id "+x.getAid());
//			System.out.println(AID);
//		    if ((x.getAid()).equals(AID)) {
//		    	System.out.println("enter in status");
//		        x.setStatus(aa.getStatus());
////		        System.out.println(aa.getStatus());
//		        l.add(x);
////		        l.add(al);
//		        break;  	
//		    }
//		}
//		a.setAppoinmentList(al);
//		return si.saveservice(al);
		return "update is done";
	
	}
	@GetMapping("/checkwetherallreadybookornot/{SID}/{contactNumber}")
	public String check(@PathVariable String SID ,@PathVariable String contactNumber)
{
		Services s=si.getSid(SID);
		List<Appointment> li=s.getAppoinmentList();
		

		Date date=new Date();
		SimpleDateFormat f=new SimpleDateFormat("dd/MM/yy");
		
		String todayDate=f.format(date);
		System.out.println(todayDate);
		for(Appointment x:li)
		{
			DateTime dt=x.getDateTime();
			System.out.println(x.getContactNumber() +" "+contactNumber);
			if(x.getContactNumber().equals(contactNumber)) {
				System.out.println(dt.getDate());
			if((dt.getDate()).equals(todayDate))
				
			{				

				return "Allready book";
			}
		}
		}
		return "not book yet";
 }
}
