package com.doubleapaper.weathertoday;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import com.doubleapaper.weathertoday.Object.AQIRegion.AQIRegion;
import com.doubleapaper.weathertoday.Object.AQIStation.AQI;
import com.doubleapaper.weathertoday.Object.AQIStation.AQIStation;
import com.doubleapaper.weathertoday.Object.Header;
import com.doubleapaper.weathertoday.Object.StationsItem;
import com.doubleapaper.weathertoday.Object.WeatherResponse;
import com.doubleapaper.weathertoday.adapter.WeatherAdapter;

import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import io.realm.Realm;
import io.realm.RealmResults;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class AQIStationActivity extends AppCompatActivity {

    private Realm realm;
    private SweetAlertDialog pDialog;
    private static String URL = "http://air4thai.pcd.go.th/services/";
    private TextView tvAqi;
    private TextView tvName, tvArea;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aqistation);
        realm = Realm.getDefaultInstance();

        tvAqi = findViewById(R.id.tvAqi);
        tvName = findViewById(R.id.tvName);
        tvArea = findViewById(R.id.tvArea);



        pDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
        pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        pDialog.setTitleText("Loading");
        pDialog.setCancelable(false);
        pDialog.show();
        GetData();
    }


    @Override
    protected void onStop() {
        super.onStop();
        realm.close();
    }


    public interface GetAQI {
        @GET("getNewAQI_JSON.php")
        Call<AQIStation> GetAQIStation(@Query("stationID") String stationID);

        @GET("getNewAQI_JSON.php/")
        Call<AQIRegion> GetAQIRegion(@Query("region") int regionID);
    }

    public void GetData(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GetAQI service = retrofit.create(GetAQI.class);
        Call<AQIStation> call = service.GetAQIStation("77t");

        call.enqueue(new Callback<AQIStation>() {
            @Override
            public void onResponse(Call<AQIStation> call, Response<AQIStation> response) {
                Log.i("joke","reponse " + response.message());
                Log.i("joke","reponse " + response.body().getJsonMemberLong());
                if (response.isSuccessful()){
                    clearData();
                    AQIStation aqiStation = response.body();
                    aqiStation.insertData(realm, aqiStation);

                    AQI aqi = aqiStation.getLastUpdate().getAQI();
                    aqi.insertData(realm, aqi, aqiStation.getStationID());
                    if (pDialog.isShowing())
                        pDialog.dismiss();
                    tvAqi.setText(aqi.getAqi());
                    tvArea.setText(aqiStation.getAreaTH());
                    tvName.setText(aqiStation.getNameTH());
                }

            }

            @Override
            public void onFailure(Call<AQIStation> call, Throwable t) {
                Log.i("joke","reponse " + t.getMessage());
                if (pDialog.isShowing())
                    pDialog.dismiss();
                tvAqi.setText("");
                tvArea.setText("");
                tvName.setText("");
            }
        });
    }
    private void clearData() {
        realm.beginTransaction();
        realm.delete(AQIStation.class);
        realm.delete(AQI.class);
        realm.commitTransaction();
    }
}
