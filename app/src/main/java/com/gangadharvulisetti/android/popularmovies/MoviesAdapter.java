package com.gangadharvulisetti.android.popularmovies;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by swamygangadharpavanvulisetti on 4/18/18.
 */

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieViewHolder> {

    private List<Movie> mMovies;
    private Context mContext;


    public MoviesAdapter(Context context, List<Movie> movies) {
        mContext = context;
        mMovies = movies;
    }


    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        view = inflater.inflate(R.layout.movie_card_view, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, final int position) {

        holder.movieTitle.setText(mMovies.get(position).getTitle());

        //Loading Images using Picasso

        Picasso.with(mContext)
                .load(mMovies.get(position).getPoster())
                .into(holder.moviePoster);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(mContext, MovieDetailsActivity.class);
                i.putExtra("Movie_image", mMovies.get(position).getPoster());
                i.putExtra("Movie_title", mMovies.get(position).getTitle());
                i.putExtra("Movie_rating",mMovies.get(position).getVoteAverage());
                i.putExtra("Movie_release_date",mMovies.get(position).getReleaseDate());
                i.putExtra("Movie_description",mMovies.get(position).getOverView());

                mContext.startActivity(i);

            }
        });

    }

    @Override
    public int getItemCount() {
        return mMovies.size();
    }

    public static class MovieViewHolder extends RecyclerView.ViewHolder {

        private ImageView moviePoster;
        private TextView movieTitle;
        private CardView cardView;

        public MovieViewHolder(View itemView) {
            super(itemView);

            moviePoster = (ImageView) itemView.findViewById(R.id.movie_image_id);
            movieTitle = (TextView) itemView.findViewById(R.id.movie_title_id);
            cardView = (CardView) itemView.findViewById(R.id.card_view_id);


        }

    }

}
