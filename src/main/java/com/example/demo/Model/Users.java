package com.example.demo.Model;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.web.multipart.MultipartFile;

public class Users {
	@Id
	private String username;
	private String firstName;
	private String lastName;
	private String about;
	private String email;
	private String password;
	// private String picPath;
	private Location location;
	@Override
	public String toString() {
		return "Users [username=" + username + ", firstName=" + firstName + ", lastName=" + lastName + ", about="
				+ about + ", email=" + email + ", password=" + password + ", location=" + location + ", impressions="
				+ impressions + "]";
	}

	private ImageData image;
	@Autowired
	private MultipartFile picPath;

	public ImageData getImage() {
		return image;
	}

	public void setImage(ImageData image) {
		this.image = image;
	}

	//

	public void setPicPath(MultipartFile picPath) throws IOException {
		try {
			ImageData image = new ImageData();
			image.setName(picPath.getOriginalFilename());
			image.setContentType(picPath.getContentType());
			image.setData(picPath.getBytes());
			System.out.println(image.getName());
			setImage(image);
		} catch (Exception e) {
			System.out.println("No Image Attached");
		}
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	private String impressions;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getImpressions() {
		return impressions;
	}

	public void setImpressions(String impressions) {
		this.impressions = impressions;
	}

}
