package com.altieri.weatherapp.ui.activity.main;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.altieri.weatherapp.bl.model.forecast.DailyForecast;
import com.altieri.weatherapp.ui.widget.daily_forecast.DailyForecastWidget;

import java.util.ArrayList;

class ForecastsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final @NonNull ArrayList<DailyForecast> mList;

    ForecastsAdapter() {
        mList = new ArrayList<>();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = new DailyForecastWidget(parent.getContext());
        return new DailyForecastViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((DailyForecastViewHolder) holder).setDailyForecast(mList.get(position));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void add(DailyForecast dailyForecast) {
        mList.add(dailyForecast);
        notifyItemInserted(mList.size() - 1);
    }

    void clear() {
        mList.clear();
        notifyDataSetChanged();
    }
}
