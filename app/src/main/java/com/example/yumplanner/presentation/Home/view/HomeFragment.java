package com.example.yumplanner.presentation.Home.view;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.bumptech.glide.Glide;
import com.example.yumplanner.R;
import com.example.yumplanner.data.home.model.DetialMeal;
import com.example.yumplanner.data.home.model.Meal;
import com.example.yumplanner.presentation.Home.presenter.HomePresenter;
import com.example.yumplanner.presentation.Home.presenter.HomePresenterImp;
import com.example.yumplanner.presentation.details.view.DetialActivity;
import com.example.yumplanner.utiles.connectivity.NetworkChangeListener;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.List;
public class HomeFragment extends Fragment implements HomeView,DessertOnClickListener {

    private HomePresenter presenter;
    private ConstraintLayout background;
    private LinearLayout error;
    private RecyclerView recyclerView;
    private MaterialButton viewRecipe;
    private ShapeableImageView mealImage;
    private TextView mealName;
    private  TextView chefName;
    private NestedScrollView nestedScrollView;
    private View loadingView;
    private NetworkChangeListener networkChangeListener;
    private LottieAnimationView disconnectedPage;
   private      RecommendationsAdaptor adaptor;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View rootView, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(rootView, savedInstanceState);
        init( rootView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
         adaptor=new RecommendationsAdaptor(this);
        presenter = new HomePresenterImp(this,getActivity().getApplicationContext());
        networkChangeListener = new NetworkChangeListener();
        networkChangeListener.setNetworkStatusListener(presenter);
        presenter.getRandomData();
        recyclerView.setAdapter(adaptor);
        viewRecipe.setOnClickListener(v ->{
            presenter.reachDetails();
        });

    }
    @Override
    public void onResume() {
        super.onResume();
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        requireActivity().registerReceiver(networkChangeListener, filter);
    }
    private void init(View rootView){
        nestedScrollView = rootView.findViewById(R.id.homeView);
        mealImage = rootView.findViewById(R.id.randomMealImage);
        mealName = rootView.findViewById(R.id.mealNmae);
        recyclerView = rootView.findViewById(R.id.recommendationRecycleView);
        viewRecipe = rootView.findViewById(R.id.btnViewRecipe);
        loadingView = rootView.findViewById(R.id.loadingHome);
        error=rootView.findViewById(R.id.homeError);
        background=rootView.findViewById(R.id.homeBackground);
        disconnectedPage=rootView.findViewById(R.id.noConnection);
        chefName=rootView.findViewById(R.id.chef_name);
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
        nestedScrollView.setVisibility(NestedScrollView.VISIBLE);
        hideLoading();

    }
    @Override
    public void setDessert(List<Meal> desserts) {
        adaptor.setDessertMeals(desserts);
    }
    @Override
    public void showError() {
        loadingView.setVisibility(View.INVISIBLE);
        error.setVisibility(LinearLayout.VISIBLE);
    }
    @Override
    public void hideBackground() {
        background.setVisibility(ConstraintLayout.INVISIBLE);
    }
    @Override
    public void showBackground() {
        background.setVisibility(ConstraintLayout.VISIBLE);
    }
    @Override
    public void toDetial(DetialMeal detailMeal) {
        Intent intent=new Intent(requireContext(), DetialActivity.class);
        intent.putExtra("MEAL_OBJECT", detailMeal);
        startActivity(intent);
        requireActivity().overridePendingTransition(R.anim.zoomout,R.anim.static_animation);

    }
    @Override
    public void toDessertDetial(String id) {
        Intent intent=new Intent(requireContext(), DetialActivity.class);
        intent.putExtra("MEAL_ID", id);
        startActivity(intent);
    }
    @Override
    public void showNetworkError() {
        disconnectedPage.setVisibility(LottieAnimationView.VISIBLE);
    }
    @Override
    public void hidNetworkError() {
        disconnectedPage.setVisibility(LottieAnimationView.INVISIBLE);
    }

    @Override
    public void setChefName(String name) {
        chefName.setText( getString(R.string.chef_name,name ));
    }

    @Override
    public void goToDetails(String id) {
        Log.d("dessertId","The id ->>" +id);
        presenter.toDessertDetail(id);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }
}
