package com.example.yumplanner.presentation.details.view;

import static java.security.AccessController.getContext;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yumplanner.R;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

public class DetialActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ImageButton backBtn;
    YouTubePlayerView youtubeWebView;
    RecyclerView cookingSteps;
    WebView webView;
    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_detial);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        backBtn=findViewById(R.id.btnBack);
        recyclerView =findViewById(R.id.ingrediant_view);
         webView=findViewById(R.id.youtubeWebView);
        cookingSteps=findViewById(R.id.steps_view);
        recyclerView.setLayoutManager(
                new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        );
        recyclerView.setAdapter(new IngrediantAdaptor());
       /* String video = "<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/watch?v=1IszT_guI08\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"strict-origin-when-cross-origin\" allowfullscreen></iframe>\n";
       webView.getSettings().setJavaScriptEnabled(true);
       webView.setWebChromeClient(new WebChromeClient());
        webView.loadData(video, "text/html","utf-8");
*/
        cookingSteps.setLayoutManager( new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        cookingSteps.setNestedScrollingEnabled(false);
        cookingSteps.setAdapter(new StepsCookingAdaptor());
        backBtn.setOnClickListener(v -> finish());


    }
    public String extractVideoId(String fullUrl) {
        if (fullUrl.contains("v=")) {
            return fullUrl.split("v=")[1].split("&")[0];
        } else if (fullUrl.contains("embed/")) {
            return fullUrl.split("embed/")[1].split("\\?")[0];
        }
        return fullUrl;
    }
}