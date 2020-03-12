package com.example.numbershapes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText input;
    private Button click;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        click = (Button) findViewById(R.id.button);

        click.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                input = (EditText) findViewById(R.id.input);
                int n=Integer.parseInt(input.getText().toString());
                Number N=new Number();
                N.number=n;
                String s=input.getText().toString();
                if( N.isSquare() && N.isTriangular()) {
                    s=s+" is square, and is triangular.";
                }
                else if(N.isTriangular()) {
                    s=s+" is not square, but is triangular.";
                }
                else if(N.isSquare()) {
                    s=s+" is square, but not triangular.";
                }
                else {
                    s=s+" is not square, and not triangular.";
                }
                Toast.makeText(MainActivity.this,s,Toast.LENGTH_LONG).show();
            }

        });
    }

}
