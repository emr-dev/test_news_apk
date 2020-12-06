package com.example.newnews;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;


@Database(entities = {News.class},version = 2)
public abstract class NewsDatabase extends RoomDatabase {

    private static NewsDatabase instance;

    public abstract NewsDao newsDao();

    public static synchronized NewsDatabase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    NewsDatabase.class,"news_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallBack)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallBack = new RoomDatabase.Callback(){

        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };


    private static class PopulateDbAsyncTask extends AsyncTask<Void,Void,Void> {
        private NewsDao newsDao;


        private PopulateDbAsyncTask(NewsDatabase db){
            this.newsDao = db.newsDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {

              newsDao.insert(new News("Название новости","Описание","https://risimore.ru/image/catalog/iiko_biz/c7529a1c-5deb-419e-bd1f-793e3f6f3370.jpg",0));
              newsDao.insert(new News("Название новости 1","Описание","https://risimore.ru/image/catalog/iiko_biz/c7529a1c-5deb-419e-bd1f-793e3f6f3370.jpg",0));
              newsDao.insert(new News("Название новости 2","Описание","https://risimore.ru/image/catalog/iiko_biz/c7529a1c-5deb-419e-bd1f-793e3f6f3370.jpg",0));
              newsDao.insert(new News("Название новости 3","Описание","https://risimore.ru/image/catalog/iiko_biz/c7529a1c-5deb-419e-bd1f-793e3f6f3370.jpg",0));
              newsDao.insert(new News("Название новости 4","Описание","https://risimore.ru/image/catalog/iiko_biz/c7529a1c-5deb-419e-bd1f-793e3f6f3370.jpg",0));
              newsDao.insert(new News("Название новости 5","Описание","https://risimore.ru/image/catalog/iiko_biz/c7529a1c-5deb-419e-bd1f-793e3f6f3370.jpg",0));
              newsDao.insert(new News("Название новости 6","Описание","https://risimore.ru/image/catalog/iiko_biz/c7529a1c-5deb-419e-bd1f-793e3f6f3370.jpg",0));
            return null;
        }
    }


}
