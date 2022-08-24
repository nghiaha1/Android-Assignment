package com.example.android_assignment.network;

import com.example.android_assignment.model.Item;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiManager {
    String SERVER = "https://springfilm.herokuapp.com";

    @GET("/apifree/home")
    Call<Item> getListApi();
}
