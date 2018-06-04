package com.example.pasha.weatherClient.command;

import com.example.pasha.weatherClient.response.PressureResponse;

import java.util.List;

import retrofit2.Call;

public final class GetPressureCommand extends AbstractCommand<PressureResponse> {

    public GetPressureCommand(String sourceCommand) {
        super(sourceCommand);
    }

    @Override
    protected Call<List<PressureResponse>> sendRequest(String date) {
        return serverApi.getPressure(date);
    }
}
