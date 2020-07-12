package net.thumbtack.school.capitalOfCountries;

import net.thumbtack.school.capitalOfCountries.rest.JsonDownloader;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import static org.junit.Assert.assertEquals;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest(JsonDownloader.class)
public class TestJsonDownloader {

    @Test
    public void testDownloadJson() throws Exception {
        InputStream stream = new ByteArrayInputStream(
                ("[{\"name\":\"Germany\",\"topLevelDomain\":[\".de\"],\"alpha2Code\":\"DE\",\"alpha3Code\":\"DEU\"]")
                        .getBytes()
        );
        HttpURLConnection http = mock(HttpURLConnection.class);
        when(http.getContent()).thenReturn(stream);
        URL url = mock(URL.class);
        when(url.openConnection()).thenReturn(http);
        PowerMockito.whenNew(URL.class).withAnyArguments().thenReturn(url);

        String result = new JsonDownloader().downloadJson("capital");
        assertEquals("{\"name\":\"Germany\",\"topLevelDomain\":[\".de\"],\"alpha2Code\":\"DE\",\"alpha3Code\":\"DEU\"", result);
    }

}
