package com.altieri.weatherapp.data.model.hours_forecast;

import com.google.auto.value.AutoValue;

@AutoValue
public abstract class HourForecastInfo {
    public abstract String getIcon();
    public abstract String getTime();
    public abstract String getTemperature();

    public static HourForecastInfo create(String icon, String time, String temperature) {
        return new AutoValue_HourForecastInfo(icon, time, temperature);
    }
}
