package com.example.firstapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

public class EventHandling extends AppCompatActivity {

    private View parent;
    private TextView text;
    private TextInputEditText inputField;
    private Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_handling);

        parent = findViewById(R.id.EventHandlingParent);
        text = findViewById(R.id.firstTextView);
        inputField = findViewById(R.id.inputEditTextBox);
        submitButton = findViewById(R.id.submitButton);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Editable inputText = inputField.getText();
                text.setText(inputText);
//                Toast.makeText(EventHandling.this, "\"" + inputText + "\"" +
//                        " has been displayed.", Toast.LENGTH_LONG).show();

                Snackbar.make(parent, "\"" + inputText + "\"" +
                        " has been displayed.", Snackbar.LENGTH_LONG)
                        .setAction("Show in Toast", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(EventHandling.this, "\"" + inputText + "\"" +
                        " has been displayed.", Toast.LENGTH_LONG).show();
                    }
                })
                        .setActionTextColor(Color.WHITE)
                        .setTextColor(Color.YELLOW).show();
            }
        });
    }
}