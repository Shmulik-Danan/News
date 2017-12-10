package com.example.elixi.news.AllNews;

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
import com.example.elixi.news.AllNews.AllNewsModels.AllNewsSources;
import com.example.elixi.news.Icon.IconModels.IconSearchResult;
import com.example.elixi.news.Icon.IconServices.IconAPIService;
import com.example.elixi.news.Icon.IconServices.IconRetrofitClient;
import com.example.elixi.news.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Shmulik on 04 דצמבר 2017.
 */

public class AllMyAdpter extends RecyclerView.Adapter<AllMyAdpter.ViewHolder>{
    ArrayList<AllNewsSources> allSources;
    Context context;
    IconAPIService service;
    String ImageUrl;
    public AllMyAdpter(Context context, ArrayList<AllNewsSources> allSources) {
        this.allSources = allSources;
        this.context = context;
         service= IconRetrofitClient.getClient("https://icons.better-idea.org/")
                .create(IconAPIService.class);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.news,parent,false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    public void onBindViewHolder( ViewHolder holder, int position) {

        YoYo.with(Techniques.FadeInLeft).playOn(holder.cardView);
        final AllNewsSources current=allSources.get(position);

       // Toast.makeText(context, "v== "+current.getUrl(), Toast.LENGTH_SHORT).show();
       /// holder.textView.setText( "v= "+get(current.getUrl()));
        Picasso.with(context).load(get(current.getUrl())).into(holder.image);



        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(current.getUrl()));
                context.startActivity(browserIntent);


            }
        });

    }
    String get(String url){
        service.get(url)
                .enqueue(new Callback<IconSearchResult>() {

                    @Override
                    public void onResponse(Call<IconSearchResult> call, Response<IconSearchResult> response) {

                        if(response.isSuccessful()){

                            if(response.body().getIcons().size()>0) {
                                ImageUrl = response.body().getIcons().get(0).getUrl();
                            }
                        }
                        else{
                            ImageUrl="https://www.google.co.il/search?q=no+photo&rlz=1C1CHZL_iwIL719IL719&tbm=isch&source=iu&ictx=1&fir=9e9JeDQI0DBZrM%253A%252CfdpOxr8npfcozM%252C_&usg=__jNiF8K-o7waY6_1jFIstrV_7ktc%3D&sa=X&ved=0ahUKEwiUr7WR2PLXAhXDDsAKHXiRA4sQ9QEIKjAB#imgrc=9e9JeDQI0DBZrM";
                        }
                    }
                    @Override
                    public void onFailure(Call<IconSearchResult> call, Throwable t) {
                    }
                });
        return ImageUrl;
    }
    @Override
    public int getItemCount() {
        return allSources.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView textView;
        ImageView image;

        public ViewHolder(View itemView) {
            super(itemView);
            image= (ImageView) itemView.findViewById(R.id.image);
            cardView= (CardView) itemView.findViewById(R.id.cardview);
            textView= (TextView) itemView.findViewById(R.id.textView);


        }
    }
}
