package com.example.contacts;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class PersonDetailActivity extends AppCompatActivity {

    String contact_name,contact_position,contact_image;
    ImageView person_image_big,person_image_mini;
    TextView person_name,person_position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.person_detail);

        contact_name = getIntent().getStringExtra("name");
        contact_position = getIntent().getStringExtra("position");
        contact_image = getIntent().getStringExtra("image_url");

        person_image_big = findViewById(R.id.contact_image_big);
        person_image_mini = findViewById(R.id.contact_image_mini);
        person_name = findViewById(R.id.contact_name);
        person_position = findViewById(R.id.contact_position);

        person_name.setText("Name : "+ contact_name);
        person_position.setText("Position : "+ contact_position);

        Glide.with(PersonDetailActivity.this).load(contact_image).into(person_image_big);
        Glide.with(PersonDetailActivity.this).load(contact_image).into(person_image_mini);
    }
}