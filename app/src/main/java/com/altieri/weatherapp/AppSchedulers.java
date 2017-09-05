package com.altieri.weatherapp;

import javax.inject.Singleton;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

@Singleton
public class AppSchedulers {
    public Scheduler mainThread() {
        return AndroidSchedulers.mainThread();
    }

    public Scheduler network() {
        return Schedulers.io();
    }

    @SuppressWarnings("unused")
    public Scheduler computation() {
        return Schedulers.computation();
    }

}
