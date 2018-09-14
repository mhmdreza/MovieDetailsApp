package com.example.mhmdreza_j.moviedetails.network;

import com.example.mhmdreza_j.moviedetails.models.MovieDetailsModel;
import com.example.mhmdreza_j.moviedetails.models.MoviesModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieService {
    @GET(".")
    Call<MoviesModel> getMovies(@Query("s") String title
            , @Query("apikey") String apiKey);

    @GET(".")
    Call<MovieDetailsModel> getMovieDetails(@Query("t") String title
            , @Query("year") String year
            , @Query("apikey") String apiKey);
}
