package com.example.url_shortner;

import com.example.url_shortner.DAO.DB;
import com.example.url_shortner.controllers.UrlServices;
import com.example.url_shortner.models.URL;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UrlServicesTests {
    @Autowired
    UrlServices urlServices;

    Connection conn;

    URL testUrlInsertRemove = new URL("testcase" + getRandomString()); // URL For insertion and remove tests

    @BeforeEach
    void getNewConnection() {
        conn = DB.getConnection();
    }

    @AfterEach
    void closeConnection() throws SQLException {
        conn.close();
    }

    @Test
    @Order(1)
    void testInsertNewUrlShouldReturnTrue() {
        boolean insertion = urlServices.createNewShortUrl(testUrlInsertRemove);
        assertTrue(insertion, "Deveria inserir URL no banco de dados");
    }

    @Test
    @Order(2)
    void testRemoveUrlShouldReturnTrue() {
        boolean remove = urlServices.removeUrl(testUrlInsertRemove.getOriginalUrl());
        assertTrue(remove, "Deveria remover URL inserida anteriormente");
    }

    @Test
    void  testCreateEmptyUrlShouldReturnFalse() {
        URL url = new URL();
        boolean insertion = urlServices.createNewShortUrl(url);
        assertFalse(insertion, "Criar url sem parametro originalUrl deve retornar false");
    }

    @Test
    void testFindUrlByEncodedKey() {
        URL url = new URL("testecase" + getRandomString());
        urlServices.createNewShortUrl(url);
        URL urlResult = urlServices.findUrlByEncodedUrl(url.getEncodedUrl());
        assertEquals(url.getOriginalUrl(), urlResult.getOriginalUrl(), "Deve retornar URL igual a criada originalmente");
        urlServices.removeUrl(url.getOriginalUrl());
    }

    @Test
    void testSearchUrlThatDoesNotExistShouldReturnFalse() {
        URL url = new URL("Url não existente");
        URL urlResult = urlServices.findUrlByEncodedUrl(url.getEncodedUrl());
        assertTrue(urlResult.hasError(), "hasError deve retornar true para buscas de urls que nao existem");
        assertFalse(urlResult.getErrorMessage().isEmpty(), "Mensagem de erro deve estar preenchida");
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
