package com.example.url_shortner.controllers;

import com.example.url_shortner.models.URL;
import com.example.url_shortner.controllers.UrlServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("http://127.0.0.1:3000")
public class Shortner {
    @Autowired
    UrlServices urlServices;
    @GetMapping("/")
    public String home() {
        return "welcome to a free url shortner. Also free from ads, consider be a sponsor :)";
    }
    @GetMapping(value = "/{urlEncoded}", produces = MediaType.APPLICATION_JSON_VALUE)
    public URL getRoute(@PathVariable(value = "urlEncoded") String urlEncoded) {
        URL result = urlServices.findUrlByEncodedUrl(urlEncoded);
        return result;
    }
}