package com.example.pasha.weatherClient.response;

import java.io.Serializable;

public final class CloudsResponse implements Serializable {

    private String clouds;

    public String getClouds() {
        return clouds;
    }

    public void setClouds(String clouds) {
        this.clouds = clouds;
    }

    @Override
    public String toString() {
        return "CloudsResponse{" +
                "clouds='" + clouds + '\'' +
                '}';
    }
}
