package com.doubleapaper.weathertoday;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Button button = findViewById(R.id.button3);
        final EditText editText = findViewById(R.id.editText);
        final TextView textView = findViewById(R.id.textView3);
        editText.setText("");
        textView.setText("");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int value = Integer.parseInt(editText.getText().toString());
                String Grade[] = {"D", "D+", "C", "C+", "B", "B+", "A"};
                String myGrade = "";
                value -= 50;
                int gradePosition = value / 5;
                if (value <= 0) myGrade = "F";
                else if (value >=30) myGrade = "A";
                else if (value > 50) myGrade= "input 0-100";
                else myGrade = Grade[gradePosition];
               /* else if (value >=75) myGrade = "B+";
                else if (value >=70) myGrade = "B";
                else if (value >=65) myGrade = "C+";
                else if (value >=60) myGrade = "C";
                else if (value >=55) myGrade = "D+";
                else if (value >=50) myGrade = "D";*/
                textView.setText(myGrade+"");
                editText.setText("");
            }
        });

    }
    private int CalculatorMax(int [] value, int max, int position){
       if (value[position] > max) {
           max = value[position];
       }
       if (value.length -1 == position ){
           return max;
       }else {
           position = position + 1;
           return CalculatorMax(value,max, position);
       }
    }

}
