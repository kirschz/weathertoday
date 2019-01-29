package com.doubleapaper.weathertoday.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.doubleapaper.weathertoday.R;

public class WeatherListItems extends BaseCustomViewGroup {
    private TextView tvWeatherStationNameTh;
    private TextView tvWeatherItemTemperature;
    private LinearLayout viewWeatherListItemDetail;

    public WeatherListItems(Context context) {
        super(context);
        initInflate();
        initInstances();
    }

    public WeatherListItems(Context context, AttributeSet attrs) {
        super(context, attrs);
        initInflate();
        initInstances();
    }

    public WeatherListItems(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initInflate();
        initInstances();
    }

    public WeatherListItems(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initInflate();
        initInstances();
    }


    public void setTvWeatherStationNameTh(String tvWeatherStationNameTh) {
        this.tvWeatherStationNameTh.setText(tvWeatherStationNameTh);
    }

    public void setTvWeatherItemTemperature(String tvWeatherItemTemperature) {
        this.tvWeatherItemTemperature.setText(tvWeatherItemTemperature);
    }

    public void setViewWeatherListItemDetail(LinearLayout viewWeatherListItemDetail) {
        this.viewWeatherListItemDetail = viewWeatherListItemDetail;
    }
    private void initInflate() {
        inflate(getContext(), R.layout.weather_item, this);
    }
    private void initInstances() {
        tvWeatherStationNameTh = (TextView) findViewById(R.id.tvWeatherStationNameTh);
        tvWeatherItemTemperature = (TextView) findViewById(R.id.tvWeatherItemTemperature);
        viewWeatherListItemDetail = (LinearLayout) findViewById(R.id.viewWeatherListItemDetail);
    }
}
