package com.altieri.weatherapp.ui.widget.hours_forecast;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.altieri.weatherapp.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

class HourForecastViewHolder extends RecyclerView.ViewHolder {

    private final ImageView mImage;
    private final TextView mHour;
    private final TextView mTemperature;

    public HourForecastViewHolder(View itemView) {
        super(itemView);
        mImage = (ImageView) itemView.findViewById(R.id.weather_icon);
        mHour = (TextView) itemView.findViewById(R.id.hour);
        mTemperature = (TextView) itemView.findViewById(R.id.temperature);
    }

    public void setHourForecast(String icon, String time, String temperature) {
        Context context = mImage.getContext();
        if (icon != null) {
            String url = context.getString(R.string.weather_icon_url, icon);
            // I should have handled the error showing a placeholder
            Glide.with(context)
                    .load(url)
                    .fitCenter()
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .into(mImage);
        } else {
            mImage.setVisibility(View.INVISIBLE);
        }
        mHour.setText(time);
        mTemperature.setText(temperature);
    }

}
