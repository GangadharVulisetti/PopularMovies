package com.gangadharvulisetti.android.popularmovies;

/**
 * Created by swamygangadharpavanvulisetti on 3/21/18.
 */

public class Movie {

    //Model

    private String title;
    private String poster;
    private String overView;
    private double voteAverage;
    private String releaseDate;

    public Movie(String title, String poster, String overView, double voteAverage, String releaseDate) {
        this.title = title;
        this.poster = poster;
        this.overView = overView;
        this.voteAverage = voteAverage;
        this.releaseDate = releaseDate;
    }


    public void setTitle(String title) {
        this.title = title;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public void setOverView(String overView) {
        this.overView = overView;
    }

    public void setVoteAverage(double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getTitle() {
        return title;
    }

    public String getPoster() {
        return "http://image.tmdb.org/t/p/w500/" + poster;
    }

    public String getOverView() {
        return overView;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public String getReleaseDate() {
        return releaseDate;
    }
}
