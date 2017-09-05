package com.altieri.weatherapp.data.model.forecast;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

import java.util.List;

@AutoValue
public abstract class RawForecast {
    public abstract @SerializedName("list") List<RawWeathers> getWeathers();

    public static TypeAdapter<RawForecast> typeAdapter(Gson gson) {
        return new AutoValue_RawForecast.GsonTypeAdapter(gson);
    }

}
