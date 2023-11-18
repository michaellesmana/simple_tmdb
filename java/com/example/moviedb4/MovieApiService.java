package com.example.moviedb4;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieApiService {

    @Headers({
            "Accept: application/json"
    })


    @GET("movie/popular")
    Call<MovieApiResponse> getPopularMovies(
            @Query("language") String language,
            @Query("page") int page,
            @Header("Authorization") String authorization,
            @Header("Accept") String accept
    );

    @GET("movie/now_playing")
    Call<MovieApiResponse> getNowPlayingMovies(
            @Query("language") String language,
            @Query("page") int page,
            @Header("Authorization") String authorization,
            @Header("Accept") String accept
    );

    @GET("movie/upcoming")
    Call<MovieApiResponse> getUpcomingMovies(
            @Query("language") String language,
            @Query("page") int page,
            @Header("Authorization") String authorization,
            @Header("Accept") String accept
    );

    @GET("tv/popular")
    Call<MovieApiResponse> getPopularTVs(
            @Query("language") String language,
            @Query("page") int page,
            @Header("Authorization") String authorization,
            @Header("Accept") String accept
    );

    @GET("person/popular")
    Call<MovieApiResponse> getPopularPerson(
            @Query("language") String language,
            @Query("page") int page,
            @Header("Authorization") String authorization,
            @Header("Accept") String accept
    );

    // Add other methods if needed
}


