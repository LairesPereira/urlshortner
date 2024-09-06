package com.example.url_shortner;

import com.example.url_shortner.models.URL;
import com.example.url_shortner.services.UrlServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShortUrlCreator {
    @Autowired
    UrlServices urlServices;

    @CrossOrigin("http://127.0.0.1:3000")
    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public URL createNewShortUrl (@RequestParam String originalUrl){
        System.out.println(originalUrl);
        URL url = new URL(originalUrl);
        if(urlServices.createNewShortUrl(url)) {
            return url;
        } else {
            System.err.println("error");
            URL errorUrl = new URL();
            errorUrl.setError(true);
            errorUrl.setErrorMessage("URL já existente no servidor!");
            return errorUrl;
        }
    }
}
