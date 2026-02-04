package com.example.yumplanner.presentation.Home.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yumplanner.R;

public class RecommendationsAdaptor extends RecyclerView.Adapter<RecommendationsAdaptor.RecommendationHolder> {
    @NonNull
    @Override
    public RecommendationHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.recommendations_item,parent,false);
        return new RecommendationHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecommendationHolder holder, int position) {
        holder.imageView.setImageResource(R.drawable.unnamed);
        holder.foodName.setText("Food");

    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public class  RecommendationHolder extends  RecyclerView.ViewHolder {
        ImageView imageView;
        TextView foodName;

        RecommendationHolder(View view){
            super(view);
            imageView=view.findViewById(R.id.recipeImage);
            foodName=view.findViewById(R.id.recipeTitle);




        }

    }
}

