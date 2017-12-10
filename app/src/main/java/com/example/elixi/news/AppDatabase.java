package com.example.elixi.news;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;


import com.example.elixi.news.News.models.Articles;

/**
 * Created by Shmulik on 08 דצמבר 2017.
 */

@Database(entities = {Articles.class}, version = 1)
public abstract  class AppDatabase extends RoomDatabase {
    //private static AppDatabase singleton;
    public static final String DATABASE_NAME = "ArticlesDb.db";

    public abstract articlesDao articlesDao();


    }


