package com.example.largestcountries;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    ListView mListView;
    ListAdapter mListAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final String[] countries =
                getResources().getStringArray(R.array.countries_array);
        final String[] areas =
                getResources().getStringArray(R.array.areas_array);
        final int[] flags = {R.drawable.russia, R.drawable.canada,
                R.drawable.usa, R.drawable.china, R.drawable.brazil, R.drawable.australia,
                R.drawable.india, R.drawable.argentina, R.drawable.kazakhstan,
                R.drawable.algeria};
        mListView = (ListView) findViewById(R.id.listview);
        mListAdapter = new
                com.example.largestcountries.ListAdapter(MainActivity.this, countries, areas, flags);
        mListView.setAdapter(mListAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int
                    i, long l) {
                Toast.makeText(MainActivity.this, countries[i]+" "+areas[i],
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}