package com.example.elixi.news.Icon.IconServices;

import android.graphics.drawable.Icon;

import com.example.elixi.news.Icon.IconModels.IconSearchResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * Created by Shmulik on 04 דצמבר 2017.
 */

public  interface IconAPIService {
    @GET("/allicons.json")
    Call<IconSearchResult> get(@Query("url") String url);
}
