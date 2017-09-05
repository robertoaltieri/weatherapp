package com.altieri.weatherapp.ui.activity.main;

import android.support.annotation.NonNull;

import com.altieri.weatherapp.bl.model.forecast.DailyForecast;
import com.altieri.weatherapp.bl.usecase.ForecastUseCase;
import com.altieri.weatherapp.data.CityData;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.observers.DisposableObserver;

class MainActivityPresenter {
    private final ForecastUseCase mForecastUseCase;
    private final CityData mCityData;

    @Inject
    MainActivityPresenter(ForecastUseCase forecastUseCase, CityData cityData) {
        mForecastUseCase = forecastUseCase;
        mCityData = cityData;
    }

    DisposableObserver<DailyForecast> showForecast(MainActivityView view) {
        return getDailyForecast(view);
    }

    DisposableObserver<DailyForecast> showForecast(MainActivityView view, String city) {
        mCityData.setCity(city);
        return getDailyForecast(view);
    }

    @NonNull
    private DisposableObserver<DailyForecast> getDailyForecast(final MainActivityView view) {
        // this is a trick to keep the things simple. The correct way would have been to
        // make the observable emitting different events. One event would be start of the list
        // the presenter would then handle it properly
        // The app should also have cached the values not to have to download them again every time
        String city = mCityData.getCity();
        view.clearForecast();
        view.setForecastVisibility(true);
        view.setSelectCityVisibility(false);
        view.showCity(city);
        Observable<DailyForecast> forecasts = mForecastUseCase.forecast(city);
        return forecasts
                .subscribeWith(new DisposableObserver<DailyForecast>() {

                    @Override
                    public void onNext(DailyForecast dailyForecast) {
                        view.addDailyForecast(dailyForecast);
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

}
