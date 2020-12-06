package com.example.newnews;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class NewsViewModel extends AndroidViewModel {

    private NewsRepository repository;
    private LiveData<List<News>> allNews;



    public NewsViewModel(@NonNull Application application) {
        super(application);

        repository = new NewsRepository(application);
        allNews = repository.getAllNews();

    }

    public void insert(News news){
        repository.insert(news);
    }

    public void update(News news){
        repository.update(news);
    }

    public void delete(News news){
        repository.delete(news);
    }

    public void deleteAllNews(News news){
        repository.deleteAllNews();
    }

    public LiveData<List<News>> getAllNews(){
        return allNews;
    }
}
