package com.example.elixi.news.AllNews.AllNewsModels;

import android.graphics.drawable.Icon;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.example.elixi.news.AllNews.AllNewsServices.AllNewsAPIService;
import com.example.elixi.news.Icon.IconModels.IconSearchResult;
import com.example.elixi.news.Icon.IconServices.IconAPIService;
import com.example.elixi.news.News.services.RetrofitClient;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Shmulik on 04 דצמבר 2017.
 */

public class AllNewsSources {

    @SerializedName("id")
    private String id;
    @SerializedName("category")
    private String category;
    @SerializedName("description")
    private String description;
    @SerializedName("name")
    private String name;
    @SerializedName("language")
    private String language;
    @SerializedName("url")
    private String url;
    @SerializedName("country")
    private String country;

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getCategory ()
    {
        return category;
    }

    public void setCategory (String category)
    {
        this.category = category;
    }

    public String getDescription ()
    {
        return description;
    }

    public void setDescription (String description)
    {
        this.description = description;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public String getLanguage ()
    {
        return language;
    }

    public void setLanguage (String language)
    {
        this.language = language;
    }

    public String getUrl ()
    {
        return url;
    }

    public void setUrl (String url)
    {
        this.url = url;
    }

    public String getCountry ()
    {
        return country;
    }

    public void setCountry (String country)
    {
        this.country = country;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [id = "+id+", category = "+category+", description = "+description+", name = "+name+", language = "+language+", url = "+url+", country = "+country+"]";
    }

    public String getImage() {



        return "";
    }

}


