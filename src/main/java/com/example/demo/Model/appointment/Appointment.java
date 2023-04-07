package com.example.demo.Model.appointment;

import org.springframework.data.annotation.Id;

public class Appointment {
	@Id
	private String aid;
	private String name;
	private String email;
	private String contactNumber;
	private DateTime dateTime;
	private String message;
	private String bookedOn;
	private int status;

	@Override
	public String toString() {
		return "Appointment [aid=" + aid + ", name=" + name + ", email=" + email + ", contactNumber=" + contactNumber
				+ ", dateTime=" + dateTime + ", message=" + message + ", bookedOn=" + bookedOn + ", status=" + status
				+ "]";
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public DateTime getDateTime() {
		return dateTime;
	}

	public void setDateTime(DateTime dateTime) {
		this.dateTime = dateTime;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
		setAID(aid);
	}

	public String getAID() {
		return aid;
	}

	public void setAID(String aid) {

		// String s=date+"_"+name;
		// aid=s;
		this.aid = aid;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getBookedOn() {
		return bookedOn;
	}

	public void setBookedOn(String bookedOn) {
		this.bookedOn = bookedOn;
	}
}
