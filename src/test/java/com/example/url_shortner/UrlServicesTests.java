package com.example.url_shortner;

import com.example.url_shortner.DAO.DB;
import com.example.url_shortner.controllers.UrlServices;
import com.example.url_shortner.models.URL;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
public class UrlServicesTests {
    @Autowired
    UrlServices urlServices;

    Connection conn;

    @BeforeEach
    void getNewConnection() {
        conn = DB.getConnection();
    }

    @AfterEach
    void closeConnection() throws SQLException {
        conn.close();
    }

    @DisplayName("Testante insercao e remocao de URL")
    @Test
    void testInsertAndRemoveNewUrlShouldReturnTrue() {
        URL url = new URL("testcase" + getRandomString());
        boolean insertion = urlServices.createNewShortUrl(url);
        boolean remove = urlServices.removeUrl(url.getOriginalUrl());
        assertTrue(insertion, "Deveria inserir URL no banco de dados");
        assertTrue(remove, "Deveria remover URL inserida anteriormente");
    }


    private String getRandomString() {
        return new Random().ints(4, 'a', 'z' + 1)
                .collect(
                        StringBuilder::new,
                        StringBuilder::appendCodePoint,
                        StringBuilder::append
                ).toString();
    }
}
