package com.example.elixi.news.Icon.IconModels;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Shmulik on 04 דצמבר 2017.
 */

public class Icons {

    @SerializedName("sha1sum")
    private String sha1sum;
    @SerializedName("bytes")
    private String bytes;
    @SerializedName("height")
    private String height;
    @SerializedName("error")
    private Object error;
    @SerializedName("width")
    private String width;
    @SerializedName("format")
    private String format;
    @SerializedName("url")
    private String url;

    public String getSha1sum ()
    {
        return sha1sum;
    }

    public void setSha1sum (String sha1sum)
    {
        this.sha1sum = sha1sum;
    }

    public String getBytes ()
    {
        return bytes;
    }

    public void setBytes (String bytes)
    {
        this.bytes = bytes;
    }

    public String getHeight ()
    {
        return height;
    }

    public void setHeight (String height)
    {
        this.height = height;
    }

    public Object getError ()
{
    return error;
}

    public void setError (Object error)
    {
        this.error = error;
    }

    public String getWidth ()
    {
        return width;
    }

    public void setWidth (String width)
    {
        this.width = width;
    }

    public String getFormat ()
    {
        return format;
    }

    public void setFormat (String format)
    {
        this.format = format;
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
        return "ClassPojo [sha1sum = "+sha1sum+", bytes = "+bytes+", height = "+height+", error = "+error+", width = "+width+", format = "+format+", url = "+url+"]";
    }
}


