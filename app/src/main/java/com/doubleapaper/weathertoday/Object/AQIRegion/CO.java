package com.doubleapaper.weathertoday.Object.AQIRegion;


import com.google.gson.annotations.SerializedName;

public class CO{

	@SerializedName("unit")
	private String unit;

	@SerializedName("value")
	private String value;

	public void setUnit(String unit){
		this.unit = unit;
	}

	public String getUnit(){
		return unit;
	}

	public void setValue(String value){
		this.value = value;
	}

	public String getValue(){
		return value;
	}

	@Override
 	public String toString(){
		return 
			"CO{" + 
			"unit = '" + unit + '\'' + 
			",value = '" + value + '\'' + 
			"}";
		}
}