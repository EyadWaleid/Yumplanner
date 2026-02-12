package com.example.yumplanner.presentation.Search.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yumplanner.R;
import com.example.yumplanner.data.home.model.Meal;
import com.example.yumplanner.presentation.Search.model.SearchableItems;
import com.example.yumplanner.utiles.imageHelper.ImageHelper;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.imageview.ShapeableImageView;
import java.util.ArrayList;
import java.util.List;
public class SearchAdaptor extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<SearchableItems> displayList = new ArrayList<>();
    private  List<Meal> displayMeals= new ArrayList<>();
  OnShowResult onShowResult;
    public  SearchAdaptor(OnShowResult onShowResult){
        this.onShowResult=onShowResult;
    }
    private  String type="";
    private  final  int TYPE_COUNTRY = 1;
    private  final int TYPE_INGREDIENT = 2;
    private   final int TYPE_CATEGORY = 3;
    private  final  int TYPE_MEAL=4;
    @Override
    public int getItemViewType(int position) {
        if ("a".equals(type)) return TYPE_COUNTRY;
        if ("i".equals(type)) return TYPE_INGREDIENT;
        if ("c".equals(type)) return TYPE_CATEGORY;
        if ("s".equals(type)) return TYPE_MEAL;
        return -1;
    }
    public void setData(List<SearchableItems> newData,String type) {
        displayMeals.clear();
        displayList.clear();
        displayList.addAll(newData);
        this.type=type;
        notifyDataSetChanged();
    }
    public  void setDisplayMeals(List<Meal> meals,String type){
        displayMeals.clear();
        displayList.clear();
        displayMeals.addAll(meals);
        this.type=type;
        notifyDataSetChanged();
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
            case TYPE_MEAL:
                View mealView = inflater.inflate(R.layout.meal_search_item, parent, false);
                return new MealHolder(mealView);
            default:
                throw new IllegalArgumentException("Invalid view type");
        }
    }
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (type.equals("c")) {
            SearchableItems item = displayList.get(position);
            ((CategoryHolder)holder).bindCategoryData(item, onShowResult);
        } else if (type.equals("i")) {
            SearchableItems item = displayList.get(position);
            ((IngredientHolder)holder).bindingIngrediantData(item, onShowResult);
        } else if (type.equals("a")) {
            SearchableItems item = displayList.get(position);
            ((CountryHolder) holder).bindCountryData(item, onShowResult);
        } else if (type.equals("s")) {
            Meal meal = displayMeals.get(position);
            ((MealHolder)holder).bindMealData(meal);
        }
    }
    @Override
    public int getItemCount() {
        if(type.equals("i")||type.equals("c")||type.equals("a")) return displayList.size();
       else  if (type.equals("s"))  return  displayMeals.size();
       else  return 0;
    }

    public  void clearSearchableItemsData(){
        displayList.clear();
        displayMeals.clear();
        type = "";
        notifyDataSetChanged();
    }
    public  void filter(List<SearchableItems> searchableItems){
        displayList.clear();

        displayList.addAll(searchableItems);
        notifyDataSetChanged();
    }

    class CountryHolder extends RecyclerView.ViewHolder {
        TextView textView;
        ShapeableImageView shapeableImageView;
        FrameLayout countryBtn;

        public CountryHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.country_search_txt);
            shapeableImageView = itemView.findViewById(R.id.country_image_search);
            countryBtn=itemView.findViewById(R.id.countryBtn);
        }
        public  void  bindCountryData(SearchableItems searchableItem,OnShowResult onShowResult){
            textView.setText(searchableItem.getName());
            ImageHelper.loadImage(itemView,searchableItem.getImageUrl(),shapeableImageView);
            countryBtn.setOnClickListener(v -> {
                onShowResult.showSearchResult(searchableItem.getName(),"a");

            });
        }
    }

    class IngredientHolder extends RecyclerView.ViewHolder {
        TextView textView;
        ShapeableImageView shapeableImageView;
        MaterialCardView materialCardView;
        public IngredientHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.ingrediant_name_search);
            shapeableImageView = itemView.findViewById(R.id.ingrediant_image_search);
           materialCardView=itemView.findViewById(R.id.ingredientCard);
        }
        public  void bindingIngrediantData(SearchableItems item,OnShowResult onShowResult){
            textView.setText(item.getName());
            ImageHelper.loadImage(itemView,item.getImageUrl(),shapeableImageView);
            materialCardView.setOnClickListener(v -> {
                onShowResult.showSearchResult(item.getName(),"i");

            });

        }
    }

      class CategoryHolder extends RecyclerView.ViewHolder {
        TextView textView;
        ShapeableImageView shapeableImageView;
        FrameLayout cataBtn;

        public CategoryHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.catagories_search_txt);
            shapeableImageView = itemView.findViewById(R.id.catagories_image_item);
            cataBtn=itemView.findViewById(R.id.cataBtn);
        }
        public  void  bindCategoryData(SearchableItems searchableItem,OnShowResult onShowResult){
            textView.setText(searchableItem.getName());
            ImageHelper.loadImage(itemView,searchableItem.getImageUrl(),shapeableImageView);
            cataBtn.setOnClickListener(v -> {
                 onShowResult.showSearchResult(searchableItem.getName(),"c");
            });

        }
    }
      class MealHolder extends  RecyclerView.ViewHolder{
          TextView textView;
          ShapeableImageView shapeableImageView;
          MaterialCardView cataBtn;

          public MealHolder(@NonNull View itemView) {
              super(itemView);
              textView = itemView.findViewById(R.id.search_meal_text);
              shapeableImageView = itemView.findViewById(R.id.search_meal_image);
              cataBtn=itemView.findViewById(R.id.mealSearch);
          }
          public  void  bindMealData(Meal meal){
              textView.setText(meal.getMealName());
              ImageHelper.loadImage(itemView, meal.getMealUrl(),shapeableImageView);
              cataBtn.setOnClickListener(v -> {

              });

          }

      }
}
