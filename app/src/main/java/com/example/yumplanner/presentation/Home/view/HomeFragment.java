package com.example.yumplanner.presentation.Home.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yumplanner.R;
import com.example.yumplanner.presentation.Auth.view.LoginPageDirections;
import com.example.yumplanner.presentation.details.view.DetialActivity;
import com.google.android.material.button.MaterialButton;


public class HomeFragment extends Fragment {

  RecyclerView recyclerView;

  MaterialButton viewRecipe;
  MaterialButton viewFood;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         View view=inflater.inflate(R.layout.fragment_home, container, false);
     return  view;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
         recyclerView = view.findViewById(R.id.recommendationRecycleView);
        viewRecipe=view.findViewById(R.id.btnViewRecipe);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        RecommendationsAdaptor adapter = new RecommendationsAdaptor();
        recyclerView.setAdapter(adapter);
        viewRecipe.setOnClickListener(v -> {
            Intent intent = new Intent(requireContext(), DetialActivity.class);
            startActivity(intent);

        });


    }
}