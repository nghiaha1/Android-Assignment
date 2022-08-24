package com.example.android_assignment.model;

import java.util.List;

public class Section {
    private String title;
    private List<Movie> movieList;

    public Section() {
    }

    public Section(String title, List<Movie> movieList) {
        this.title = title;
        this.movieList = movieList;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Movie> getMovieList() {
        return movieList;
    }

    public void setMovieList(List<Movie> movieList) {
        this.movieList = movieList;
    }
}
