package com.altieri.weatherapp.ui.widget.hours_forecast;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.altieri.weatherapp.R;
import com.altieri.weatherapp.data.model.hours_forecast.HourForecastInfo;

import java.util.ArrayList;

class HoursForecastAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final ArrayList<HourForecastInfo> mList;

    HoursForecastAdapter() {
        mList = new ArrayList<>();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.one_hour_forecast, parent, false);

        return new HourForecastViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        HourForecastInfo info = mList.get(position);
        ((HourForecastViewHolder) holder).setHourForecast(info.getIcon(), info.getTime(), info.getTemperature());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void add(String icon, String time, String temperature) {
        mList.add(HourForecastInfo.create(icon, time, temperature));
        notifyItemInserted(mList.size() - 1);
    }

    public void clear() {
        mList.clear();
        notifyDataSetChanged();
    }
}
