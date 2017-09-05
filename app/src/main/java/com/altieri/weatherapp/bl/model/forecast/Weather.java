package com.altieri.weatherapp.bl.model.forecast;

import android.support.annotation.NonNull;

import com.google.auto.value.AutoValue;

@AutoValue
public abstract class Weather {
    public abstract String getIcon();
    public abstract String getTime();
    public abstract double getTemperature();

    public static Weather create(String icon, @NonNull String time, @NonNull Double temperature) {
        return new AutoValue_Weather(icon, time, temperature);
    }
}
