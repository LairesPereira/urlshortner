package com.example.url_shortner;

import com.example.url_shortner.DAO.UrlDAO;
import com.example.url_shortner.models.URL;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UrlDAOTests {
    @Autowired
    UrlDAO urlDAO;

    @Test
    void insertExistingUrlShouldThrowException() {
        URL url = new URL("testecasewlbn");
        assertEquals(0, urlDAO.insertUrl(url));
    }
}
