package com.example.elixi.news.News.services;


import com.example.elixi.news.News.models.SearchResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by elixi on 29 נובמבר 2017.
 */

public interface APIService {
  //@GET("https://newsapi.org/v2/top-headlines?sources=google-news-is&apiKey=d6c8fed938d34d42860ff2376b4dcdbe")


     @GET("{GET}")
    Call<SearchResult> get(@Path("GET") String GET,@Query("apiKey")String apiKey, @Query("sources")String sources,
                           @Query("language")String language);
}
