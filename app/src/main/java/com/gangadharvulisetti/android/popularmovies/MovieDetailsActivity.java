package com.gangadharvulisetti.android.popularmovies;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class MovieDetailsActivity extends AppCompatActivity {

    private TextView tv_title;
    private TextView tv_description;
    private TextView tv_rating;
    private TextView tv_release_date;
    private ImageView iv_movie_poster;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        // Hiding the Action Bar
        getSupportActionBar().hide();

        // get intent data
        Intent intent = getIntent();

        String Poster = intent.getExtras().getString("Movie_image");
        String Title = intent.getExtras().getString("Movie_title");
        String Description = intent.getExtras().getString("Movie_description");
        String ReleaseDate = intent.getExtras().getString("Movie_release_date");
        double Rating = intent.getExtras().getDouble("Movie_rating");



        CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.collapsing_toolbar_id);
        collapsingToolbarLayout.setTitleEnabled(true);

        tv_title = findViewById(R.id.md_movie_title_id);
        tv_rating = findViewById(R.id.md_rating_id);
        tv_release_date = findViewById(R.id.md_release_date_id);
        tv_description = findViewById(R.id.md_movie_description);
        iv_movie_poster = findViewById(R.id.md_movie_poster_id);


        tv_title.setText(Title);
        tv_description.setText(Description);
        tv_release_date.setText(ReleaseDate);
        tv_rating.setText(Rating + "");


        collapsingToolbarLayout.setTitle(Title);

        // Set Image using Picasso

        Picasso.with(this)
                .load(Poster)
                .into(iv_movie_poster);


    }
}
