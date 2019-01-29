package com.doubleapaper.weathertoday.Object;

import android.util.Log;

import com.google.gson.annotations.SerializedName;



public class Temperature  {

	@SerializedName("Value")
	private double value;

	@SerializedName("Unit")
	private String unit;

	private String province;

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public void setValue(double value){
		this.value = value;
	}

	public double getValue(){
		return value;
	}

	public void setUnit(String unit){
		this.unit = unit;
	}

	public String getUnit(){
		return unit;
	}


	@Override
 	public String toString(){
		return 
			"Temperature{" + 
			"value = '" + value + '\'' + 
			",unit = '" + unit + '\'' + 
			"}";
		}

}