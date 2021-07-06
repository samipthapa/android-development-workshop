package com.example.firstapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class DetailBookActivity extends AppCompatActivity {

    private TextView bookName, authorName, pageCount, shortDesc, longDesc;
    private ImageView bookImage;
    private Button currentlyReading, finishedReading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_book);
        initView();

        Book book = new Book(1, "The Great Gatsby", "F. Scott Fitzgerald", "180",
                "https://cdn.waterstones.com/bookjackets/large/9780/1411/9780141182636.jpg",
                " A Novel by F. Scott Fitzgerald",
                "The Great Gatsby, F. Scott Fitzgerald’s third book, stands as the supreme achievement of his career." +
                        " First published in 1925, this quintessential novel of the Jazz Age has been acclaimed by generations of readers." +
                        " The story of the mysteriously wealthy Jay Gatsby and his love for the beautiful Daisy Buchanan," +
                        " of lavish parties on Long Island at a time when The New York Times noted “gin was the national drink and sex the national obsession,” it is an exquisitely crafted tale of America in the 1920s.");
        setData(book);
    }

    private void setData(Book book) {
        bookName.setText(book.getName());
        authorName.setText(book.getAuthor());
        pageCount.setText(book.getPages());
        shortDesc.setText(book.getShortDesc());
        longDesc.setText(book.getLongDesc());
        Glide.with(this)
                .asBitmap().load(book.getImageUrl())
                .into(bookImage);
    }

    public void initView() {
        bookName = findViewById(R.id.bookName);
        authorName = findViewById(R.id.authorName);
        pageCount = findViewById(R.id.pageCount);
        shortDesc = findViewById(R.id.shortDescription);
        longDesc = findViewById(R.id.longDescription);

        bookImage = findViewById(R.id.imageView);

        currentlyReading = findViewById(R.id.currentlyReading);
        finishedReading = findViewById(R.id.finishedReading);
    }
}