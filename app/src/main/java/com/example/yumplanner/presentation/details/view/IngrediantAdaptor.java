package com.example.yumplanner.presentation.details.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yumplanner.R;


public class IngrediantAdaptor extends RecyclerView.Adapter<IngrediantAdaptor.IngreidantHolder> {


    @NonNull
    @Override
    public IngreidantHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.ingrediant_item,parent,false);
        return new IngreidantHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IngreidantHolder holder, int position) {
        holder.imageView.setImageResource(R.drawable.unnamed);
        holder.ingrediantName.setText("Onion");
        holder.ingredinatAmount.setText("2 slice");

    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public class  IngreidantHolder extends  RecyclerView.ViewHolder {
        ImageView imageView;
        TextView  ingrediantName;
        TextView ingredinatAmount;

        IngreidantHolder(View view){
            super(view);
            imageView=view.findViewById(R.id.ingrediant_image);
            ingrediantName=view.findViewById(R.id.ingrediant_name);
            ingredinatAmount=view.findViewById(R.id.ingrediant_amount);




        }

    }
}

