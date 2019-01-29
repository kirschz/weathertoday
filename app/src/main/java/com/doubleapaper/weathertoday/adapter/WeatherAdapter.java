package com.doubleapaper.weathertoday.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.doubleapaper.weathertoday.Object.StationsItem;
import com.doubleapaper.weathertoday.view.WeatherListItems;

import java.util.List;

public class WeatherAdapter extends BaseAdapter {

    List<StationsItem> list = null;

    public WeatherAdapter(List<StationsItem> list) {
        this.list = list;
    }

    @Override
    public int getCount() {
        if (list == null)
            return 0;
        else return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        WeatherListItems item;
        if (view != null)
            item = (WeatherListItems) view;
        else
            item = new WeatherListItems(viewGroup.getContext());

        StationsItem weatherDao = (StationsItem) getItem(i);
        if (weatherDao.getObserve() != null)
            item.setTvWeatherItemTemperature(weatherDao.getObserve().getTemperature().getValue() + "");
        else
            item.setTvWeatherItemTemperature(weatherDao.getTemperatureValue() + "");
        item.setTvWeatherStationNameTh(weatherDao.getStationNameTh());

        return item;
    }
}

