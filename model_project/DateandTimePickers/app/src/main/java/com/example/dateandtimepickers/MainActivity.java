package com.example.dateandtimepickers;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DatePicker dpDate;
    TimePicker tpTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dpDate = (DatePicker)findViewById(R.id.dpDate);
        // init
        // dpDate.init(2002, 10, 27, null);
        tpTime = (TimePicker)findViewById(R.id.tpTime);
        // set the time picker mode to 24 hour view
        tpTime.setIs24HourView(true);
        // set a time changed listener to time picker
        tpTime.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker timePicker, int i, int i1) {
                String time = "Current time: " + timePicker.getCurrentHour() + " : " + timePicker.getCurrentMinute();
                Toast.makeText(getApplicationContext(), time,
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void getDate(View v) {
        StringBuilder builder=new StringBuilder();
        builder.append("Current Date: ");
        builder.append((dpDate.getMonth() + 1)+"/");//month is 0 based
        builder.append(dpDate.getDayOfMonth()+"/");
        builder.append(dpDate.getYear());
        Toast.makeText(this, builder.toString(), Toast.LENGTH_SHORT).show();
    }
}