package com.example.yumplanner.presentation.Search.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yumplanner.R;
import com.example.yumplanner.presentation.Search.model.Category;
import com.example.yumplanner.presentation.Search.model.Country;
import com.example.yumplanner.presentation.Search.model.Ingridents;
import com.example.yumplanner.presentation.Search.model.SearchableItems;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;


public class SearchFragment extends Fragment {

    private ChipGroup filterChipGroup;
    private TextInputEditText searchEditText;
    private RecyclerView recyclerView;
    private SearchAdaptor adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_search, container, false);
        return  view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        filterChipGroup = view.findViewById(R.id.filterChipGroup);
        searchEditText = view.findViewById(R.id.searchEditText);
        recyclerView = view.findViewById(R.id.searchItems);

         adapter = new SearchAdaptor();
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recyclerView.setAdapter(adapter);

        filterChipGroup.check(R.id.chipCountry);
        loadCountryData();

        filterChipGroup.setOnCheckedStateChangeListener((group, checkedIds) -> {
            if (checkedIds.isEmpty()) return;
            int checkedId = checkedIds.get(0);
            searchEditText.setText("");

            if (checkedId == R.id.chipCountry) {
                loadCountryData();
            } else if (checkedId == R.id.chipIngredient) {
                loadIngredientData();
            } else if (checkedId == R.id.chipCategory) {
                loadCategoryData();
            }
        });

        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter.filter(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }

            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
        });
    }

    private void loadCountryData() {
        searchEditText.setHint("Search by Country...");
        List<SearchableItems> countries = new ArrayList<>();
        countries.add(new Country("Egypt"));
        countries.add(new Country("Italy"));
        countries.add(new Country("Japan"));
        countries.add(new Country("Mexico"));
        adapter.setData(countries);
    }

    private void loadIngredientData() {
        searchEditText.setHint("Search by Ingredient...");
        adapter.setData(new ArrayList<>());
        List<SearchableItems> ingredients = new ArrayList<>();
        ingredients.add(new Ingridents("Chicken"));
        ingredients.add(new Ingridents("Tomato"));
        ingredients.add(new Ingridents("Onion"));
        ingredients.add(new Ingridents("Garlic"));
        adapter.setData(ingredients);
    }

    private void loadCategoryData() {
        adapter.setData(new ArrayList<>());
        List<SearchableItems> ingredients = new ArrayList<>();
        ingredients.add(new Category("Chicken"));
        ingredients.add(new Category("Tomato"));
        ingredients.add(new Category("Onion"));
        ingredients.add(new Category("Garlic"));
        adapter.setData(ingredients);
    }
}