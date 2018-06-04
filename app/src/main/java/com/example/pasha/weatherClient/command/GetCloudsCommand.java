package com.example.pasha.weatherClient.command;

import com.example.pasha.weatherClient.response.CloudsResponse;

import java.util.List;

import retrofit2.Call;

public final class GetCloudsCommand extends AbstractCommand<CloudsResponse> {

    public GetCloudsCommand(String sourceCommand) {
        super(sourceCommand);
    }

    @Override
    protected Call<List<CloudsResponse>> sendRequest(String date) {
        return serverApi.getClouds(date);
    }
}
