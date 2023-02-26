package com.example.demo.Services.Model;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;

import com.example.demo.Model.Location;

public class Services {
	  @Id
	    String SID;
	  String username;
	    String category;
	    String s=username+"_"+category;
	    String serviceName;
	    ServiceTime serviceTime;
	    BreakTime breakTime;
	    String appoinmentTime;
	    Location location;
	    String description;
	    List<Appointment> appoinmentList;
	    String createdTime;
	    Date createdDate=new Date();
	    
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public String getSID() {
			return SID;
		}
		public void setSID(String sID) {
			SID = s;
		}
		public String getCategory() {
			return category;
		}
		public void setCategory(String category) {
			this.category = category;
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
