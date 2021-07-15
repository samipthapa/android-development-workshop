package com.example.firstapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.GridLayoutManager;

import android.content.Intent;
import android.os.Bundle;

public class AlreadyReadBookActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_already_read_book);

        RecyclerView alreadyReadRecView = findViewById(R.id.alreadyReadRecView);
        BookRecyclerViewAdapter alreadyReadAdapter = new BookRecyclerViewAdapter(this, "alreadyReadBooks");
        alreadyReadRecView.setAdapter(alreadyReadAdapter);
        alreadyReadRecView.setLayoutManager(new GridLayoutManager(this, 1));

        alreadyReadAdapter.setBooks(Utils.getInstance(this).getAlreadyReadBooks());
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, BookActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}