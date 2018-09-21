package com.example.omdb.views;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.example.omdb.adapter.MovieAdapter;
import com.example.omdb.controller.MovieController;
import com.example.omdb.object.MovieResp;
import com.example.omdb.R;
import com.example.omdb.object.Movie;
import com.example.omdb.services.ApiClient;
import com.example.omdb.services.ApiInterface;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieActivity extends AppCompatActivity {

    @BindView(R.id.list_movie)
    RecyclerView listMovie;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @BindView(R.id.search_view)
    MaterialSearchView searchView;


    private ApiInterface mInterfaceService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        ButterKnife.bind(this);

        mInterfaceService = ApiClient.getClient().create(ApiInterface.class);

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


    }


    private void searchClicked(){
        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                new SearchMovie(query).execute();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });


    }


    public class SearchMovie extends AsyncTask<Void, Void, MovieResp>{

        private MovieController controller;
        private String imdbId;
        private MovieResp movieResp;


        public SearchMovie (String imdbId){
            this.imdbId = imdbId;
        }


        @Override
        protected void onPreExecute(){
            controller = new MovieController((MovieActivity.this));
        }


        @Override
        protected MovieResp doInBackground(Void... voids) {
            movieResp = controller.getMovie(imdbId);
            return movieResp;
        }
        @Override
        protected void onPostExecute(MovieResp movieResp) {

            if(movieResp.success){
//                Toast.makeText(MovieActivity.this, movieResp.message, Toast.LENGTH_SHORT).show();
                searchView.setVisibility(View.VISIBLE);
                searchView.setVisibility(View.GONE);



                initListMovie(movieResp.movieList);

            } else {
//                Toast.makeText(MovieActivity.this, movieResp.message, Toast.LENGTH_SHORT).show();
            }

        }

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
}
