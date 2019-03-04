package com.doubleapaper.weathertoday;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class LifeCycleActivity extends AppCompatActivity   {
    private String TAG = LifeCycleActivity.class.getSimpleName();
    TextView tvLifeCycleActivity;
    Button button2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_life_cycle);
        tvLifeCycleActivity = findViewById(R.id.tvLifeCycleActivity);
        button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvLifeCycleActivity.setText("");
            }
        });
        Log.i(TAG,"onCreate");
        tvLifeCycleActivity.setText(tvLifeCycleActivity.getText()+"onCreate \n");
        Toast.makeText(this, "onCreate", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG,"onStart");
        tvLifeCycleActivity.setText(tvLifeCycleActivity.getText()+"onStart \n");
        Toast.makeText(this, "onStart", Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG,"onResume");
        tvLifeCycleActivity.setText(tvLifeCycleActivity.getText()+"onResume \n");
        Toast.makeText(this, "onResume", Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG,"onRestart");
        tvLifeCycleActivity.setText(tvLifeCycleActivity.getText()+"onRestart \n");
        Toast.makeText(this, "onRestart", Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG,"onPause");
        tvLifeCycleActivity.setText(tvLifeCycleActivity.getText()+"onPause \n");
        Toast.makeText(this, "onPause", Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG,"onStop");
        tvLifeCycleActivity.setText(tvLifeCycleActivity.getText()+"onStop \n");
        Toast.makeText(this, "onStop", Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG,"onDestroy");
        tvLifeCycleActivity.setText(tvLifeCycleActivity.getText()+"onDestroy \n");
        Toast.makeText(this, "onDestroy", Toast.LENGTH_SHORT).show();
    }
}
