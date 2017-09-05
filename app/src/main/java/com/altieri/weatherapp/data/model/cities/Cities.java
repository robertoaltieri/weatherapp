package com.altieri.weatherapp.data.model.cities;

import android.os.Parcelable;
import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

import java.util.ArrayList;

@AutoValue
public abstract class Cities implements Parcelable {
    @Nullable
    public abstract ArrayList<String> getCities();

    public static TypeAdapter<Cities> typeAdapter(Gson gson) {
        return new AutoValue_Cities.GsonTypeAdapter(gson);
    }

    public static Cities create() {
        return new AutoValue_Cities(new ArrayList<>());
    }
}
