package com.example.newnews;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "news_table")
public class News {


    @PrimaryKey(autoGenerate = true)
    private int id;

    private String name;

    private String description;

    private String image;

    private String date;

    private int sort;


    public News(String name, String description, String image,  String date,  int sort) {
        this.name = name;
        this.description = description;
        this.image = image;
        this.date = date;
        this.sort = sort;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }

    public int getSort() {
        return sort;
    }

    public String getDate() {
        return date;
    }
}
