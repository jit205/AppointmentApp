package com.example.demo.Controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Model.User.Users;
import com.example.demo.Services.UserService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/user")
public class UserController {
    Map<String, Object> res;
    @Autowired
    private MongoTemplate mongoTemplate;

    @GetMapping("/img/{UID}")
    public ResponseEntity<byte[]> getimage(@PathVariable("UID") String UID) {
        try {
            System.out.println(UID);
            Query query = new Query();
            query.addCriteria(Criteria.where("_id").is(UID));
            Users i = mongoTemplate.findOne(query, Users.class);
            System.out.println(i.getImage().getData());
            byte[] newdata = i.getImage().getData();
            org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
            headers.setContentType(MediaType.IMAGE_PNG);
            return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(newdata);
        } catch (Exception e) {
            return null;
        }
    }

    @Autowired
    UserService us;

    @GetMapping("/get/{UID}")
    private Users getUser(@PathVariable("UID") String UID) {
        res = new HashMap<String, Object>();
        try {
            Users user = us.getUsersById(UID);
            user.setImage(null);
            return user;
        } catch (Exception e) {
            return null;
        }
    }
}
