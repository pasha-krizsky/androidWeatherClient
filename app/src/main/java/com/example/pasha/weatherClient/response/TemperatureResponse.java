package com.example.pasha.weatherClient.response;

import java.io.Serializable;

public final class TemperatureResponse implements Serializable {

    private String temperature;

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    @Override
    public String toString() {
        return "TemperatureResponse{" +
                "temperature='" + temperature + '\'' +
                '}';
    }
}
