package net.thumbtack.school.capitalOfCountries.service;


import net.thumbtack.school.capitalOfCountries.jdbc.JdbcUtils;
import net.thumbtack.school.capitalOfCountries.parser.JsonParser;
import net.thumbtack.school.capitalOfCountries.rest.JsonDownloader;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class ResponseService {

    private JsonDownloader jsonDownloader = new JsonDownloader();
    private JsonParser jsonParser = new JsonParser();

    public List<String> getCountryAndCurrency() throws SQLException, IOException, ParseException {
        List<String> countriesAndCurrencies = new LinkedList<>();
        String selectQuery = "select * from capitals";
        try(PreparedStatement stmt = JdbcUtils.getConnection().prepareStatement(selectQuery); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                String capital = rs.getString(2);
                String json = jsonDownloader.downloadJson(capital);
                String result = jsonParser.getResult(json);
                System.out.println(result);
                countriesAndCurrencies.add(result);
            }
        }
        return countriesAndCurrencies;
    }
}
