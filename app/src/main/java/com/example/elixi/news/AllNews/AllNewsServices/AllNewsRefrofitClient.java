package com.example.elixi.news.AllNews.AllNewsServices;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Shmulik on 04 דצמבר 2017.
 */

public class AllNewsRefrofitClient {
    public static Retrofit getClient(String baseUrl){
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }
}
