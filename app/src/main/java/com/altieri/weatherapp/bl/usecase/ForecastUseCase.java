package com.altieri.weatherapp.bl.usecase;

import android.support.annotation.NonNull;

import com.altieri.weatherapp.bl.TemperatureHelper;
import com.altieri.weatherapp.bl.model.forecast.DailyForecast;
import com.altieri.weatherapp.data.ForecastData;
import com.altieri.weatherapp.data.model.forecast.Main;
import com.altieri.weatherapp.data.model.forecast.RawForecast;
import com.altieri.weatherapp.bl.model.forecast.Weather;
import com.altieri.weatherapp.data.model.forecast.RawWeatherInfo;
import com.altieri.weatherapp.data.model.forecast.RawWeathers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Observable;

public class ForecastUseCase {
    private final ForecastData mForecastData;
    private final TemperatureHelper mTemperatureHelper;

    @Inject
    public ForecastUseCase(ForecastData forecastData, TemperatureHelper temperatureHelper) {
        mForecastData = forecastData;
        mTemperatureHelper = temperatureHelper;
    }

    public Observable<DailyForecast> forecast(String city) {
        return mForecastData.forecasts(city)
                .onErrorResumeNext(Observable.empty())
                .switchMap(rawForecast -> {
            HashMap<String, ArrayList<Weather>> weathersMap = getWeathers(rawForecast);
            Set<String> dates = weathersMap.keySet();
            String[] array = dates.toArray(new String[dates.size()]);
            Arrays.sort(array);
            return Observable.fromArray(array).map(date -> DailyForecast.create(date, weathersMap.get(date)));
        });
    }

    @NonNull
    private HashMap<String, ArrayList<Weather>> getWeathers(RawForecast rawForecast) {
        HashMap<String, ArrayList<Weather>> dailyForecasts = new HashMap<>();
        for (RawWeathers rawWeathers: rawForecast.getWeathers()) {
            mapRawWeathers(dailyForecasts, rawWeathers);
        }

        return dailyForecasts;
    }

    private void mapRawWeathers(HashMap<String, ArrayList<Weather>> dailyForecasts, RawWeathers rawWeathers) {
        Integer dateSeconds = rawWeathers.getData();
        Main info = rawWeathers.getMainInfo();
        if (info != null && dateSeconds != null) {
            Double temperature = info.getTemperature();
            if (temperature != null) {
                DateFormat formatterDate = SimpleDateFormat.getDateInstance();
                DateFormat formatterTime = SimpleDateFormat.getTimeInstance();
                Calendar dateTime = Calendar.getInstance();
                long millis = TimeUnit.SECONDS.toMillis(dateSeconds);
                dateTime.setTimeInMillis(millis);
                Date dateCalendar = dateTime.getTime();
                String date = formatterDate.format(dateCalendar);
                String time = formatterTime.format(dateCalendar);
                ArrayList<Weather> weathers = dailyForecasts.get(date);
                if (weathers == null) {
                    weathers = new ArrayList<>();
                    dailyForecasts.put(date, weathers);
                }
                List<RawWeatherInfo> weatherList = rawWeathers.getWeatherList();
                String icon = weatherList != null && weatherList.get(0) != null ? weatherList.get(0).getIcon() : null;
                double fromKelvin2Celsius = mTemperatureHelper.fromKelvin2Celsius(temperature);
                weathers.add(Weather.create(icon, time, fromKelvin2Celsius));
            }
        }
    }
}
