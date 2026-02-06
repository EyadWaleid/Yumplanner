package com.example.yumplanner.presentation.Home.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.yumplanner.R;
import com.example.yumplanner.data.dto.DetialMealDTO;
import com.example.yumplanner.data.model.DetailMeal;
import com.example.yumplanner.data.model.Meal;
import com.example.yumplanner.presentation.Home.presenter.HomePresenter;
import com.example.yumplanner.presentation.Home.presenter.HomePresenterImp;
import com.example.yumplanner.presentation.details.view.DetialActivity;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.List;

/*public class HomeFragment extends Fragment implements HomeView{

  RecyclerView recyclerView;

  MaterialButton viewRecipe;
  ShapeableImageView mealImage;
  TextView mealName;
  NestedScrollView nestedScrollView;
  HomePresenter homePresenter;
  MaterialButton viewFood;
  HomeViewModel viewModel;
  View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         View view=inflater.inflate(R.layout.fragment_home, container, false);
     return  view;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        homePresenter= new HomePresenterImp(this);
        nestedScrollView=view.findViewById(R.id.homeView);
        mealImage=view.findViewById(R.id.randomMealImage);
        mealName=view.findViewById(R.id.mealNmae);
         recyclerView = view.findViewById(R.id.recommendationRecycleView);
        viewRecipe=view.findViewById(R.id.btnViewRecipe);
        this.view=view.findViewById(R.id.loadingHome);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        RecommendationsAdaptor adapter = new RecommendationsAdaptor();
        recyclerView.setAdapter(adapter);
        viewRecipe.setOnClickListener(v -> {
            Intent intent = new Intent(requireContext(), DetialActivity.class);
            startActivity(intent);
        });

        homePresenter.getRandomData();

        viewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        viewModel.getRandomMeal().observe(getViewLifecycleOwner(), meal -> {
            if (meal == null) return;

            Glide.with(mealImage)
                    .load(meal.getIdMeal())
                    .into(mealImage);

            mealName.setText(meal.getStrMeal());
        });

        viewModel.fetchRandomMeal();

    }



    @Override
    public void showError() {
        nestedScrollView.setVisibility(NestedScrollView.VISIBLE);

    }

    @Override
    public void showLoading() {
         view.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        view.setVisibility(View.GONE);
    }

    @Override
    public void setData(DetailMeal detailMeal) {
        Glide.with(this)
                .load(detailMeal.getStrMealThumb())
                .into(mealImage);
        mealName.setText(detailMeal.getStrMeal());
        nestedScrollView.setVisibility(NestedScrollView.VISIBLE);



    }
}*/
/*public class HomeFragment extends Fragment {

    private RecyclerView recyclerView;
    private MaterialButton viewRecipe;
    private ShapeableImageView mealImage;
    private TextView mealName;
    private NestedScrollView nestedScrollView;
    private View loadingView;

    private HomeViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Initialize views
        nestedScrollView = view.findViewById(R.id.homeView);
        mealImage = view.findViewById(R.id.randomMealImage);
        mealName = view.findViewById(R.id.mealNmae);
        recyclerView = view.findViewById(R.id.recommendationRecycleView);
        viewRecipe = view.findViewById(R.id.btnViewRecipe);
        loadingView = view.findViewById(R.id.loadingHome);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        RecommendationsAdaptor adapter = new RecommendationsAdaptor();
        recyclerView.setAdapter(adapter);

        viewRecipe.setOnClickListener(v -> {
            Intent intent = new Intent(requireContext(), DetialActivity.class);
            startActivity(intent);
        });

        viewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        viewModel.getRandomMeal().observe(getViewLifecycleOwner(), meal -> {
            if (meal == null) return;

            Glide.with(mealImage)
                    .load(meal.getStrMealThumb())
                    .into(mealImage);

            mealName.setText(meal.getStrMeal());
            nestedScrollView.setVisibility(View.VISIBLE);
            loadingView.setVisibility(View.GONE);
        });

        loadingView.setVisibility(View.VISIBLE);
        nestedScrollView.setVisibility(View.GONE);

        viewModel.fetchRandomMeal();
    }
}*/
public class HomeFragment extends Fragment implements HomeView {

    private HomePresenter presenter;
    private RecyclerView recyclerView;
    private MaterialButton viewRecipe;
    private ShapeableImageView mealImage;
    private TextView mealName;
    private NestedScrollView nestedScrollView;
    private View loadingView;
    private HomeViewModel viewModel;
   private      RecommendationsAdaptor adaptor;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View rootView, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(rootView, savedInstanceState);

        nestedScrollView = rootView.findViewById(R.id.homeView);
        mealImage = rootView.findViewById(R.id.randomMealImage);
        mealName = rootView.findViewById(R.id.mealNmae);
        recyclerView = rootView.findViewById(R.id.recommendationRecycleView);
        viewRecipe = rootView.findViewById(R.id.btnViewRecipe);
        loadingView = rootView.findViewById(R.id.loadingHome);



        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
         adaptor=new RecommendationsAdaptor();
        viewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        presenter = new HomePresenterImp(this, viewModel);
        presenter.getRandomData();
        recyclerView.setAdapter(adaptor);



        viewRecipe.setOnClickListener(v ->{
            presenter.reachDetails();

        });
    }


    @Override
    public void showLoading() {
        loadingView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        loadingView.setVisibility(View.GONE);}

    @Override
    public void setSpecialMeal(String mealTitle, String imageMeal) {
        Glide.with(this)
                .load(imageMeal)
                .into(mealImage);

        mealName.setText(mealTitle);
        nestedScrollView.setVisibility(View.VISIBLE);
        hideLoading();

    }
    @Override
    public void setDessert(List<Meal> desserts) {
        adaptor.setDessertMeals(desserts);
    }

    @Override
    public void showError() {
        loadingView.setVisibility(View.INVISIBLE);
        nestedScrollView.setVisibility(NestedScrollView.VISIBLE);
        mealName.setText("Error loading meal");
    }
    @Override
    public void toDetial(DetialMealDTO detailMeal) {
        Intent intent=new Intent(requireContext(), DetialActivity.class);
        intent.putExtra("MEAL_OBJECT", detailMeal);
        startActivity(intent);
    }
}
