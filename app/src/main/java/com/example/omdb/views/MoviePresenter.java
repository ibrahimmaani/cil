package com.example.omdb.views;

import com.example.omdb.object.MovieResp;
import com.example.omdb.services.ApiClient;
import com.example.omdb.services.ApiInterface;

import java.lang.annotation.Annotation;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Converter;
import retrofit2.Response;

public class MoviePresenter {
    private MovieView movieView;
    private ApiInterface mInterfaceService;

    public MoviePresenter(MovieView movieView) {
        this.movieView = movieView;
        mInterfaceService = ApiClient.getClient().create(ApiInterface.class);
    }

    public void getMovie(String title) {
        System.out.println(title);
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

        System.out.println(movieResp);

        movieView.showMovie(movieResp.movieList);
    }
}
