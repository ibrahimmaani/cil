package com.example.omdb;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolderAdapter> {
    public Context mContext;

    public MovieAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public MovieAdapter.ViewHolderAdapter onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, parent, false);
        return new MovieAdapter.ViewHolderAdapter(v);
    }

    @Override
    public void onBindViewHolder(final MovieAdapter.ViewHolderAdapter holder, int position) {

        holder.tvDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, MovieDetailActivity.class);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    class ViewHolderAdapter extends RecyclerView.ViewHolder {

        @BindView(R.id.card)
        CardView card;

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

        @BindView(R.id.tv_detail)
        TextView tvDetail;

        public ViewHolderAdapter(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
