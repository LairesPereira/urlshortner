package com.example.url_shortner;

import com.example.url_shortner.DAO.DB;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.sql.*;

@SpringBootApplication
public class UrlShortnerApplication {
	public static void main(String[] args) {
		SpringApplication.run(UrlShortnerApplication.class, args);
	}

}
