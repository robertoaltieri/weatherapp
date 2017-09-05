package com.altieri.weatherapp;

import android.app.Activity;
import android.app.Application;

import com.altieri.weatherapp.di.AppComponent;
import com.altieri.weatherapp.di.DaggerAppComponent;
import com.altieri.weatherapp.di.module.AppModule;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

public class App extends Application implements HasActivityInjector {
    @Inject DispatchingAndroidInjector<Activity> mDispatchingActivityInjector;

    private AppComponent sAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        sAppComponent = DaggerAppComponent
                .builder()
                .appModule(new AppModule(this))
                .build();
        sAppComponent.inject(this);

    }

    public AppComponent getAppComponent() {
        return sAppComponent;
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return mDispatchingActivityInjector;
    }
}
