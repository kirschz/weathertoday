package com.doubleapaper.weathertoday.Object;

import android.util.Log;

import com.google.gson.annotations.SerializedName;

import java.util.UUID;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;

public class StationsItem extends RealmObject {

	@SerializedName("StationNameEng")
	private String stationNameEng;

	@PrimaryKey
	@SerializedName("WmoNumber")
	private String wmoNumber;

	@Ignore
	@SerializedName("Observe")
	private Observe observe;

	@Ignore
	@SerializedName("Latitude")
	private Latitude latitude;

	@SerializedName("StationNameTh")
	private String stationNameTh;

	@Ignore
	@SerializedName("Longitude")
	private Longitude longitude;

	@SerializedName("Province")
	private String province;


	private double TemperatureValue;
	private String Lat;
	private String Lng;

	public void setLatitude(String Latitude) {
		Lat = Latitude;
	}
	public String getLat(){
		return Lat;
	}

	public void setLongitude(String Longitude) {
		Lng = Longitude;
	}
	public String getLng(){
		return Lng;
	}


	public void setStationNameEng(String stationNameEng){
		this.stationNameEng = stationNameEng;
	}

	public String getStationNameEng(){
		return stationNameEng;
	}

	public void setWmoNumber(String wmoNumber){
		this.wmoNumber = wmoNumber;
	}

	public String getWmoNumber(){
		return wmoNumber;
	}

	public void setObserve(Observe observe){
		this.observe = observe;
	}

	public Observe getObserve(){
		return observe;
	}

	public void setLatitude(Latitude latitude){
		this.latitude = latitude;
	}

	public Latitude getLatitude(){
		return latitude;
	}

	public void setStationNameTh(String stationNameTh){
		this.stationNameTh = stationNameTh;
	}

	public String getStationNameTh(){
		return stationNameTh;
	}

	public void setLongitude(Longitude longitude){
		this.longitude = longitude;
	}

	public Longitude getLongitude(){
		return longitude;
	}

	public void setProvince(String province){
		this.province = province;
	}

	public String getProvince(){
		return province;
	}

	public double getTemperatureValue() {
		return TemperatureValue;
	}

	public void setTemperatureValue(double temperatureValue) {
		TemperatureValue = temperatureValue;
	}

	@Override
 	public String toString(){
		return 
			"StationsItem{" + 
			"stationNameEng = '" + stationNameEng + '\'' + 
			",wmoNumber = '" + wmoNumber + '\'' + 
			",observe = '" + observe + '\'' + 
			",latitude = '" + latitude + '\'' + 
			",stationNameTh = '" + stationNameTh + '\'' + 
			",longitude = '" + longitude + '\'' + 
			",province = '" + province + '\'' + 
			"}";
		}
	public void insertData(Realm realm, final StationsItem stationsItem, final double temperatureValue) {
		realm.executeTransaction(new Realm.Transaction() {
			@Override
			public void execute(Realm realm) {
				StationsItem u = realm.createObject(StationsItem.class, UUID.randomUUID().toString());
				u.setStationNameTh(stationsItem.getStationNameTh());
				u.setStationNameEng(stationsItem.getStationNameEng());
				u.setProvince(stationsItem.getProvince());

				u.setTemperatureValue(temperatureValue);
				u.setLatitude(stationsItem.getLatitude().getValue());
				u.setLongitude(stationsItem.getLongitude().getValue());
			}
		});
 		/*realm.executeTransactionAsync(
				new Realm.Transaction() {
					@Override
					public void execute(Realm realm) {
						StationsItem u = realm.createObject(StationsItem.class, UUID.randomUUID().toString());
						u.setStationNameTh(stationsItem.getStationNameTh());
						u.setStationNameEng(stationsItem.getStationNameEng());
						u.setTemperatureValue(temperatureValue);
					}
				}, new Realm.Transaction.OnSuccess() {
					@Override
					public void onSuccess() {
						//Toast.makeText(getApplicationContext(), "Generate Photo complete.", Toast.LENGTH_SHORT).show();
					}

				}, new Realm.Transaction.OnError() {
					@Override
					public void onError(Throwable error) {
						error.printStackTrace();
						//Toast.makeText(getApplicationContext(), "" + error.getMessage(), Toast.LENGTH_SHORT).show();

					}
				});*/
	}
}