package com.gangadharvulisetti.android.popularmovies.utilities;

import android.content.Context;
import android.text.TextUtils;

import com.gangadharvulisetti.android.popularmovies.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by swamygangadharpavanvulisetti on 4/24/18.
 */

public final class OpenMovieJsonResults {
    /**
     * This method parses JSON from a web response and returns an ArrayList of Movies
     * @param movieJsonStr JSON response from server
     *
     * @return Array of Strings describing weather data
     *
     * @throws JSONException If JSON data cannot be properly parsed
     */

    public static List<Movie> getMoviesFromJson(Context context, String movieJsonStr) throws JSONException{

        List<Movie> movieList = new ArrayList<Movie>();

        if (TextUtils.isEmpty(movieJsonStr)) {
            return null;
        }

        try {

            JSONObject baseJsonResopnse = new JSONObject(movieJsonStr);
            JSONArray moviesArray = baseJsonResopnse.getJSONArray("results");

            for (int i = 0; i < moviesArray.length(); i++) {

                JSONObject movieDetails = moviesArray.getJSONObject(i);

                String title = movieDetails.getString("title");
                String poster = movieDetails.getString("poster_path");
                String overView = movieDetails.getString("overview");
                double voteAverage = movieDetails.getDouble("vote_average");
                String releaseDate = movieDetails.getString("release_date");

                movieList.add(new Movie(title, poster, overView, voteAverage, releaseDate));
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }

        return movieList;


    }
}
