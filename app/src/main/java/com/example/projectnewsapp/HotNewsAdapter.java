package com.example.projectnewsapp;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class HotNewsAdapter extends RecyclerView.Adapter<HotNewsAdapter.ViewHolder> {
    private List<Model2> model2List;

    public HotNewsAdapter(List<Model2> model2List) {
        this.model2List = model2List;
    }

    @NonNull
    @Override
    public HotNewsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_headlines, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HotNewsAdapter.ViewHolder holder, int position) {
        String image = model2List.get(position).getImage();
        String by = model2List.get(position).getBy();
        String time = model2List.get(position).getTime();
        String headline = model2List.get(position).getName();

        Glide.with(holder.itemView.getContext())
                .load(image)
                .placeholder(R.drawable.no_image)
                .error(R.drawable.no_image)
                .into(holder.image);
        holder.by.setText(by);
        holder.time.setText(time);
        holder.setHeadline(headline);
    }

    @Override
    public int getItemCount() {
        return model2List.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView image;
        private TextView by, time, headline;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.detailsImage);
            by = itemView.findViewById(R.id.detailsPublishedBy);
            time = itemView.findViewById(R.id.detailsPublishTime);
            headline = itemView.findViewById(R.id.hotnewsHeadline);
        }

        private void setHeadline(String hLine) {
            headline.setText(hLine);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(itemView.getContext(), DetailsActivity.class);
                    intent.putExtra("title", hLine);
                    itemView.getContext().startActivity(intent);
                }
            });
        }
    }

}
