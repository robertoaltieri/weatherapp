package com.altieri.weatherapp.di.module;

import android.support.annotation.NonNull;

import com.altieri.weatherapp.AppSchedulers;
import com.altieri.weatherapp.data.gson.AppAdapterFactory;
import com.altieri.weatherapp.data.network.ServiceWeather;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

@Module
public class NetModule {
    private NetModule() {}

    private static final int TIMEOUT = 60; //seconds
    private static final String WEATHER_BASE_URL = "http://samples.openweathermap.org";

    @NonNull
    @Provides
    @Singleton
    static Gson providesGson() {
        return new GsonBuilder()
                .registerTypeAdapterFactory(AppAdapterFactory.create())
                .create();
    }

    @NonNull
    @Provides
    @Singleton
    static AppSchedulers providesAppSchedulers() {
        return new AppSchedulers();
    }

    @NonNull
    @Provides
    @Singleton
    static OkHttpClient providesOkHttpClient() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .addInterceptor(logging)
                .readTimeout(TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
                .connectTimeout(TIMEOUT, TimeUnit.SECONDS);
        return builder.build();
    }

    @Provides
    @Singleton
    static Retrofit.Builder providesRetrofitBuilder(Gson gson, OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient);
    }

    @Provides
    @Singleton
    static ServiceWeather providesServiceWeather(Retrofit.Builder retrofitBuilder) {
        Retrofit retrofit = retrofitBuilder.baseUrl(WEATHER_BASE_URL).build();
        return retrofit.create(ServiceWeather.class);
    }

}
