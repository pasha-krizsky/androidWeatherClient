package com.example.pasha.weatherClient.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.pasha.weatherClient.R;
import com.example.pasha.weatherClient.fragment.RecognizerFragment;
import com.example.pasha.weatherClient.response.CloudsResponse;
import com.example.pasha.weatherClient.response.PressureResponse;
import com.example.pasha.weatherClient.response.TemperatureResponse;
import com.example.pasha.weatherClient.response.WeatherResponse;
import com.example.pasha.weatherClient.response.WindResponse;

import java.util.List;

public class ResultActivity extends AppCompatActivity {

    private TextView temperatureText;
    private TextView temperatureValue;

    private TextView windDirectionText;
    private TextView windDirectionValue;

    private TextView windSpeedText;
    private TextView windSpeedValue;

    private TextView pressureText;
    private TextView pressureValue;

    private TextView cloudsText;
    private TextView cloudsValue;

    private Button goToMainActivityBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        initializeViews();

        processResponse();
    }

    private void processResponse() {
        Intent intent = getIntent();

        if (intent.getSerializableExtra(RecognizerFragment.WEATHER_PARAM) != null) {
            List<WeatherResponse> weatherResponses =
                    (List<WeatherResponse>) intent.getSerializableExtra(RecognizerFragment.WEATHER_PARAM);
            processWeatherResponse(weatherResponses);

        } else if (intent.getSerializableExtra(RecognizerFragment.TEMPERATURE_PARAM) != null) {
            List<TemperatureResponse> temperatureResponses =
                    (List<TemperatureResponse>) intent.getSerializableExtra(RecognizerFragment.TEMPERATURE_PARAM);
            processTemperatureResponse(temperatureResponses);
        } else if (intent.getSerializableExtra(RecognizerFragment.WIND_PARAM) != null) {
            List<WindResponse> windResponses =
                    (List<WindResponse>) intent.getSerializableExtra(RecognizerFragment.WIND_PARAM);
            processWindResponse(windResponses);
        } else if (intent.getSerializableExtra(RecognizerFragment.PRESSURE_PARAM) != null) {
            List<PressureResponse> pressureResponses =
                    (List<PressureResponse>) intent.getSerializableExtra(RecognizerFragment.PRESSURE_PARAM);
            processPressureResponse(pressureResponses);

        } else if (intent.getSerializableExtra(RecognizerFragment.CLOUDS_PARAM) != null) {
            List<CloudsResponse> cloudsRespons =
                    (List<CloudsResponse>) intent.getSerializableExtra(RecognizerFragment.CLOUDS_PARAM);
            processHumidityResponse(cloudsRespons);
        }
    }

    private void processWeatherResponse(List<WeatherResponse> weatherResponses) {
        makeTemperatureVisible();
        temperatureValue.setText(weatherResponses.get(0).getTemperature());

        makeHumidityVisible();
        cloudsValue.setText(weatherResponses.get(0).getClouds());

        makeWindVisible();
        windDirectionValue.setText(weatherResponses.get(0).getWind_direction());
        windSpeedValue.setText(weatherResponses.get(0).getWind_speed());

        makePressureVisible();
        pressureValue.setText(weatherResponses.get(0).getPressure());
    }

    private void processTemperatureResponse(List<TemperatureResponse> temperatureResponses) {
        makeCloudsInvisible();
        makeWindInvisible();
        makePressureInvisible();

        makeTemperatureVisible();
        temperatureValue.setText(temperatureResponses.get(0).getTemperature());
    }

    private void processPressureResponse(List<PressureResponse> pressureResponses) {
        makeCloudsInvisible();
        makeWindInvisible();
        makePressureInvisible();
        makeTemperatureInvisible();

        makePressureVisible();
        pressureValue.setText(pressureResponses.get(0).getPressure());
    }

    private void processWindResponse(List<WindResponse> windResponses) {
        makeCloudsInvisible();
        makePressureInvisible();
        makeTemperatureInvisible();

        makeWindVisible();
        windSpeedValue.setText(windResponses.get(0).getWind_speed());
        windDirectionValue.setText(windResponses.get(0).getWind_direction());
    }

    private void processHumidityResponse(List<CloudsResponse> cloudsResponses) {
        makeWindInvisible();
        makePressureInvisible();
        makeTemperatureInvisible();

        makeHumidityVisible();
        cloudsValue.setText(cloudsResponses.get(0).getClouds());
    }

    private void initializeViews() {
        goToMainActivityBtn = findViewById(R.id.btnActOne);
        goToMainActivityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        temperatureText = findViewById(R.id.temperature_text);
        temperatureValue = findViewById(R.id.temperature_value);

        windDirectionText = findViewById(R.id.wind_direction_text);
        windDirectionValue = findViewById(R.id.wind_direction_value);

        windSpeedText = findViewById(R.id.wind_speed_text);
        windSpeedValue = findViewById(R.id.wind_speed_value);

        pressureText = findViewById(R.id.pressure_text);
        pressureValue = findViewById(R.id.pressure_value);

        cloudsText = findViewById(R.id.humidity_text);
        cloudsValue = findViewById(R.id.humidity_value);
    }

    private void makeTemperatureInvisible() {
        temperatureText.setVisibility(View.GONE);
        temperatureValue.setVisibility(View.GONE);
    }

    private void makePressureInvisible() {
        pressureText.setVisibility(View.GONE);
        pressureValue.setVisibility(View.GONE);
    }

    private void makeCloudsInvisible() {
        cloudsValue.setVisibility(View.GONE);
        cloudsText.setVisibility(View.GONE);
    }

    private void makeWindInvisible() {
        windSpeedValue.setVisibility(View.GONE);
        windSpeedText.setVisibility(View.GONE);
        windDirectionValue.setVisibility(View.GONE);
        windDirectionText.setVisibility(View.GONE);
    }

    private void makeTemperatureVisible() {
        temperatureText.setVisibility(View.VISIBLE);
        temperatureValue.setVisibility(View.VISIBLE);
    }

    private void makePressureVisible() {
        pressureText.setVisibility(View.VISIBLE);
        pressureValue.setVisibility(View.VISIBLE);
    }

    private void makeHumidityVisible() {
        cloudsValue.setVisibility(View.VISIBLE);
        cloudsText.setVisibility(View.VISIBLE);
    }

    private void makeWindVisible() {
        windSpeedValue.setVisibility(View.VISIBLE);
        windSpeedText.setVisibility(View.VISIBLE);
        windDirectionValue.setVisibility(View.VISIBLE);
        windDirectionText.setVisibility(View.VISIBLE);
    }
}
