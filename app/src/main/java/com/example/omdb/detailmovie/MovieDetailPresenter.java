package com.example.omdb.detailmovie;

import com.example.omdb.model.Movie;
import com.example.omdb.model.MovieResp;
import com.example.omdb.model.ApiClient;
import com.example.omdb.model.ApiInterface;

import java.lang.annotation.Annotation;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Converter;
import retrofit2.Response;

public class MovieDetailPresenter {
    private MovieDetailView movieDetailView;
    private ApiInterface mInterfaceService;

    public MovieDetailPresenter(MovieDetailView movieDetailView){
        this.movieDetailView = movieDetailView;
        mInterfaceService = ApiClient.getClient().create(ApiInterface.class);

    }

    public void getDetail (String title){
        Movie movie= new Movie();
        Call<Movie> call = mInterfaceService.getDetail("c0c1952d", title);
        try {
            Response<Movie> resp = call.execute();

            if (resp.isSuccessful()){
                movie = resp.body();
                movie.success = true;
            }else{
                if (resp.errorBody() != null) {
                    Converter<ResponseBody, Movie> errorConverter = ApiClient.getClient()
                            .responseBodyConverter(MovieResp.class, new Annotation[0]);
                    movie= errorConverter.convert(resp.errorBody());
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            movie= null;
        }

        movieDetailView.showDetailMovie(movie);
    }
}
