package com.example.newnews;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


@Database(entities = {News.class},version = 4)
public abstract class NewsDatabase extends RoomDatabase {

    private static NewsDatabase instance;

    public abstract NewsDao newsDao();

    public static synchronized NewsDatabase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    NewsDatabase.class,"nexws_database1")
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
              newsDao = db.newsDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {

//              newsDao.insert(new News("Название новости","Описание","https://risimore.ru/image/catalog/iiko_biz/c7529a1c-5deb-419e-bd1f-793e3f6f3370.jpg","12.11.15",0));
//              newsDao.insert(new News("Название новости 1","Описание","https://risimore.ru/image/catalog/iiko_biz/c7529a1c-5deb-419e-bd1f-793e3f6f3370.jpg","12.11.15",0));
//              newsDao.insert(new News("Название новости 2","Описание","https://risimore.ru/image/catalog/iiko_biz/c7529a1c-5deb-419e-bd1f-793e3f6f3370.jpg","12.11.15",0));
//              newsDao.insert(new News("Название новости 3","Описание","https://risimore.ru/image/catalog/iiko_biz/c7529a1c-5deb-419e-bd1f-793e3f6f3370.jpg","12.11.15",0));
//              newsDao.insert(new News("Название новости 4","Описание","https://risimore.ru/image/catalog/iiko_biz/c7529a1c-5deb-419e-bd1f-793e3f6f3370.jpg","12.11.15",0));
//              newsDao.insert(new News("Название новости 5","Описание","https://risimore.ru/image/catalog/iiko_biz/c7529a1c-5deb-419e-bd1f-793e3f6f3370.jpg","12.11.15",0));
//              newsDao.insert(new News("Название новости 6","Описание","https://risimore.ru/image/catalog/iiko_biz/c7529a1c-5deb-419e-bd1f-793e3f6f3370.jpg","12.11.15",0));
//              newsDao.insert(new News("Название новости 6","Описание","https://risimore.ru/image/catalog/iiko_biz/c7529a1c-5deb-419e-bd1f-793e3f6f3370.jpg","12.11.15",0));

            OkHttpClient client = new OkHttpClient().newBuilder()
                    .build();
            Request request = new Request.Builder()
                    .url("http://api-news.emrdev.ru/api/article")
                    .method("GET", null)
                    .addHeader("X-AUTH-TOKEN", "PZxmIMMtOsLw1xFGzsN3hiLOOa/wx37cA264gpN27fRaiCgCrRwWMGLNyXyTwKzFjwYPJ8+epX4HIEurqDkAbQ==")
                    .addHeader("Cookie", "PHPSESSID=3eb921dl7sava0tav3529k9pu4")
                    .build();
            try {
                Response response = client.newCall(request).execute();
                JSONObject myjson = null;
                try {
                    myjson = new JSONObject(response.body().string());
                    JSONArray arr = myjson.getJSONArray("data");
                    for (int i = 0; i < arr.length(); i++) {
                        newsDao.insert(new News(
                                arr.getJSONObject(i).getString("title"),
                                arr.getJSONObject(i).getString("description"),
                                arr.getJSONObject(i).getString("image"),
                                arr.getJSONObject(i).getString("date"),0));

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }


            return null;
        }
    }


}
