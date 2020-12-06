package com.example.newnews;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

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
        newsViewModel =  new ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(NewsViewModel.class);


        newsViewModel.getAllNews().observe(this, new Observer<List<News>>() {
            @Override
            public void onChanged(List<News> news) {
                Toast.makeText(MainActivity.this,"Go go go", Toast.LENGTH_LONG).show();
            }
        });
}
}