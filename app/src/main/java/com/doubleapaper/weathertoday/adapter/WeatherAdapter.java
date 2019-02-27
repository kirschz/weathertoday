package com.doubleapaper.weathertoday.adapter;

import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.doubleapaper.weathertoday.AQIStationActivity;
import com.doubleapaper.weathertoday.MainActivity;
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
    public View getView(int i, final View view, final ViewGroup viewGroup) {
        WeatherListItems item;
        if (view != null)
            item = (WeatherListItems) view;
        else
            item = new WeatherListItems(viewGroup.getContext());

        StationsItem weatherDao = (StationsItem) getItem(i);
        if (weatherDao.getObserve() != null) {
            item.setTvWeatherItemTemperature(weatherDao.getObserve().getTemperature().getValue() + "");
            item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent myIntent = new Intent(viewGroup.getContext(), AQIStationActivity.class);
                    myIntent.putExtra("key", "value");
                    viewGroup.getContext().startActivity(myIntent);
                }
            });
        }else {
            item.setTvWeatherItemTemperature(weatherDao.getTemperatureValue() + "");
            item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent myIntent = new Intent(viewGroup.getContext(), AQIStationActivity.class);
                    myIntent.putExtra("key", "value");
                    viewGroup.getContext().startActivity(myIntent);
                }
            });
        }
        item.setTvWeatherStationNameTh(weatherDao.getStationNameTh());

        return item;
    }
}

