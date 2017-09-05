package com.altieri.weatherapp.ui.activity.city;

import com.altieri.weatherapp.data.CityData;
import com.altieri.weatherapp.ui.activity.main.MainActivityView;

import javax.inject.Inject;

import io.reactivex.observers.DisposableObserver;

public class SelectCityPresenter {
    private final CityData mCityData;
    private MainActivityView mView;

    @Inject
    SelectCityPresenter(CityData cityData) {
        mCityData = cityData;
    }

    public DisposableObserver showCities(String query) {
        mView.clearCities();
        mView.setSelectCityVisibility(true);
        mView.setForecastVisibility(false);
        return mCityData.filter(query).subscribeWith(new DisposableObserver<String>() {
            @Override
            public void onNext(String city) {
                mView.addCity(city);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    public void setCity(String city) {
        mView.clearCities();
        mView.setSelectCityVisibility(false);
        mView.clearForecast();
        mView.setForecastVisibility(true);
        mView.setCity(city);
    }

    public void setView(MainActivityView view) {
        mView = view;
    }
}
