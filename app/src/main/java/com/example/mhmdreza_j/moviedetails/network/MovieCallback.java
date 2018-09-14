package com.example.mhmdreza_j.moviedetails.network;

import com.example.mhmdreza_j.moviedetails.models.MoviesModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieCallback implements Callback<MoviesModel> {
    private MovieCallbackListener listener;

    public MovieCallback(MovieCallbackListener listener) {
        this.listener = listener;
    }

    @Override
    public void onResponse(Call<MoviesModel> call, Response<MoviesModel> response) {
        if (response.isSuccessful()) {
            listener.setData(response.body());
        }
    }

    @Override
    public void onFailure(Call<MoviesModel> call, Throwable t) {
    }
}
