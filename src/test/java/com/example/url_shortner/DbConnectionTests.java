package com.example.url_shortner;

import com.example.url_shortner.DAO.DB;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class DbConnectionTests {
    Connection conn = DB.getConnection();

    @BeforeEach
    void getConn() {
        conn = DB.getConnection();
    }

    @AfterEach
    void closeConn() throws SQLException {
        conn.close();
    }

    @Test
    void assertConnection() {
        assertNotNull(conn, "A conexao não deve ser nula.");
    }

    @Test
    void testConnectionIsClosedProperly() throws SQLException {
        DB.closeConnection();
        assertTrue(conn.isClosed(), "A conexao deve estar fechada");
    }

    @Test
    void testIfConnectionIsReusable() {
        Connection conn = DB.getConnection();
        Connection conn2 = DB.getConnection();
        assertSame(conn, conn2, "Os objetos de conexao devem ser os mesmos");
    }
}
