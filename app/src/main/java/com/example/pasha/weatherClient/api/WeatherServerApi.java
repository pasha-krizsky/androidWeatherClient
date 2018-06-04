package com.example.pasha.weatherClient.api;

import com.example.pasha.weatherClient.response.CloudsResponse;
import com.example.pasha.weatherClient.response.PressureResponse;
import com.example.pasha.weatherClient.response.TemperatureResponse;
import com.example.pasha.weatherClient.response.WeatherResponse;
import com.example.pasha.weatherClient.response.WindResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherServerApi {

    @GET("temperature")
    Call<List<TemperatureResponse>> getTemperature(@Query("date") String date);

    @GET("wind")
    Call<List<WindResponse>> getWind(@Query("date") String date);

    @GET("clouds")
    Call<List<CloudsResponse>> getClouds(@Query("date") String date);

    @GET("pressure")
    Call<List<PressureResponse>> getPressure(@Query("date") String date);

    @GET("weather")
    Call<List<WeatherResponse>> getWeather(@Query("date") String date);
}
