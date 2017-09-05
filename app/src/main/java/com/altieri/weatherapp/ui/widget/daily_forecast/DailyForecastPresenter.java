package com.altieri.weatherapp.ui.widget.daily_forecast;

import android.support.annotation.NonNull;

import com.altieri.weatherapp.bl.model.forecast.DailyForecast;
import com.altieri.weatherapp.bl.model.forecast.Weather;

import java.util.ArrayList;

import javax.inject.Inject;

class DailyForecastPresenter {
    interface DailyForecastView {
        void showDate(String date);
        void showHoursForecast(ArrayList<Weather> weathers);
    }

    @Inject
    public DailyForecastPresenter() {

    }

    public void showForecast(@NonNull DailyForecastView view, @NonNull DailyForecast dailyForecast) {
        view.showDate(dailyForecast.getDate());
        view.showHoursForecast(dailyForecast.getWeathers());
    }
}
