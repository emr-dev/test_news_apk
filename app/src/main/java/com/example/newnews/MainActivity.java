package com.example.newnews;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import java.util.List;
import java.util.Observable;

public class MainActivity extends AppCompatActivity {


    private NewsViewModel newsViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.news_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        NewsAdapter newsAdapter = new NewsAdapter();

        recyclerView.setAdapter(newsAdapter);
        newsViewModel =  new ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(NewsViewModel.class);

        newsViewModel.getAllNews().observe(this, new Observer<List<News>>() {
            @Override
            public void onChanged(List<News> news) {
                Toast.makeText(MainActivity.this,       ""+news.size(), Toast.LENGTH_LONG).show();
                newsAdapter.setNews(news);
            }
        });
}
}