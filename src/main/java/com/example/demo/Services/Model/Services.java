package com.example.demo.Services.Model;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;

import com.example.demo.Model.Location;

public class Services {
	@Id
	private	String SID;
	private  String username;
	private    String category;
	private  String serviceName;
	private ServiceTime serviceTime;
	private  BreakTime breakTime;
	private  String appoinmentTime;
	private  Location location;
	private  String description;
	private  List<Appointment> appoinmentList;
	private String createdTime;
	private	 Date createdDate=new Date();
	    
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		
		public String getCategory() {
			return category;
		}
		public void setCategory(String category) {
			this.category = category;
			setSID(SID);
		}
		public String getSID() {
			return SID;
		}
		public void setSID(String SID) {
			String s=username+"_"+category;
			System.out.println("SID "+s);
			SID=s;
			this.SID = SID;
		}
		public String getServiceName() {
			return serviceName;
		}
		public void setServiceName(String serviceName) {
			this.serviceName = serviceName;
		}
		public ServiceTime getServiceTime() {
			return serviceTime;
		}
		public void setServiceTime(ServiceTime serviceTime) {
			this.serviceTime = serviceTime;
		}
		public BreakTime getBreakTime() {
			return breakTime;
		}
		public void setBreakTime(BreakTime breakTime) {
			this.breakTime = breakTime;
		}
		public String getAppoinmentTime() {
			return appoinmentTime;
		}
		public void setAppoinmentTime(String appoinmentTime) {
			this.appoinmentTime = appoinmentTime;
		}
		public Location getLocation() {
			return location;
		}
		public void setLocation(Location location) {
			this.location = location;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		public List<Appointment> getAppoinmentList() {
			return appoinmentList;
		}
		public void setAppoinmentList(List<Appointment> appoinmentList) {
			this.appoinmentList = appoinmentList;
		}
		public String getCreatedTime() {
			return createdTime;
		}
		public void setCreatedTime(String createdTime) {
			this.createdTime = createdTime;
		}
		public Date getCreatedDate() {
			return createdDate;
		}
		public void setCreatedDate(Date createdDate) {
			this.createdDate = createdDate;
		}
	    
}
