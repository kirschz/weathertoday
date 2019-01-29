package com.doubleapaper.weathertoday.Object;

import android.util.Log;

import com.google.gson.annotations.SerializedName;

public class Observe  {

	@SerializedName("Temperature")
	private Temperature temperature;

	@SerializedName("DiffMinTemperature")
	private DiffMinTemperature diffMinTemperature;

	@SerializedName("RelativeHumidity")
	private RelativeHumidity relativeHumidity;

	@SerializedName("MaxTemperature")
	private MaxTemperature maxTemperature;

	@SerializedName("DiffMaxTemperature")
	private DiffMaxTemperature diffMaxTemperature;

	@SerializedName("Rainfall")
	private Rainfall rainfall;

	@SerializedName("MeanSeaLevelPressure")
	private MeanSeaLevelPressure meanSeaLevelPressure;

	@SerializedName("Time")
	private String time;

	@SerializedName("WindSpeed")
	private WindSpeed windSpeed;

	@SerializedName("MinTemperature")
	private MinTemperature minTemperature;

	@SerializedName("WindDirection")
	private WindDirection windDirection;

	private int province;

	public int getProvince() {
		return province;
	}

	public void setProvince(int province) {
		this.province = province;
	}



	public void setTemperature(Temperature temperature){
		this.temperature = temperature;
	}

	public Temperature getTemperature(){
		return temperature;
	}

	public void setDiffMinTemperature(DiffMinTemperature diffMinTemperature){
		this.diffMinTemperature = diffMinTemperature;
	}

	public DiffMinTemperature getDiffMinTemperature(){
		return diffMinTemperature;
	}

	public void setRelativeHumidity(RelativeHumidity relativeHumidity){
		this.relativeHumidity = relativeHumidity;
	}

	public RelativeHumidity getRelativeHumidity(){
		return relativeHumidity;
	}

	public void setMaxTemperature(MaxTemperature maxTemperature){
		this.maxTemperature = maxTemperature;
	}

	public MaxTemperature getMaxTemperature(){
		return maxTemperature;
	}

	public void setDiffMaxTemperature(DiffMaxTemperature diffMaxTemperature){
		this.diffMaxTemperature = diffMaxTemperature;
	}

	public DiffMaxTemperature getDiffMaxTemperature(){
		return diffMaxTemperature;
	}

	public void setRainfall(Rainfall rainfall){
		this.rainfall = rainfall;
	}

	public Rainfall getRainfall(){
		return rainfall;
	}

	public void setMeanSeaLevelPressure(MeanSeaLevelPressure meanSeaLevelPressure){
		this.meanSeaLevelPressure = meanSeaLevelPressure;
	}

	public MeanSeaLevelPressure getMeanSeaLevelPressure(){
		return meanSeaLevelPressure;
	}

	public void setTime(String time){
		this.time = time;
	}

	public String getTime(){
		return time;
	}

	public void setWindSpeed(WindSpeed windSpeed){
		this.windSpeed = windSpeed;
	}

	public WindSpeed getWindSpeed(){
		return windSpeed;
	}

	public void setMinTemperature(MinTemperature minTemperature){
		this.minTemperature = minTemperature;
	}

	public MinTemperature getMinTemperature(){
		return minTemperature;
	}

	public void setWindDirection(WindDirection windDirection){
		this.windDirection = windDirection;
	}

	public WindDirection getWindDirection(){
		return windDirection;
	}

	@Override
 	public String toString(){
		return 
			"Observe{" + 
			"temperature = '" + temperature + '\'' + 
			",diffMinTemperature = '" + diffMinTemperature + '\'' + 
			",relativeHumidity = '" + relativeHumidity + '\'' + 
			",maxTemperature = '" + maxTemperature + '\'' + 
			",diffMaxTemperature = '" + diffMaxTemperature + '\'' + 
			",rainfall = '" + rainfall + '\'' + 
			",meanSeaLevelPressure = '" + meanSeaLevelPressure + '\'' + 
			",time = '" + time + '\'' + 
			",windSpeed = '" + windSpeed + '\'' + 
			",minTemperature = '" + minTemperature + '\'' + 
			",windDirection = '" + windDirection + '\'' + 
			"}";
		}

}