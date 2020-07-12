package net.thumbtack.school.capitalOfCountries;

import net.thumbtack.school.capitalOfCountries.parser.JsonParser;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;


import static org.junit.Assert.assertEquals;


public class TestParser {

    @Parameters
    @DataProvider(name = "request")
    public static Object[][] createEmployee() {
        return new Object[][] {
                {"{\"name\":\"Estonia\",\"fhjlasgb\":\"asdfasd\",\"dfas\":\"1212\",\"capital\":\"Tallinn\",\"currencies\"" +
                        ":[{\"code\":\"EUR\",\"name\":\"Euro\",\"symbol\":\"€\"}]}", "Capital: Tallinn, Country: Estonia, Currencies: EUR"},
                {"{\"name\":\"Greece\",\"fhjlasgb\":\"asdfasd\",\"capital\":\"Athens\",\"currencies\":[{\"code\":\"EUR\",\"name\":\"Euro\",\"symbol\":\"€\"}],\"dfas\":\"1212\",\"asi\":\"fkhgasd\"}",
                "Capital: Athens, Country: Greece, Currencies: EUR"},
                {"{\"fks\":\"126gf\",\"name\":\"Thailand\",\"fhjlasgb\":\"asdfasd\",\"capital\":\"Bangkok\",\"currencies\":[{\"code\":\"THB\",\"name\":\"Bath\",\"symbol\":\"B\"}],\"dfas\":\"1212\",\"asi\":\"fkhgasd\"}",
                "Capital: Bangkok, Country: Thailand, Currencies: THB"},
        };
    }

    @Test(dataProvider = "request")
    public void testParser(String request, String response) throws ParseException {
        JsonParser jsonParser = new JsonParser();
        assertEquals(response, jsonParser.getResult(request));
    }
}
