package com.altieri.weatherapp.ui.widget.hours_forecast;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.altieri.weatherapp.App;
import com.altieri.weatherapp.R;
import com.altieri.weatherapp.bl.model.forecast.Weather;

import java.util.ArrayList;

import javax.inject.Inject;

public class HoursForecastWidget extends LinearLayout implements HoursForecastPresenter.HoursForecastView {

    @Inject
    HoursForecastPresenter mPresenter;
    private HoursForecastAdapter mAdapter;

    public HoursForecastWidget(Context context) {
        super(context);
        init(context);
    }

    public HoursForecastWidget(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public HoursForecastWidget(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        ((App)context.getApplicationContext()).getAppComponent().inject(this);
        inflateLayout(context);
    }

    private void inflateLayout(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.hours_forecast, this, true);
        RecyclerView hoursForecastList = (RecyclerView) root.findViewById(R.id.hours_forecast_list);
        mAdapter = new HoursForecastAdapter();
        hoursForecastList.setAdapter(mAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        hoursForecastList.setLayoutManager(layoutManager);

    }

    public void setHoursForecast(ArrayList<Weather> weathers) {
        mPresenter.showHoursForecast(this, weathers);
    }

    @Override
    public void addHour(String icon, String time, String temperature) {
        mAdapter.add(icon, time, temperature);
    }

    @Override
    public void clear() {
        mAdapter.clear();
    }
}
