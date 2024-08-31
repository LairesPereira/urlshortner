package com.example.url_shortner;

import com.example.url_shortner.exceptions.UrlDaoException;
import com.example.url_shortner.models.URL;
import com.example.url_shortner.services.UrlServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.sql.ResultSet;
import java.sql.SQLException;

@RestController
@RequestMapping("/")
public class Shortner {
    @Autowired
    UrlServices urlServices;

    @RequestMapping(value = "/{urlEncoded}")
    public URL getRoute(@PathVariable(value = "urlEncoded") String urlEncoded) {
        URL result = urlServices.findUrlByEncodedUrl(urlEncoded);
        return result;
    }

    @RequestMapping(value = "create/{originalUrl}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public URL createNewShortUrl (@PathVariable("originalUrl") String originalUrl){
        URL url = new URL(originalUrl);
        if(urlServices.createNewShortUrl(url)) {
            return url;
        } else {
            throw new UrlDaoException("URL já existente no servidor.");
        }
    }
}