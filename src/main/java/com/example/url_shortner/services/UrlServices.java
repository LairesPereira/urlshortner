package com.example.url_shortner.services;

import com.example.url_shortner.DAO.DB;
import com.example.url_shortner.DAO.UrlDAO;
import com.example.url_shortner.exceptions.UrlDaoException;
import com.example.url_shortner.models.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.*;

@Service
public class UrlServices {
    Connection conn = DB.getConnection();

    @Autowired
    UrlDAO urlDAO;

    public URL findUrlByEncodedUrl(String urlEncoded) {
        URL url = new URL();
        System.out.println("new request");
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
        return urlDAO.insertUrl(url) == 1;
    }
}
