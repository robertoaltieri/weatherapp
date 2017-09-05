package com.altieri.weatherapp.data.network;


import com.altieri.weatherapp.data.model.forecast.RawForecast;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ServiceWeather {
    @GET("/data/2.5/forecast")
    Observable<RawForecast> forecast(@Query("q") String city,
                                     @Query("appid") String appId);

}
