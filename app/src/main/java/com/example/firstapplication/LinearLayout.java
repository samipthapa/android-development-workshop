package com.example.firstapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class LinearLayout extends AppCompatActivity {

    private TextView linearLayoutView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linear_layout);

        linearLayoutView = findViewById(R.id.linearView);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String statePassed = extras.getString("destination");
            linearLayoutView.setText(statePassed);
        }
    }
}