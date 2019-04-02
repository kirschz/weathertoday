package com.doubleapaper.weathertoday;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.Arrays;

public class Main3Activity extends AppCompatActivity {

    private  Integer [] input = new Integer[200];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        Button btAdd = findViewById(R.id.btAddValue);
        Button btCal = findViewById(R.id.btCalculator);
        final EditText editText = findViewById(R.id.etInput);
        final TextView textView = findViewById(R.id.textView4);
        final TextView tvAmount = findViewById(R.id.tvAmount);
        final TextView tvInput = findViewById(R.id.tvInput);

        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i = Integer.parseInt("0"+tvAmount.getText().toString());
                input[i++] = Integer.parseInt((editText.getText().toString()));
                tvAmount.setText(i+"");
                editText.setText("");
            }
        });
        btCal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int max = 0;
                int min = 9999;
                int amount =  Integer.parseInt(tvAmount.getText().toString());
                String inputNumber = "Input : ";

                for (int i = 0; i < amount; i++){
                    if (input[i] > max) max = input[i];
                    if (input[i] < min) min = input[i];
                    inputNumber += input[i] + ", ";
                }
                tvInput.setText(inputNumber.substring(0, inputNumber.length()-2));
                textView.setText("Max : " + GetMaxValue(input) +
                        "\nMin : " + GetMinValue(input) +
                        "\nฐานนิยม : " + GetModValue(input) +
                        "\nมัธยฐาน : " + GetMedianValue(input, amount)+
                        "\nค่าเฉลี่ย : " + GetAverageValue(input) );

            }
        });
    }

    private int GetMaxValue(Integer [] v){
        int value = v[0];
        for (int i = 0; i < v.length; i++){
            if (v[i] == null) break;
            if (v[i] > value) value = v[i];
        }
        return  value;
    }
    private int GetMinValue(Integer [] v){
        int value = v[0];
        for (int i = 0; i < v.length; i++){
            if (v[i] == null) break;
            if (v[i] < value) value = v[i];
        }
        return  value;
    }
    private int GetModValue(Integer [] v){
        int [] mod = new int[100];
        for (int i = 0; i < v.length; i++){
            if (v[i] == null) break;
            mod [v[i]] =  mod[v[i]] + 1;
        }
        int max = 0;
        int modValue = -1;
        for (int i = 0; i < 100; i++){
            if (mod[i] > max && mod[i]  > 1)  {
                modValue = i;
                max = mod[i];
            }
        }
        return modValue;
    }
    private double GetMedianValue(Integer [] v ,int amount){
        if (amount == 1) return  v[0];
        double median = 0d;
        int [] array = new int[amount] ;
        for (int i = 0; i < amount; i++){
            if (v[i] == null ) break;
            array[i] = v[i];
        }
        Arrays.sort(array);

        if ( amount % 2 == 0){
            median = (array[(amount / 2) - 1] + array[(amount/2)]) /2d;
        }else {
            median = array[(amount/2) ];
        }
        return median;
    }
    private double GetAverageValue(Integer [] v){
        double average = 0d;
        int sum = 0;
        for (int i = 0; i < v.length; i++){
            if (v[i] == null) {
                average = sum / (i*1d);
                break;
            }
            else sum += v[i];
        }
        return average;
    }

}
