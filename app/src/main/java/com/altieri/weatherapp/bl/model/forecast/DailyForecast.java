package com.altieri.weatherapp.bl.model.forecast;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

import java.util.ArrayList;

@AutoValue
public abstract class DailyForecast {
    public abstract String getDate();
    public abstract ArrayList<Weather> getWeathers();

    public static DailyForecast create(String date, ArrayList<Weather> weathers) {
        return new AutoValue_DailyForecast(date, weathers);
    }

    public static TypeAdapter<DailyForecast> typeAdapter(Gson gson) {
        return new AutoValue_DailyForecast.GsonTypeAdapter(gson);
    }
}
