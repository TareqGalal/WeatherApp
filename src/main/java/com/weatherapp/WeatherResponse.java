package com.weatherapp;

public class WeatherResponse {
    Main main;
    Wind wind;

    public class Main {
        double temp;
        int humidity;
    }

    public class Wind {
        double speed;
    }
}