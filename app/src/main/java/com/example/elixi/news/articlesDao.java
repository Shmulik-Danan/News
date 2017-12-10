package com.example.elixi.news;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;


import com.example.elixi.news.News.models.Articles;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shmulik on 07 דצמבר 2017.
 */
@Dao
public interface articlesDao {

    @Query("SELECT * FROM articles")
    List<Articles> getall();

    @Insert
    void insertAll(Articles... articles);

    @Query("DELETE FROM articles")
    void deleteAll();

}
