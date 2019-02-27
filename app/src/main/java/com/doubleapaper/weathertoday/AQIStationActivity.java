package com.doubleapaper.weathertoday;

import android.content.Intent;
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

    private String TAG = AQIStationActivity.class.getSimpleName();
    private Realm realm;
    private SweetAlertDialog pDialog;
    private static String URL = "http://air4thai.pcd.go.th/services/";
    private TextView tvAqi;
    private TextView tvName, tvArea;
    Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aqistation);
        realm = Realm.getDefaultInstance();

        tvAqi = findViewById(R.id.tvAqi);
        tvName = findViewById(R.id.tvName);
        tvArea = findViewById(R.id.tvArea);
        tvAqi.setText("");
        tvArea.setText("");
        tvName.setText("");
        retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        pDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
        pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5FF86"));
        pDialog.setTitleText("Loading AQI");
        pDialog.setCancelable(false);
        pDialog.show();
        GetDataStation();
    }

    @Override
    protected void onStop() {
        super.onStop();
        realm.close();
    }

    public interface GetAQI {
        @GET("getNewAQI_JSON.php/")
        Call<AQIStation> GetAQIStation(@Query("stationID") String stationID);

        @GET("getNewAQI_JSON.php/")
        Call<AQIRegion> GetAQIRegion(@Query("region") int regionID);
    }
    public void GetDataRegion(){
    /*    1กรุงเทพฯและปริมณฑล
        2ภาคเหนือ
        5ภาคตะวันออกเฉียงเหนือ
        7ภาคกลางและตะวันตก
        3ภาคตะวันออก
        6ภาคใต้
        8พื้นที่ ต.หน้าพระลาน*/

        GetAQI service = retrofit.create(GetAQI.class);
        Call<AQIRegion> call = service.GetAQIRegion(3);

        call.enqueue(new Callback<AQIRegion>() {
            @Override
            public void onResponse(Call<AQIRegion> call, Response<AQIRegion> response) {
                if (response.isSuccessful()){
                    if (pDialog.isShowing())
                        pDialog.dismiss();
                }
            }
            @Override
            public void onFailure(Call<AQIRegion> call, Throwable t) {
                if (pDialog.isShowing())
                    pDialog.dismiss();

            }
        });
    }
    public void GetDataStation(){
        /*
        60t ต.วังเย็น, อ.แปลงยาว, ฉะเชิงเทรา
        32t ต.ทุ่งสุขลา อ.ศรีราชา, ชลบุรี
        71t ต.อรัญประเทศ, อ.อรัญประเทศ, สระแก้ว
        77t ต.ท่าตูม อ.ศรีมหาโพธิ, จ.ปราจีนบุรี
         */
        GetAQI service = retrofit.create(GetAQI.class);
        Call<AQIStation> call = service.GetAQIStation("77t");

        call.enqueue(new Callback<AQIStation>() {
            @Override
            public void onResponse(Call<AQIStation> call, Response<AQIStation> response) {

                if (response.isSuccessful()){
                    clearData();
                    AQIStation aqiStation = response.body();
                    aqiStation.insertData(realm, aqiStation);

                    AQI aqi = aqiStation.getLastUpdate().getAQI();
                    aqi.insertData(realm, aqi, aqiStation.getStationID());
                    if (pDialog.isShowing())
                        pDialog.dismiss();
                    tvAqi.setText("AQI : " +aqi.getAqi());
                    tvArea.setText(aqiStation.getAreaTH());
                    tvName.setText(aqiStation.getNameTH());
                    //Intent myIntent = new Intent(AQIStationActivity.this, CameraActivity.class);
                    //myIntent.putExtra("key", "value");
                    //AQIStationActivity.this.startActivity(myIntent);

                }

            }

            @Override
            public void onFailure(Call<AQIStation> call, Throwable t) {
                if (pDialog.isShowing())
                    pDialog.dismiss();
                tvAqi.setText("");
                tvArea.setText("");
                tvName.setText("");
                Toast.makeText(AQIStationActivity.this,"Fail:" +t.getMessage(), Toast.LENGTH_LONG).show();

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
