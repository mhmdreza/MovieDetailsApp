package com.example.mhmdreza_j.moviedetails.network;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieProvider {
    private static final String BASE_URL = "http://www.omdbapi.com/";
    private MovieService service;
    
    public MovieProvider(){
            Retrofit retrofit = new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(BASE_URL)
                    .client(new OkHttpClient())
                    .build();
            service = retrofit.create(MovieService.class);
    }

    public MovieService getService() {
        return service;
    }
}
