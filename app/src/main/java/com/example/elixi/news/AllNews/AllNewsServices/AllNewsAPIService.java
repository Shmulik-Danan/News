package com.example.elixi.news.AllNews.AllNewsServices;

import com.example.elixi.news.AllNews.AllNewsModels.AllNewsSearchResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Shmulik on 04 דצמבר 2017.
 */

public interface AllNewsAPIService {
    @GET("{GET}")

    Call<AllNewsSearchResult> get(@Path("GET") String GET, @Query("apiKey")String apiKey);
}
