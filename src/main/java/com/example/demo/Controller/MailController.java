package com.example.demo.Controller;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.Model.otp;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/mail")
public class MailController {

    Map<String, Object> res;

    @PostMapping("/send")
    public Map<String, Object> sendmail(@RequestBody Map<String, String> bdy) {
        res = new HashMap<String, Object>();
        try {
            // int b = new Random().nextInt(900000) + 100000;
            // otp = Integer.toString(b);
            String body = "Hello sir,\n\n" + "Your OTP is: " + bdy.get("otp");
            System.out.print(bdy.get("otp") + " " + bdy.get("email"));
            // sendEmail(bdy.get("email"), body, "Your OTP to verify your Email");
            res.put("msg", "OTP is send on " + bdy.get("email"));
        } catch (Exception e) {
            res.put("msg", "server error");
        }
        return res;
    }

    // @PostMapping("/verifyotp")
    // public Boolean checkOtp(@RequestBody Map<String, String> bdy) {
    // System.out.println(bdy.get("otp") + " " + otp);
    // if (otp.equals(bdy.get("otp")))
    // return true;
    // else
    // return false;
    // }

    @Autowired
    private JavaMailSender jm;

    public void sendEmail(String email, String body, String subject) throws Exception {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("jittamsakhia02@gmail.com");
        message.setTo(email);
        message.setText(body);
        message.setSubject(subject);
        jm.send(message);
        System.out.print("mail is send");
    }
}
