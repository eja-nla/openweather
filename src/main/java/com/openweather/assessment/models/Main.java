package com.openweather.assessment.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Main {

    private Double temp;
    @JsonProperty("temp_min")

    private Double tempMin;
    @JsonProperty("temp_max")
    private Double tempMax;
    private Double pressure;
    private Integer humidity;
    @JsonProperty("feels_like")
    private Double feelsLike;

    @JsonProperty("sea_level")
    private Integer seaLevel;

    @JsonProperty("grnd_level")
    private Integer grndLevel;

    /**
     *
     * @return
     * The temp
     */
    public Double getTemp() {
        return temp;
    }

    /**
     *
     * @param temp
     * The temp
     */
    public void setTemp(Double temp) {
        this.temp = temp;
    }

    /**
     *
     * @return
     * The tempMin
     */
    public Double getFeelsLike() {
        return feelsLike;
    }

    /**
     *
     * @param feelsLike
     * The temp
     */
    public void setFeelsLike(Double feelsLike) {
        this.feelsLike = feelsLike;
    }

    /**
     *
     * @param tempMin
     * The temp_min
     */
    public void setTempMin(Double tempMin) {
        this.tempMin = tempMin;
    }

    /**
     *
     * @return
     * The tempMin
     */
    public Double getTempMin() {
        return tempMin;
    }

    /**
     *
     * @return
     * The tempMax
     */
    public Double getTempMax() {
        return tempMax;
    }

    /**
     *
     * @param tempMax
     * The temp_max
     */
    public void setTempMax(Double tempMax) {
        this.tempMax = tempMax;
    }

    /**
     *
     * @return
     * The pressure
     */
    public Double getPressure() {
        return pressure;
    }

    /**
     *
     * @param pressure
     * The pressure
     */
    public void setPressure(Double pressure) {
        this.pressure = pressure;
    }

    /**
     *
     * @return
     * The humidity
     */
    public Integer getHumidity() {
        return humidity;
    }

    /**
     *
     * @param humidity
     * The humidity
     */
    public void setHumidity(Integer humidity) {
        this.humidity = humidity;
    }

    public Integer getSeaLevel() {
        return seaLevel;
    }

    public void setSeaLevel(Integer seaLevel) {
        this.seaLevel = seaLevel;
    }

    public Integer getGrndLevel() {
        return grndLevel;
    }

    public void setGrndLevel(Integer grndLevel) {
        this.grndLevel = grndLevel;
    }
}