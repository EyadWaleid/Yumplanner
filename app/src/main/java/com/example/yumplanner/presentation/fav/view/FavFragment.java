package com.example.yumplanner.presentation.fav.view;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.yumplanner.R;
import com.example.yumplanner.data.home.model.DetialMeal;
import com.example.yumplanner.presentation.details.view.DetialActivity;
import com.example.yumplanner.presentation.fav.presenter.FavPresenter;
import com.example.yumplanner.presentation.fav.presenter.FavPresenterImp;
import com.example.yumplanner.utiles.SnackbarHelper;
import com.example.yumplanner.utiles.connectivity.NetworkChangeListener;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;


public class FavFragment extends Fragment  implements  FavView , OnDeleteListener {
    RecyclerView recyclerView;
    FavAdaptor favAdaptor;
    FavPresenter presenter;
    private NetworkChangeListener networkChangeListener;

    LottieAnimationView loader;
    TextView favNoData;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_fav, container, false);
     return  view ;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initWidgets(view);
        networkChangeListener = new NetworkChangeListener();
        networkChangeListener.setNetworkStatusListener(presenter);
        presenter.loadFavData();

    }

    private void initWidgets(@NonNull View view) {
        recyclerView= view.findViewById(R.id.favListview);
        favNoData=view.findViewById(R.id.favEmpty);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.requireContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setNestedScrollingEnabled(false);

        favAdaptor=new FavAdaptor(this);
        loader=view.findViewById(R.id.favLoader);
        recyclerView.setAdapter(favAdaptor);
        presenter=new FavPresenterImp(this,requireContext());
    }
    //provide data for the adaptor
    @Override
    public void setData(List<DetialMeal> detialMeals) {
        favAdaptor.setDetialMeals(detialMeals);
    }
    @Override
    public void goToDetails(DetialMeal detialMeal) {
        Intent intent=new Intent(requireContext(), DetialActivity.class);
        intent.putExtra("MEAL_OBJECT", detialMeal);
        startActivity(intent);
        requireActivity().overridePendingTransition(R.anim.static_animation,R.anim.zoomin);
    }
    @Override
    public void onResume() {
        super.onResume();
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        requireActivity().registerReceiver(networkChangeListener, filter);
    }
    @Override
    public void showLoader() {
        loader.setVisibility(LottieAnimationView.VISIBLE);

    }

    @Override
    public void hideLoader() {
        loader.setVisibility(LottieAnimationView.INVISIBLE);

    }

    @Override
    public void showBackGround() {
        recyclerView.setVisibility(RecyclerView.VISIBLE);

    }

    @Override
    public void showNoData() {
        favNoData.setVisibility(View.VISIBLE);
    }

    @Override
    public void hidNoData() {
        favNoData.setVisibility(View.INVISIBLE);
    }
    @Override
    public void hideBackGround() {
        recyclerView.setVisibility(RecyclerView.GONE);
    }
    @Override
    public void showFavSnackBar(String message ) {
        SnackbarHelper.show(getView(),message, SnackbarHelper.Type.SUCCESS);
    }
    //delete fav from adaptor
    @Override
    public void removeMealFromFav(DetialMeal detialMeal) {
        presenter.deleteFavData(detialMeal);
    }
    // go to details function
    @Override
    public void goTODetial(DetialMeal detialMeal) {
        presenter.goToDetails(detialMeal);
    }
    @Override
    public void updateNetworkState(boolean isConnected) {
        if (favAdaptor != null) {
            favAdaptor.setNetworkState(isConnected);
        }


    }}
