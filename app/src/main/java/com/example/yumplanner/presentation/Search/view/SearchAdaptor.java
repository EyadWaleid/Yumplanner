package com.example.yumplanner.presentation.Search.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yumplanner.R;
import com.example.yumplanner.presentation.Search.model.Category;
import com.example.yumplanner.presentation.Search.model.Country;
import com.example.yumplanner.presentation.Search.model.Ingridents;
import com.example.yumplanner.presentation.Search.model.SearchableItems;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.ArrayList;
import java.util.List;

public class SearchAdaptor extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<SearchableItems> displayList = new ArrayList<>();
    private List<SearchableItems> originalList = new ArrayList<>();

    private static final int TYPE_COUNTRY = 1;
    private static final int TYPE_INGREDIENT = 2;
    private static final int TYPE_CATEGORY = 3;

    @Override
    public int getItemViewType(int position) {
        SearchableItems item = displayList.get(position);
        if (item instanceof Country) return TYPE_COUNTRY;
        if (item instanceof Ingridents) return TYPE_INGREDIENT;
        if (item instanceof Category) return TYPE_CATEGORY;
        return -1;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        switch (viewType) {
            case TYPE_COUNTRY:
                View countryView = inflater.inflate(R.layout.country_item, parent, false);
                return new CountryHolder(countryView);

            case TYPE_INGREDIENT:
                View ingredientView = inflater.inflate(R.layout.ingrediant_search_item, parent, false);
                return new IngredientHolder(ingredientView);

            case TYPE_CATEGORY:
                View categoryView = inflater.inflate(R.layout.catagories_item, parent, false);
                return new CategoryHolder(categoryView);

            default:
                throw new IllegalArgumentException("Invalid view type");
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        SearchableItems item = displayList.get(position);

        if (holder instanceof CountryHolder && item instanceof Country) {
            ((CountryHolder) holder).textView.setText(item.getDisplayName());
        } else if (holder instanceof IngredientHolder && item instanceof Ingridents) {
            ((IngredientHolder) holder).textView.setText(item.getDisplayName());
        } else if (holder instanceof CategoryHolder && item instanceof Category) {
            ((CategoryHolder) holder).textView.setText(item.getDisplayName());
        }
    }

    @Override
    public int getItemCount() {
        return displayList.size();
    }

    public void filter(String query) {
        List<SearchableItems> filteredList = new ArrayList<>();
        for (SearchableItems item : originalList) {
            if (item.getDisplayName().toLowerCase().contains(query.toLowerCase())) {
                filteredList.add(item);
            }
        }
        displayList.clear();
        displayList.addAll(filteredList);
        notifyDataSetChanged();
    }

    // Set new data
    public void setData(List<SearchableItems> newData) {
        originalList.clear();
        originalList.addAll(newData);

        displayList.clear();
        displayList.addAll(newData);

        notifyDataSetChanged();
    }

    public static class CountryHolder extends RecyclerView.ViewHolder {
        TextView textView;
        ShapeableImageView shapeableImageView;

        public CountryHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.country_search_txt);
            shapeableImageView = itemView.findViewById(R.id.country_image_search);
        }
    }

    public static class IngredientHolder extends RecyclerView.ViewHolder {
        TextView textView;
        ShapeableImageView shapeableImageView;

        public IngredientHolder(@NonNull View itemView) {
            super(itemView);
         /*   textView = itemView.findViewById(R.id.ingredient_search_txt);
            shapeableImageView = itemView.findViewById(R.id.ingredient_image_search);*/
        }
    }

    public static class CategoryHolder extends RecyclerView.ViewHolder {
        TextView textView;
        ShapeableImageView shapeableImageView;

        public CategoryHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.catagories_search_txt);
            shapeableImageView = itemView.findViewById(R.id.catagories_image_item);
        }
    }
}
