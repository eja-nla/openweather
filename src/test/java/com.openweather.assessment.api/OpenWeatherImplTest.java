package com.openweather.assessment.api;

import com.openweather.assessment.models.WeatherData;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.net.http.HttpClient;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;

/**
 * OpenWeatherImpl Test
 * Created by Kore Aguda on 3/6/23.
 */
public class OpenWeatherImplTest {
    HttpClient httpClient;
    @Before
    public void setUp() {
        //we should mock/spy the client but for the purpose of this assessment, it's ok to excuse the live call
        httpClient = HttpClient.newBuilder()
              .version(HttpClient.Version.HTTP_2)
              .executor(Executors.newFixedThreadPool(3))
              .connectTimeout(Duration.ofSeconds(10))
              .build();
    }

    @Test
    public void lookupByZipCode() {
        OpenWeather weatherApi = new OpenWeatherImpl(httpClient);
        WeatherData result = weatherApi.lookupByZipCode("94040", "us");
        Assert.assertNotNull(result);
        Assert.assertEquals(result.getName(), "Mountain View");
    }

    @Test
    public void lookupByZipCodes() {
        HttpClient httpClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .executor(Executors.newFixedThreadPool(3))
                .connectTimeout(Duration.ofSeconds(10))
                .build();
        OpenWeather weatherApi = new OpenWeatherImpl(httpClient);
        Map<String, String> map = new HashMap<>();
        map.put("94040", "us");
        map.put("11201", "us");
        map.put("75206", "us");
        List<WeatherData> result = weatherApi.lookupByZipCodes(map);
        Assert.assertEquals(3, result.size());
    }
}