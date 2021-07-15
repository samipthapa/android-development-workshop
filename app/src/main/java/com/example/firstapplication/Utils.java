package com.example.firstapplication;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class Utils {

    private static final String ALL_BOOKS = "books";
    private static final String ALREADY_READ_BOOKS = "already_read_books";
    private static final String CURRENTLY_READING_BOOKS = "currently_reading_books";

    public static Utils instance;

    private SharedPreferences sharedPreferences;

    public ArrayList<Book> getBooks() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Book>>(){}.getType();
        ArrayList<Book> books = gson.fromJson(sharedPreferences.getString(ALL_BOOKS, null), type);
        return books;
    }

    public ArrayList<Book> getCurrentlyReadingBooks() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Book>>(){}.getType();
        ArrayList<Book> books = gson.fromJson(sharedPreferences.getString(CURRENTLY_READING_BOOKS, null), type);
        return books;
    }

    public ArrayList<Book> getAlreadyReadBooks() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Book>>(){}.getType();
        return gson.fromJson(sharedPreferences.getString(ALREADY_READ_BOOKS, null), type);
    }

    public Utils(Context context) {
        sharedPreferences = context.getSharedPreferences("alternate_db", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();

        if (null == getBooks()) {
            initData();
        }
        if (null == getAlreadyReadBooks()) {
            editor.putString(ALREADY_READ_BOOKS, gson.toJson(new ArrayList<Book>()));
            editor.commit();
        }
        if (null == getCurrentlyReadingBooks()) {
            editor.putString(CURRENTLY_READING_BOOKS, gson.toJson(new ArrayList<Book>()));
            editor.commit();
        }

    }

    public static synchronized Utils getInstance(Context context) {
        //singleton pattern
        if (null == instance) {
            instance = new Utils(context);
        }
        return instance;
    }

    private void initData() {
        ArrayList<Book> books = new ArrayList<>();
        books.add(new Book(1, "Dune", "Frank Herbert", "890",
                "https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1546269682l/43419431._SY475_.jpg",
                "Frank Herbert’s classic masterpiece—a triumph of the imagination and one of the bestselling science fiction novels of all time.",
                "Set on the desert planet Arrakis, Dune is the story of the boy Paul Atreides, heir to a noble family tasked with ruling an inhospitable world where the only thing of value is the “spice” melange, a drug capable of extending life and enhancing consciousness. Coveted across the known universe, melange is a prize worth killing for...."));
        books.add(new Book(2, "The Murder of Roger Ackroyd", "Agatha Christie", "288",
                "https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1389734015l/16328.jpg",
                "Considered to be one of Agatha Christie's most controversial mysteries, The Murder of Roger Ackroyd breaks all the rules of traditional mystery writing.",
                "The peaceful English village of King’s Abbot is stunned. First, the attractive widow Ferrars dies from an overdose of veronal. Not twenty-four hours later, Roger Ackroyd—the man she had planned to marry—is murdered. It is a baffling, complex case involving blackmail, suicide, and violent death, a cast that taxes Hercule Poirot’s “little grey cells” before he reaches one of the most startling conclusions of his fabled career."));
        books.add(new Book(3, "The Catcher in the Rye", "J. D. Salinger", "277",
                "https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1398034300l/5107.jpg",
                "The Catcher in the Rye, novel by J.D. Salinger published in 1951.",
                "The novel details two days in the life of 16-year-old Holden Caulfield after he has been expelled from prep school. Confused and disillusioned, Holden searches for truth and rails against the “phoniness” of the adult world. He ends up exhausted and emotionally unstable. The events are related after the fact."));

        Gson gson = new Gson();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(ALL_BOOKS, gson.toJson(books));
        editor.commit();
    }

    public Book getBookById(int id) {
        ArrayList<Book> books = getBooks();
        if (books != null) {
            for (Book b: books) {
                if (b.getId() == id) {
                    return b;
                }
            }
        }
        return null;
    }

    public boolean addToCurrentlyReading (Book book) {
        ArrayList<Book> books = getCurrentlyReadingBooks();
        if (null != books) {
            if (books.add(book)) {
                Gson gson = new Gson();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(CURRENTLY_READING_BOOKS);
                editor.putString(CURRENTLY_READING_BOOKS, gson.toJson(books));
                editor.commit();
                return true;
            }
        }
        return false;
    }

    public boolean removeFromAlreadyReadBooks (Book book) {
        ArrayList<Book> books = getAlreadyReadBooks();
        if (null != books) {
            for (Book b: books) {
                if (b.getId() == book.getId()) {
                    if (books.remove(b)) {
                        Gson gson = new Gson();
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove(ALREADY_READ_BOOKS);
                        editor.putString(ALREADY_READ_BOOKS, gson.toJson(books));
                        editor.commit();
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean removeFromCurrentlyReading (Book book) {
        ArrayList<Book> books = getCurrentlyReadingBooks();
        if (null != books) {
            for (Book b: books) {
                if (b.getId() == book.getId()) {
                    if (books.remove(b)) {
                        Gson gson = new Gson();
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove(CURRENTLY_READING_BOOKS);
                        editor.putString(CURRENTLY_READING_BOOKS, gson.toJson(books));
                        editor.commit();
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean addToAlreadyRead(Book book) {
        ArrayList<Book> books = getAlreadyReadBooks();
        if (null != books) {
            if (books.add(book)) {
                Gson gson = new Gson();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(ALREADY_READ_BOOKS);
                editor.putString(ALREADY_READ_BOOKS, gson.toJson(books));
                editor.commit();
                return true;
            }
        }
        return false;
    }
}
