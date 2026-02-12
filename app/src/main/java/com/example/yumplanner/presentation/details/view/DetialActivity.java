package com.example.yumplanner.presentation.details.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
import com.example.yumplanner.data.home.model.DetialMeal;
import com.example.yumplanner.data.home.model.MealIngredient;
import com.example.yumplanner.presentation.details.presenter.DetialPresenter;
import com.example.yumplanner.presentation.details.presenter.DetialPresenterImp;
import com.example.yumplanner.utiles.SnackbarHelper;
import com.example.yumplanner.utiles.imageHelper.ImageHelper;
import com.google.android.material.button.MaterialButton;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import java.util.Calendar;
import java.util.List;

public class DetialActivity extends AppCompatActivity implements DetialView {
    RecyclerView recyclerView;
    ImageButton backBtn;
    ImageButton favBtn;
    DetialPresenter detialPresenter;
    StepsCookingAdaptor stepsCookingAdaptor;
    YouTubePlayerView youtubeWebView;
    RecyclerView cookingSteps;
    TextView mealName;
    ImageView mealImage;
    IngrediantAdaptor ingrediantAdaptor;
    Button cancelBtn  ;
    ConstraintLayout detialView;
    ConstraintLayout calender;
    MaterialButton planMeal;
    CalendarView calendarView;
    LinearLayout linearLayout;
    Button saveMeal;
    View loader;
    String date;

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

        detialPresenter = new DetialPresenterImp(this, getApplicationContext());
        checkDeliverdData();

        backBtn.setOnClickListener(v -> finish());

        getLifecycle().addObserver(youtubeWebView);
        planMeal.setOnClickListener(
                v -> {
                    detialPresenter.addCalender();

                }
        );
        saveMeal.setOnClickListener(v -> {
            detialPresenter.OnSaveVMeal();

        });
        cancelBtn.setOnClickListener(v -> {
            detialPresenter.cancelCalender();
        });
        favBtn.setOnClickListener(v -> {
            detialPresenter.favMeal();
        });
        calendarView.setOnDateChangeListener((view, year, month, dayOfMonth) -> {
            String selectedDate = year + "-" + String.format("%02d", month + 1) + "-" + String.format("%02d", dayOfMonth);
            detialPresenter.onDateSelected(selectedDate);
        });
    }

    private void checkDeliverdData() {
        if (getIntent().hasExtra("MEAL_OBJECT")) {
            DetialMeal meal = getIntent().getParcelableExtra("MEAL_OBJECT");
            detialPresenter.getData(meal);
        } else if (getIntent().hasExtra("MEAL_ID")) {
            String mealId = (String) getIntent().getSerializableExtra("MEAL_ID");
            detialPresenter.getDataById(mealId);
        } else {
            finish();
        }
    }

    private void inizilizeViews() {
        loader = findViewById(R.id.loadingDetial);
        backBtn = findViewById(R.id.btnBack);
        calender = findViewById(R.id.calenderFragment);
        detialView = findViewById(R.id.detialView);
        recyclerView = findViewById(R.id.ingrediant_view);
        youtubeWebView = findViewById(R.id.youtubeWebView);
        cookingSteps = findViewById(R.id.steps_view);
        mealImage = findViewById(R.id.headerImage);
        linearLayout = findViewById(R.id.error);
        mealName = findViewById(R.id.txtTitle);
        favBtn = findViewById(R.id.favBtn);
        planMeal = findViewById(R.id.calenderShower);
        saveMeal = findViewById(R.id.saveBtn);
        cancelBtn=findViewById(R.id.cancelBtn);
        calendarView = findViewById(R.id.calender);
        cookingSteps.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        cookingSteps.setNestedScrollingEnabled(false);
        recyclerView.setNestedScrollingEnabled(false);
        stepsCookingAdaptor = new StepsCookingAdaptor();
        cookingSteps.setAdapter(stepsCookingAdaptor);
        recyclerView.setLayoutManager(
                new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        );
        ingrediantAdaptor = new IngrediantAdaptor();
        recyclerView.setAdapter(ingrediantAdaptor);
        Calendar today = Calendar.getInstance();


        calendarView.setMinDate(today.getTimeInMillis());
        date = today.get(Calendar.YEAR) + "-" +
                String.format("%02d", today.get(Calendar.MONTH) + 1) + "-" +
                String.format("%02d", today.get(Calendar.DAY_OF_MONTH));
    }

    @Override
    public void setData(String image, String mealName) {
        ImageHelper.loadImage(mealImage, image, mealImage);
        this.mealName.setText(mealName);
    }

    @Override
    public void setIngredients(List<MealIngredient> list) {
        ingrediantAdaptor.setIngredientsList(list);

    }

    @Override
    public void setSteps(List<String> steps) {

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
    public String setDate() {
        return date;
    }

    @Override
    public void fillIcon() {
        favBtn.setImageResource(R.drawable.colored_fav);
    }

    @Override
    public void unFillIcon() {
        favBtn.setImageResource(R.drawable.unfill_fav);

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

    @Override
    public void hideCalender() {
        calender.setVisibility(ConstraintLayout.GONE);
    }

    @Override
    public void showSuccessSnackbar() {
        SnackbarHelper.show(this, "The meal added ", SnackbarHelper.Type.SUCCESS);
    }

    @Override
    public void showFailureSnackbar() {
        SnackbarHelper.show(this, "There is meal in this data remove it first ", SnackbarHelper.Type.ERROR);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        detialPresenter.clear();
    }
}