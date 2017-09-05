package com.altieri.weatherapp.data.model.forecast;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

@AutoValue
public abstract class Main {
    public abstract @SerializedName("temp") Double getTemperature();

    public static TypeAdapter<Main> typeAdapter(Gson gson) {
        return new AutoValue_Main.GsonTypeAdapter(gson);
    }

}
