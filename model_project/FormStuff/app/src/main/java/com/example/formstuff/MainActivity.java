package com.example.formstuff;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RatingBar;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ImageButton button = (ImageButton)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Perform action on clicks
                Toast.makeText(MainActivity.this, "Beep Bop", Toast.LENGTH_SHORT).show();
            }
        });

        final ToggleButton togglebutton = (ToggleButton) findViewById(R.id.toggleButton);
        togglebutton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on clicks
                if (togglebutton.isChecked()) {
                    Toast.makeText(MainActivity.this, "Toggle Button Checked",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Toggle Button Not checked",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        final EditText edittext = (EditText) findViewById(R.id.editText);
        edittext.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // If the event is a key-down event on the "enter" button
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    // Perform action on key press
                    Toast.makeText(MainActivity.this, edittext.getText(),
                            Toast.LENGTH_SHORT).show();
                    return true;
                }
                return false;
            }
        });

           final CheckBox cb_single = (CheckBox) findViewById(R.id.cb_single);
        cb_single.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Perform action on clicks
                if (cb_single.isChecked()) {
                    Toast.makeText(MainActivity.this, "Ckecker", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(MainActivity.this, "UnCkecker", Toast.LENGTH_LONG).show();
                }
            }
        });

        final RatingBar ratingbar = (RatingBar) findViewById(R.id.ratingBar);
        ratingbar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean
                    fromUser) {
                Toast.makeText(MainActivity.this, "New Rating: " + rating,
                        Toast.LENGTH_SHORT).show();
            }
        });


    }

    // multiple checkbox click method
    public void onCheckboxClicked(View view) {
        // Is the button now checked?
        boolean checked = ((CheckBox) view).isChecked();
        // Check which radio button was clicked
        switch (view.getId()) {
            case R.id.cb_addidas:
                if (checked)
                    Toast.makeText(MainActivity.this, "Addida Selected",
                            Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "Addida Unselected",
                            Toast.LENGTH_SHORT).show();
                break;
            case R.id.cb_nike:
                if (checked)
                    Toast.makeText(MainActivity.this, "Nike Selected",
                            Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "Nike Unselected",
                            Toast.LENGTH_SHORT).show();
                break;
        }
    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();
        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radio_red:
                if (checked)
                    Toast.makeText(MainActivity.this, "Red Selected",
                            Toast.LENGTH_SHORT).show();
                break;
            case R.id.radio_blue:
                if (checked)
                    Toast.makeText(MainActivity.this, "Blue Selected",
                            Toast.LENGTH_SHORT).show();
                break;
        }
    }

}
