package com.example.intent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText et;
    private static final int REQUEST_CODE = 1;
    private static final int REQUEST_CODE_PERMISSION = 2;
    private static String[] PERMISSIONS_REQ = {
            Manifest.permission.CALL_PHONE,
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et = (EditText) findViewById(R.id.editText1);
        // Checking and Seeking for CALL_PHONE permission
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]
                    {Manifest.permission. CALL_PHONE}, REQUEST_CODE);
        }
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) !=
                PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.
                    CALL_PHONE}, REQUEST_CODE);
        }
    }
    public void goToSecondActivity(View v) {
        String myMessage = et.getText().toString();
        Intent intent = new Intent(this, Main2Activity.class);
        Bundle bundle = new Bundle();
        bundle.putString("Message", myMessage);
        intent.putExtras(bundle);
        startActivity(intent);
    }
    public void goToThirdActivity(View view) {
        Intent intent = new Intent(this, Main3Activity.class);
        startActivityForResult(intent, REQUEST_CODE);
    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                et.setText(data.getData().toString());
            }
        }
    }
    public void showWebPage(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW,
                Uri.parse("http://developer.android.com"));
        startActivity(intent);
    }
    public void callNumber(View view) {
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:34426789"));
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        startActivity(intent);
    }

}