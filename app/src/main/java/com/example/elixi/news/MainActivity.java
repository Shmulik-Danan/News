package com.example.elixi.news;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.elixi.news.AllNews.AllMyAdpter;
import com.example.elixi.news.AllNews.AllNewsModels.AllNewsSearchResult;
import com.example.elixi.news.AllNews.AllNewsModels.AllNewsSources;
import com.example.elixi.news.AllNews.AllNewsServices.AllNewsAPIService;
import com.example.elixi.news.AllNews.AllNewsServices.AllNewsRefrofitClient;
import com.example.elixi.news.News.MyAdpter;
import com.example.elixi.news.News.models.Articles;
import com.example.elixi.news.News.models.SearchResult;
import com.example.elixi.news.News.services.APIService;
import com.example.elixi.news.News.services.RetrofitClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    public static final String DATABASE_NAME = "ArticlesDb.db";

    TextView title,descritpion;
    ImageView imageView;

    APIService service;
    AllNewsAPIService serviceNews;

    RecyclerView recyclerView;
    RecyclerView recyclerview_icon;
    Context context;
    private RecyclerView.LayoutManager mLayoutManager;
    boolean isLoaded=false;

    AppDatabase db;
    private SwipeRefreshLayout swipeContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        title =(TextView)findViewById(R.id.title);
        descritpion=(TextView)findViewById(R.id.description);
        imageView=(ImageView)findViewById(R.id.image);
        initCollapsingToolbar();

        recyclerView= (RecyclerView) findViewById(R.id.recyclerview);
        recyclerview_icon= (RecyclerView) findViewById(R.id.recyclerview_icon);
        swipeContainer = (SwipeRefreshLayout) findViewById(R.id.swipeContainer);



        BuildAppDatabase();

        service= RetrofitClient.getClient("https://newsapi.org/v2/").create(APIService.class);
        if(db.articlesDao().getall().size()==0){
            getarticles();
        }
        else{
            MyAdpter adpternew= new MyAdpter(MainActivity.this,
                    new ArrayList<Articles>( db.articlesDao().getall()));

            mLayoutManager = new LinearLayoutManager(MainActivity.this);
            recyclerView.setLayoutManager(mLayoutManager);
            recyclerView.setAdapter(adpternew);

        }


        serviceNews= AllNewsRefrofitClient.getClient("https://newsapi.org/v2/").create(AllNewsAPIService.class);
        // getAllNews();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);


        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getarticles();
                swipeContainer.setRefreshing(false);

            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();

    }
    void getAllNews(){
        anim();
        serviceNews.get("sources",getString(R.string.api_key))
                .enqueue(new Callback<AllNewsSearchResult>() {
                    @Override
                    public void onResponse(Call<AllNewsSearchResult> call, Response<AllNewsSearchResult> response) {

                        if(response.isSuccessful()){
                            anim();
                            ArrayList<AllNewsSources> sources=response.body().getSources();
                            AllMyAdpter adpternew= new AllMyAdpter(MainActivity.this,sources);
                            mLayoutManager = new LinearLayoutManager(MainActivity.this,LinearLayoutManager.HORIZONTAL,true);
                            recyclerview_icon.setLayoutManager(mLayoutManager);
                            recyclerview_icon.setAdapter(adpternew);

                        }
                        else{
                            Snackbar.make(title,"API Callwas not good",Snackbar.LENGTH_SHORT);
                        }

                    }

                    @Override
                    public void onFailure(Call<AllNewsSearchResult> call, Throwable t) {

                    }
                });
    }



    void getarticles(){
        anim();

        service.get("top-headlines",getString(R.string.api_key),"google-news-is","")

                .enqueue(new Callback<SearchResult>() {


                    @Override
                    public void onResponse(Call<SearchResult> call, Response<SearchResult> response) {

                        if(response.isSuccessful()){
                            anim();
                            ArrayList<Articles> articles=response.body().getArticles();
                            saveData(articles);

                            MyAdpter adpternew= new MyAdpter(MainActivity.this,
                                    new ArrayList<Articles>( db.articlesDao().getall()));

                            mLayoutManager = new LinearLayoutManager(MainActivity.this);
                            recyclerView.setLayoutManager(mLayoutManager);
                            recyclerView.setAdapter(adpternew);


                        }
                        else{
                            Snackbar.make(title,"API Callwas not good",Snackbar.LENGTH_SHORT);
                        }

                    }

                    @Override
                    public void onFailure(Call<SearchResult> call, Throwable t) {

                    }
                });

    }

    private void initCollapsingToolbar() {
        final CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        // collapsingToolbar.setTitle(" ");
        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.appbar);
        appBarLayout.setExpanded(true);

        // hiding & showing the title when toolbar expanded & collapsed
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                  // collapsingToolbar.setTitle(getString(R.string.app_name));
                    isShow = true;
                } else if (isShow) {
                    collapsingToolbar.setTitle(" ");
                    isShow = false;
                }
            }
        });
    }

    void BuildAppDatabase(){
         db = Room.databaseBuilder(MainActivity.this,
                AppDatabase.class,
                DATABASE_NAME).allowMainThreadQueries()
                .build();
    }


     void saveData(ArrayList<Articles> articlesList) {


        db.articlesDao().deleteAll();
        db.articlesDao().insertAll(articlesList.toArray(new Articles[articlesList.size()]));
    }

    public void anim(){
        if(!isLoaded) {

            isLoaded=true;
        }
        else{

            isLoaded=false;

        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case(R.id.fab):

                break;
        }
    }
}
