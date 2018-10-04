package com.example.omdb.detailmovie;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.omdb.R;
import com.example.omdb.model.Movie;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieDetailActivity extends AppCompatActivity implements MovieDetailView {

    @BindView(R.id.btn_back)
    ImageView btnBack;

    @BindView(R.id.scroll)
    NestedScrollView scroll;

    @BindView(R.id.img_movie)
    ImageView imgMovie;

    @BindView(R.id.tv_title)
    TextView tvTitle;

    @BindView(R.id.tv_genre)
    TextView tvGenre;

    @BindView(R.id.tv_duration)
    TextView tvDuration;

    @BindView(R.id.tv_year)
    TextView tvYear;

    @BindView(R.id.tv_rating)
    TextView tvRating;

    @BindView(R.id.tv_description)
    TextView tvDescription;

    private MovieDetailPresenter movieDetailPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        ButterKnife.bind(this);
        String title;
        title = getIntent().getStringExtra(Intent.EXTRA_TITLE);
        tvTitle.setText(title);
        movieDetailPresenter = new MovieDetailPresenter(MovieDetailActivity.this);
        movieDetailPresenter.getDetail(title);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
                finish();
            }
        });
    }

    @Override
    public void showDetailMovie(Movie movie) {
        Glide.with(MovieDetailActivity.this)
                .load(movie.poster)
                .into(imgMovie);

        tvYear.setText(movie.year);
        tvGenre.setText(movie.genre);
        tvDuration.setText(movie.runtime);
        tvRating.setText(movie.imdbRating);
        tvDescription.setText(movie.plot);
    }
}
