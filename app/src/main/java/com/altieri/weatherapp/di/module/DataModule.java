package com.altieri.weatherapp.di.module;

import android.content.Context;
import android.content.res.AssetManager;

import com.altieri.weatherapp.di.subcomponent.AppBaseActivitySubComponent;
import com.altieri.weatherapp.di.subcomponent.MainActivitySubComponent;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(subcomponents = {
        MainActivitySubComponent.class,
        AppBaseActivitySubComponent.class
})
public class DataModule {
    @Provides
    @Singleton
    static AssetManager providesAssetManager(Context context) {
        return context.getApplicationContext().getAssets();
    }

}
