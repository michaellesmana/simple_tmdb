package com.example.moviedb4;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieFragment extends Fragment {

    private List<Movie> movieList;
    private MovieAdapter movieAdapter;
    private MovieApiService movieApiService;
    private RecyclerView recyclerView;
    private int currentPage = 1; // Keep track of the current page

    public MovieFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_movie, container, false);

        recyclerView = rootView.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(requireContext(), 3));

        movieList = new ArrayList<>();
        movieAdapter = new MovieAdapter(movieList, requireContext());
        recyclerView.setAdapter(movieAdapter);

        setupRetrofit();

        // Set up scroll listener
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                // Check if the user has reached the end of the list
                if (!recyclerView.canScrollVertically(1)) {
                    // Load more movies
                    switch (SharedVariables.MENU) {
                        case 0 :
                            fetchPopularMovies(false);
                            break;

                        case 1:
                            fetchNowPlayingMovies(false);
                            break;

                        case 2:
                            fetchUpcomingMovies(false);
                            break;

                        case 3:

                            break;

                        case 4:
                            fetchPopularTVs(false);
                            break;

                        case 5:

                            break;

                        case 6:
                            fetchPopularPerson(false);
                            break;
                    }
                }
            }
        });

        fetchPopularMovies(true); // Initial data load

        return rootView;
    }

    private void setupRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        movieApiService = retrofit.create(MovieApiService.class);
    }

    public void fetchPopularMovies(boolean isFromBeginning) {
        if (isFromBeginning) {
            currentPage = 1;
        }

        Call<MovieApiResponse> call = movieApiService.getPopularMovies(
                "en-US",
                currentPage,
                "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJiMDViNjUwZjk0ODEwNGY0NzlmZWEwNGNhYjRhMDE0MSIsInN1YiI6IjY1NTcxODQ5ZWE4NGM3MTA5MjI4OTFiOSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.esDRGkCwXKGin7BbuAB6-SkDwf_lQqMV92YdVZk9whE",
                "application/json"
        );

        call.enqueue(new Callback<MovieApiResponse>() {
            @Override
            public void onResponse(Call<MovieApiResponse> call, Response<MovieApiResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Movie> movies = response.body().getResults();

                    Gson gson = new GsonBuilder().setPrettyPrinting().create();
                    String jsonResponse = gson.toJson(response.body());

                    Log.d("VCustom list response movie : ", jsonResponse);

                    if (isFromBeginning) {
                        movieList.clear();
                    }

                    movieList.addAll(movies);
                    movieAdapter.notifyDataSetChanged();
                    currentPage++; // Increment the page number
                } else {
                    // Handle error
                    Log.e("MovieFragment", "Error: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<MovieApiResponse> call, Throwable t) {
                // Handle failure
                Log.e("MovieFragment", "Failed to fetch movies", t);
            }
        });
    }

    public void fetchNowPlayingMovies(boolean isFromBeginning) {
        if (isFromBeginning) {
            currentPage = 1;
        }

        Call<MovieApiResponse> call = movieApiService.getNowPlayingMovies(
                "en-US",
                currentPage,
                "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJiMDViNjUwZjk0ODEwNGY0NzlmZWEwNGNhYjRhMDE0MSIsInN1YiI6IjY1NTcxODQ5ZWE4NGM3MTA5MjI4OTFiOSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.esDRGkCwXKGin7BbuAB6-SkDwf_lQqMV92YdVZk9whE",
                "application/json"
        );

        call.enqueue(new Callback<MovieApiResponse>() {
            @Override
            public void onResponse(Call<MovieApiResponse> call, Response<MovieApiResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Movie> movies = response.body().getResults();

                    Gson gson = new GsonBuilder().setPrettyPrinting().create();
                    String jsonResponse = gson.toJson(response.body());

                    Log.d("VCustom list response movie : ", jsonResponse);

                    if (isFromBeginning) {
                        movieList.clear();
                    }
                    movieList.addAll(movies);
                    movieAdapter.notifyDataSetChanged();
                    currentPage++; // Increment the page number
                } else {
                    // Handle error
                    Log.e("MovieFragment", "Error: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<MovieApiResponse> call, Throwable t) {
                // Handle failure
                Log.e("MovieFragment", "Failed to fetch movies", t);
            }
        });
    }

    public void fetchUpcomingMovies(boolean isFromBeginning) {
        if (isFromBeginning) {
            currentPage = 1;
        }

        Call<MovieApiResponse> call = movieApiService.getUpcomingMovies(
                "en-US",
                currentPage,
                "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJiMDViNjUwZjk0ODEwNGY0NzlmZWEwNGNhYjRhMDE0MSIsInN1YiI6IjY1NTcxODQ5ZWE4NGM3MTA5MjI4OTFiOSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.esDRGkCwXKGin7BbuAB6-SkDwf_lQqMV92YdVZk9whE",
                "application/json"
        );

        call.enqueue(new Callback<MovieApiResponse>() {
            @Override
            public void onResponse(Call<MovieApiResponse> call, Response<MovieApiResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Movie> movies = response.body().getResults();

                    Gson gson = new GsonBuilder().setPrettyPrinting().create();
                    String jsonResponse = gson.toJson(response.body());

                    Log.d("VCustom list response movie : ", jsonResponse);

                    if (isFromBeginning) {
                        movieList.clear();
                    }
                    movieList.addAll(movies);
                    movieAdapter.notifyDataSetChanged();
                    currentPage++; // Increment the page number
                } else {
                    // Handle error
                    Log.e("MovieFragment", "Error: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<MovieApiResponse> call, Throwable t) {
                // Handle failure
                Log.e("MovieFragment", "Failed to fetch movies", t);
            }
        });
    }

    public void fetchPopularTVs(boolean isFromBeginning) {
        if (isFromBeginning) {
            currentPage = 1;
        }

        Call<MovieApiResponse> call = movieApiService.getPopularTVs(
                "en-US",
                currentPage,
                "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJiMDViNjUwZjk0ODEwNGY0NzlmZWEwNGNhYjRhMDE0MSIsInN1YiI6IjY1NTcxODQ5ZWE4NGM3MTA5MjI4OTFiOSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.esDRGkCwXKGin7BbuAB6-SkDwf_lQqMV92YdVZk9whE",
                "application/json"
        );

        call.enqueue(new Callback<MovieApiResponse>() {
            @Override
            public void onResponse(Call<MovieApiResponse> call, Response<MovieApiResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Movie> movies = response.body().getResults();

                    Gson gson = new GsonBuilder().setPrettyPrinting().create();
                    String jsonResponse = gson.toJson(response.body());

                    Log.d("VCustom list response movie : ", jsonResponse);

                    if (isFromBeginning) {
                        movieList.clear();
                    }

                    movieList.addAll(movies);
                    movieAdapter.notifyDataSetChanged();
                    currentPage++; // Increment the page number
                } else {
                    // Handle error
                    Log.e("MovieFragment", "Error: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<MovieApiResponse> call, Throwable t) {
                // Handle failure
                Log.e("MovieFragment", "Failed to fetch movies", t);
            }
        });
    }

    public void fetchPopularPerson(boolean isFromBeginning) {
        if (isFromBeginning) {
            currentPage = 1;
        }

        Call<MovieApiResponse> call = movieApiService.getPopularPerson(
                "en-US",
                currentPage,
                "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJiMDViNjUwZjk0ODEwNGY0NzlmZWEwNGNhYjRhMDE0MSIsInN1YiI6IjY1NTcxODQ5ZWE4NGM3MTA5MjI4OTFiOSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.esDRGkCwXKGin7BbuAB6-SkDwf_lQqMV92YdVZk9whE",
                "application/json"
        );

        call.enqueue(new Callback<MovieApiResponse>() {
            @Override
            public void onResponse(Call<MovieApiResponse> call, Response<MovieApiResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Movie> movies = response.body().getResults();

                    Gson gson = new GsonBuilder().setPrettyPrinting().create();
                    String jsonResponse = gson.toJson(response.body());

                    Log.d("VCustom list response movie : ", jsonResponse);

                    if (isFromBeginning) {
                        movieList.clear();
                    }

                    movieList.addAll(movies);
                    movieAdapter.notifyDataSetChanged();
                    currentPage++; // Increment the page number
                } else {
                    // Handle error
                    Log.e("MovieFragment", "Error: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<MovieApiResponse> call, Throwable t) {
                // Handle failure
                Log.e("MovieFragment", "Failed to fetch movies", t);
            }
        });
    }
}
