package com.doubleapaper.weathertoday.Object.AQIStation;

import android.util.Log;

import com.google.gson.annotations.SerializedName;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class AQI extends RealmObject {

	@SerializedName("aqi")
	private String aqi;

	@SerializedName("Level")
	private String level;

	public String getStationID() {
		return stationID;
	}

	public void setStationID(String stationID) {
		this.stationID = stationID;
	}

	@PrimaryKey
	private String stationID;

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
	public void insertData(Realm realm, final AQI aqi,final String stationID) {
		realm.executeTransaction(new Realm.Transaction() {
			@Override
			public void execute(Realm realm) {
				AQI u = realm.createObject(AQI.class, stationID);
				u.aqi = aqi.aqi;
				u.level =aqi.level;
				Log.i("joke","aqi ");
			}
		});
	}
}