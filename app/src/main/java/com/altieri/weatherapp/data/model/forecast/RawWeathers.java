package com.altieri.weatherapp.data.model.forecast;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

import java.util.List;

@AutoValue
public abstract class RawWeathers {
    public abstract @SerializedName("dt") Integer getData();
    public abstract @SerializedName("main") com.altieri.weatherapp.data.model.forecast.Main getMainInfo();
    public abstract @SerializedName("weather") List<RawWeatherInfo> getWeatherList();

    public static TypeAdapter<RawWeathers> typeAdapter(Gson gson) {
        return new AutoValue_RawWeathers.GsonTypeAdapter(gson);
    }

}
