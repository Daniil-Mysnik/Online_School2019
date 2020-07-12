package net.thumbtack.school.capitalOfCountries.rest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class JsonDownloader {

    public String downloadJson(String capital) throws IOException {
        URL url = new URL("http://restcountries.eu/rest/v2/capital/" + capital);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.connect();
        try(InputStreamReader in = new InputStreamReader((InputStream) conn.getContent());
            BufferedReader buff = new BufferedReader(in)) {
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = buff.readLine()) != null) {
                String result = line.substring(1, line.length() - 1);
                sb.append(result);
            }
            return sb.toString();
        }
    }
}
