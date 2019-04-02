package com.doubleapaper.weathertoday;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.text.DecimalFormat;

public class Main6Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);
        RadioGroup rg = this.findViewById ( R.id.rg );
        final TextView textView10 = findViewById(R.id.textView10);
        final TextView textView11 = findViewById(R.id.textView11);
        final LinearLayout layoutCircle = findViewById(R.id.layoutCircle);
        rg.setOnCheckedChangeListener ( new RadioGroup.OnCheckedChangeListener ( )
                                         {
                                             public void onCheckedChanged ( RadioGroup group, int checkedId )
                                             {
                                                 RadioButton checkedRadio = Main6Activity.this.findViewById ( checkedId );
                                                 String checkedText = checkedRadio.getText ().toString ();
                                                 layoutCircle.setVisibility(View.INVISIBLE);
                                                 if (checkedText.equals("สามเเหลี่ยม")){
                                                     textView10.setText("ฐาน");
                                                     textView11.setText("สูง");
                                                 }else  if (checkedText.equals("สี่เหลี่ยม"))
                                                 {
                                                     textView10.setText("กวาง");
                                                     textView11.setText("ยาว");
                                                 }else {
                                                     textView10.setText("รัศมี");
                                                     layoutCircle.setVisibility(View.GONE);
                                                 }
                                             }
                                         }
        );
    }

    public void Cal(View view) {
        RadioButton r1 = findViewById(R.id.r1);
        RadioButton r2 = findViewById(R.id.r2);
        RadioButton r3 = findViewById(R.id.r3);
        EditText editText5 = findViewById(R.id.editText5);
        EditText editText4 = findViewById(R.id.editText4);
        TextView textView9 = findViewById(R.id.textView9);
        double a = 0d;
           /* if (r1.isChecked()){
                a = Double.parseDouble(editText5.getText().toString()) * Double.parseDouble(editText4.getText().toString()) / 2;
                textView9.setText("พื้นที่ "+ r1.getText().toString() + " คือ "  + a);
            }else if (r2.isChecked()) {
                a = Double.parseDouble(editText5.getText().toString()) * Double.parseDouble(editText4.getText().toString());
                textView9.setText("พื้นที่ "+ r2.getText().toString() + " คือ "  + a);
            }else {
                a = Double.parseDouble(editText5.getText().toString()) *  Double.parseDouble(editText5.getText().toString()) * 22/7;
                DecimalFormat formatter = new DecimalFormat("#0.00");
                textView9.setText("พื้นที่ "+ r3.getText().toString() + " คือ "  +formatter.format(a));
            }*/
        String input = editText5.getText().toString();
        char[] c = input.toCharArray();
        String[] text = {"ล้าน","แสน","หมื่น","พัน","ร้อย","สิบ",""};
        String[] textNumber = {"0","หนึ่ง", "สอง","สาม","สี่","ห้า","หก","เจ็ด","แปด","เก้า"};
        String ans = "";
        int n = 6 - c.length +1;
        for (int i=0; i < c.length; i++){
            ans += textNumber[ Integer.parseInt(c[i]+"")] +text[n];
        }
        textView9.setText(ans);
    }
}
