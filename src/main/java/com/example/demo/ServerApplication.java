package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.Repositery.UserRepositery;

@SpringBootApplication
public class ServerApplication {
	
@Autowired
private	UserRepositery ur;
	public static void main(String[] args) {
		SpringApplication.run(ServerApplication.class, args);
	}

//	public void run(String...args)throws Exception
//	{
//		Users u1=new Users();
//		u1.setFirstName("abc");
//		ur.save(u1);
//		Users u2=new Users();
//	}
}
