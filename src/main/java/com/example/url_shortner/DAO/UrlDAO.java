package com.example.url_shortner.DAO;

import com.example.url_shortner.exceptions.UrlDaoException;
import com.example.url_shortner.models.URL;
import org.springframework.stereotype.Service;

import java.sql.*;

@Service
public class UrlDAO {
    Connection conn;

    public int insertUrl(URL url) {
        conn = DB.getConnection();

        String query = "INSERT INTO Urls (originalUrl, keyUrl) VALUES ('"
                + url.getOriginalUrl() + "', '"
                + url.getEncodedUrl() + "')";
        try {
            PreparedStatement st = conn.prepareStatement(query);
            return st.executeUpdate();
        } catch (SQLException e) {
            throw new UrlDaoException("URL ja existente no servidor.");
        }
    }

    public ResultSet findUrl(String urlEncoded) {
        conn = DB.getConnection();
        String query = "SELECT * FROM Urls WHERE keyUrl = ?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, urlEncoded);
            return pstmt.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
