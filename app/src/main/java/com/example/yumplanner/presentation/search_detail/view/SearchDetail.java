package com.example.yumplanner.presentation.search_detail.view;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yumplanner.R;
import com.example.yumplanner.data.home.model.Meal;
import com.example.yumplanner.presentation.details.view.DetialActivity;
import com.example.yumplanner.presentation.search_detail.presenter.SearchDetailPresenter;
import com.example.yumplanner.presentation.search_detail.presenter.SearchDetailPresenterImp;
import com.google.android.material.textfield.TextInputEditText;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.rxjava3.core.Observable;

public class SearchDetail extends AppCompatActivity implements SearchDetialView ,OnDetailClick {
    SearchDetailPresenter searchDetailPresenter;
    RecyclerView searchDetailsData;
    SearchDetailAdaptor searchDetailAdaptor;
    TextInputEditText searchInput;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_search_detail);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
          searchDetailPresenter=new SearchDetailPresenterImp(this,this.getApplicationContext());
          searchDetailsData=findViewById(R.id.searchDetailrecyleVeiw);
          searchInput=findViewById(R.id.searchInput);
          searchDetailAdaptor =new SearchDetailAdaptor(this);
        searchDetailsData.setLayoutManager(new GridLayoutManager(this, 1));
        searchDetailsData.setAdapter(searchDetailAdaptor);
         String name = getIntent().getStringExtra("NAME");
        String type =  getIntent().getStringExtra("TYPE");

        Map<String, String> map = new HashMap<>();
        map.put(type, name);
        searchDetailPresenter.getMealsData(map);
        searchDetailPresenter.searchData(createSearchObservable());
    }

    @Override
    public void setItemData(List<Meal> meals) {

            searchDetailAdaptor.setData(meals);

    }

    @Override
    public void goDetials(String id) {
        searchDetailPresenter.toDetailScreen(id);

    }

    @Override
    public void toDetails(String id) {
        Intent intent=new Intent(this, DetialActivity.class);
        intent.putExtra("MEAL_ID", id);
        startActivity(intent);}
    private Observable<String> createSearchObservable() {
        return Observable.create(emitter -> {
            searchInput.addTextChangedListener(new TextWatcher() {
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

}