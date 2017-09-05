package com.altieri.weatherapp.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.altieri.weatherapp.AppCompositeDisposable;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

public abstract class AppBaseActivity extends AppCompatActivity {
    @Inject
    protected AppCompositeDisposable mCompositeDisposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        mCompositeDisposable.reset();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mCompositeDisposable.dispose();
    }
}
