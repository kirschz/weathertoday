package com.doubleapaper.weathertoday;


import com.google.gson.annotations.SerializedName;

public class UserData{

	@SerializedName("Email")
	private String email;

	@SerializedName("FirstName")
	private String firstName;

	@SerializedName("LastName")
	private String lastName;

	@SerializedName("userid")
	private int userid;

	public void setEmail(String email){
		this.email = email;
	}

	public String getEmail(){
		return email;
	}

	public void setFirstName(String firstName){
		this.firstName = firstName;
	}

	public String getFirstName(){
		return firstName;
	}

	public void setLastName(String lastName){
		this.lastName = lastName;
	}

	public String getLastName(){
		return lastName;
	}

	public void setUserid(int userid){
		this.userid = userid;
	}

	public int getUserid(){
		return userid;
	}

	@Override
 	public String toString(){
		return 
			"UserDatas{" + 
			"email = '" + email + '\'' + 
			",firstName = '" + firstName + '\'' + 
			",lastName = '" + lastName + '\'' + 
			",userid = '" + userid + '\'' + 
			"}";
		}
}