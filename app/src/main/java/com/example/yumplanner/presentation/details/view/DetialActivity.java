package com.example.yumplanner.presentation.details.view;

import static androidx.core.content.ContentProviderCompat.requireContext;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CalendarView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yumplanner.R;
import com.example.yumplanner.data.dto.DetialMealDTO;
import com.example.yumplanner.data.model.DetailMeal;
import com.example.yumplanner.data.model.Ingredient;
import com.example.yumplanner.presentation.details.presenter.DetialPresenter;
import com.example.yumplanner.presentation.details.presenter.DetialPresenterImp;
import com.example.yumplanner.utiles.imageHelper.ImageHelper;
import com.google.android.material.button.MaterialButton;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import java.util.Calendar;
import java.util.List;

public class DetialActivity extends AppCompatActivity implements  DetialView {
    RecyclerView recyclerView;
    ImageButton backBtn;
    DetialPresenter detialPresenter;
    StepsCookingAdaptor stepsCookingAdaptor;
    YouTubePlayerView youtubeWebView;
    RecyclerView cookingSteps;
    TextView mealName;
    ImageView mealImage;
    IngrediantAdaptor ingrediantAdaptor;
    ConstraintLayout detialView;
    ConstraintLayout calender;
    MaterialButton planMeal;
    CalendarView calendarView;
    LinearLayout linearLayout;
    View loader;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_detial);
        EdgeToEdge.enable(this);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.detialMain), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        inizilizeViews();

        detialPresenter=new DetialPresenterImp(this);
        checkDeliverdData();


        backBtn.setOnClickListener(v -> finish());

        getLifecycle().addObserver(youtubeWebView);
        planMeal.setOnClickListener(
                v -> {
                    detialPresenter.addCalender();

                }
        );

    }
    private void  checkDeliverdData(){
        if (getIntent().hasExtra("MEAL_OBJECT")) {
            DetialMealDTO meal = getIntent().getParcelableExtra("MEAL_OBJECT");
            detialPresenter.getData(meal);
        }
        else if (getIntent().hasExtra("MEAL_ID")) {
            String mealId = (String) getIntent().getSerializableExtra("MEAL_ID");
            detialPresenter.getDataById(mealId);
        }
        else {
            Toast.makeText(this, "Error: No data", Toast.LENGTH_SHORT).show();
            finish();
        }
    }
    private  void inizilizeViews(){
        loader=findViewById(R.id.loadingDetial);
        backBtn = findViewById(R.id.btnBack);
        calender=findViewById(R.id.calenderFragment);
        detialView=findViewById(R.id.detialView);
        recyclerView = findViewById(R.id.ingrediant_view);
        youtubeWebView = findViewById(R.id.youtubeWebView);
        cookingSteps = findViewById(R.id.steps_view);
        mealImage=findViewById(R.id.headerImage);
        linearLayout=findViewById(R.id.error);
        mealName=findViewById(R.id.txtTitle);
        planMeal=findViewById(R.id.calenderShower);
        calendarView=findViewById(R.id.calender);
        cookingSteps.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        cookingSteps.setNestedScrollingEnabled(false);
        recyclerView.setNestedScrollingEnabled(false);

        stepsCookingAdaptor =new StepsCookingAdaptor();
        cookingSteps.setAdapter(stepsCookingAdaptor);
        recyclerView.setLayoutManager(
                new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        );
         ingrediantAdaptor= new IngrediantAdaptor();
        recyclerView.setAdapter(ingrediantAdaptor);
        Calendar today = Calendar.getInstance();
        today.set(Calendar.HOUR_OF_DAY, 0);
        today.set(Calendar.MINUTE, 0);
        today.set(Calendar.SECOND, 0);
        today.set(Calendar.MILLISECOND, 0);

        calendarView.setMinDate(today.getTimeInMillis());


    }
    @Override
    public void setData(String image, String mealName) {
        ImageHelper.loadImage(mealImage,image,mealImage);
        this.mealName.setText(mealName);
    }
    @Override
    public void setIngredients(List<Ingredient> list) {
         ingrediantAdaptor.setIngredientsList(list);

    }
    @Override
    public void setSteps(List<String>steps) {
        stepsCookingAdaptor.setStepList(steps);
    }
    @Override
    public void setVideo() {

    }

    @Override
    public void showLoader() {
        loader.setVisibility(View.VISIBLE);
    }
    @Override
    public void hideLoader() {
        loader.setVisibility(View.INVISIBLE);

    }

    @Override
    public void hideView() {
        detialView.setVisibility(ConstraintLayout.INVISIBLE);
    }
    @Override
    public void showView() {
        detialView.setVisibility(ConstraintLayout.VISIBLE);
    }

    @Override
    public void showError() {
        detialView.setVisibility(ConstraintLayout.INVISIBLE);
        linearLayout.setVisibility(LinearLayout.VISIBLE);
    }

    @Override
    public void showCalender() {
        calender.setVisibility(ConstraintLayout.VISIBLE);

    }


}