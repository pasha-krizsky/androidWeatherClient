package com.example.pasha.weatherClient.response;

import java.io.Serializable;

public final class PressureResponse implements Serializable {

    private String pressure;

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    @Override
    public String toString() {
        return "PressureResponse{" +
                "pressure='" + pressure + '\'' +
                '}';
    }
}
