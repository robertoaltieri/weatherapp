package com.altieri.weatherapp.ui.activity.city;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.altieri.weatherapp.R;

import java.util.ArrayList;

public class SelectCityAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final @NonNull ArrayList<String> mList;
    private final SelectCityPresenter mPresenter;

    public SelectCityAdapter(SelectCityPresenter presenter) {
        mPresenter = presenter;
        mList = new ArrayList<>();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.select_city, parent, false);
        return new SelectCityViewHolder(itemView, mPresenter);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((SelectCityViewHolder) holder).setCity(mList.get(position));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void add(String city) {
        mList.add(city);
        notifyItemInserted(mList.size() - 1);
    }

    public void clear() {
        mList.clear();
        notifyDataSetChanged();
    }
}
