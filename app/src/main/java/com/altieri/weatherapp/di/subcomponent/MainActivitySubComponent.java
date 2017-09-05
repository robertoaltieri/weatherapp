package com.altieri.weatherapp.di.subcomponent;

import android.app.Activity;

import com.altieri.weatherapp.ui.activity.main.MainActivity;
import com.altieri.weatherapp.ui.activity.main.MainActivityView;

import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.ActivityKey;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;

@Subcomponent(modules = MainActivitySubComponent.ComponentModule.class)
public interface MainActivitySubComponent extends AndroidInjector<MainActivity> {
    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<MainActivity> {
    }

    @Module
    abstract class ComponentModule {
        @Binds
        abstract MainActivityView providesView(MainActivity featureActivity);

        @Binds
        @IntoMap
        @ActivityKey(MainActivity.class)
        abstract AndroidInjector.Factory<? extends Activity>
        bindMainActivityInjectorFactory(MainActivitySubComponent.Builder builder);
    }

}
