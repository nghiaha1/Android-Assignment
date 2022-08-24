package com.example.android_assignment.activity;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android_assignment.R;
import com.example.android_assignment.adapter.MovieAdapter;
import com.example.android_assignment.adapter.SectionAdapter;
import com.example.android_assignment.model.Data;
import com.example.android_assignment.model.Item;
import com.example.android_assignment.model.Movie;
import com.example.android_assignment.model.Section;
import com.example.android_assignment.network.ApiManager;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    CarouselView carouselView;
    int[] sampleImages = {R.drawable.banner1,
            R.drawable.banner2,
            R.drawable.banner3};
    List<Section> listSection;
    SectionAdapter sectionAdapter;
    MovieAdapter movieAdapter;
    List<List<Movie>> listTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initBanner();
        initView();
    }

    private void initView() {
        RecyclerView rvHome = findViewById(R.id.rvHome);

        callApi();

        RecyclerView.LayoutManager layoutManager =
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        sectionAdapter = new SectionAdapter(this, listSection);

        rvHome.setLayoutManager(layoutManager);
        rvHome.setAdapter(sectionAdapter);
    }

    private void callApi() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiManager.SERVER)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiManager service = retrofit.create(ApiManager.class);
        service.getListApi().enqueue(new Callback<Item>() {
            @Override
            public void onResponse(Call<Item> call, Response<Item> response) {
                Item item = response.body();
                Data data = item.getData();

                listTitle.add(data.getTrending());
                listTitle.add(data.getHot());
                listTitle.add(data.getPopular());
                listTitle.add(data.getUpcoming());

                movieAdapter.reloadData(listTitle.get(0));
                movieAdapter.reloadData(listTitle.get(1));
                movieAdapter.reloadData(listTitle.get(2));
                movieAdapter.reloadData(listTitle.get(3));

                listSection.add(new Section("Trending", listTitle.get(0)));
                listSection.add(new Section("Hot", listTitle.get(2)));
                listSection.add(new Section("Popular", listTitle.get(3)));
                listSection.add(new Section("Upcoming", listTitle.get(4)));
            }

            @Override
            public void onFailure(Call<Item> call, Throwable t) {

            }
        });
    }

    private void initBanner() {
        carouselView = findViewById(R.id.carouselView);
        carouselView.setPageColor(sampleImages.length);
        carouselView.setImageListener(new ImageListener() {
            @Override
            public void setImageForPosition(int position, ImageView imageView) {
                imageView.setImageResource(sampleImages[position]);
            }
        });
    }
}