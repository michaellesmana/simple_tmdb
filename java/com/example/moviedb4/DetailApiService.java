package com.example.moviedb4;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface DetailApiService {

    @GET("movie/{movie_id}")
    Call<MovieDetail> getMovieDetail(
            @Path("movie_id") long movieId,
            @Query("language") String language,
            @Header("Authorization") String authorization,
            @Header("Accept") String accept
    );

    @GET("tv/{series_id}")
    Call<TVsDetail> getTvDetail(
            @Path("series_id") long seriesId,
            @Query("language") String language,
            @Header("Authorization") String authorization,
            @Header("Accept") String accept
    );

    @GET("person/{person_id}")
    Call<PersonDetail> getPersonDetail(
            @Path("person_id") long personId,
            @Query("language") String language,
            @Header("Authorization") String authorization,
            @Header("Accept") String accept
    );
}
