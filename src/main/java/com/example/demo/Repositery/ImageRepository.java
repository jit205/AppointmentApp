package com.example.demo.Repositery;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.Model.ImageData;

public interface ImageRepository extends MongoRepository<ImageData,String> {
	
}
