package com.example.demo.Controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin(origins = "http://localhost:3000")

public class MainController {
    @RequestMapping("/")
    public String hello() {
        return "Server Running";
    }
}
