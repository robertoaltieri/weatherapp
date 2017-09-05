package com.altieri.weatherapp.ui.activity.main;

import com.altieri.weatherapp.bl.model.forecast.DailyForecast;

public interface MainActivityView {
    void clearForecast();

    void addDailyForecast(DailyForecast dailyForecast);

    void setForecastVisibility(boolean visible);

    void clearCities();

    void setSelectCityVisibility(boolean visible);

    void addCity(String city);

    void setCity(String city);

    void showCity(String city);
}
