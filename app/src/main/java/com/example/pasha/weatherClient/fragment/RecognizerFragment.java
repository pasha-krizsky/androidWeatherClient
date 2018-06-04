package com.example.pasha.weatherClient.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.pasha.weatherClient.R;
import com.example.pasha.weatherClient.activity.ResultActivity;
import com.example.pasha.weatherClient.command.Command;
import com.example.pasha.weatherClient.command.GetCloudsCommand;
import com.example.pasha.weatherClient.command.GetPressureCommand;
import com.example.pasha.weatherClient.command.GetTemperatureCommand;
import com.example.pasha.weatherClient.command.GetWeatherCommand;
import com.example.pasha.weatherClient.command.GetWindCommand;
import com.example.pasha.weatherClient.response.CloudsResponse;
import com.example.pasha.weatherClient.response.PressureResponse;
import com.example.pasha.weatherClient.response.TemperatureResponse;
import com.example.pasha.weatherClient.response.WeatherResponse;
import com.example.pasha.weatherClient.response.WindResponse;
import com.example.pasha.weatherClient.word.Word;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.yandex.speechkit.Recognizer;
import ru.yandex.speechkit.SpeechKit;
import ru.yandex.speechkit.Vocalizer;
import ru.yandex.speechkit.gui.RecognizerActivity;

public class RecognizerFragment extends Fragment {

    private static final String TAG = "RecognizerFragmentLogs";

    private static final String SPEECH_API_KEY = "069b6659-984b-4c5f-880e-aaedcfd84102";
    private static final int REQUEST_CODE = 31;

    private static final String EMPTY_COMMAND_ERROR_MSG = "Ask me about the weather first!";

    public static final String WEATHER_PARAM = "weather";
    public static final String TEMPERATURE_PARAM = "temperature";
    public static final String WIND_PARAM = "wind";
    public static final String PRESSURE_PARAM = "pressure";
    public static final String CLOUDS_PARAM = "clouds";

    private String pronouncedCommand = "";
    private Vocalizer vocalizer;

    public RecognizerFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SpeechKit.getInstance().configure(getContext(), SPEECH_API_KEY);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button startRecognitionBtn = view.findViewById(R.id.start_recognition_btn);
        Button startVocalizerBtn = view.findViewById(R.id.start_vocalizer_btn);
        Button goResultActivityBtn = view.findViewById(R.id.btnActTwo);

        setRecognizerListener(startRecognitionBtn);
        setVocalizerListener(startVocalizerBtn);
        setGoResultActivityListener(goResultActivityBtn);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, requestCode, data);
        if (requestCode == REQUEST_CODE) {
            if (resultCode == RecognizerActivity.RESULT_OK && data != null) {
                pronouncedCommand = data.getStringExtra(RecognizerActivity.EXTRA_RESULT);
                processPronouncedCommand();
            }
        }
    }

    private void setRecognizerListener(Button startRecognitionBtn) {
        startRecognitionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pronouncedCommand = "";
                Intent intent = new Intent(getActivity(), RecognizerActivity.class);
                intent.putExtra(RecognizerActivity.EXTRA_MODEL, Recognizer.Model.QUERIES);
                intent.putExtra(RecognizerActivity.EXTRA_LANGUAGE, Recognizer.Language.RUSSIAN);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });
    }

    private void setVocalizerListener(Button startVocalizerBtn) {
        startVocalizerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(pronouncedCommand)) {
                    Toast.makeText(getContext(), EMPTY_COMMAND_ERROR_MSG, Toast.LENGTH_SHORT).show();
                } else {
                    resetVocalizer();
                    vocalizer = Vocalizer.createVocalizer(Vocalizer.Language.RUSSIAN,
                            pronouncedCommand,
                            true, Vocalizer.Voice.ALYSS);
                    vocalizer.start();
                }
            }
        });
    }

    private void setGoResultActivityListener(Button goActivityBtn) {
        goActivityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ResultActivity.class);
                startActivity(intent);
            }
        });
    }

    private void processPronouncedCommand() {
        if (pronouncedCommand.contains(Word.WEATHER_WORD)) {
            processWeatherCommand();
        } else if (pronouncedCommand.contains(Word.TEMPERATURE_WORD)) {
            processTemperatureCommand();
        } else if (pronouncedCommand.contains(Word.WIND_WORD)) {
            processWindCommand();
        } else if (pronouncedCommand.contains(Word.PRESSURE_WORD)) {
            processPressureCommand();
        } else if (pronouncedCommand.contains(Word.CLOUDS_WORD)) {
            processCloudsCommand();
        }
    }

    private void processWeatherCommand() {
        Command<WeatherResponse> command = new GetWeatherCommand(pronouncedCommand);
        Call<List<WeatherResponse>> responses = command.execute();

        Log.i(TAG, responses.toString());

        responses.enqueue(new Callback<List<WeatherResponse>>() {
            @Override
            public void onResponse(Call<List<WeatherResponse>> call, Response<List<WeatherResponse>> response) {
                Intent intent = new Intent(getActivity(), ResultActivity.class);
                intent.putExtra(WEATHER_PARAM, (Serializable) response.body());
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<List<WeatherResponse>> call, Throwable t) {

            }
        });

    }

    private void processTemperatureCommand() {
        Command<TemperatureResponse> command = new GetTemperatureCommand(pronouncedCommand);
        Call<List<TemperatureResponse>> responses = command.execute();

        Log.i(TAG, responses.toString());

        responses.enqueue(new Callback<List<TemperatureResponse>>() {
            @Override
            public void onResponse(Call<List<TemperatureResponse>> call, Response<List<TemperatureResponse>> response) {
                Intent intent = new Intent(getActivity(), ResultActivity.class);
                intent.putExtra(TEMPERATURE_PARAM, (Serializable) response.body());
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<List<TemperatureResponse>> call, Throwable t) {

            }
        });
    }

    private void processWindCommand() {
        Command<WindResponse> command = new GetWindCommand(pronouncedCommand);
        Call<List<WindResponse>> responses = command.execute();

        Log.i(TAG, responses.toString());

        responses.enqueue(new Callback<List<WindResponse>>() {
            @Override
            public void onResponse(Call<List<WindResponse>> call, Response<List<WindResponse>> response) {
                Intent intent = new Intent(getActivity(), ResultActivity.class);
                intent.putExtra(WIND_PARAM, (Serializable) response.body());
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<List<WindResponse>> call, Throwable t) {

            }
        });
    }

    private void processPressureCommand() {
        Command<PressureResponse> command = new GetPressureCommand(pronouncedCommand);
        Call<List<PressureResponse>> responses = command.execute();

        Log.i(TAG, responses.toString());

        responses.enqueue(new Callback<List<PressureResponse>>() {
            @Override
            public void onResponse(Call<List<PressureResponse>> call, Response<List<PressureResponse>> response) {
                Intent intent = new Intent(getActivity(), ResultActivity.class);
                intent.putExtra(PRESSURE_PARAM, (Serializable) response.body());
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<List<PressureResponse>> call, Throwable t) {

            }
        });
    }

    private void processCloudsCommand() {
        Command<CloudsResponse> command = new GetCloudsCommand(pronouncedCommand);
        Call<List<CloudsResponse>> responses = command.execute();

        Log.i(TAG, responses.toString());

        responses.enqueue(new Callback<List<CloudsResponse>>() {
            @Override
            public void onResponse(Call<List<CloudsResponse>> call, Response<List<CloudsResponse>> response) {
                Intent intent = new Intent(getActivity(), ResultActivity.class);
                intent.putExtra(CLOUDS_PARAM, (Serializable) response.body());
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<List<CloudsResponse>> call, Throwable t) {

            }
        });
    }

    private void resetVocalizer() {
        if (vocalizer != null) {
            vocalizer.cancel();
            vocalizer = null;
        }
    }
}
