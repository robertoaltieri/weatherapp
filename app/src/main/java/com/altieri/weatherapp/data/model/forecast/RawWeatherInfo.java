package com.altieri.weatherapp.data.model.forecast;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

@AutoValue
public abstract class RawWeatherInfo {
    //http://openweathermap.org/img/w/{icon}.png
    public abstract @SerializedName("icon") String getIcon();

    public static TypeAdapter<RawWeatherInfo> typeAdapter(Gson gson) {
        return new AutoValue_RawWeatherInfo.GsonTypeAdapter(gson);
    }

}
