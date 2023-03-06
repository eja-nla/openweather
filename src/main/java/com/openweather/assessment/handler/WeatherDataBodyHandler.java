package com.openweather.assessment.handler;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.openweather.assessment.models.WeatherData;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.net.http.HttpResponse;
import java.util.function.Supplier;

/**
 * Created by Kore Aguda on 3/6/23.
 * 
 * A serialization handler for WeatherData
 */
public class WeatherDataBodyHandler implements HttpResponse.BodyHandler<Supplier<WeatherData>> {
    private static final ObjectMapper mapper = new ObjectMapper()
            .setSerializationInclusion(JsonInclude.Include.NON_NULL);
    public WeatherDataBodyHandler() {
    }

    @Override
    public HttpResponse.BodySubscriber<Supplier<WeatherData>> apply(HttpResponse.ResponseInfo responseInfo) {
        HttpResponse.BodySubscriber<InputStream> upstream = HttpResponse.BodySubscribers.ofInputStream();

        return HttpResponse.BodySubscribers.mapping(
                upstream,
                inputStream -> toSupplierOfType(inputStream, WeatherData.class));
    }
    public static Supplier<WeatherData> toSupplierOfType(InputStream inputStream, Class<WeatherData> targetType) {
        return () -> {
            try (InputStream stream = inputStream) {
                return mapper.readValue(stream, targetType);
            } catch (IOException e) {
                throw new UncheckedIOException(e);
            }
        };
    }

}
