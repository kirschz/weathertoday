package com.doubleapaper.weathertoday.Object.AQIStation;


import com.google.gson.annotations.SerializedName;


public class PM10{

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
			"PM10{" + 
			"unit = '" + unit + '\'' + 
			",value = '" + value + '\'' + 
			"}";
		}
}