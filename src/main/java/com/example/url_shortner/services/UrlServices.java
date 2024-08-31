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
        try {
            ResultSet resultSet = urlDAO.findUrl(urlEncoded);
            URL url = new URL(
                    resultSet.getString("originalUrl"),
                    resultSet.getString("keyUrl")
            );
            return url;
        } catch (SQLException e) {
            throw new UrlDaoException("Erro interno na busca e leitura da url da URL");
        }
    }

    public boolean createNewShortUrl(URL url) {
        return urlDAO.insertUrl(url) == 1;
    }
}
