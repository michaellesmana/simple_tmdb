package com.example.moviedb4;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.text.DecimalFormat;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private List<Movie> movieList;
    private Context context; // Add a Context variable

    private DetailApiService detailApiService;

    public MovieAdapter(List<Movie> movieList, Context context) {
        this.movieList = movieList;
        this.context = context;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Movie movie = movieList.get(position);

        // Set data to views

        Glide.with(context)
                .load("https://image.tmdb.org/t/p/w500" + movie.getPoster_path()) // Base URL for images
                .into(holder.imageViewMovie);

        holder.textViewTitle.setText(movie.getTitle());

        DecimalFormat df = new DecimalFormat("#.##");
        df.setGroupingUsed(false);

        holder.textViewRating.setText(df.format(movie.getVote_average()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                setupRetrofit();

                long ID = movie.getId();

                Log.d("VCustom item clicked : ", String.valueOf(ID));

                if (SharedVariables.MENU >= 0 && SharedVariables.MENU <= 2) {
                    //movie

                    fetchMovieDetails(ID);
                } else if (SharedVariables.MENU == 4) {
                    //TV

                    fetchTVsDetails(ID);
                } else if (SharedVariables.MENU == 6) {
                    //Person

                    fetchPersonDetails(ID);
                }
            }
        });
    }

    private void setupRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        detailApiService = retrofit.create(DetailApiService.class);
    }

    public void fetchMovieDetails(long movieId) {
        // Use Retrofit or your preferred networking library to make the API call
        // Replace the placeholder URL with the actual URL for movie details
        String apiKey = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJiMDViNjUwZjk0ODEwNGY0NzlmZWEwNGNhYjRhMDE0MSIsInN1YiI6IjY1NTcxODQ5ZWE4NGM3MTA5MjI4OTFiOSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.esDRGkCwXKGin7BbuAB6-SkDwf_lQqMV92YdVZk9whE"; // Replace with your API key
        String language = "en-US"; // Replace with the desired language
        String accept = "application/json";

        Call<MovieDetail> call = detailApiService.getMovieDetail(movieId, language, apiKey, accept);
        call.enqueue(new Callback<MovieDetail>() {
            @Override
            public void onResponse(Call<MovieDetail> call, Response<MovieDetail> response) {
                if (response.isSuccessful()) {
                    // Handle the response and update your UI with movie details
                    MovieDetail movieDetail = response.body();
                    // TODO: Update UI with movie details

                    String title = movieDetail.getTitle();
                    String date = movieDetail.getReleaseDate();
                    String overview = movieDetail.getOverview();
                    String picturePath = movieDetail.getBackdropPath();

                    Log.d("VCustom picture path : ", picturePath);

                    // Start the DetailActivity and pass the necessary data
                    Intent intent = new Intent(context, DetailActivity.class);
                    intent.putExtra("MOVIE_TITLE", title);
                    intent.putExtra("MOVIE_DATE", date);
                    intent.putExtra("MOVIE_OVERVIEW", overview);
                    intent.putExtra("MOVIE_PICTURE_PATH", picturePath);
                    context.startActivity(intent);
                } else {
                    // Handle error response
                    // TODO: Show error message
                }
            }

            @Override
            public void onFailure(Call<MovieDetail> call, Throwable t) {
                // Handle network failure
                // TODO: Show error message
            }
        });
    }

    public void fetchTVsDetails(long tvID) {
        // Use Retrofit or your preferred networking library to make the API call
        // Replace the placeholder URL with the actual URL for movie details
        String apiKey = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJiMDViNjUwZjk0ODEwNGY0NzlmZWEwNGNhYjRhMDE0MSIsInN1YiI6IjY1NTcxODQ5ZWE4NGM3MTA5MjI4OTFiOSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.esDRGkCwXKGin7BbuAB6-SkDwf_lQqMV92YdVZk9whE"; // Replace with your API key
        String language = "en-US"; // Replace with the desired language
        String accept = "application/json";

        Call<TVsDetail> call = detailApiService.getTvDetail(tvID, language, apiKey, accept);
        call.enqueue(new Callback<TVsDetail>() {
            @Override
            public void onResponse(Call<TVsDetail> call, Response<TVsDetail> response) {
                if (response.isSuccessful()) {
                    // Handle the response and update your UI with movie details
                    TVsDetail tVsDetail = response.body();
                    // TODO: Update UI with movie details

                    String title = tVsDetail.getName();
                    String date = tVsDetail.getFirstAirDate();
                    String overview = tVsDetail.getOverview();
                    String picturePath = tVsDetail.getBackdropPath();

                    Log.d("VCustom picture path : ", picturePath);

                    // Start the DetailActivity and pass the necessary data
                    Intent intent = new Intent(context, DetailActivity.class);
                    intent.putExtra("MOVIE_TITLE", title);
                    intent.putExtra("MOVIE_DATE", date);
                    intent.putExtra("MOVIE_OVERVIEW", overview);
                    intent.putExtra("MOVIE_PICTURE_PATH", picturePath);
                    context.startActivity(intent);
                } else {
                    // Handle error response
                    // TODO: Show error message
                }
            }

            @Override
            public void onFailure(Call<TVsDetail> call, Throwable t) {
                // Handle network failure
                // TODO: Show error message
            }
        });
    }

    public void fetchPersonDetails(long personID) {
        // Use Retrofit or your preferred networking library to make the API call
        // Replace the placeholder URL with the actual URL for movie details
        String apiKey = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJiMDViNjUwZjk0ODEwNGY0NzlmZWEwNGNhYjRhMDE0MSIsInN1YiI6IjY1NTcxODQ5ZWE4NGM3MTA5MjI4OTFiOSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.esDRGkCwXKGin7BbuAB6-SkDwf_lQqMV92YdVZk9whE"; // Replace with your API key
        String language = "en-US"; // Replace with the desired language
        String accept = "application/json";

        Call<PersonDetail> call = detailApiService.getPersonDetail(personID, language, apiKey, accept);
        call.enqueue(new Callback<PersonDetail>() {
            @Override
            public void onResponse(Call<PersonDetail> call, Response<PersonDetail> response) {
                if (response.isSuccessful()) {
                    // Handle the response and update your UI with movie details
                    PersonDetail personDetail = response.body();
                    // TODO: Update UI with movie details

                    String title = personDetail.getName();
                    String date = personDetail.getBirthday();
                    String overview = personDetail.getBiography();
                    String picturePath = personDetail.getProfilePath();

                    Log.d("VCustom picture path : ", picturePath);

                    // Start the DetailActivity and pass the necessary data
                    Intent intent = new Intent(context, DetailActivity.class);
                    intent.putExtra("MOVIE_TITLE", title);
                    intent.putExtra("MOVIE_DATE", date);
                    intent.putExtra("MOVIE_OVERVIEW", overview);
                    intent.putExtra("MOVIE_PICTURE_PATH", picturePath);
                    context.startActivity(intent);
                } else {
                    // Handle error response
                    // TODO: Show error message
                }
            }

            @Override
            public void onFailure(Call<PersonDetail> call, Throwable t) {
                // Handle network failure
                // TODO: Show error message
            }
        });
    }


    @Override
    public int getItemCount() {
        return movieList.size();
    }

    static class MovieViewHolder extends RecyclerView.ViewHolder {

        ImageView imageViewMovie;
        TextView textViewTitle, textViewRating;

        MovieViewHolder(@NonNull View itemView) {
            super(itemView);

            imageViewMovie = itemView.findViewById(R.id.imageViewMovie);
            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewRating = itemView.findViewById(R.id.textViewRating);
        }
    }
}
