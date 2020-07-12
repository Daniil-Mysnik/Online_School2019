package net.thumbtack.school.capitalOfCountries;

import net.thumbtack.school.capitalOfCountries.jdbc.JdbcUtils;
import net.thumbtack.school.capitalOfCountries.service.JdbsService;
import net.thumbtack.school.capitalOfCountries.service.ResponseService;
import org.json.simple.parser.ParseException;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

@RunWith(PowerMockRunner.class)
@PrepareForTest(JdbcUtils.class)
public class TestJdbcCalls {

    private static Connection spyConnection;
    private static final String USER = "test";
    private static final String PASSWORD = "test";
    private static final String URL = "jdbc:mysql://localhost:3306/capital?useUnicode=yes&characterEncoding=UTF8&useSSL=false&serverTimezone=Asia/Omsk";

    @BeforeClass()
    public static void setUp() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
        spyConnection = spy(connection);
        PowerMockito.mockStatic(JdbcUtils.class);
        when(JdbcUtils.getConnection()).thenReturn(spyConnection);
    }

    @Before()
    public void clearDatabase() throws SQLException {
        JdbsService.deleteCapitals();
    }

    @Test
    public void testStatementsNumber() throws SQLException, IOException, ParseException {
        reset(spyConnection);
        ResponseService responseService = new ResponseService();
        responseService.getCountryAndCurrency();
        verify(spyConnection, never()).createStatement();
        verify(spyConnection).prepareStatement(anyString());
    }
}