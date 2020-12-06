package com.example.newnews;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsHolder>{

    private List<News>  news = new ArrayList<>();

    @NonNull
    @Override
    public NewsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news,parent,false);
        return new NewsHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsHolder holder, int position) {
        News current_news = news.get(position);
        holder.title.setText(current_news.getName());
        holder.description.setText(current_news.getDescription());
        holder.date.setText(current_news.getDate());


        Glide.with(holder.image.getContext())
                .load(current_news.getImage())
                .into(holder.image);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), DetalActivity.class );
                intent.putExtra("EXTRA_TITLE", current_news.getName() );
                intent.putExtra("EXTRA_DESCRIPTION", current_news.getDescription() );
                intent.putExtra("EXTRA_DATE", current_news.getDate() );
                intent.putExtra("EXTRA_IMAGE", current_news.getImage() );
                view.getContext().startActivity(intent);
            }
        });

    }


    public void setNews(List<News> news){
        this.news = news;
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return news.size();
    }



    class NewsHolder extends RecyclerView.ViewHolder{
        private TextView title;
        private TextView description;
        private TextView date;
        private ImageView image;
        public NewsHolder(View itemView){
            super(itemView);
            title = itemView.findViewById(R.id.title);
            description = itemView.findViewById(R.id.description);
            image = itemView.findViewById(R.id.image);
            date = itemView.findViewById(R.id.date);
        }
    }
}
