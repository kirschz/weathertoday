package com.doubleapaper.weathertoday.Object;

import android.util.Log;

import com.google.gson.annotations.SerializedName;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class Header extends RealmObject {
	@PrimaryKey
	@SerializedName("LastBuiltDate")
	private String lastBuiltDate;

	@SerializedName("Description")
	private String description;

	@SerializedName("CopyRight")
	private String copyRight;

	@Required
	@SerializedName("Title")
	private String title;

	@SerializedName("Uri")
	private String uri;

	@SerializedName("Generator")
	private String generator;

	public void setLastBuiltDate(String lastBuiltDate){
		this.lastBuiltDate = lastBuiltDate;
	}

	public String getLastBuiltDate(){
		return lastBuiltDate;
	}

	public void setDescription(String description){
		this.description = description;
	}

	public String getDescription(){
		return description;
	}

	public void setCopyRight(String copyRight){
		this.copyRight = copyRight;
	}

	public String getCopyRight(){
		return copyRight;
	}

	public void setTitle(String title){
		this.title = title;
	}

	public String getTitle(){
		return title;
	}

	public void setUri(String uri){
		this.uri = uri;
	}

	public String getUri(){
		return uri;
	}

	public void setGenerator(String generator){
		this.generator = generator;
	}

	public String getGenerator(){
		return generator;
	}

	@Override
 	public String toString(){
		return 
			"Header{" + 
			"lastBuiltDate = '" + lastBuiltDate + '\'' + 
			",description = '" + description + '\'' + 
			",copyRight = '" + copyRight + '\'' + 
			",title = '" + title + '\'' + 
			",uri = '" + uri + '\'' + 
			",generator = '" + generator + '\'' + 
			"}";
		}
	public void insertData(Realm realm, final Header header) {

		realm.executeTransactionAsync(
				new Realm.Transaction() {
					@Override
					public void execute(Realm realm) {
						Header u = realm.createObject(Header.class,header.getLastBuiltDate());
						u.setTitle(header.getTitle());
						u.setCopyRight(header.getCopyRight());
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
				});
	}
}