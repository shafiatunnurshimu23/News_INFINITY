package com.example.projectnewsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class AdminPannelActivity extends AppCompatActivity {
    Button write_btn,read_btn;
ProgressDialog pd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_pannel);
        //actionbar bg color
        getSupportActionBar().setBackgroundDrawable(
                new ColorDrawable(Color.parseColor("#FF018786")));
        //backbutton
        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("Admin pannel");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        pd=new ProgressDialog(this);
        pd.setMessage("Loading Homepage....");


        write_btn=findViewById(R.id.Write_button);
        read_btn=findViewById(R.id.read_button);
        write_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pd.show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        pd.dismiss();
                    }
                },1000);
                Intent intent =new Intent(AdminPannelActivity.this,AddNewsToFireStoreActivity.class);
                startActivity(intent);

            }
        });
        read_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pd.show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        pd.dismiss();
                    }
                },1000);
                Intent intent =new Intent(AdminPannelActivity.this,HomepageActivity.class);
                startActivity(intent);


            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        if(item.getItemId()==android.R.id.home)
        {
            this.finish();
        }
        else if(item.getItemId()==R.id.logOut){
            Toast.makeText(AdminPannelActivity.this,"Logged out",Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(AdminPannelActivity.this,AdminLoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.admin_menu,menu);
        return true;
    }
}