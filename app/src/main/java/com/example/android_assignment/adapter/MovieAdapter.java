package com.example.android_assignment.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.android_assignment.R;
import com.example.android_assignment.model.Movie;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter {
    private Activity activity;
    private List<Movie> movieList;
    private String section;

    public MovieAdapter(Activity activity, List<Movie> movieList) {
        this.activity = activity;
        this.movieList = movieList;
    }


    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = activity.getLayoutInflater().inflate(R.layout.item_movie, parent, false);
        if (section.equalsIgnoreCase("Hot") || section.equalsIgnoreCase("Upcoming")) {
            view = activity.getLayoutInflater().inflate(R.layout.item_movie_big_size, parent,false);
        }
        MovieViewHolder holder = new MovieViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MovieViewHolder viewHolder = (MovieViewHolder) holder;
        Movie model = movieList.get(position);
        viewHolder.tvName.setText(model.getName());
        Glide.with(activity).load(model.getThumbnail()).into(viewHolder.ivThumbnail);
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;
        ImageView ivThumbnail;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            ivThumbnail = itemView.findViewById(R.id.ivThumbnail);

        }
    }

    @SuppressLint("NotifyDataSetChanged")
    public void reloadData(List<Movie> list){
        movieList = list;
        notifyDataSetChanged();
    }


}
