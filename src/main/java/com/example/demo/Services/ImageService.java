package com.example.demo.Services;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Service;

import com.example.demo.Model.ImageData;
import com.example.demo.Repositery.ImageRepository;

import jakarta.annotation.Resource;

@Service
public class ImageService {
	


	@Autowired
    private ImageRepository imageRepository;

    public Resource getImageById(String id) throws IOException {
        ImageData image = imageRepository.findById(id).orElseThrow(() -> new RuntimeException("Image not found"));
        return (Resource) new ByteArrayResource(image.getData());
    } 	
}
