package com.example.yumplanner.presentation.search_detail.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yumplanner.R;
import com.example.yumplanner.data.home.model.Meal;
import com.example.yumplanner.utiles.imageHelper.ImageHelper;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.ArrayList;
import java.util.List;

public class SearchDetailAdaptor  extends RecyclerView.Adapter<SearchDetailAdaptor.SearchDetailHolder> {
    List<Meal> meals=new ArrayList<>();
    OnDetailClick onDetailClick;
    public SearchDetailAdaptor(OnDetailClick onDetailClick){
        this.onDetailClick=onDetailClick;
    }
    @NonNull
    @Override
    public SearchDetailHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.search_detail_item,parent,false);
        return new SearchDetailHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchDetailHolder holder, int position) {
        holder.bindingData(meals.get(position),onDetailClick);
    }
    public  void setData(List<Meal>meals){
        this.meals=meals;
        notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        return meals.size();
    }

    class  SearchDetailHolder extends  RecyclerView.ViewHolder{
        TextView searchItemName;
        ShapeableImageView searchItemImage;
        MaterialCardView cardView;
      public SearchDetailHolder(@NonNull View itemView) {
          super(itemView);
          searchItemName=itemView.findViewById(R.id.search_detail_text);
          searchItemImage=itemView.findViewById(R.id.search_detail_image);
          cardView=itemView.findViewById(R.id.detialmealBtn);
      }
      public  void bindingData(Meal meals,OnDetailClick onDetailClick){
          searchItemName.setText(meals.getMealName());
          ImageHelper.loadImage(itemView,meals.getMealUrl(),searchItemImage);
          cardView.setOnClickListener(v -> {

               onDetailClick.toDetails(meals.getMealId());
          });
      }
  }
 }
