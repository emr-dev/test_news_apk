package com.example.newnews;

import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class DetalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detal);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        CollapsingToolbarLayout toolBarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);

        Bundle extras = getIntent().getExtras();

        String title = extras.getString("EXTRA_TITLE");

        toolBarLayout.setTitle(title);
        toolbar.setTitle(title);
        setTitle(title);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "На доработке", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        String description = extras.getString("EXTRA_DESCRIPTION");
        String data = extras.getString("EXTRA_DATE");
        String image = extras.getString("EXTRA_IMAGE");

        TextView content = findViewById(R.id.content);
        ImageView detal_image = findViewById(R.id.detal_image);

        content.setText(description);


        Glide.with(this)
                .load(image)
                .into(detal_image);

        Toast.makeText(this, "" + image, Toast.LENGTH_LONG).show();


    }
}