package com.example.newnews;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsHolder> {

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

        public NewsHolder(View itemView){
            super(itemView);
            title = itemView.findViewById(R.id.title);
            description = itemView.findViewById(R.id.description);
            date = itemView.findViewById(R.id.date);
        }
    }
}
