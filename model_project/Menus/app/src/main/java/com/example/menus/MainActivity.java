package com.example.menus;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText mOutEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mOutEditText = (EditText) findViewById(R.id.editText);
        registerForContextMenu(mOutEditText);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the aaction bar if it is present
        getMenuInflater().inflate(R.menu.menu_options, menu);
        return true; }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_settings:
                Toast.makeText(this, "Settings Button Clicked !",
                        Toast.LENGTH_LONG).show();
                return true;
            case R.id.menu_about:
                Toast.makeText(this, "About Button Clicked !",
                        Toast.LENGTH_LONG).show();
                return true;
            case R.id.menu_feedback:
                Toast.makeText(this, "Send Feedback Button Clicked !",
                        Toast.LENGTH_LONG).show();
                return true;
            case R.id.menu_exit:
                finish();
                return true;
        }
        return false; }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_context, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuItemWelcome:
                mOutEditText.setText( this.getResources().getText( R.string.welcome_msg)
                );
                return true;
            case R.id.menuItemAbout:
                mOutEditText.setText( this.getResources().getText( R.string.about_msg) );
                return true;
            default:
                return super.onContextItemSelected(item);
        } }

}

