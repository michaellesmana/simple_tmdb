package com.example.moviedb4;

import java.util.List;

public class Movie {
    private boolean adult;
    private String backdrop_path;
    private List<Integer> genre_ids;
    private long id;
    private String original_language;
    private String original_title;
    private String overview;
    private double popularity;
    private String poster_path;
    private String release_date;
    private String title;
    private boolean video;
    private float vote_average;
    private int vote_count;

    // Add any other fields you need

    public Movie(boolean adult, String backdrop_path, List<Integer> genre_ids, long id,
                 String original_language, String original_title, String overview,
                 double popularity, String poster_path, String release_date, String title,
                 boolean video, float vote_average, int vote_count) {
        this.adult = adult;
        this.backdrop_path = backdrop_path;
        this.genre_ids = genre_ids;
        this.id = id;
        this.original_language = original_language;
        this.original_title = original_title;
        this.overview = overview;
        this.popularity = popularity;
        this.poster_path = poster_path;
        this.release_date = release_date;
        this.title = title;
        this.video = video;
        this.vote_average = vote_average;
        this.vote_count = vote_count;
    }

    // Add getters for each field

    public String toLog() {
        return "Movie{" +
                "imageResId=" + backdrop_path +
                ", title='" + title + '\'' +
                ", rate=" + vote_average +
                '}';
    }

    public boolean isAdult() {
        return adult;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public List<Integer> getGenre_ids() {
        return genre_ids;
    }

    public long getId() {
        return id;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public String getOverview() {
        return overview;
    }

    public double getPopularity() {
        return popularity;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public String getRelease_date() {
        return release_date;
    }

    public String getTitle() {
        return title;
    }

    public boolean isVideo() {
        return video;
    }

    public float getVote_average() {
        return vote_average;
    }

    public int getVote_count() {
        return vote_count;
    }
}
