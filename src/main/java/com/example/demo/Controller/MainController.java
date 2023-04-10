package com.example.demo.Controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")

public class MainController {
    Map<String, Object> res;

    @RequestMapping("/")
    public String hello() {
        return "Server Running";
    }

    @GetMapping("/FixData.json")
    public Map<String, Object> categoryList() {
        res = new HashMap<>();
        String[] category = { "Salon", "Gym", "Office", "Doctor", "Consultant", "Other" };
        try {
            res.put("category", category);
        } catch (Exception e) {
            res = null;
        }
        return res;
    }
}
