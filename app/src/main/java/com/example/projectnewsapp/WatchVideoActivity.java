package com.example.projectnewsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

public class WatchVideoActivity extends AppCompatActivity {
    ImageView bbc_news,cnn_news,sky_news,alzazeera_news,fox_news,msnbc_news;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watch_video);

        //actionbar bg color
        getSupportActionBar().setBackgroundDrawable(
                new ColorDrawable(Color.parseColor("#FF018786")));

        bbc_news=findViewById(R.id.BBC_news);
        cnn_news=findViewById(R.id.CNN);
        sky_news=findViewById(R.id.sky_news);
        alzazeera_news=findViewById(R.id.alzazeera_news);
        fox_news=findViewById(R.id.FoxNews);
        msnbc_news=findViewById(R.id.MSNBC_news);
        bbc_news.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bbcMethod();
            }
        });
        cnn_news.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cnnMethod();
            }
        });
        msnbc_news.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                msnbcMethod();
            }
            
        });
        sky_news.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                skyNewsMethod();
            }
        });
        alzazeera_news.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alzazeeraMethod();
            }
        });
        fox_news.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               foxMethod();
            }
        });


        /// back button
        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("TV Channels");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==android.R.id.home)
        {
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }
    //back Buton

    private void foxMethod() {
        Intent intent=new Intent(WatchVideoActivity.this,ChannelWebViewActivity.class);
        intent.putExtra("link","https://www.foxnews.com/shows/fox-news-live");
        startActivity(intent);
    }

    private void alzazeeraMethod() {
        Intent intent=new Intent(WatchVideoActivity.this,ChannelWebViewActivity.class);
        intent.putExtra("link","https://www.aljazeera.com/live");
        startActivity(intent);
    }

    private void skyNewsMethod() {
        Intent intent=new Intent(WatchVideoActivity.this,ChannelWebViewActivity.class);
        intent.putExtra("link","https://news.sky.com/videos");
        startActivity(intent);
    }

    private void cnnMethod()
    {
        Intent intent=new Intent(WatchVideoActivity.this,ChannelWebViewActivity.class);
        intent.putExtra("link","https://edition.cnn.com/videos/");
        startActivity(intent);
    }

    void bbcMethod()
    {
        Intent intent=new Intent(WatchVideoActivity.this,ChannelWebViewActivity.class);
        intent.putExtra("link","https://www.bbc.com/news/av/10462520");
        startActivity(intent);
    }
    private void msnbcMethod() {
        Intent intent=new Intent(WatchVideoActivity.this,ChannelWebViewActivity.class);
        intent.putExtra("link","https://www.msnbc.com/live");
        startActivity(intent);
    }
}