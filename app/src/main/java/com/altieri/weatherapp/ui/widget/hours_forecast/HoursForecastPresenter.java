package com.altieri.weatherapp.ui.widget.hours_forecast;

import com.altieri.weatherapp.bl.model.forecast.Weather;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.inject.Inject;

class HoursForecastPresenter {

    @Inject
    public HoursForecastPresenter() {

    }

    public void showHoursForecast(HoursForecastView view, ArrayList<Weather> weathers) {
        view.clear();
        for (Weather weather: weathers) {
            DecimalFormat df = new DecimalFormat("#.##");
            df.setRoundingMode(RoundingMode.HALF_EVEN);
            String temperature = df.format(weather.getTemperature());
            view.addHour(weather.getIcon(), weather.getTime(), temperature);
        }
    }

    interface HoursForecastView {
        void addHour(String icon, String time, String temperature);

        void clear();
    }
}
