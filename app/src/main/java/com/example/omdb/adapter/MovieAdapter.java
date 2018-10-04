package com.example.omdb.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.omdb.R;
import com.example.omdb.model.Movie;
import com.example.omdb.detailmovie.MovieDetailActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolderAdapter> {
    public Context mContext;
    public List<Movie> movieList;

    public MovieAdapter(Context mContext,List<Movie> movieList) {
        this.mContext = mContext;
        this.movieList = movieList;
    }

    @Override
    public MovieAdapter.ViewHolderAdapter onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, parent, false);
        return new MovieAdapter.ViewHolderAdapter(v);
    }

    @Override
    public void onBindViewHolder(final MovieAdapter.ViewHolderAdapter holder, int position) {

        final Movie movie = movieList.get(position);

        if(movie.poster != null && movie.poster.length() > 0) {
            Glide.with(mContext).load(movie.poster).into(holder.imgMovie);
        }
        holder.tvTitle.setText(movie.title);
        holder.tvYear.setText(movie.year);

        holder.tvDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, MovieDetailActivity.class).putExtra(Intent.EXTRA_TITLE, movie.title);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (movieList !=null){
        }else {
            return 0;
        }
        return movieList.size();
    }

    class ViewHolderAdapter extends RecyclerView.ViewHolder {

        @BindView(R.id.card)
        CardView card;

        @BindView(R.id.img_movie)
        ImageView imgMovie;

        @BindView(R.id.tv_title)
        TextView tvTitle;

        @BindView(R.id.tv_year)
        TextView tvYear;

        @BindView(R.id.tv_detail)
        TextView tvDetail;

        public ViewHolderAdapter(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
