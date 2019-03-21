package com.doubleapaper.weathertoday.Object.AQIStation;


import com.google.gson.annotations.SerializedName;



public class LastUpdate  {

	@SerializedName("date")
	private String date;

	@SerializedName("NO2")
	private NO2 nO2;

	@SerializedName("O3")
	private O3 o3;

	@SerializedName("PM25")
	private PM25 pM25;

	@SerializedName("SO2")
	private SO2 sO2;

	@SerializedName("PM10")
	private PM10 pM10;

	@SerializedName("AQI")
	private AQI aQI;

	@SerializedName("time")
	private String time;

	@SerializedName("CO")
	private CO cO;




	public void setDate(String date){
		this.date = date;
	}

	public String getDate(){
		return date;
	}

	public void setNO2(NO2 nO2){
		this.nO2 = nO2;
	}

	public NO2 getNO2(){
		return nO2;
	}

	public void setO3(O3 o3){
		this.o3 = o3;
	}

	public O3 getO3(){
		return o3;
	}

	public void setPM25(PM25 pM25){
		this.pM25 = pM25;
	}

	public PM25 getPM25(){
		return pM25;
	}

	public void setSO2(SO2 sO2){
		this.sO2 = sO2;
	}

	public SO2 getSO2(){
		return sO2;
	}

	public void setPM10(PM10 pM10){
		this.pM10 = pM10;
	}

	public PM10 getPM10(){
		return pM10;
	}

	public void setAQI(AQI aQI){
		this.aQI = aQI;
	}

	public AQI getAQI(){
		return aQI;
	}

	public void setTime(String time){
		this.time = time;
	}

	public String getTime(){
		return time;
	}

	public void setCO(CO cO){
		this.cO = cO;
	}

	public CO getCO(){
		return cO;
	}

	@Override
 	public String toString(){
		return 
			"LastUpdate{" + 
			"date = '" + date + '\'' + 
			",nO2 = '" + nO2 + '\'' + 
			",o3 = '" + o3 + '\'' + 
			",pM25 = '" + pM25 + '\'' + 
			",sO2 = '" + sO2 + '\'' + 
			",pM10 = '" + pM10 + '\'' + 
			",aQI = '" + aQI + '\'' + 
			",time = '" + time + '\'' + 
			",cO = '" + cO + '\'' + 
			"}";
		}
}