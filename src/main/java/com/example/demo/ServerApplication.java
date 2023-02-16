package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import com.example.demo.Repositery.UserRepositery;
import com.example.demo.Services.UserService;

@SpringBootApplication
public class ServerApplication {
	
//@Autowired
//private	UserRepositery ur;
	
	@Autowired
	UserService us;
	public static void main(String[] args) {
		SpringApplication.run(ServerApplication.class, args);
	}
	@EventListener(ApplicationReadyEvent.class)
	public void mail()
	{
		us.sendEmail("jittamsakhia02@gmail.com", "Hi dhruv how are you", "This is subject");
		System.out.print("yes mail is send");
	}
}
