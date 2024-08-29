package com.example.url_shortner;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Shortner {
    @RequestMapping("/")
    String home() {
        return "Hello World!";
    }
    
}
