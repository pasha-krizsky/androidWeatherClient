package com.example.pasha.weatherClient.command;

import com.example.pasha.weatherClient.response.TemperatureResponse;

import java.util.List;

import retrofit2.Call;

public final class GetTemperatureCommand extends AbstractCommand<TemperatureResponse> {

    public GetTemperatureCommand(String sourceCommand) {
        super(sourceCommand);
    }

    @Override
    protected Call<List<TemperatureResponse>> sendRequest(String date) {
        return serverApi.getTemperature(date);
    }
}
