package com.example.yumplanner.presentation.Home.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.yumplanner.R;
import com.example.yumplanner.data.home.model.Meal;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;
import java.util.List;

public class RecommendationsAdaptor extends RecyclerView.Adapter<RecommendationsAdaptor.RecommendationHolder> {
    DessertOnClickListener dessertOnClickListener;

    public RecommendationsAdaptor(DessertOnClickListener dessertOnClickListener) {
        this.dessertOnClickListener = dessertOnClickListener;
    }

    List<Meal>dessertMeals=new ArrayList<>();
    @NonNull
    @Override
    public RecommendationHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.recommendations_item,parent,false);
        return new RecommendationHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecommendationHolder holder, int position) {
        Meal dessert=dessertMeals.get(position);
        holder.bindData(dessert);
    }
    @Override
    public int getItemCount() {
        return dessertMeals.size();
    }
    public class  RecommendationHolder extends  RecyclerView.ViewHolder {
        ImageView imageView;
        TextView foodName;
        MaterialCardView cardView;
        RecommendationHolder(View itemView){
            super(itemView);
            imageView=itemView.findViewById(R.id.recipeImage);
            foodName=itemView.findViewById(R.id.recipeTitle);
            cardView=itemView.findViewById(R.id.recipeCard);
        }
        public  void bindData(Meal dessert){
            Glide.with(itemView)
                    .load(dessert.getMealUrl())
                    .into(imageView);
            foodName.setText(dessert.getMealName());
            cardView.setOnClickListener(v -> {
                dessertOnClickListener.goToDetails(dessert.getMealId());
            });

        }

    }
    public void setDessertMeals(List<Meal> meals){
        dessertMeals=meals;
    }

}

