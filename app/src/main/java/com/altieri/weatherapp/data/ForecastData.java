package com.altieri.weatherapp.data;

import com.altieri.weatherapp.AppSchedulers;
import com.altieri.weatherapp.data.model.forecast.RawForecast;
import com.altieri.weatherapp.data.network.ServiceWeather;

import javax.inject.Inject;

import io.reactivex.Observable;

public class ForecastData {
    public static final String DEFAULT_CITY = "London,uk";
    private final ServiceWeather mServiceWeather;
    private final AppSchedulers mAppSchedulers;

    @Inject
    ForecastData(ServiceWeather serviceWeather, AppSchedulers appSchedulers) {
        mServiceWeather = serviceWeather;
        mAppSchedulers = appSchedulers;
    }

    public Observable<RawForecast> forecasts(String city) {
        // hardcoded value. The first one should actually be set by the user the second one should be
        // retrieved from a backend server to keep it secret
        Observable<RawForecast> forecast = mServiceWeather.forecast(city, "b1b15e88fa797225412429c1c50c122a1");
        return forecast
                .subscribeOn(mAppSchedulers.network())
                .observeOn(mAppSchedulers.mainThread());
    }

}
