package com.doubleapaper.weathertoday.Object.AQIStation;

import com.google.gson.annotations.SerializedName;

import java.util.UUID;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;


public class AQIStation extends RealmObject {

	@SerializedName("nameTH")
	private String nameTH;

	@Ignore
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

	@PrimaryKey
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
			"AQIStation{" + 
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
	public void insertData(Realm realm, final AQIStation aqiStation) {
		realm.executeTransaction(new Realm.Transaction() {
			@Override
			public void execute(Realm realm) {
				AQIStation u = realm.createObject(AQIStation.class, aqiStation.stationID);
				u.nameTH = aqiStation.nameTH;
				u.areaTH = aqiStation.areaTH;
				u.lat = aqiStation.lat;
				u.jsonMemberLong = aqiStation.jsonMemberLong;

			}
		});
	}
}