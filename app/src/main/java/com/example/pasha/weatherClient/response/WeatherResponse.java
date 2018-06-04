package com.example.pasha.weatherClient.response;

import java.io.Serializable;

public final class WeatherResponse implements Serializable {

    private String temperature;
    private String pressure;
    private String clouds;
    private String wind_speed;
    private String wind_direction;

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public String getClouds() {
        return clouds;
    }

    public void setClouds(String clouds) {
        this.clouds = clouds;
    }

    public String getWind_speed() {
        return wind_speed;
    }

    public void setWind_speed(String wind_speed) {
        this.wind_speed = wind_speed;
    }

    public String getWind_direction() {
        return wind_direction;
    }

    public void setWind_direction(String wind_direction) {
        this.wind_direction = wind_direction;
    }

    @Override
    public String toString() {
        return "WeatherResponse{" +
                "temperature='" + temperature + '\'' +
                ", pressure='" + pressure + '\'' +
                ", clouds='" + clouds + '\'' +
                ", windSpeed='" + wind_speed + '\'' +
                ", windDirection='" + wind_direction + '\'' +
                '}';
    }
}
