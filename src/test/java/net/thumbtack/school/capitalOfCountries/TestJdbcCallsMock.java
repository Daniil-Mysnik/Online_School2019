package net.thumbtack.school.capitalOfCountries;

import net.thumbtack.school.capitalOfCountries.jdbc.JdbcUtils;
import net.thumbtack.school.capitalOfCountries.rest.JsonDownloader;
import net.thumbtack.school.capitalOfCountries.service.ResponseService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.whenNew;

@RunWith(PowerMockRunner.class)
@PrepareForTest({JdbcUtils.class, ResponseService.class})
public class TestJdbcCallsMock {


    @Test
    public void testStatementsNumber1() throws Exception {

        ResultSet resultSet = mock(ResultSet.class);

        when(resultSet.getString(2)).thenReturn("Tallinn");

        PreparedStatement statement = mock(PreparedStatement.class);
        when(statement.executeQuery()).thenReturn(resultSet);

        Connection jdbcConnection = mock(Connection.class);
        when(jdbcConnection.prepareStatement(anyString())).thenReturn(statement);

        mockStatic(JdbcUtils.class);
        when(JdbcUtils.getConnection()).thenReturn(jdbcConnection);

        when(resultSet.next()).thenReturn(true).thenReturn(false);

        JsonDownloader jsonDownloader = mock(JsonDownloader.class);
        whenNew(JsonDownloader.class).withNoArguments().thenReturn(jsonDownloader);
        when(jsonDownloader.downloadJson(anyString())).thenReturn("{\"name\":\"Estonia\",\"capital\":\"Tallinn\",\"currencies\":[{\"code\":\"EUR\",\"name\":\"Euro\",\"symbol\":\"€\"}]}");

        ResponseService responseService = new ResponseService();
        responseService.getCountryAndCurrency();

        verify(jdbcConnection, times(0)).createStatement();
        verify(jdbcConnection, times(1)).prepareStatement(anyString());
    }
}
