package com.altieri.weatherapp.ui.widget.daily_forecast;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.altieri.weatherapp.App;
import com.altieri.weatherapp.R;
import com.altieri.weatherapp.bl.model.forecast.DailyForecast;
import com.altieri.weatherapp.bl.model.forecast.Weather;
import com.altieri.weatherapp.ui.widget.hours_forecast.HoursForecastWidget;

import java.util.ArrayList;

import javax.inject.Inject;

public class DailyForecastWidget extends LinearLayout implements DailyForecastPresenter.DailyForecastView {

    private TextView mDate;
    private HoursForecastWidget mHoursForecast;

    @Inject
    DailyForecastPresenter mPresenter;

    public DailyForecastWidget(Context context) {
        super(context);
        init(context);
    }

    public DailyForecastWidget(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public DailyForecastWidget(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public DailyForecastWidget(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context) {
        // I'm using the "context hack" as mentioned here
        // https://github.com/google/dagger/issues/720#issuecomment-298347606
        // to avoid having to pass the presenter to a method or having the presentation logic
        // in the container (activity in this case)
        ((App)context.getApplicationContext()).getAppComponent().inject(this);
        inflateLayout(context);
    }

    private void inflateLayout(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.daily_forecast, this, true);
        mDate = (TextView) root.findViewById(R.id.forecast_date);
        mHoursForecast = (HoursForecastWidget) root.findViewById(R.id.forecast_hours_list);
    }

    public void setDailyForecast(DailyForecast dailyForecast) {
        mPresenter.showForecast(this, dailyForecast);
    }

    @Override
    public void showDate(String date) {
        mDate.setText(date);
    }

    @Override
    public void showHoursForecast(ArrayList<Weather> weathers) {
        mHoursForecast.setHoursForecast(weathers);
    }

}
