package com.example.mhmdreza_j.moviedetails;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mhmdreza_j.moviedetails.listener.MovieListener;
import com.example.mhmdreza_j.moviedetails.models.SearchResult;

public class MovieViewHolder extends RecyclerView.ViewHolder {
    private ImageView movieImageView;
    private TextView movieNameTextView;
    private TextView movieYearTextView;
    private View itemView;
    private int position;

    public MovieViewHolder(@NonNull View itemView, final MovieListener listener) {
        super(itemView);
        this.itemView = itemView;
        movieImageView = itemView.findViewById(R.id.image_view_movie_detail);
        movieNameTextView = itemView.findViewById(R.id.text_view_movie_title);
        movieYearTextView = itemView.findViewById(R.id.text_view_movie_year);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onClick(view, getAdapterPosition());
            }
        });
    }

    public void setCardData(SearchResult result, int position){
        this.position = position;
        Glide.with(itemView.getContext())
                .load(result.getPoster())
                .into(movieImageView);
        movieNameTextView.setText(result.getTitle());
        movieYearTextView.setText(result.getYear());
    }
}
