package com.example.elixi.news.Icon.IconModels;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Shmulik on 04 דצמבר 2017.
 */

public class IconSearchResult {

    @SerializedName("icons")
    private ArrayList<Icons>icons;
    @SerializedName("url")
    private String url;

    public ArrayList<Icons> getIcons ()
    {
        return icons;
    }

    public void setIcons (ArrayList<Icons> icons)
    {
        this.icons = icons;
    }

    public String getUrl ()
    {
        return url;
    }

    public void setUrl (String url)
    {
        this.url = url;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [icons = "+icons+", url = "+url+"]";
    }
}

