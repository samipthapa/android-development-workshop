package com.example.firstapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class DetailBookActivity extends AppCompatActivity {
    public static final String BOOK_ID_KEY = "bookId";

    private TextView bookName, authorName, pageCount, shortDesc, longDesc;
    private ImageView bookImage;
    private Button currentlyReading, finishedReading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_book);
        initView();

        Intent intent = getIntent();
        if (null != intent) {
            int bookId = intent.getIntExtra(BOOK_ID_KEY, -1);
            if (bookId != -1) {
                Book incomingBook = Utils.getInstance(this).getBookById(bookId);
                if (null != incomingBook) {
                    setData(incomingBook);
                    handleAlreadyRead(incomingBook);
                    handleCurrentlyReading(incomingBook);
                }
            }
        }
    }

    private void handleAlreadyRead(Book book) {
        ArrayList<Book> alreadyReadBooks = Utils.getInstance(this).getAlreadyReadBooks();

        boolean alreadyReadExists = false;
        for (Book b: alreadyReadBooks) {
            if (b.getId() == book.getId()) {
                alreadyReadExists = true;
                break;
            }
        }
        if (alreadyReadExists) {
            finishedReading.setEnabled(false);
        }
        else {
            finishedReading.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Utils.getInstance(DetailBookActivity.this).addToAlreadyRead(book)) {
                        Toast.makeText(DetailBookActivity.this, "Book Added", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(DetailBookActivity.this, AlreadyReadBookActivity.class);
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(DetailBookActivity.this, "Error! Try Again", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void handleCurrentlyReading(Book book) {
        ArrayList<Book> currentlyReadingBooks = Utils.getInstance(this).getCurrentlyReadingBooks();

        boolean currentlyReadingExits = false;
        for (Book b: currentlyReadingBooks) {
            if (b.getId() == book.getId()) {
                currentlyReadingExits = true;
                break;
            }
        }
        if (currentlyReadingExits) {
            currentlyReading.setEnabled(false);
        }
        else {
            currentlyReading.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Utils.getInstance(DetailBookActivity.this).addToCurrentlyReading(book)) {
                        Toast.makeText(DetailBookActivity.this, "Book Added", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(DetailBookActivity.this, CurrentlyReadingBookActivity.class);
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(DetailBookActivity.this, "Error! Try Again", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
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
        shortDesc = findViewById(R.id.textView6);
        longDesc = findViewById(R.id.textView8);

        bookImage = findViewById(R.id.imageView);

        currentlyReading = findViewById(R.id.currentlyReading);
        finishedReading = findViewById(R.id.finishedReading);
    }
}