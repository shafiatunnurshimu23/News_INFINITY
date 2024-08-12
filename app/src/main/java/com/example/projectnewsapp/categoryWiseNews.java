package com.example.projectnewsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class categoryWiseNews extends AppCompatActivity {
    TextView textView;
    RecyclerView recyclerView;
    HotNewsAdapter hotNewsAdapter;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_wise_news);

        //actionbar bg color
        getSupportActionBar().setBackgroundDrawable(
                new ColorDrawable(Color.parseColor("#FF018786")));

        progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Loading category wise News.....");
        recyclerView = findViewById(R.id.categoryNewsRecyclerView);

        String title = getIntent().getStringExtra("title");

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        List<Model2> model2List = new ArrayList<Model2>();
        hotNewsAdapter = new HotNewsAdapter(model2List);
        recyclerView.setAdapter(hotNewsAdapter);

        FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
        progressDialog.show();

        firebaseFirestore.collection("CategoryWiseNews").document(title).get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if(task.isComplete()){
                            progressDialog.cancel();
                            DocumentSnapshot ds = task.getResult();
                            long total = ds.getLong("no_of_news");
                            for (long x =total; x>=1; x--) {
                                model2List.add(new Model2(ds.getString("name_" + x),
                                        ds.getString("image_"+x), ds.getString("by_"+x),
                                        ds.getString("time_"+x)));
                            }
                            hotNewsAdapter.notifyDataSetChanged();
                        }
                    }
                });
/// back button
        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("Category Wise News");
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
    //back button
}