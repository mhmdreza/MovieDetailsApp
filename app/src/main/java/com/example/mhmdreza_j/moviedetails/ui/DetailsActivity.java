package com.example.mhmdreza_j.moviedetails.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mhmdreza_j.moviedetails.R;

import static com.example.mhmdreza_j.moviedetails.ui.MainActivity.MOVIE_ACTORS_KEY;
import static com.example.mhmdreza_j.moviedetails.ui.MainActivity.MOVIE_DIRECTOR_KEY;
import static com.example.mhmdreza_j.moviedetails.ui.MainActivity.MOVIE_PLOT_KEY;
import static com.example.mhmdreza_j.moviedetails.ui.MainActivity.MOVIE_POSTER_KEY;
import static com.example.mhmdreza_j.moviedetails.ui.MainActivity.MOVIE_TITLE_KEY;
import static com.example.mhmdreza_j.moviedetails.ui.MainActivity.MOVIE_YEAR_KEY;

public class DetailsActivity extends AppCompatActivity {
    private ImageView posterImageView;
    private TextView titleTextView;
    private TextView directorTextView;
    private TextView yearTextView;
    private TextView actorsTextView;
    private TextView plotTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        initialViews();
        setViews();
    }

    private void initialViews() {
        posterImageView = findViewById(R.id.image_view_movie_image);
        titleTextView = findViewById(R.id.text_view_movie_name);
        directorTextView = findViewById(R.id.text_view_director);
        actorsTextView = findViewById(R.id.text_view_actors);
        plotTextView = findViewById(R.id.text_view_plot);
        yearTextView = findViewById(R.id.text_view_year);
    }

    private void setViews() {
        String year = getIntent().getStringExtra(MOVIE_YEAR_KEY);
        String director = getIntent().getStringExtra(MOVIE_DIRECTOR_KEY);
        String actors = getIntent().getStringExtra(MOVIE_ACTORS_KEY);
        String poster = getIntent().getStringExtra(MOVIE_POSTER_KEY);
        String plot = getIntent().getStringExtra(MOVIE_PLOT_KEY);
        String title = getIntent().getStringExtra(MOVIE_TITLE_KEY);

        yearTextView.setText(String.format("year: %s", year));
        directorTextView.setText(String.format("director: %s", director));
        actorsTextView.setText(String.format("actors: %s", actors));
        plotTextView.setText(String.format("plot: %s", plot));
        titleTextView.setText(String.format("title: %s", title));
        Glide.with(this).load(poster).into(posterImageView);
    }
}
