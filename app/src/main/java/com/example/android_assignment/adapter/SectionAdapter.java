package com.example.android_assignment.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android_assignment.R;
import com.example.android_assignment.model.Section;

import java.util.List;

public class SectionAdapter extends RecyclerView.Adapter {
    private Activity activity;
    private List<Section> sectionList;
    MovieAdapter movieAdapter;

    public SectionAdapter(Activity activity, List<Section> sectionList) {
        this.activity = activity;
        this.sectionList = sectionList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = activity.getLayoutInflater().inflate(R.layout.item_section, parent, false);
        SectionViewHolder holder = new SectionViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        SectionViewHolder viewHolder = (SectionViewHolder) holder;
        Section model = sectionList.get(position);

        RecyclerView.LayoutManager layoutManager =
                new LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false);

        MovieAdapter adapter = new MovieAdapter(activity, model.getMovieList());
        adapter.setSection(model.getTitle());

        viewHolder.rvSection.setLayoutManager(layoutManager);
        viewHolder.rvSection.setAdapter(adapter);
        viewHolder.rvSection.setHasFixedSize(true);

        viewHolder.tvTitle.setText(model.getTitle());
    }

    @Override
    public int getItemCount() {
        return sectionList.size();
    }

    public class SectionViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle;
        RecyclerView rvSection;

        public SectionViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            rvSection = itemView.findViewById(R.id.rvSection);
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    public void reloadSection(List<Section> list){
        sectionList = list;
        notifyDataSetChanged();
    }
}
