package com.example.moviedb4;

// GenreFragment.java

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GenreFragment extends Fragment {


    private ListView listViewGenres;
    private GenreAdapter genreAdapter;

    public GenreFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_genre, container, false);

        listViewGenres = view.findViewById(R.id.listViewGenres);
        genreAdapter = new GenreAdapter(requireContext(), new ArrayList<>());
        listViewGenres.setAdapter(genreAdapter);

        // Fetch and display genres (you need to implement this method)
        fetchGenres();

        return view;
    }

    private void fetchGenres() {
        GenreApiService genreApiService = RetrofitClient.getRetrofit().create(GenreApiService.class);
        Call<GenreApiResponse> call = genreApiService.getGenres("Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJiMDViNjUwZjk0ODEwNGY0NzlmZWEwNGNhYjRhMDE0MSIsInN1YiI6IjY1NTcxODQ5ZWE4NGM3MTA5MjI4OTFiOSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.esDRGkCwXKGin7BbuAB6-SkDwf_lQqMV92YdVZk9whE", "application/json");

        call.enqueue(new Callback<GenreApiResponse>() {
            @Override
            public void onResponse(@NonNull Call<GenreApiResponse> call, @NonNull Response<GenreApiResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    GenreApiResponse genreApiResponse = response.body();
                    List<Genre> genres = genreApiResponse.getGenres();
                    displayGenres(genres);
                }
            }

            @Override
            public void onFailure(@NonNull Call<GenreApiResponse> call, @NonNull Throwable t) {
                // Handle network failure
                t.printStackTrace();
            }
        });
    }

    private void displayGenres(List<Genre> genres) {
        // Use your custom GenreAdapter instead of ArrayAdapter
        genreAdapter.clear();  // Clear existing data if any

        // Add the fetched genres to the adapter
        genreAdapter.addAll(genres);

        Log.d("VCustom isi genre : ", String.valueOf(genres));

        listViewGenres.setAdapter(genreAdapter);

        listViewGenres.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Handle genre item click
                Genre selectedGenre = genres.get(position);

                // Pass the selected genre to the movie fragment to load movies based on the genre
                // ((MovieFragment) requireActivity().getSupportFragmentManager().findFragmentById(R.id.fragment_container))
                //        .fetchMoviesByGenre(selectedGenre.getName());

                // Close the drawer if open
                // ((MainActivity) requireActivity()).closeDrawer();
            }
        });
    }

}