package com.example.omdb.listmovie;

import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.example.omdb.adapter.MovieAdapter;
import com.example.omdb.R;
import com.example.omdb.model.Movie;
import com.example.omdb.model.ApiInterface;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieActivity extends AppCompatActivity implements MovieView {

    @BindView(R.id.list_movie)
    RecyclerView listMovie;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @BindView(R.id.search_view)
    MaterialSearchView searchView;


    private ApiInterface mInterfaceService;
    private MoviePresenter moviePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        ButterKnife.bind(this);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        moviePresenter = new MoviePresenter(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("search movie");
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));

        searchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() {

            }

            @Override
            public void onSearchViewClosed() {

            }
        });

        searchClicked();
        this.moviePresenter = new MoviePresenter(MovieActivity.this);
    }


    private void searchClicked(){
        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                System.out.println("Query"+ query);
                moviePresenter.getMovie(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_item,menu);
        MenuItem item = menu.findItem(R.id.action_search);
        searchView.setMenuItem(item);
        return true;
    }

    public void initListMovie(List<Movie> movieList){
        MovieAdapter movieAdapter = new MovieAdapter(this, movieList);
        GridLayoutManager layoutManager = new GridLayoutManager(this,1,GridLayoutManager.VERTICAL,false);
        listMovie.setLayoutManager(layoutManager);
        listMovie.setAdapter(movieAdapter);
        movieAdapter.notifyDataSetChanged();
    }

    @Override
    public void showMovie(List<Movie> listMovie) {
        searchView.setVisibility(View.VISIBLE);
        searchView.setVisibility(View.GONE);
        initListMovie(listMovie);
    }
}
