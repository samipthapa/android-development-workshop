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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        linearLayout = findViewById(R.id.linearLayoutButton);
        relativeLayout = findViewById(R.id.relativeLayoutButton);
        frameLayout = findViewById(R.id.frameLayoutButton);
        tableLayout = findViewById(R.id.tableLayoutButton);

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
    }
}