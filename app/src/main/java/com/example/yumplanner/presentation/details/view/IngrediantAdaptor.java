package com.example.yumplanner.presentation.details.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yumplanner.R;
import com.example.yumplanner.data.home.model.Ingredient;
import com.example.yumplanner.utiles.imageHelper.ImageHelper;

import java.util.ArrayList;
import java.util.List;


public class IngrediantAdaptor extends RecyclerView.Adapter<IngrediantAdaptor.IngreidantHolder> {

    List<Ingredient> ingredientsList=new ArrayList<>();
    @NonNull
    @Override
    public IngreidantHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.ingrediant_item,parent,false);
        return new IngreidantHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IngreidantHolder holder, int position) {

       holder.bindingData(ingredientsList.get(position));

    }
    void setIngredientsList(List<Ingredient>ingredientsList){
        this.ingredientsList=ingredientsList;
    }

    @Override
    public int getItemCount() {
        return ingredientsList.size();
    }

    public class  IngreidantHolder extends  RecyclerView.ViewHolder {
        ImageView imageView;
        TextView  ingrediantName;
        TextView ingredinatAmount;
     public    IngreidantHolder(View view){
            super(view);
            imageView=view.findViewById(R.id.ingrediant_image);
            ingrediantName=view.findViewById(R.id.ingrediant_name);
            ingredinatAmount=view.findViewById(R.id.ingrediant_amount);
        }
        public void bindingData(Ingredient ing){
            ImageHelper.loadImage(itemView,ing.getImageUrl(),imageView);
            ingrediantName.setText(ing.getName());
           ingredinatAmount.setText(ing.getMeasure());
        }

    }
}

