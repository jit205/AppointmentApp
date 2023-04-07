package com.example.demo.Model.service;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;

import com.example.demo.Model.Location;
import com.example.demo.Model.appointment.Appointment;

public class Services {
	@Id
	private String sid;
	private String username;
	private String category;
	private String serviceName;
	private TimeSlot serviceTime;
	private TimeSlot breakTime;
	private String appoinmentTime;
	private Location location;
	private List<Integer> holidays;
	private String description;
	private List<Appointment> appointmentList;

	private Date createdAt = new Date();
	private Date updatedAt = new Date();

	public List<Appointment> getAppointmentList() {
		return appointmentList;
	}

	public void setAppointmentList(List<Appointment> appointmentList) {
		this.appointmentList = appointmentList;
	}

	@Override
	public String toString() {
		return "Services [sid=" + sid + ", username=" + username + ", category=" + category + ", serviceName="
				+ serviceName + ", serviceTime=" + serviceTime + ", breakTime=" + breakTime + ", appoinmentTime="
				+ appoinmentTime + ", location=" + location + ", holidays=" + holidays + ", description=" + description
				+ ", appointmentList=" + appointmentList + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + "]";
	}

	public List<Integer> getHolidays() {
		return holidays;
	}

	public void setHolidays(List<Integer> holidays) {
		this.holidays = holidays;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

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
		setSID(sid);
	}

	public String getSID() {
		return sid;
	}

	public void setSID(String sid) {
		this.sid = sid;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
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

	public TimeSlot getServiceTime() {
		return serviceTime;
	}

	public void setServiceTime(TimeSlot serviceTime) {
		this.serviceTime = serviceTime;
	}

	public TimeSlot getBreakTime() {
		return breakTime;
	}

	public void setBreakTime(TimeSlot breakTime) {
		this.breakTime = breakTime;
	}

}
