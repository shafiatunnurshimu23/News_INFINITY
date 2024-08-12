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
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SearchNewsActivity extends AppCompatActivity {
    DatabaseReference ref;
    private ListView listdata;
    private String[] headlines;
   private AutoCompleteTextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_news);
///colored actionbar
        getSupportActionBar().setBackgroundDrawable(
                new ColorDrawable(Color.parseColor("#FF018786")));
        /// back button
        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("Search News");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        ref= FirebaseDatabase.getInstance().getReference("TopNews");
        listdata=findViewById(R.id.List_of_news);
        headlines=getResources().getStringArray(R.array.headline);
        textView=(AutoCompleteTextView) findViewById(R.id.SearchText);

        //from string finding the headlines
        ArrayAdapter adapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,headlines);
        textView.setThreshold(1);
        textView.setAdapter(adapter);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(SearchNewsActivity.this,DetailsActivity.class);
                intent.putExtra("title",textView.getText().toString());
                startActivity(intent);
            }
        });


//end of search


        ValueEventListener event=new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                populateSearch(snapshot);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        ref.addListenerForSingleValueEvent(event);
    }

    private void populateSearch(DataSnapshot snapshot) {
        ArrayList<String> name=new ArrayList<>();
        if(snapshot.exists()){
            for (DataSnapshot ds : snapshot.getChildren()){
                String n=ds.child("name").getValue(String.class);
                name.add(n);
            }

        }else {
            Toast.makeText(SearchNewsActivity.this,"No Data Found!!",Toast.LENGTH_SHORT).show();
        }
    }

    //backButton
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==android.R.id.home)
        {
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }
    //backbutton
}