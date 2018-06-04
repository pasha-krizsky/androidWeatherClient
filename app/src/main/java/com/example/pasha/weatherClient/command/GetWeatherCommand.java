package com.example.pasha.weatherClient.command;

import com.example.pasha.weatherClient.response.WeatherResponse;

import java.util.List;

import retrofit2.Call;

public final class GetWeatherCommand extends AbstractCommand<WeatherResponse> {

    public GetWeatherCommand(String sourceCommand) {
        super(sourceCommand);
    }

    @Override
    protected Call<List<WeatherResponse>> sendRequest(String date) {
        return serverApi.getWeather(date);
    }
}
