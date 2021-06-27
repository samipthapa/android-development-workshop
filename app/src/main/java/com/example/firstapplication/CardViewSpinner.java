package com.example.firstapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Spinner;

import com.google.android.material.snackbar.Snackbar;

public class CardViewSpinner extends AppCompatActivity {

    private Spinner nameSpinner;
    private ConstraintLayout parent;
    private ImageView cardImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_view_spinner);

        nameSpinner = findViewById(R.id.spinner);
        parent = findViewById(R.id.cardViewSpinnerLayout);
        cardImage = findViewById(R.id.cardViewImage);

        nameSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Snackbar.make(parent, nameSpinner.getSelectedItem().toString() + " has been selected", Snackbar.LENGTH_LONG).show();
                String name = nameSpinner.getSelectedItem().toString();
                if(name.equals("Zuko")) {
                    cardImage.setImageResource(R.mipmap.zuko);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Snackbar.make(parent, nameSpinner.getSelectedItem().toString(), Snackbar.LENGTH_SHORT).show();
            }
        });

//        cardImage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                cardImage.setImageResource(R.mipmap.zuko);
//            }
//        });
    }
}