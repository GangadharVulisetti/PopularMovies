package com.gangadharvulisetti.android.popularmovies.utilities;

import android.net.Uri;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by swamygangadharpavanvulisetti on 3/15/18.
 */

public final class NetworkUtils {

    private static final String TAG = NetworkUtils.class.getSimpleName();

    private static final String MOVIES_BASE_URL = "http://api.themoviedb.org/3/movie/";

    private static final String PARAM_QUERY = "api_key";

    private static final String API_KEY = " ";

    /**
     * Builds the URL used to query The Movie DB.
     */

    public static URL buildUrl(String showMovies) {
        Uri builtUri = Uri.parse(MOVIES_BASE_URL + showMovies)
                .buildUpon()
                .appendQueryParameter(PARAM_QUERY, API_KEY)
                .build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        Log.v(TAG, "Built URI " + url);
        return url;
    }

    /**
     * This method returns the entire result from the HTTP response.
     *
     * @param url The URL to fetch the HTTP response from.
     * @return The contents of the HTTP response.
     * @throws IOException Related to network and stream reading
     */

    public static String getResponseFromHttpUrl(URL url)throws IOException{
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {

            InputStream in = urlConnection.getInputStream();

            Scanner scan = new Scanner(in);
            scan.useDelimiter("\\A");

            boolean hasInput = scan.hasNext();

            if (hasInput) {
                return scan.next();
            } else {
                return null;
            }
        }finally {
            urlConnection.disconnect();
        }

    }

}
