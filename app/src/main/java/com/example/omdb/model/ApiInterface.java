package com.example.omdb.services;

import com.example.omdb.object.MovieResp;
import com.example.omdb.object.Movie;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("?")
    Call<MovieResp> getMovie (@Query("apikey") String apiKey,
                              @Query("s") String title);

    @GET("?")
    Call<Movie> getDetail (@Query("apikey") String apiKey,
                           @Query("t") String title);




}
