package com.example.yumplanner.presentation.plan.view;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.yumplanner.R;
import com.example.yumplanner.data.home.model.DetialMeal;
import com.example.yumplanner.presentation.details.view.DetialActivity;
import com.example.yumplanner.presentation.plan.presenter.PlanPresenter;
import com.example.yumplanner.presentation.plan.presenter.PlanPresenterImp;
import com.example.yumplanner.utiles.SnackbarHelper;
import com.example.yumplanner.utiles.connectivity.NetworkChangeListener;
import com.example.yumplanner.utiles.imageHelper.ImageHelper;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.imageview.ShapeableImageView;

import java.io.File;
import java.util.Calendar;

public class CalenderFragment extends Fragment  implements CalenderView{
    CalendarView calendarView;
    MaterialCardView cardView;
    MaterialButton deleteBtn;
    ShapeableImageView mealImage;
    TextView mealName;
    TextView noData;
    PlanPresenter planPresenter;
    String date;
    NetworkChangeListener networkChangeListener;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         View view =inflater.inflate(R.layout.fragment_calender, container, false);
     return  view;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        networkChangeListener = new NetworkChangeListener();
        networkChangeListener.setNetworkStatusListener(planPresenter);
        planPresenter.getMeal(date);
        calendarView.setOnDateChangeListener((view1, year, month, dayOfMonth) -> {
            String selectedDate = year + "-" + String.format("%02d", month + 1) + "-" + String.format("%02d", dayOfMonth);
            planPresenter.getMeal(selectedDate);
        });
        deleteBtn.setOnClickListener(v -> {
            planPresenter.deleteBtn();
        });
        cardView.setOnClickListener(v -> {
            planPresenter.goToDetails();
        });

    }

    @Override
    public void removeDeletICon() {
        deleteBtn.setVisibility(Button.INVISIBLE);
        deleteBtn.setClickable(false);
    }

    @Override
    public void adddDeleteICon() {
        deleteBtn.setVisibility(Button.VISIBLE);
        deleteBtn.setClickable(true);


    }

    @Override
    public void onResume() {
        super.onResume();
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        requireActivity().registerReceiver(networkChangeListener, filter);
    }

    void initView(View view){

        calendarView=view.findViewById(R.id.calender_view);
        cardView=view.findViewById(R.id.plan_meal_image);
        deleteBtn=view.findViewById(R.id.deleteBtn);
        mealImage=view.findViewById(R.id.recipeImageplanner);
        mealName=view.findViewById(R.id.recipePlannedName);
        noData=view.findViewById(R.id.noData);
        planPresenter=new PlanPresenterImp(this,this.requireActivity().getApplicationContext());

        Calendar today = Calendar.getInstance();

         date = today.get(Calendar.YEAR) + "-" +
                String.format("%02d", today.get(Calendar.MONTH) + 1) + "-" +//the + 1 cause the Calendar is zero based
                String.format("%02d", today.get(Calendar.DAY_OF_MONTH));
    }
    @Override
    public void setImage(String imageUrl) {

        Glide.with(requireContext())
                .load(new File(imageUrl))
                .placeholder(R.drawable.unnamed)
                .error(R.drawable.unnamed)
                .into(mealImage);

    }
    @Override
    public void setText(String mealName) {
        this.mealName.setText(mealName);
    }
    @Override
    public void showData() {
        cardView.setVisibility(CardView.VISIBLE);
    }
    @Override
    public void hideData() {
        cardView.setVisibility(CardView.INVISIBLE);
    }
    @Override
    public void showNoData() {
        noData.setVisibility(TextView.VISIBLE);}
    @Override
    public void hideNoData() {
        noData.setVisibility(TextView.INVISIBLE);
    }
    @Override
    public  void showSuccessSnackBar(){
        SnackbarHelper.show(this.getView(),"Meal deleted sucessfully", SnackbarHelper.Type.SUCCESS);
    }
    @Override
    public void toDetail(DetialMeal detailMeal) {
        Intent intent=new Intent(requireContext(), DetialActivity.class);
        intent.putExtra("MEAL_OBJECT", detailMeal);
        startActivity(intent);
        requireActivity().overridePendingTransition(R.anim.from_right,R.anim.from_left);

    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        planPresenter.onDestroy();
    }

}