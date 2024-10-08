package com.example.url_shortner.DAO;

import com.example.url_shortner.models.URL;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

@Service
public class DB {
    private static Connection conn = null;
    public static Connection getConnection() {
        try{
            if (conn == null || conn.isClosed()){
                try {
                    Properties props = loadProperties();
                    String url = props.getProperty("spring.datasource.url");
                    String user = props.getProperty("spring.datasource.username");
                    String password = props.getProperty("spring.datasource.password");
                    conn = DriverManager.getConnection(url, user, password);
                } catch (SQLException e) {
                    System.err.println(e);
                }
            }
        } catch (SQLException e) {
            System.err.println(e);
        };
        return conn;
    }

    public static void closeConnection() {
        if (conn != null) {
            try {
                conn.close();
            } catch (Exception e) {
                System.err.println(e);
            }
        }
    }

    private static Properties loadProperties() {
        try {
            FileInputStream fs = new FileInputStream("src/main/resources/application.properties");
            Properties props = new Properties();
            props.load(fs);
            return props;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
