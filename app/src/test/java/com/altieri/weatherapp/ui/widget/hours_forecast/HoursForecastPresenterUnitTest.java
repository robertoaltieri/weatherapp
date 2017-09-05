package com.altieri.weatherapp.ui.widget.hours_forecast;

import com.altieri.weatherapp.bl.model.forecast.Weather;

import org.junit.Test;

import java.util.ArrayList;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class HoursForecastPresenterUnitTest {
    @Test
    public void show_hours_forecast_presents_correct_info() throws Exception {
        HoursForecastPresenter hoursForecastPresenter = new HoursForecastPresenter();
        ArrayList<Weather> weathers = new ArrayList<>();
        Weather weather1 = Weather.create("icon", "weather1", 12.34);
        weathers.add(weather1);
        HoursForecastPresenter.HoursForecastView view = mock(HoursForecastPresenter.HoursForecastView.class);
        hoursForecastPresenter.showHoursForecast(view, weathers);
        verify(view, times(1)).addHour("icon", "weather1", "12.34");
    }
}