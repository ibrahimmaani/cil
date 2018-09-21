package com.example.omdb.controller;

import android.app.Activity;

import com.example.omdb.object.Movie;
import com.example.omdb.object.MovieResp;
import com.example.omdb.services.ApiClient;
import com.example.omdb.services.ApiInterface;

import java.lang.annotation.Annotation;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Converter;
import retrofit2.Response;

public class MovieController {

    private Activity activity;
    private ApiInterface mInterfaceService;

    public MovieController(Activity activity){
        this.activity = activity;
        mInterfaceService = ApiClient.getClient().create(ApiInterface.class);
    }

    public MovieResp getMovie(String title){
        MovieResp movieResp = new MovieResp();
        Call<MovieResp> call = mInterfaceService.getMovie("c0c1952d", title);
        try {
            Response<MovieResp> resp = call.execute();

            if (resp.isSuccessful()){
                movieResp= resp.body();
                movieResp.success = true;
            }else{
                if (resp.errorBody() != null) {
                    Converter<ResponseBody, MovieResp> errorConverter = ApiClient.getClient().responseBodyConverter(MovieResp.class, new Annotation[0]);
                    movieResp= errorConverter.convert(resp.errorBody());
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            movieResp = null;
        }

        return movieResp;
    }

    public Movie getDetail (String title){
        Movie movie= new Movie();
        Call<Movie> call = mInterfaceService.getDetail("c0c1952d", title);
        try {
            Response<Movie> resp = call.execute();

            if (resp.isSuccessful()){
                movie = resp.body();
                movie.success = true;
            }else{
                if (resp.errorBody() != null) {
                    Converter<ResponseBody, Movie> errorConverter = ApiClient.getClient().responseBodyConverter(MovieResp.class, new Annotation[0]);
                    movie= errorConverter.convert(resp.errorBody());
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            movie= null;
        }

        return movie;
    }
    }

