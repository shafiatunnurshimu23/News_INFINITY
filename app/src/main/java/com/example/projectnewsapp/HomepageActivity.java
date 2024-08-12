package com.example.projectnewsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.view.GravityCompat;
import androidx.core.view.MenuItemCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class HomepageActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    RecyclerView recyclerView, recyclerView2;
    private ArrayList<String> catNames = new ArrayList<>();
    private ArrayList<String> catImageUrl = new ArrayList<>();
    HotNewsAdapter hotNewsAdapter;
    ProgressDialog progressDialog;
    ImageView imageView;
    FirebaseAuth auth;

    List<Model2> model2List = new ArrayList<Model2>();
    //navigation
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        auth=FirebaseAuth.getInstance();
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView2 = findViewById(R.id.recyclerView2);
        progressDialog =new ProgressDialog(this);
        imageView=findViewById(R.id.toolbar_search_image);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(HomepageActivity.this,SearchNewsActivity.class);
                startActivity(intent);
            }
        });


        //////Category
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, catNames, catImageUrl);
        recyclerView.setAdapter(adapter);

        FirebaseFirestore firebaseFirestore;
        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseFirestore.collection("Categories").orderBy("index").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        catNames.clear();
                        catImageUrl.clear();
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot documentSnapshot : task.getResult()) {
                                String icon = documentSnapshot.getString("icon");
                                String categoryName = documentSnapshot.getString("categoryName");
                                catNames.add(categoryName);
                                catImageUrl.add(icon);
                            }
                            adapter.notifyDataSetChanged();
                        } else {
                            String error = task.getException().getMessage();
                            Toast.makeText(HomepageActivity.this, error, Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        //////Category

        //////Hot News
        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(this);
        recyclerView2.setLayoutManager(linearLayoutManager2);

        hotNewsAdapter = new HotNewsAdapter(model2List);
        recyclerView2.setAdapter(hotNewsAdapter);
        progressDialog.setMessage("loading news...");
        progressDialog.show();

        firebaseFirestore.collection("TopNews").orderBy("index").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            progressDialog.cancel();
                            for (QueryDocumentSnapshot documentSnapshot : task.getResult()) {
                                long noOfProducts = documentSnapshot.getLong("no_of_news");
                                for (long x = 1; x <= noOfProducts; x++) {
                                    model2List.add(new Model2(documentSnapshot.getString("name_" + x),
                                            documentSnapshot.getString("image_" + x), documentSnapshot.getString("by_" + x),
                                            documentSnapshot.getString("time_" + x)));
                                }
                            }
                            hotNewsAdapter.notifyDataSetChanged();
                        } else {
                            progressDialog.cancel();
                            String error = task.getException().getMessage();
                            Toast.makeText(HomepageActivity.this, error, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        //////Hot News




        //navigation

        drawerLayout = findViewById(R.id.Drawer_layout);
        navigationView = findViewById(R.id.navigation_view);
        toolbar = findViewById(R.id.toolbar_id);

        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.Open_navigation_drawer, R.string.Close_navigation_drawer);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.home_icon);
        ///navigation




    }
///navigation
    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home_icon:
                break;
            case R.id.country_option: {
                Intent intent = new Intent(HomepageActivity.this, CountryChoiceActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.video_icon: {
                Intent intent = new Intent(HomepageActivity.this, WatchVideoActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.Sign_up_icon: {
                Intent intent = new Intent(HomepageActivity.this, SignUpActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.login_icon: {
                Intent intent = new Intent(HomepageActivity.this, LoginActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.logout_icon: {
                Toast.makeText(HomepageActivity.this, "Loged out from account!!!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(HomepageActivity.this, LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
                break;
            }

        }
        drawerLayout.closeDrawer(GravityCompat.START);

        return true;
    }
    //navigation


    //search menu





}
