package com.openweather.assessment.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Snow {

    @JsonProperty("1h")
    private Double oneHr;
    @JsonProperty("3h")
    private Double threeHrs;

    public Double getOneHr() {
        return oneHr;
    }

    public void setOneHr(Double oneHr) {
        this.oneHr = oneHr;
    }

    public Double getThreeHrs() {
        return threeHrs;
    }

    public void setThreeHrs(Double threeHrs) {
        this.threeHrs = threeHrs;
    }
}
