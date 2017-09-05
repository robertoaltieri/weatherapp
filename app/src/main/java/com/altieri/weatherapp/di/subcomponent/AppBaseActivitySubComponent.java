package com.altieri.weatherapp.di.subcomponent;
import android.app.Activity;

import com.altieri.weatherapp.ui.activity.AppBaseActivity;

import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.ActivityKey;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;

@Subcomponent
public interface AppBaseActivitySubComponent extends AndroidInjector<AppBaseActivity> {
    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<AppBaseActivity> {
    }

    @Module
    abstract class ComponentModule {
        @Binds
        @IntoMap
        @ActivityKey(AppBaseActivity.class)
        abstract AndroidInjector.Factory<? extends Activity>
        bindAppBaseActivityInjectorFactory(AppBaseActivitySubComponent.Builder builder);
    }

}
