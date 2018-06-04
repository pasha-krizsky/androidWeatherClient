package com.example.pasha.weatherClient.command;

import com.example.pasha.weatherClient.api.WeatherServerApi;
import com.example.pasha.weatherClient.word.Word;

import java.util.List;

import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public abstract class AbstractCommand<T> implements Command<T> {

    private static final String BASE_SERVER_URL = "http://192.155.28.100:8080/";

    protected String sourceCommand;
    protected static WeatherServerApi serverApi;

    static {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_SERVER_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        serverApi = retrofit.create(WeatherServerApi.class);
    }

    public AbstractCommand(String sourceCommand) {
        this.sourceCommand = sourceCommand;
    }

    public Call<List<T>> execute() {
        if (sourceCommand.contains(Word.TODAY_WORD)) {
            return sendRequest(Word.TODAY_WORD.substring(0,1));
        } else if (sourceCommand.contains(Word.DAY_AFTER_TOMORROW_WORD)) {
            return sendRequest(Word.DAY_AFTER_TOMORROW_WORD.substring(0,1));
        } else if (sourceCommand.contains(Word.TOMORROW_WORD)) {
            return sendRequest(Word.TOMORROW_WORD.substring(0,1));
        } else if (sourceCommand.contains(Word.WEEK_WORD)) {
            return sendRequest(Word.WEEK_WORD.substring(0,1));
        } else {
            return sendRequest(Word.WEEK_WORD.substring(0,1));
        }
    }

    protected abstract Call<List<T>> sendRequest(String date);
}
