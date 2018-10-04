package com.example.omdb.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieResp {

    @SerializedName("Search")
    public List<Movie> movieList;

    @SerializedName("totalResults")
    public String totalResults;

    @SerializedName("Response")
    public String response;

    public boolean success = false;

}
