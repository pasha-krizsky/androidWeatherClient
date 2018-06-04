package com.example.pasha.weatherClient.command;

import com.example.pasha.weatherClient.response.WindResponse;

import java.util.List;

import retrofit2.Call;

public final class GetWindCommand extends AbstractCommand<WindResponse> {

    public GetWindCommand(String sourceCommand) {
        super(sourceCommand);
    }

    @Override
    protected Call<List<WindResponse>> sendRequest(String date) {
        return serverApi.getWind(date);
    }
}
