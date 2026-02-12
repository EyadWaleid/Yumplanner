package com.example.yumplanner.presentation.fav.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.yumplanner.R;
import com.example.yumplanner.data.home.model.DetialMeal;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FavAdaptor extends RecyclerView.Adapter<FavAdaptor.FavHolder>{
    List<DetialMeal>detialMeals =new ArrayList<>();
    OnDeleteListener onDeleteListener;
    boolean isNetworkConnected=true;

    public FavAdaptor(OnDeleteListener onDeleteListener) {
        this.onDeleteListener = onDeleteListener;
    }
    @NonNull
    @Override
    public FavHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.fav_item,parent,false);
        return new FavHolder(view);
    }
    void setDetialMeals(List<DetialMeal> detialMeals){
        this.detialMeals=detialMeals;
        notifyDataSetChanged();
    }
    public void setNetworkState(boolean connected) {
        isNetworkConnected = connected;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull FavHolder holder, int position) {
        holder.bindingData(detialMeals.get(position),isNetworkConnected);
    }
    @Override
    public int getItemCount() {
        return detialMeals.size();
    }
    class  FavHolder extends  RecyclerView.ViewHolder{
        ImageView imageView;
        TextView mealName;
        MaterialCardView mealCard;
        MaterialButton deleteBtn;

        public FavHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.recipeImageFav);
            mealName=itemView.findViewById(R.id.recipeName);
            mealCard=itemView.findViewById(R.id.fav_nav);
            deleteBtn=itemView.findViewById(R.id.btnRemove);


        }
        public void bindingData(DetialMeal detialMeal,boolean isConnected){
            mealName.setText(detialMeal.getMealName());
            if(isConnected){
                deleteBtn.setVisibility(View.VISIBLE);
                deleteBtn.setEnabled(true);
            } else {
                deleteBtn.setVisibility(View.INVISIBLE);
                deleteBtn.setEnabled(false);
            }
            Glide.with(itemView)
                    .load(new File(detialMeal.getImgeUrl()))
                    .into(imageView);
            mealCard.setOnClickListener(v -> {
                onDeleteListener.goTODetial(detialMeal);
            });
            deleteBtn.setOnClickListener(v -> {
                onDeleteListener.removeMealFromFav(detialMeal);
                int index = detialMeals.indexOf(detialMeal);
                if (index != -1) {
                    detialMeals.remove(index);
                    notifyItemRemoved(index);
                }
            });

        }
    }
}
