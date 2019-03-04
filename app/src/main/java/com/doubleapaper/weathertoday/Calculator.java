package com.doubleapaper.weathertoday;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Calculator extends AppCompatActivity {

    Button btp,btn,bt;
    Button []btNumber = new Button[10];
    TextView tv,tv2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        int i = 0;
        btNumber[i++] = findViewById(R.id.bt0);
        btNumber[i++] = findViewById(R.id.bt1);
        btNumber[i++] = findViewById(R.id.bt2);
        btNumber[i++] = findViewById(R.id.bt3);
        btNumber[i++] = findViewById(R.id.bt4);
        btNumber[i++] = findViewById(R.id.bt5);
        btNumber[i++] = findViewById(R.id.bt6);
        btNumber[i++] = findViewById(R.id.bt7);
        btNumber[i++] = findViewById(R.id.bt8);
        btNumber[i++] = findViewById(R.id.bt9);
        btn = findViewById(R.id.btn);
        btp = findViewById(R.id.btp);
        bt = findViewById(R.id.bt);
        tv = findViewById(R.id.textView);
        tv2 = findViewById(R.id.textView2);
    }

    public void btClick(View view) {
        try {
            int n = tv.getText().toString().length();
            for (int z = 0; z < btNumber.length; z++) {
                if (btNumber[z].getId() == view.getId()) {
                    tv.setText(tv.getText().toString() + btNumber[z].getText().toString());
                    btn.setEnabled(true);
                    btp.setEnabled(true);
                }
            }
            if (view.getId() == R.id.btp || view.getId() == R.id.btn) {
                Button button = (Button) findViewById(view.getId());
                tv.setText(tv.getText().toString() + button.getText().toString());
                btn.setEnabled(false);
                btp.setEnabled(false);
            } else if (view.getId() == R.id.bt && n > 2) {
                char[] c = tv.getText().toString().toCharArray();
                int sum = 0;

                String[] s = new String[n];

                String[] x = new String[n];
                int j = 0, k = 0;
                for (int i = 0; i < n; i++) {
                    if (Character.isDigit(c[i])) {
                        s[j] = s[j] == null ? c[i] + "" : s[j] + c[i];
                    } else {
                        j++;
                        x[k++] = "" + c[i];
                    }
                }
                k = 0;
                sum = Integer.parseInt(s[0]);
                for (int i = 1; i <= j; i++) {
                    sum = x[k].equals("+") ? sum + Integer.parseInt(s[i]) : sum - Integer.parseInt(s[i]);
                    k++;
                }
                tv2.setText("Result : " + sum);
            } else if (view.getId() == R.id.btClear) {
                btn.setEnabled(false);
                btp.setEnabled(false);
                tv.setText("");
                tv2.setText("");
            }
        }catch (Exception ex){
            Toast.makeText(this, "Fail" + ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
//        switch (view.getId()){
//
//            case R.id.bt0 : {
//                tv.setText(tv.getText().toString() + bt0.getText().toString());
//                btn.setEnabled(true);
//                btp.setEnabled(true);
//                break;
//            }
//            case R.id.bt1 : {
//                tv.setText(tv.getText().toString() + bt1.getText().toString());
//                btn.setEnabled(true);
//                btp.setEnabled(true);
//                break;
//            }
//            case R.id.bt2 :{
//                tv.setText(tv.getText().toString() + bt2.getText().toString());
//                btn.setEnabled(true);
//                btp.setEnabled(true);
//                break;}
//            case R.id.bt3 :{
//                tv.setText(tv.getText().toString() + bt3.getText().toString());
//                btn.setEnabled(true);
//                btp.setEnabled(true);
//            break;}
//            case R.id.bt4 : {
//                tv.setText(tv.getText().toString() + bt4.getText().toString());
//            btn.setEnabled(true);
//                btp.setEnabled(true);
//                break;}
//            case R.id.bt5 : {tv.setText(tv.getText().toString() + bt5.getText().toString());   btn.setEnabled(true);
//                btp.setEnabled(true);
//                break;}
//            case R.id.bt6 : {tv.setText(tv.getText().toString() + bt6.getText().toString());   btn.setEnabled(true);
//                btp.setEnabled(true);
//                break;}
//            case R.id.bt7 : {tv.setText(tv.getText().toString() + bt7.getText().toString());   btn.setEnabled(true);
//                btp.setEnabled(true);
//                break;}
//            case R.id.bt8 : {tv.setText(tv.getText().toString() + bt8.getText().toString());   btn.setEnabled(true);
//                btp.setEnabled(true);
//                break;}
//            case R.id.bt9 : {tv.setText(tv.getText().toString() + bt9.getText().toString());   btn.setEnabled(true);
//                btp.setEnabled(true);
//                break;}
//            case R.id.btn : {
//                tv.setText(tv.getText().toString() + btn.getText().toString());
//                btn.setEnabled(false);
//                btp.setEnabled(false);
//                break;
//            }
//            case R.id.btp : {
//                tv.setText(tv.getText().toString() + btp.getText().toString());
//                btn.setEnabled(false);
//                btp.setEnabled(false);
//                break;
//            }
//            case R.id.bt : {
//
//                char [] c = tv.getText().toString().toCharArray();
//                int n =  tv.getText().toString().length();
//                int sum = 0;
//
//                String [] s = new String[n];
//                for (int i = 0; i < n; i++) {
//                   s[i] = "0";
//                }
//                String [] x = new String[n];
//                int j = 0, k=0 ;
//                for (int i = 0; i < n; i++){
//                    Log.i("Main","c " + Character.isDigit(c[i]));
//                    if (Character.isDigit(c[i]))
//                    {
//                        s[j] += c[i];
//
//                    }else {
//                        j++;
//                        x[k++] = "" +c[i] ;
//                    }
//                }
//                k = 0;
//                Log.i("Main","btp" +  s[0]);
//                sum = Integer.parseInt(s[0]);
//                for (int i = 1; i <= j; i++){
//                    if (x[k].equals("+")){
//                        Log.i("Main","btp" + sum);
//                        sum += Integer.parseInt(s[i]);
//                        tv.setText("" +sum );
//
//                    }else if (x[k].equals("-")){
//                        Log.i("Main","btn" + sum);
//                        sum -= Integer.parseInt(s[i]);
//                        tv.setText(""+sum);
//
//                    }
//                    Log.i("Main","k" + k);
//                    k++;
//                }
//
//                break;
//            }
//            default: break;
//        }

    }
}
