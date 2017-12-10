package com.example.elixi.news.News.services;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by elixi on 29 נובמבר 2017.
 */

public class RetrofitClient {
    public static Retrofit getClient(String baseUrl){
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }
}
