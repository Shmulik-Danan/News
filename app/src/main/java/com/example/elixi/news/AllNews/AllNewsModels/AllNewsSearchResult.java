package com.example.elixi.news.AllNews.AllNewsModels;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Shmulik on 04 דצמבר 2017.
 */

public class AllNewsSearchResult {
    @SerializedName("status")
    private String status;
    @SerializedName("sources")
    private ArrayList<AllNewsSources> sources;

    public String getStatus ()
    {
        return status;
    }

    public void setStatus (String status)
    {
        this.status = status;
    }

    public ArrayList<AllNewsSources> getSources ()
    {
        return sources;
    }

    public void setSources (ArrayList<AllNewsSources> sources)
    {
        this.sources = sources;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [status = "+status+", sources = "+sources+"]";
    }
}

