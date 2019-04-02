package com.doubleapaper.weathertoday;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Main4Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        Button btSubmit = findViewById(R.id.btSubmit);
        final EditText editText = findViewById(R.id.editText2);
        final TextView textView = findViewById(R.id.tvShow);
        btSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int line = Integer.parseInt(editText.getText().toString());
                String []Monthe  = {"",""};
                textView.setText(Monthe[line]);
                String myLine ="";
                //five
               /* for (int i = 1 ; i <= line; i++){
                    for (int j = 1; j <= line; j++){
                        if (j + i > line)  myLine += "*";
                       else  myLine += "_";
                    }
                    myLine += "\n";
                }
                for (int i = line-1; i > 0; i--){
                    for (int j = 1; j <= line; j++){
                        if (j + i > line)  myLine += "*";
                        else  myLine += "_";
                    }
                    myLine += "\n";
                }*/
               /* myLine += "------------\n";
                for (int i = 1 ; i <= line; i++){
                    for (int j = 1; j <= (line * 2) - 1 ; j++){
                        if (j + i <= line) myLine += "_";
                        else if ((i+j == line+1) ||  i+j <= line + 1 + (2* (i-1)) )  {
                            if ((i+j == line+1) ||  i+j == line + 1 + (2* (i-1)) || i == line)
                            myLine += "0";
                            else myLine += "8";
                        }
                        else  myLine += "_";
                    }
                    myLine += "\n";
                }*/
                //myLine += "------------\n";
                /*for (int i = line ; i > 0; i--){
                    for (int j = 1; j <= (line * 2) - 1 ; j++){
                        if (j + i <= line) myLine += "_";
                        else if ((i+j == line+1) ||  i+j <= line + 1 + (2* (i-1)) )  myLine += "*";
                        else  myLine += "_";
                    }
                    myLine += "\n";
                }*/

                /*myLine += "------------\n";
                for (int i = 1 ; i <= line; i++){
                    for (int j = 1; j <= line; j++){
                        if (i == 1  || line == j || j==1 || line == i)  myLine += "i"+i +" j"+ j + ", ";
                        else  myLine += "||"+" ||"+ ", ";

                    }
                    myLine += "\n";
                }
                for (int i = 1 ; i <= line; i++){
                    for (int j = 1; j <=i; j++){
                        if (line > 3){
                            if (j == i || line == j || j == 1 || line == i )  myLine += "0";
                            else myLine += "8";
                        }
                        else  myLine += "0";
                    }
                    myLine += "\n";
                }*/


            }
        });
    }
}
