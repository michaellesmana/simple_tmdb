package com.example.moviedb4;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface GenreApiService {

    @GET("genre/movie/list")
    Call<GenreApiResponse> getGenres(
            @Header("Authorization") String authorization,
            @Header("Accept") String accept
    );
}
