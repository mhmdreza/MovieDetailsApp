package com.example.mhmdreza_j.moviedetails.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mhmdreza_j.moviedetails.MovieAdapter;
import com.example.mhmdreza_j.moviedetails.R;
import com.example.mhmdreza_j.moviedetails.listener.MovieListener;
import com.example.mhmdreza_j.moviedetails.models.MovieDetailsModel;
import com.example.mhmdreza_j.moviedetails.models.MoviesModel;
import com.example.mhmdreza_j.moviedetails.models.SearchResult;
import com.example.mhmdreza_j.moviedetails.network.MovieCallback;
import com.example.mhmdreza_j.moviedetails.network.MovieCallbackListener;
import com.example.mhmdreza_j.moviedetails.network.MovieProvider;
import com.example.mhmdreza_j.moviedetails.network.MovieService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.provider.MediaStore.Audio.AudioColumns.TITLE_KEY;

public class MainActivity extends AppCompatActivity
        implements MovieCallbackListener, MovieListener{
    private static final String API_KEY = "9e713962";
    static final String MOVIE_TITLE_KEY = "MOVIE_TITLE_KEY";
    static final String MOVIE_YEAR_KEY = "MOVIE_YEAR_KEY";
    static final String MOVIE_DIRECTOR_KEY = "MOVIE_DIRECTOR_KEY";
    static final String MOVIE_ACTORS_KEY = "MOVIE_ACTORS_KEY";
    static final String MOVIE_POSTER_KEY = "MOVIE_POSTER_KEY";
    static final String MOVIE_PLOT_KEY = "MOVIE_PLOT_KEY";
    private EditText movieNameEditText;
    private MoviesModel moviesModel;
    private RecyclerView recyclerView;
    private MovieAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeViews();
    }

    private void initializeViews() {
        movieNameEditText = findViewById(R.id.edit_text_movie_name);
        recyclerView = findViewById(R.id.recycler_view_movie_details);
    }

    public void onSubmitButtonClicked(View view) {
        MovieProvider provider = new MovieProvider();
        MovieService service = provider.getService();
        String movieName = movieNameEditText.getText().toString();
        Call<MoviesModel> moviesModelCall = service.getMovies(movieName, API_KEY);
        moviesModelCall.enqueue(new MovieCallback( this));
    }

    private void setMovieModel(MoviesModel moviesModel) {
        this.moviesModel = moviesModel;
    }

    @Override
    public void setData(MoviesModel moviesModel) {
        if(moviesModel.getSearchResults() == null){
            Toast.makeText(this, "no movies found!!!", Toast.LENGTH_SHORT).show();
            return;
        }
        setMovieModel(moviesModel);
        adapter = new MovieAdapter(this, moviesModel.getSearchResults());
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onClick(View view, int position) {
        MovieProvider provider = new MovieProvider();
        MovieService service = provider.getService();
        SearchResult result = moviesModel.getSearchResults().get(position);
        Call<MovieDetailsModel> call = service.getMovieDetails(result.getTitle()
                ,result.getYear()
                , API_KEY);
        call.enqueue(new Callback<MovieDetailsModel>() {
            @Override
            public void onResponse(Call<MovieDetailsModel> call, Response<MovieDetailsModel> response) {
                if (response.isSuccessful()) {
                    showMovieDetails(response.body());
                }
            }

            @Override
            public void onFailure(Call<MovieDetailsModel> call, Throwable t) {

            }
        });
    }

    private void showMovieDetails(MovieDetailsModel movieDetailsModel) {
        Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
        intent.putExtra(MOVIE_TITLE_KEY, movieDetailsModel.getTitle());
        intent.putExtra(MOVIE_YEAR_KEY, movieDetailsModel.getYear());
        intent.putExtra(MOVIE_DIRECTOR_KEY, movieDetailsModel.getDirector());
        intent.putExtra(MOVIE_ACTORS_KEY, movieDetailsModel.getActors());
        intent.putExtra(MOVIE_POSTER_KEY, movieDetailsModel.getPoster());
        intent.putExtra(MOVIE_PLOT_KEY, movieDetailsModel.getPlot());
        startActivity(intent);
    }
}
