package com.example.mhmdreza_j.moviedetails.models;

public class MovieDetailsModel {
    private String Title;
    private String Year;
    private String Poster;
    private String Plot;

    public String getTitle() {
        return Title;
    }

    public String getYear() {
        return Year;
    }

    public String getPoster() {
        return Poster;
    }

    public String getPlot() {
        return Plot;
    }

    public String getDirector() {
        return Director;
    }

    public String getActors() {
        return Actors;
    }

    private String Director;
    private String Actors;
}
