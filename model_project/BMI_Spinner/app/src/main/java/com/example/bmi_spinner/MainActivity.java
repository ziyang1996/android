package com.example.bmi_spinner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText vWeight;
    Button submitButton;
    ImageView mImageView;
    String[] feetArray, inchesArray;
    int feet, inches;
    Spinner spinnerFeet, spinnerInches;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        vWeight = (EditText) findViewById(R.id.weight);
        feetArray = getResources().getStringArray(R.array.feet);
        inchesArray = getResources().getStringArray(R.array.inches);
        spinnerFeet = (Spinner) findViewById(R.id.spinner_feet);
        ArrayAdapter<String> adapterFeet = new ArrayAdapter<String>(this,
                R.layout.dropdown_item, feetArray);
        spinnerFeet.setAdapter(adapterFeet);
        spinnerInches = (Spinner) findViewById(R.id.spinner_inches);
        ArrayAdapter<String> adapterInches = new ArrayAdapter<String>(this,
                R.layout.dropdown_item, inchesArray);
        spinnerInches.setAdapter(adapterInches);
        spinnerFeet.setOnItemSelectedListener(new Spinner.OnItemSelectedListener()
        {
              @Override
              public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                  feet = arg0.getSelectedItemPosition() + 1;
              }
              @Override
              public void onNothingSelected(AdapterView<?> arg0) {
              }
        });
        spinnerInches.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                inches = arg0.getSelectedItemPosition();
                String x= vWeight.getText().toString()+" "+feet+" "+inches;
                //Toast.makeText(MainActivity.this,x,Toast.LENGTH_LONG).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });
    }
    public void calcBMI(View view) {
        String weight = vWeight.getText().toString();
        Intent intent = new Intent(this, com.example.bmi_spinner.Main2Activity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("feet", feet);
        bundle.putInt("inches", inches);
        bundle.putString("weight", weight);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}