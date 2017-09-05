package com.altieri.weatherapp.data.model.forecast;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

@AutoValue
public abstract class City {
    public abstract @SerializedName("id") Integer getId();
    public abstract @SerializedName("name") String getName();
    public abstract @SerializedName("country") String getCountry();

    public static TypeAdapter<City> typeAdapter(Gson gson) {
        return new AutoValue_City.GsonTypeAdapter(gson);
    }
}
