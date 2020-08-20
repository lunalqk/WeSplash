package com.example.wesplash;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Pairing extends AppCompatActivity {
    private TextView Activity;
    private TextView Tips;
    private TextView Pairing;
    private TextView Blog;
    private TextView Shop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pairing);

        Activity = findViewById(R.id.Activity);
        Tips = findViewById(R.id.Tips);
        Pairing = findViewById(R.id.Pairing);
        Blog = findViewById(R.id.Blog);
        Shop = findViewById(R.id.Shop);

        Tips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        Activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        Blog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        Shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}