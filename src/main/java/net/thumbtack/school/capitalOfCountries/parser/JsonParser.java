package net.thumbtack.school.capitalOfCountries.parser;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

public class JsonParser {

    public String getResult(String jsonString) throws ParseException {
        Object object = new JSONParser().parse(jsonString);
        JSONObject jsonObject = (JSONObject) object;
        String capital = (String) jsonObject.get("capital");
        String country = (String) jsonObject.get("name");
        JSONArray currenciesArray = (JSONArray) jsonObject.get("currencies");
        String currencies = "";
        for (Object o : currenciesArray) {
            JSONObject currency = (JSONObject) o;
            currencies = (String) currency.get("code");
        }
        return "Capital: " + capital + ", Country: " + country + ", Currencies: " + currencies;
    }
}
