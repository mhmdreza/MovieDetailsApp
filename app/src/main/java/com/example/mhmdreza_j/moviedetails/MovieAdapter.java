package com.example.mhmdreza_j.moviedetails;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mhmdreza_j.moviedetails.listener.MovieListener;
import com.example.mhmdreza_j.moviedetails.models.SearchResult;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieViewHolder> {
    private MovieListener listener;
    private ArrayList<SearchResult> searchResults;

    public MovieAdapter(MovieListener listener, ArrayList<SearchResult> searchResults) {
        this.listener = listener;
        this.searchResults = searchResults;
    }
    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = (LayoutInflater.from(viewGroup.getContext())).inflate(R.layout.movie_details_card, null);
        return new MovieViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder movieViewHolder, int i) {
        movieViewHolder.setCardData(searchResults.get(i), i);
    }

    @Override
    public int getItemCount() {
        if (searchResults == null){
            return 0;
        }
        return searchResults.size();
    }
}
