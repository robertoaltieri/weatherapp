package com.altieri.weatherapp.ui.activity.main;

import android.app.SearchManager;
import android.app.SearchableInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.TextView;

import com.altieri.weatherapp.R;
import com.altieri.weatherapp.bl.model.forecast.DailyForecast;
import com.altieri.weatherapp.ui.activity.AppBaseActivity;
import com.altieri.weatherapp.ui.activity.city.SelectCityAdapter;
import com.altieri.weatherapp.ui.activity.city.SelectCityPresenter;

import javax.inject.Inject;

public class MainActivity extends AppBaseActivity implements MainActivityView {

    @Inject
    MainActivityPresenter mMainActivityPresenter;
    @Inject
    SelectCityPresenter mSelectCityPresenter;

    private ForecastsAdapter mForecastsAdapter;
    private SelectCityAdapter mSelectCityAdapter;
    private RecyclerView mCityList;
    private RecyclerView mDailyForecastList;
    private SearchView mSearchView;
    private TextView mCity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mForecastsAdapter = new ForecastsAdapter();
        mDailyForecastList = (RecyclerView) findViewById(R.id.forecast_list);
        mDailyForecastList.setAdapter(mForecastsAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mDailyForecastList.setLayoutManager(layoutManager);

        mSelectCityAdapter = new SelectCityAdapter(mSelectCityPresenter);
        mCityList = (RecyclerView) findViewById(R.id.city_list);
        mCityList.setAdapter(mSelectCityAdapter);
        layoutManager = new LinearLayoutManager(this);
        mCityList.setLayoutManager(layoutManager);

        mCity = (TextView) findViewById(R.id.forecast_city);

        mSelectCityPresenter.setView(this);
        handleIntent(getIntent());

    }

    @Override
    protected void onNewIntent(Intent intent) {
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {

        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            Log.d("test", query);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        mSearchView = (SearchView) menu.findItem(R.id.search).getActionView();
        ComponentName componentName = getComponentName();
        SearchableInfo searchableInfo = searchManager.getSearchableInfo(componentName);
        mSearchView.setSearchableInfo(searchableInfo);
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextChange(String query) {
                // TODO not handling the lifecycle properly
                mSelectCityPresenter.showCities(query);
                return true;
            }

            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }
        });

        return true;
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        mCompositeDisposable.add(mMainActivityPresenter.showForecast(this));
    }

    @Override
    public void clearForecast() {
        mForecastsAdapter.clear();
    }

    @Override
    public void addDailyForecast(DailyForecast dailyForecast) {
        mForecastsAdapter.add(dailyForecast);
    }

    @Override
    public void setForecastVisibility(boolean visible) {
        mDailyForecastList.setVisibility(visible ? View.VISIBLE : View.GONE);
    }

    @Override
    public void clearCities() {
        mSelectCityAdapter.clear();
    }

    @Override
    public void setSelectCityVisibility(boolean visible) {
        mCityList.setVisibility(visible ? View.VISIBLE : View.GONE);
    }

    @Override
    public void addCity(String city) {
        mSelectCityAdapter.add(city);
    }

    @Override
    public void setCity(String city) {
        // I'm not handling properly the lifecycle here
        // I would need to use an RxBus to keep this simple
        // I didn't have the time to do it
        mSearchView.clearFocus();
        mSearchView.setQuery("", false);
        mSearchView.setIconified(true);
        mMainActivityPresenter.showForecast(this, city);
    }

    @Override
    public void showCity(String city) {
        mCity.setText(city);
    }

}
