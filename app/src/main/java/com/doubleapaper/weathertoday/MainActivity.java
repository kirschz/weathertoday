package com.doubleapaper.weathertoday;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.AbsListView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.doubleapaper.weathertoday.Object.Header;
import com.doubleapaper.weathertoday.Object.Observe;
import com.doubleapaper.weathertoday.Object.StationsItem;
import com.doubleapaper.weathertoday.Object.WeatherResponse;
import com.doubleapaper.weathertoday.adapter.WeatherAdapter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import io.realm.Realm;
import io.realm.RealmResults;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;

public class MainActivity extends AppCompatActivity {

    private TextView tvMain;
    private TextView tvlastBuiltDate;
    private ListView listView;
    private Realm realm;
    private SweetAlertDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        realm = Realm.getDefaultInstance();

        listView =  findViewById(R.id.lvMain);
        tvlastBuiltDate = findViewById(R.id.tvlastBuiltDate);

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

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public interface GetWeatherToday {
        @GET("V1/")
        Call<WeatherResponse> GetWeatherToday(@Query("type") String type);
    }
    public void GetData(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://data.tmd.go.th/api/WeatherToday/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GetWeatherToday service = retrofit.create(GetWeatherToday.class);
        Call<WeatherResponse> call = service.GetWeatherToday("json");

        call.enqueue(new Callback<WeatherResponse>() {
            @Override
            public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {
                Log.i("MainActivity","code:" +response.code());
                if (response.isSuccessful()){
                   WeatherResponse weatherResponse = response.body();
                   List<StationsItem> stationsItems = weatherResponse.getStations();

                    clearData();
                    Header header = new Header();
                    header.insertData(realm, weatherResponse.getHeader());
                    StationsItem stationsItem = new StationsItem();
                    for (int i = 0; i < stationsItems.size(); i++) {
                        stationsItem.insertData(realm, stationsItems.get(i), stationsItems.get(i).getObserve().getTemperature().getValue());
                    }
                    tvlastBuiltDate.setText(weatherResponse.getHeader().getLastBuiltDate());
                    WeatherAdapter listAdapter = new WeatherAdapter(stationsItems);
                    listView.setAdapter(listAdapter);
                    if (pDialog.isShowing()) pDialog.dismiss();

                    Intent myIntent = new Intent(MainActivity.this, CameraActivity.class);
                    myIntent.putExtra("key", "value");
                   // MainActivity.this.startActivity(myIntent);

                }

            }


            @Override
            public void onFailure(Call<WeatherResponse> call, Throwable t) {

                Toast.makeText(MainActivity.this,"Fail:" +t.getMessage(), Toast.LENGTH_LONG).show();

                try {
                    RealmResults<Header> headers = realm.where(Header.class).findAll();
                    RealmResults<StationsItem> RealmStationsItem = realm.where(StationsItem.class).findAll();
                    List<StationsItem> unmanagedList = realm.copyFromRealm(RealmStationsItem);

                    tvlastBuiltDate.setText(headers.get(headers.size()-1).getLastBuiltDate());
                    tvlastBuiltDate.setTextColor(Color.RED);
                    WeatherAdapter listAdapter = new WeatherAdapter(unmanagedList);
                    listView.setAdapter(listAdapter);
                    if (pDialog.isShowing()) pDialog.dismiss();
                }catch (Exception ex){
                    if (pDialog.isShowing()) pDialog.dismiss();
                }

            }
        });
    }
    private void clearData() {
        realm.beginTransaction();
        realm.delete(Header.class);
        realm.delete(StationsItem.class);
        realm.commitTransaction();
    }

}
