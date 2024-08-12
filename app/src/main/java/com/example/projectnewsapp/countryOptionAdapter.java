package com.example.projectnewsapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class countryOptionAdapter extends RecyclerView.Adapter<countryOptionAdapter.ViewHolder> {
    private ArrayList<String> catNames=new ArrayList<>();
    private ArrayList<String> catImageUrl=new ArrayList<>();
    private Context catContext;

    public countryOptionAdapter(Context catContext,ArrayList<String> catNames, ArrayList<String> catImageUrl) {
        this.catNames = catNames;
        this.catImageUrl = catImageUrl;
        this.catContext = catContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.country_category,parent,false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(catContext)
                .asBitmap()
                .load(catImageUrl.get(position))
                .into(holder.imageView);
        holder.setName(catNames.get(position));

    }

    @Override
    public int getItemCount() {
        return catNames.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.category_image_country);
            name=itemView.findViewById(R.id.category_name_country);
        }

        private void setName(String title) {
            name.setText(title);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(itemView.getContext(), CountryWiseNews.class);
                    intent.putExtra("title", title);
                    itemView.getContext().startActivity(intent);

                }
            });
        }
    }
}
