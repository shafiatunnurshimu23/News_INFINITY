package com.example.projectnewsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class CountryChoiceActivity extends AppCompatActivity {
    RecyclerView asia_recycler, africa_recyclerView,europe_recyclerView,north_recyclerView,south_recyclerView,australia_recyclerView;
    private ArrayList<String> catNames3 = new ArrayList<>();
    private ArrayList<String> catImageUrl = new ArrayList<>();

    private ArrayList<String> catNames2 = new ArrayList<>();
    private ArrayList<String> catImageUrl2 = new ArrayList<>();

    private ArrayList<String> catNames = new ArrayList<>();
    private ArrayList<String> catImageUrl3 = new ArrayList<>();

    private ArrayList<String> catNames4 = new ArrayList<>();
    private ArrayList<String> catImageUrl4 = new ArrayList<>();

    private ArrayList<String> catNames5 = new ArrayList<>();
    private ArrayList<String> catImageUrl5 = new ArrayList<>();

    private ArrayList<String> catNames6 = new ArrayList<>();
    private ArrayList<String> catImageUrl6 = new ArrayList<>();
    ProgressDialog progressDialog;

    private AutoCompleteTextView searchText;
    private String[] countries;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_choice);
        asia_recycler = findViewById(R.id.Asia_recyclerView);
        africa_recyclerView = findViewById(R.id.Africa_recyclerView);
        europe_recyclerView = findViewById(R.id.Europe_recyclerView);
        north_recyclerView = findViewById(R.id.NorthAmerica_recyclerView);
        south_recyclerView = findViewById(R.id.SouthAmerica_recyclerView);
        australia_recyclerView = findViewById(R.id.Australia_recyclerView);
        progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Loading country names.....");
        progressDialog.show();

        //search country
        searchText=findViewById(R.id.Search_country_name);
        countries=getResources().getStringArray(R.array.country_name);
        ArrayAdapter adapter2=new ArrayAdapter(this,android.R.layout.simple_list_item_1,countries);
        searchText.setThreshold(1);
        searchText.setAdapter(adapter2);
        searchText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(CountryChoiceActivity.this,CountryWiseNews.class);
                intent.putExtra("title",searchText.getText().toString());
                startActivity(intent);
            }
        });


        //actionbar bg color
        getSupportActionBar().setBackgroundDrawable(
                new ColorDrawable(Color.parseColor("#FF018786")));

        //asia
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        asia_recycler.setLayoutManager(linearLayoutManager);
        countryOptionAdapter adapter = new countryOptionAdapter(this, catNames3, catImageUrl);
        asia_recycler.setAdapter(adapter);

        FirebaseFirestore firebaseFirestore;
        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseFirestore.collection("Asia").orderBy("index").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        catNames3.clear();
                        catImageUrl.clear();
                        if (task.isSuccessful()) {
                            progressDialog.cancel();
                            for (QueryDocumentSnapshot documentSnapshot : task.getResult()) {
                                String icon = documentSnapshot.getString("icon");
                                String categoryName = documentSnapshot.getString("categoryName");
                                catNames3.add(categoryName);
                                catImageUrl.add(icon);
                            }
                            adapter.notifyDataSetChanged();
                        } else {
                            String error = task.getException().getMessage();
                            Toast.makeText(CountryChoiceActivity.this, error, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        //asia
        africamethod();
       Europemethod();
      NorthMethod();
        SouthAmericamethod();
        AustraliaMethod();

        /// back button
        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("Country Selection Activity");
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

    //Europe
    private void Europemethod() {
        LinearLayoutManager linearLayoutManager3 = new LinearLayoutManager(this);
        linearLayoutManager3.setOrientation(LinearLayoutManager.HORIZONTAL);
        europe_recyclerView.setLayoutManager(linearLayoutManager3);
        countryOptionAdapter adapter = new countryOptionAdapter(this, catNames, catImageUrl3);
        europe_recyclerView.setAdapter(adapter);

        FirebaseFirestore firebaseFirestore;
        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseFirestore.collection("Europe").orderBy("index").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        catNames.clear();
                        catImageUrl3.clear();
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot documentSnapshot : task.getResult()) {
                                String icon = documentSnapshot.getString("icon");
                                String categoryName = documentSnapshot.getString("categoryName");
                                catNames.add(categoryName);
                                catImageUrl3.add(icon);
                            }
                            adapter.notifyDataSetChanged();
                        } else {
                            String error = task.getException().getMessage();
                            Toast.makeText(CountryChoiceActivity.this, error, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
    //north america
    private void NorthMethod() {
        LinearLayoutManager linearLayoutManager4 = new LinearLayoutManager(this);
        linearLayoutManager4.setOrientation(LinearLayoutManager.HORIZONTAL);
        north_recyclerView.setLayoutManager(linearLayoutManager4);
        countryOptionAdapter adapter = new countryOptionAdapter(this, catNames4, catImageUrl4);
        north_recyclerView.setAdapter(adapter);

        FirebaseFirestore firebaseFirestore;
        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseFirestore.collection("North America").orderBy("index").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        catNames4.clear();
                        catImageUrl4.clear();
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot documentSnapshot : task.getResult()) {
                                String icon = documentSnapshot.getString("icon");
                                String categoryName = documentSnapshot.getString("categoryName");
                                catNames4.add(categoryName);
                                catImageUrl4.add(icon);
                            }
                            adapter.notifyDataSetChanged();
                        } else {
                            String error = task.getException().getMessage();
                            Toast.makeText(CountryChoiceActivity.this, error, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
    //australia
    private void AustraliaMethod() {
        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(this);
        linearLayoutManager2.setOrientation(LinearLayoutManager.HORIZONTAL);
        australia_recyclerView.setLayoutManager(linearLayoutManager2);
        countryOptionAdapter adapter2 = new countryOptionAdapter(this, catNames5, catImageUrl5);
        australia_recyclerView.setAdapter(adapter2);

        FirebaseFirestore firebaseFirestore2;
        firebaseFirestore2 = FirebaseFirestore.getInstance();
        firebaseFirestore2.collection("Australia").orderBy("index").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        catNames5.clear();
                        catImageUrl5.clear();
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot documentSnapshot : task.getResult()) {
                                String icon = documentSnapshot.getString("icon");
                                String categoryName = documentSnapshot.getString("categoryName");
                                catNames5.add(categoryName);
                                catImageUrl5.add(icon);
                            }
                            adapter2.notifyDataSetChanged();
                        } else {
                            String error = task.getException().getMessage();
                            Toast.makeText(CountryChoiceActivity.this, error, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
    //south america
    private void SouthAmericamethod(){
        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(this);
        linearLayoutManager2.setOrientation(LinearLayoutManager.HORIZONTAL);
        south_recyclerView.setLayoutManager(linearLayoutManager2);
        countryOptionAdapter adapter2 = new countryOptionAdapter(this, catNames6, catImageUrl6);
        south_recyclerView.setAdapter(adapter2);

        FirebaseFirestore firebaseFirestore2;
        firebaseFirestore2 = FirebaseFirestore.getInstance();
        firebaseFirestore2.collection("South America").orderBy("index").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        catNames6.clear();
                        catImageUrl6.clear();
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot documentSnapshot : task.getResult()) {
                                String icon = documentSnapshot.getString("icon");
                                String categoryName = documentSnapshot.getString("categoryName");
                                catNames6.add(categoryName);
                                catImageUrl6.add(icon);
                            }
                            adapter2.notifyDataSetChanged();
                        } else {
                            String error = task.getException().getMessage();
                            Toast.makeText(CountryChoiceActivity.this, error, Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }
//africa
    private void africamethod() {
        //africa
        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(this);
        linearLayoutManager2.setOrientation(LinearLayoutManager.HORIZONTAL);
        africa_recyclerView.setLayoutManager(linearLayoutManager2);
        countryOptionAdapter adapter2 = new countryOptionAdapter(this, catNames2, catImageUrl2);
        africa_recyclerView.setAdapter(adapter2);

        FirebaseFirestore firebaseFirestore2;
        firebaseFirestore2 = FirebaseFirestore.getInstance();
        firebaseFirestore2.collection("Africa").orderBy("index").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        catNames2.clear();
                        catImageUrl2.clear();
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot documentSnapshot : task.getResult()) {
                                String icon = documentSnapshot.getString("icon");
                                String categoryName = documentSnapshot.getString("categoryName");
                                catNames2.add(categoryName);
                                catImageUrl2.add(icon);
                            }
                            adapter2.notifyDataSetChanged();
                        } else {
                            String error = task.getException().getMessage();
                            Toast.makeText(CountryChoiceActivity.this, error, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
//africa
    }
}