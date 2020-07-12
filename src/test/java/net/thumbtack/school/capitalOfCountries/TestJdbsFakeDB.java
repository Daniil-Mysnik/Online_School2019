package net.thumbtack.school.capitalOfCountries;

import net.thumbtack.school.capitalOfCountries.jdbc.JdbcUtils;
import net.thumbtack.school.capitalOfCountries.rest.JsonDownloader;
import net.thumbtack.school.capitalOfCountries.service.JdbsService;
import net.thumbtack.school.capitalOfCountries.service.ResponseService;
import org.h2.jdbcx.JdbcDataSource;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;


import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.whenNew;

@RunWith(PowerMockRunner.class)
@PrepareForTest({JdbcUtils.class, ResponseService.class})
public class TestJdbsFakeDB {

    private static final String createTablesSQL ="CREATE TABLE capitals (id INT NOT NULL AUTO_INCREMENT, name VARCHAR(50) NOT NULL,\n" +
            "UNIQUE(name), PRIMARY KEY (id)) ENGINE=INNODB DEFAULT CHARSET=utf8;\n" +
            "\n" +
            "INSERT INTO capitals (name) VALUES ('Helsinki');\n" +
            "INSERT INTO capitals (name) VALUES ('Berlin');";

    private static JdbcDataSource ds = new JdbcDataSource();
    private static Connection connection;

    @BeforeClass()
    public static void setUp() throws SQLException {
        ds.setURL("jdbc:h2:mem:~/ttschool");
        ds.setUser("sa");
        ds.setPassword("sa");
        connection = ds.getConnection();
        try (PreparedStatement stmt = connection.prepareStatement(createTablesSQL)) {
            stmt.executeUpdate();
        }
    }

    @Before()
    public void clearDatabase() {
        PowerMockito.mockStatic(JdbcUtils.class);
        when(JdbcUtils.getConnection()).thenReturn(connection);
    }

    @Test
    public void testCurrentCapitals() throws Exception {
        JsonDownloader jsonDownloader = mock(JsonDownloader.class);
        whenNew(JsonDownloader.class).withNoArguments().thenReturn(jsonDownloader);
        when(jsonDownloader.downloadJson(anyString())).thenReturn("{\"name\":\"Finland\",\"capital\":\"Helsinki\",\"currencies\":[{\"code\":\"EUR\",\"name\":\"Euro\",\"symbol\":\"€\"}]}")
                .thenReturn("{\"name\":\"Germany\",\"capital\":\"Berlin\",\"currencies\":[{\"code\":\"EUR\",\"name\":\"Euro\",\"symbol\":\"€\"}]}");
        ResponseService responseService = new ResponseService();
        List<String> responses = responseService.getCountryAndCurrency();
        assertEquals(2, responses.size());
        assertTrue(responses.get(0).contains("Helsinki") && responses.get(0).contains("Finland") && responses.get(0).contains("EUR"));
        assertTrue(responses.get(1).contains("Berlin") && responses.get(1).contains("Germany") && responses.get(1).contains("EUR"));
    }

    @Test
    public void testThrowsSQLException() {
        Method[] declaredMethods = JdbsService.class.getDeclaredMethods();
        for (Method method : declaredMethods) {
            if (!Modifier.isPublic(method.getModifiers()))
                continue;
            Class<?>[] exceptionTypes = method.getExceptionTypes();
            boolean throwSQLException = false;
            for (Class<?> exception : exceptionTypes) {
                if (exception == SQLException.class) {
                    throwSQLException = true;
                    break;
                }
            }
            if (!throwSQLException) {
                fail();
            }
        }
    }
}
