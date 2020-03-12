package com.example.bmi_spinner;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import java.text.DecimalFormat;
import androidx.appcompat.app.AppCompatActivity;

import com.example.bmi_spinner.R;

public class Main2Activity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Bundle bundle = getIntent().getExtras();
        int feet = bundle.getInt("feet");
        int inches = bundle.getInt("inches");
        String w = bundle.getString("weight");
        double height = (feet*12.0+inches)*0.0254;
        double weight = Double.parseDouble(w)*0.45395;
        double bmi = weight / (height*height);
        DecimalFormat nf = new DecimalFormat("0.00");
        TextView result = (TextView) findViewById(R.id.resultTV);
        result.setText(getString(R.string.bmi_result) + " " + nf.format(bmi));
        // Give health advice
        ImageView image = (ImageView) findViewById(R.id.resultImage);
        TextView advice = (TextView) findViewById(R.id.adviceTV);
        if (bmi > 25) {
            image.setImageResource(R.drawable.bot_fat);
            advice.setText(R.string.advice_heavy);
        } else if (bmi < 20) {
            image.setImageResource(R.drawable.bot_thin);
            advice.setText(R.string.advice_light);
        } else {
            image.setImageResource(R.drawable.bot_fit);
            advice.setText(R.string.advice_average);
        }
    }
}