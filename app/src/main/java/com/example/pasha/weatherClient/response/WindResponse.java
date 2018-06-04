package com.example.pasha.weatherClient.response;

import java.io.Serializable;

public final class WindResponse implements Serializable {

    private String wind_speed;
    private String wind_direction;

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
        return "WindResponse{" +
                "wind_speed='" + wind_speed + '\'' +
                ", wind_direction='" + wind_direction + '\'' +
                '}';
    }
}

