package com.doubleapaper.weathertoday.Object.AQIRegion;

import com.google.gson.annotations.SerializedName;
public class StationsItem{

	@SerializedName("nameTH")
	private String nameTH;

	@SerializedName("LastUpdate")
	private LastUpdate lastUpdate;

	@SerializedName("stationType")
	private String stationType;

	@SerializedName("areaTH")
	private String areaTH;

	@SerializedName("nameEN")
	private String nameEN;

	@SerializedName("areaEN")
	private String areaEN;

	@SerializedName("lat")
	private String lat;

	@SerializedName("long")
	private String jsonMemberLong;

	@SerializedName("stationID")
	private String stationID;

	public void setNameTH(String nameTH){
		this.nameTH = nameTH;
	}

	public String getNameTH(){
		return nameTH;
	}

	public void setLastUpdate(LastUpdate lastUpdate){
		this.lastUpdate = lastUpdate;
	}

	public LastUpdate getLastUpdate(){
		return lastUpdate;
	}

	public void setStationType(String stationType){
		this.stationType = stationType;
	}

	public String getStationType(){
		return stationType;
	}

	public void setAreaTH(String areaTH){
		this.areaTH = areaTH;
	}

	public String getAreaTH(){
		return areaTH;
	}

	public void setNameEN(String nameEN){
		this.nameEN = nameEN;
	}

	public String getNameEN(){
		return nameEN;
	}

	public void setAreaEN(String areaEN){
		this.areaEN = areaEN;
	}

	public String getAreaEN(){
		return areaEN;
	}

	public void setLat(String lat){
		this.lat = lat;
	}

	public String getLat(){
		return lat;
	}

	public void setJsonMemberLong(String jsonMemberLong){
		this.jsonMemberLong = jsonMemberLong;
	}

	public String getJsonMemberLong(){
		return jsonMemberLong;
	}

	public void setStationID(String stationID){
		this.stationID = stationID;
	}

	public String getStationID(){
		return stationID;
	}

	@Override
 	public String toString(){
		return 
			"StationsItem{" + 
			"nameTH = '" + nameTH + '\'' + 
			",lastUpdate = '" + lastUpdate + '\'' + 
			",stationType = '" + stationType + '\'' + 
			",areaTH = '" + areaTH + '\'' + 
			",nameEN = '" + nameEN + '\'' + 
			",areaEN = '" + areaEN + '\'' + 
			",lat = '" + lat + '\'' + 
			",long = '" + jsonMemberLong + '\'' + 
			",stationID = '" + stationID + '\'' + 
			"}";
		}
}