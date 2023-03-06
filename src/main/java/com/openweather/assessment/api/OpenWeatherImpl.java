package com.openweather.assessment.api;

import static java.time.temporal.ChronoUnit.SECONDS;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.openweather.assessment.handler.WeatherDataBodyHandler;
import com.openweather.assessment.models.WeatherData;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * Created by Kore Aguda on 3/6/23.
 */
public class OpenWeatherImpl implements OpenWeather{
    private final HttpClient httpClient;
    private static final String API_KEY = System.getProperty("WEATHER_API_KEY");
    private static final String URL_TEMPLATE = "https://api.openweathermap.org/data/2.5/weather?zip=%s,%s&appid=%s";
    public OpenWeatherImpl(HttpClient client) {
        this.httpClient = client;
    }

    @Override
    public WeatherData lookupByZipCode(String zipCode, String countryCode) {
        final String URL = String.format(URL_TEMPLATE, zipCode, countryCode, API_KEY);
        try {
            HttpRequest request = HttpRequest
                    .newBuilder()
                    .uri(new URI(URL))
                    .version(HttpClient.Version.HTTP_2)
                    .timeout(Duration.of(10, SECONDS))
                    .headers("Content-Type", "application/json")
                    .GET()
                    .build();
            return httpClient
                    .send(request, new WeatherDataBodyHandler())
                    .body()
                    .get();
        } catch (URISyntaxException | IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<WeatherData> lookupByZipCodes(Map<String, String> zipAndCountryCodes) {
        List<URI> weatherUrls = new ArrayList<>(zipAndCountryCodes.size());
        List<WeatherData> weatherData = new ArrayList<>(zipAndCountryCodes.size());
        zipAndCountryCodes.forEach((zip, countryCode) -> {
            try {
                weatherUrls.add(new URI(String.format(URL_TEMPLATE, zip, countryCode, API_KEY)));
            } catch (URISyntaxException e) {
                throw new RuntimeException(e);
            }
        });
        try{
            List<CompletableFuture<Supplier<WeatherData>>> result = weatherUrls.stream()
                    .map(url -> httpClient.sendAsync(
                                    HttpRequest.newBuilder(url)
                                            .GET()
                                            .headers("Content-Type", "application/json")
                                            .build(),
                                    new WeatherDataBodyHandler())
                            .thenApply(HttpResponse::body))
                    .collect(Collectors.toList());
            for (CompletableFuture<Supplier<WeatherData>> f : result) {
                weatherData.add(f.get().get());
            }
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        return weatherData;
    }
}