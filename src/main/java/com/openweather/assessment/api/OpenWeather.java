package com.openweather.assessment.api;

import com.openweather.assessment.models.WeatherData;

import java.util.List;
import java.util.Map;

/**
 * Created by Kore Aguda on 3/6/23.
 */
public interface OpenWeather {
    /**
     * lookupByZipCode
     * returns weatherdata object for a given zipcode, country pair
     * This call is synchronous
     * */
    WeatherData lookupByZipCode(String zipCode, String countryCode);

    /**
     * lookupByZipCodes
     * returns a list of weatherdata objects for a given collection of zipcode, country pair
     * This call is asynchronous
     * **/
    List<WeatherData> lookupByZipCodes(Map<String, String> zipAndCountryCodes);
}