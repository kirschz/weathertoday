package com.doubleapaper.weathertoday;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Main5Activity extends AppCompatActivity {
    private  int i = 0, j= 0;
    private  int row = 0 ,column = 0;
    private int [][] input = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        final EditText et = findViewById(R.id.editText3);
        final TextView tv = findViewById(R.id.textView8);
        final TextView tvInput = findViewById(R.id.textView6);
        final Button bt = findViewById(R.id.button4);
        final Button btShow = findViewById(R.id.button5);


        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int number =  Integer.parseInt(et.getText().toString());
                et.setText("");
                if (tvInput.getText().toString().equals("input row")){
                    row = number;
                    tvInput.setText("input column");
                }else if (tvInput.getText().toString().equals("input column")){
                    column = number;
                    tvInput.setText("input number");
                    input = new int[row][column];
                }else {
                    Log.i("joke", "I "+ i +" J " +j  + " row " + row + "column" + column);
                    input[i][j] = number;

                    if (++j == column){
                        if (++i == row ){
                            et.setEnabled(false);
                            bt.setEnabled(false);
                        }
                        j = 0;
                    }


                }

            }
        });
        btShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number = "";
                for(int i = 0; i < row; i++){
                    for(int j =0; j <column; j++){
                        number += input[i][j] + " ";
                    }
                    number += "\n";
                }
                tv.setText(number);
            }
        });

    }
}
