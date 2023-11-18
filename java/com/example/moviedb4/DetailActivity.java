package com.example.moviedb4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // Find views in your layout
        ImageView imageViewPoster = findViewById(R.id.imageViewPoster);
        TextView textViewTitle = findViewById(R.id.textViewTitle);
        TextView textViewDate = findViewById(R.id.textViewDate);
        TextView textViewOverview = findViewById(R.id.textViewOverview);

        // Retrieve data from the intent
        Intent intent = getIntent();
        if (intent != null && intent.getExtras() != null) {
            String title = intent.getStringExtra("MOVIE_TITLE");
            String date = intent.getStringExtra("MOVIE_DATE");
            String overview = intent.getStringExtra("MOVIE_OVERVIEW");
            String picturePath = intent.getStringExtra("MOVIE_PICTURE_PATH");

            // Set data to views
            textViewTitle.setText(title);
            textViewDate.setText("Release Date : " + date);
            textViewOverview.setText("Overview : \n" + overview);

             Glide.with(this).load("https://image.tmdb.org/t/p/w500" + picturePath).into(imageViewPoster);
        }
    }
}
