package com.example.demo.Services.Model;

import java.util.Date;

public class Appointment {

	 String name;
	    String email;
	    String contactNumber;
	    String dateTime;
	    String message;
	    String bookedOn;
	   Date date = new Date();
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
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
		public String getDateTime() {
			return dateTime;
		}
		public void setDateTime(String dateTime) {
			this.dateTime = dateTime;
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
