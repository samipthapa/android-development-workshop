package com.example.firstapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class BookActivity extends AppCompatActivity {

    private RecyclerView booksRecView;
    private BookRecyclerViewAdapter bookRecyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        bookRecyclerViewAdapter = new BookRecyclerViewAdapter(this);
        booksRecView = findViewById(R.id.booksRecView);

        booksRecView.setAdapter(bookRecyclerViewAdapter);
        booksRecView.setLayoutManager(new GridLayoutManager(this, 1));

        ArrayList<Book> books = new ArrayList<>();
        books.add(new Book(1, "The Great Gatsby", "F. Scott Fitzgerald", "180",
                "https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcSuJ_yewMdULCJT15hAwyU-OikRIU0GC6yPuUyaBcEk08yEMc5E",
                " A Novel by F. Scott Fitzgerald",
                "The Great Gatsby, F. Scott Fitzgerald’s third book, stands as the supreme achievement of his career." +
                        " First published in 1925, this quintessential novel of the Jazz Age has been acclaimed by generations of readers." +
                        " The story of the mysteriously wealthy Jay Gatsby and his love for the beautiful Daisy Buchanan," +
                        " of lavish parties on Long Island at a time when The New York Times noted “gin was the national drink and sex the national obsession,” it is an exquisitely crafted tale of America in the 1920s."));
        books.add(new Book(2, "Dune", "Frank Herbert", "412",
                "https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1555447414l/44767458.jpg",
                "Science-fiction novel by American author Frank Herbert",
                "Dune is set in the distant future amidst a feudal interstellar society in which various noble houses control planetary fiefs." +
                        "It tells the story of young Paul Atreides, whose family accepts the stewardship of the planet Arrakis."));
//        books.add(new Book(3, "The Catcher in the Rye", "J. D. Salinger", "234"))
        bookRecyclerViewAdapter.setBooks(books);
    }
}