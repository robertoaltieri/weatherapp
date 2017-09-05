package com.altieri.weatherapp.ui.activity.main;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.altieri.weatherapp.bl.model.forecast.DailyForecast;
import com.altieri.weatherapp.ui.widget.daily_forecast.DailyForecastWidget;

class DailyForecastViewHolder extends RecyclerView.ViewHolder {
    private final DailyForecastWidget mRoot;

    public DailyForecastViewHolder(View itemView) {
        super(itemView);
        mRoot = (DailyForecastWidget) itemView;
    }

    public void setDailyForecast(DailyForecast dailyForecast) {
        mRoot.setDailyForecast(dailyForecast);
    }
}
