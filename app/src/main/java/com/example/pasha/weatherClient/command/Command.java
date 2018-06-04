package com.example.pasha.weatherClient.command;

import java.util.List;

import retrofit2.Call;

public interface Command<T> {
    Call<List<T>> execute();
}
