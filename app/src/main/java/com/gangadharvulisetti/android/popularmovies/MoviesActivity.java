package com.gangadharvulisetti.android.popularmovies;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.gangadharvulisetti.android.popularmovies.utilities.NetworkUtils;
import com.gangadharvulisetti.android.popularmovies.utilities.OpenMovieJsonResults;

import java.net.URL;
import java.util.List;

public class MoviesActivity extends AppCompatActivity {

    private MoviesAdapter mMovieAdapter;

    private RecyclerView mRecyclerView;

    private final String TOP_RATED = "top_rated";
    private final String NOW_PLAYING = "now_playing";
    private final String POPULAR = "popular";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        loadMovieData(NOW_PLAYING);
    }

    private void loadMovieData(String displayingMovies) {
        new FetchMoviesTask().execute(displayingMovies);
    }


    public class FetchMoviesTask extends AsyncTask<String, Void, List<Movie>> {

        @Override
        protected List<Movie> doInBackground(String... params) {

            if (params[0] == null || params.length == 0){
                return null;
            }

            String showMovies = params[0];
            URL moviesRequestUrl = NetworkUtils.buildUrl(showMovies);

            try {
                String jsonMovieResponse = NetworkUtils.getResponseFromHttpUrl(moviesRequestUrl);

                List<Movie> movies = OpenMovieJsonResults.getMoviesFromJson(MoviesActivity.this, jsonMovieResponse);

                return movies;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }

        }

        @Override
        protected void onPostExecute(List<Movie> movies) {
            if (movies != null) {
                mMovieAdapter = new MoviesAdapter(MoviesActivity.this, movies);
                mRecyclerView.setLayoutManager(new GridLayoutManager(MoviesActivity.this, 2));
                mRecyclerView.setAdapter(mMovieAdapter);
            }
        }
    }

    // Creating the Menu

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.sort_movies,menu);
        return true;
    }

    // Creating the options in the Menu

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_most_popular:
                loadMovieData(POPULAR);
                return true;
            case R.id.action_highest_rated:
                loadMovieData(TOP_RATED);
                return true;
            default:
                return super.onOptionsItemSelected(item);
    }
}

}