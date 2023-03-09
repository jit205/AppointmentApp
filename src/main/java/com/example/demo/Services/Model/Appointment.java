package com.example.demo.Services.Model;

import java.util.Date;

import org.springframework.data.annotation.Id;

public class Appointment {
	@Id
	private	String AID; 
	private	String name;
		private    String email;
		private    String contactNumber;
	    private    DateTime dateTime;
	    private    String message;
	 private   String bookedOn;
	   private Status updatestatus;
	    
	  
	public DateTime getDateTime() {
		return dateTime;
	}
	public void setDateTime(DateTime dateTime) {
		this.dateTime = dateTime;
	}
	
	public Status getUpdatestatus() {
		return updatestatus;
	}
	public void setUpdatestatus(Status updatestatus) {
		this.updatestatus = updatestatus;
	}

	Date date = new Date();
	   public String getName() {
		   return name;
	   }
	   public void setName(String name) {
		   this.name = name;
		   setAID(AID); 
	   }
	  
		public String getAID() {
		return AID;
	}
	public void setAID(String AID) {
		
	     
//		String s=date+"_"+name;
//		AID=s;	
		this.AID = AID;
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
