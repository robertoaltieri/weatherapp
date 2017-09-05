package com.altieri.weatherapp.ui.widget.hours_forecast;

import com.altieri.weatherapp.bl.TemperatureHelper;
import com.altieri.weatherapp.bl.model.forecast.DailyForecast;
import com.altieri.weatherapp.bl.model.forecast.Weather;
import com.altieri.weatherapp.bl.usecase.ForecastUseCase;
import com.altieri.weatherapp.data.ForecastData;
import com.altieri.weatherapp.data.model.forecast.Main;
import com.altieri.weatherapp.data.model.forecast.RawForecast;
import com.altieri.weatherapp.data.model.forecast.RawWeatherInfo;
import com.altieri.weatherapp.data.model.forecast.RawWeathers;

import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.observers.TestObserver;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ForecastUseCaseUnitTest {
    @Test
    public void testGetForecastReturnsEmptyInCaseOfError() throws Exception {
        ForecastData forecastData = mock(ForecastData.class);
        Observable<RawForecast> observableError = Observable.error(new Exception(""));
        when(forecastData.forecasts(ForecastData.DEFAULT_CITY)).thenAnswer(new Answer<Observable<RawForecast>>() {
            @Override
            public Observable<RawForecast> answer(InvocationOnMock invocation) throws Throwable {
                return observableError;
            }
        });
        TemperatureHelper temperatureHelper = mock(TemperatureHelper.class);
        ForecastUseCase forecastUseCase = new ForecastUseCase(forecastData, temperatureHelper);
        @SuppressWarnings("unchecked")
        TestObserver<DailyForecast> subscriber = new TestObserver<>();
        forecastUseCase.forecast(ForecastData.DEFAULT_CITY).subscribeWith(subscriber);
        subscriber
                .assertNoValues()
                .assertNoErrors()
                .assertComplete();
    }

    @Test
    public void testGetForecastReturnsCorrectValues() throws Exception {
        ForecastData forecastData = mock(ForecastData.class);

        when(forecastData.forecasts(ForecastData.DEFAULT_CITY)).thenAnswer(new Answer<Observable<RawForecast>>() {
            @Override
            public Observable<RawForecast> answer(InvocationOnMock invocation) throws Throwable {
                return Observable.just(new RawForecast() {
                    @Override
                    public List<RawWeathers> getWeathers() {
                        ArrayList<RawWeathers> rawWeathers = new ArrayList<>();
                        rawWeathers.add(new RawWeathers() {
                            @Override
                            public Integer getData() {
                                Calendar cal = new GregorianCalendar(2017,8,1, 20, 0, 0);
                                return (int) (cal.getTimeInMillis() / 1000);
                            }

                            @Override
                            public Main getMainInfo() {
                                return new Main() {
                                    @Override
                                    public Double getTemperature() {
                                        return 273.15;
                                    }
                                };
                            }

                            @Override
                            public List<RawWeatherInfo> getWeatherList() {
                                ArrayList<RawWeatherInfo> rawWeatherInfo = new ArrayList<>();
                                rawWeatherInfo.add(new RawWeatherInfo() {
                                    @Override
                                    public String getIcon() {
                                        return "icon";
                                    }
                                });
                                return rawWeatherInfo;
                            }

                        });
                        return rawWeathers;
                    }
                });
            }
        });
        // I'm not mocking this just to keep the things simpler for now
        TemperatureHelper temperatureHelper = new TemperatureHelper();
        ForecastUseCase forecastUseCase = new ForecastUseCase(forecastData, temperatureHelper);
        Weather correct = new Weather() {
            @Override
            public String getIcon() {
                return "icon";
            }

            @Override
            public String getTime() {
                return "20:00:00";
            }

            @Override
            public double getTemperature() {
                return 0;
            }
        };

        @SuppressWarnings("unchecked")
        Observer<DailyForecast> observer = mock(Observer.class);
        forecastUseCase.forecast(ForecastData.DEFAULT_CITY).subscribeWith(observer);
        final ArgumentCaptor<DailyForecast> captor = ArgumentCaptor.forClass(DailyForecast.class);
        verify(observer).onNext(captor.capture());
        final DailyForecast dailyForecast = captor.getValue();
        assertEquals(dailyForecast.getDate(), "01-Sep-2017");
        ArrayList<Weather> weathers = dailyForecast.getWeathers();
        assertEquals(weathers.size(), 1);
        Weather weather = weathers.get(0);
        assertEquals(weather.getIcon(), "icon");
        assertEquals(weather.getTemperature(), correct.getTemperature());
        assertEquals(weather.getTime(), correct.getTime());
    }

}