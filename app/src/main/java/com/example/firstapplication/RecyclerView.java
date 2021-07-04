package com.example.firstapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class RecyclerView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        androidx.recyclerview.widget.RecyclerView contactsRecView = findViewById(R.id.recyclerView);

        ArrayList<Contact> contacts = new ArrayList<>();
        contacts.add(new Contact("Gojo", "gojo.satoru@jujutsukaisen.com", "https://static.wikia.nocookie.net/jujutsu-kaisen/images/5/5a/Satoru_Gojo_arrives_on_the_battlefield_%28Anime%29.png/revision/latest?cb=20210226205256"));
        contacts.add(new Contact("Jotaro", "jotaro.kujo@jojo.com", "https://static.wikia.nocookie.net/characters/images/3/37/Jotaro-kujo-19368.jpg/revision/latest/top-crop/width/360/height/450?cb=20201018085812"));
        contacts.add(new Contact("Thorfinn", "thorfinn@vinlandsaga.com", "https://i1.wp.com/www.cercatoridiatlantide.it/wp-content/uploads/2019/09/vinland_saga_08_thorfinn.png?fit=1024%2C573&ssl=1"));
        contacts.add(new Contact("Loki", "loki.lauffeyson@mcu.com", "https://images.immediate.co.uk/production/volatile/sites/3/2020/09/loki-tv-show-ddb01c2.jpg?webp=true&quality=90&resize=620%2C413"));

        ContactRecViewAdapter contactRecViewAdapter = new ContactRecViewAdapter(this);

        contactRecViewAdapter.setContacts(contacts);

        contactsRecView.setAdapter(contactRecViewAdapter);

        // programatically managed layout
        contactsRecView.setLayoutManager(new GridLayoutManager(this, 1));
    }
}

