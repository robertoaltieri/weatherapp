package com.altieri.weatherapp.di;

import com.altieri.weatherapp.App;
import com.altieri.weatherapp.di.module.AppModule;
import com.altieri.weatherapp.di.module.DataModule;
import com.altieri.weatherapp.di.module.NetModule;
import com.altieri.weatherapp.di.subcomponent.AppBaseActivitySubComponent;
import com.altieri.weatherapp.di.subcomponent.MainActivitySubComponent;
import com.altieri.weatherapp.ui.widget.daily_forecast.DailyForecastWidget;
import com.altieri.weatherapp.ui.widget.hours_forecast.HoursForecastWidget;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {
        MainActivitySubComponent.ComponentModule.class,
        AppBaseActivitySubComponent.ComponentModule.class,
        AppModule.class,
        DataModule.class,
        NetModule.class,
        AndroidSupportInjectionModule.class
})

public interface AppComponent {
    void inject(App app);

    void inject(DailyForecastWidget widget);
    void inject(HoursForecastWidget widget);
}

