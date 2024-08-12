package com.example.projectnewsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.Toast;

public class SplashScreen extends AppCompatActivity {
    private ProgressBar progressBar;
    private int progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);
        progressBar=findViewById(R.id.progress_bar);
        Toast.makeText(SplashScreen.this,"Welcome to  News INFINITY",Toast.LENGTH_SHORT).show();
        Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {

                doWork();
                statApp();
            }
        });
        thread.start();
    }
    public void doWork() {
        for (progress = 15; progress <= 100; progress = progress + 40) {
            try {
                Thread.sleep(1000);
                progressBar.setProgress(progress);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }
    public void statApp(){
        Intent intent=new Intent(SplashScreen.this,AdminOrUser.class);
        startActivity(intent);
        finish();
    }
}

