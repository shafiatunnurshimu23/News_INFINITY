package com.example.projectnewsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class AdminLoginActivity extends AppCompatActivity {
    private EditText text,text1,text2;
    Button button;
    ProgressDialog progressDialog;
    int f=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);
        text1=findViewById(R.id.Admin_email_id);
        text2=findViewById(R.id.Admin_password);
        text=findViewById(R.id.Admin_name);
        button=findViewById(R.id.Admin_login);

        //actionbar bg color
        getSupportActionBar().setBackgroundDrawable(
                new ColorDrawable(Color.parseColor("#FF018786")));
        //backbutton
        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("Admin Pannel");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("loading");
        FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog.show();
                firebaseFirestore.collection("Admin").get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if(task.isSuccessful()) {
                                    progressDialog.cancel();
                                    for(QueryDocumentSnapshot documentSnapshot : task.getResult()) {
                                        String pass=documentSnapshot.getString("password");
                                        String email=documentSnapshot.getString("email");

                                        if(pass.equals(text2.getText().toString()) && email.equals(text1.getText().toString()))
                                        {
                                            Toast.makeText(AdminLoginActivity.this,"Login Successful!!",Toast.LENGTH_SHORT).show();
                                            f=1;
                                            Intent intent=new Intent(AdminLoginActivity.this,AdminPannelActivity.class);
                                        startActivity(intent);
                                            break;

                                        }
                                    }
                                    if(f==0) {
                                        Toast.makeText(AdminLoginActivity.this, "Wrong Information!!!", Toast.LENGTH_SHORT).show();
                                    }

                                } else {
                                    progressDialog.cancel();
                                    String error = task.getException().getMessage();
                                    Toast.makeText(AdminLoginActivity.this, error, Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

            }
        });

    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==android.R.id.home)
        {
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }

}