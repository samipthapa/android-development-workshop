package com.example.firstapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button linearLayout;
    private Button relativeLayout;
    private Button frameLayout;
    private Button tableLayout;
    private Button listView;
    private Button cardView, recyclerView, navigation, bookApplication;
    private Button userDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userDetails = findViewById(R.id.contactForm);
        linearLayout = findViewById(R.id.linearLayoutButton);
        relativeLayout = findViewById(R.id.relativeLayoutButton);
        frameLayout = findViewById(R.id.frameLayoutButton);
        tableLayout = findViewById(R.id.tableLayoutButton);
        listView = findViewById(R.id.listViewButton);
        cardView = findViewById(R.id.cardViewButton);
        recyclerView = findViewById(R.id.recyclerViewButton);
        navigation = findViewById(R.id.navigationButton);
        bookApplication = findViewById(R.id.bookApplicationButton);

        userDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, UserDetailsNavHost.class);
                startActivity(intent);
            }
        });

        bookApplication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, BookActivity.class);
                startActivity(intent);
            }
        });

        navigation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NavigationHost.class);
                startActivity(intent);
            }
        });

        recyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent recylerIntent = new Intent(MainActivity.this, RecyclerView.class);
                startActivity(recylerIntent);
            }
        });

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cardIntent = new Intent(MainActivity.this, CardViewSpinner.class);
                startActivity(cardIntent);
            }
        });

        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent linearIntent = new Intent(MainActivity.this, LinearLayout.class);
                linearIntent.putExtra("destination", "Linear Layout Sent from Intent");
                startActivity(linearIntent);
            }
        });

        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent relativeIntent = new Intent(MainActivity.this, RelativeLayout.class);
                startActivity(relativeIntent);
            }
        });

        frameLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent frameIntent = new Intent(MainActivity.this, FrameLayout.class);
                startActivity(frameIntent);
            }
        });

        tableLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent tableIntent = new Intent(MainActivity.this, TableLayout.class);
                startActivity(tableIntent);
            }
        });

        listView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent listIntent = new Intent(MainActivity.this, ListView1.class);
                startActivity(listIntent);
            }
        });

        Utils.getInstance(this);
    }
}