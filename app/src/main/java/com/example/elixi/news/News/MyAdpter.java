package com.example.elixi.news.News;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.elixi.news.News.models.Articles;
import com.example.elixi.news.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Shmulik on 03 דצמבר 2017.
 */

public class MyAdpter extends RecyclerView.Adapter<MyAdpter.ViewHolder>{
    ArrayList<Articles>  articles;
    Context context;

    public MyAdpter(Context context, ArrayList<Articles> articles) {
        this.articles = articles;
        this.context = context;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.custum_row_news,parent,false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        YoYo.with(Techniques.FadeInLeft).playOn(holder.cardView);
        final Articles current=articles.get(position);
        holder.Title.setText(current.getTitle());
        holder.Description.setText(current.getDescription());
        holder.publishedAt.setText(current.getPublishedAt());
        if ((current.getUrlToImage() != null) && current.getUrlToImage().contains("http")) {
            Picasso.with(context).load(current.getUrlToImage()).into(holder.image);
        } else {
            holder.image.setImageResource(R.drawable.news_image);
        }
        holder.cardView.setOnClickListener(new View.OnClickListener() {
             @Override
            public void onClick(View view) {

                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(current.getUrl()));
                context.startActivity(browserIntent);
  /*
                Intent intent=new Intent(context,Web.class);
                intent.putExtra("Link",current.getUrl());
                intent.putExtra("Class",MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                context.startActivity(intent);
*/



            }
        });

    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView Title,Description,publishedAt;
        ImageView image;
        CardView cardView;


        public ViewHolder(View itemView) {
            super(itemView);
            Title= (TextView) itemView.findViewById(R.id.title);
            Description= (TextView) itemView.findViewById(R.id.description);
            publishedAt= (TextView) itemView.findViewById(R.id.publishedAt);
            image= (ImageView) itemView.findViewById(R.id.image);
            cardView= (CardView) itemView.findViewById(R.id.cardview);


        }
    }
}
