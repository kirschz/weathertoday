package com.doubleapaper.weathertoday.Object;

import com.google.gson.annotations.SerializedName;

public class WindDirection{

	@SerializedName("Value")
	private String value;

	@SerializedName("Unit")
	private String unit;

	public void setValue(String value){
		this.value = value;
	}

	public String getValue(){
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
			"WindDirection{" + 
			"value = '" + value + '\'' + 
			",unit = '" + unit + '\'' + 
			"}";
		}
}