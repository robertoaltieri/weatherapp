package com.altieri.weatherapp.ui.widget.daily_forecast;


import com.altieri.weatherapp.bl.model.forecast.DailyForecast;
import com.altieri.weatherapp.bl.model.forecast.Weather;

import org.junit.Test;

import java.util.ArrayList;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class DailyForecastPresenterUnitTest {
    @Test
    public void show_forecast_show_correct_info() throws Exception {
        DailyForecastPresenter dailyForecastPresenter = new DailyForecastPresenter();
        ArrayList<Weather> weathers = new ArrayList<>();
        DailyForecastPresenter.DailyForecastView view = mock(DailyForecastPresenter.DailyForecastView.class);
        dailyForecastPresenter.showForecast(view, DailyForecast.create("date", weathers)); //I'm not mocking DailyForecast to keep the things simpler
        verify(view, times(1)).showDate("date");
        verify(view, times(1)).showHoursForecast(weathers);
    }
}