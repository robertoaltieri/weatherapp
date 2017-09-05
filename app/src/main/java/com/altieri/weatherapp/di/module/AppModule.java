package com.altieri.weatherapp.di.module;

import android.content.Context;

import com.altieri.weatherapp.App;
import com.altieri.weatherapp.di.subcomponent.AppBaseActivitySubComponent;
import com.altieri.weatherapp.di.subcomponent.MainActivitySubComponent;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

@Module(subcomponents = {
        MainActivitySubComponent.class,
        AppBaseActivitySubComponent.class
})
public class AppModule {

    private final App mApplication;

    public AppModule(App application) {
        mApplication = application;
    }

    @Provides
    CompositeDisposable providesCompositeDisposable() {
        return new CompositeDisposable();
    }

    @Provides
    @Singleton
    Context providesContext() {
        return mApplication.getApplicationContext();
    }

}
