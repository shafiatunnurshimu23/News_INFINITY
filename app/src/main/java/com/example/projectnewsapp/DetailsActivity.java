package com.example.projectnewsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class DetailsActivity extends AppCompatActivity {
    TextView detailsTitle, detailsPublishedBy, detailsPublishTime, detailsDescription;
    ImageView detailsImage;
    String description, image, by, time;
    int flag = 0;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        //actionbar bg color
        getSupportActionBar().setBackgroundDrawable(
                new ColorDrawable(Color.parseColor("#FF018786")));

        detailsTitle = findViewById(R.id.detailsTitle);
        detailsPublishedBy = findViewById(R.id.detailsPublishedBy);
        detailsPublishTime = findViewById(R.id.detailsPublishTime);
        detailsDescription = findViewById(R.id.detailsDescription);
        detailsImage = findViewById(R.id.detailsImage);
        progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Loading details....");

        String title = getIntent().getStringExtra("title");

        FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
        progressDialog.show();

        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseFirestore.collection("CategoryWiseNews").orderBy("index").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()) {
                            progressDialog.cancel();
                            for(QueryDocumentSnapshot documentSnapshot : task.getResult()) {
                                long noOfNews = documentSnapshot.getLong("no_of_news");
                                for(long i = 1; i<=noOfNews; i++) {
                                    String getTitle = documentSnapshot.getString("name_" + i);
                                    if(getTitle.equals(title)) {
                                        description = documentSnapshot.getString("details_"+i);
                                        image = documentSnapshot.getString("image_"+i);
                                        by = documentSnapshot.getString("by_"+i);
                                        time = documentSnapshot.getString("time_"+i);
                                        System.out.println("-------------" + by);

                                        Glide.with(DetailsActivity.this).load(image).apply(new RequestOptions()).placeholder(R.drawable.news).into(detailsImage);
                                        detailsTitle.setText(title);
                                        detailsPublishedBy.setText(by);
                                        detailsPublishTime.setText(time);
                                        detailsDescription.setText(description);
                                        flag = 1;
                                        break;
                                    }
                                }
                                if(flag==1) {
                                    break;
                                }
                            }
                        }
                        else {
                            progressDialog.cancel();
                            String error = task.getException().getMessage();
                            Toast.makeText(DetailsActivity.this, error, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        /// back button
        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("Details News");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);


    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==android.R.id.home)
        {
            this.finish();
        }
        else if(item.getItemId()==R.id.share_icon_actionbar)
        {
            Intent intent=new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_SUBJECT,"abcd");
            intent.putExtra(Intent.EXTRA_TEXT,""+detailsTitle.getText().toString()+detailsDescription.getText().toString());
            startActivity(Intent.createChooser(intent,"share Via"));
        }

        return super.onOptionsItemSelected(item);
    }
    //back button

    //share menu

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.share_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }


}