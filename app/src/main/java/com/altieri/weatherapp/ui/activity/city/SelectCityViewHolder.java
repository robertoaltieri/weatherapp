package com.altieri.weatherapp.ui.activity.city;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.altieri.weatherapp.R;
import com.jakewharton.rxbinding2.view.RxView;

class SelectCityViewHolder extends RecyclerView.ViewHolder {
    private final TextView mCity;
    private final SelectCityPresenter mPresenter;

    SelectCityViewHolder(View itemView, SelectCityPresenter presenter) {
        super(itemView);
        mCity = (TextView) itemView.findViewById(R.id.city);
        mPresenter = presenter;
        RxView.clicks(mCity).subscribe(o -> mPresenter.setCity(mCity.getText().toString()));
    }

    public void setCity(String city) {
        mCity.setText(city);
    }
}
