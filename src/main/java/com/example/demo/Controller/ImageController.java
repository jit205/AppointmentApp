package com.example.demo.Controller;

import java.io.IOException;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.Model.ImageData;
import com.example.demo.Services.ImageService;

import jakarta.annotation.Resource;


@RestController
@RequestMapping("/images")
public class ImageController {
	


    @Autowired
    private MongoTemplate mongoTemplate;
    
    @PostMapping()
    public String uploadImage(@RequestParam("file") MultipartFile file) throws IOException {
    	ImageData image = new ImageData();
        image.setName(file.getOriginalFilename());
        image.setContentType(file.getContentType());
        image.setData(file.getBytes());
        mongoTemplate.save(image);
        return "Image uploaded successfully";
    }
    @Autowired
    private ImageService imageService;

    

    @GetMapping("/image/{id}")
    public ResponseEntity<Resource> getImage(@PathVariable String id) throws IOException {
        Resource imageResource = imageService.getImageById(id);
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_PNG)
                .body(imageResource);
    }

    
    
}