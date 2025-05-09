package com.weatherapp;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class WeatherController {
    private TextField cityTextField;
    private Button fetchButton;
    private Label tempLabel, humidityLabel, windSpeedLabel;
    private WeatherAPI weatherAPI;

    public WeatherController() {
        weatherAPI = new WeatherAPI();
    }

    public VBox createUI() {
        cityTextField = new TextField();
        cityTextField.setPromptText("Enter city name");

        fetchButton = new Button("Get Weather");

        tempLabel = new Label("Temperature: ");
        humidityLabel = new Label("Humidity: ");
        windSpeedLabel = new Label("Wind Speed: ");

        fetchButton.setOnAction(event -> fetchWeatherData());

        VBox layout = new VBox(10);
        layout.getChildren().addAll(cityTextField, fetchButton, tempLabel, humidityLabel, windSpeedLabel);
        return layout;
    }

    private void fetchWeatherData() {
        String city = cityTextField.getText().trim();
        if (!city.isEmpty()) {
            WeatherResponse weatherResponse = weatherAPI.fetchWeather(city);
            if (weatherResponse != null) {
                updateWeatherUI(weatherResponse);
            } else {
                showError("Failed to retrieve weather data.");
            }
        } else {
            showError("City name cannot be empty.");
        }
    }

    private void updateWeatherUI(WeatherResponse weatherResponse) {
        tempLabel.setText("Temperature: " + weatherResponse.main.temp + "°C");
        humidityLabel.setText("Humidity: " + weatherResponse.main.humidity + "%");
        windSpeedLabel.setText("Wind Speed: " + weatherResponse.wind.speed + " km/h");
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Invalid Input");
        alert.setContentText(message);
        alert.showAndWait();
    }
}