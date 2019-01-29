package com.doubleapaper.weathertoday.Object;

import com.google.gson.annotations.SerializedName;

public class WindSpeed{

	@SerializedName("Value")
	private double value;

	@SerializedName("Unit")
	private String unit;

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
			"WindSpeed{" + 
			"value = '" + value + '\'' + 
			",unit = '" + unit + '\'' + 
			"}";
		}
}