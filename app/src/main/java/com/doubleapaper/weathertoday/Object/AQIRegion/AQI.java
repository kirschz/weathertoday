package com.doubleapaper.weathertoday.Object.AQIRegion;


import com.google.gson.annotations.SerializedName;


public class AQI{

	@SerializedName("aqi")
	private String aqi;

	@SerializedName("Level")
	private String level;

	public void setAqi(String aqi){
		this.aqi = aqi;
	}

	public String getAqi(){
		return aqi;
	}

	public void setLevel(String level){
		this.level = level;
	}

	public String getLevel(){
		return level;
	}

	@Override
 	public String toString(){
		return 
			"AQI{" + 
			"aqi = '" + aqi + '\'' + 
			",level = '" + level + '\'' + 
			"}";
		}
}