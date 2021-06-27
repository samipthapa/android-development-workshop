package com.example.firstapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class ListView1 extends AppCompatActivity {

    private FloatingActionButton addButton;
    private ListView countries;
    private View parent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view1);

        parent = findViewById(R.id.listViewParent);
        countries = findViewById(R.id.listView_countries);
        addButton = findViewById(R.id.floatingAdd);

        ArrayList<String> countriesList = new ArrayList<>();
        countriesList.add("Nepal");
        countriesList.add("USA");
        countriesList.add("Spain");
        countriesList.add("Japan");

        ArrayAdapter<String> countriesAdapter = new ArrayAdapter<>(
          this, android.R.layout.simple_list_item_1, countriesList
        );
        countries.setAdapter(countriesAdapter);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<String> countriesList = new ArrayList<>();
                countriesList.add("Australia");
                countriesList.add("South Korea");
                countriesList.add("India");
                countriesList.add("United Kingdom");

                ArrayAdapter<String> countriesAdapter = new ArrayAdapter<>(
                        ListView1.this, android.R.layout.simple_list_item_1, countriesList
                );
                countries.setAdapter(countriesAdapter);
                Snackbar.make(parent, "Floating Button Pressed", Snackbar.LENGTH_SHORT).show();
            }
        });
    }
}