package com.doubleapaper.weathertoday.Object.AQIRegion;

import java.util.List;

import com.google.gson.annotations.SerializedName;


public class AQIRegion{

	@SerializedName("nameTH")
	private String nameTH;

	@SerializedName("regionID")
	private String regionID;

	@SerializedName("nameEN")
	private String nameEN;

	@SerializedName("stations")
	private List<StationsItem> stations;

	public void setNameTH(String nameTH){
		this.nameTH = nameTH;
	}

	public String getNameTH(){
		return nameTH;
	}

	public void setRegionID(String regionID){
		this.regionID = regionID;
	}

	public String getRegionID(){
		return regionID;
	}

	public void setNameEN(String nameEN){
		this.nameEN = nameEN;
	}

	public String getNameEN(){
		return nameEN;
	}

	public void setStations(List<StationsItem> stations){
		this.stations = stations;
	}

	public List<StationsItem> getStations(){
		return stations;
	}

	@Override
 	public String toString(){
		return 
			"AQIRegion{" + 
			"nameTH = '" + nameTH + '\'' + 
			",regionID = '" + regionID + '\'' + 
			",nameEN = '" + nameEN + '\'' + 
			",stations = '" + stations + '\'' + 
			"}";
		}
}