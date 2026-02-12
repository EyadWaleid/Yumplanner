package com.example.yumplanner.presentation.Search.view;

import android.content.Intent;
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

import com.airbnb.lottie.LottieAnimationView;
import com.example.yumplanner.R;
import com.example.yumplanner.data.home.model.Meal;
import com.example.yumplanner.presentation.Search.model.SearchableItems;
import com.example.yumplanner.presentation.Search.presenter.SearchPresenter;
import com.example.yumplanner.presentation.Search.presenter.SearchPresenterImp;
import com.example.yumplanner.presentation.search_detail.view.SearchDetail;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;


public class SearchFragment extends Fragment implements SearchView,OnShowResult {

    private ChipGroup filterChipGroup;
    private TextInputEditText searchEditText;
    private RecyclerView recyclerView;
    private SearchAdaptor adapter;
    private SearchPresenter searchPresenter;
    private LottieAnimationView loader;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_search, container, false);
        return  view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
         init(view);

        filterChipGroup.setOnCheckedStateChangeListener((group, checkedIds) -> {

            if (checkedIds.isEmpty()) return;
            int checkedId = checkedIds.get(0);
            loader.setVisibility(LottieAnimationView.VISIBLE);
            recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
            recyclerView.setAdapter(adapter);
            searchEditText.setText("");
            if (checkedId == R.id.chipCountry) {
                searchPresenter.onDestroy();
                searchPresenter.getAreaList();
                searchPresenter.searchData(createSearchObservable());
            } else if (checkedId == R.id.chipIngredient) {
                searchPresenter.onDestroy();

                searchPresenter.getIngredientList();
                searchPresenter.searchData(createSearchObservable());

            } else if (checkedId == R.id.chipCategory) {
                searchPresenter.onDestroy();

                clearData();
                searchPresenter.getCategoryList();
                searchPresenter.searchData(createSearchObservable());
            }

        });

    }
    void init(View view){
        filterChipGroup = view.findViewById(R.id.filterChipGroup);
        searchEditText = view.findViewById(R.id.searchEditText);
        recyclerView = view.findViewById(R.id.searchItems);
        adapter = new SearchAdaptor(this);
        loader= view.findViewById(R.id.searchLoader);
        searchPresenter = new SearchPresenterImp(this,this.getActivity().getApplicationContext());

    }
    @Override
    public void setCategoryData(List<SearchableItems> items,String type) {

        if (adapter != null) {
            adapter.setData(items, type);
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        searchEditText.setText("");
    }

    @Override
    public void setAreaData(List<SearchableItems> items, String type) {
        if (adapter != null) {
            adapter.setData(items, type);
        }
    }

    @Override
    public void addSearchedData(List<SearchableItems> searchableItemsList ) {
        adapter.filter(searchableItemsList);
    }

    @Override
    public void viewLoader() {
        loader.setVisibility(LottieAnimationView.VISIBLE);

    }

    @Override
    public void addSearchMeal(List<Meal> meals,String type) {
        adapter.setDisplayMeals(meals,type);

    }
    @Override
    public void hideLoader() {
        loader.setVisibility(LottieAnimationView.INVISIBLE);

    }

    @Override
    public void showData() {
        recyclerView.setVisibility(RecyclerView.VISIBLE);
    }

    @Override
    public void hideData() {
        recyclerView.setVisibility(RecyclerView.INVISIBLE);
    }

    @Override
    public void clearData() {
        adapter.clearSearchableItemsData();
    }

    @Override
    public void setTextWatcher(TextWatcher textWatcher) {
        searchEditText.addTextChangedListener(textWatcher);
    }
    @Override
    public void toResultDetials(String name, String type) {
        searchEditText.setText("");
        searchEditText.clearFocus();
        Intent intent=new Intent(requireContext(), SearchDetail.class);
        intent.putExtra("NAME", name);
        intent.putExtra("TYPE",type);
        startActivity(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        searchPresenter.onDestroy();
    }
    private Observable<String> createSearchObservable() {
        return Observable.create(emitter -> {
            searchEditText.addTextChangedListener(new TextWatcher() {
                @Override
                public void afterTextChanged(Editable s) {
                }

                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    emitter.onNext(s.toString());


                }
            });
        });
    }

    @Override
    public void showSearchResult(String name, String type) {
     searchPresenter.toSearchResult(name,type);
    }

}
