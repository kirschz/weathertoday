package com.doubleapaper.weathertoday.Object;

import android.util.Log;
import android.widget.Toast;

import java.util.List;
import java.util.UUID;

import com.google.gson.annotations.SerializedName;


public class WeatherResponse   {
	@SerializedName("Stations")
	private List<StationsItem> stations;

	@SerializedName("Header")
	private Header header;

	public void setStations(List<StationsItem> stations){
		this.stations = stations;
	}

	public List<StationsItem> getStations(){
		return stations;
	}

	public void setHeader(Header header){
		this.header = header;
	}

	public Header getHeader(){
		return header;
	}

	@Override
 	public String toString(){
		return 
			"WeatherResponse{" + 
			"stations = '" + stations + '\'' + 
			",header = '" + header + '\'' + 
			"}";
		}

}