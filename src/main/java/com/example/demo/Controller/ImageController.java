package com.example.demo.Controller;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.awt.Image;
import java.io.IOException;
import java.net.http.HttpHeaders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.Model.ImageData;
import com.example.demo.Model.Users;
import com.example.demo.Repositery.ImageRepository;
import com.example.demo.Services.ImageService;

import jakarta.annotation.Resource;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

//@RestController
//@RequestMapping("/img")
//public class ImageController {
//	@Autowired
//	private ImageRepository ir;
//
//	@Autowired
//	private MongoTemplate mongoTemplate;
//
//	@PostMapping("/save")
//	public String uploadImage(@RequestParam("file") MultipartFile file) throws IOException {
//		ImageData image = new ImageData();
//		image.setName(file.getOriginalFilename());
//		image.setContentType(file.getContentType());
//		image.setData(file.getBytes());
//		ir.save(image);
//		return "Image uploaded successfully";
//	}
//
//
//
//	@GetMapping("/retrive/{id}")
//	public ResponseEntity<byte[]> getimage(@PathVariable("id") String id) {
//		System.out.println(id);
//		Query query = new Query();
//		query.addCriteria(Criteria.where("_id").is(id));
//		ImageData i = mongoTemplate.findOne(query, ImageData.class);
//		byte[] newdata = i.getData();
//		org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
//		headers.setContentType(MediaType.IMAGE_PNG);
////		System.out.println(i);
////		return null;
//		return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(newdata);
////		return new ResponseEntity<>(newdata, headers,));
//	}

//}