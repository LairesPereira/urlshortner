package com.example.url_shortner.controllers;

import com.example.url_shortner.DAO.DB;
import com.example.url_shortner.DAO.UrlDAO;
import com.example.url_shortner.models.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.*;

@Service
public class UrlServices {
    Connection conn = DB.getConnection();

    @Autowired
    UrlDAO urlDAO = new UrlDAO();

    public URL findUrlByEncodedUrl(String urlEncoded) {
        URL url = new URL();
        try {
            ResultSet resultSet = urlDAO.findUrl(urlEncoded);
            if(resultSet.next()) {
                url.setOriginalUrl(resultSet.getString("originalUrl"));
                url.setEncodedUrl(resultSet.getString("keyUrl"));
            } else {
                url.setError(true);
                url.setErrorMessage("URL not found");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return url;
    }

    public boolean createNewShortUrl(URL url) {
        if(url == null || url.getOriginalUrl() == null) return false;

        int conflictControlCounter = 0;
        try {
            while(urlDAO.findUrl(url.getEncodedUrl()).next()) { // check if the encoded random key already exists, if so, re-generate encoded key
                url.renewEncodedUrl();
                conflictControlCounter++;
                if (conflictControlCounter > 10) { // prevents infinite loop
                    return false;
                }
            };
            return urlDAO.insertUrl(url) == 1;
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        }
    }

    public boolean removeUrl(String originalUrl) {
        return urlDAO.removeUrl(originalUrl) == 1;
    }
}
