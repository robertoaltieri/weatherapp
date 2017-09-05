package com.altieri.weatherapp.data;

import com.altieri.weatherapp.data.model.cities.Cities;
import com.google.gson.Gson;

import java.util.ArrayList;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Single;

public class CityData {
    private final AppStorage mAppStorage;
    private final Gson mGson;
    private String mCity = ForecastData.DEFAULT_CITY;

    @Inject
    CityData(AppStorage appStorage, Gson gson) {
        mAppStorage = appStorage;
        mGson = gson;
    }

    private Single<ArrayList<String>> hardcodedCities() {
        Single<ArrayList<String>> single = Single.create(e -> {
            ArrayList<String> item;
            try {
                String json = mAppStorage.fileFromAsset("uk_locations.json");
                ArrayList<String> cities = mGson.fromJson(json, Cities.class).getCities();
                item = cities != null ? cities : new ArrayList<>();
            } catch (Exception ex) {
                item = new ArrayList<>();
            }
            e.onSuccess(item);
        });
        return single.<ArrayList<String>>cache();
    }

    public Observable<String> filter(String query) {
        return hardcodedCities()
                .toObservable()
                .switchMap(Observable::fromIterable)
                .filter(city -> city.toLowerCase().startsWith(query.toLowerCase()));
    }

    public void setCity(String city) {
        mCity = city;
    }

    public String getCity() {
        return mCity;
    }
}
