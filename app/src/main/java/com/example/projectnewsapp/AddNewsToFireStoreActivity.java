package com.example.projectnewsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddNewsToFireStoreActivity extends AppCompatActivity {
    EditText name_,category_,time_,image_,source_,details_;
    Button submitBtn,cancelBtn;
    long number=0;
    String x;

    HotNewsAdapter hotNewsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_news_to_fire_store);
        name_=findViewById(R.id.title);
        category_=findViewById(R.id.category);
        time_=findViewById(R.id.time);
        image_=findViewById(R.id.image);
        source_=findViewById(R.id.source);
        details_=findViewById(R.id.details);
        submitBtn=findViewById(R.id.submit_btn);
        cancelBtn=findViewById(R.id.cancel_btn);
         x=category_.getText().toString();
         cancelBtn.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Intent intent=new Intent(AddNewsToFireStoreActivity.this,AdminPannelActivity.class);
                 startActivity(intent);
             }
         });
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);

        List<Model2> model2List = new ArrayList<Model2>();

        hotNewsAdapter = new HotNewsAdapter(model2List);

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertData();
            }
        });

        //actionbar bg color
        getSupportActionBar().setBackgroundDrawable(
                new ColorDrawable(Color.parseColor("#FF018786")));
        //backbutton
        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("Admin pannel");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

    }

    private void insertData() {
      //  FirebaseFirestore firebaseFirestore=FirebaseFirestore.getInstance();
      //  firebaseFirestore.collection("CategoryWiseNews").document("International").get()
               // .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                   // @Override
                   // public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        //if(task.isComplete()){

                           // DocumentSnapshot ds = task.getResult();
                         //  number = ds.getLong("no_of_news");


                    //    }
                  //  }
               // });
        if((category_.getText().toString()).equals("International")){
            number=3;
        }
        else if((category_.getText().toString()).equals("Bangla News")){
            number=5;
        }
        else if((category_.getText().toString()).equals("Politics")){
            number=4;
        }
        else if((category_.getText().toString()).equals("Sports")){
            number=4;
        }
        else if((category_.getText().toString()).equals("Movie")){
            number=4;
        }
        else if((category_.getText().toString()).equals("Astronomy")){
            number=3;
        }else{
            number=2;
        }




        DocumentReference docRef=FirebaseFirestore.getInstance()
               .collection("CategoryWiseNews")
               .document(category_.getText().toString());
     Map<String,Object> items=new HashMap<>();
     number++;
        items.put("name_"+number,name_.getText().toString());
        items.put("time_"+number,time_.getText().toString());
        items.put("by_"+number,source_.getText().toString());
        items.put("image_"+number,image_.getText().toString());
        items.put("details_"+number,details_.getText().toString());
        items.put("no_of_news",number);

        docRef.update(items).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(AddNewsToFireStoreActivity.this,"News stored Successsfully",Toast.LENGTH_SHORT).show();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

           Log.e("tag","on failure",e);
                Toast.makeText(AddNewsToFireStoreActivity.this,"Cant store News",Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        if(item.getItemId()==android.R.id.home)
        {
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }
}