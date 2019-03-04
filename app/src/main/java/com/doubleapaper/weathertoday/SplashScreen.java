package com.doubleapaper.weathertoday;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.util.List;

public class SplashScreen extends AppCompatActivity {
    Handler handler;
    Runnable runnable;
    Long delay_time;
    Long time = 1000L;
    private boolean hasCameraPermission;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Dexter.withActivity(this)
                    .withPermissions(
                            Manifest.permission.CAMERA,
                            Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.READ_PHONE_STATE,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE
                    ).withListener(new MultiplePermissionsListener() {
                @Override public void onPermissionsChecked(MultiplePermissionsReport report) {
                    if (report.areAllPermissionsGranted()){
                        hasCameraPermission =true;
                        handler = new Handler();
                        runnable = new Runnable() {
                            public void run() {
                                Intent myIntent = new Intent(SplashScreen.this, Calculator.class);
                                myIntent.putExtra("key", "value");
                                SplashScreen.this.startActivity(myIntent);
                                finish();
                            }
                        };

                    }else {
                        hasCameraPermission = false;
                        Context context = getApplicationContext();
                        CharSequence text = "No Permissions";
                        int duration = Toast.LENGTH_SHORT;
                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                        //finish();
                    }
                }
                @Override public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                    token.continuePermissionRequest();
                }

            }).check();
        }else {
            Intent myIntent = new Intent(SplashScreen.this, MainActivity.class);
            myIntent.putExtra("key", "value");
            SplashScreen.this.startActivity(myIntent);
            finish();
        }
    }

    public void onResume() {
        super.onResume();
        if (hasCameraPermission){
            delay_time = time;
            handler.postDelayed(runnable, delay_time);
            time = System.currentTimeMillis();
        }

    }

    public void onStop() {
        super.onStop();
        if (hasCameraPermission){
            handler.removeCallbacks(runnable);
            time = delay_time - (System.currentTimeMillis() - time);
        }

    }

}
